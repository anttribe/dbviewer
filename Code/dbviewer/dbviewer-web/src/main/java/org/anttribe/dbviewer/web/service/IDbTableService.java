package org.anttribe.dbviewer.web.service;

import java.util.List;

import org.anttribe.dbviewer.web.vo.dbTable.DbTableGetParam;
import org.anttribe.dbviewer.web.vo.dbTable.DbTableListParam;
import org.anttribe.dbviewer.web.vo.dbTable.DbTableVo;

/**
 * @author zhaoyong
 * @date 2020年12月18日
 */
public interface IDbTableService {

	/**
	 * 条件查询数据库表列表
	 * 
	 * @param dbTableListParam
	 * @return
	 */
	List<DbTableVo> list(DbTableListParam dbTableListParam);

	/**
	 * 根据数据表名和参数获取数据表信息
	 * 
	 * @param tableName
	 * @param dbTableGetParam
	 * @return
	 */
	DbTableVo get(String tableName, DbTableGetParam dbTableGetParam);

}