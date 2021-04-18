package org.anttribe.dbviewer.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.anttribe.dbviewer.base.core.model.DataSourceDo;
import org.anttribe.dbviewer.base.infra.dbassistor.Dialect;
import org.anttribe.dbviewer.base.runtime.manager.IDataSourceManager;
import org.anttribe.dbviewer.web.service.IDataSourceService;
import org.anttribe.dbviewer.web.vo.dataSource.DataSourceAddParam;
import org.anttribe.dbviewer.web.vo.dataSource.DataSourceEditParam;
import org.anttribe.dbviewer.web.vo.dataSource.DataSourceListParam;
import org.anttribe.dbviewer.web.vo.dataSource.DataSourceVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyong
 * @date 2020-12-08
 */
@Service
public class DataSourceServiceImpl implements IDataSourceService {

	@Autowired
	private IDataSourceManager dataSourceManager;

	@Override
	public List<DataSourceVo> list(DataSourceListParam dataSourceListParam) {
		List<DataSourceVo> dataSourceVos = new ArrayList<DataSourceVo>();

		// 设置查询条件
		DataSourceDo criteria = new DataSourceDo();
		criteria.setAlias(dataSourceListParam.getSearchAlias());

		List<DataSourceDo> dataSources = dataSourceManager.list(criteria);
		if (!CollectionUtils.isEmpty(dataSources)) {
			for (DataSourceDo dataSource : dataSources) {
				DataSourceVo dataSourceVo = new DataSourceVo();
				dataSourceVo.setId(dataSource.getId());
				dataSourceVo.setAlias(dataSource.getAlias());
				if (null != dataSource.getDialect()) {
					dataSourceVo.setDialect(dataSource.getDialect().name());
				}
				dataSourceVo.setConnUrl(dataSource.getConnUrl());
				dataSourceVos.add(dataSourceVo);
			}
		}
		return dataSourceVos;
	}

	@Override
	public void add(DataSourceAddParam dataSourceAddParam) {
		DataSourceDo dataSource = new DataSourceDo();
		dataSource.setAlias(dataSourceAddParam.getAlias());
		if (!StringUtils.isEmpty(dataSourceAddParam.getDialect())) {
			Dialect dialect = Dialect.valueOf(dataSourceAddParam.getDialect());
			if (null != dialect) {
				dataSource.setDialect(dialect);
			}
		}
		dataSource.setConnUrl(dataSourceAddParam.getConnUrl());
		dataSource.setUsername(dataSourceAddParam.getUsername());
		dataSource.setPassword(dataSourceAddParam.getPassword());
		dataSourceManager.persist(dataSource);
	}

	@Override
	public void edit(DataSourceEditParam dataSourceEditParam) {
		DataSourceDo dataSource = new DataSourceDo();
		dataSource.setId(dataSourceEditParam.getId());
		dataSource.setAlias(dataSourceEditParam.getAlias());
		if (!StringUtils.isEmpty(dataSourceEditParam.getDialect())) {
			Dialect dialect = Dialect.valueOf(dataSourceEditParam.getDialect());
			if (null != dialect) {
				dataSource.setDialect(dialect);
			}
		}
		dataSource.setConnUrl(dataSourceEditParam.getConnUrl());
		dataSource.setUsername(dataSourceEditParam.getUsername());
		dataSource.setPassword(dataSourceEditParam.getPassword());

		dataSourceManager.persist(dataSource);
	}

	@Override
	public DataSourceVo getById(String id) {
		DataSourceDo dataSource = dataSourceManager.get(id);
		if (null == dataSource) {
			return null;
		}

		DataSourceVo dataSourceVo = new DataSourceVo();
		dataSourceVo.setId(dataSource.getId());
		dataSourceVo.setAlias(dataSource.getAlias());
		if (null != dataSource.getDialect()) {
			dataSourceVo.setDialect(dataSource.getDialect().name());
		}
		dataSourceVo.setConnUrl(dataSource.getConnUrl());
		dataSourceVo.setUsername(dataSource.getUsername());
		dataSourceVo.setPassword(dataSource.getPassword());
		return dataSourceVo;
	}

	@Override
	public void remove(String id) {
		dataSourceManager.remove(id);
	}

}