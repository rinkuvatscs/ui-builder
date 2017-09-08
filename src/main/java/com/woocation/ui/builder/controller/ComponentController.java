package com.woocation.ui.builder.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.woocation.ui.builder.request.Component;
import com.woocation.ui.builder.service.impl.ComponentServiceImpl;

@RestController
@RequestMapping("/woocation/component")
public class ComponentController {

	@Autowired
	private ComponentServiceImpl componentServiceImpl;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public Component addComponent(
			@RequestParam(name = "componentName") String componentName,
			@RequestBody Map<String, Object> componentContent) {

		return componentServiceImpl.addComponent(new Component(componentName,
				componentContent));

	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	@ResponseBody
	public Component delete(
			@RequestParam(name = "componentName") String componentName) {

		return componentServiceImpl.deleteComponent(componentName);

	}

}
