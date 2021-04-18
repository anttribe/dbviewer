package org.anttribe.dbviewer.base.infra.support.manager.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.anttribe.dbviewer.base.infra.support.dao.IDao;
import org.anttribe.dbviewer.base.infra.support.manager.IManager;
import org.anttribe.dbviewer.base.infra.support.model.Model;
import org.anttribe.dbviewer.base.infra.utils.UUIDUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhaoyong
 * @date 2020-11-29
 */
public abstract class AbstractManagerImpl<M extends Model, Dao extends IDao<M>> implements IManager<M> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected IDao<M> dao;

	@Override
	public List<M> list(M criteria) {
		logger.debug("listing datas with criteria: {}", criteria);

		return dao.list(criteria);
	}

	@Override
	public M get(Serializable id) {
		logger.debug("getting data with id: {}", id);
		return dao.get(id);
	}

	@Override
	public boolean persist(M model) {
		logger.info("persisting data with model: {}", model);

		if (null == model) {
			logger.warn("persisting data, param model is null.");
			return false;
		}

		// 默认id为主键, 判断id是否存在，判断时保存/更新数据的条件
		if (StringUtils.isEmpty(model.getId())) {
			// TODO: 生成id
			model.setId(UUIDUtils.getRandomUUID());
			int r = dao.insert(model);
			logger.info("model not exist, then save new model, model: {}", model);
			return this.retBool(r);
		}

		M persistentModel = dao.get(model.getId());
		if (null == persistentModel) {
			logger.info("model not exist, then save new model, model: {}", model);
			int r = dao.insert(model);
			return this.retBool(r);
		}

		int r = dao.update(model);
		logger.info("model exist, then update model info, model: {}", model);
		return this.retBool(r);
	}

	@Override
	public boolean remove(Serializable... ids) {
		logger.info("deleting data from db, param: {}", Arrays.toString(ids));

		if (ArrayUtils.isEmpty(ids)) {
			return false;
		}

		if (ids.length == 1) {
			return this.retBool(dao.remove(ids[0]));
		}
		return this.retBool(dao.batchRemove(ids));
	}

	protected boolean retBool(int result) {
		return result >= 1;
	}

}