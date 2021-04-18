package org.anttribe.dbviewer.base.core.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.anttribe.dbviewer.base.core.dao.IDataSourceDao;
import org.anttribe.dbviewer.base.core.model.DataSourceDo;
import org.anttribe.dbviewer.base.infra.support.dao.annotations.MybatisDao;
import org.springframework.stereotype.Repository;

/**
 * @author zhaoyong
 * @date 2020-11-29
 */
@Repository
@MybatisDao
public class DataSourceMybatisDaoImpl implements IDataSourceDao {

	@Override
	public int insert(DataSourceDo model) {
		return 0;
	}

	@Override
	public int batchInsert(List<DataSourceDo> models) {
		return 0;
	}

	@Override
	public int update(DataSourceDo model) {
		return 0;
	}

	@Override
	public int remove(Serializable id) {
		return 0;
	}

	@Override
	public List<DataSourceDo> list(DataSourceDo criteria) {
		return null;
	}

	@Override
	public List<DataSourceDo> list(Map<String, Object> criteria) {
		return null;
	}

	@Override
	public DataSourceDo find(DataSourceDo criteria) {
		return null;
	}

	@Override
	public DataSourceDo find(Map<String, Object> criteria) {
		return null;
	}

	@Override
	public DataSourceDo get(Serializable id) {
		return null;
	}

	@Override
	public int batchRemove(Serializable... ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int batchUpdate(List<DataSourceDo> models) {
		// TODO Auto-generated method stub
		return 0;
	}

}