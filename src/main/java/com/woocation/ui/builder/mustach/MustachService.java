package com.woocation.ui.builder.mustach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.woocation.ui.builder.jsonmapper.ProteusClient;
import com.woocation.ui.builder.jsonmapper.Transformer;
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

	public String  replaceContent1(String componentName, Map<String, Object> content)  {
		Component component = componentServiceImpl.findComponent(componentName);
		try {
			return combindTemplateAndJsonDataString(new JSONObject(content), new JSONObject(component.getComponentContent()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public Map<String, Object> combindTemplateAndJsonData(JSONObject dataJson, JSONObject template) throws JSONException {
		
		System.out.println("111===============");
			
		
		JSONObject data = Transformer.transformImpl(dataJson, template);
		System.out.println(data.toString(3));
		System.out.println("222===============");
		Map<String, Object> test = toMap(data);
		
		System.out.println("After Map ======");
		System.out.println("Map "+test);
		
		return test;
		
	}
	
	
public String combindTemplateAndJsonDataString(JSONObject dataJson, JSONObject template) throws JSONException {
		
		System.out.println("111===============");
			
		
		JSONObject data = Transformer.transformImpl(dataJson, template);
		System.out.println(data.toString(3));
		System.out.println("222===============");
		Map<String, Object> test = toMap(data);
		
		System.out.println("After Map ======");
		System.out.println("Map "+test);
		
		return data.toString(3);
		
	}
	
	
	public  Map<String, Object> toMap(JSONObject object) throws JSONException {
	    Map<String, Object> map = new HashMap<String, Object>();

	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);

	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}
	
	public  List<Object> toList(JSONArray array) throws JSONException {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.length(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }
	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	  }
}
