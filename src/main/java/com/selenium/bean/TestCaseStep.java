package com.selenium.bean;

public class TestCaseStep extends AbstractSeleniumBean {
	private String testStepId;
	private String testCaseId;
	private Integer sequenceNo;
	private String keyword;
	private String description;
	private String inputData;
	private String objectName;
	private String objectType;
	private String parentRecordKey;
	
	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getTestStepId() {
		return testStepId;
	}

	public void setTestStepId(String testStepId) {
		this.testStepId = testStepId;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public Integer getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInputData() {
		return inputData;
	}

	public void setInputData(String inputData) {
		this.inputData = inputData;
	}

	public String getParentRecordKey() {
		return parentRecordKey;
	}

	public void setParentRecordKey(String parentRecordKey) {
		this.parentRecordKey = parentRecordKey;
	}
}
