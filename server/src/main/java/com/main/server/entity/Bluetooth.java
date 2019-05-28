package com.main.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.*;

@TableName("Bluetooth")
public class Bluetooth {
	
    private String id;	//数据id
    private String x;	//x坐标
    private String y;    //y坐标
    private String uuid;	//设备编号
    private String major;	//设备1信号强度
    private String minor;	//设备2编号
    private String measuredpower;	//设备2信号强度
    private Date timeStamp;		//数据创建时间
    
    public String getId() { 
    	return id; 
    }
    
    public void setId(String id) {
    	this.id =  id; 
    }
    
    public String getX() { 
    	return x; 
    }
    
    public void setX(String x) { 
    	this.x =  x; 
    }
    
    public String getY() { 
    	return y; 
    }
    
    public void setY(String y) { 
    	this.y =  y; 
    }
    
    public String getUuid() { 
    	return uuid; 
    }
    
    public void setUuid(String uuid) { 
    	this.uuid =  uuid; 
    }
    
    public String getMajor() { 
    	return major; 
    }
    
    public void setMajor(String major) { 
    	this.major =  major; 
    }
    
    public String getMinor() { 
    	return minor; 
    }
    
    public void setMinor(String minor) { 
    	this.minor =  minor; 
    }
    
    public String getMeasuredpower() { 
    	return measuredpower; 
    }
    
    public void setMeasuredpower(String measuredpower) { 
    	this.measuredpower =  measuredpower; 
    }
    
    public Date getTimeStamp() { 
    	return timeStamp; 
    }
    
    public void setTimeStamp(Date timeStamp) { 
    	this.timeStamp =  timeStamp; 
    }
}