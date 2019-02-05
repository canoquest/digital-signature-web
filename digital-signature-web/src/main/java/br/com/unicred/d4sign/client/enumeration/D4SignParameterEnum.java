package br.com.unicred.d4sign.client.enumeration;

public enum D4SignParameterEnum {
	
	TOKEN_API ("tokenAPI", "live_c3d68984dc58c7f5387f0a4eea98302020b484dcbbef6a89a0a1114d5415360b"),
	CRYPT_KEY ("cryptKey", "live_crypt_1dMHE8UVDn3zD8qjYRwFTKmjnSzi7bDe"),	
	URL_WEB_HOOK ("urlWebHook", "http://localhost:8080/d4sign-web/application/modules/document/signatureResult.jsp"),
	UUID_SAFE ("uuid_safe", "4e9798ee-7427-456a-aadf-0c9586842c01"),
	UUID_FOLDER ("uuid_folder", "a9221d1d-425f-4e48-bedf-6eac4ce3983f"),
	PDF ("PDF", "PDF");
	
	private String key;
	
	private String value;

	private D4SignParameterEnum(String key, String value) {
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