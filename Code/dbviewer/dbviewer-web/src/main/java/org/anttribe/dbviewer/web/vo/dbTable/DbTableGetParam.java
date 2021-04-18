package org.anttribe.dbviewer.web.vo.dbTable;

/**
 * @author zhaoyong
 * @date 2020年12月18日
 */
public class DbTableGetParam {

	/**
	 * 数据源id
	 */
	private String dataSourceId;

	/**
	 * 数据库(全名fullName)
	 */
	private String dbSchema;

	/**
	 * 数据表名
	 */
	private String dbTableName;

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getDbSchema() {
		return dbSchema;
	}

	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}

	public String getDbTableName() {
		return dbTableName;
	}

	public void setDbTableName(String dbTableName) {
		this.dbTableName = dbTableName;
	}

}