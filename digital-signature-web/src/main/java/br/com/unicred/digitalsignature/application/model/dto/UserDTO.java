package br.com.unicred.digitalsignature.application.model.dto;

import java.math.BigDecimal;

import org.primefaces.model.UploadedFile;

public class UserDTO {
	
	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	private Integer installmentsNumber;
	
	private UploadedFile userSignature;
	
	private UploadedFile document;

	public UserDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getInstallmentsNumber() {
		return installmentsNumber;
	}

	public void setInstallmentsNumber(Integer installmentsNumber) {
		this.installmentsNumber = installmentsNumber;
	}

	public UploadedFile getUserSignature() {
		return userSignature;
	}

	public void setUserSignature(UploadedFile userSignature) {
		this.userSignature = userSignature;
	}

	public UploadedFile getDocument() {
		return document;
	}

	public void setDocument(UploadedFile document) {
		this.document = document;
	}
	
}