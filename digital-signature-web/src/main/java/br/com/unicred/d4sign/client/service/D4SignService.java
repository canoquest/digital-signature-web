package br.com.unicred.d4sign.client.service;

import br.com.unicred.d4sign.client.enumeration.D4SignParameterEnum;
import br.com.unicred.d4sign.client.facade.DocumentFacade;
import br.com.unicred.d4sign.client.model.request.DocumentBinaryRequest;
import br.com.unicred.digitalsignature.core.model.dto.DocumentUploadedDTO;
import br.com.unicred.digitalsignature.core.service.CoreServiceInterface;
import br.com.unicred.rest.core.exception.FacadeException;

public class D4SignService implements CoreServiceInterface {	

	public D4SignService() {
		super();
	}

	@Override
	public DocumentUploadedDTO uploadFileBinary(String fileBase64, byte[] fileByteArray, String fileName) {
		try {
			DocumentBinaryRequest document = new DocumentBinaryRequest();
			document.setBase64_binary_file(fileBase64);
			document.setMime_type("");
			document.setName(fileName);
			document.setUuid_folder(D4SignParameterEnum.UUID_FOLDER.getValue());
			
			DocumentFacade documentFacade = new DocumentFacade();
			documentFacade.uploadFileBinary(null);
		} catch (FacadeException ex) {
		
		}
		return null;
	}

}