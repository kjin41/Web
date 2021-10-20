package com.ssafy.ws.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
@ComponentScan(basePackages = {"com.ssafy.ws.model"})
public class Application {
	
	@Bean
	public DataSource getDataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/ssafydb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8");
		dataSource.setUsername("ssafy");
		dataSource.setPassword("ssafy");
		
		return dataSource;
	}

}
