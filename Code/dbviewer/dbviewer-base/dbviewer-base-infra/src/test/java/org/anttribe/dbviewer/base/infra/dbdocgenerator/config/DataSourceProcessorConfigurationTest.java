package org.anttribe.dbviewer.base.infra.dbdocgenerator.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author zhaoyong
 * @date 2021年4月10日
 */
public class DataSourceProcessorConfigurationTest {

	@Test
	public void testGetTableNamePattern() {
		DataSourceProcessorConfiguration configuration = DataSourceProcessorConfiguration.builder()
				.excludeTablePrefixs("t_,d_").build();

		String tableNamePattern = "(^[(?!t_)|(?!de)])([a-zA-Z0-9]*_?)"; // configuration.getTableNamePattern();
		System.out.println(tableNamePattern);

		String tableName = "deatasource";
		Pattern pattern = Pattern.compile(tableNamePattern, Pattern.CASE_INSENSITIVE);
		Matcher m = pattern.matcher(tableName);
		System.out.println(m.matches());
	}

}