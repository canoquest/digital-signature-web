package br.com.unicred.d4sign.client.model.response;

import java.util.List;

public class SafeResponse {
	
	List<SafeDetailResponse> listSafeDetail;

	public SafeResponse() {
		super();
	}

	public List<SafeDetailResponse> getListSafeDetail() {
		return listSafeDetail;
	}

	public void setListSafeDetail(List<SafeDetailResponse> listSafeDetail) {
		this.listSafeDetail = listSafeDetail;
	}

}