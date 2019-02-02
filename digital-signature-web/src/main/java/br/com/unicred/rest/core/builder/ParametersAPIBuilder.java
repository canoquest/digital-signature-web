package br.com.unicred.rest.core.builder;

import java.util.HashMap;
import java.util.Map;

import br.com.unicred.rest.core.enumeration.APIParameterEnum;

public class ParametersAPIBuilder {
	
	protected Map<String, Object> parameters;
	
	public ParametersAPIBuilder() {
		super();
	}
	
	private void configurationParameters() {	
		createParameters();
		parameters.put(APIParameterEnum.CONTENT_TYPE.getKey(), APIParameterEnum.CONTENT_TYPE.getValue());	
	}
	
	private void authenticationParameters() {
		createParameters();
		parameters.put(APIParameterEnum.TOKEN_API.getKey(), APIParameterEnum.TOKEN_API.getValue());
		parameters.put(APIParameterEnum.CRYPT_KEY.getKey(), APIParameterEnum.CRYPT_KEY.getValue());		
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
