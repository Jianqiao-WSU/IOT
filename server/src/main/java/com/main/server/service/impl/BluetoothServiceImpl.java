package com.main.server.service.impl;

import com.main.server.entity.Bluetooth;
import com.main.server.mapper.BluetoothMapper;
import com.main.server.service.IBluetoothService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class BluetoothServiceImpl extends ServiceImpl<BluetoothMapper, Bluetooth> implements IBluetoothService {

	@Resource
	BluetoothMapper bluetoothMapper;
	
	@Override
	public List<Bluetooth> findAll() {
		// TODO Auto-generated method stub
		return bluetoothMapper.findAll();
	}
}
