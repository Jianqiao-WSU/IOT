package com.main.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.*;

@TableName("Bluetooth")
public class Bluetooth {
	
    private String BT_id;	//数据id
    private String x;	//x坐标
    private String y;    //y坐标
    private String beacon1;	//设备1编号
    private String rssi1;	//设备1信号强度
    private String beacon2;	//设备2编号
    private String rssi2;	//设备2信号强度
    private String beacon3;	//设备3编号
    private String rssi3;	//设备3信号强度
    private Date timeStamp;		//数据创建时间
    
    public String getBT_id() { 
    	return BT_id; 
    }
    
    public void setBT_id(String BT_id) {
    	this.BT_id =  BT_id; 
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
    
    public String getBeacon1() { 
    	return beacon1; 
    }
    
    public void setBeacon1(String beacon1) { 
    	this.beacon1 =  beacon1; 
    }
    
    public String getRssi1() { 
    	return rssi1; 
    }
    
    public void setRssi1(String rssi1) { 
    	this.rssi1 =  rssi1; 
    }
    
    public String getBeacon2() { 
    	return beacon2; 
    }
    
    public void setBeacon2(String beacon2) { 
    	this.beacon2 =  beacon2; 
    }
    
    public String getRssi2() { 
    	return rssi2; 
    }
    
    public void setRssi2(String rssi2) { 
    	this.rssi2 =  rssi2; 
    }
    
    public String getBeacon3() { 
    	return beacon3; 
    }
    
    public void setBeacon3(String beacon3) { 
    	this.beacon3 =  beacon3; 
    }
    
    public String getRssi3() { 
    	return rssi3; 
    }
    
    public void setRssi3(String rssi3) { 
    	this.rssi3 =  rssi3; 
    }
    public Date getTimeStamp() { 
    	return timeStamp; 
    }
    
    public void setTimeStamp(Date timeStamp) { 
    	this.timeStamp =  timeStamp; 
    }
}