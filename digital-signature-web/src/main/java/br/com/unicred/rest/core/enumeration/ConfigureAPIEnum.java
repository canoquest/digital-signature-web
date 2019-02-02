package br.com.unicred.rest.core.enumeration;

public enum ConfigureAPIEnum {

	API_HOST ("http://localhost:8080/rest-app/api/v1"),
	APPLICATION_JSON ("application/json"),
	MULTIPART_FORM_DATA ("multipart/form-data");
	
	private String value;

	private ConfigureAPIEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}