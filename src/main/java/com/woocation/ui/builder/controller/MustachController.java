package com.woocation.ui.builder.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.woocation.ui.builder.mustach.MustachService;



@RestController
@RequestMapping(value ="/mustach")
public class MustachController {
	
	
	@Autowired
	MustachService mustachService ; 
	
	
	@RequestMapping(value = "/replaceContent" , method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> replaceContent(String componentName , @RequestBody Map<String,Object> data)  {
		 return mustachService.replaceContent(componentName, data) ;
	}

	@RequestMapping(value = "/replaceContentString" , method = RequestMethod.POST)
	@ResponseBody
	public String replaceContent1(String componentName , @RequestBody Map<String,Object> data)  {
		 return mustachService.replaceContent1(componentName, data) ;
	}
	
}
