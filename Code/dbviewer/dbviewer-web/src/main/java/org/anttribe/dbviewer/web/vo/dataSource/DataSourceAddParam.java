package org.anttribe.dbviewer.web.vo.dataSource;

import org.anttribe.dbviewer.web.vo.Vo;

/**
 * @author zhaoyong
 * @date 2020-12-08
 */
public class DataSourceAddParam extends Vo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4479042525622514075L;

	/**
	 * 数据库连接别名
	 */
	private String alias;

	/**
	 * 数据库方言
	 */
	private String dialect;

	/**
	 * 数据库链接地址
	 */
	private String connUrl;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public String getConnUrl() {
		return connUrl;
	}

	public void setConnUrl(String connUrl) {
		this.connUrl = connUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}