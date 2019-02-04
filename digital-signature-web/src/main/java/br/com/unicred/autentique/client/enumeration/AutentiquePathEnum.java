package br.com.unicred.autentique.client.enumeration;

public enum AutentiquePathEnum {
	
	HOST ("https://sandbox.autentique.com.br"),
	CREATE_FOLDER ("/pastas.json"),
	DOCUMENTS ("/documentos.json");	
	
	private String value;

	private AutentiquePathEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}	

}