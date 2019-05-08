package com.main.server.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.main.server.annotation.UserLoginToken;
import com.main.server.entity.User;
import com.main.server.mapper.UserMapper;
import com.main.server.vo.Json;
import com.main.server.service.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author camliu
 * @since 2019-03-19
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private IUserService userService;
	@Autowired
    TokenService tokenService;
	
	HttpServletResponse responce;
	
	@PostMapping(path = "/register")
	public Json register(@RequestParam String body) throws IOException {
		System.out.println(body);
		String oper = "add sys user";
//		log.info("{}, body: {}", oper, body);
		User user = JSON.parseObject(body, User.class);
        if (StringUtils.isEmpty(user.getUsername())) {
        	System.out.println(Json.fail(oper, "用户帐号名不能为空"));
            return Json.fail(oper, "用户帐号名不能为空");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
        	System.out.println(Json.fail(oper, "密码不能为空"));
            return Json.fail(oper, "密码不能为空");
        }

//        try {
    	User userDB = userService.selectById(user.getUsername());
//            System.out.println(userDB.getUsername());
        if (userDB != null) {
        	System.out.println(Json.fail(oper, "用户已注册"));
            return Json.fail(oper, "用户已注册");
        }
        
        //密码加密
        RandomNumberGenerator saltGen = new SecureRandomNumberGenerator();
        String salt = saltGen.nextBytes().toBase64();
        String hashedPwd = new Sha256Hash(user.getPassword(), salt, 1024).toBase64();
        //保存新用户数据
        user.setPassword(hashedPwd);
        System.out.println(salt);
        user.setSalt(salt);
        user.setCreated(new Date());
        
        boolean success = userService.insertUser(user);
        System.out.println(Json.result(oper, success)
                .data("username",user.getUsername())
                .data("created",user.getCreated()));
        return Json.result(oper, success)
                .data("username",user.getUsername())
                .data("created",user.getCreated());
	}
	
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Json login(@RequestParam String username,
                        @RequestParam String password) throws IOException {
		String oper = "user login";
        if (StringUtils.isEmpty(username)) {
            return Json.fail(oper, "用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            return Json.fail(oper, "密码不能为空");
        }
        
        Subject currentUser = SecurityUtils.getSubject();
        
		System.out.println(currentUser);
//		currentUser.login(new UsernamePasswordToken(username, password));
        try {
//        	System.out.println(currentUser.login(new UsernamePasswordToken(username, password)));
        	System.out.println("123456"+password);
        	currentUser.login(new UsernamePasswordToken(username, password));
        	User userList = (User) currentUser.getPrincipal();
        	
//        	Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
//        	
//	        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//			SqlSession session = sqlSessionFactory.openSession();
//	        UserMapper um = session.getMapper(UserMapper.class);
//	        User userList = um.selectById(username);
//	        System.out.println(userList.getUname()+"123456");
//	        System.out.println(userList.getPSW()+"123456");
	        if (userList == null) {throw new AuthenticationException();}
	        log.info("user login: {}, sessionId: {}",userList.getUsername(),currentUser.getSession().getId());
	        System.out.println("登录成功！");
	        String token = tokenService.getToken(userList);
//	        responce.setHeader("token", token);
        	return Json.succ(oper).data("token", token)
                    .data("username",userList.getUsername());
        } catch ( UnknownAccountException uae ) {
        	System.out.println("用户帐号不正确");
            return Json.fail(oper,"该帐号不存在");
        } catch ( IncorrectCredentialsException ice ) {
            log.warn("用户密码不正确");
            return Json.fail(oper,"用户密码不正确");
        } catch ( AuthenticationException ae ) {
        	System.out.println("登录出错");
            return Json.fail(oper,"登录失败："+ae.getMessage());
        }
    }
	
	@UserLoginToken
	@RequestMapping(path = "/resetPsw", method = RequestMethod.POST)
    @ResponseBody
    public Json resetPsw(@RequestParam String body) throws IOException {
		String oper = "user reset password";
		User user = JSON.parseObject(body, User.class);
		System.out.println(body);
        if (StringUtils.isEmpty(user.getUsername())) {
        	System.out.println(Json.fail(oper, "用户帐号获取异常"));
            return Json.fail(oper, "用户帐号获取异常");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
        	System.out.println(Json.fail(oper, "密码不能为空"));
            return Json.fail(oper, "密码不能为空");
        }
        if (StringUtils.isEmpty(user.getResetPSW())) {
        	System.out.println(Json.fail(oper, "重设密码不能为空"));
            return Json.fail(oper, "重设密码不能为空");
        }
        Subject currentUser = SecurityUtils.getSubject();
        try {
        	currentUser.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
        	User userList = (User) currentUser.getPrincipal();
	        if (userList == null) {throw new AuthenticationException();}
	        log.info("user login: {}, sessionId: {}",userList.getUsername(),currentUser.getSession().getId());
	        System.out.println("登录成功！");
	        
	        System.out.println(user.getResetPSW());
	        //密码加密
	        RandomNumberGenerator saltGen = new SecureRandomNumberGenerator();
	        String salt = saltGen.nextBytes().toBase64();
	        String hashedPwd = new Sha256Hash(user.getResetPSW(), salt, 1024).toBase64();
	        user.setPassword(hashedPwd);
	        user.setSalt(salt);
	        user.setUpdated(new Date());
	        System.out.println(user.getPassword());
	        
	        boolean success = userService.resetPasswordUser(user);
	        
	        System.out.println(Json.result(oper, success)
	                .data("updated",user.getUpdated()));
	        return Json.result(oper, success)
	                .data("updated",user.getUpdated());
        } catch ( IncorrectCredentialsException ice ) {
            log.warn("用户密码不正确");
            return Json.fail(oper,"用户密码不正确");
        } catch ( AuthenticationException ae ) {
        	System.out.println("登录出错");
            return Json.fail(oper,"登录失败："+ae.getMessage());
        }
    }
	
	@UserLoginToken
	@RequestMapping(path = "/deleteAcc", method = RequestMethod.POST)
    @ResponseBody
    public Json deleteAcc(@RequestParam String body) throws IOException {
		String oper = "delete user";
		User user = JSON.parseObject(body, User.class);
		System.out.println(body);
        if (StringUtils.isEmpty(user.getUsername())) {
        	System.out.println(Json.fail(oper, "用户帐号获取异常"));
            return Json.fail(oper, "用户帐号获取异常");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
        	System.out.println(Json.fail(oper, "密码不能为空"));
            return Json.fail(oper, "密码不能为空");
        }
        Subject currentUser = SecurityUtils.getSubject();
        try {
        	currentUser.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
        	User userList = (User) currentUser.getPrincipal();
	        if (userList == null) {throw new AuthenticationException();}
	        log.info("user login: {}, sessionId: {}",userList.getUsername(),currentUser.getSession().getId());
	        
	        boolean success = userService.deleteUser(user);
	        System.out.println("删除成功！");
	        System.out.println(Json.result(oper, success));
	        return Json.result(oper, success);
        } catch ( IncorrectCredentialsException ice ) {
            log.warn("用户密码不正确");
            return Json.fail(oper,"用户密码不正确");
        } catch ( AuthenticationException ae ) {
        	System.out.println("登录出错");
            return Json.fail(oper,"登录失败："+ae.getMessage());
        }
    }
}
