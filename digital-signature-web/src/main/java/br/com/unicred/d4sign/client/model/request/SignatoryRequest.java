package br.com.unicred.d4sign.client.model.request;

import java.util.List;

public class SignatoryRequest {

	private List<SignerRequest> signers;

	public SignatoryRequest() {
		super();
	}

	public List<SignerRequest> getSigners() {
		return signers;
	}

	public void setSigners(List<SignerRequest> signers) {
		this.signers = signers;
	}	
	
}