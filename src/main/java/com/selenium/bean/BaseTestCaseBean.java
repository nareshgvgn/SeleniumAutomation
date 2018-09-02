package com.selenium.bean;

import java.util.List;

public class BaseTestCaseBean {
	
	private String recordId;
	private long version;
	private List<String> testCaseIds;

	public List<String> getTestCaseIds() {
		return testCaseIds;
	}

	public void setTestCaseIds(List<String> testCaseIds) {
		this.testCaseIds = testCaseIds;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
