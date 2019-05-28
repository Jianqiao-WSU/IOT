package com.main.server.mapper;

import com.main.server.entity.Bluetooth;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


public interface BluetoothMapper extends BaseMapper<Bluetooth> {
	@Select("select *from bluetooth")
	List<Bluetooth> findAll();
}
