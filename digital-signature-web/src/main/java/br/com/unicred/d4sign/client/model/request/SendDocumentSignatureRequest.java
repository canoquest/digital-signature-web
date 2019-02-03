package br.com.unicred.d4sign.client.model.request;

public class SendDocumentSignatureRequest {
	
	private String message;
	
	private String skip_email;
	
	private String workflow;
	
	private String tokenAPI;

	public SendDocumentSignatureRequest() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSkip_email() {
		return skip_email;
	}

	public void setSkip_email(String skip_email) {
		this.skip_email = skip_email;
	}

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}

	public String getTokenAPI() {
		return tokenAPI;
	}

	public void setTokenAPI(String tokenAPI) {
		this.tokenAPI = tokenAPI;
	}
	
}