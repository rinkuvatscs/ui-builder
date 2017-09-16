package com.woocation.ui.builder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woocation.ui.builder.jsonmapper.TemplateDataBinder;
import com.woocation.ui.builder.request.Component;

@Service
public class DataBinderServiceImpl {

	
	@Autowired
	private ComponentServiceImpl componentServiceImpl;
	
	@Autowired
	private TemplateDataBinder templateDataBinder ;
	
	
	public Map<String, Object> bindData(String componentName, Map<String, Object> content)  {
		Component component = componentServiceImpl.findComponent(componentName);
		try {
			return toMap(templateDataBinder.bindDataAndTemplate(new JSONObject(component.getComponentContent()) , new JSONObject(content)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
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
