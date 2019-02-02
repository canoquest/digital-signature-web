package br.com.unicred.digitalsignature.application.enumeration;

public enum UrlFinishedEnum {
	
	SIGNED_DOCUMENT_LIST ("/digital-signature-web/src/main/webapp/application/modules/documentSignature/signedDocumentList.xhtml", "signedDocumentList.jsf"),
	SIGNED_DOCUMENT_BY_USER ("/digital-signature-web/src/main/webapp/application/modules/documentSignature/signedDocumentByUser.xhtml", "signedDocumentByUser.jsf");
	
	private String completePath;
	
	private String urlFinished;

	private UrlFinishedEnum(String completePath, String urlFinished) {
		this.completePath = completePath;
		this.urlFinished = urlFinished;
	}

	public String getCompletePath() {
		return completePath;
	}

	public String getUrlFinished() {
		return urlFinished;
	}

}