package org.anttribe.dbviewer.base.infra.dbdocgenerator;

import java.util.HashMap;
import java.util.Map;

import org.anttribe.dbviewer.base.infra.dbassistor.DbAssistor;
import org.anttribe.dbviewer.base.infra.dbassistor.DbAssistorFactory;
import org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource;
import org.anttribe.dbviewer.base.infra.dbassistor.metadata.DbSchema;
import org.anttribe.dbviewer.base.infra.dbdocgenerator.config.Configuration;
import org.anttribe.dbviewer.base.infra.dbdocgenerator.config.DataSourceProcessorConfiguration;
import org.anttribe.dbviewer.base.infra.dbdocgenerator.exception.DbDocumentGeneratorException;
import org.anttribe.dbviewer.base.infra.docgenerator.DocumentGenerator;
import org.anttribe.dbviewer.base.infra.docgenerator.Generator;
import org.anttribe.dbviewer.base.infra.docgenerator.in.DataModel;
import org.anttribe.dbviewer.base.infra.docgenerator.out.Output;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 数据库文档生成
 * 
 * @author zhaoyong
 * @date 2021年2月10日
 */
public class DbDocumentGenerator {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbDocumentGenerator.class);

	/**
	 * 数据源
	 */
	private DataSource dataSource;

	/**
	 * 配置信息
	 */
	private Configuration configuration;

	/**
	 * 数据库操作辅助工具
	 */
	private DbAssistor dbAssistor;

	/**
	 * 文档生成器
	 */
	private Generator documentGenerator;

	public DbDocumentGenerator(DataSource dataSource, Configuration configuration) {
		Assert.notNull(dataSource, "dataSource must not be null");
		Assert.notNull(configuration, "configuration must not be null");

		this.dataSource = dataSource;
		this.configuration = configuration;

		// 构造documentGenerator
		org.anttribe.dbviewer.base.infra.docgenerator.config.Configuration generatorConfiguration = org.anttribe.dbviewer.base.infra.docgenerator.config.Configuration
				.builder().templateConfig(this.configuration.getTemplateConfig())
				.outputConfig(this.configuration.getOutputConfig()).build();
		documentGenerator = new DocumentGenerator(generatorConfiguration);
	}

	/**
	 * 生成文档
	 * 
	 * @return
	 */
	public Output generate() {
		return this.generate(new HashMap<String, Object>());
	}

	/**
	 * 生成文档
	 * 
	 * @param extraData
	 *            附加数据
	 * @return
	 */
	public Output generate(Map<String, Object> extraData) {
		// 构造DataModel
		DataModel dataModel = new DataSourceProcessor(this.dataSource, this.configuration.getDataSourceConfig())
				.process();
		if (null == dataModel) {
			return null;
		}
		// 添加额外数据
		if (!MapUtils.isEmpty(extraData)) {
			dataModel.addAllObjects(extraData);
		}

		// 文档生成
		return documentGenerator.generate(dataModel);
	}

	/**
	 * 数据处理
	 * 
	 * @author zhaoyong
	 * @date 2021年2月17日
	 */
	class DataSourceProcessor {

		/**
		 * 数据源对象
		 */
		private DataSource dataSource;

		/**
		 * 数据处理配置
		 */
		private DataSourceProcessorConfiguration dataSourceProcessorConfiguration;

		public DataSourceProcessor(DataSource dataSource,
				DataSourceProcessorConfiguration dataSourceProcessorConfiguration) {
			this.dataSource = dataSource;
			this.dataSourceProcessorConfiguration = dataSourceProcessorConfiguration;
		}

		/**
		 * 处理数据，获取数据模型
		 * 
		 * @return DataModel
		 */
		public DataModel process() {
			// 根据配置将数据库结构构造成DataModel数据模型对象
			dbAssistor = DbAssistorFactory.me().getDbAssistor((DataSource) this.dataSource);

			// 获取当前数据库schema
			DbSchema dbSchema = dbAssistor.getSchema();
			if (null == dbSchema) {
				LOGGER.error("failed to get current db schema info");
				throw new DbDocumentGeneratorException("can not get current db schema");
			}

			// 获取数据库相关信息

			// 1.数据库表
			// 获取需要处理的表名规则，用于筛选表
			String tableNamePattern = dataSourceProcessorConfiguration.getTableNamePattern();
			dbSchema.setTables(dbAssistor.listSchemaTables(dbSchema.getFullName(), tableNamePattern));

			// TODO: 2.数据库index

			// 构造DataModel
			DataModel dataModel = new DataModel();
			dataModel.addObject("dbSchema", dbSchema);

			return dataModel;
		}
	}

}