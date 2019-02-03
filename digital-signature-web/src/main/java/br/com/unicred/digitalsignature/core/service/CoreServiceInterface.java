package br.com.unicred.digitalsignature.core.service;

import br.com.unicred.digitalsignature.core.model.dto.DocumentUploadedDTO;

public interface CoreServiceInterface {
	
	public DocumentUploadedDTO uploadFileBinary(String fileBase64, byte[] fileByteArray, String fileName);

}
