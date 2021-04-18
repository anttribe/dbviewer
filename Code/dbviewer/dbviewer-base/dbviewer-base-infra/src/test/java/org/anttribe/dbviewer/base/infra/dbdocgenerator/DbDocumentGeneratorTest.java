package org.anttribe.dbviewer.base.infra.dbdocgenerator;

import org.anttribe.dbviewer.base.infra.dbassistor.Dialect;
import org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource;
import org.anttribe.dbviewer.base.infra.dbdocgenerator.config.Configuration;
import org.anttribe.dbviewer.base.infra.dbdocgenerator.config.DataSourceProcessorConfiguration;
import org.anttribe.dbviewer.base.infra.docgenerator.config.OutputConfiguration;
import org.anttribe.dbviewer.base.infra.docgenerator.config.TemplateConfiguration;
import org.anttribe.dbviewer.base.infra.docgenerator.out.FileType;
import org.anttribe.dbviewer.base.infra.docgenerator.out.Output;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhaoyong
 * @date 2021年2月15日
 */
public class DbDocumentGeneratorTest {

	private DataSource dataSource;

	private Configuration configuration;

	@Before
	public void before() {
		dataSource = new DataSource();
		dataSource.setDialect(Dialect.MYSQL);
		dataSource.setUrl(Dialect.MYSQL.populateDbConnectUrl("localhost", "3306", "db_dbviewer"));
		dataSource.setUsername("admin");
		dataSource.setPassword("123456");
	}

	@Test
	public void testGenerate() {
		// 数据库处理配置
		DataSourceProcessorConfiguration dataSourceProcessorConfiguration = DataSourceProcessorConfiguration.builder()
				.build();
		// 模板配置
		TemplateConfiguration templateConfiguration = TemplateConfiguration.builder()
				.templateFile("/Testing/dbviewer/templates/freemarker/documentation_html.ftl").build();

		// 输出配置
		OutputConfiguration outputConfiguration = OutputConfiguration.builder()
				.outputDirectory("/Testing/dbviewer/outputs/").outputFileType(FileType.HTML).build();
		configuration = Configuration.builder().build();

		DbDocumentGenerator generator = new DbDocumentGenerator(dataSource, configuration);
		Output output = generator.generate();
		System.out.println(output);
	}

	@Test
	public void testDataSourceProcessorConfigurationGenerate() {
		// 数据库处理配置
		DataSourceProcessorConfiguration dataSourceProcessorConfiguration = DataSourceProcessorConfiguration.builder()
				.build();
		// 模板配置
		TemplateConfiguration templateConfiguration = TemplateConfiguration.builder()
				.templateFile("/Testing/dbviewer/templates/freemarker/documentation_html.ftl").build();

		// 输出配置
		OutputConfiguration outputConfiguration = OutputConfiguration.builder()
				.outputDirectory("/Testing/dbviewer/outputs/").outputFileType(FileType.HTML).build();
		configuration = Configuration.builder().dataSourceConfig(dataSourceProcessorConfiguration)
				.templateConfig(templateConfiguration).outputConfig(outputConfiguration).build();

		DbDocumentGenerator generator = new DbDocumentGenerator(dataSource, configuration);
		Output output = generator.generate();
		System.out.println(output);
	}

	@Test
	public void testTemplateConfigurationGenerate() {
		// 数据库处理配置
		DataSourceProcessorConfiguration dataSourceProcessorConfiguration = DataSourceProcessorConfiguration.builder()
				.build();
		// 模板配置
		TemplateConfiguration templateConfiguration = TemplateConfiguration.builder()
				.templateFile("/Testing/dbviewer/templates/freemarker/documentation_html.ftl").build();
		// 输出配置
		OutputConfiguration outputConfiguration = OutputConfiguration.builder()
				.outputDirectory("/Testing/dbviewer/outputs/").outputFileType(FileType.HTML).build();
		configuration = Configuration.builder().dataSourceConfig(dataSourceProcessorConfiguration)
				.templateConfig(templateConfiguration).outputConfig(outputConfiguration).build();

		DbDocumentGenerator generator = new DbDocumentGenerator(dataSource, configuration);
		Output output = generator.generate();
		System.out.println(output);
	}

	@Test
	public void testOutputConfigurationGenerate() {
		// 数据库处理配置
		DataSourceProcessorConfiguration dataSourceProcessorConfiguration = DataSourceProcessorConfiguration.builder()
				.build();
		// 模板配置
		TemplateConfiguration templateConfiguration = TemplateConfiguration.builder()
				.templateFile("/Testing/dbviewer/templates/freemarker/documentation_html.ftl").build();
		// 输出配置
		OutputConfiguration outputConfiguration = OutputConfiguration.builder().build();
		configuration = Configuration.builder().dataSourceConfig(dataSourceProcessorConfiguration)
				.templateConfig(templateConfiguration).outputConfig(outputConfiguration).build();

		DbDocumentGenerator generator = new DbDocumentGenerator(dataSource, configuration);
		Output output = generator.generate();
		System.out.println(output);
	}

}