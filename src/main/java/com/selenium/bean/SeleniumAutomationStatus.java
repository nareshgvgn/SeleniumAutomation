package com.selenium.bean;


public enum SeleniumAutomationStatus {
	INPROGRESS, COMPLETED, FAILED, PASSED;
	
	 public static SeleniumAutomationStatus valueOf(final int position)
    {
        return SeleniumAutomationStatus.values()[position];
    }
}
