package org.anttribe.dbviewer.web.vo.dbTable;

import java.util.List;

/**
 * @author zhaoyong
 * @date 2021-01-05
 */
public class PrimaryKeyVo {

	/**
	 * 主键名称
	 */
	private String name;

	/**
	 * 主键列
	 */
	private List<String> columns;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

}