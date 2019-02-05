package br.com.unicred.d4sign.client.model.response;

public class DocumentResponse {
	
	private String uuiDoc;
	
	private String nameDoc;
	
	private String size;
	
	private String pages;
	
	private String uuidSafe;
	
	private String safeName;
	
	private String statusId;
	
	private String statusName;

	public DocumentResponse() {
		super();
	}

	public String getUuiDoc() {
		return uuiDoc;
	}

	public void setUuiDoc(String uuiDoc) {
		this.uuiDoc = uuiDoc;
	}

	public String getNameDoc() {
		return nameDoc;
	}

	public void setNameDoc(String nameDoc) {
		this.nameDoc = nameDoc;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getUuidSafe() {
		return uuidSafe;
	}

	public void setUuidSafe(String uuidSafe) {
		this.uuidSafe = uuidSafe;
	}

	public String getSafeName() {
		return safeName;
	}

	public void setSafeName(String safeName) {
		this.safeName = safeName;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}