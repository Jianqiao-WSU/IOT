package com.main.server.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;

import com.main.server.entity.Bluetooth;
import com.main.server.service.*;

@RestController
@RequestMapping("/api")
public class BluetoothController {
	
	@Autowired
    private IBluetoothService bluetoothService;
	
	HttpServletResponse responce;
	
    @GetMapping(path = "/bluetooth")
    public List<Bluetooth> getBluetoothInfo(){
        return bluetoothService.findAll();
    }
}
