package br.com.unicred.api.client.facade;

import java.util.HashMap;
import java.util.Map;

import br.com.unicred.api.client.enumeration.APIPathEnum;
import br.com.unicred.api.client.model.request.DocumentRequest;
import br.com.unicred.api.client.model.response.DocumentResponse;
import br.com.unicred.rest.core.enumeration.ConfigureAPIEnum;
import br.com.unicred.rest.core.exception.APIClientException;
import br.com.unicred.rest.core.exception.FacadeException;
import br.com.unicred.rest.core.facade.ClientAPIFacade;

public class DocumentFacade {
	
	private static final String HOST = ConfigureAPIEnum.API_HOST.getValue();
	
	private static final String APPLICATION_JSON = ConfigureAPIEnum.APPLICATION_JSON.getValue();

	public DocumentFacade() {
		super();		
	}
	
	public DocumentResponse sendDocument(DocumentRequest documentRequest) throws FacadeException {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			String path = APIPathEnum.SEND_DOCUMENT.getValue();
			
			ClientAPIFacade clientFacade = new ClientAPIFacade(HOST, path, APPLICATION_JSON);
			DocumentResponse documentPathResponse = (DocumentResponse) clientFacade.post(documentRequest, parameters, DocumentResponse.class);			
			return documentPathResponse;
		} catch (APIClientException ex) {
			throw new FacadeException("Erro consumir o método send da API de Documentos");
		}
	}

}