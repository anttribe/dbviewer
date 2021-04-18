package org.anttribe.dbviewer.base.runtime.manager.impl;

import java.util.List;

import org.anttribe.dbviewer.base.infra.dbassistor.DbAssistor;
import org.anttribe.dbviewer.base.infra.dbassistor.DbAssistorFactory;
import org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbSchema;
import org.anttribe.dbviewer.base.runtime.manager.IDbSchemaManager;
import org.springframework.stereotype.Component;

/**
 * @author zhaoyong
 * @date 2021-01-02
 */
@Component
public class DbSchemaManagerImpl implements IDbSchemaManager {

	@Override
	public List<DbSchema> list(DataSource dataSource, DbSchema criteria) {
		DbAssistor dbAssistor = DbAssistorFactory.me().getDbAssistor(dataSource);
		return dbAssistor.listSchemas();
	}

}