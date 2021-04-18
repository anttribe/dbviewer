package org.anttribe.dbviewer.web.vo;

/**
 * 抽象结果
 * 
 * @author zhaoyong
 * @date 2020-12-12
 */
public class Result<T> {

	/**
	 * 结果响应码
	 */
	private String resultCode;

	/**
	 * 响应消息
	 */
	private String message;

	/**
	 * 消息体
	 */
	private T resultBody;

	public Result() {
	}

	public Result(String resultCode) {
		this.resultCode = resultCode;
	}

	public Result(String resultCode, T resultBody) {
		this.resultCode = resultCode;
		this.resultBody = resultBody;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResultBody() {
		return resultBody;
	}

	public void setResultBody(T resultBody) {
		this.resultBody = resultBody;
	}

}