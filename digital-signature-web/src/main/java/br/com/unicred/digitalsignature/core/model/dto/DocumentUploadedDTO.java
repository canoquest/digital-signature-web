package br.com.unicred.digitalsignature.core.model.dto;

public class DocumentUploadedDTO {
	
	private String idDocument;
	
	private String urlDocument;
	
	private String message;
	
	private Boolean success;
	
	private Integer httpCode;
	
	private String urlWebViewProvider;
	
	private String tokenAssinatura;
	
	public DocumentUploadedDTO() {
		super();
	}

	public String getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(String idDocument) {
		this.idDocument = idDocument;
	}

	public String getUrlDocument() {
		return urlDocument;
	}

	public void setUrlDocument(String urlDocument) {
		this.urlDocument = urlDocument;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
	}

	public String getUrlWebViewProvider() {
		return urlWebViewProvider;
	}

	public void setUrlWebViewProvider(String urlWebViewProvider) {
		this.urlWebViewProvider = urlWebViewProvider;
	}

	public String getTokenAssinatura() {
		return tokenAssinatura;
	}

	public void setTokenAssinatura(String tokenAssinatura) {
		this.tokenAssinatura = tokenAssinatura;
	}
	
}