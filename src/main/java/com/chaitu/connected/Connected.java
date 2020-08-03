package com.chaitu.connected;

public enum Connected {
	YES("Yes"),
	NO("No");
	
	private String value;

	Connected(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
