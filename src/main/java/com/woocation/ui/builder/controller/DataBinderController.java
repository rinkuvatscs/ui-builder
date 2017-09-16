package com.woocation.ui.builder.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.woocation.ui.builder.service.impl.DataBinderServiceImpl;


@RequestMapping(value = "/databinder/")
@RestController
public class DataBinderController {
	
	@Autowired
	private DataBinderServiceImpl dataBinderServiceImpl ; 
	
	@RequestMapping(value = "/binddata" , method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> replaceContent(String componentName , @RequestBody Map<String,Object> data)  {
		 return dataBinderServiceImpl.bindData(componentName, data) ;
	}


}
