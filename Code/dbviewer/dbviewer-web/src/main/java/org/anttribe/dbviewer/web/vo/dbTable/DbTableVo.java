package org.anttribe.dbviewer.web.vo.dbTable;

import java.util.List;

/**
 * @author zhaoyong
 * @date 2020年12月16日
 */
public class DbTableVo {

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 表注释
	 */
	private String comment;

	/**
	 * 数据库列
	 */
	private List<DbColumnVo> columns;

	/**
	 * 主键
	 */
	private PrimaryKeyVo primaryKey;

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

	public List<DbColumnVo> getColumns() {
		return columns;
	}

	public void setColumns(List<DbColumnVo> columns) {
		this.columns = columns;
	}

	public PrimaryKeyVo getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(PrimaryKeyVo primaryKey) {
		this.primaryKey = primaryKey;
	}

}