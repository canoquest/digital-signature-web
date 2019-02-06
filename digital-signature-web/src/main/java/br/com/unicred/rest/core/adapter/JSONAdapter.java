package br.com.unicred.rest.core.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.unicred.rest.core.exception.JSONAdapterException;

public class JSONAdapter {

	private static final String FORMATO_DATA_PADRAO = "yyyy-MM-dd'T'HH:mm:ss";

	/**
	 * Construtor Padrão
	 */
	public JSONAdapter() {
		super();
	}

	/**
	 * Método que transforma do tipo JSON para objeto Java (POJO).
	 *
	 * @param json - Mensagem no formato JSON
	 * @param classe - Classe que será transformada a partir de uma mensagem JSON
	 * @return - Retorna a classe gerada transformada
	 * @throws JSONAdapterException - Lança uma exceção do tipo
	 * 				{@link br.com.unicred.restwsclient.exception.JSONAdapterException}
	 */
	public static Object adapterToObject(String json, Class<?> classe)
			throws JSONAdapterException {
		try {
			Gson gson = createGson();			
			return gson.fromJson(json, classe);
		} catch (Exception ex) {
			throw new JSONAdapterException(ex);
		}
	}

	/**
	 * Método que transforma do tipo Objeto Java (POJO) para JSON.
	 *
	 * @param object - Objeto que deve ser transformado em uma mesnagem no formato JSON
	 * @return - Retorna uma mensagem no formato JSON
	 * @throws JSONAdapterException - Lança uma exceção do tipo
	 * {@link br.com.unicred.restwsclient.exception.JSONAdapterException}
	 */
	public static String adapterToJSON(Object object)
			throws JSONAdapterException {
		try {
			Gson gson = createGson();
			return gson.toJson(object);
		} catch (Exception ex) {
			throw new JSONAdapterException(ex);
		}
	}

	/**
	 * Cria o Gson Builder comum para os adapters com as configurações padronizadas.
	 *
	 * @return - Retorna uma instância de {@link com.google.gson.Gson} contendo as
	 * 				configurações de datas.
	 * @throws JSONAdapterException - Lança uma exceção do tipo
	 * 					{@link br.com.unicred.restwsclient.exception.JSONAdapterException}
	 */
	private static Gson createGson() throws JSONAdapterException {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();

			gsonBuilder.registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter());
			gsonBuilder.serializeNulls();
			GsonBuilder dateFormatSetup = gsonBuilder.setDateFormat(FORMATO_DATA_PADRAO);
			return dateFormatSetup.create();
		} catch (Exception ex) {
			throw new JSONAdapterException(ex);
		}
	}

	
}
