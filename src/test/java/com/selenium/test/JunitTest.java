package com.selenium.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.selenium.bean.TestRunBean;
import com.selenium.util.TestSuiteUtility;

public class JunitTest
{
	public static void main(String a[])
	{
		ApplicationContext ctx = new FileSystemXmlApplicationContext("WebContent/WEB-INF/spring-servlet.xml");
		
		TestSuiteUtility testSuiteUtility = ctx.getBean(TestSuiteUtility.class);
		
		try
		{
			TestRunBean trb = new TestRunBean();
			trb.setTestCaseId("TST001");
			trb.setPropertySet("9cdddea5-6");
			testSuiteUtility.startTestRun(trb);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
