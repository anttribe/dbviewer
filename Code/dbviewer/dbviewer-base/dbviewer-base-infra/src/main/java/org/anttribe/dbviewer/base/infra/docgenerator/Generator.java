package org.anttribe.dbviewer.base.infra.docgenerator;

import org.anttribe.dbviewer.base.infra.docgenerator.in.DataModel;
import org.anttribe.dbviewer.base.infra.docgenerator.out.Output;

/**
 * @author zhaoyong
 * @date 2021年1月14日
 */
public interface Generator {
	
	/**
	 * 提供数据模型 生成文档
	 * 
	 * @param dataModel
	 * @return
	 */
	Output generate(DataModel dataModel);
	
}