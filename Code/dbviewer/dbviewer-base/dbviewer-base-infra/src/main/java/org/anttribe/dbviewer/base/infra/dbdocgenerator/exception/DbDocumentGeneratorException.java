package org.anttribe.dbviewer.base.infra.dbdocgenerator.exception;

import org.anttribe.dbviewer.base.infra.docgenerator.exception.DocumentGeneratorException;

/**
 * @author zhaoyong
 * @date 2021年4月8日
 */
public class DbDocumentGeneratorException extends DocumentGeneratorException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6299112725858043922L;

	public DbDocumentGeneratorException() {
		super();
	}

	public DbDocumentGeneratorException(String message) {
		super(message);
	}

	public DbDocumentGeneratorException(Throwable cause) {
		super(cause);
	}

	public DbDocumentGeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	protected DbDocumentGeneratorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}