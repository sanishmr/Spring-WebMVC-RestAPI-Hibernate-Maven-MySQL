package com.san.spring.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {
		@ComponentScan("com.san.spring.dao"),
		@ComponentScan("com.san.spring.service")
})

public class AppConfig {
	@Autowired
	private Environment env;
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		Properties props = new Properties();
		
		// Setting JDBC properties
		
		System.out.println("----------- Inside App config before setting ---------------");
		
		props.forEach((k, v) -> System.out.println(k + ":" + v)); 
		
		System.out.println("----------- -------------------------------- ---------------");
		
		props.put(DRIVER, env.getProperty("mysql.driver"));
		props.put(URL, env.getProperty("mysql.url"));
		props.put(USER, env.getProperty("mysql.user"));
		props.put(PASS, env.getProperty("mysql.password"));
		
		
		
		// Setting hibernate properties
		
						
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
		
		
		// Setting C3P0 properties
		
		props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
		
		
		System.out.println("----------- Inside App config After setting ---------------");
		
		props.forEach((k, v) -> System.out.println(k + ":" + v)); 
		
		System.out.println("----------- -------------------------------- ---------------");
		
		factoryBean.setHibernateProperties(props);
		factoryBean.setPackagesToScan("com.san.spring.model");
		
		return factoryBean;
		
			
	}
	
	@Bean   ///  No qualifying bean of type 'org.springframework.transaction.PlatformTransactionManager' available at com.san.spring.controller.BookController.list(BookController.java:35)
	public HibernateTransactionManager getTransactionManager() {
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		
		return transactionManager;
		
	}

}
