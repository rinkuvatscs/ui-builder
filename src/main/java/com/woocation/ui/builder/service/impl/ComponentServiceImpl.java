package com.woocation.ui.builder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

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
		} catch (Exception exception) {
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

	public Component findComponent(String componentName) {
		try {
			Component component = componentRepository
					.findByComponentName(componentName);
			if (StringUtils.isEmpty(component)) {
				throw new CustomException(
						WoocationType.COMPONENT_NOT_EXIST.getMessage());
			}
			return component;
		} catch (Exception exception) {
			throw new CustomException(
					WoocationType.UNABLE_TO_FIND_COMPONENT.getMessage());
		}
	}

	public Component updateComponent(Component component) {
		try {
			Component componentTemp = componentRepository
					.findByComponentName(component.getComponentName());
			if (StringUtils.isEmpty(component)) {
				throw new CustomException(
						WoocationType.COMPONENT_NOT_EXIST.getMessage());
			}
			componentTemp.setComponentContent(component.getComponentContent());
			return componentRepository.save(componentTemp);
		} catch (Exception exception) {
			throw new CustomException(
					WoocationType.UNABLE_TO_UPDATE_COMPONENT.getMessage());
		}

	}
}
