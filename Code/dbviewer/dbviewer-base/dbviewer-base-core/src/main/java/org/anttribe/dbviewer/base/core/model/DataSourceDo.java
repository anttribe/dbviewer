package org.anttribe.dbviewer.base.core.model;

import org.anttribe.dbviewer.base.infra.dbassistor.Dialect;
import org.anttribe.dbviewer.base.infra.support.model.Model;

/**
 * 数据源, 抽象“数据库”对象模型
 * 
 * @author zhaoyong
 * @date 2020-11-20
 */
public class DataSourceDo extends Model {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5584285985673942786L;

	/**
	 * 数据库别名
	 */
	private String alias;

	/**
	 * 数据库方言
	 */
	private Dialect dialect;

	/**
	 * 数据库链接url
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

	public Dialect getDialect() {
		return dialect;
	}

	public void setDialect(Dialect dialect) {
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