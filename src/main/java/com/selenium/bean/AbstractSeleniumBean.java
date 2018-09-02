package com.selenium.bean;

import java.util.List;

public abstract class AbstractSeleniumBean {
	
	private String recordId;
	private long version;
	private List<String> testCaseIds;
	private String type;
	
	public String getType() {
		if(this instanceof TestCaseBean)
			this.type = "TESTCASE";
		if(this instanceof TestCaseStep)
			this.type = "TESTSTEP";
		return this.type;
	}

	public void setType(String type) {
			this.type = type;
	}

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
