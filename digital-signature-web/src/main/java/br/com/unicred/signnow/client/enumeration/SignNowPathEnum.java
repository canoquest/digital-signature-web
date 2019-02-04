package br.com.unicred.signnow.client.enumeration;

public enum SignNowPathEnum {
	
	HOST ("localhost:8080"),	
	DOCUMENT ("/document"),
	DOCUMENTS ("/documents");
	
	private String value;

	private SignNowPathEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}	

}