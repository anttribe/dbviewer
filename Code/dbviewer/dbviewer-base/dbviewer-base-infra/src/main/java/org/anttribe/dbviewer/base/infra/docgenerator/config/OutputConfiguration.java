package org.anttribe.dbviewer.base.infra.docgenerator.config;

import org.anttribe.dbviewer.base.infra.docgenerator.naming.NamingHandler;
import org.anttribe.dbviewer.base.infra.docgenerator.naming.TimestampNamingHandler;
import org.anttribe.dbviewer.base.infra.docgenerator.out.FileType;
import org.apache.commons.lang3.StringUtils;

/**
 * 输出配置
 * 
 * @author zhaoyong
 * @date 2021年1月12日
 */
public class OutputConfiguration {

	/**
	 * 输出文件目录
	 */
	private String outputDirectory;

	/**
	 * 输出文件类型
	 */
	private FileType outputFileType;

	/**
	 * 输出文件命名处理类
	 */
	private NamingHandler namingHandler;

	private OutputConfiguration() {
	}

	public String getOutputDirectory() {
		return outputDirectory;
	}

	private void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	public FileType getOutputFileType() {
		return outputFileType;
	}

	private void setOutputFileType(FileType outputFileType) {
		this.outputFileType = outputFileType;
	}

	public NamingHandler getNamingHandler() {
		return namingHandler;
	}

	private void setNamingHandler(NamingHandler namingHandler) {
		this.namingHandler = namingHandler;
	}

	/**
	 * 获取builder对象
	 * 
	 * @return
	 */
	public static OutputConfigurationBuilder builder() {
		return new OutputConfigurationBuilder();
	}

	/**
	 * OutputConfigurationBuilder
	 * 
	 * @author zhaoyong
	 * @date 2021年4月14日
	 */
	public static class OutputConfigurationBuilder {

		/**
		 * 默认输出路径
		 */
		private static final String DEFAULT_OUTPUT_DIRECTORY = System.getProperty("user.dir");

		/**
		 * 默认输出文件类型
		 */
		private static final FileType DEFAULT_OUTPUT_FILE_TYPE = FileType.TXT;

		/**
		 * 输出文件目录
		 */
		private String outputDirectory;

		/**
		 * 输出文件类型
		 */
		private FileType outputFileType;

		/**
		 * 输出文件命名处理类
		 */
		private NamingHandler namingHandler;

		public OutputConfigurationBuilder outputDirectory(String outputDirectory) {
			this.outputDirectory = outputDirectory;
			return this;
		}

		public OutputConfigurationBuilder outputFileType(FileType outputFileType) {
			this.outputFileType = outputFileType;
			return this;
		}

		public OutputConfigurationBuilder namingHandler(NamingHandler namingHandler) {
			this.namingHandler = namingHandler;
			return this;
		}

		public OutputConfiguration build() {
			OutputConfiguration outputConfiguration = new OutputConfiguration();
			if (StringUtils.isEmpty(outputDirectory)) {
				outputDirectory = DEFAULT_OUTPUT_DIRECTORY;
			}
			outputConfiguration.setOutputDirectory(outputDirectory);

			if (null == outputFileType) {
				outputFileType = DEFAULT_OUTPUT_FILE_TYPE;
			}
			outputConfiguration.setOutputFileType(outputFileType);

			if (null == namingHandler) {
				namingHandler = new TimestampNamingHandler();
			}
			outputConfiguration.setNamingHandler(namingHandler);
			return outputConfiguration;
		}

	}

}