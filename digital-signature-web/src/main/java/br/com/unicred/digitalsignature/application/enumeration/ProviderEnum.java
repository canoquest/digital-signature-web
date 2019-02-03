package br.com.unicred.digitalsignature.application.enumeration;

public enum ProviderEnum {
	
	DOCUSIGN ("DocuSign"),
	D4SIGN ("D4Sign"),
	AUTENTIQUE ("Autentique"),
	ADOBESIGN ("AdobeSign"),
	SIGNNOW ("SignNow");
	
	private String value;

	private ProviderEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}	

}