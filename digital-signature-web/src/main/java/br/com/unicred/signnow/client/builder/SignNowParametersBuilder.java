package br.com.unicred.signnow.client.builder;

import java.util.HashMap;
import java.util.Map;

public class SignNowParametersBuilder {
	
	protected Map<String, Object> queryParameters;
	
	protected Map<String, Object> headerParameters;
	
	public SignNowParametersBuilder() {
		super();		
		initializate();
	}	
	
	private void initializate() {
		queryParameters = new HashMap<String, Object>();
		headerParameters = new HashMap<String, Object>();
	}
	
	private void createQueryParameters() {				
	}	
	
	private void createHeadParameters() {						
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