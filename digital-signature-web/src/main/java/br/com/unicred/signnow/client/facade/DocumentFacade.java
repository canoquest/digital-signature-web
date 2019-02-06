package br.com.unicred.signnow.client.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import br.com.unicred.d4sign.client.model.response.SendDocumentSignatureResponse;
import br.com.unicred.rest.core.enumeration.APIClientParameterEnum;
import br.com.unicred.rest.core.exception.APIClientException;
import br.com.unicred.rest.core.exception.FacadeException;
import br.com.unicred.rest.core.facade.APIClientFacade;
import br.com.unicred.signnow.client.enumeration.SignNowPathEnum;
import br.com.unicred.signnow.client.model.request.DocumentUploadRequest;
import br.com.unicred.signnow.client.model.response.DocumentDetailResponse;
import br.com.unicred.signnow.client.model.response.DocumentResponse;
import br.com.unicred.signnow.client.model.response.DocumentUploadResponse;

public class DocumentFacade extends SignNowFacade {
	
	private static final Logger LOGGER = Logger.getLogger(DocumentFacade.class);
	
	private static final String HOST = SignNowPathEnum.HOST.getValue();
	
	private static final String APPLICATION_JSON = APIClientParameterEnum.APPLICATION_JSON.getValue();		

	public DocumentFacade() {
		super();
	}
	
	public DocumentUploadResponse uploadDocument(DocumentUploadRequest documentUploadRequest) throws FacadeException {
		try {
			Map<String, Object> queryParameters = getQueryParameters();
			Map<String, Object> headerParameters = getHeaderParameters();
			String path = SignNowPathEnum.DOCUMENT.getValue(); 
			Boolean converterToJSON = Boolean.FALSE;			
			
			APIClientFacade clientFacade = new APIClientFacade(HOST, path, APPLICATION_JSON);
			DocumentUploadResponse documentUploadResponse = (DocumentUploadResponse) clientFacade.post(documentUploadRequest, queryParameters, headerParameters, DocumentUploadResponse.class, converterToJSON);			
			return documentUploadResponse;
		} catch (APIClientException ex) {
			LOGGER.error("Erro consumir o método uploadFilePath da API D4Sign. ", ex);
			throw new FacadeException("Erro consumir o método uploadFilePath da API D4Sign. ");
		}
	}
	
	public DocumentResponse listDocuments() throws FacadeException {
		try {
			Map<String, Object> queryParameters = getQueryParameters();
			Map<String, Object> headerParameters = getHeaderParameters();
			String path = SignNowPathEnum.DOCUMENTS.getValue();
			
			APIClientFacade clientFacade = new APIClientFacade(HOST, path, APPLICATION_JSON);
			DocumentDetailResponse[] arrayDocumentoDetailResponse = (DocumentDetailResponse[]) clientFacade.get(queryParameters, headerParameters, DocumentDetailResponse[].class);			
			
			List<DocumentDetailResponse> listDocumentoDetailResponse = new ArrayList<DocumentDetailResponse>();
			if (arrayDocumentoDetailResponse != null) {
				for (Integer index = 0; index < arrayDocumentoDetailResponse.length; index++) {
					listDocumentoDetailResponse.add(arrayDocumentoDetailResponse[index]);
				}
			}
			
			DocumentResponse documentResponse = new DocumentResponse();
			documentResponse.setListDocumentDetailResponse(listDocumentoDetailResponse);
			
			return documentResponse;
		} catch (APIClientException ex) {
			LOGGER.error("Erro consumir o método uploadFileBinary da API D4Sign. ", ex);
			throw new FacadeException("Erro consumir o método uploadFileBinary da API D4Sign. ");
		}
	}
	
	public SendDocumentSignatureResponse getDocument(String uuidDocument) throws FacadeException {
		try {
			Map<String, Object> queryParameters = getQueryParameters();
			Map<String, Object> headerParameters = getHeaderParameters();
			String path = SignNowPathEnum.DOCUMENT.getValue() + "/" + uuidDocument;
			
			APIClientFacade clientFacade = new APIClientFacade(HOST, path, APPLICATION_JSON);
			SendDocumentSignatureResponse documentSignatureResponse = (SendDocumentSignatureResponse) clientFacade.get(queryParameters, headerParameters, SendDocumentSignatureResponse.class);			
			return documentSignatureResponse;
		} catch (APIClientException ex) {
			LOGGER.error("Erro consumir o método sendtosigner da API D4Sign. ", ex);
			throw new FacadeException("Erro consumir o método sendtosigner da API D4Sign. ");
		}
	}

}