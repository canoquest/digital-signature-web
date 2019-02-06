package br.com.unicred.signnow.client.model.request;

public class DocumentUploadRequest {
	
	private String fileBase64;
	
	private String fileName;

	public DocumentUploadRequest() {
		super();		
	}

	public String getFileBase64() {
		return fileBase64;
	}

	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}		
	
}