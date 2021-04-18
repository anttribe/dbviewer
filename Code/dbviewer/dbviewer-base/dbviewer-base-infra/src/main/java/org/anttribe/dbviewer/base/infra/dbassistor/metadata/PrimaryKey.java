package org.anttribe.dbviewer.base.infra.dbassistor.metadata;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.CollectionUtils;

/**
 * 主键
 * 
 * @author zhaoyong
 * @date 2021-01-04
 */
public class PrimaryKey {

	/**
	 * 主键名称
	 */
	private String name;

	/**
	 * 主键列
	 */
	private List<String> columns;

	/**
	 * 内部实现的主键列
	 */
	private Set<PrimaryKeyColumn> innerColumns;

	public PrimaryKey() {
		columns = new ArrayList<String>();
		innerColumns = new TreeSet<PrimaryKeyColumn>(new Comparator<PrimaryKeyColumn>() {
			@Override
			public int compare(PrimaryKeyColumn o1, PrimaryKeyColumn o2) {
				return o1.getSeq() - o2.getSeq();
			}
		});
	}

	/**
	 * 添加主键列
	 * 
	 * @param columnName
	 * @param seq
	 */
	public void addColumn(String columnName, short seq) {
		innerColumns.add(new PrimaryKeyColumn(columnName, seq));
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * 主键列
	 * 
	 * @author zhaoyong
	 * @date 2021-01-04
	 */
	class PrimaryKeyColumn {
		/**
		 * 列名
		 */
		private String columnName;

		/**
		 * 序列
		 */
		private short seq;

		public PrimaryKeyColumn(String columnName, short seq) {
			this.columnName = columnName;
			this.seq = seq;
		}

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public short getSeq() {
			return seq;
		}

		public void setSeq(short seq) {
			this.seq = seq;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getColumns() {
		if (CollectionUtils.isEmpty(columns) && !CollectionUtils.isEmpty(innerColumns)) {
			for (PrimaryKeyColumn primaryKeyColumn : innerColumns) {
				columns.add(primaryKeyColumn.getColumnName());
			}
		}
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

}