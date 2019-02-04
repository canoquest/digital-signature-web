package br.com.unicred.autentique.client.facade;

import java.util.Map;

import br.com.unicred.autentique.client.builder.AutentiqueParametersBuilder;

public class AutentiqueFacade {
	
	public AutentiqueFacade() {
		super();
	}
	
	public Map<String, Object> getQueryParameters() {						 
		return null;
	}
	
	public Map<String, Object> getHeaderParameters() {		
		AutentiqueParametersBuilder builder = new AutentiqueParametersBuilder();
		Map<String, Object> parameters = builder.buildHeaderParameters();				 
		return parameters;
	}

}