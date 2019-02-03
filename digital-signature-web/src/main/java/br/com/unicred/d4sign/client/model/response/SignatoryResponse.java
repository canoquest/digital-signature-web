package br.com.unicred.d4sign.client.model.response;

import java.util.List;

public class SignatoryResponse {
	
	private List<SignerResponse> message;

	public SignatoryResponse() {
		super();
	}

	public List<SignerResponse> getMessage() {
		return message;
	}

	public void setMessage(List<SignerResponse> message) {
		this.message = message;
	}	

}