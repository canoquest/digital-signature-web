package br.com.unicred.d4sign.client.facade;

import java.util.Map;

import br.com.unicred.d4sign.client.builder.D4SignParametersBuilder;

public class D4SignFacade {
	
	public D4SignFacade() {
		super();
	}
	
	public Map<String, Object> getParameters() {		
		D4SignParametersBuilder builder = new D4SignParametersBuilder();
		Map<String, Object> parameters = builder.build();				 
		return parameters;
	}

}