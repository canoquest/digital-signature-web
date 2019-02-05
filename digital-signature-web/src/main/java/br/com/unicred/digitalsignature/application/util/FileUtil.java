package br.com.unicred.digitalsignature.application.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

public class FileUtil {	
	
	public static final String FILE_PATH_ORIGEN = "C:\\File\\";
	public static final String FILE_PATH_DESTINY = "C:\\File\\";
	
	private FileUtil() {
		super();
	}
	
	public static Boolean saveFileBase64(String fileBase64, String filePathDestiny) {		
		Boolean success = saveFilePath(fileBase64, filePathDestiny);
		return success;
	}
	
	public static Boolean saveFileByteArray(byte[] fileByteArray, String filePathDestiny) {
		Boolean success = saveFilePath(fileByteArray, filePathDestiny);
		return success;
	}
	
	public static InputStream getInputStream(String filePath) {
		InputStream inputStream = null;
		try {
			File file = new File(filePath);
			inputStream = new FileInputStream(file);			
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		return inputStream;
	}
	
	public static Boolean copyFileByteArray(byte[] fileByteArray, String filePathOrigen, String filePathDestiny) {		
		String fileBase64 = getFileBase64(fileByteArray, filePathOrigen);
		Boolean success = saveFilePath(fileBase64, filePathDestiny);
		return success;
	}
	
	private static String getFileBase64(byte[] fileByteArray, String filePath) {
		String fileBase64 = null;
		try {
			// Obtém o arquivo do diretório informado
			File file = new File(filePath);
			fileByteArray = new byte[(int)file.length()];
			
			// Executa a leitura do arquivo para que o array de byte não seja 
			// apenas criado, mas que seja lido também
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(fileByteArray); 
			fileInputStream.close();
			
			// Executa a transformação de array de bytes para String Base64
			fileBase64 = adapterFileByteArrayToFileBase64(fileByteArray);			
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return fileBase64;
	}
	
	private static Boolean saveFilePath(String fileBase64, String filePathDestiny) {
		Boolean success = Boolean.TRUE;
		try {
			// Executa a transformação de String Base64 para array de bytes
			byte[] fileByteArray = adapterFileBase64ToFileByteArray(fileBase64);			
			
			// Executa a escrita do arquivo no diretório informado.
			try (OutputStream stream = new FileOutputStream(filePathDestiny)) {
			    stream.write(fileByteArray);
			}			
		} catch (FileNotFoundException ex) {
			success = Boolean.FALSE;
			ex.printStackTrace();
		} catch (IOException ex) {
			success = Boolean.FALSE;
			ex.printStackTrace();
		}
		return success;
	}
	
	private static Boolean saveFilePath(byte[] fileByteArray, String filePathDestiny) {
		Boolean success = Boolean.TRUE;
		try {			
			// Executa a escrita do arquivo no diretório informado.
			try (OutputStream stream = new FileOutputStream(filePathDestiny)) {
			    stream.write(fileByteArray);
			}			
		} catch (FileNotFoundException ex) {
			success = Boolean.FALSE;
			ex.printStackTrace();
		} catch (IOException ex) {
			success = Boolean.FALSE;
			ex.printStackTrace();
		}
		return success;
	}	
	
	/**
	 * Executa a transformação de array de bytes para String Base64
	 * 
	 * @param fileByteArray - Recebe como parâmetro um array de bytes
	 * @return - Retorna uma String no padrão Base64
	 */
	private static String adapterFileByteArrayToFileBase64(byte[] fileByteArray) {
		String fileBase64 = new String(Base64.getEncoder().encode(fileByteArray));		
		return fileBase64;
	}
	
	/**
	 * Executa a transformação de String Base64 para array de bytes
	 * 
	 * @param fileBase64 - Recebe como parâmetro uma String no padrão Base64
	 * @return - Retorna um array de bytes
	 */
	private static byte[] adapterFileBase64ToFileByteArray(String fileBase64) {
		byte[] fileByteArray = Base64.getDecoder().decode(fileBase64.getBytes());
		return fileByteArray;
	}	

}