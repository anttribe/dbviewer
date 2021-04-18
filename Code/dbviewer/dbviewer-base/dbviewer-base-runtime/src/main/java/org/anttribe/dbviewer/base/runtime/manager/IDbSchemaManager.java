package org.anttribe.dbviewer.base.runtime.manager;

import java.util.List;

import org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbSchema;

/**
 * @author zhaoyong
 * @date 2021-01-02
 */
public interface IDbSchemaManager {

	/**
	 * 列表获取数据源下的schema
	 * 
	 * @param dataSource
	 * @param criteria
	 * @return
	 */
	List<DbSchema> list(DataSource dataSource, DbSchema criteria);

}