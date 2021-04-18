package org.anttribe.dbviewer.base.infra.dbassistor.introspector;

import org.anttribe.dbviewer.base.infra.dbassistor.DbAssistor;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbSchema;

/**
 * @author zhaoyong
 * @date 2020-12-13
 */
public class MysqlDatabaseIntrospector extends DatabaseIntrospector {

	public MysqlDatabaseIntrospector(DbSchema schema, DbAssistor dbAssistor) {
		super(schema, dbAssistor);
	}

}