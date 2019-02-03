package br.com.unicred.digitalsignature.core.model.dto;

public class ProcessSignatureDTO {
	
	private DocumentUploadedDTO documentUploadedDTO;
	
	private Boolean proccessResult;

	public ProcessSignatureDTO() {
		super();
	}

	public DocumentUploadedDTO getDocumentUploadedDTO() {
		return documentUploadedDTO;
	}

	public void setDocumentUploadedDTO(DocumentUploadedDTO documentUploadedDTO) {
		this.documentUploadedDTO = documentUploadedDTO;
	}

	public Boolean getProccessResult() {
		return proccessResult;
	}

	public void setProccessResult(Boolean proccessResult) {
		this.proccessResult = proccessResult;
	}

}