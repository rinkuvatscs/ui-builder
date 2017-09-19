package com.woocation.ui.builder.jsonmapper;

import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woocation.ui.builder.repository.ComponentRepository;
import com.woocation.ui.builder.repository.InsightRepository;
import com.woocation.ui.builder.request.Insight;

@Component
public class TemplateDataBinder {

	@Autowired
	private InsightRepository insightRepository;

	@Autowired
	private ComponentRepository componentRepository;

	private static final String WOOCATION_LIST = "woocationList_";

	private static final String WOOCATION_TEXT = "woocationText_";

	private static final String INSIGHT = "insights";

	private static final String TEMPLATE = "template";

	public JSONObject bindDataAndTemplate(JSONObject template, JSONObject data) {
		JSONObject responseData = new JSONObject();
		Iterator templatekeys = template.keys();

		while (templatekeys.hasNext()) {
			String key = (String) templatekeys.next();
			if (key.startsWith(WOOCATION_LIST)) {
				String actualkey = key.substring(14, key.length());
				try {
					JSONArray jsonArray = data.getJSONArray(actualkey);
					JSONArray newJsonArray = new JSONArray();
					for (int jsonArrayCount = 0; jsonArrayCount < jsonArray.length(); jsonArrayCount++) {
						newJsonArray.put(jsonArrayCount, bindDataAndTemplate(template.getJSONObject(key),
								jsonArray.getJSONObject(jsonArrayCount)));
					}
					if (newJsonArray.length() > 0) {
						responseData.put(actualkey, newJsonArray);
					}

				} catch (JSONException jsonException) {
					System.out.println("Key " + actualkey + " Not found in data");
					// responseData.NULL ;
				}
			} else if (template.opt(key) instanceof JSONObject) {
				try {
					responseData.put(key, bindDataAndTemplate(template.getJSONObject(key), data.getJSONObject(key)));
				} catch (JSONException jsonException) {
					System.out.println("Key " + key + " Not found in data");
					// responseData = null ;
				}
			} else {
				try {
					responseData.put(key, data.get(key));
				} catch (JSONException jsonException) {
					System.out.println("Key " + key + " Not found in data so putting default value from template");
					responseData.put(key, template.get(key));
				}
			}
		}

		return responseData;
	}

	// 0 1 2

	/**
	 * 
	 * @param template
	 *            object is for one insight only
	 * @param data
	 *            object is page specific
	 * @return
	 */
	public JSONObject bindDataAndTemplateWithMapper(String insightName, JSONObject data) {
		JSONObject responseData = new JSONObject();
		JSONArray newJsonArray = new JSONArray();
		Insight insight = insightRepository.findByInsightName(insightName);

		for (int i = 0; i < insight.getComponents().size(); i++) {
			Map<String, String> replacementContent = insight.getInsightContent().get(insight.getComponents().get(i));
			String componentName = null;
			if (insight.getComponents().get(i).contains("-")) {
				String[] splitData = insight.getComponents().get(i).split("-");
				componentName = splitData[0];
			} else {
				componentName = insight.getComponents().get(i);
			}
			com.woocation.ui.builder.request.Component component = componentRepository
					.findByComponentName(componentName);
			JSONObject componentJsonobject = new JSONObject(component.getComponentContent());

			JSONObject responseComponent = convertTemplateToData(componentJsonobject, data, replacementContent);
			responseComponent.put("order", i);
			newJsonArray.put(i, responseComponent);
		}
		
		responseData.put(TEMPLATE, newJsonArray);
		return responseData;
	}

	JSONObject convertTemplateToData(JSONObject componentJsonobject, Object data,
			Map<String, String> replacementContent) {
		JSONObject globalJson = null;
		String globalString = null;
		JSONObject responseData = new JSONObject();
		if (data instanceof JSONObject) {
			globalJson = (JSONObject) data;
		} else if (data instanceof String) {
			globalString = (String) data;
		}

		Iterator<String> componentKey = componentJsonobject.keys();
		while (componentKey.hasNext()) {
			String componentContentKey = componentKey.next();
			if (componentContentKey.startsWith(WOOCATION_TEXT)) {
				String actualkey = componentContentKey.substring(14, componentContentKey.length());
				if (globalString != null) {
					responseData.put(actualkey, globalString);
				} else {
					String travserString = replacementContent
							.get(componentContentKey);
					if (travserString != null) {
						if (travserString.contains("-")) {
							String[] travserPath = travserString.split("-");
							responseData.put(actualkey, findTextInJsonData(travserPath, globalJson));
						} else {
							responseData.put(actualkey, globalJson.get(travserString));
						}
					} else {
						responseData.put(componentContentKey, componentJsonobject.get(componentContentKey));
					}
				}
			} else if (componentContentKey.startsWith(WOOCATION_LIST)) {
				String actualkey = componentContentKey.substring(14, componentContentKey.length());
				JSONObject tempData = componentJsonobject.getJSONObject(componentContentKey);
				JSONArray newJsonArray = new JSONArray();
				String travserString = replacementContent
						.get(componentContentKey);
				if (travserString != null) {
					if (travserString.contains(".")) {
						String[] travserPath = travserString.split(".");
						JSONArray dataJson = findListInJsonData(travserPath, globalJson);
						for (int jsonArrayCount = 0; jsonArrayCount < dataJson.length(); jsonArrayCount++) {
							newJsonArray.put(jsonArrayCount,
									convertTemplateToData(tempData, dataJson.get(jsonArrayCount), replacementContent));
						}
						if (newJsonArray.length() > 0) {
							responseData.put(actualkey, newJsonArray);
						}
					}

				}
			} else if (componentJsonobject.opt(componentContentKey) instanceof JSONObject) {
				responseData.put(componentContentKey, convertTemplateToData(
						componentJsonobject.getJSONObject(componentContentKey), data, replacementContent));
			} else {
				responseData.put(componentContentKey, componentJsonobject.get(componentContentKey));
			}

		}
		return responseData;
	}

	String findTextInJsonData(String[] travserpath, JSONObject data) {

		JSONObject tempObject = data;
		for (int j = 0; j < travserpath.length - 1; j++) {

			tempObject = (JSONObject) tempObject.get(travserpath[j]);

		}
		return tempObject.getString(travserpath[travserpath.length - 1]);
	}

	JSONArray findListInJsonData(String[] travserpath, JSONObject data) {

		JSONObject tempObject = data;
		for (int j = 0; j < travserpath.length - 1; j++) {

			tempObject = (JSONObject) tempObject.get(travserpath[j]);

		}
		return tempObject.getJSONArray(travserpath[travserpath.length - 1]);
	}

}
