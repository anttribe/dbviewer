package org.anttribe.dbviewer.web.vo.dbTable;

/**
 * @author zhaoyong
 * @date 2020年12月18日
 */
public class DbColumnVo {

	/**
	 * 列名
	 */
	private String columnName;

	/**
	 * 注释
	 */
	private String comment;

	/**
	 * 数据库类型
	 */
	private String jdbcType;

	/**
	 * 长度
	 */
	private int size;

	/**
	 * 精度
	 */
	private int precision;

	/**
	 * 小数位
	 */
	private int digits;

	/**
	 * 默认值
	 */
	private String defaultVal;

	/**
	 * 是否允许空
	 */
	private boolean nullable = true;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPrecision() {
		return precision;
	}

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public String getDefaultVal() {
		return defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

}