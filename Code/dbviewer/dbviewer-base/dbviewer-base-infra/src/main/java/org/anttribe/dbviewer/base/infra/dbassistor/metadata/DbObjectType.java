package org.anttribe.dbviewer.base.infra.dbassistor.metadata;

/**
 * 数据库表类型
 * 
 * @author zhaoyong
 * @version 2015年12月27日
 */
public enum DbObjectType {
	/** 表 */
	TABLE,
	/** 视图 */
	VIEW,
	/** 系统表 */
	SYSTEM_TABLE,
	/** 全局临时表 */
	GLOBAL_TEMPORARY,
	/** 局部临时表 */
	LOCAL_TEMPORARY,
	/** 别名 */
	ALIAS,
	/** 同义词 */
	SYNONYM;
}