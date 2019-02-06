package br.com.unicred.d4sign.client.facade;

import java.util.Map;

import org.apache.log4j.Logger;

import br.com.unicred.d4sign.client.enumeration.D4SignPathEnum;
import br.com.unicred.d4sign.client.model.request.SignatoryRequest;
import br.com.unicred.d4sign.client.model.response.SignatoryResponse;
import br.com.unicred.rest.core.enumeration.APIClientParameterEnum;
import br.com.unicred.rest.core.exception.APIClientException;
import br.com.unicred.rest.core.exception.FacadeException;
import br.com.unicred.rest.core.facade.APIClientFacade;

public class SignatoryFacade extends D4SignFacade {
	
	private static final Logger LOGGER = Logger.getLogger(DocumentFacade.class);
	
	private static final String HOST = D4SignPathEnum.HOST.getValue();
	
	private static final String APPLICATION_JSON = APIClientParameterEnum.APPLICATION_JSON.getValue();

	public SignatoryFacade() {
		super();
	}
	
	public SignatoryResponse createSignatoryList(SignatoryRequest signatoryRequest, String uuidDocument) throws FacadeException {
		try {
			Map<String, Object> queryParameters = getQueryParameters();
			Map<String, Object> headerParameters = getHeaderParameters();
			String path = D4SignPathEnum.DOCUMENTS.getValue() + "/" + uuidDocument + "/createlist";
			Boolean converterToJSON = Boolean.TRUE;
			
			APIClientFacade clientFacade = new APIClientFacade(HOST, path, APPLICATION_JSON);
			SignatoryResponse signatoryResponse = (SignatoryResponse) clientFacade.post(signatoryRequest, queryParameters, headerParameters, SignatoryResponse.class, converterToJSON);			
			return signatoryResponse;
		} catch (APIClientException ex) {
			LOGGER.error("Erro consumir o método createWebhook da API D4Sign. ", ex);
			throw new FacadeException("Erro consumir o método createWebhook da API D4Sign. ");
		}
	}

}