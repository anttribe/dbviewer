package org.anttribe.dbviewer.base.infra.dbdocgenerator.config;

import org.anttribe.dbviewer.base.infra.docgenerator.config.OutputConfiguration;
import org.anttribe.dbviewer.base.infra.docgenerator.config.TemplateConfiguration;
import org.springframework.util.Assert;

/**
 * 配置信息
 * 
 * @author zhaoyong
 * @date 2021年2月10日
 */
public class Configuration {

	/**
	 * 数据源解析配置
	 */
	private DataSourceProcessorConfiguration dataSourceConfig;

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

	public DataSourceProcessorConfiguration getDataSourceConfig() {
		return dataSourceConfig;
	}

	private void setDataSourceConfig(DataSourceProcessorConfiguration dataSourceConfig) {
		this.dataSourceConfig = dataSourceConfig;
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
		 * 数据源解析配置
		 */
		private DataSourceProcessorConfiguration dataSourceConfig;

		/**
		 * 模板配置
		 */
		private TemplateConfiguration templateConfig;

		/**
		 * 输出配置
		 */
		private OutputConfiguration outputConfig;

		public ConfigurationBuilder dataSourceConfig(DataSourceProcessorConfiguration dataSourceConfig) {
			this.dataSourceConfig = dataSourceConfig;
			return this;
		}

		public ConfigurationBuilder templateConfig(TemplateConfiguration templateConfig) {
			this.templateConfig = templateConfig;
			return this;
		}

		public ConfigurationBuilder outputConfig(OutputConfiguration outputConfig) {
			this.outputConfig = outputConfig;
			return this;
		}

		public Configuration build() {
			Assert.notNull(dataSourceConfig, "dataSourceConfig must not be null");
			Assert.notNull(templateConfig, "templateConfig must not be null");
			Assert.notNull(outputConfig, "outputConfig must not be null");

			Configuration configuration = new Configuration();
			configuration.setDataSourceConfig(dataSourceConfig);
			configuration.setTemplateConfig(templateConfig);
			configuration.setOutputConfig(outputConfig);
			return configuration;
		}

	}

}