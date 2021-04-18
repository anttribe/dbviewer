package org.anttribe.dbviewer.base.runtime.manager.impl;

import org.anttribe.dbviewer.base.core.dao.IDataSourceDao;
import org.anttribe.dbviewer.base.core.model.DataSourceDo;
import org.anttribe.dbviewer.base.infra.support.manager.impl.AbstractManagerImpl;
import org.anttribe.dbviewer.base.runtime.manager.IDataSourceManager;
import org.springframework.stereotype.Component;

/**
 * @author zhaoyong
 * @date 2020-11-20
 */
@Component
public class DataSourceManagerImpl extends AbstractManagerImpl<DataSourceDo, IDataSourceDao>
		implements IDataSourceManager {
}