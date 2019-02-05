package br.com.unicred.digitalsignature.core.service;

import br.com.unicred.digitalsignature.core.model.dto.DocumentUploadedDTO;
import br.com.unicred.digitalsignature.core.model.dto.ProcessSignatureDTO;

public interface CoreServiceInterface {
	
	public ProcessSignatureDTO processDocumentSignature(String fileBase64, byte[] fileByteArray, String fileName, String email);
	
	public DocumentUploadedDTO uploadDocument(String fileBase64, byte[] fileByteArray, String fileName, String email);

}