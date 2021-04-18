package org.anttribe.dbviewer.web.vo.dbTable;

/**
 * @author zhaoyong
 * @date 2020年12月18日
 */
public class DbTableListParam {

	/**
	 * 数据源id
	 */
	private String dataSourceId;

	/**
	 * 数据库(全名fullName)
	 */
	private String dbSchema;

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
	
}