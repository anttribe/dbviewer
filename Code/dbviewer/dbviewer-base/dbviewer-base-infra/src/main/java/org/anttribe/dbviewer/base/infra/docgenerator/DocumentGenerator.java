package org.anttribe.dbviewer.base.infra.docgenerator;

import org.anttribe.dbviewer.base.infra.docgenerator.config.Configuration;
import org.anttribe.dbviewer.base.infra.docgenerator.engine.TemplateEngine;
import org.anttribe.dbviewer.base.infra.docgenerator.engine.TemplateEngineFactory;
import org.anttribe.dbviewer.base.infra.docgenerator.engine.exception.TemplateEngineException;
import org.anttribe.dbviewer.base.infra.docgenerator.exception.DocumentGeneratorException;
import org.anttribe.dbviewer.base.infra.docgenerator.in.DataModel;
import org.anttribe.dbviewer.base.infra.docgenerator.out.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 文档生成器
 * 
 * @author zhaoyong
 * @date 2021-01-07
 */
public class DocumentGenerator implements Generator {

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentGenerator.class);

	/**
	 * 配置信息
	 */
	private Configuration configuration;

	public DocumentGenerator(Configuration configuration) {
		Assert.notNull(configuration, "configuration must not be null");

		this.configuration = configuration;
	}

	@Override
	public Output generate(DataModel dataModel) {
		TemplateEngine templateEngine = TemplateEngineFactory.me().getTemplateEngine(this.configuration);
		if (null == templateEngine) {
			LOGGER.error("can not get TemplateEngine with template configuration: {}",
					this.configuration.getTemplateConfig());
			throw new DocumentGeneratorException("can not get TemplateEngine with template configuration");
		}

		try {
			return templateEngine.process(dataModel);
		} catch (TemplateEngineException e) {
			throw new DocumentGeneratorException(e);
		}
	}

}