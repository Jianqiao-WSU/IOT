package com.main.server.config;

import com.main.server.shiro.UserRealm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class ShiroConfig {

	
	@Bean
	public FilterRegistrationBean delegatingFilterProxy(){
	    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	    DelegatingFilterProxy proxy = new DelegatingFilterProxy();
	    proxy.setTargetFilterLifecycle(true);
	    proxy.setTargetBeanName("shiroFilter");
	    filterRegistrationBean.setFilter(proxy);
	    return filterRegistrationBean;
	}
//	@Bean
//	public FilterRegistrationBean delegatingFilterProxy(){
//	    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//	    DelegatingFilterProxy proxy = new DelegatingFilterProxy();
//	    proxy.setTargetFilterLifecycle(true);
//	    proxy.setTargetBeanName("shiroFilter");
//	    filterRegistrationBean.setFilter(proxy);
//	    return filterRegistrationBean;
//	}
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shirFilter(org.apache.shiro.mgt.SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter()");  
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/main");
		// 配置数据库中的resource
		return shiroFilterFactoryBean;
	}
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法, 与注册时使用的散列算法相同
        hashedCredentialsMatcher.setHashIterations(1);// 散列次数, 与注册时使用的散列册数相同
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);// 生成16进制, 与注册时的生成格式相同
        return hashedCredentialsMatcher;
    }
    
	@Bean
    public Realm realm() {
		UserRealm userRealm = new UserRealm();
        // 设置加密算法
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
//        return new UserRealm();
    }
	@Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
	
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        creator.setUsePrefix(true);
        return creator;
    }

    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录。
     * 这里只做鉴权，不做权限控制，因为权限用注解来做。
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        //哪些请求可以匿名访问
        chain.addPathDefinition("/login", "anon");
        chain.addPathDefinition("/**", "anon");
        chain.addPathDefinition("/register", "anon");
//        chain.addPathDefinition("/page/401", "anon");
//        chain.addPathDefinition("/page/403", "anon");
//        chain.addPathDefinition("/page/index", "anon");

        //除了以上的请求外，其它请求都需要登录
//        chain.addPathDefinition("/**", "authc");
        return chain;
    }
    
    @Bean  
    public DefaultWebSecurityManager securityManager(){  
       DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();  
       List<Realm> realm=new ArrayList<>();
       realm.add(realm());
       securityManager.setRealms(realm);
       return securityManager;  
    }  
    
    @ModelAttribute(name = "subject")
    public Subject subject() {
        return SecurityUtils.getSubject();
    }
}
