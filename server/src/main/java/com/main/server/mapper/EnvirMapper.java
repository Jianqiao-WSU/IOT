package com.main.server.mapper;

import com.main.server.entity.Environment;
import com.main.server.entity.User;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;


public interface EnvirMapper extends BaseMapper<Environment> {
	@Select("select *from environment")
	List<Environment> findAll();	//以ID查询
	
	@Insert("insert into environment(deviceId,temperature,humidity,pressure,sun,time,RSSI) values(#{deviceId},#{temperature},#{humidity},#{pressure},#{sun},#{time},#{RSSI})")
	boolean insertData(Environment environment);
}
