package br.com.unicred.d4sign.client.model.request;

public class DocumentPathRequest {

	private String file;
	
	private String uuid_folder;

	public DocumentPathRequest() {
		super();
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getUuid_folder() {
		return uuid_folder;
	}

	public void setUuid_folder(String uuid_folder) {
		this.uuid_folder = uuid_folder;
	}
	
}