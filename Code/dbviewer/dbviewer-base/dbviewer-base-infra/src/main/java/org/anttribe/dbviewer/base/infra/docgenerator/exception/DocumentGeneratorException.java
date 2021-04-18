package org.anttribe.dbviewer.base.infra.docgenerator.exception;

/**
 * @author zhaoyong
 * @date 2021年4月8日
 */
public class DocumentGeneratorException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4357133041028366446L;

	public DocumentGeneratorException() {
		super();
	}

	public DocumentGeneratorException(String message) {
		super(message);
	}

	public DocumentGeneratorException(Throwable cause) {
		super(cause);
	}

	public DocumentGeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	protected DocumentGeneratorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}