package org.anttribe.dbviewer.base.infra.docgenerator.naming;

import java.util.UUID;

/**
 * UUID 命名
 * 
 * @author zhaoyong
 * @date 2021年2月17日
 */
public class UuidNamingHandler implements NamingHandler {

	@Override
	public String naming() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

}