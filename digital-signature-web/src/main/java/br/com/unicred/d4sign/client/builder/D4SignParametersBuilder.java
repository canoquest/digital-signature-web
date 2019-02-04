package br.com.unicred.d4sign.client.builder;

import java.util.HashMap;
import java.util.Map;

import br.com.unicred.d4sign.client.enumeration.D4SignParameterEnum;
import br.com.unicred.rest.core.enumeration.APIClientParameterEnum;

public class D4SignParametersBuilder {
	
	protected Map<String, Object> queryParameters;
	
	protected Map<String, Object> headerParameters;
	
	public D4SignParametersBuilder() {
		super();		
		initializate();
	}	
	
	private void initializate() {
		queryParameters = new HashMap<String, Object>();
		headerParameters = new HashMap<String, Object>();
	}
	
	private void createQueryParameters() {		
		queryParameters.put(D4SignParameterEnum.TOKEN_API.getKey(), D4SignParameterEnum.TOKEN_API.getValue());
		queryParameters.put(D4SignParameterEnum.CRYPT_KEY.getKey(), D4SignParameterEnum.CRYPT_KEY.getValue());		
	}	
	
	private void createHeadParameters() {		
		headerParameters.put(APIClientParameterEnum.CONTENT_TYPE.getKey(), APIClientParameterEnum.CONTENT_TYPE.getValue());				
	}
	
	public Map<String, Object> buildQueryParameters() {		
		createQueryParameters();		
		return queryParameters;
	}
	
	public Map<String, Object> buildHeaderParameters() {		
		createHeadParameters();
		return headerParameters;
	}

}