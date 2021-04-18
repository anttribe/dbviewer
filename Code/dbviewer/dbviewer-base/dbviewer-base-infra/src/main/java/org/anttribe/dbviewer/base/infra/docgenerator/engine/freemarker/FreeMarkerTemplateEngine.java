package org.anttribe.dbviewer.base.infra.docgenerator.engine.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.anttribe.dbviewer.base.infra.dbdocgenerator.exception.DbDocumentGeneratorException;
import org.anttribe.dbviewer.base.infra.docgenerator.config.Configuration;
import org.anttribe.dbviewer.base.infra.docgenerator.config.TemplateConfiguration;
import org.anttribe.dbviewer.base.infra.docgenerator.engine.AbstractTemplateEngine;
import org.anttribe.dbviewer.base.infra.docgenerator.engine.exception.TemplateEngineException;
import org.anttribe.dbviewer.base.infra.docgenerator.in.DataModel;
import org.anttribe.dbviewer.base.infra.docgenerator.out.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author zhaoyong
 * @date 2021年1月13日
 */
public class FreeMarkerTemplateEngine extends AbstractTemplateEngine {

	private static final Logger LOGGER = LoggerFactory.getLogger(FreeMarkerTemplateEngine.class);

	/**
	 * 默认构造器
	 * 
	 * @param configuration
	 */
	public FreeMarkerTemplateEngine(Configuration configuration) {
		super(configuration);
	}

	@Override
	public Output process(DataModel dataModel) {
		// 加载模板
		Template template = new TemplateLoader(this.configuration.getTemplateConfig()).loadTemplate();
		if (null == template) {
			LOGGER.warn("Unavailable template file");
			throw new TemplateEngineException("Unavailable template file");
		}

		LOGGER.debug("start to generate document with dataModel[{}] and template[{}]", dataModel, template);
		// 生成文档
		Output output = this.createOutput();
		if (null != output && null != output.getOutputFile()) {
			try {
				// 模版 + 数据
				Writer out = new FileWriter(output.getOutputFile());
				template.process(dataModel.getModelMap(), out);

				LOGGER.debug("end generating document with dataModel[] and template[]", dataModel);
			} catch (TemplateException | IOException e) {
				throw new TemplateEngineException("process datamodel with template get error", e);
			}
		}
		return output;
	}

	/**
	 * 模板加载
	 * 
	 * @author zhaoyong
	 * @date 2021年2月17日
	 */
	class TemplateLoader {

		/**
		 * 默认编码
		 */
		private static final String DEFAULT_ENCODING = "UTF-8";

		private TemplateConfiguration templateConfiguration;

		public TemplateLoader(TemplateConfiguration templateConfiguration) {
			this.templateConfiguration = templateConfiguration;
		}

		/**
		 * 加载模板
		 * 
		 * @return
		 */
		public Template loadTemplate() {
			File templateFile = null != templateConfiguration ? templateConfiguration.getTemplateFile() : null;
			if (null == templateFile) {
				LOGGER.error("template file must not be null");
				throw new DbDocumentGeneratorException("template file must not be null");
			}
			if (!templateFile.exists()) {
				LOGGER.error("template file does not exist");
				throw new DbDocumentGeneratorException("template file does not exist");
			}
			if (!templateFile.isFile()) {
				LOGGER.error("template file parameter is invalid");
				throw new DbDocumentGeneratorException("template file parameter invalid");
			}

			Template template = null;
			// FreeMarker模板配置
			freemarker.template.Configuration freeMarkerConfiguration = new freemarker.template.Configuration(
					freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			freeMarkerConfiguration.setDefaultEncoding(DEFAULT_ENCODING);
			try {
				// 加载模板目录
				freeMarkerConfiguration.setDirectoryForTemplateLoading(templateFile.getParentFile());
				template = freeMarkerConfiguration.getTemplate(templateFile.getName());

				return template;
			} catch (IOException e) {
				LOGGER.error("failed to load template file, cause: {}", e);
				throw new DbDocumentGeneratorException("failed to load template file", e);
			}
		}

	}

}