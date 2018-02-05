package com.example.demo.persistence.mapper.first;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.first.FirstModel;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(
		exclude = {DataSourceAutoConfiguration.class, 
				DataSourceTransactionManagerAutoConfiguration.class, 
				MybatisAutoConfiguration.class
	})

public class FirstMapperTest {
	@Autowired
	FirstMapper firstMapper;
	
	@Test
	public void selectByIdTest() {
		FirstModel first = firstMapper.selectById(1);
		assertEquals("first name", first.getName());
	}
}
