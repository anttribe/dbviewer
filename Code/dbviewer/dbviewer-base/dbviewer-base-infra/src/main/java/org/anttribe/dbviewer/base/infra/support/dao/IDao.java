package org.anttribe.dbviewer.base.infra.support.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoyong
 * @date 2020-11-28
 */
public interface IDao<T> {

	/**
	 * 新增数据
	 * 
	 * @param model
	 * @return int 执行数据条数
	 */
	int insert(T model);

	/**
	 * 批量新增
	 * 
	 * @param models
	 * @return int 执行数据条数
	 */
	int batchInsert(List<T> models);

	/**
	 * 修改数据
	 * 
	 * @param model
	 * @return int 执行数据条数
	 */
	int update(T model);

	/**
	 * 批量更新数据
	 * 
	 * @param models
	 * @return
	 */
	int batchUpdate(List<T> models);

	/**
	 * 删除指定id数据
	 * 
	 * @param id
	 * @return
	 */
	int remove(Serializable id);

	/**
	 * 删除指定id数据
	 * 
	 * @param ids
	 * @return
	 */
	int batchRemove(Serializable... ids);

	/**
	 * 查询列表数据
	 * 
	 * @param criteria
	 * @return
	 */
	List<T> list(T criteria);

	/**
	 * 条件查询列表数据
	 * 
	 * @param criteria
	 * @return
	 */
	List<T> list(Map<String, Object> criteria);

	/**
	 * 条件查询
	 * 
	 * @param criteria
	 * @return T
	 */
	T find(T criteria);

	/**
	 * 条件查询
	 * 
	 * @param criteria
	 * @return T
	 */
	T find(Map<String, Object> criteria);

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return T
	 */
	T get(Serializable id);

}