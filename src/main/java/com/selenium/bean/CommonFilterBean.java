package com.selenium.bean;

import java.util.List;

public class CommonFilterBean {
	
	private Integer currentPage;
	private List<String> testCaseIds;
	
	 public Integer getCurrentPage() {
	        return currentPage;
	    }

	    public void setCurrentPage(Integer currentPage) {
	        this.currentPage = currentPage;
	    }
	    
	    public List<String> getTestCaseIds() {
			return testCaseIds;
		}

		public void setTestCaseIds(List<String> testCaseIds) {
			this.testCaseIds = testCaseIds;
		}
}
