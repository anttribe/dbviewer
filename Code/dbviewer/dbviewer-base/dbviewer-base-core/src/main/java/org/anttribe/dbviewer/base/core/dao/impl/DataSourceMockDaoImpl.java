package org.anttribe.dbviewer.base.core.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.anttribe.dbviewer.base.core.dao.IDataSourceDao;
import org.anttribe.dbviewer.base.core.model.DataSourceDo;
import org.anttribe.dbviewer.base.infra.dbassistor.Dialect;
import org.anttribe.dbviewer.base.infra.support.dao.annotations.MockDao;
import org.anttribe.dbviewer.base.infra.utils.UUIDUtils;

/**
 * @author zhaoyong
 * @date 2020年12月14日
 */
@MockDao
public class DataSourceMockDaoImpl implements IDataSourceDao {

	@Override
	public int insert(DataSourceDo model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int batchInsert(List<DataSourceDo> models) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(DataSourceDo model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int batchUpdate(List<DataSourceDo> models) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int batchRemove(Serializable... ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DataSourceDo> list(DataSourceDo criteria) {
		List<DataSourceDo> dataSources = new ArrayList<DataSourceDo>();
		for (int i = 0; i < 11; i++) {
			DataSourceDo dataSource = new DataSourceDo();
			dataSource.setId(UUIDUtils.getRandomUUID());
			dataSource.setAlias("admin@localhost" + i);
			dataSource.setDialect(Dialect.MYSQL);
			dataSource.setUsername("admin");
			dataSource.setPassword("");
			dataSources.add(dataSource);
		}
		return dataSources;
	}

	@Override
	public List<DataSourceDo> list(Map<String, Object> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSourceDo find(DataSourceDo criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSourceDo find(Map<String, Object> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSourceDo get(Serializable id) {
		DataSourceDo dataSource = new DataSourceDo();
		dataSource.setId(UUIDUtils.getRandomUUID());
		dataSource.setAlias("admin@localhost");
		dataSource.setDialect(Dialect.MYSQL);
		dataSource.setConnUrl("");
		dataSource.setUsername("admin");
		dataSource.setPassword("");

		return dataSource;
	}

}
