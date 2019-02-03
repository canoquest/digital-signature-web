package br.com.unicred.d4sign.client.model.request;

public class DocumentBinaryRequest {
	
	private String base64_binary_file;
	
	private String mime_type;
	
	private String name;
	
	private String uuid_folder;

	public DocumentBinaryRequest() {
		super();
	}

	public String getBase64_binary_file() {
		return base64_binary_file;
	}

	public void setBase64_binary_file(String base64_binary_file) {
		this.base64_binary_file = base64_binary_file;
	}

	public String getMime_type() {
		return mime_type;
	}

	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid_folder() {
		return uuid_folder;
	}

	public void setUuid_folder(String uuid_folder) {
		this.uuid_folder = uuid_folder;
	}

}