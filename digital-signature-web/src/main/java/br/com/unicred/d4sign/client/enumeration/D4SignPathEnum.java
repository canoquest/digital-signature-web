package br.com.unicred.d4sign.client.enumeration;

public enum D4SignPathEnum {
	
	HOST ("http://demo.d4sign.com.br/api/v1"),
	BALANCE ("/account/balance"),
	LIST_SAFES ("/safes"),
	LIST_FOLDERS ("/folders"),
	DOCUMENTS ("/documents");
	
	private String value;

	private D4SignPathEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}	

}