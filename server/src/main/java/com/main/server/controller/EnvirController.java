package com.main.server.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;

import com.main.server.entity.Environment;
import com.main.server.service.*;

@RestController
@RequestMapping("/api")
public class EnvirController {
//	private static final Logger log = LoggerFactory.getLogger(EnvirController.class);
	
	@Autowired
    private IEnvirService envirService;
	
	HttpServletResponse responce;
	
    @GetMapping(path = "/environment")
    public List<Environment> getAllStudentInfo(){
        return envirService.findAll();
    }
}
