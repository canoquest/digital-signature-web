package br.com.unicred.rest.core.api;

import java.util.Map;

import br.com.unicred.rest.core.builder.ParametersAPIBuilder;

public class ConfigureAPI {	
	
	public ConfigureAPI() {
		super();
	}
	
	public Map<String, Object> createParameters() {
		ParametersAPIBuilder builder = new ParametersAPIBuilder();
		Map<String, Object> parameters = builder.build();
		return parameters;
	}

}