package org.anttribe.dbviewer.web.vo.dbSchema;

import org.anttribe.dbviewer.web.vo.Vo;

/**
 * @author zhaoyong
 * @date 2021-01-02
 */
public class DbSchemaVo extends Vo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5613159543741645964L;

	/**
	 * 全名(catalog.schema)
	 */
	private String fullName;

	/**
	 * 目录
	 */
	private String catalog;

	/**
	 * 模式
	 */
	private String schema;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

}