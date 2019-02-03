package br.com.unicred.d4sign.client.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.unicred.d4sign.client.enumeration.D4SignParameterEnum;
import br.com.unicred.d4sign.client.facade.DocumentFacade;
import br.com.unicred.d4sign.client.facade.SignatoryFacade;
import br.com.unicred.d4sign.client.facade.WebhookFacade;
import br.com.unicred.d4sign.client.model.request.DocumentBinaryRequest;
import br.com.unicred.d4sign.client.model.request.SendDocumentSignatureRequest;
import br.com.unicred.d4sign.client.model.request.SignatoryRequest;
import br.com.unicred.d4sign.client.model.request.SignerRequest;
import br.com.unicred.d4sign.client.model.request.WebhookRequest;
import br.com.unicred.d4sign.client.model.response.DocumentBinaryResponse;
import br.com.unicred.d4sign.client.model.response.SendDocumentSignatureResponse;
import br.com.unicred.d4sign.client.model.response.SignatoryResponse;
import br.com.unicred.d4sign.client.model.response.SignerResponse;
import br.com.unicred.d4sign.client.model.response.WebhookResponse;
import br.com.unicred.digitalsignature.core.model.dto.DocumentUploadedDTO;
import br.com.unicred.digitalsignature.core.model.dto.ProcessSignatureDTO;
import br.com.unicred.digitalsignature.core.service.CoreServiceInterface;
import br.com.unicred.rest.core.exception.FacadeException;

public class D4SignService implements CoreServiceInterface {	
	
	private static final Logger LOGGER = Logger.getLogger(D4SignService.class);

	public D4SignService() {
		super();
	}	
	
	@Override
	public ProcessSignatureDTO processDocumentSignature(String fileBase64, byte[] fileByteArray, String fileName, String email) {	
		DocumentUploadedDTO  documentUploadedDTO = uploadFileBinary(fileBase64, fileByteArray, fileName, email);	
		String idDocument = (documentUploadedDTO != null ? documentUploadedDTO.getIdDocument() : null);
		
		Boolean successWebHook = Boolean.FALSE;	
		Boolean successDocument = Boolean.FALSE;
		if (idDocument != null && !idDocument.isEmpty()) {
			successWebHook = createWebHook(idDocument);
			successDocument = Boolean.TRUE;
		}
		
		Boolean successSignatory = Boolean.FALSE;
		if (successWebHook) {
			successSignatory = createSignatoryList(email, idDocument);
		}	
		
		Boolean documentSignatureSend = Boolean.FALSE;
		if (successSignatory) {
			documentSignatureSend = sendToSigner(idDocument);
		}
		
		ProcessSignatureDTO processSignatureDTO = new ProcessSignatureDTO();
		processSignatureDTO.setDocumentUploadedDTO(documentUploadedDTO);
		if (successDocument && successWebHook && successSignatory && documentSignatureSend) {
			processSignatureDTO.setProccessResult(Boolean.TRUE);
		} else {
			processSignatureDTO.setProccessResult(Boolean.FALSE);
		}
		
		return processSignatureDTO;
	}

	@Override
	public DocumentUploadedDTO uploadFileBinary(String fileBase64, byte[] fileByteArray, String fileName, String email) {
		DocumentUploadedDTO documentUploadedDTO = null;		
		try {
			DocumentBinaryRequest documentRequest = new DocumentBinaryRequest();
			documentRequest.setBase64_binary_file(fileBase64);
			documentRequest.setMime_type(D4SignParameterEnum.APPLICATION_PDF.getValue());
			documentRequest.setName(fileName);
			documentRequest.setUuid_folder(D4SignParameterEnum.UUID_FOLDER.getValue());
			
			DocumentFacade documentFacade = new DocumentFacade();
			DocumentBinaryResponse documentResponse = documentFacade.uploadFileBinary(documentRequest);
			
			documentUploadedDTO = new DocumentUploadedDTO();
			documentUploadedDTO.setIdDocument(documentResponse.getUuid());
			documentUploadedDTO.setMessage(documentResponse.getMessage());
		} catch (FacadeException ex) {
			LOGGER.error("Erro ao enviar o documento. ", ex);
		}
		return documentUploadedDTO;
	}
	
	private Boolean createWebHook(String idDocument) {
		Boolean successResult = Boolean.FALSE;		
		String successMessage = "Webhook successfully registered";
		try {		
			WebhookRequest webhookRequest = new WebhookRequest();
			webhookRequest.setUrl(D4SignParameterEnum.URL_WEB_HOOK.getValue());
					
			WebhookFacade webhookFacade = new WebhookFacade();
			WebhookResponse webhookResponse = webhookFacade.createWebhook(webhookRequest, idDocument);
			
			if (webhookResponse != null && webhookResponse.getMessage() != null 
					&& webhookResponse.getMessage().equalsIgnoreCase(successMessage)) {
				successResult = Boolean.TRUE;
			}
		} catch (FacadeException ex) {
			LOGGER.error("Erro ao cadastrar o webhook. ", ex);
		}	
		return successResult;
	}
	
	private Boolean createSignatoryList(String email, String idDocument) {
		Boolean successResult = Boolean.FALSE;	
		String successMessage = "created";
		try {
			List<SignerRequest> listSignerRequest = new ArrayList<SignerRequest>();
			
			SignerRequest signerRequest1 = new SignerRequest();
			signerRequest1.setEmail(email);
			signerRequest1.setAct("1");
			signerRequest1.setForeign("1");
			signerRequest1.setCertificadoicpbr("0");
			signerRequest1.setAssinatura_presencial("0");
			signerRequest1.setDocauth("0");
			signerRequest1.setDocauthandselfie("0");
			signerRequest1.setEmbed_methodauth("email");
			signerRequest1.setEmbed_smsnumber("");
			signerRequest1.setUpload_allow("0");
			signerRequest1.setUpload_obs("Contrato Social");
			listSignerRequest.add(signerRequest1);		
			
			SignatoryRequest signatoryRequest = new SignatoryRequest();
			signatoryRequest.setSigners(listSignerRequest);
			
			SignatoryFacade signatoryFacade = new SignatoryFacade();
			SignatoryResponse signatoryResponse = signatoryFacade.createSignatoryList(signatoryRequest, idDocument);
			
			if (signatoryResponse != null && signatoryResponse.getMessage() != null 
					&& !signatoryResponse.getMessage().isEmpty()) {
				List<SignerResponse> listSignerResponse = signatoryResponse.getMessage();
				for (SignerResponse signerResponse : listSignerResponse) {
					if (signatoryResponse != null && signerResponse.getStatus() != null 
							&& !signerResponse.getStatus().isEmpty()) {
						if (signerResponse.getStatus().equalsIgnoreCase(successMessage)) {
							successResult = Boolean.TRUE;
						}
					}
				}
			}
			
		} catch (FacadeException ex) {
			LOGGER.error("Erro ao cadastrar a lista de signat�rios. ", ex);
		}	
		return successResult;
	}
	
	private Boolean sendToSigner(String idDocument) {
		Boolean successResult = Boolean.FALSE;
		String successMessage = "File sent to successfully signing";
		try {
			SendDocumentSignatureRequest documentSignatureRequest = new SendDocumentSignatureRequest();
			documentSignatureRequest.setMessage("Voc� tem um novo documento pendente de assinatura.");
			documentSignatureRequest.setSkip_email("0");
			documentSignatureRequest.setWorkflow("0");
			documentSignatureRequest.setTokenAPI(D4SignParameterEnum.TOKEN_API.getValue());
			
			DocumentFacade documentFacade = new DocumentFacade();
			SendDocumentSignatureResponse documentSignatureResponse = documentFacade.sendToSigner(documentSignatureRequest, idDocument);
			if (documentSignatureResponse != null && documentSignatureResponse.getMessage() != null 
					&& !documentSignatureResponse.getMessage().isEmpty()) {
				if (documentSignatureResponse.getMessage().equalsIgnoreCase(successMessage)) {
					successResult = Boolean.TRUE;
				}
			}
		} catch (FacadeException ex) {
			LOGGER.error("Erro ao enviar o documento para a assinatura. ", ex);
		}		
		return successResult;
	}

}