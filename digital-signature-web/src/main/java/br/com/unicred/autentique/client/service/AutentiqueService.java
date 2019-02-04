package br.com.unicred.autentique.client.service;

import br.com.unicred.autentique.client.facade.DocumentFacade;
import br.com.unicred.autentique.client.model.request.DocumentRequest;
import br.com.unicred.autentique.client.model.response.DocumentResponse;
import br.com.unicred.digitalsignature.core.model.dto.DocumentUploadedDTO;
import br.com.unicred.digitalsignature.core.model.dto.ProcessSignatureDTO;
import br.com.unicred.digitalsignature.core.service.CoreServiceInterface;
import br.com.unicred.rest.core.exception.FacadeException;

public class AutentiqueService implements CoreServiceInterface {

	public AutentiqueService() {
		super();
	}

	@Override
	public ProcessSignatureDTO processDocumentSignature(String fileBase64, byte[] fileByteArray, String fileName, String email) {
		DocumentUploadedDTO documentUploadedDTO = uploadDocument(fileBase64, fileByteArray, fileName, email);
		String idDocument = (documentUploadedDTO != null ? documentUploadedDTO.getIdDocument() : null);
		
		ProcessSignatureDTO processSignatureDTO = new ProcessSignatureDTO();
		processSignatureDTO.setDocumentUploadedDTO(documentUploadedDTO);
		if (idDocument != null) {
			processSignatureDTO.setProccessResult(Boolean.TRUE);
		} else {
			processSignatureDTO.setProccessResult(Boolean.FALSE);
		}
		
		return processSignatureDTO;
	}

	@Override
	public DocumentUploadedDTO uploadDocument(String fileBase64, byte[] fileByteArray, String fileName, String email) {
		DocumentUploadedDTO documentUploadedDTO = null;
		try {
			DocumentRequest documentRequest = new DocumentRequest();
			documentRequest.setNome("Arquivo 03");
			documentRequest.setEmail(email);
			documentRequest.setFuncao("assinar");
			documentRequest.setArquivo(fileBase64);
			documentRequest.setMensagem("Você tem um documento pendente de assinatura.");
			documentRequest.setRejeitavel(Boolean.TRUE);
			documentRequest.setLembrete_assinatura(Boolean.FALSE);
			documentRequest.setLembrete_vencimento(Boolean.FALSE);
			documentRequest.setDias_vencimento(7);
			documentRequest.setData_vencimento("");
			
			DocumentFacade documentFacade = new DocumentFacade();
			DocumentResponse documentResponse = documentFacade.upload(documentRequest);
			
			documentUploadedDTO = new DocumentUploadedDTO();
			documentUploadedDTO.setIdDocument(documentResponse.getUuid());
			documentUploadedDTO.setTokenAssinatura(documentResponse.getAssinatura());
		} catch (FacadeException ex) {
			
		}		
		return documentUploadedDTO;
	}

}