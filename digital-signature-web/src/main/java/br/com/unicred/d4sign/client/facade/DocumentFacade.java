package br.com.unicred.d4sign.client.facade;

import java.util.Map;

import br.com.unicred.d4sign.client.enumeration.D4SignParameterEnum;
import br.com.unicred.d4sign.client.enumeration.D4SignPathEnum;
import br.com.unicred.d4sign.client.model.request.DocumentBinaryRequest;
import br.com.unicred.d4sign.client.model.request.DocumentPathRequest;
import br.com.unicred.d4sign.client.model.request.SendDocumentSignatureRequest;
import br.com.unicred.d4sign.client.model.response.DocumentBinaryResponse;
import br.com.unicred.d4sign.client.model.response.DocumentPathResponse;
import br.com.unicred.d4sign.client.model.response.SendDocumentSignatureResponse;
import br.com.unicred.rest.core.enumeration.ConfigureAPIEnum;
import br.com.unicred.rest.core.exception.APIClientException;
import br.com.unicred.rest.core.exception.FacadeException;
import br.com.unicred.rest.core.facade.ClientAPIFacade;

public class DocumentFacade extends D4SignFacade {
	
	private static final String HOST = D4SignPathEnum.HOST.getValue();
	
	private static final String APPLICATION_JSON = ConfigureAPIEnum.APPLICATION_JSON.getValue();	
	
	private static final String UUID_SAFE = D4SignParameterEnum.UUID_SAFE.getValue();

	public DocumentFacade() {
		super();
	}
	
	public DocumentPathResponse uploadFilePath(DocumentPathRequest documentPathRequest) throws FacadeException {
		try {
			Map<String, Object> parameters = getParameters();			
			String path = D4SignPathEnum.DOCUMENTS.getValue() + UUID_SAFE + "/upload";
			
			ClientAPIFacade clientFacade = new ClientAPIFacade(HOST, path, APPLICATION_JSON);
			DocumentPathResponse documentPathResponse = (DocumentPathResponse) clientFacade.post(documentPathRequest, parameters, DocumentPathResponse.class);			
			return documentPathResponse;
		} catch (APIClientException ex) {
			throw new FacadeException("Erro consumir o m�todo uploadFile da API D4Sign");
		}
	}
	
	public DocumentBinaryResponse uploadFileBinary(DocumentBinaryRequest documentBinaryRequest) throws FacadeException {
		try {
			Map<String, Object> parameters = getParameters();
			String path = D4SignPathEnum.DOCUMENTS.getValue() + UUID_SAFE + "/uploadbinary";
			
			ClientAPIFacade clientFacade = new ClientAPIFacade(HOST, path, APPLICATION_JSON);
			DocumentBinaryResponse documentBinaryResponse = (DocumentBinaryResponse) clientFacade.post(documentBinaryRequest, parameters, DocumentBinaryResponse.class);			
			return documentBinaryResponse;
		} catch (APIClientException ex) {
			throw new FacadeException("Erro consumir o m�todo uploadFile da API D4Sign");
		}
	}
	
	public SendDocumentSignatureResponse sendToSigner(SendDocumentSignatureRequest documentSignatureRequest, String uuidDocument) throws FacadeException {
		try {
			Map<String, Object> parameters = getParameters();
			String path = D4SignPathEnum.DOCUMENTS.getValue() + "/" + uuidDocument + "/sendtosigner";
			
			ClientAPIFacade clientFacade = new ClientAPIFacade(HOST, path, APPLICATION_JSON);
			SendDocumentSignatureResponse documentSignatureResponse = (SendDocumentSignatureResponse) clientFacade.post(documentSignatureRequest, parameters, SendDocumentSignatureResponse.class);			
			return documentSignatureResponse;
		} catch (APIClientException ex) {
			throw new FacadeException("Erro consumir o m�todo sendtosigner da API D4Sign");
		}
	}

}