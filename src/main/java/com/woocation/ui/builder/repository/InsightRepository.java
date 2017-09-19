package com.woocation.ui.builder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.woocation.ui.builder.request.Insight;

@Repository
public interface InsightRepository  extends CrudRepository<Insight	, String>{

	
	Insight findByInsightName(String insightName);
	
}
