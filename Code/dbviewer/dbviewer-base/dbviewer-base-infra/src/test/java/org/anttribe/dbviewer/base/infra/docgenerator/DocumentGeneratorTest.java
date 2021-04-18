package org.anttribe.dbviewer.base.infra.docgenerator;

import org.anttribe.dbviewer.base.infra.docgenerator.config.Configuration;
import org.anttribe.dbviewer.base.infra.docgenerator.config.OutputConfiguration;
import org.anttribe.dbviewer.base.infra.docgenerator.config.TemplateConfiguration;
import org.anttribe.dbviewer.base.infra.docgenerator.in.DataModel;
import org.anttribe.dbviewer.base.infra.docgenerator.out.FileType;
import org.anttribe.dbviewer.base.infra.docgenerator.out.Output;
import org.junit.Test;

/**
 * @author zhaoyong
 * @date 2021年1月13日
 */
public class DocumentGeneratorTest {

	@Test
	public void testGenerate() {
		DataModel dataModel = new DataModel();
		dataModel.addObject("version", "1.0.0");

		// 模板配置
		TemplateConfiguration templateConfiguration = TemplateConfiguration.builder()
				.templateFile("/Testing/docgenerator/templates/freemarker/documentation_txt.ftl").build();

		// 输出配置
		OutputConfiguration outputConfiguration = OutputConfiguration.builder()
				.outputDirectory("/Testing/docgenerator/outputs/").outputFileType(FileType.HTML).build();
		Configuration configuration = Configuration.builder().templateConfig(templateConfiguration)
				.outputConfig(outputConfiguration).build();

		Generator generator = new DocumentGenerator(configuration);
		Output output = generator.generate(dataModel);
		System.out.println(output);
	}

}