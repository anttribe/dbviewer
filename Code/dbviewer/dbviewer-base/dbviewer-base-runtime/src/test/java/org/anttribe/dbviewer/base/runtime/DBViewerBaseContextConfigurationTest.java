package org.anttribe.dbviewer.base.runtime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhaoyong
 * @date 2020-11-27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class DBViewerBaseContextConfigurationTest {

	@Value("${app.support.db.types}")
	private String supportDbTypes;

	@Test
	public void testPropertyPlaceHolder() {
		System.out.println(supportDbTypes);
	}
}