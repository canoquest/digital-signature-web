package br.com.unicred.rest.core.adapter;

import javax.ws.rs.core.Response;

import br.com.unicred.rest.core.exception.JSONAdapterException;

public class APIJsonAdapter {

	/**
	 * M�todo respons�vel por retornar o objeto equivalente � mensagem JSON, que est�
	 * encapsulada no objeto {@link javax.ws.rs.core.Response}, obtido no retorno da
	 * requisi��o Rest.
	 *
	 * @param response - Objeto {@link javax.ws.rs.core.Response}, que encapsula informa��es de retorno
	 * 					da requisi��o Rest ao n�vel do protocolo HTTP.
	 * @param classe - Classe que define o padr�o de transforma��o de mensagem JSON para objeto Java.
	 * @return - Retorna um objeto da classe que define o padr�o de transforma��o de mensagem
	 * 				JSON para objeto Java.
	 * @throws JSONAdapterException - Lan�a uma exce��o do tipo
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
	 * M�todo respons�vel pela transforma��o de um objeto Java em uma mensagem em formato JSON.
	 *
	 * @param object - Objeto Java que deve ser transformado em uma mensagem JSON.
	 * @return - Retorna uma mensagem em formato JSON a partir do obejto Java informado.
	 * @throws JSONAdapterException - Lan�a uma exce��o do tipo
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
	 * M�todo respons�vel em obter a mensagem JSON encapsula no objeto
	 * {@link javax.ws.rs.core.Response}, oriundo do retorno da requisi��o Rest.
	 *
	 * @param response - Objeto {@link javax.ws.rs.core.Response}, que encapsula informa��es de retorno
	 * 					da requisi��o Rest ao n�vel do protocolo HTTP.
	 * @return - Retorna a mensagem JSON encapsula no objeto {@link javax.ws.rs.core.Response}
	 * @throws JSONAdapterException - Lan�a uma exce��o do tipo
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
	 * M�todo respons�vel pela transforma��o de uma mensagem em formato JSON para um objeto Java,
	 * definido pela classe informada como par�metro.
	 *
	 * @param json - Mensagem JSON
	 * @param classe - Classe que define o objeto no qual a mensagem ser� transformada.
	 * @return - Retorna um objeto Java definido pela classe informada
	 * @throws JSONAdapterException - Lan�a uma exce��o do tipo
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
