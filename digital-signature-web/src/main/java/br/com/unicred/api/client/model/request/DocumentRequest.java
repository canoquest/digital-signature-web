package br.com.unicred.api.client.model.request;

public class DocumentRequest {
	
	private String documentBase64;
	
	private String fileName;

	public DocumentRequest() {
		super();
	}

	public String getDocumentBase64() {
		return documentBase64;
	}

	public void setDocumentBase64(String documentBase64) {
		this.documentBase64 = documentBase64;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}