package org.anttribe.dbviewer.base.infra.docgenerator.engine;

import org.anttribe.dbviewer.base.infra.docgenerator.engine.freemarker.FreeMarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhaoyong
 * @date 2021年1月13日
 */
public enum TemplateEngineType {

	FreeMarker(".ftl", FreeMarkerTemplateEngine.class);

	/**
	 * 模板文件后缀
	 */
	private String templateFileSuffix;

	/**
	 * 引擎类
	 */
	private Class<? extends TemplateEngine> engineClass;

	private TemplateEngineType(String templateFileSuffix, Class<? extends TemplateEngine> engineClass) {
		this.templateFileSuffix = templateFileSuffix;
		this.engineClass = engineClass;
	}

	/**
	 * 根据模板文件后缀获取到支持模板引擎枚举
	 * 
	 * @param templateFileSuffix
	 * @return
	 */
	public static TemplateEngineType valueOfTemplateFileSuffix(String templateFileSuffix) {
		if (StringUtils.isEmpty(templateFileSuffix)) {
			return null;
		}

		TemplateEngineType templateEngineType = null;
		for (TemplateEngineType enumVal : TemplateEngineType.values()) {
			if (templateFileSuffix.equals(enumVal.getTemplateFileSuffix())) {
				templateEngineType = enumVal;
				break;
			}
		}
		return templateEngineType;
	}

	public String getTemplateFileSuffix() {
		return templateFileSuffix;
	}

	public Class<? extends TemplateEngine> getEngineClass() {
		return engineClass;
	}

}