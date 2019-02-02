package br.com.unicred.rest.core.facade;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import br.com.unicred.rest.core.adapter.APIJsonAdapter;
import br.com.unicred.rest.core.api.ConfigureAPI;
import br.com.unicred.rest.core.builder.APIClientBuilder;
import br.com.unicred.rest.core.exception.APIClientException;

public class ClientAPIFacade {
	
	private static final Logger LOGGER = Logger.getLogger(ClientAPIFacade.class);
	
	private Map<String, Object> parametersConfiguration;
	
	private String host;
	
	private String path;
	
	private String contentType;	
	
	public ClientAPIFacade(String host, String path, String contentType) {
		super();
		this.host = host;
		this.path = path;
		this.contentType = contentType;
		initializate();
	}
	
	private void initializate() {
		ConfigureAPI configureAPI = new ConfigureAPI();
		parametersConfiguration = configureAPI.createParameters();		 
	}
	
	private void addParametersConfiguration(Map<String, Object> parameters) {
		if (parameters == null) {
			parameters = new HashMap<String, Object>(); 
		}
		parameters.putAll(parametersConfiguration);
	}
	
	public Object get(Map<String, Object> parameters, Class<?> classe) throws APIClientException {
		addParametersConfiguration(parameters);
		
		APIClientBuilder apiClientBuilder = new APIClientBuilder(host, path, contentType, parameters);		
		
		Response response = apiClientBuilder.get();
		
		String json = APIJsonAdapter.adapterToJSON(response);
		
		Object objectResponse = APIJsonAdapter.adapterToObject(json, classe);				
		return objectResponse;
	}
	
	public Object post(Object objectRequest, Map<String, Object> parameters, Class<?> classe) throws APIClientException {
		addParametersConfiguration(parameters);
		
		final String body = APIJsonAdapter
				.adapterFromObjectToJson(objectRequest);
		
		LOGGER.info("JSON: " + body);	
		
		APIClientBuilder apiClientBuilder = new APIClientBuilder(host, path, contentType, parameters);		
		
		Response response = apiClientBuilder.post(body);		
		
		String json = APIJsonAdapter.adapterToJSON(response);
		
		Object objectResponse = APIJsonAdapter.adapterToObject(json, classe);				
		return objectResponse;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
}