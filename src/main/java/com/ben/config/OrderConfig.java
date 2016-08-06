package com.ben.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.ben.domain.mysql2", entityManagerFactoryRef = "orderEntityManager", transactionManagerRef = "transactionManager")
public class OrderConfig {
	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;
	@Autowired
	private Environment env;

	@Bean(name = "orderDataSource")
	public AtomikosDataSourceBean orderDataSource() {
		MysqlXADataSource xaDataSource = new MysqlXADataSource();
		xaDataSource.setUrl(env.getRequiredProperty("db.mysql2.url"));
		xaDataSource.setPassword(env.getRequiredProperty("db.mysql2.password"));
		xaDataSource.setUser(env.getRequiredProperty("db.mysql2.username"));
		xaDataSource.setPinGlobalTxToPhysicalConnection(true);
		AtomikosDataSourceBean  atomikosDataSource = new AtomikosDataSourceBean();
	    atomikosDataSource.setUniqueResourceName("xads2");
	    atomikosDataSource.setXaDataSource(xaDataSource);
	    atomikosDataSource.setXaDataSourceClassName(MysqlXADataSource.class.getName());
	    return atomikosDataSource;
	}
	
	@Bean(name = "orderEntityManager")
	@DependsOn("transactionManager")
	public LocalContainerEntityManagerFactoryBean orderEntityManager() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		
		LocalContainerEntityManagerFactoryBean entityManager  = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setJtaDataSource(orderDataSource());
		entityManager.setPackagesToScan("com.ben.domain.mysql2");
		entityManager.setPersistenceUnitName("customerPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}
}
