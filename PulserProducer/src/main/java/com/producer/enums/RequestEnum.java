package com.producer.enums;

public enum RequestEnum {
	
	NEW("NEW"),
	PUBLISHED("PUBLISHED");

	private String value;
	RequestEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
