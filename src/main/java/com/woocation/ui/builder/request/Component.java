package com.woocation.ui.builder.request;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "component")
public class Component {

	public Component() {
		super();
	}

	public Component(String componentName, String componentContent) {
		this.componentName = componentName;
		this.componentContent = componentContent;
	}

	@Id
	private String id;

	@Indexed(unique = true)
	private String componentName;

	private String componentContent;

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

	public String getComponentContent() {
		return componentContent;
	}

	public void setComponentContent(String componentContent) {
		this.componentContent = componentContent;
	}

}
