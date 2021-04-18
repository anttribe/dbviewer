package org.anttribe.dbviewer.base.infra.dbassistor;

import org.anttribe.dbviewer.base.infra.dbassistor.datasource.DataSource;

/**
 * @author zhaoyong
 * @date 2020-12-21
 */
public class DbAssistorFactory {

	private DbAssistorFactory() {
	}

	public static DbAssistorFactory me() {
		return DatabaseIntrospectorFactoryInstance.INSTANCE;
	}

	/**
	 * 获取DbAssistor工具类
	 * 
	 * @param dataSource
	 * @return
	 */
	public DbAssistor getDbAssistor(DataSource dataSource) {
		return this.getDbAssistor(dataSource, Boolean.TRUE);
	}

	/**
	 * 获取DbAssistor工具类
	 * 
	 * @param dataSource
	 * @return
	 */
	public DbAssistor getDbAssistor(DataSource dataSource, boolean lazyLoad) {
		if (null == dataSource) {
			return null;
		}
		return new DbAssistor(dataSource, lazyLoad);
	}

	/**
	 * 静态内部类实现单例
	 * 
	 * @author zhaoyong
	 * @date 2020-12-21
	 */
	private static class DatabaseIntrospectorFactoryInstance {

		public static final DbAssistorFactory INSTANCE = new DbAssistorFactory();

	}

}