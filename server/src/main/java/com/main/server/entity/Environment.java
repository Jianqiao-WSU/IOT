package com.main.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.*;

@TableName("Environment")
public class Environment {
	
    private String deviceId;	//数据id
    private String temperature;	//气温
    private String humidity;    //湿度
    private String pressure;	//气压
    private String RSSI;
    private String sun;
    private String time;		//数据创建时间
    
    
    public String getDeviceId() { 
    	return deviceId; 
    }
    
    public void setDeviceId(String deviceId) {
    	this.deviceId =  deviceId; 
    }
    
    public String getTemperature() { 
    	return temperature; 
    }
    
    public void setTemperature(String temperature) { 
    	this.temperature =  temperature; 
    }
    
    public String getHumidity() { 
    	return humidity; 
    }
    
    public void setHumidity(String humidity) { 
    	this.humidity =  humidity; 
    }
    
    public String getPressure() { 
    	return pressure; 
    }
    
    public void setPressure(String pressure) { 
    	this.pressure =  pressure; 
    }
    
    public String getRSSI() { 
    	return RSSI; 
    }
    
    public void setRSSI(String RSSI) { 
    	this.RSSI =  RSSI; 
    }
    
    public String getSun() { 
    	return sun; 
    }
    
    public void setSun(String sun) { 
    	this.sun =  sun; 
    }
    
    public String getTime() { 
    	return time; 
    }
    
    public void setTime(String time) { 
    	this.time =  time; 
    }
}