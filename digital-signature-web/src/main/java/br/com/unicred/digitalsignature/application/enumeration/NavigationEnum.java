package br.com.unicred.digitalsignature.application.enumeration;

public enum NavigationEnum {
	
	NEW_DOCUMENTO ("novo_documento"),
	VIEW_USER_VALUES ("exibe_informacoes_usuario"),
	SELECT_PROVIDER ("seleciona_fornecedor"),
	SIGNED_DOCUMENT_BY_USER ("signed_document_by_user"),
	LIST_SIGNED_DOCUMENTS ("list_signed_documents");
	
	private String value;

	private NavigationEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}