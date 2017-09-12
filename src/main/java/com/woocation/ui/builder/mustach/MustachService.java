package com.woocation.ui.builder.mustach;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.woocation.ui.builder.jsonmapper.ProteusClient;
import com.woocation.ui.builder.request.Component;
import com.woocation.ui.builder.service.impl.ComponentServiceImpl;
@org.springframework.stereotype.Component
public class MustachService {

	@Autowired
	private ComponentServiceImpl componentServiceImpl;

	public Map<String, Object> replaceContent(String componentName, Map<String, Object> content)  {
		Component component = componentServiceImpl.findComponent(componentName);
		try {
			return combindTemplateAndJsonData(new JSONObject(content), new JSONObject(component.getComponentContent()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}


	public Map<String, Object> combindTemplateAndJsonData(JSONObject dataJson, JSONObject template) throws JSONException {
		Map<String,Object> response = new HashMap<String, Object>() ;
		ProteusClient client = ProteusClient.getInstance();
		JSONObject data = client.transform(dataJson, template);;
		System.out.println(data.toString(3));
		
		Iterator<String> keys = data.keys();
		while(keys.hasNext()) {
			String key = keys.next() ;
			response.put(key , data.get(key));
		}
		
		return  response ;
	}
}
