package com.example.demo.persistence.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
public class SecondDatabaseConfiguration {

	@Bean
	public MapperScannerConfigurer secondMapperScannerConfigurer() {
		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
		configurer.setBasePackage("com.example.demo.persistence.mapper.second");
		configurer.setSqlSessionFactoryBeanName("secondSqlSessionFactory");
		return configurer;
	}
	
	/**
	 * This bean uses Atomikos
	 * to get transaction atomicity for 
	 * few data sources (distributed transaction)
	 */
    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.second")
    public DataSource secondDataSource() {
    	AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
    	atomikosDataSourceBean.setPoolSize(10);    	
    	atomikosDataSourceBean.setMaxLifetime(3600);
        return atomikosDataSourceBean;
    }

	@Bean
	public SqlSessionFactory secondSqlSessionFactory(
			@Qualifier("secondDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
	}
	
}