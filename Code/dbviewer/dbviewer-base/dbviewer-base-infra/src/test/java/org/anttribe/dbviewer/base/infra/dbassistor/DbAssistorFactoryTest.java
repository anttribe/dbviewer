package org.anttribe.dbviewer.base.infra.dbassistor;

import org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource;
import org.junit.Test;

/**
 * @author zhaoyong
 * @date 2020-12-25
 */
public class DbAssistorFactoryTest {

	@Test
	public void testGetDbAssistorFactory() {
		DbAssistorFactory factory = DbAssistorFactory.me();
		System.out.println(factory);
	}

	@Test
	public void testGetDbAssistor() {
		DataSource dataSource = new DataSource();
		dataSource.setDialect(Dialect.MYSQL);
		dataSource.setUrl(Dialect.MYSQL.populateDbConnectUrl("localhost", "3306", "db_dbviewer"));
		dataSource.setUsername("admin");
		dataSource.setPassword("123456");

		DbAssistor assistor = DbAssistorFactory.me().getDbAssistor(dataSource);
		System.out.println(assistor);
	}

}