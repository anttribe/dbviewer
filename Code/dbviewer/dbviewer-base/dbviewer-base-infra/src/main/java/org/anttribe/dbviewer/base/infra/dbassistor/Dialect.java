package org.anttribe.dbviewer.base.infra.dbassistor;

/**
 * 数据库方言
 * 
 * @author zhaoyong
 * @date 2020-11-28
 */
public enum Dialect {

	MYSQL("com.mysql.jdbc.Driver") {
		@Override
		public String populateDbConnectUrl(String host, String port, String database) {
			String connUrl = "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
			return String.format(connUrl, host, port, database);
		}
	};

	/**
	 * 数据库driver
	 */
	private String driverClass;

	/**
	 * 构造数据库连接url
	 * 
	 * @param host     主机名/ip
	 * @param port     端口
	 * @param database 数据库名
	 * @return url
	 */
	public abstract String populateDbConnectUrl(String host, String port, String database);

	private Dialect(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getDriverClass() {
		return driverClass;
	}

}