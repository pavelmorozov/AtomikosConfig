package com.example.demo.persistence.mapper.first;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.first.FirstModel;


@Mapper
public interface FirstMapper {

	@Select("SELECT * from first WHERE id = #{id}")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "name", column = "name")
	})
	FirstModel selectById(@Param("id") long id);

	
	@Insert("INSERT into first (id, name) VALUES(#{id}, #{name})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	long insert(FirstModel first);
}
