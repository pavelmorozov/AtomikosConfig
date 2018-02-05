package com.example.demo;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(
		exclude = {DataSourceAutoConfiguration.class, 
				DataSourceTransactionManagerAutoConfiguration.class, 
				MybatisAutoConfiguration.class})
public class AtomikosConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtomikosConfigApplication.class, args);
	}
}
