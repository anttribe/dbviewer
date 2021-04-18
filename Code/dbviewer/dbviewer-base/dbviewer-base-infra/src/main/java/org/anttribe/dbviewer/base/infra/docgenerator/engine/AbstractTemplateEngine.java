package org.anttribe.dbviewer.base.infra.docgenerator.engine;

import java.io.File;
import java.io.IOException;

import org.anttribe.dbviewer.base.infra.docgenerator.config.Configuration;
import org.anttribe.dbviewer.base.infra.docgenerator.config.OutputConfiguration;
import org.anttribe.dbviewer.base.infra.docgenerator.engine.exception.TemplateEngineException;
import org.anttribe.dbviewer.base.infra.docgenerator.naming.NamingHandler;
import org.anttribe.dbviewer.base.infra.docgenerator.out.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaoyong
 * @date 2021年4月17日
 */
public abstract class AbstractTemplateEngine implements TemplateEngine {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTemplateEngine.class);

	protected Configuration configuration;

	public AbstractTemplateEngine(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * 创建输出
	 * 
	 * @return
	 */
	protected Output createOutput() {
		return new OutputCreator(this.configuration.getOutputConfig()).create();
	}

	/**
	 * 输出生成
	 * 
	 * @author zhaoyong
	 * @date 2021年4月14日
	 */
	class OutputCreator {

		private OutputConfiguration outputConfiguration;

		public OutputCreator(OutputConfiguration outputConfiguration) {
			this.outputConfiguration = outputConfiguration;
		}

		/**
		 * 创建输出
		 * 
		 * @return
		 */
		public Output create() {
			if (null == outputConfiguration) {
				return null;
			}

			// 自动创建文件输出目录
			String outputDirectory = outputConfiguration.getOutputDirectory();
			File outputDirectoryFile = new File(outputDirectory);
			if (!outputDirectoryFile.exists()) {
				outputDirectoryFile.mkdirs();
			}

			// 输出文件命名
			NamingHandler namingHandler = outputConfiguration.getNamingHandler();
			// 输出文件
			Output output = new Output();
			String outputFilename = namingHandler.naming() + (null != outputConfiguration.getOutputFileType()
					? outputConfiguration.getOutputFileType().getFileSuffix() : "");
			try {
				File outputFile = new File(outputDirectoryFile.getCanonicalPath() + File.separator + outputFilename);
				output.setOutputFile(outputFile);

				return output;
			} catch (IOException e) {
				LOGGER.error("failed to create output file, cause: {}", e);
				throw new TemplateEngineException("failed to create output file", e);
			}
		}

	}

}