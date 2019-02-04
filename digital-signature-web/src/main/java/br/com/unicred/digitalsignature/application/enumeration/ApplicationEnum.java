package br.com.unicred.digitalsignature.application.enumeration;

public enum ApplicationEnum {
	
	SESSION_USER ("session_user");
	
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