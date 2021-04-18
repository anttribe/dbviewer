package org.anttribe.dbviewer.base.infra.docgenerator.engine.exception;

/**
 * @author zhaoyong
 * @date 2021年4月17日
 */
public class TemplateEngineException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2343804258357299359L;

	public TemplateEngineException() {
		super();
	}

	public TemplateEngineException(String message) {
		super(message);
	}

	public TemplateEngineException(Throwable cause) {
		super(cause);
	}

	public TemplateEngineException(String message, Throwable cause) {
		super(message, cause);
	}

	protected TemplateEngineException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}