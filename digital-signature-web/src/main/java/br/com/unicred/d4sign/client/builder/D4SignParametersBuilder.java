package br.com.unicred.d4sign.client.builder;

import java.util.HashMap;
import java.util.Map;

import br.com.unicred.d4sign.client.enumeration.D4SignParameterEnum;
import br.com.unicred.rest.core.enumeration.APIParameterEnum;

public class D4SignParametersBuilder {
	
	protected Map<String, Object> parameters;
	
	public D4SignParametersBuilder() {
		super();
	}
	
	private void configurationParameters() {	
		createParameters();
		parameters.put(APIParameterEnum.CONTENT_TYPE.getKey(), APIParameterEnum.CONTENT_TYPE.getValue());	
	}
	
	private void authenticationParameters() {
		createParameters();
		parameters.put(D4SignParameterEnum.TOKEN_API.getKey(), D4SignParameterEnum.TOKEN_API.getValue());
		parameters.put(D4SignParameterEnum.CRYPT_KEY.getKey(), D4SignParameterEnum.CRYPT_KEY.getValue());		
	}
	
	private Boolean emptyParameters() {
		Boolean result = (parameters == null || parameters.isEmpty() ? Boolean.TRUE : Boolean.FALSE);
		return result;
	}
	
	private void createParameters() {
		if (emptyParameters()) {
			parameters = new HashMap<String, Object>();
		}		
	}
	
	public Map<String, Object> build() {
		configurationParameters();
		authenticationParameters();
		return parameters;
	}

}
