package br.com.unicred.d4sign.client.model.response;

public class SafeDetailResponse {
	
	private String uuid_safe;
	
	private String name_safe;

	public SafeDetailResponse() {
		super();
	}

	public String getUuid_safe() {
		return uuid_safe;
	}

	public void setUuid_safe(String uuid_safe) {
		this.uuid_safe = uuid_safe;
	}

	public String getName_safe() {
		return name_safe;
	}

	public void setName_safe(String name_safe) {
		this.name_safe = name_safe;
	}

}