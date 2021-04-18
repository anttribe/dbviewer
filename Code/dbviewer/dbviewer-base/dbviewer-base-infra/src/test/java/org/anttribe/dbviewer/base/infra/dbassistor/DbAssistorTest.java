package org.anttribe.dbviewer.base.infra.dbassistor;

import java.util.List;

import org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbTable;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhaoyong
 * @date 2020-12-25
 */
public class DbAssistorTest {

	DbAssistor assistor = null;

	@Before
	public void before() {
		DataSource dataSource = new DataSource();
		dataSource.setDialect(Dialect.MYSQL);
		dataSource.setUrl(Dialect.MYSQL.populateDbConnectUrl("localhost", "3306", "db_dbviewer"));
		dataSource.setUsername("admin");
		dataSource.setPassword("123456");

		assistor = DbAssistorFactory.me().getDbAssistor(dataSource);
	}

	@Test
	public void testListTables() {
		List<DbTable> dbTables = assistor.listTables();
		System.out.println(dbTables);
	}

	@Test
	public void testListTablesWithTableNamePattern() {
		List<DbTable> dbTables = assistor.listTables("^t\\_database\\w+");
		System.out.println(dbTables);
	}

	@Test
	public void testGetTable() {
		DbTable dbTable = assistor.getTable("t_datasource_info");
		System.out.println(dbTable);
	}

	@Test
	public void testListSchemaTables() {
		List<DbTable> dbTables = assistor.listSchemaTables("db_favorites");
		System.out.println(dbTables);
	}

	@Test
	public void testListSchemaTablesWithTableNamePattern() {
		List<DbTable> dbTables = assistor.listSchemaTables("db_favorites", "^t\\_favorite\\w+");
		System.out.println(dbTables);

		String tableNamePattern = "(^_([a-zA-Z0-9]_?)*$)|(^[a-zA-Z](_?[a-zA-Z0-9])*_?$)";
		dbTables = assistor.listSchemaTables("db_favorites", tableNamePattern);
		System.out.println(dbTables);
	}

	@Test
	public void testGetSchemaTable() {
		DbTable dbTable = assistor.getSchemaTable("db_favorites", "t_favorite_info");
		System.out.println(dbTable);
	}

}