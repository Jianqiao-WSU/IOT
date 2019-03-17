package com.main.dao;

public class User {
	//用户id
    private String userID;
    //用户密码
    private String password;
    
    public String getID() { return userID; }
    
    public void setID(String userID) { this.userID =  userID; }
    
    public String getPSW() { return password; }
    
    public void setPSW(String password) { this.password =  password; }
}
