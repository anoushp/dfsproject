package com.spring.test.web.dao;

public enum MaturityCompliance {
	FULLY(0,"Fully"), PARTIALLY(1,"Partially");
	

    private MaturityCompliance(int value, String key) {
		this.value = value;
		this.key = key;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	private int value;
    private String key;

}
