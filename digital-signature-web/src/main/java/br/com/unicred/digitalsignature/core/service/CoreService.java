package br.com.unicred.digitalsignature.core.service;

import br.com.unicred.digitalsignature.application.util.FileUtil;
import br.com.unicred.digitalsignature.application.util.StringUtil;
import br.com.unicred.digitalsignature.core.model.dto.DocumentUploadedDTO;
import br.com.unicred.digitalsignature.core.model.dto.ProcessSignatureDTO;

public abstract class CoreService {

	public abstract ProcessSignatureDTO processDocumentSignature(String fileBase64, byte[] fileByteArray, String fileName, String email);
	
	public abstract DocumentUploadedDTO uploadDocument(String fileBase64, byte[] fileByteArray, String fileName, String email);
	
	public void saveFileBase64(String fileBase64, String fileName, String idDocument) {
		String newFileName = StringUtil.updateFileName(fileName, idDocument);
		String filePath = FileUtil.FILE_PATH_DESTINY + newFileName;
		FileUtil.saveFileBase64(fileBase64, filePath);
	}
	
	public void saveFileByteArray(byte[] fileByteArray, String fileName, String idDocument) {
		String newFileName = StringUtil.updateFileName(fileName, idDocument);
		String filePath = FileUtil.FILE_PATH_DESTINY + newFileName;
		FileUtil.saveFileByteArray(fileByteArray, filePath);
	}
	
}