package com.woocation.ui.builder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.mongodb.DuplicateKeyException;
import com.woocation.ui.builder.constants.WoocationType;
import com.woocation.ui.builder.exception.CustomException;
import com.woocation.ui.builder.repository.ComponentRepository;
import com.woocation.ui.builder.request.Component;

@org.springframework.stereotype.Component
public class ComponentServiceImpl {

	@Autowired
	private ComponentRepository componentRepository;

	public Component addComponent(Component component) {
		try {
			return componentRepository.save(component);
		}  catch (Exception exception) {
			if (exception instanceof org.springframework.dao.DuplicateKeyException) {
				throw new CustomException(
						WoocationType.DUPLICATE_KEY_EXCEPTION.getMessage());
			}
			throw new CustomException(
					WoocationType.UNABLE_TO_ADD_COMPONENT.getMessage());
		}
	}

	public Component deleteComponent(String componentName) {
		try {
			Component component = componentRepository
					.findByComponentName(componentName);
			if (StringUtils.isEmpty(component)) {
				return null;
			}
			componentRepository.delete(component);
			return component;
		} catch (Exception exception) {
			throw new CustomException(
					WoocationType.UNABLE_TO_DELETE_COMPONENT.getMessage());
		}
	}
}
