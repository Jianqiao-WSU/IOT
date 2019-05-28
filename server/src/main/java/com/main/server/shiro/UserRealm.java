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
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
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
        User userDB = userService.selectById(username);
        if (userDB == null) {
            throw new UnknownAccountException("找不到用户（"+username+"）的帐号信息");
        }
        ByteSource salt = ByteSource.Util.bytes(username);
        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方就能拿出用户的所有信息，包括角色和权限
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDB, userDB.getPassword(), salt, getName());
        if (userDB.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(userDB.getSalt()));
        }
        return info;
	}
	
}
