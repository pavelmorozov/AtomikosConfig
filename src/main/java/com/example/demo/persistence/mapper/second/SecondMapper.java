package com.example.demo.persistence.mapper.second;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.second.SecondModel;


@Mapper
public interface SecondMapper {

	@Select("SELECT * from second WHERE id = #{id}")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "name", column = "name")
	})
	SecondModel selectById(@Param("id") long id);

}
