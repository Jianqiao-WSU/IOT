package com.main.server.service;

import com.main.server.entity.User;

import java.util.Set;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author camliu
 * @since 2019-03-19
 */
public interface IUserService extends IService<User> {

	User selectById(String id);
//	User queryForLogin(String username, String psw);
//	User findByID(String username);

	boolean insertUser(User user);

}
