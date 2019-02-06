package br.com.unicred.signnow.client.model.response;

import java.util.List;

public class DocumentResponse {
	
	private List<DocumentDetailResponse> listDocumentDetailResponse;

	public DocumentResponse() {
		super();
	}

	public List<DocumentDetailResponse> getListDocumentDetailResponse() {
		return listDocumentDetailResponse;
	}

	public void setListDocumentDetailResponse(List<DocumentDetailResponse> listDocumentDetailResponse) {
		this.listDocumentDetailResponse = listDocumentDetailResponse;
	}	

}