package br.com.unicred.rest.core.exception;

public class JSONAdapterException extends APIClientException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor padr�o
	 */
	public JSONAdapterException() {
		super();
	}

	/**
	 * Construtor para inicializa��o de propriedades
	 *
	 * @param message - Mensagem de exce��o lan�ada
	 * @param cause - Motivo que ocasionou a exce��o
	 * @param enableSuppression - Flag da super classe {@link java.lang.Exception}
	 * @param writableStackTrace - Flag da super classe {@link java.lang.Exception}
	 */
	public JSONAdapterException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Construtor para inicializa��o de propriedades
	 *
	 * @param message - Mensagem de exce��o lan�ada
	 * @param cause - Motivo que ocasionou a exce��o
	 */
	public JSONAdapterException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor para inicializa��o de propriedades
	 *
	 * @param message - Mensagem de exce��o lan�ada
	 */
	public JSONAdapterException(String message) {
		super(message);
	}

	/**
	 * Construtor para inicializa��o de propriedades
	 *
	 * @param cause - Motivo que ocasionou a exce��o
	 */
	public JSONAdapterException(Throwable cause) {
		super(cause);
	}
	
}