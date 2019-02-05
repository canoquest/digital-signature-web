package br.com.unicred.d4sign.client.facade;

import java.util.Map;

import org.apache.log4j.Logger;

import br.com.unicred.d4sign.client.enumeration.D4SignParameterEnum;
import br.com.unicred.d4sign.client.enumeration.D4SignPathEnum;
import br.com.unicred.d4sign.client.model.request.DocumentBinaryRequest;
import br.com.unicred.d4sign.client.model.request.DocumentDownloadRequest;
import br.com.unicred.d4sign.client.model.request.DocumentPathRequest;
import br.com.unicred.d4sign.client.model.request.SendDocumentSignatureRequest;
import br.com.unicred.d4sign.client.model.response.DocumentBinaryResponse;
import br.com.unicred.d4sign.client.model.response.DocumentDownloadResponse;
import br.com.unicred.d4sign.client.model.response.DocumentPathResponse;
import br.com.unicred.d4sign.client.model.response.DocumentResponse;
import br.com.unicred.d4sign.client.model.response.SendDocumentSignatureResponse;
import br.com.unicred.rest.core.enumeration.APIClientParameterEnum;
import br.com.unicred.rest.core.exception.APIClientException;
import br.com.unicred.rest.core.exception.FacadeException;
import br.com.unicred.rest.core.facade.APIClientFacade;

public class DocumentFacade extends D4SignFacade {
	
	private static final Logger LOGGER = Logger.getLogger(DocumentFacade.class);
	
	private static final String HOST = D4SignPathEnum.HOST.getValue();
	
	private static final String APPLICATION_JSON = APIClientParameterEnum.APPLICATION_JSON.getValue();	
	
	private static final String UUID_SAFE = D4SignParameterEnum.UUID_SAFE.getValue();

	public DocumentFacade() {
		super();
	}
	
	public DocumentPathResponse uploadFilePath(DocumentPathRequest documentPathRequest) throws FacadeException {
		try {
			Map<String, Object> queryParameters = getQueryParameters();
			Map<String, Object> headerParameters = getHeaderParameters();
			String path = D4SignPathEnum.DOCUMENTS.getValue() + "/" + UUID_SAFE + "/upload";
			
			APIClientFacade clientFacade = new APIClientFacade(HOST, path, APPLICATION_JSON);
			DocumentPathResponse documentPathResponse = (DocumentPathResponse) clientFacade.post(documentPathRequest, queryParameters, headerParameters, DocumentPathResponse.class);			
			return documentPathResponse;
		} catch (APIClientException ex) {
			LOGGER.error("Erro consumir o método uploadFilePath da API D4Sign. ", ex);
			throw new FacadeException("Erro consumir o método uploadFilePath da API D4Sign. ");
		}
	}
	
	public DocumentBinaryResponse uploadFileBinary(DocumentBinaryRequest documentBinaryRequest) throws FacadeException {
		try {
			Map<String, Object> queryParameters = getQueryParameters();
			Map<String, Object> headerParameters = getHeaderParameters();
			String path = D4SignPathEnum.DOCUMENTS.getValue() + "/" + UUID_SAFE + "/uploadbinary";
			
			APIClientFacade clientFacade = new APIClientFacade(HOST, path, APPLICATION_JSON);
			DocumentBinaryResponse documentBinaryResponse = (DocumentBinaryResponse) clientFacade.post(documentBinaryRequest, queryParameters, headerParameters, DocumentBinaryResponse.class);			
			return documentBinaryResponse;
		} catch (APIClientException ex) {
			LOGGER.error("Erro consumir o método uploadFileBinary da API D4Sign. ", ex);
			throw new FacadeException("Erro consumir o método uploadFileBinary da API D4Sign. ");
		}
	}
	
	public SendDocumentSignatureResponse sendToSigner(SendDocumentSignatureRequest documentSignatureRequest, String uuidDocument) throws FacadeException {
		try {
			Map<String, Object> queryParameters = getQueryParameters();
			Map<String, Object> headerParameters = getHeaderParameters();
			String path = D4SignPathEnum.DOCUMENTS.getValue() + "/" + uuidDocument + "/sendtosigner";
			
			APIClientFacade clientFacade = new APIClientFacade(HOST, path, APPLICATION_JSON);
			SendDocumentSignatureResponse documentSignatureResponse = (SendDocumentSignatureResponse) clientFacade.post(documentSignatureRequest, queryParameters, headerParameters, SendDocumentSignatureResponse.class);			
			return documentSignatureResponse;
		} catch (APIClientException ex) {
			LOGGER.error("Erro consumir o método sendtosigner da API D4Sign. ", ex);
			throw new FacadeException("Erro consumir o método sendtosigner da API D4Sign. ");
		}
	}
	
	public DocumentResponse getDocument(String uuidDocument) throws FacadeException {
		try {
			Map<String, Object> queryParameters = getQueryParameters();
			Map<String, Object> headerParameters = getHeaderParameters();
			String path = D4SignPathEnum.DOCUMENTS.getValue() + "/" + uuidDocument;
			
			APIClientFacade clientFacade = new APIClientFacade(HOST, path, APPLICATION_JSON);
			DocumentResponse documentResponse = (DocumentResponse) clientFacade.get(queryParameters, headerParameters, DocumentResponse.class);			
			return documentResponse;
		} catch (APIClientException ex) {
			LOGGER.error("Erro consumir o método sendtosigner da API D4Sign. ", ex);
			throw new FacadeException("Erro consumir o método sendtosigner da API D4Sign. ");
		}
	}
	
	public DocumentDownloadResponse getDocumentForDownload(DocumentDownloadRequest documentDownloadRequest, String uuidDocument) throws FacadeException {
		try {
			Map<String, Object> queryParameters = getQueryParameters();
			Map<String, Object> headerParameters = getHeaderParameters();
			String path = D4SignPathEnum.DOCUMENTS.getValue() + "/" + uuidDocument + "/download";
			
			APIClientFacade clientFacade = new APIClientFacade(HOST, path, APPLICATION_JSON);
			DocumentDownloadResponse documentDownloadResponse = (DocumentDownloadResponse) clientFacade.post(documentDownloadRequest, queryParameters, headerParameters, DocumentDownloadResponse.class);			
			return documentDownloadResponse;
		} catch (APIClientException ex) {
			LOGGER.error("Erro consumir o método sendtosigner da API D4Sign. ", ex);
			throw new FacadeException("Erro consumir o método sendtosigner da API D4Sign. ");
		}
	}

}