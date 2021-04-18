package org.anttribe.dbviewer.base.infra.docgenerator.config;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

/**
 * 模板配置
 * 
 * @author zhaoyong
 * @date 2021年1月12日
 */
public class TemplateConfiguration {

	/**
	 * 模板文件
	 */
	private File templateFile;

	private TemplateConfiguration() {
	}

	public File getTemplateFile() {
		return templateFile;
	}

	private void setTemplateFile(File templateFile) {
		this.templateFile = templateFile;
	}

	public static TemplateConfigurationBuilder builder() {
		return new TemplateConfiguration.TemplateConfigurationBuilder();
	}

	public static class TemplateConfigurationBuilder {

		/**
		 * 模板文件
		 */
		private String templateFileName;

		public TemplateConfigurationBuilder templateFile(String templateFileName) {
			this.templateFileName = templateFileName;
			return this;
		}

		/**
		 * 构造TemplateConfiguration
		 * 
		 * @return
		 */
		public TemplateConfiguration build() {
			TemplateConfiguration templateConfiguration = new TemplateConfiguration();
			// 加载模板文件
			if (!StringUtils.isEmpty(templateFileName)) {
				templateFileName = templateFileName.replace("\\", "/");
				File templateFile = new File(templateFileName);
				if (templateFile.exists() && templateFile.isFile()) {
					templateConfiguration.setTemplateFile(templateFile);
				}
			}
			return templateConfiguration;
		}

	}

}