package org.anttribe.dbviewer.base.infra.dbassistor.metadata;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author zhaoyong
 * @date 2020-12-30
 */
public class DbSchema {

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

	/**
	 * 数据库表
	 */
	private List<DbTable> tables;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getFullName() {
		if (StringUtils.isEmpty(fullName)) {
			StringBuffer fullNameStrB = new StringBuffer();
			if (!StringUtils.isEmpty(catalog)) {
				fullNameStrB.append(catalog);
			}
			if (!StringUtils.isEmpty(schema)) {
				if (!StringUtils.isEmpty(catalog)) {
					fullNameStrB.append('.');
				}
				fullNameStrB.append(schema);
			}
			fullName = fullNameStrB.toString();
		}
		return fullName;
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

	public List<DbTable> getTables() {
		return tables;
	}

	public void setTables(List<DbTable> tables) {
		this.tables = tables;
	}

}