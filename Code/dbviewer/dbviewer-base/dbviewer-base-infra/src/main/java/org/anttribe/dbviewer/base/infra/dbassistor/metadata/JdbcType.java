package org.anttribe.dbviewer.base.infra.dbassistor.metadata;

/**
 * JDBC类型
 * 
 * @author zhaoyong
 * @version 2015年12月21日
 */
public enum JdbcType {
	/** */
	ARRAY(java.sql.Types.ARRAY),
	/** */
	BIGINT(java.sql.Types.BIGINT),
	/** */
	BINARY(java.sql.Types.BINARY),
	/** */
	BIT(java.sql.Types.BIT),
	/** */
	BLOB(java.sql.Types.BLOB),
	/** */
	BOOLEAN(java.sql.Types.BOOLEAN),
	/** */
	CHAR(java.sql.Types.CHAR),
	/** */
	CLOB(java.sql.Types.CLOB),
	/** */
	DATALINK(java.sql.Types.DATALINK),
	/** */
	DATE(java.sql.Types.DATE),
	/** */
	DECIMAL(java.sql.Types.DECIMAL),
	/** */
	DISTINCT(java.sql.Types.DISTINCT),
	/** */
	DOUBLE(java.sql.Types.DOUBLE),
	/** */
	FLOAT(java.sql.Types.FLOAT),
	/** */
	INTEGER(java.sql.Types.INTEGER),
	/** */
	JAVA_OBJECT(java.sql.Types.JAVA_OBJECT),
	/** */
	LONGNVARCHAR(java.sql.Types.LONGNVARCHAR),
	/** */
	LONGVARBINARY(java.sql.Types.LONGVARBINARY),
	/** */
	LONGVARCHAR(java.sql.Types.LONGVARCHAR),
	/** */
	NCHAR(java.sql.Types.NCHAR),
	/** */
	NCLOB(java.sql.Types.NCLOB),
	/** */
	NULL(java.sql.Types.NULL),
	/** */
	NUMERIC("NUMBER", java.sql.Types.NUMERIC, 10, 2),
	/** */
	NVARCHAR(java.sql.Types.NVARCHAR),
	/** */
	OTHER(java.sql.Types.OTHER),
	/** */
	REAL(java.sql.Types.REAL),
	/** */
	REF(java.sql.Types.REF),
	/** */
	ROWID(java.sql.Types.ROWID),
	/** */
	SMALLINT(java.sql.Types.SMALLINT),
	/** */
	SQLXML(java.sql.Types.SQLXML),
	/** */
	STRUCT(java.sql.Types.STRUCT),
	/** */
	TIME(java.sql.Types.TIME),
	/** */
	TIMESTAMP(java.sql.Types.TIMESTAMP),
	/** */
	TINYINT(java.sql.Types.TINYINT),
	/** */
	VARBINARY(java.sql.Types.VARBINARY),
	/** */
	VARCHAR(java.sql.Types.VARCHAR),
	/** */
	VARCHAR2("VARCHAR2", java.sql.Types.VARCHAR, 100);

	private int sqlType;

	/**
	 * 编码
	 */
	private String code;

	/**
	 * 精度位
	 */
	private int precision;

	/**
	 * 小数位
	 */
	private int digits;

	private JdbcType(int sqlType) {
		this.sqlType = sqlType;
		this.code = this.name();
	}

	private JdbcType(String code, int sqlType) {
		this.code = code;
		this.sqlType = sqlType;
	}

	private JdbcType(int sqlType, int precision) {
		this.code = this.name();
		this.sqlType = sqlType;
		this.precision = precision;
	}

	private JdbcType(String code, int sqlType, int precision) {
		this.code = code;
		this.sqlType = sqlType;
		this.precision = precision;
	}

	private JdbcType(int sqlType, int precision, int digits) {
		this.code = this.name();
		this.sqlType = sqlType;
		this.precision = precision;
		this.digits = digits;
	}

	private JdbcType(String code, int sqlType, int precision, int digits) {
		this.code = code;
		this.sqlType = sqlType;
		this.precision = precision;
		this.digits = digits;
	}

	public int getSqlType() {
		return sqlType;
	}

	public String getCode() {
		return code;
	}

	public int getPrecision() {
		return precision;
	}

	public int getDigits() {
		return digits;
	}

	public static JdbcType valueOf(int sqlType) {
		JdbcType[] types = JdbcType.values();
		for (JdbcType jdbcType : types) {
			if (jdbcType.sqlType == sqlType) {
				return jdbcType;
			}
		}
		return null;
	}

	public static JdbcType valueOfCode(String code) {
		JdbcType[] types = JdbcType.values();
		for (JdbcType jdbcType : types) {
			if (code.equalsIgnoreCase(jdbcType.getCode())) {
				return jdbcType;
			}
		}
		return null;
	}
}