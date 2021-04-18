package org.anttribe.dbviewer.base.infra.dbassistor.metadata;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 数据表
 * 
 * @author zhaoyong
 * @date 2020-12-13
 */
public class DbTable {

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 注释说明
	 */
	private String comment;

	/**
	 * 类型: 表
	 */
	private DbObjectType type = DbObjectType.TABLE;

	/**
	 * 列
	 */
	private List<DbColumn> columns;

	/**
	 * 主键
	 */
	private PrimaryKey primaryKey;

	public DbTable() {
		columns = new ArrayList<DbColumn>();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public DbObjectType getType() {
		return type;
	}

	public void setType(DbObjectType type) {
		this.type = type;
	}

	public List<DbColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<DbColumn> columns) {
		this.columns = columns;
	}

	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(PrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

}