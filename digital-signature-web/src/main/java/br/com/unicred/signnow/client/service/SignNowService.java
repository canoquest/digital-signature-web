package br.com.unicred.signnow.client.service;

import br.com.unicred.digitalsignature.core.model.dto.DocumentUploadedDTO;
import br.com.unicred.digitalsignature.core.model.dto.ProcessSignatureDTO;
import br.com.unicred.digitalsignature.core.service.CoreServiceInterface;
import br.com.unicred.rest.core.exception.FacadeException;
import br.com.unicred.signnow.client.facade.DocumentFacade;
import br.com.unicred.signnow.client.model.request.DocumentUploadRequest;
import br.com.unicred.signnow.client.model.response.DocumentUploadResponse;

public class SignNowService implements CoreServiceInterface {

	public SignNowService() {
		super();
	}

	@Override
	public ProcessSignatureDTO processDocumentSignature(String fileBase64, byte[] fileByteArray, String fileName, String email) {
		DocumentUploadedDTO documentUploadedDTO = uploadDocument(fileBase64, fileByteArray, fileName, email);
		Boolean successResult = Boolean.FALSE;
		if (documentUploadedDTO != null && documentUploadedDTO.getUrlWebViewProvider() != null 
				&& !documentUploadedDTO.getUrlWebViewProvider().isEmpty()) {
			successResult = Boolean.TRUE;
		}		
		ProcessSignatureDTO processSignatureDTO = new ProcessSignatureDTO();
		processSignatureDTO.setProccessResult(successResult);
		return processSignatureDTO;
	}

	@Override
	public DocumentUploadedDTO uploadDocument(String fileBase64, byte[] fileByteArray, String fileName, String email) {
		DocumentUploadedDTO documentUploadedDTO = null;
		try {
			DocumentUploadRequest documentUploadRequest = new DocumentUploadRequest();
			documentUploadRequest.setFile(fileBase64);
			
			DocumentFacade documentFacade = new DocumentFacade();
			DocumentUploadResponse documentUploadResponse = documentFacade.uploadDocument(documentUploadRequest);
			
			documentUploadedDTO = new DocumentUploadedDTO();
			documentUploadedDTO.setUrlWebViewProvider(documentUploadResponse.getDocument());
		} catch (FacadeException ex) {
			
		}		
		return documentUploadedDTO;
	}	

}