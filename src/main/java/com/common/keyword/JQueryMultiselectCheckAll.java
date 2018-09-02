package com.common.keyword;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JQueryMultiselectCheckAll
{
	public static void execute(WebDriver driver, String element)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String inject ="$('#:ELEMENT_ID').multiselect('checkAll');";
		inject = inject.replace(":ELEMENT_ID", element);
		jse.executeScript(inject);
	}
}
