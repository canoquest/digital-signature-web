package br.com.unicred.digitalsignature.application.managedbean;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.unicred.digitalsignature.application.enumeration.UrlFinishedEnum;
import br.com.unicred.digitalsignature.application.model.dto.SignedDocumentDTO;
import br.com.unicred.digitalsignature.application.util.FileUtil;
import br.com.unicred.digitalsignature.core.managedbean.CoreMBean;

@SuppressWarnings("deprecation")
@ManagedBean(name = "documentMBean")
@ViewScoped
public class DocumentMBean extends CoreMBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<SignedDocumentDTO> listSignedDocumentDTO;	
	
	private StreamedContent streamedContent;
	
	public DocumentMBean() {
		super();
	}
	
	public void initializate() {
		listSignedDocumentDTO = new ArrayList<SignedDocumentDTO>();
		Boolean loadSignedDocuments = currentUrlValidate(UrlFinishedEnum.SIGNED_DOCUMENT_LIST.getUrlFinished());
		if (loadSignedDocuments) {
			loadSignedDocuments();
		}		
		getFile();
	}
	
	private void loadSignedDocuments() {	
		SignedDocumentDTO document1 = new SignedDocumentDTO();
		document1.setDocumentProvider("Documento 1 - DocuSign");
		document1.setSignatureDate(Calendar.getInstance().getTime());
		listSignedDocumentDTO.add(document1);
		
		SignedDocumentDTO document2 = new SignedDocumentDTO();
		document2.setDocumentProvider("Documento 2 - D4Sign");
		document2.setSignatureDate(Calendar.getInstance().getTime());
		listSignedDocumentDTO.add(document2);
		
		SignedDocumentDTO document3 = new SignedDocumentDTO();
		document3.setDocumentProvider("Documento 3 - Autentique");
		document3.setSignatureDate(Calendar.getInstance().getTime());
		listSignedDocumentDTO.add(document3);
		
		SignedDocumentDTO document4 = new SignedDocumentDTO();
		document4.setDocumentProvider("Documento 4 - AdobeSign");
		document4.setSignatureDate(Calendar.getInstance().getTime());
		listSignedDocumentDTO.add(document4);
		
		SignedDocumentDTO document5 = new SignedDocumentDTO();
		document5.setDocumentProvider("Documento 4 - SignNow");
		document5.setSignatureDate(Calendar.getInstance().getTime());
		listSignedDocumentDTO.add(document5);
	}	
	
	private void getFile() {
		String filePath = FileUtil.FILE_PATH_DESTINY + "\\0.384210768276638.pdf";
		InputStream inputStream = FileUtil.getInputStream(filePath);
		streamedContent = new DefaultStreamedContent(inputStream, "application/pdf", "0.384210768276638.pdf");
	}

	public List<SignedDocumentDTO> getListSignedDocumentDTO() {
		return listSignedDocumentDTO;
	}

	public void setListSignedDocumentDTO(List<SignedDocumentDTO> listSignedDocumentDTO) {
		this.listSignedDocumentDTO = listSignedDocumentDTO;
	}

	public StreamedContent getStreamedContent() {
		return streamedContent;
	}

	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}	

}