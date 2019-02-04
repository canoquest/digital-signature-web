package br.com.unicred.signnow.client.model.response;

import java.util.List;

public class DocumentResponse {
	
	private List<DocumentoDetailResponse> listDocumentDetailResponse;

	public DocumentResponse() {
		super();
	}

	public List<DocumentoDetailResponse> getListDocumentDetailResponse() {
		return listDocumentDetailResponse;
	}

	public void setListDocumentDetailResponse(List<DocumentoDetailResponse> listDocumentDetailResponse) {
		this.listDocumentDetailResponse = listDocumentDetailResponse;
	}	

}