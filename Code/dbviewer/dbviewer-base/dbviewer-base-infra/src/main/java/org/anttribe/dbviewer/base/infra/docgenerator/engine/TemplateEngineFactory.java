package org.anttribe.dbviewer.base.infra.docgenerator.engine;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.anttribe.dbviewer.base.infra.docgenerator.config.Configuration;
import org.anttribe.dbviewer.base.infra.docgenerator.config.TemplateConfiguration;
import org.anttribe.dbviewer.base.infra.docgenerator.exception.DocumentGeneratorException;
import org.springframework.util.Assert;

/**
 * 模板引擎工厂方法
 * 
 * @author zhaoyong
 * @date 2021年1月13日
 */
public class TemplateEngineFactory {

	private TemplateEngineFactory() {
	}

	/**
	 * 静态单例实现
	 * 
	 * @return
	 */
	public static TemplateEngineFactory me() {
		return TemplateEngineFactoryInstance.INSTANCE;
	}

	/**
	 * 获取模板引擎
	 * 
	 * @param templateConfiguration
	 * @return
	 */
	public TemplateEngine getTemplateEngine(Configuration configuration) {
		// 从配置中获取到模板配置
		TemplateConfiguration templateConfiguration = configuration.getTemplateConfig();
		File templateFile = templateConfiguration.getTemplateFile();
		if (null == templateFile) {
			return null;
		}

		TemplateEngineType TemplateEngineType = this.getTemplateEngineType(templateFile);
		if (null == TemplateEngineType) {
			throw new DocumentGeneratorException("unsupported Template Type");
		}
		return this.getTemplateEngine(TemplateEngineType, configuration);
	}

	/**
	 * 获取模板引擎
	 * 
	 * @param templateEngineType
	 * @return
	 */
	private TemplateEngine getTemplateEngine(TemplateEngineType templateEngineType, Configuration configuration) {
		Assert.notNull(templateEngineType, "TemplateEngineType must not be null");
		Assert.notNull(templateEngineType.getEngineClass(), "TemplateEngineType engineClass must not be null");

		Class<? extends TemplateEngine> engineClass = templateEngineType.getEngineClass();
		try {
			// 构造带Configuration参数的TemplateEngine
			Constructor<? extends TemplateEngine> costructor = engineClass.getConstructor(Configuration.class);
			return costructor.newInstance(configuration);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException
				| InvocationTargetException e) {
			throw new DocumentGeneratorException(e);
		}
	}

	/**
	 * 根据模板文件获取模板引擎类型
	 * 
	 * @param templateFile
	 * @return TemplateEngineType
	 */
	private TemplateEngineType getTemplateEngineType(File templateFile) {
		// 根据模板文件后缀获取文件类型
		String templateFileName = templateFile.getName();
		int index = templateFileName.lastIndexOf('.');
		if (index <= 0 || index > templateFileName.length() - 1) {
			throw new DocumentGeneratorException("unsupported this template file type");
		}

		String templateFileSuffix = templateFileName.substring(index, templateFileName.length());
		return TemplateEngineType.valueOfTemplateFileSuffix(templateFileSuffix);
	}

	private static class TemplateEngineFactoryInstance {

		public static final TemplateEngineFactory INSTANCE = new TemplateEngineFactory();

	}

}