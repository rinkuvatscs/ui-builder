package com.woocation.ui.builder.controller;

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
@RequestMapping(value = "/woocation/component" , produces ="application/json")
public class ComponentController {

	@Autowired
	private ComponentServiceImpl componentServiceImpl;

	@RequestMapping(value = "/", method = RequestMethod.POST , consumes = "text/plain" )
	@ResponseBody
	public Component addComponent(
			@RequestParam(name = "componentName") String componentName,
			@RequestBody String componentContent) {

		return componentServiceImpl.addComponent(new Component(componentName,
				componentContent));

	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	@ResponseBody
	public Component deleteComponent(
			@RequestParam(name = "componentName") String componentName) {

		return componentServiceImpl.deleteComponent(componentName);

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public Component getComponent(
			@RequestParam(name = "componentName") String componentName) {

		return componentServiceImpl.findComponent(componentName);

	}

	@RequestMapping(value = "/", method = RequestMethod.PUT , consumes = "text/plain")
	@ResponseBody
	public Component updateComponent(
			@RequestParam(name = "componentName") String componentName,
			@RequestBody String componentContent) {

		return componentServiceImpl.updateComponent(new Component(
				componentName, componentContent));

	}
}
