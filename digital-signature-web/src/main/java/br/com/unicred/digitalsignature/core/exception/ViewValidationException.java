package br.com.unicred.digitalsignature.core.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe base de exce��o para ser adotada na camada de consumo de servi�os.
 *
 * @author carlos.costa
 */
public class ViewValidationException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Throwable cause;
	protected String message;
	protected List<String> listMessages;

	/**
	 * Construtor Padr�o
	 */
	public ViewValidationException() {
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
	public ViewValidationException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		addMessage(message);
	}

	/**
	 * Construtor para inicializa��o de propriedades
	 *
	 * @param message - Mensagem de exce��o lan�ada
	 * @param cause - Motivo que ocasionou a exce��o
	 */
	public ViewValidationException(final String message, final Throwable cause) {
		super(message, cause);
		addMessage(message);
		this.message = message;
		this.cause = cause;
	}

	/**
	 * Construtor para inicializa��o de propriedades
	 *
	 * @param message - Mensagem de exce��o lan�ada
	 */
	public ViewValidationException(final String message) {
		super(message);
		addMessage(message);
		this.message = message;
	}

	/**
	 * Construtor para inicializa��o de propriedades
	 *
	 * @param cause - Motivo que ocasionou a exce��o
	 */
	public ViewValidationException(final Throwable cause) {
		super(cause);
		this.cause = cause;
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public synchronized Throwable getCause() {
		return super.getCause();
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return super.getStackTrace();
	}

	/**
	 * Retorna a lista de mensagens de exce��o.
	 *
	 * @return - Retorna um objeto do tipo java.util.List
	 */
	protected List<String> getMessagesCause() {
		return this.listMessages;
	}

	/**
	 * Adiciona uma nova mensagem na lista de mensagens
	 *
	 * @param message - Mensagem de exce��o lan�ada
	 */
	protected void addMessage(final String message) {
		if (listMessages == null) {
			listMessages = new ArrayList<String>();
		}
		listMessages.add(message);
	}

}

