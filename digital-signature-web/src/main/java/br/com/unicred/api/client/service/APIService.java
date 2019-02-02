package br.com.unicred.api.client.service;

import org.apache.log4j.Logger;

import br.com.unicred.api.client.facade.DocumentFacade;
import br.com.unicred.api.client.model.request.DocumentRequest;
import br.com.unicred.rest.core.exception.FacadeException;

public class APIService {
	
	private static final Logger LOGGER = Logger.getLogger(APIService.class);
	
	public APIService() {
		super();
	}
	
	public void sendDocument(String documentBase64, byte[] documentByteArray, String fileName) {
		try {
			DocumentRequest documentRequest = new DocumentRequest();
			documentRequest.setDocumentBase64(documentBase64);			
			documentRequest.setFileName(fileName);
			
			DocumentFacade documentFacade = new DocumentFacade();
			documentFacade.sendDocument(documentRequest);
		} catch (FacadeException ex) {
			LOGGER.error("Erro ao consumir a API de documentos", ex);			
		}		
	}

}