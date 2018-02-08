package com.example.demo.persistence.mapper.first;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.first.FirstModel;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(
		exclude = {DataSourceAutoConfiguration.class, 
				DataSourceTransactionManagerAutoConfiguration.class, 
				MybatisAutoConfiguration.class
	})
@AutoConfigureMockMvc
public class FirstMapperTest {
	@Autowired
	FirstMapper firstMapper;
	
	@Autowired
	@Qualifier("firstDataSource")
	DataSource firstDataSource;
	
	@Autowired
	@Qualifier("secondDataSource")
	DataSource secondDataSource;
	
	@Test
	public void selectByIdTest() {
		FirstModel first = firstMapper.selectById(1);
		assertEquals("first name", first.getName());
	}
	
	@Test
	@Transactional
	public void insertTest() {
		FirstModel firstModel = new FirstModel();
		firstModel.setName("new enty");
		firstMapper.insert(firstModel);

		assertTrue(firstModel.getId()>0);
	}
	
	@After
	public void afterTest() {
		
		System.out.println("Close data sources");
		
		((AtomikosDataSourceBean)firstDataSource).close();
		((AtomikosDataSourceBean)secondDataSource).close();
	} 
}
