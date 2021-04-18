package org.anttribe.dbviewer.base.infra.docgenerator.naming;

/**
 * 时间戳 命名
 * 
 * @author zhaoyong
 * @date 2021年2月17日
 */
public class TimestampNamingHandler extends DatetimeNamingHandler {

	/**
	 * 默认时间格式化字符串
	 */
	private static final String DEFAULT_TIMESTAMP_FORMAT_PATTERN = "yyyyMMddHHmmssSSS";

	public TimestampNamingHandler() {
		super(DEFAULT_TIMESTAMP_FORMAT_PATTERN);
	}

}