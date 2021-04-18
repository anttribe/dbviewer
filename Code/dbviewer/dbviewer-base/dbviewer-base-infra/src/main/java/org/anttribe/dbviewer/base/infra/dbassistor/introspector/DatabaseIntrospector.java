package org.anttribe.dbviewer.base.infra.dbassistor.introspector;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.anttribe.dbviewer.base.infra.dbassistor.DbAssistor;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbColumn;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbObjectType;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbSchema;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbTable;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.JdbcType;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.PrimaryKey;
import org.anttribe.dbviewer.base.infra.dbassistor.utils.DbUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhaoyong
 * @date 2020-12-13
 */
public abstract class DatabaseIntrospector implements Refreshable {

	/**
	 * 表名通配符
	 */
	private static final String DEFAULT_TABLENAME_WILDCARD = "%";

	/**
	 * 当前数据库信息
	 */
	protected DbSchema schema;

	protected DbAssistor dbAssistor;

	protected DatabaseIntrospector(DbSchema schema, DbAssistor dbAssistor) {
		this.schema = schema;
		this.dbAssistor = dbAssistor;
	}

	@Override
	public void refresh() {
		this.introspectTables();
	}

	/**
	 * 获取所有的数据库表
	 * 
	 * @return List<DbTable>
	 */
	public List<DbTable> introspectTables() {
		if (null == schema) {
			return null;
		}

		if (CollectionUtils.isEmpty(schema.getTables())) {
			List<DbTable> dbTables = this.introspectTablesInner(DEFAULT_TABLENAME_WILDCARD, DbObjectType.TABLE);
			schema.setTables(dbTables);
		}
		return schema.getTables();
	}

	/**
	 * 获取指定表明的表
	 * 
	 * @param tableNamePattern
	 * @return
	 */
	public List<DbTable> introspectTables(String tableNamePattern) {
		if (null == schema || StringUtils.isEmpty(tableNamePattern)) {
			return null;
		}

		if (CollectionUtils.isEmpty(schema.getTables())) {
			List<DbTable> dbTables = this.introspectTablesInner(DEFAULT_TABLENAME_WILDCARD, DbObjectType.TABLE);
			schema.setTables(dbTables);
		}

		// 过滤表
		Pattern pattern = Pattern.compile(tableNamePattern, Pattern.CASE_INSENSITIVE);
		List<DbTable> rDbtables = new ArrayList<DbTable>();
		for (DbTable dbTable : schema.getTables()) {
			Matcher m = pattern.matcher(dbTable.getTableName());
			if (null != m && m.matches()) {
				rDbtables.add(dbTable);
			}
		}
		return rDbtables;
	}

	public DbTable introspectTable(String tableName) {
		if (null == schema || StringUtils.isEmpty(tableName)) {
			return null;
		}

		if (CollectionUtils.isEmpty(schema.getTables())) {
			List<DbTable> dbTables = this.introspectTablesInner(tableName, DbObjectType.TABLE);
			if (CollectionUtils.isEmpty(dbTables)) {
				return null;
			}
			return dbTables.get(0);
		}

		DbTable rDbTable = null;
		// 从所有表中过滤表
		for (DbTable dbTable : schema.getTables()) {
			if (tableName.contentEquals(dbTable.getTableName())) {
				rDbTable = dbTable;
				break;
			}
		}
		return rDbTable;
	}

	/**
	 * “内省”表
	 */
	private List<DbTable> introspectTablesInner(String tableNamePattern, DbObjectType dbObjectType) {
		List<DbTable> dbTables = new ArrayList<DbTable>();
		Connection conn = dbAssistor.getConnection();
		if (null != conn) {
			try {
				DatabaseMetaData databaseMetaData = conn.getMetaData();
				ResultSet rs = databaseMetaData.getTables(schema.getCatalog(), schema.getSchema(), tableNamePattern,
						new String[] { dbObjectType.name() });
				while (rs.next()) {
					DbTable dbTable = new DbTable();
					dbTable.setTableName(rs.getString("TABLE_NAME"));
					dbTable.setComment(rs.getString("REMARKS"));
					dbTable.setType(dbObjectType);

					// 数据列
					ResultSet columnsRs = databaseMetaData.getColumns(schema.getCatalog(), schema.getSchema(),
							dbTable.getTableName(), null);
					List<DbColumn> dbColumns = new ArrayList<DbColumn>();
					while (columnsRs.next()) {
						DbColumn dbColumn = new DbColumn();
						dbColumn.setColumnName(columnsRs.getString("COLUMN_NAME"));
						dbColumn.setComment(columnsRs.getString("REMARKS"));
						// 数据类型
						dbColumn.setJdbcType(JdbcType.valueOf(columnsRs.getInt("DATA_TYPE")));
						dbColumn.setNullable(columnsRs.getInt("NULLABLE") == ResultSetMetaData.columnNullable);
						dbColumn.setSize(columnsRs.getInt("COLUMN_SIZE"));
						dbColumn.setDigits(columnsRs.getInt("DECIMAL_DIGITS"));
						dbColumn.setDefaultVal(columnsRs.getString("COLUMN_DEF"));
						dbColumns.add(dbColumn);
					}
					dbTable.setColumns(dbColumns);
					DbUtils.closeQuietly(columnsRs);

					// 主键
					ResultSet primaryKeyRs = databaseMetaData.getPrimaryKeys(schema.getCatalog(), schema.getSchema(),
							dbTable.getTableName());
					PrimaryKey primaryKey = new PrimaryKey();
					while (primaryKeyRs.next()) {
						primaryKey.setName(primaryKeyRs.getString("PK_NAME"));
						primaryKey.addColumn(primaryKeyRs.getString("COLUMN_NAME"), primaryKeyRs.getShort("KEY_SEQ"));
					}
					dbTable.setPrimaryKey(primaryKey);
					DbUtils.closeQuietly(primaryKeyRs);

					dbTables.add(dbTable);
				}
				DbUtils.closeQuietly(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbAssistor.closeConnection(conn);
			}
		}

		return dbTables;
	}

	public DbSchema getSchema() {
		return schema;
	}

}