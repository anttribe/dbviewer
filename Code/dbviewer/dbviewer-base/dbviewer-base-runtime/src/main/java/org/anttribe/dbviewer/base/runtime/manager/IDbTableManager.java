package org.anttribe.dbviewer.base.runtime.manager;

import java.util.List;

import org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbTable;

/**
 * @author zhaoyong
 * @date 2021-01-02
 */
public interface IDbTableManager {

	/**
	 * 列表获取指定数据库下的表
	 * 
	 * @param dataSource
	 * @param dbSchema
	 * @param criteria
	 * @return
	 */
	List<DbTable> list(DataSource dataSource, String dbSchema, DbTable criteria);

	/**
	 * 根据数据表名获取表信息
	 * 
	 * @param dataSource
	 * @param dbSchema
	 * @param dbTableName
	 * @return
	 */
	DbTable get(DataSource dataSource, String dbSchema, String dbTableName);

}