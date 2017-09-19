package com.woocation.ui.builder.request;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/*insightName : "Sim_purchage" ,
components:  ["Heading","List","Drop Down" , "Testing"],
componentContents:
                  {
						
                	  "Heading": {
								  "$text" :"insights.sim_purchase.city"
								 }
					  "Compount list": {
	  							"$lists" :"insights.sim_purchase.data.AVAILABLE AT",
	  							 "$text" :"insights.sim_purchase.data.AVAILABILITY"	
	 							 }
	              }
                  
*/
@Document(collection = "insight")
public class Insight {

	
	public  Insight() {
		// TODO Auto-generated constructor stub
	}
	

	
	public Insight(String insightName, List<String> components, Map<String, Map<String, String>> insightContent) {
		super();
		this.insightName = insightName;
		this.components = components;
		this.insightContent = insightContent;
	}



	@Id
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Indexed(unique = true)
	private String insightName ;
	
	private List<String> components ;
	
	private Map<String,Map<String,String>> insightContent ;

	public Map<String, Map<String,String>> getInsightContent() {
		return insightContent;
	}

	public void setInsightContent(Map<String, Map<String,String>> insightContent) {
		this.insightContent = insightContent;
	}

	public String getInsightName() {
		return insightName;
	}

	public void setInsightName(String insightName) {
		this.insightName = insightName;
	}

	public List<String> getComponents() {
		return components;
	}

	public void setComponents(List<String> components) {
		this.components = components;
	}
}
