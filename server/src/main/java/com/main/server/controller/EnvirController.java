package com.main.server.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.main.server.entity.Environment;
import com.main.server.entity.User;
import com.main.server.service.*;

@RestController
@RequestMapping("/api")
public class EnvirController {
//	private static final Logger log = LoggerFactory.getLogger(EnvirController.class);
	
	@Autowired
    private IEnvirService envirService;
	
	HttpServletResponse responce;
	
    @GetMapping(path = "/environment")
    public List<Environment> getEnvironmentInfo(){
        return envirService.findAll();
    }
    
    @ResponseBody
    @RequestMapping(path = "/environmentData", method = RequestMethod.POST)
    public String getByJSON(@RequestBody String jsonParam) {
        // 直接将json信息打印出来
        System.out.println(jsonParam);
        
        JSONArray json = JSONArray.parseArray(jsonParam);
        System.out.println(json);
        if (json.size() > 0) {
			for (int i = 0; i < json.size(); i++) {
				JSONObject hostObject = json.getJSONObject(i); 
				Environment environment = (Environment)JSONObject.toJavaObject(hostObject, Environment.class);
				System.out.println(hostObject);
				
				JSONArray sensors = JSONArray.parseArray(hostObject.get("sensors").toString());
				System.out.println(sensors);
				if (sensors.size() > 0) {
					for (int j = 0; j < sensors.size(); j++) {
						JSONObject sensor = sensors.getJSONObject(j);
						if (sensor.get("temperature") != null) {
							 environment.setTemperature(sensor.get("temperature").toString());
						}
						if (sensor.get("humidity") != null) {
							 environment.setHumidity(sensor.get("humidity").toString());
						}
						if (sensor.get("air_pressure") != null) {
							 environment.setPressure(sensor.get("air_pressure").toString());
						}
						if (sensor.get("illumination") != null) {
							 environment.setSun(sensor.get("illumination").toString());
						}
					}
				}
				boolean success = envirService.insertData(environment);
//				// 得到 每个对象中的属性值
//				Iterator<String> sIterator = hostObject.keySet().iterator();
//				while (sIterator.hasNext()) {
//					//获得参数名
//					String name = sIterator.next();
//					//获得参数值
//					String value = hostObject.getString(name);
//					System.out.println(name + " : " + value);
//				}
			}
		}
        return "SUCCESS";
    }
}
