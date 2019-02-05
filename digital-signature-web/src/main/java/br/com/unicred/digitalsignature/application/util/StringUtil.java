package br.com.unicred.digitalsignature.application.util;

public class StringUtil {
	
	private StringUtil() {
		super();
	}

	public static String updateFileName(String fileName, String idDocument) {	
		Double randon = Math.random();
		String idDocumentFileName = "";
		if (fileName.contains(".pdf")) {
			Integer extensionPosition = fileName.indexOf(".pdf");
			String fileNameExtensionOf = fileName.substring(0, extensionPosition);
			String newFileName = fileNameExtensionOf + randon + fileName.substring(extensionPosition, fileName.length());
			idDocumentFileName = idDocument + fileName.substring(extensionPosition, fileName.length());
			System.out.println("New Name: " + newFileName);
			System.out.println("Randon Name: " + idDocumentFileName);
		}	
		return idDocumentFileName;
	}

}