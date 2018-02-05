package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.persistence.mapper.first.FirstMapper;
import com.example.demo.persistence.mapper.second.SecondMapper;

@RestController
public class DemoController {
	
	@Autowired
	FirstMapper firstMapper;
	
	@Autowired
	SecondMapper secondMapper;
	
	@GetMapping("/demo-controller")
	@Transactional
	public  String getDemoData() {
		
		String firstName = firstMapper.selectById(1).getName();
		String secondName = secondMapper.selectById(1).getName();
		
		String response = "{\"first\":"+firstName+", \"secondName\":"+secondName+"}";
		
		return response; 
	}

}
