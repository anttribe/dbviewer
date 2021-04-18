package org.anttribe.dbviewer.base.runtime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author zhaoyong
 * @date 2020-11-20
 */
@Configuration
@ComponentScan("org.anttribe.dbviewer.base.*")
@PropertySource("classpath:application.properties")
public class DbViewerBaseContextConfiguration {

	@Bean
	public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}