package br.com.unicred.signnow.client.model.response;

public class DocumentUploadResponse {
	
	private String document;
	
	private String id;

	public DocumentUploadResponse() {
		super();
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
	
}