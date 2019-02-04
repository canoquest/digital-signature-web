package br.com.unicred.autentique.client.builder;

import java.util.HashMap;
import java.util.Map;

import br.com.unicred.autentique.client.enumeration.AutentiqueParameterEnum;
import br.com.unicred.rest.core.enumeration.APIClientParameterEnum;

public class AutentiqueParametersBuilder {	
	
	protected Map<String, Object> headerParameters;
	
	public AutentiqueParametersBuilder() {
		super();		
		initializate();
	}	
	
	private void initializate() {		
		headerParameters = new HashMap<String, Object>();
	}		
	
	private void createHeadParameters() {		
		headerParameters.put(APIClientParameterEnum.CONTENT_TYPE.getKey(), APIClientParameterEnum.CONTENT_TYPE.getValue());
		headerParameters.put(AutentiqueParameterEnum.X_AUTNTIQ_API.getKey(), AutentiqueParameterEnum.X_AUTNTIQ_API.getValue());
	}	
	
	public Map<String, Object> buildHeaderParameters() {		
		createHeadParameters();
		return headerParameters;
	}

}