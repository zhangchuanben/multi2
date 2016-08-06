package com.ben.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.ben.domain.mysql", entityManagerFactoryRef = "customerEntityManager", transactionManagerRef = "transactionManager")
public class CustomerConfig {
	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;
	@Autowired
	private Environment env;

	@Bean(name = "customerDataSource")
	@Primary
	public AtomikosDataSourceBean customerDataSource() {
		MysqlXADataSource xaDataSource = new MysqlXADataSource();
		xaDataSource.setUrl(env.getRequiredProperty("db.mysql.url"));
		xaDataSource.setPassword(env.getRequiredProperty("db.mysql.password"));
		xaDataSource.setUser(env.getRequiredProperty("db.mysql.username"));
		xaDataSource.setPinGlobalTxToPhysicalConnection(true);
		AtomikosDataSourceBean  atomikosDataSource = new AtomikosDataSourceBean();
	    atomikosDataSource.setUniqueResourceName("xads1");
	    atomikosDataSource.setXaDataSource(xaDataSource);
	    atomikosDataSource.setXaDataSourceClassName(MysqlXADataSource.class.getName());
	    return atomikosDataSource;
	}
	
	@Bean(name = "customerEntityManager")
	@DependsOn("transactionManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean customerEntityManager() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		
		LocalContainerEntityManagerFactoryBean entityManager  = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setJtaDataSource(customerDataSource());
		entityManager.setPackagesToScan("com.ben.domain.mysql");
		entityManager.setPersistenceUnitName("customerPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}
}
