package com.main.server.service.impl;

import com.main.server.entity.User;
import com.main.server.mapper.UserMapper;
import com.main.server.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author camliu
 * @since 2019-03-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

//	@Autowired
//    private UserMapper userMapper;
//    public User queryForLogin(String username, String psw) {
//        return userMapper.queryForLogin(username, psw);
//    }
	@Resource
    UserMapper userMapper;
    public User selectById(String id) {
        return userMapper.selectById(id);
    }
	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.insertUser(user);
	}
}
