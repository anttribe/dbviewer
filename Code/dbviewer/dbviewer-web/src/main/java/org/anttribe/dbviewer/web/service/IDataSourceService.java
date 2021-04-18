package org.anttribe.dbviewer.web.service;

import java.util.List;

import org.anttribe.dbviewer.web.vo.dataSource.DataSourceAddParam;
import org.anttribe.dbviewer.web.vo.dataSource.DataSourceEditParam;
import org.anttribe.dbviewer.web.vo.dataSource.DataSourceListParam;
import org.anttribe.dbviewer.web.vo.dataSource.DataSourceVo;

/**
 * @author zhaoyong
 * @date 2020-12-08
 */
public interface IDataSourceService {

	/**
	 * 列表查询数据源
	 * 
	 * @param dataSourceListParam
	 * @return
	 */
	List<DataSourceVo> list(DataSourceListParam dataSourceListParam);

	/**
	 * 新增数据
	 * 
	 * @param dataSourceAddParam
	 */
	void add(DataSourceAddParam dataSourceAddParam);

	/**
	 * 编辑数据
	 * 
	 * @param dataSourceEditParam
	 */
	void edit(DataSourceEditParam dataSourceEditParam);

	/**
	 * 根据id获取数据源
	 * 
	 * @param id
	 * @return
	 */
	DataSourceVo getById(String id);

	void remove(String id);
}