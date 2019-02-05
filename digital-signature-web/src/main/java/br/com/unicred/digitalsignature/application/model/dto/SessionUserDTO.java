package br.com.unicred.digitalsignature.application.model.dto;

public class SessionUserDTO {
	
	private String fileName;
	
	private String fileBase64;
	
	private byte[] fileByteArray;
	
	private String userName;
	
	private String userEmail;
	
	private String providerSelected;

	public SessionUserDTO() {
		super();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileBase64() {
		return fileBase64;
	}

	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}

	public byte[] getFileByteArray() {
		return fileByteArray;
	}

	public void setFileByteArray(byte[] fileByteArray) {
		this.fileByteArray = fileByteArray;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getProviderSelected() {
		return providerSelected;
	}

	public void setProviderSelected(String providerSelected) {
		this.providerSelected = providerSelected;
	}
	
}