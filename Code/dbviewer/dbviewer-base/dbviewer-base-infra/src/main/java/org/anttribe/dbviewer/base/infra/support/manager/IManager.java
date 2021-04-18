package org.anttribe.dbviewer.base.infra.support.manager;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyong
 * @date 2020-11-29
 */
public interface IManager<T> {

	/**
	 * 列表查询数据
	 * 
	 * @return
	 */
	List<T> list(T criteria);

	/**
	 * 根据id获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	T get(Serializable id);

	/**
	 * 持久化数据
	 * 
	 * @param model
	 * @return boolean操作是否成功
	 */
	boolean persist(T model);

	/**
	 * 删除数据
	 * 
	 * @param ids
	 * @return boolean操作是否成功
	 */
	boolean remove(Serializable... ids);

}