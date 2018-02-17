package com.vineet.spring.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages="com.vineet.spring")   //look for all the classes annotated as @component in base package com.vineet.spring
/*@PropertySources({
@PropertySource("classpath:/config/oracledatabase.properties"),})*/
@PropertySource("classpath:/config/${DBVendor}/database.properties")

public class ApplicationConfig {

	@Autowired
	Environment environment;   //The role of the environment object with relation to properties is to provide the user with a convenient service interface for configuring property sources and resolving properties from them.
	
	@Bean
	protected DataSource dataSource() {
		
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(PropertyKeys.URL));
		driverManagerDataSource.setUsername(environment.getProperty(PropertyKeys.USER));
		driverManagerDataSource.setPassword(environment.getProperty(PropertyKeys.PASSWORD));
		driverManagerDataSource.setDriverClassName(environment.getProperty(PropertyKeys.DRIVER));
		return driverManagerDataSource;
	}
	
	@Bean
	protected JdbcTemplate getJDBCTemplate(DataSource dataSource) {
		
		return new JdbcTemplate(dataSource);
		
	}
	
	/*@Bean
	protected PersonDAOImpl getPersonDAO()
	{
		PersonDAOImpl obj = new PersonDAOImpl(dataSource());
		return obj;
	}*/
}


