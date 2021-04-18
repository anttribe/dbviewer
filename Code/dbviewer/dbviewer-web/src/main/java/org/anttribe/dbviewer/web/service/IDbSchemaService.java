package org.anttribe.dbviewer.web.service;

import java.util.List;

import org.anttribe.dbviewer.web.vo.dbSchema.DbSchemaListParam;
import org.anttribe.dbviewer.web.vo.dbSchema.DbSchemaVo;

/**
 * @author zhaoyong
 * @date 2021-01-02
 */
public interface IDbSchemaService {

	/**
	 * 条件查询数据库中shcema列表
	 * 
	 * @param dbSchemaListParam
	 * @return
	 */
	List<DbSchemaVo> list(DbSchemaListParam dbSchemaListParam);

}