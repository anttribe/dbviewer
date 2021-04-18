package org.anttribe.dbviewer.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.anttribe.dbviewer.base.core.model.DataSourceDo;
import org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbSchema;
import org.anttribe.dbviewer.base.runtime.manager.IDataSourceManager;
import org.anttribe.dbviewer.base.runtime.manager.IDbSchemaManager;
import org.anttribe.dbviewer.web.service.IDbSchemaService;
import org.anttribe.dbviewer.web.vo.dbSchema.DbSchemaListParam;
import org.anttribe.dbviewer.web.vo.dbSchema.DbSchemaVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyong
 * @date 2021-01-02
 */
@Service
public class DbSchemaServiceImpl implements IDbSchemaService {

	@Autowired
	private IDataSourceManager dataSourceManager;

	@Autowired
	private IDbSchemaManager dbSchemaManager;

	@Override
	public List<DbSchemaVo> list(DbSchemaListParam dbSchemaListParam) {
		DataSourceDo dataSourceDo = dataSourceManager.get(dbSchemaListParam.getDataSourceId());
		if (null == dataSourceDo) {
			return null;
		}

		DataSource dataSource = new DataSource();
		dataSource.setDialect(dataSourceDo.getDialect());
		dataSource.setUrl(dataSourceDo.getConnUrl());
		dataSource.setUsername(dataSourceDo.getUsername());
		dataSource.setPassword(dataSourceDo.getPassword());
		List<DbSchema> dbSchemas = dbSchemaManager.list(dataSource, new DbSchema());
		if (CollectionUtils.isEmpty(dbSchemas)) {
			return null;
		}

		List<DbSchemaVo> dbSchemaVos = new ArrayList<DbSchemaVo>();
		for (DbSchema dbSchema : dbSchemas) {
			DbSchemaVo dbSchemaVo = new DbSchemaVo();
			dbSchemaVo.setFullName(dbSchema.getFullName());
			dbSchemaVo.setCatalog(dbSchema.getCatalog());
			dbSchemaVo.setSchema(dbSchema.getSchema());
			dbSchemaVos.add(dbSchemaVo);
		}
		return dbSchemaVos;
	}

}