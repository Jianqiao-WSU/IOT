package com.main.server.service.impl;

import com.main.server.entity.Environment;
import com.main.server.mapper.EnvirMapper;
import com.main.server.mapper.UserMapper;
import com.main.server.service.IEnvirService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvirServiceImpl extends ServiceImpl<EnvirMapper, Environment> implements IEnvirService {

	@Resource
    EnvirMapper envirMapper;
	
	@Override
	public List<Environment> findAll() {
		// TODO Auto-generated method stub
		return envirMapper.findAll();
	}

	@Override
	public boolean insertData(Environment environment) {
		// TODO Auto-generated method stub
		return envirMapper.insertData(environment);
	}
}
