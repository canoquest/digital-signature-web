package br.com.unicred.rest.core.enumeration;

public enum APIParameterEnum {		
	
	CONTENT_TYPE ("Content-Type", "application/json");
	
	private String key;
	
	private String value;

	private APIParameterEnum(String key, String value) {
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