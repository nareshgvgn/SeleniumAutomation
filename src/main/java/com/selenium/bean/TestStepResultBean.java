package com.selenium.bean;

public class TestStepResultBean extends TestCaseStep {
	private String testStepId;
	private DetailInfo details;
	private String testRunID;
	private SeleniumAutomationStatus status;
	
	public String getTestStepId() {
		return testStepId;
	}

	public void setTestStepId(String testStepId) {
		this.testStepId = testStepId;
	}

	public DetailInfo getDetails() {
		return details;
	}

	public void setDetails(DetailInfo details) {
		this.details = details;
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
