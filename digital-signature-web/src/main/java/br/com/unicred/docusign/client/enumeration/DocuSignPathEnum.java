package br.com.unicred.docusign.client.enumeration;

public enum DocuSignPathEnum {
	
	HOST ("http://demo.d4sign.com.br/api/v1"),	
	URL_INDEX ("http://localhost:8080/docusign-web/application/modules/authenticate/authenticate.jsp");
	
	private String value;

	private DocuSignPathEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}	

}