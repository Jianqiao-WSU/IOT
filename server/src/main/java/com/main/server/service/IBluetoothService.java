package com.main.server.service;

import com.main.server.entity.Bluetooth;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author camliu
 * @since 2019-03-19
 */
public interface IBluetoothService extends IService<Bluetooth> {

	List<Bluetooth> findAll();

}
