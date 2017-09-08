package com.woocation.ui.builder.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.woocation.ui.builder.request.Component;

@Repository
public interface ComponentRepository extends MongoRepository<Component	, Integer>{

	//Component deleteComponentByComponentName(String componentName) ;
	
	Component findByComponentName(String componentNane);
	
}
