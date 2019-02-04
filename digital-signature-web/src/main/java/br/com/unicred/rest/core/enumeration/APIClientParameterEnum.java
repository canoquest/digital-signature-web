package br.com.unicred.rest.core.enumeration;

public enum APIClientParameterEnum {		
	
	APPLICATION_JSON ("application/json", "application/json"),
	MULTIPART_FORM_DATA ("multipart/form-data", "multipart/form-data"),
	APPLICATION_PDF ("application/pdf", "application/pdf"),
	CONTENT_TYPE ("Content-Type", "application/json");
	
	private String key;
	
	private String value;

	private APIClientParameterEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

}