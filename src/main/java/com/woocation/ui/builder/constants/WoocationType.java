package com.woocation.ui.builder.constants;

public enum WoocationType {

	UNABLE_TO_DELETE_COMPONENT(
			"Unable to delete component. Please contact to an Administrator"), UNABLE_TO_ADD_COMPONENT(
			"Unable to add component. Please contact to an Administrator"),
			DUPLICATE_KEY_EXCEPTION(
					"Component Name Already exist. Please choose differnt one");

	private String message;

	private WoocationType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}
