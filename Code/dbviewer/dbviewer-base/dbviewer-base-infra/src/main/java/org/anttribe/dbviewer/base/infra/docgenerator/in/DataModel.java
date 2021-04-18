package org.anttribe.dbviewer.base.infra.docgenerator.in;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

/**
 * @author zhaoyong
 * @date 2021年1月12日
 */
public class DataModel {

	/**
	 * 模型数据
	 */
	private Map<String, Object> modelMap;

	public DataModel() {
		modelMap = new HashMap<String, Object>();
	}

	/**
	 * @param key
	 * @param value
	 */
	public void addObject(String key, Object value) {
		Assert.notNull(key, "ModelMap key must not be null");

		getModelMap().put(key, value);
	}

	/**
	 * @param modelMap
	 */
	public void addAllObjects(Map<String, ?> modelMap) {
		Assert.notNull(modelMap, "ModelMap must not be null");

		getModelMap().putAll(modelMap);
	}

	public Map<String, Object> getModelMap() {
		return modelMap;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}