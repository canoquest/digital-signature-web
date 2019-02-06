package br.com.unicred.d4sign.client.facade;

import java.util.Map;

import org.apache.log4j.Logger;

import br.com.unicred.d4sign.client.enumeration.D4SignPathEnum;
import br.com.unicred.d4sign.client.model.request.WebhookRequest;
import br.com.unicred.d4sign.client.model.response.WebhookResponse;
import br.com.unicred.rest.core.enumeration.APIClientParameterEnum;
import br.com.unicred.rest.core.exception.APIClientException;
import br.com.unicred.rest.core.exception.FacadeException;
import br.com.unicred.rest.core.facade.APIClientFacade;

public class WebhookFacade extends D4SignFacade {
	
	private static final Logger LOGGER = Logger.getLogger(WebhookFacade.class);

	private static final String HOST = D4SignPathEnum.HOST.getValue();
	
	private static final String APPLICATION_JSON = APIClientParameterEnum.APPLICATION_JSON.getValue();	

	public WebhookFacade() {
		super();
	}
	
	public WebhookResponse createWebhook(WebhookRequest webhookRequest, String uuidDocument) throws FacadeException {
		try {
			Map<String, Object> queryParameters = getQueryParameters();
			Map<String, Object> headerParameters = getHeaderParameters();
			String path = D4SignPathEnum.DOCUMENTS.getValue() + "/" + uuidDocument + "/webhooks";
			Boolean converterToJSON = Boolean.TRUE;
			
			APIClientFacade clientFacade = new APIClientFacade(HOST, path, APPLICATION_JSON);
			WebhookResponse webhookResponse = (WebhookResponse) clientFacade.post(webhookRequest, queryParameters, headerParameters, WebhookResponse.class, converterToJSON);			
			return webhookResponse;
		} catch (APIClientException ex) {
			LOGGER.error("Erro consumir o método createWebhook da API D4Sign. ", ex);
			throw new FacadeException("Erro consumir o método createWebhook da API D4Sign. ");
		}
	}
	
}