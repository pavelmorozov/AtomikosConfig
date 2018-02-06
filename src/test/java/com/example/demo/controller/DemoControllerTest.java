package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sql.DataSource;

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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.first.FirstModel;
import com.example.demo.model.second.SecondModel;
import com.example.demo.persistence.mapper.first.FirstMapper;
import com.example.demo.persistence.mapper.second.SecondMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, MybatisAutoConfiguration.class })
@AutoConfigureMockMvc

public class DemoControllerTest {

	@Autowired
	@Qualifier("firstDataSource")
	DataSource firstDataSource;
	
	@Autowired
	@Qualifier("secondDataSource")
	DataSource secondDataSource;
	
	@MockBean
	FirstMapper firstMapper;

	@MockBean
	SecondMapper secondMapper;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	public void getDemoDataTest() throws Exception {

		FirstModel firstModel = new FirstModel();
		firstModel.setId(1);
		firstModel.setName("first name");
		given(firstMapper.selectById(1l)).willReturn(firstModel);
		
		SecondModel secondModel = new SecondModel();
		secondModel.setId(1);
		secondModel.setName("second name");
		given(secondMapper.selectById(1l)).willReturn(secondModel);

		mvc.perform(get("/demo-controller").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.first", is("first name")));

	}
	
	@After
	public void afterTest() {
		((AtomikosDataSourceBean)firstDataSource).close();
		((AtomikosDataSourceBean)secondDataSource).close();
	}	

}
