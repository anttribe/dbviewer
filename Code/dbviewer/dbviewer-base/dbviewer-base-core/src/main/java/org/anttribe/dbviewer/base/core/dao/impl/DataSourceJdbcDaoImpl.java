package org.anttribe.dbviewer.base.core.dao.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.anttribe.dbviewer.base.core.dao.IDataSourceDao;
import org.anttribe.dbviewer.base.core.model.DataSourceDo;
import org.anttribe.dbviewer.base.infra.dbassistor.Dialect;
import org.anttribe.dbviewer.base.infra.support.dao.annotations.JdbcDao;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

/**
 * @author zhaoyong
 * @date 2020-11-28
 */
@JdbcDao
@Primary
public class DataSourceJdbcDaoImpl implements IDataSourceDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insert(DataSourceDo model) {
		String sql = "INSERT INTO t_datasource_info(`id`, `alias`, `dialect`, `conn_url`, `username`, `password`) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		return this.jdbcTemplate.update(sql,
				new Object[] { model.getId(), model.getAlias(),
						null != model.getDialect() ? model.getDialect().name() : null, model.getConnUrl(),
						model.getUsername(), model.getPassword() });
	}

	@Override
	public int batchInsert(final List<DataSourceDo> models) {
		String sql = "INSERT INTO t_datasource_info(`id`, `alias`, `dialect`, `conn_url`, `username`, `password`) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		int[] r = this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				DataSourceDo ds = models.get(i);
				if (null != ds) {
					ps.setString(1, ds.getId());
					ps.setString(2, ds.getAlias());
					ps.setString(3, null != ds.getDialect() ? ds.getDialect().name() : null);
					ps.setString(4, ds.getConnUrl());
					ps.setString(5, ds.getUsername());
					ps.setString(6, ds.getPassword());
				}
			}

			@Override
			public int getBatchSize() {
				return models.size();
			}
		});
		return this.retResult(r);
	}

	@Override
	public int update(DataSourceDo model) {
		String sql = "UPDATE t_datasource_info "
				+ "SET `alias` = ?, `dialect` = ?, `conn_url` = ?, `username` = ?, `password` = ? " + "WHERE `id` = ?";
		return this.jdbcTemplate.update(sql,
				new Object[] { model.getAlias(), null != model.getDialect() ? model.getDialect().name() : null,
						model.getConnUrl(), model.getUsername(), model.getPassword(), model.getId() });
	}

	@Override
	public int batchUpdate(final List<DataSourceDo> models) {
		String sql = "UPDATE t_datasource_info "
				+ "SET `alias` = ?,  `dialect` = ?, `conn_url` = ?, `username` = ?, `password` = ? " + "WHERE `id` = ?";
		int[] r = this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				DataSourceDo ds = models.get(i);
				if (null != ds) {
					ps.setString(1, ds.getAlias());
					ps.setString(2, null != ds.getDialect() ? ds.getDialect().name() : null);
					ps.setString(3, ds.getConnUrl());
					ps.setString(4, ds.getUsername());
					ps.setString(5, ds.getPassword());
					ps.setString(6, ds.getId());
				}
			}

			@Override
			public int getBatchSize() {
				return models.size();
			}
		});
		return this.retResult(r);
	}

	@Override
	public int remove(Serializable id) {
		String sql = "DELETE FROM t_datasource_info WHERE `id` = ?";
		return this.jdbcTemplate.update(sql, id);
	}

	@Override
	public int batchRemove(final Serializable... ids) {
		String sql = "DELETE FROM t_datasource_info WHERE `id` = ?";
		int[] r = this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Serializable id = ids[i];
				ps.setString(1, id.toString());
			}

			@Override
			public int getBatchSize() {
				return ids.length;
			}
		});
		return this.retResult(r);
	}

	@Override
	public List<DataSourceDo> list(DataSourceDo criteria) {
		String sql = "SELECT `id`, `alias`, `dialect`, `conn_url`, `username`, `password` FROM t_datasource_info";

		// 构造查询条件
		List<String> criteriaStrs = new ArrayList<String>();
		List<Object> args = new ArrayList<Object>();
		if (!StringUtils.isEmpty(criteria.getAlias())) {
			criteriaStrs.add(" alias like ? ");
			args.add('%' + criteria.getAlias() + '%');
		}
		if (null != criteria.getDialect()) {
			criteriaStrs.add(" dialect = ? ");
			args.add(criteria.getDialect().name());
		}

		// 拼接查询条件语句
		if (!CollectionUtils.isEmpty(criteriaStrs)) {
			StringBuffer criteriaStrB = new StringBuffer();
			criteriaStrB.append(" WHERE ");
			int i = 0;
			for (String criteriaStr : criteriaStrs) {
				if (i != 0) {
					criteriaStrB.append(" AND ");
				}
				criteriaStrB.append(criteriaStr);
				i++;
			}
			sql += criteriaStrB.toString();
		}

		List<DataSourceDo> dataSources = this.jdbcTemplate.query(sql, args.toArray(), new DataSourceRowMapper());
		return dataSources;
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
		String sql = "SELECT id, alias, dialect, conn_url, username, password FROM t_datasource_info where id = ?";
		List<DataSourceDo> dataSources = this.jdbcTemplate.query(sql, new Object[] { id }, new DataSourceRowMapper());
		if (!CollectionUtils.isEmpty(dataSources)) {
			return dataSources.get(0);
		}
		return null;
	}

	private int retResult(int[] r) {
		if (ArrayUtils.isEmpty(r)) {
			return 0;
		}

		int j = 0;
		for (int i = 0; i < r.length; i++) {
			if (0 == r[i]) {
				continue;
			}
			j += r[i];
		}
		return j;
	}

	class DataSourceRowMapper implements RowMapper<DataSourceDo> {

		@Override
		public DataSourceDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DataSourceDo dataSource = new DataSourceDo();
			dataSource.setId(rs.getString("id"));
			dataSource.setAlias(rs.getString("alias"));

			String dialect = rs.getString("dialect");
			if (!StringUtils.isEmpty(dialect)) {
				dataSource.setDialect(Dialect.valueOf(dialect));
			}

			dataSource.setConnUrl(rs.getString("conn_url"));
			dataSource.setUsername(rs.getString("username"));
			dataSource.setPassword(rs.getString("password"));
			return dataSource;
		}

	}

}