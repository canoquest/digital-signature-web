package br.com.unicred.rest.core.exception;

public class JSONAdapterException extends APIClientException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor padrão
	 */
	public JSONAdapterException() {
		super();
	}

	/**
	 * Construtor para inicialização de propriedades
	 *
	 * @param message - Mensagem de exceção lançada
	 * @param cause - Motivo que ocasionou a exceção
	 * @param enableSuppression - Flag da super classe {@link java.lang.Exception}
	 * @param writableStackTrace - Flag da super classe {@link java.lang.Exception}
	 */
	public JSONAdapterException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Construtor para inicialização de propriedades
	 *
	 * @param message - Mensagem de exceção lançada
	 * @param cause - Motivo que ocasionou a exceção
	 */
	public JSONAdapterException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor para inicialização de propriedades
	 *
	 * @param message - Mensagem de exceção lançada
	 */
	public JSONAdapterException(final String message) {
		super(message);
	}

	/**
	 * Construtor para inicialização de propriedades
	 *
	 * @param cause - Motivo que ocasionou a exceção
	 */
	public JSONAdapterException(final Throwable cause) {
		super(cause);
	}
	
}