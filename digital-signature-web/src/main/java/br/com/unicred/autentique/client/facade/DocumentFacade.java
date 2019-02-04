package br.com.unicred.autentique.client.facade;

import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.unicred.autentique.client.enumeration.AutentiquePathEnum;
import br.com.unicred.autentique.client.model.request.DocumentRequest;
import br.com.unicred.autentique.client.model.response.DocumentResponse;
import br.com.unicred.rest.core.enumeration.APIClientParameterEnum;
import br.com.unicred.rest.core.exception.APIClientException;
import br.com.unicred.rest.core.exception.FacadeException;
import br.com.unicred.rest.core.facade.APIClientFacade;

public class DocumentFacade extends AutentiqueFacade {
	
	private static final Logger LOGGER = Logger.getLogger(DocumentFacade.class);
	
	private static final String HOST = AutentiquePathEnum.HOST.getValue();
	
	private static final String APPLICATION_JSON = APIClientParameterEnum.APPLICATION_JSON.getValue();	

	public DocumentFacade() {
		super();
	}
	
	public DocumentResponse upload(DocumentRequest documentRequest) throws FacadeException {
		try {
			Map<String, Object> queryParameters = getQueryParameters();
			Map<String, Object> headParameters = getHeaderParameters();
			String path = AutentiquePathEnum.DOCUMENTS.getValue();
			
			 ObjectMapper mapper = new ObjectMapper();
			    String body = mapper.writerWithDefaultPrettyPrinter()
			   		 .writeValueAsString(documentRequest);
			
			APIClientFacade clientFacade = new APIClientFacade(HOST, path, APPLICATION_JSON);
			DocumentResponse documentResponse = (DocumentResponse) clientFacade.post(body, queryParameters, headParameters, DocumentResponse.class);		
			return documentResponse;
		} catch (APIClientException ex) {
			LOGGER.error("Erro consumir o método uploadFilePath da API D4Sign. ", ex);
			throw new FacadeException("Erro consumir o método uploadFilePath da API D4Sign. ");
		} catch (JsonProcessingException ex) {
			LOGGER.error("Erro ao executar o processamento do JSON. ", ex);
			throw new FacadeException("Erro ao executar o processamento do JSON. ");
		}
	}

}