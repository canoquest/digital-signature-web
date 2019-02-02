package br.com.unicred.digitalsignature.core.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe base de exceção para ser adotada na camada de consumo de serviços.
 *
 * @author carlos.costa
 */
public class ViewValidationException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Throwable cause;
	protected String message;
	protected List<String> listMessages;

	/**
	 * Construtor Padrão
	 */
	public ViewValidationException() {
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
	public ViewValidationException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		addMessage(message);
	}

	/**
	 * Construtor para inicialização de propriedades
	 *
	 * @param message - Mensagem de exceção lançada
	 * @param cause - Motivo que ocasionou a exceção
	 */
	public ViewValidationException(final String message, final Throwable cause) {
		super(message, cause);
		addMessage(message);
		this.message = message;
		this.cause = cause;
	}

	/**
	 * Construtor para inicialização de propriedades
	 *
	 * @param message - Mensagem de exceção lançada
	 */
	public ViewValidationException(final String message) {
		super(message);
		addMessage(message);
		this.message = message;
	}

	/**
	 * Construtor para inicialização de propriedades
	 *
	 * @param cause - Motivo que ocasionou a exceção
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
	 * Retorna a lista de mensagens de exceção.
	 *
	 * @return - Retorna um objeto do tipo java.util.List
	 */
	protected List<String> getMessagesCause() {
		return this.listMessages;
	}

	/**
	 * Adiciona uma nova mensagem na lista de mensagens
	 *
	 * @param message - Mensagem de exceção lançada
	 */
	protected void addMessage(final String message) {
		if (listMessages == null) {
			listMessages = new ArrayList<String>();
		}
		listMessages.add(message);
	}

}

