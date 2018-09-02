package com.selenium.bean;

import java.util.List;

public class TestCaseBean<T extends AbstractSeleniumBean> extends AbstractSeleniumBean {

	private String testCaseId;
	private int priority;
	private String moduleName;
	private String testDesinedBy;
	private String testCaseTitle;
	private String testcaseDescription;
	private List<String> testcasesPreExecute;
	private String projectId;
	private String projectName;
	private List<TestCaseStep> lstSteps;
	private Integer sequenceNo;
	
	public Integer getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getTestDesinedBy() {
		return testDesinedBy;
	}

	public void setTestDesinedBy(String testDesinedBy) {
		this.testDesinedBy = testDesinedBy;
	}

	public String getTestCaseTitle() {
		return testCaseTitle;
	}

	public void setTestCaseTitle(String testCaseTitle) {
		this.testCaseTitle = testCaseTitle;
	}

	public String getTestcaseDescription() {
		return testcaseDescription;
	}

	public void setTestcaseDescription(String testcaseDescription) {
		this.testcaseDescription = testcaseDescription;
	}

	public List<String> getTestcasesPreExecute() {
		return testcasesPreExecute;
	}

	public void setTestcasesPreExecute(List<String> testcasesPreExecute) {
		this.testcasesPreExecute = testcasesPreExecute;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String project) {
		this.projectId = project;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<TestCaseStep> getLstTestSteps() {
		return lstSteps;
	}

	public void setLstTestSteps(List<TestCaseStep> lstSteps) {
		this.lstSteps = lstSteps;
	}
}
