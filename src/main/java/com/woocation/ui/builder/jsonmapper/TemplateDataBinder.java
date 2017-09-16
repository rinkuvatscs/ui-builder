package com.woocation.ui.builder.jsonmapper;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class TemplateDataBinder {

	private static final String WOOCATION_LIST ="woocationList_";
	
	public JSONObject bindDataAndTemplate(JSONObject template , JSONObject data) {
		JSONObject responseData = new JSONObject() ;
		Iterator templatekeys = template.keys();
		
		while(templatekeys.hasNext()) {
		String key = (String) templatekeys.next();
		if(key.startsWith(WOOCATION_LIST) ) {
			String actualkey = key.substring(14 , key.length());
			try{
			JSONArray jsonArray = data.getJSONArray(actualkey);
			JSONArray newJsonArray = new JSONArray() ;
			for(int jsonArrayCount =0 ; jsonArrayCount < jsonArray.length() ;jsonArrayCount++) {
				newJsonArray.put(jsonArrayCount , bindDataAndTemplate(template.getJSONObject(key) ,jsonArray.getJSONObject(jsonArrayCount)));
			}
			if(newJsonArray.length() > 0) {
				responseData.put(actualkey, newJsonArray);	
			}
			
			}catch(JSONException jsonException ){
				System.out.println("Key "+actualkey +" Not found in data");
				//responseData.NULL ;
			}
		} else if(template.opt(key) instanceof JSONObject) {
			try{
			responseData.put(key, bindDataAndTemplate(template.getJSONObject(key) , data.getJSONObject(key)));
			}catch(JSONException jsonException  ){
				System.out.println("Key "+key +" Not found in data");
				//responseData = null ;
			}
		} else{
			try{
			responseData.put(key, data.get(key));
			}catch(JSONException jsonException  ){
				System.out.println("Key "+key +" Not found in data so putting default value from template");
				responseData.put(key, template.get(key));
			}
			}
		}
		
		return responseData ;
	}
	
}
