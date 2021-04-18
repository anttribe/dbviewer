package org.anttribe.dbviewer.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.anttribe.dbviewer.base.core.model.DataSourceDo;
import org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbColumn;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbTable;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.PrimaryKey;
import org.anttribe.dbviewer.base.runtime.manager.IDataSourceManager;
import org.anttribe.dbviewer.base.runtime.manager.IDbTableManager;
import org.anttribe.dbviewer.web.service.IDbTableService;
import org.anttribe.dbviewer.web.vo.dbTable.DbColumnVo;
import org.anttribe.dbviewer.web.vo.dbTable.DbTableGetParam;
import org.anttribe.dbviewer.web.vo.dbTable.DbTableListParam;
import org.anttribe.dbviewer.web.vo.dbTable.DbTableVo;
import org.anttribe.dbviewer.web.vo.dbTable.PrimaryKeyVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyong
 * @date 2020年12月18日
 */
@Service
public class DbTableServiceImpl implements IDbTableService {

	@Autowired
	private IDataSourceManager dataSourceManager;

	@Autowired
	private IDbTableManager dbTableManager;

	@Override
	public List<DbTableVo> list(DbTableListParam dbTableListParam) {
		if (null == dbTableListParam || StringUtils.isEmpty(dbTableListParam.getDataSourceId())
				|| StringUtils.isEmpty(dbTableListParam.getDbSchema())) {
			return null;
		}

		DataSourceDo dataSourceDo = dataSourceManager.get(dbTableListParam.getDataSourceId());
		if (null == dataSourceDo) {
			return null;
		}

		DataSource dataSource = new DataSource();
		dataSource.setDialect(dataSourceDo.getDialect());
		dataSource.setUrl(dataSourceDo.getConnUrl());
		dataSource.setUsername(dataSourceDo.getUsername());
		dataSource.setPassword(dataSourceDo.getPassword());
		List<DbTable> dbTables = dbTableManager.list(dataSource, dbTableListParam.getDbSchema(), new DbTable());
		if (CollectionUtils.isEmpty(dbTables)) {
			return null;
		}

		List<DbTableVo> dbTableVos = new ArrayList<DbTableVo>();
		for (DbTable dbTable : dbTables) {
			DbTableVo dbTableVo = new DbTableVo();
			dbTableVo.setTableName(dbTable.getTableName());
			dbTableVo.setComment(dbTable.getComment());
			dbTableVos.add(dbTableVo);
		}
		return dbTableVos;
	}

	@Override
	public DbTableVo get(String dbTableName, DbTableGetParam dbTableGetParam) {
		if (StringUtils.isEmpty(dbTableName)
				|| (null == dbTableGetParam || StringUtils.isEmpty(dbTableGetParam.getDataSourceId())
						|| StringUtils.isEmpty(dbTableGetParam.getDbSchema()))) {
			return null;
		}

		DataSourceDo dataSourceDo = dataSourceManager.get(dbTableGetParam.getDataSourceId());
		if (null == dataSourceDo) {
			return null;
		}

		DataSource dataSource = new DataSource();
		dataSource.setDialect(dataSourceDo.getDialect());
		dataSource.setUrl(dataSourceDo.getConnUrl());
		dataSource.setUsername(dataSourceDo.getUsername());
		dataSource.setPassword(dataSourceDo.getPassword());
		DbTable dbTable = dbTableManager.get(dataSource, dbTableGetParam.getDbSchema(), dbTableName);
		if (null == dbTable) {
			return null;
		}

		DbTableVo dbTableVo = new DbTableVo();
		dbTableVo.setTableName(dbTable.getTableName());
		dbTableVo.setComment(dbTable.getComment());
		// 列
		if (!CollectionUtils.isEmpty(dbTable.getColumns())) {
			List<DbColumnVo> dbColumnVos = new ArrayList<DbColumnVo>();
			for (DbColumn dbColumn : dbTable.getColumns()) {
				DbColumnVo dbColumnVo = new DbColumnVo();
				dbColumnVo.setColumnName(dbColumn.getColumnName());
				dbColumnVo.setComment(dbColumn.getComment());
				if (null != dbColumn.getJdbcType()) {
					dbColumnVo.setJdbcType(dbColumn.getJdbcType().name());
				}
				dbColumnVo.setSize(dbColumn.getSize());
				dbColumnVo.setDefaultVal(dbColumn.getDefaultVal());
				dbColumnVo.setNullable(dbColumn.isNullable());
				dbColumnVos.add(dbColumnVo);
			}
			dbTableVo.setColumns(dbColumnVos);
		}
		// 主键
		PrimaryKey primaryKey = dbTable.getPrimaryKey();
		if (null != primaryKey && !CollectionUtils.isEmpty(primaryKey.getColumns())) {
			PrimaryKeyVo primaryKeyVo = new PrimaryKeyVo();
			primaryKeyVo.setName(primaryKey.getName());
			primaryKeyVo.setColumns(primaryKey.getColumns());
			
			dbTableVo.setPrimaryKey(primaryKeyVo);
		}

		return dbTableVo;
	}

}