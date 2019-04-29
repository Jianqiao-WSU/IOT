package com.main.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.*;

@TableName("user")
public class User {
	
    private String username;	//用户id
    private String password;	//用户密码
    private String salt;    // 加密盐值
    private String nickname;	//用户昵称
    private Date created;		//用户创建时间
    private Date updated;		//用户更新时间
    private String authority;	//用户权限
    private String resetPSW;    //用户重置密码
    
    public String getUsername() { 
    	return username; 
    }
    
    public void setUsername(String username) {
    	this.username =  username; 
    }
    
    public String getPassword() { 
    	return password; 
    }
    
    public void setPassword(String password) { 
    	this.password =  password; 
    }
    
    public String getNickname() { 
    	return nickname; 
    }
    
    public void setNickname(String nickname) { 
    	this.nickname =  nickname; 
    }
    
    public Date getCreated() { 
    	return created; 
    }
    
    public void setCreated(Date created) { 
    	this.created =  created; 
    }
    
    
    public Date getUpdated() { 
    	return updated; 
    }
    
    public void setUpdated(Date updated) { 
    	this.updated =  updated; 
    }
    

    public String getSalt() {
        return salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    } 
    
    public String getAuthority() { 
    	return authority; 
    }
    
    public void setAuthority(String authority) { 
    	this.authority =  authority; 
    }
    
    public String getResetPSW() { 
    	return resetPSW; 
    }
    
    public void setResetPSW(String resetPSW) { 
    	this.resetPSW =  resetPSW; 
    }
}
