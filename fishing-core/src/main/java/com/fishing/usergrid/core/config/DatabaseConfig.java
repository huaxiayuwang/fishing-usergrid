package com.fishing.usergrid.core.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fishing.usergrid.core.properties.HibernatePorperties;
import com.fishing.usergrid.core.properties.MysqlConnectionProperties;

/**
 * 
 * @author zhouhu <zhouhu690109@163.com>
 * 
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	@Autowired
	private MysqlConnectionProperties mysqlConnectionProperties;

	@Autowired
	private HibernatePorperties hibernatePorperties;

	@Autowired
	@Qualifier(value = "mysqlDataSource")
	private DataSource mysqlDataSource;

	@Resource(name = "mysqlEntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory;

	/**
	 * DataSource definition for database connection. Settings are read from the
	 * application.properties file (using the env object).
	 */
	@Bean(name = "mysqlDataSource")
	public DataSource mysqlDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(mysqlConnectionProperties.getDriver());
		dataSource.setUrl(mysqlConnectionProperties.getUrl());
		dataSource.setUsername(mysqlConnectionProperties.getUsername());
		dataSource.setPassword(mysqlConnectionProperties.getPassword());
		dataSource.setInitialSize(mysqlConnectionProperties.getInitialSize());
		dataSource.setMaxActive(mysqlConnectionProperties.getMaxActive());
		return dataSource;
	}

	/**
	 * Declare the JPA entity manager factory.
	 */
	@Bean(name = "mysqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(mysqlDataSource);
		// Classpath scanning of @Component, @Service, etc annotated class
		factory.setPackagesToScan("com.fishing");
		// vendor adapter
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		// Hibernate properties
		Properties pro = new Properties();
		pro.put("hibernate.dialect", hibernatePorperties.getDialect());
		pro.put("hibernate.show_sql", hibernatePorperties.isShow_sql());
		pro.put("hibernate.hbm2ddl.auto", hibernatePorperties.getHbm2ddl().getAuto());
		factory.setJpaProperties(pro);
		return factory;
	}

	/**
	 * Declare the transaction manager.
	 */
	@Bean(name = "mysqlTransactionManager")
	public JpaTransactionManager mysqlTransactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(mysqlEntityManagerFactory.getObject());
		return jpaTransactionManager;
	}

	/**
	 * PersistenceExceptionTranslationPostProcessor is a bean post processor
	 * which adds an advisor to any bean annotated with Repository so that any
	 * platform-specific exceptions are caught and then rethrown as one Spring's
	 * unchecked data access exceptions (i.e. a subclass of
	 * DataAccessException).
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
