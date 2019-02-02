package br.com.unicred.rest.core.enumeration;

public enum APIParameterEnum {		
	
	TOKEN_API ("tokenAPI", "live_c3d68984dc58c7f5387f0a4eea98302020b484dcbbef6a89a0a1114d5415360b"),
	CRYPT_KEY ("cryptKey", "live_crypt_1dMHE8UVDn3zD8qjYRwFTKmjnSzi7bDe"),
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