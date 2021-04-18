package org.anttribe.dbviewer.base.infra.docgenerator.naming;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期格式化命名
 * 
 * @author zhaoyong
 * @date 2021年2月17日
 */
public class DatetimeNamingHandler implements NamingHandler {

	/**
	 * 默认时间格式化字符串
	 */
	private static final String DEFAULT_FORMAT_PATTERN = "yyyyMMddHHmmssSSS";

	private String formatPattern;

	public DatetimeNamingHandler(String formatPattern) {
		this.formatPattern = formatPattern;
	}

	@Override
	public String naming() {
		if (StringUtils.isEmpty(formatPattern)) {
			formatPattern = DEFAULT_FORMAT_PATTERN;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);

		return dateFormat.format(new Date());
	}

	public String getFormatPattern() {
		return formatPattern;
	}

	public void setFormatPattern(String formatPattern) {
		this.formatPattern = formatPattern;
	}

}