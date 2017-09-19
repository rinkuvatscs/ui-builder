package com.woocation.ui.builder.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.woocation.ui.builder.request.Insight;
import com.woocation.ui.builder.service.impl.InsightServiceImpl;

@RestController
@RequestMapping(value = "/woocation/insight" , produces ="application/json")
public class InsightController {
	

	@Autowired
	private InsightServiceImpl insightServiceImpl ;
	
	@RequestMapping(value = "/", method = RequestMethod.POST  )
	@ResponseBody
	public Insight addInsight(
			@RequestBody Insight insight) {

		return insightServiceImpl.saveInsight(insight);
	}
	
	
	@RequestMapping(value = "/merge", method = RequestMethod.POST  )
	@ResponseBody
	public Map<String,Object> mergeInsight(
			 @RequestParam (name ="insightName") String insightName , @RequestBody Map<String,Object> insightContent) {

		return insightServiceImpl.mergeContent(insightName , insightContent);
	}
	

	@RequestMapping(value = "/", method = RequestMethod.DELETE  )
	@ResponseBody
	public Insight deleteInsight(
			 String insightName) {

		return insightServiceImpl.deleteInsight(insightName);
	}
}
