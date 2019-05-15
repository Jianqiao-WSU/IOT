package com.main.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.*;

@TableName("Environment")
public class Environment {
	
    private String id;	//数据id
    private String temperature;	//气温
    private String humidity;    //湿度
    private String pressure;	//气压
    private Date timeStamp;		//数据创建时间
    
    public String getId() { 
    	return id; 
    }
    
    public void setId(String id) {
    	this.id =  id; 
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
    
    public Date getTimeStamp() { 
    	return timeStamp; 
    }
    
    public void setTimeStamp(Date timeStamp) { 
    	this.timeStamp =  timeStamp; 
    }
}