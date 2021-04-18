package org.anttribe.dbviewer.base.runtime.manager.impl;

import java.util.List;

import org.anttribe.dbviewer.base.infra.dbassistor.DbAssistor;
import org.anttribe.dbviewer.base.infra.dbassistor.DbAssistorFactory;
import org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbTable;
import org.anttribe.dbviewer.base.runtime.manager.IDbTableManager;
import org.springframework.stereotype.Component;

/**
 * @author zhaoyong
 * @date 2021-01-02
 */
@Component
public class DbTableManagerImpl implements IDbTableManager {

	@Override
	public List<DbTable> list(DataSource dataSource, String dbSchema, DbTable criteria) {
		DbAssistor dbAssistor = DbAssistorFactory.me().getDbAssistor(dataSource);
		return dbAssistor.listSchemaTables(dbSchema);
	}

	@Override
	public DbTable get(DataSource dataSource, String dbSchema, String dbTableName) {
		DbAssistor dbAssistor = DbAssistorFactory.me().getDbAssistor(dataSource);
		return dbAssistor.getSchemaTable(dbSchema, dbTableName);
	}

}