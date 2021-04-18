package org.anttribe.dbviewer.base.infra.utils;

import java.util.UUID;

/**
 * @author zhaoyong
 * @date 2020-12-12
 */
public final class UUIDUtils {

	/**
	 * 生成唯一主键(UUID)
	 * 
	 * @return String
	 */
	public static String getRandomUUID() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

}