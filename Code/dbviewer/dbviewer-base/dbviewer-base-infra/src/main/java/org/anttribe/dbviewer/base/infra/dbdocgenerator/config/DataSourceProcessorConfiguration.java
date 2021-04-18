package org.anttribe.dbviewer.base.infra.dbdocgenerator.config;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据源配置
 * 
 * @author zhaoyong
 * @date 2021年2月16日
 */
public class DataSourceProcessorConfiguration {

	/**
	 * 表名正则
	 */
	private String tableNamePattern;

	private DataSourceProcessorConfiguration() {
	}

	public String getTableNamePattern() {
		return tableNamePattern;
	}

	private void setTableNamePattern(String tableNamePattern) {
		this.tableNamePattern = tableNamePattern;
	}

	/**
	 * 获取builder对象
	 * 
	 * @return
	 */
	public static DataSourceProcessorConfigurationBuilder builder() {
		return new DataSourceProcessorConfigurationBuilder();
	}

	/**
	 * @author zhaoyong
	 * @date 2021年4月7日
	 */
	public static class DataSourceProcessorConfigurationBuilder {

		/**
		 * 默认表名正则
		 */
		private static final String TABLE_NAME_PATTERN = "(^_([a-zA-Z0-9]_?)+$)|(^[a-zA-Z](_?[a-zA-Z0-9])*_?$)";

		/**
		 * 校验表名正则
		 */
		private static final String VERIFY_TABLE_NAME_PATTERN = TABLE_NAME_PATTERN;

		/**
		 * 校验表前缀正则
		 */
		private static final String VERIFY_TABLE_NAME_PREFIX_PATTERN = "(^_([a-zA-Z0-9]_?)+)|(^[a-zA-Z](_?[a-zA-Z0-9])*_?)";

		/**
		 * 校验表后缀正则
		 */
		private static final String VERIFY_TABLE_NAME_SUFFIX_PATTERN = "(_([a-zA-Z0-9]_?)+$)|([a-zA-Z](_?[a-zA-Z0-9])*_?$)";

		/**
		 * 排除的表名
		 */
		private List<String> excludeTables;

		/**
		 * 排除的表名的前缀
		 */
		private List<String> excludeTablePrefixs;

		/**
		 * 排除的表名的后缀
		 */
		private List<String> excludeTableSuffixs;

		/**
		 * 排除的数据库表名
		 * 
		 * @param excludeTableNames
		 * @return
		 */
		public DataSourceProcessorConfigurationBuilder excludeTableNames(String excludeTableNames) {
			if (!StringUtils.isEmpty(excludeTableNames)) {
				String[] excludeTableNameArray = StringUtils.split(excludeTableNames, ",");
				if (!ArrayUtils.isEmpty(excludeTableNameArray)) {
					if (null == this.excludeTables) {
						this.excludeTables = new ArrayList<String>();
					}
					Pattern p = Pattern.compile(VERIFY_TABLE_NAME_PATTERN, Pattern.CASE_INSENSITIVE);
					// 对参数进行校验和处理， 符合表名规则
					for (String excludeTableName : excludeTableNameArray) {
						Matcher m = p.matcher(excludeTableName);
						if (null != m && m.matches()) {
							this.excludeTables.add(excludeTableName);
						}
					}
				}
			}
			return this;
		}

		/**
		 * 排除的数据库表前缀
		 * 
		 * @param excludeTablePrefixs
		 * @return
		 */
		public DataSourceProcessorConfigurationBuilder excludeTablePrefixs(String excludeTablePrefixs) {
			if (!StringUtils.isEmpty(excludeTablePrefixs)) {
				String[] excludeTablePrefixArray = StringUtils.split(excludeTablePrefixs, ",");
				if (!ArrayUtils.isEmpty(excludeTablePrefixArray)) {
					if (null == this.excludeTablePrefixs) {
						this.excludeTablePrefixs = new ArrayList<String>();
					}
					Pattern p = Pattern.compile(VERIFY_TABLE_NAME_PREFIX_PATTERN, Pattern.CASE_INSENSITIVE);
					// 对参数进行校验和处理， 符合表名规则
					for (String excludeTablePrefix : excludeTablePrefixArray) {
						Matcher m = p.matcher(excludeTablePrefix);
						if (null != m && m.matches()) {
							this.excludeTablePrefixs.add(excludeTablePrefix);
						}
					}
				}
			}
			return this;
		}

		/**
		 * 排除的表后缀列表
		 * 
		 * @param excludeTableSuffixs
		 * @return
		 */
		public DataSourceProcessorConfigurationBuilder excludeTableSuffixs(String excludeTableSuffixs) {
			if (!StringUtils.isEmpty(excludeTableSuffixs)) {
				String[] excludeTableSuffixArray = StringUtils.split(excludeTableSuffixs, ",");
				if (!ArrayUtils.isEmpty(excludeTableSuffixArray)) {
					if (null == this.excludeTableSuffixs) {
						this.excludeTableSuffixs = new ArrayList<String>();
					}
					Pattern p = Pattern.compile(VERIFY_TABLE_NAME_SUFFIX_PATTERN, Pattern.CASE_INSENSITIVE);
					// 对参数进行校验和处理， 符合表名规则
					for (String excludeTableSuffix : excludeTableSuffixArray) {
						Matcher m = p.matcher(excludeTableSuffix);
						if (null != m && m.matches()) {
							this.excludeTableSuffixs.add(excludeTableSuffix);
						}
					}
				}
			}
			return this;
		}

		/**
		 * 获取构造后对象
		 * 
		 * @return
		 */
		public DataSourceProcessorConfiguration build() {
			DataSourceProcessorConfiguration configuration = new DataSourceProcessorConfiguration();
			configuration.setTableNamePattern(TABLE_NAME_PATTERN);

			StringBuilder tableNamePatternBuilder = new StringBuilder("");
			// 过滤表前缀
			List<String> excludeTablePrefixs = this.excludeTablePrefixs;
			if (!CollectionUtils.isEmpty(excludeTablePrefixs)) {
				tableNamePatternBuilder.append("^");
				int index = 0;
				for (String excludeTablePrefix : excludeTablePrefixs) {
					if (index++ > 0) {
						tableNamePatternBuilder.append("|");
					}
					tableNamePatternBuilder.append("(?!(").append(excludeTablePrefix).append("))");
				}
				// tableNamePatternBuilder.append(")");
			}

			// 过滤表
			List<String> excludeTables = this.excludeTables;
			if (!CollectionUtils.isEmpty(excludeTables)) {
			}

			// 过滤表后缀
			List<String> excludeTableSuffixs = this.excludeTableSuffixs;
			if (!CollectionUtils.isEmpty(excludeTableSuffixs)) {
				tableNamePatternBuilder.append("(");
				int index = 0;
				for (String excludeTableSuffix : excludeTableSuffixs) {
					if (index > 0) {
						tableNamePatternBuilder.append("|");
					}
					tableNamePatternBuilder.append("(?!").append(excludeTableSuffix).append(")");
				}
				tableNamePatternBuilder.append(")$");
			}

			if (!StringUtils.isEmpty(tableNamePatternBuilder)) {
				configuration.setTableNamePattern(tableNamePatternBuilder.toString());
			}
			return configuration;
		}

	}

}