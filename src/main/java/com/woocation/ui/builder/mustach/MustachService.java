package com.woocation.ui.builder.mustach;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import com.woocation.ui.builder.request.Component;
import com.woocation.ui.builder.service.impl.ComponentServiceImpl;

@org.springframework.stereotype.Component
public class MustachService {

	@Autowired
	private ComponentServiceImpl componentServiceImpl;

	public Map<String, Object> replaceContent(String componentName, Map<String, Object> content) {
		Component component = componentServiceImpl.findComponent(componentName);
		Gson gson = new Gson();
		Template tmpl = Mustache.compiler().compile(gson.toJson(component));
		// System.out.println(tmpl.execute(content));
		Map<String, Object> dataNew = gson.fromJson(tmpl.execute(content), Map.class);

		System.out.println(dataNew.get("componentContent"));
		// dataNew.put("componentContent",gson.fromJson(String.valueOf(dataNew.get("componentContent")),
		// Map.class));
		return dataNew;
	}

	Map<String, Object> replaceData(Map<String, Object> template, Map<String, Object> data) {

		template.forEach((key, value) -> {
			if (value instanceof Collection) {

			}
		});

		return null;
	}

}
