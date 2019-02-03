package br.com.unicred.d4sign.client.model.response;

public class DocumentBinaryResponse {

	private String message;
	
	private String uuid;

	public DocumentBinaryResponse() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}