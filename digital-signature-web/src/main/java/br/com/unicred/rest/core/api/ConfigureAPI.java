package br.com.unicred.rest.core.api;

import java.util.Map;

import br.com.unicred.d4sign.client.builder.D4SignParametersBuilder;

public class ConfigureAPI {	
	
	public ConfigureAPI() {
		super();
	}
	
	public Map<String, Object> createParameters() {
		D4SignParametersBuilder builder = new D4SignParametersBuilder();
		Map<String, Object> parameters = builder.build();
		return parameters;
	}

}