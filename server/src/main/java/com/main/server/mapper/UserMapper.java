package com.main.server.mapper;

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

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author camliu
 * @since 2019-03-19
 */
public interface UserMapper extends BaseMapper<User> {
	@Select("select *from user where username=#{id}")
	User selectById(Serializable id);	//以ID查询
//	User selectById(@Param("id") Serializable id);
	
//	public List<User> queryAll();
//	User queryForLogin(User user);
//	User queryForLogin(String username, String psw);
	
	@Insert("insert into user(username,password,salt) values(#{username},#{password},#{salt})")
	boolean insertUser(User user);
	
	@Update("update user set password=#{password},salt=#{salt} where username=#{username}")
	boolean resetPasswordUser(User user);
	
	@Delete("delete from user where username=#{username}")
	boolean deleteUser(User user);
}
