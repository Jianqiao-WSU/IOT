package com.main.server.shiro;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.main.server.entity.User;
import com.main.server.mapper.UserMapper;
import com.main.server.service.IUserService;


import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * 这个类是参照JDBCRealm写的，主要是自定义了如何查询用户信息，如何查询用户的角色和权限，如何校验密码等逻辑
 */
public class UserRealm extends AuthorizingRealm {

	private static final Logger log =LoggerFactory.getLogger(UserRealm.class);
	@Autowired

    private IUserService userService;
	private UserMapper userMapper;
	
	@Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        //设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        hashMatcher.setStoredCredentialsHexEncoded(false);
        hashMatcher.setHashIterations(1024);
        super.setCredentialsMatcher(hashMatcher);
    }
//	public UserRealm() {
//        setName("UserRealm");
//        // 采用MD5加密
//        setCredentialsMatcher(new HashedCredentialsMatcher("md5"));
//    }

	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		log.info("##################执行Shiro权限认证##################");
//		User user = (User) principals.getPrimaryPrincipal();
//		if (user != null) {
//            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
////            //用户的角色集合
////            info.addRoles(user.getRoleStrlist()); 
////            //用户的权限集合
////            info.addStringPermissions(user.getPerminsStrlist()); 
//            return info;
//        }
//		return null;
		//null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        User user = (User) getAvailablePrincipal(principals);
        String roles = user.getAuthority();
        log.info("获取角色权限信息: roles: {}",roles);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(roles);
        return info;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		log.info("验证当前Subject时获取到token为：" + token.toString());
        String username = upToken.getUsername();
        String password = new String((char[]) token.getCredentials());
        System.out.println(password);
        if (username == null) {
            throw new AccountException("用户名不能为空");
        }
//        System.out.println(new QueryWrapper<User>().eq("username", username));
        
//        User userDB = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        User userDB = userService.selectById(username);
//        User userDB = userMapper.selectOne("com.main.mapper.UserMapper.selectById", username);
//        User userDB = userMapper.queryForLogin(username, psw);
        if (userDB == null) {
            throw new UnknownAccountException("找不到用户（"+username+"）的帐号信息");
        }
//        String md5Pwd = new Md5Hash(password, username).toHex();
//        System.out.println(md5Pwd);
        ByteSource salt = ByteSource.Util.bytes(username);
        System.out.println("密码");
        System.out.println(userDB.getPassword());
        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
        //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
//        Set<User> roles = IUserService.getRolesByUserId(userDB.getUname());
//        userDB.getRoles().addAll(roles);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDB, userDB.getPassword(), salt, getName());
        if (userDB.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(userDB.getSalt()));
        }
        return info;
	}
	
}
