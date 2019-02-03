package br.com.unicred.api.client.enumeration;

public enum APIPathEnum {
	
	HOST ("http://demo.d4sign.com.br/api/v1"),
	SEND_DOCUMENT ("/document/send");	
	
	private String value;

	private APIPathEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}	

}