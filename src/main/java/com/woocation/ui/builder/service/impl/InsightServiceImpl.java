package com.woocation.ui.builder.service.impl;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woocation.ui.builder.jsonmapper.TemplateDataBinder;
import com.woocation.ui.builder.repository.InsightRepository;
import com.woocation.ui.builder.request.Insight;

@Component
public class InsightServiceImpl {

	@Autowired
	private InsightRepository insightRepository;
	
	@Autowired
	private DataBinderServiceImpl dataBinderServiceImpl ; 

	@Autowired
	TemplateDataBinder templateDataBinder ; 
	
	public Insight findbyInsightName(String insightName) {
		return insightRepository.findByInsightName(insightName);
	}

	public Insight saveInsight(Insight insight) {
		return insightRepository.save(insight);
	}
	
	public Map<String,Object> mergeContent(String insightName , Map<String,Object> insightContent) {
		return dataBinderServiceImpl.toMap(templateDataBinder.bindDataAndTemplateWithMapper(insightName, new JSONObject(insightContent)));
	}
	
	public Insight deleteInsight(String insightName) {
		Insight insight = insightRepository.findByInsightName(insightName);
		if(insight != null) {
			 insightRepository.delete(insight);
		}
		return insight;
	}
}
