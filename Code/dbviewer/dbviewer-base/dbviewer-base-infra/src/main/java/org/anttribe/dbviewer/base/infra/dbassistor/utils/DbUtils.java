package org.anttribe.dbviewer.base.infra.dbassistor.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * @author zhaoyong
 * @date 2020-12-29
 */
public final class DbUtils {

	/**
	 * 获取数据库链接
	 * 
	 * @param dataSource
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(DataSource dataSource) throws SQLException {
		Connection conn = null;
		if (null != dataSource) {
			if (dataSource instanceof org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource) {
				org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource uDataSource = (org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource) dataSource;
				Properties dbProps = new Properties();
				dbProps.setProperty("user", uDataSource.getUsername());
				dbProps.setProperty("password", uDataSource.getPassword());
				// 设置可以获取备注信息
				dbProps.setProperty("remarks", "true");
				// 解决MySQL可以获取备注信息
				dbProps.setProperty("useInformationSchema", "true");
				conn = DriverManager.getConnection(uDataSource.getUrl(), dbProps);
			} else {
				conn = dataSource.getConnection();
			}
		}
		return conn;
	}

	/**
	 * 关闭数据库链接
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	public static void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * 关闭ResultSet资源
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public static void closeQuietly(ResultSet rs) throws SQLException {
		if (null != rs) {
			rs.close();
		}
	}

}