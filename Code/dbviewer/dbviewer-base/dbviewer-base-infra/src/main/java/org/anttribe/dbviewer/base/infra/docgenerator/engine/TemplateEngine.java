package org.anttribe.dbviewer.base.infra.docgenerator.engine;

import org.anttribe.dbviewer.base.infra.docgenerator.in.DataModel;
import org.anttribe.dbviewer.base.infra.docgenerator.out.Output;

/**
 * @author zhaoyong
 * @date 2021年1月13日
 */
public interface TemplateEngine {

	/**
	 * 生成文档处理方法
	 * 
	 * @param dataModel
	 */
	Output process(DataModel dataModel);

}