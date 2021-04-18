package org.anttribe.dbviewer.base.infra.dbassistor.metadata;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 数据列
 * 
 * @author zhaoyong
 * @date 2020-12-13
 */
public class DbColumn {

	/**
	 * 列名
	 */
	private String columnName;

	/**
	 * 注释说明
	 */
	private String comment;

	/**
	 * 是否为主键
	 */
	private boolean isPrimaryKey = false;

	/**
	 * 值能否为空, 默认允许
	 */
	private boolean nullable = true;

	/**
	 * jdbc数据类型
	 */
	private JdbcType jdbcType;

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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

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

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public JdbcType getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(JdbcType jdbcType) {
		this.jdbcType = jdbcType;
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

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}

	public String getDefaultVal() {
		return defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

}