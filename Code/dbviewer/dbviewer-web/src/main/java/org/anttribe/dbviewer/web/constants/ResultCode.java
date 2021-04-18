package org.anttribe.dbviewer.web.constants;

/**
 * 响应结果码
 * 
 * @author zhaoyong
 * @date 2020-12-12
 */
public class ResultCode {

	/**
	 * 成功响应
	 */
	public static final String RESULT_SUCCESS = "000000";

	/**
	 * 用户鉴权模块的结果码
	 * 
	 * @author zhaoyong
	 * @date 2021年1月12日
	 */
	public class Authc {
		
		/**
		 * 未知用户
		 */
		public static final String RESULT_UNKNOWN_ACCOUNT = "010001";
		
		/**
		 * 错误密码
		 */
		public static final String RESULT_INCORRECT_CREDENTIALS = "010002";
		
		/**
		 * 帐号禁用
		 */
		public static final String RESULT_DISABLED_ACCOUNT = "010003";
		
	}

}