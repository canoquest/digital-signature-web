package br.com.unicred.rest.core.adapter;

import javax.ws.rs.core.Response;

import br.com.unicred.rest.core.exception.JSONAdapterException;

public class APIJsonAdapter {

	/**
	 * Método responsável por retornar o objeto equivalente à mensagem JSON, que está
	 * encapsulada no objeto {@link javax.ws.rs.core.Response}, obtido no retorno da
	 * requisição Rest.
	 *
	 * @param response - Objeto {@link javax.ws.rs.core.Response}, que encapsula informações de retorno
	 * 					da requisição Rest ao nível do protocolo HTTP.
	 * @param classe - Classe que define o padrão de transformação de mensagem JSON para objeto Java.
	 * @return - Retorna um objeto da classe que define o padrão de transformação de mensagem
	 * 				JSON para objeto Java.
	 * @throws JSONAdapterException - Lança uma exceção do tipo
	 * 				{@link br.com.unicred.restwsclient.exception.JSONAdapterException}
	 */
	public static final Object adapterFromJsonToObject(final Response response, final Class<?> classe)
			throws JSONAdapterException {
		try {
			final String json = adapterToJSON(response);
			return adapterToObject(json, classe);
		} catch (final JSONAdapterException ex) {
			throw new JSONAdapterException(ex.getMessage());
		}
	}

	/**
	 * Método responsável pela transformação de um objeto Java em uma mensagem em formato JSON.
	 *
	 * @param object - Objeto Java que deve ser transformado em uma mensagem JSON.
	 * @return - Retorna uma mensagem em formato JSON a partir do obejto Java informado.
	 * @throws JSONAdapterException - Lança uma exceção do tipo
	 * 				{@link br.com.unicred.restwsclient.exception.JSONAdapterException}
	 */
	public static final String adapterFromObjectToJson(final Object object)
			throws JSONAdapterException {
		try {
			return JSONAdapter.adapterToJSON(object);
		} catch (final Exception ex) {
			throw new JSONAdapterException(ex.getMessage());
		}
	}

	/**
	 * Método responsável em obter a mensagem JSON encapsula no objeto
	 * {@link javax.ws.rs.core.Response}, oriundo do retorno da requisição Rest.
	 *
	 * @param response - Objeto {@link javax.ws.rs.core.Response}, que encapsula informações de retorno
	 * 					da requisição Rest ao nível do protocolo HTTP.
	 * @return - Retorna a mensagem JSON encapsula no objeto {@link javax.ws.rs.core.Response}
	 * @throws JSONAdapterException - Lança uma exceção do tipo
	 * 				{@link br.com.unicred.restwsclient.exception.JSONAdapterException}
	 */
	public static String adapterToJSON(final Response response)
			throws JSONAdapterException {
		try {
			return response.readEntity(String.class);
		} catch (final Exception ex) {
			throw new JSONAdapterException(ex.getMessage());
		}
	}

	/**
	 * Método responsável pela transformação de uma mensagem em formato JSON para um objeto Java,
	 * definido pela classe informada como parâmetro.
	 *
	 * @param json - Mensagem JSON
	 * @param classe - Classe que define o objeto no qual a mensagem será transformada.
	 * @return - Retorna um objeto Java definido pela classe informada
	 * @throws JSONAdapterException - Lança uma exceção do tipo
	 * 				{@link br.com.unicred.restwsclient.exception.JSONAdapterException}
	 */
	public static Object adapterToObject(final String json, final Class<?> classe)
			throws JSONAdapterException {
		try {
			return JSONAdapter.adapterToObject(json, classe);
		} catch (final Exception ex) {
			throw new JSONAdapterException(ex);
		}
	}

		
}
