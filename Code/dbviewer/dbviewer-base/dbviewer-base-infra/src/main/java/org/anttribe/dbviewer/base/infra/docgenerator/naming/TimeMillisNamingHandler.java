package org.anttribe.dbviewer.base.infra.docgenerator.naming;

/**
 * 毫秒数 命名
 * 
 * @author zhaoyong
 * @date 2021年4月14日
 */
public class TimeMillisNamingHandler implements NamingHandler {

	@Override
	public String naming() {
		return "" + System.currentTimeMillis();
	}

}