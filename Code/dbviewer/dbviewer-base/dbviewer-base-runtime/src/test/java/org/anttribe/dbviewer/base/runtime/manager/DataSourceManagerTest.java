package org.anttribe.dbviewer.base.runtime.manager;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.anttribe.dbviewer.base.core.model.DataSourceDo;
import org.anttribe.dbviewer.base.infra.dbassistor.Dialect;
import org.anttribe.dbviewer.base.runtime.manager.IDataSourceManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhaoyong
 * @date 2020-11-21
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = DbViewerBaseContextConfiguration.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class DataSourceManagerTest {

	@Autowired
	private IDataSourceManager dataSourceManager;

	@Test
	public void testList() {
		assertNotNull(dataSourceManager);

		DataSourceDo criteria = new DataSourceDo();
		criteria.setAlias("admin");
		criteria.setDialect(Dialect.MYSQL);
		List<DataSourceDo> dataSources = dataSourceManager.list(criteria);
		System.out.println(dataSources);

	}

	@Test
	public void testPersist() {
		assertNotNull(dataSourceManager);

		DataSourceDo model = new DataSourceDo();
		model.setId(String.valueOf(System.currentTimeMillis()));
		model.setAlias("admin@localhost");
		// model.setDialect(Dialect.MYSQL);
		model.setUsername("admin");
		model.setPassword("123456");

		dataSourceManager.persist(model);
	}

	@Test
	public void testRemove() {
		assertNotNull(dataSourceManager);

		boolean r = dataSourceManager.remove("1606667961539", "1606667886342");
		System.out.println(r);
	}

}