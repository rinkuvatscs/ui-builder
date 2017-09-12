package com.woocation.ui.builder.request;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "component")
@JsonAutoDetect(fieldVisibility = Visibility.ANY  )
public class Component 
{

	public Component() {
		super();
	}

	public Component(String componentName, Map<String, Object> componentContent) {
		this.componentName = componentName;
		this.componentContent = componentContent;
	}

	@Id
	private String id;

	@Indexed(unique = true)
	private String componentName;

	@JsonIgnore 
	private Map<String, Object> componentContent;

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getComponentContent() {
		return componentContent;
	}

	public void setComponentContent(Map<String, Object> componentContent) {
		this.componentContent = componentContent;
	}

}
