package org.anttribe.dbviewer.base.infra.dbassistor;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource;
import org.anttribe.dbviewer.base.infra.dbassistor.introspector.DatabaseIntrospector;
import org.anttribe.dbviewer.base.infra.dbassistor.introspector.MysqlDatabaseIntrospector;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.Database;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbSchema;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbTable;
import org.anttribe.dbviewer.base.infra.dbassistor.utils.DbUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * 数据库操作“辅助”
 * 
 * @author zhaoyong
 * @date 2020-12-21
 */
public class DbAssistor {

	private final Logger logger = LoggerFactory.getLogger(DbAssistor.class);

	/**
	 * 数据源
	 */
	private DataSource dataSource;

	/**
	 * 数据库信息
	 */
	private Database database;

	/**
	 * 是否懒加载
	 */
	private boolean lazyLoad;

	/**
	 * 数据库“内省”器集合，对应于每一个Schema
	 */
	private Map<String, DatabaseIntrospector> databaseIntrospectors;

	/**
	 * 针对当前连接的“内省”器
	 */
	private DatabaseIntrospector currentDatabaseIntrospector;

	public DbAssistor(DataSource dataSource, boolean lazyLoad) {
		this.dataSource = dataSource;
		this.lazyLoad = lazyLoad;

		this.initAssistor();
	}

	/**
	 * 初始化动作
	 */
	private void initAssistor() {
		Connection conn = null;
		try {
			conn = this.getConnection();
			if (null != conn) {
				DatabaseMetaData databaseMetadata = conn.getMetaData();
				database = this.buildDatabase(databaseMetadata);
				if (null != database && !CollectionUtils.isEmpty(database.getSchemas())) {
					databaseIntrospectors = new HashMap<String, DatabaseIntrospector>();
					// 针对每一个Schema创建Introspector对象
					for (DbSchema schema : database.getSchemas()) {
						DatabaseIntrospector databaseIntrospector = null;
						if (Dialect.MYSQL.equals(dataSource.getDialect())) {
							databaseIntrospector = new MysqlDatabaseIntrospector(schema, this);
						}
						if (null != databaseIntrospector) {
							databaseIntrospectors.put(schema.getFullName(), databaseIntrospector);
						}
					}
					// 当前连接的数据库Schema
					DbSchema currentSchema = new DbSchema();
					currentSchema.setCatalog(conn.getCatalog());
					currentSchema.setSchema(conn.getSchema());
					currentDatabaseIntrospector = databaseIntrospectors.get(currentSchema.getFullName());
					if (null == currentDatabaseIntrospector) {
						// TODO: warn日志记录
					} else if (!lazyLoad) {
						currentDatabaseIntrospector.refresh();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 构造数据库对象
	 * 
	 * @param databaseMetadata
	 * @return Database
	 * @throws SQLException
	 */
	private Database buildDatabase(DatabaseMetaData databaseMetadata) throws SQLException {
		if (null == databaseMetadata) {
			return null;
		}
		Database database = new Database();
		// 构建数据库产品信息
		database.setProductName(databaseMetadata.getDatabaseProductName());
		database.setProductName(databaseMetadata.getDatabaseProductVersion());

		database.setSchemas(this.buildDbSchemas(databaseMetadata));

		return database;
	}

	/**
	 * 构造所有数据库对象
	 * 
	 * @param databaseMetadata
	 * @return
	 */
	private List<DbSchema> buildDbSchemas(DatabaseMetaData databaseMetadata) {
		List<DbSchema> schemas = new ArrayList<DbSchema>();
		try {
			// 设置 Schemas
			List<String> catalogs = new ArrayList<String>();
			ResultSet rs = databaseMetadata.getCatalogs();
			while (rs.next()) {
				catalogs.add(rs.getString("TABLE_CAT"));
			}
			DbUtils.closeQuietly(rs);

			if (CollectionUtils.isEmpty(catalogs)) {
				ResultSet schemasRs = databaseMetadata.getSchemas();
				while (schemasRs.next()) {
					DbSchema schema = new DbSchema();
					schema.setCatalog(schemasRs.getString("TABLE_CATALOG"));
					schema.setSchema(schemasRs.getString("TABLE_SCHEM"));
					schemas.add(schema);
				}
				DbUtils.closeQuietly(schemasRs);
			} else {
				for (String catalog : catalogs) {
					List<DbSchema> tempSchemas = new ArrayList<DbSchema>();
					ResultSet schemasRs = databaseMetadata.getSchemas(catalog, null);
					while (schemasRs.next()) {
						DbSchema schema = new DbSchema();
						schema.setCatalog(schemasRs.getString("TABLE_CATALOG"));
						schema.setSchema(schemasRs.getString("TABLE_SCHEM"));
						tempSchemas.add(schema);
					}
					DbUtils.closeQuietly(schemasRs);

					if (CollectionUtils.isEmpty(tempSchemas)) {
						DbSchema schema = new DbSchema();
						schema.setCatalog(catalog);
						schemas.add(schema);
					} else {
						schemas.addAll(tempSchemas);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return schemas;
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DbUtils.getConnection(this.dataSource);
		} catch (SQLException e) {
			logger.warn("failed to get connection with dataSource: {}", this.dataSource);
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 */
	public void closeConnection(Connection conn) {
		try {
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 列表当前数据源对应的数据库列表
	 * 
	 * @return
	 */
	public List<DbSchema> listSchemas() {
		return database.getSchemas();
	}

	/**
	 * 获取当前数据库Schema
	 * 
	 * @return
	 */
	public DbSchema getSchema() {
		if (null == currentDatabaseIntrospector) {
			return null;
		}
		return currentDatabaseIntrospector.getSchema();
	}

	/**
	 * 列表获取当前数据库中所有的表
	 * 
	 * @return List<DbTable>
	 */
	public List<DbTable> listTables() {
		if (null == currentDatabaseIntrospector) {
			return null;
		}
		return currentDatabaseIntrospector.introspectTables();
	}

	/**
	 * 列表获取当前数据库中指定表名的表
	 * 
	 * @param tableNamePattern
	 * @return List<DbTable>
	 */
	public List<DbTable> listTables(String tableNamePattern) {
		if (null == currentDatabaseIntrospector) {
			return null;
		}
		return currentDatabaseIntrospector.introspectTables(tableNamePattern);
	}

	/**
	 * 根据表名获取指定表
	 * 
	 * @param tableName
	 * @return DbTable
	 */
	public DbTable getTable(String tableName) {
		if (null == currentDatabaseIntrospector) {
			return null;
		}
		return currentDatabaseIntrospector.introspectTable(tableName);
	}

	/**
	 * 列表获取指定schema下的所有表
	 * 
	 * @param schema
	 * @return
	 */
	public List<DbTable> listSchemaTables(String schema) {
		if (StringUtils.isEmpty(schema)) {
			return this.listTables();
		}

		DatabaseIntrospector databaseIntrospector = this.databaseIntrospectors.get(schema);
		if (null == databaseIntrospector) {
			return null;
		}
		return databaseIntrospector.introspectTables();
	}

	/**
	 * 列表获取指定schema下的所有表
	 * 
	 * @param schema
	 * @return
	 */
	public List<DbTable> listSchemaTables(String schema, String tableNamePattern) {
		if (StringUtils.isEmpty(schema)) {
			return this.listTables(tableNamePattern);
		}

		DatabaseIntrospector databaseIntrospector = this.databaseIntrospectors.get(schema);
		if (null == databaseIntrospector) {
			return null;
		}
		return databaseIntrospector.introspectTables(tableNamePattern);
	}

	/**
	 * 列表获取指定schema下的所有表
	 * 
	 * @param schema
	 * @return
	 */
	public DbTable getSchemaTable(String schema, String tableName) {
		if (StringUtils.isEmpty(schema)) {
			return this.getTable(tableName);
		}

		DatabaseIntrospector databaseIntrospector = this.databaseIntrospectors.get(schema);
		if (null == databaseIntrospector) {
			return null;
		}
		return databaseIntrospector.introspectTable(tableName);
	}

}