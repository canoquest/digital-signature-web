package br.com.unicred.digitalsignature.application.enumeration;

public enum ApplicationEnum {
	
	SESSION_USER ("session_user"),
	URL_DOCUMENT ("url_document");
	
	private String value;

	private ApplicationEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}	

}