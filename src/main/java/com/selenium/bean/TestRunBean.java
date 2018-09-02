package com.selenium.bean;

import java.util.Date;

public class TestRunBean extends TestCaseBean {

	private String testRunID;
	private String testRunBy;
	private Date startTime;
	private Date endTime;
	private SeleniumAutomationStatus status;
	private String title;
	private String machineName;
	private String browser;
	private String testCaseId;
	private String propertySet;
	
	public String getPropertySet() {
		return propertySet;
	}

	public void setPropertySet(String propertySet) {
		this.propertySet = propertySet;
	}

	public SeleniumAutomationStatus getStatus() {
		return status;
	}

	public void setStatus(SeleniumAutomationStatus status) {
		this.status = status;
	}

	public String getTestRunID() {
		return testRunID;
	}

	public void setTestRunID(String testRunID) {
		this.testRunID = testRunID;
	}

	public String getTestRunBy() {
		return testRunBy;
	}

	public void setTestRunBy(String testRunBy) {
		this.testRunBy = testRunBy;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

}
