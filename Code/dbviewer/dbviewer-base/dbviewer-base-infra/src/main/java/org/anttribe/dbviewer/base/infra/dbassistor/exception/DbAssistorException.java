package org.anttribe.dbviewer.base.infra.dbassistor.exception;

/**
 * @author zhaoyong
 * @date 2020-12-14
 */
public class DbAssistorException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7951593880281651827L;

	public DbAssistorException() {
		super();
	}

	public DbAssistorException(String message) {
		super(message);
	}

	public DbAssistorException(Throwable cause) {
		super(cause);
	}

	public DbAssistorException(String message, Throwable cause) {
		super(message, cause);
	}

	protected DbAssistorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}