package com.selenium.bean;

import java.util.Map;

public class CommonBean {
	private String value;
	private String description;
	private Map<String, Object> objMap;
	private String value1;
	private String value2;
	private String value3;
	public CommonBean()
	{
		
	}
	public CommonBean(String value, String description)
	{
		this.value = value;
		this.description = description;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Map<String, Object> getObjMap() {
		return objMap;
	}
	public void setObjMap(Map<String, Object> objMap) {
		this.objMap = objMap;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
}
