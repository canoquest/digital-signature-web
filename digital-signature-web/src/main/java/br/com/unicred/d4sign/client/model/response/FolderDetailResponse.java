package br.com.unicred.d4sign.client.model.response;

import java.util.Date;

public class FolderDetailResponse {
	
	private String uuid_safe;
	
	private String uuid_folder;
	
	private String name;
	
	private Date dt_cadastro;

	public FolderDetailResponse() {
		super();
	}

	public String getUuid_safe() {
		return uuid_safe;
	}

	public void setUuid_safe(String uuid_safe) {
		this.uuid_safe = uuid_safe;
	}

	public String getUuid_folder() {
		return uuid_folder;
	}

	public void setUuid_folder(String uuid_folder) {
		this.uuid_folder = uuid_folder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDt_cadastro() {
		return dt_cadastro;
	}

	public void setDt_cadastro(Date dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}

}