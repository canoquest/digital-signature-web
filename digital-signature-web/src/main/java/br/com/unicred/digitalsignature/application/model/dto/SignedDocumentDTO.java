package br.com.unicred.digitalsignature.application.model.dto;

import java.io.Serializable;
import java.util.Date;

public class SignedDocumentDTO implements Serializable {
		
	private static final long serialVersionUID = 1L;

	private String documentProvider;
	
	private Date signatureDate;

	public SignedDocumentDTO() {
		super();
	}

	public String getDocumentProvider() {
		return documentProvider;
	}

	public void setDocumentProvider(String documentProvider) {
		this.documentProvider = documentProvider;
	}

	public Date getSignatureDate() {
		return signatureDate;
	}

	public void setSignatureDate(Date signatureDate) {
		this.signatureDate = signatureDate;
	}	

}