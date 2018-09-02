package com.selenium.bean;

public class TestCaseResultBean extends TestCaseBean {
	private String testCaseId;
	private String testRunID;
	private SeleniumAutomationStatus status;

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String getTestRunID() {
		return testRunID;
	}

	public void setTestRunID(String testRunID) {
		this.testRunID = testRunID;
	}

	public SeleniumAutomationStatus getStatus() {
		return status;
	}

	public void setStatus(SeleniumAutomationStatus status) {
		this.status = status;
	}
}
