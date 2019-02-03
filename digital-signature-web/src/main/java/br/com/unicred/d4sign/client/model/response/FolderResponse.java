package br.com.unicred.d4sign.client.model.response;

import java.util.List;

public class FolderResponse {
	
	private List<FolderDetailResponse> listFolderDetail;

	public FolderResponse() {
		super();
	}

	public List<FolderDetailResponse> getListFolderDetail() {
		return listFolderDetail;
	}

	public void setListFolderDetail(List<FolderDetailResponse> listFolderDetail) {
		this.listFolderDetail = listFolderDetail;
	}

}