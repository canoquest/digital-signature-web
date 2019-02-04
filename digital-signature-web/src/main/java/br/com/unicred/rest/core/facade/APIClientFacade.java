package br.com.unicred.rest.core.facade;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import br.com.unicred.rest.core.adapter.APIJsonAdapter;
import br.com.unicred.rest.core.builder.APIClientBuilder;
import br.com.unicred.rest.core.exception.APIClientException;

public class APIClientFacade {
	
	private static final Logger LOGGER = Logger.getLogger(APIClientFacade.class);
	
	private String host;
	
	private String path;
	
	private String contentType;	
	
	public APIClientFacade(String host, String path, String contentType) {
		super();
		this.host = host;
		this.path = path;
		this.contentType = contentType;		
	}	
	
	public Object get(Map<String, Object> queryParameters, Map<String, Object> headParameters, Class<?> classe) throws APIClientException {		
		APIClientBuilder apiClientBuilder = new APIClientBuilder(host, path, contentType, queryParameters, headParameters);		
		
		Response response = apiClientBuilder.get();
		
		String json = APIJsonAdapter.adapterToJSON(response);
		
		Object objectResponse = APIJsonAdapter.adapterToObject(json, classe);				
		return objectResponse;
	}
	
	public Object post(Object objectRequest, Map<String, Object> queryParameters, Map<String, Object> headParameters, Class<?> classe) throws APIClientException {		
		final String body = APIJsonAdapter
				.adapterFromObjectToJson(objectRequest);
		
		return postProcess(body, queryParameters, headParameters, classe);
	}
	
	public Object post(String body, Map<String, Object> queryParameters, Map<String, Object> headParameters, Class<?> classe) throws APIClientException {		
		return postProcess(body, queryParameters, headParameters, classe);
	}
	
	private Object postProcess(String body, Map<String, Object> queryParameters, Map<String, Object> headParameters, Class<?> classe) throws APIClientException {
		LOGGER.info("JSON: " + body);	
		System.out.println("Log: " + body);
		
		APIClientBuilder apiClientBuilder = new APIClientBuilder(host, path, contentType, queryParameters, headParameters);		
		
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