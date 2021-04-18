package org.anttribe.dbviewer.web.vo.authc;

/**
 * @author zhaoyong
 * @date 2021年1月12日
 */
public class LoginParam {

	/**
	 * 登录用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

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