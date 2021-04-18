package org.anttribe.dbviewer.base.infra.docgenerator.config;

import org.springframework.util.Assert;

/**
 * 配置信息
 * 
 * @author zhaoyong
 * @date 2021-01-07
 */
public class Configuration {

	/**
	 * 模板配置
	 */
	private TemplateConfiguration templateConfig;

	/**
	 * 输出配置
	 */
	private OutputConfiguration outputConfig;

	private Configuration() {
	}

	public TemplateConfiguration getTemplateConfig() {
		return templateConfig;
	}

	private void setTemplateConfig(TemplateConfiguration templateConfig) {
		this.templateConfig = templateConfig;
	}

	public OutputConfiguration getOutputConfig() {
		return outputConfig;
	}

	private void setOutputConfig(OutputConfiguration outputConfig) {
		this.outputConfig = outputConfig;
	}

	public static ConfigurationBuilder builder() {
		return new ConfigurationBuilder();
	}

	public static class ConfigurationBuilder {

		/**
		 * 模板配置
		 */
		private TemplateConfiguration templateConfig;

		/**
		 * 输出配置
		 */
		private OutputConfiguration outputConfig;

		public ConfigurationBuilder templateConfig(TemplateConfiguration templateConfig) {
			this.templateConfig = templateConfig;
			return this;
		}

		public ConfigurationBuilder outputConfig(OutputConfiguration outputConfig) {
			this.outputConfig = outputConfig;
			return this;
		}

		public Configuration build() {
			Assert.notNull(templateConfig, "templateConfig must not be null");
			Assert.notNull(outputConfig, "outputConfig must not be null");

			Configuration configuration = new Configuration();
			configuration.setTemplateConfig(templateConfig);
			configuration.setOutputConfig(outputConfig);
			return configuration;
		}

	}

}