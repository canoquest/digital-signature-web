package br.com.unicred.autentique.client.enumeration;

public enum AutentiqueParameterEnum {
	
	X_AUTNTIQ_API ("X-Autntiq-Api", "645B7B4FE3B8608D2FDA8325E35B3669F62CEB6FF22634A8B00B6F7C000F44BD"),		
	UUID_FOLDER ("uuid_folder", "438cec83abbe0146c120f69b9df790762c1eb8ae");
	
	private String key;
	
	private String value;

	private AutentiqueParameterEnum(String key, String value) {
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