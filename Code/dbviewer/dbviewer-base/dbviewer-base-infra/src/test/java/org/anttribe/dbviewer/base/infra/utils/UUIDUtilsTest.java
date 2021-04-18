package org.anttribe.dbviewer.base.infra.utils;

import java.util.UUID;

import org.junit.Test;

/**
 * @author zhaoyong
 * @date 2020-12-12
 */
public class UUIDUtilsTest {

	@Test
	public void testGetUUID() {
		String s = UUID.randomUUID().toString();
		System.out.println(s);

		System.out.println(s.substring(0, 8));
	}

}