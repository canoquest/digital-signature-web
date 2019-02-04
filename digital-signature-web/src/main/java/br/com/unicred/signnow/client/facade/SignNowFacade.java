package br.com.unicred.signnow.client.facade;

import java.util.Map;

import br.com.unicred.signnow.client.builder.SignNowParametersBuilder;

public class SignNowFacade {
	
	public SignNowFacade() {
		super();
	}
	
	public Map<String, Object> getQueryParameters() {		
		SignNowParametersBuilder builder = new SignNowParametersBuilder();
		Map<String, Object> parameters = builder.buildQueryParameters();				 
		return parameters;
	}
	
	public Map<String, Object> getHeaderParameters() {		
		SignNowParametersBuilder builder = new SignNowParametersBuilder();
		Map<String, Object> parameters = builder.buildHeaderParameters();				 
		return parameters;
	}

}