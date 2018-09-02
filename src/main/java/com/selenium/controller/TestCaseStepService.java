package com.selenium.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.selenium.bean.ActionError;
import com.selenium.bean.ActionResult;
import com.selenium.bean.KeywordBean;
import com.selenium.bean.TestCaseBean;
import com.selenium.bean.TestCaseStep;
import com.selenium.dao.KeywordExecuterDao;
import com.selenium.dao.TestCaseStepDao;
import com.selenium.dao.UtilDao;
import com.selenium.util.CompileSourceInMemory;
import com.selenium.validator.TestCaseStepValidator;

@RestController
public class TestCaseStepService {

	@Autowired
	private TestCaseStepDao testCaseStepDao;

	@Autowired
	UtilDao utilDao;

	@Autowired
	KeywordExecuterDao keywordExecuterDao;

	@Autowired
	TestCaseStepValidator validator;

	@Autowired
	@Qualifier("messageSource")
	ReloadableResourceBundleMessageSource resourceBundle;

	@RequestMapping("/addTestCaseStep.json")
	public @ResponseBody
	ActionResult addTestCase(@RequestBody TestCaseStep testCaseBean) {
		ActionResult actionResult = new ActionResult();
		ActionError error = new ActionError();
		actionResult.setStatus("SUCCESS");
		BindException errors = new BindException(testCaseBean,
				"testCaseStepBean");
		testCaseBean.setRecordId(utilDao.getUniqueId());
		validator.validate(testCaseBean, errors);
		if (!errors.hasErrors())
			testCaseStepDao.addRecord(testCaseBean);
		else {
			actionResult.setStatus("ERROR");
			for (ObjectError objError : errors.getAllErrors()) {
				error = new ActionError();
				error.setErrorMessage(resourceBundle.getMessage(
						objError.getCode(), objError.getArguments(), null));
				actionResult.addError(error);
			}
		}
		return actionResult;
	}

	@RequestMapping("/testCaseStepList.json")
	public @ResponseBody
	Map<String, Object> testCaseList(
			@RequestBody TestCaseFilter testCaseFilterBean) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<TestCaseStep> list = new ArrayList<TestCaseStep>();
		list = testCaseStepDao.readTestCaseSteps(testCaseFilterBean
				.getTestCaseId());
		result.put("list", list);
		if (list != null)
			result.put("totalRows", list.size());
		return result;
	}

	@RequestMapping("/readTestCaseStep.json")
	public @ResponseBody
	TestCaseStep readTestCaseStep(@RequestBody TestCaseStep bean) {
		TestCaseStep result = testCaseStepDao.readRecord(bean.getTestStepId());
		return result;
	}

	@RequestMapping("/updateTestCaseStep.json")
	public @ResponseBody
	ActionResult updateTestCase(@RequestBody TestCaseStep testCaseBean) {
		ActionResult actionResult = new ActionResult();
		ActionError error = new ActionError();
		actionResult.setStatus("SUCCESS");
		BindException errors = new BindException(testCaseBean,
				"testCaseStepBean");
		validator.validate(testCaseBean, errors);
		if (!errors.hasErrors())
			testCaseStepDao.updateRecord(testCaseBean);
		else {
			actionResult.setStatus("ERROR");
			for (ObjectError objError : errors.getAllErrors()) {
				error = new ActionError();
				error.setErrorMessage(resourceBundle.getMessage(
						objError.getCode(), objError.getArguments(), null));
				actionResult.addError(error);
			}
		}
		return actionResult;
	}

	@RequestMapping("/deleteTestCaseStep/{recordID}.json")
	public @ResponseBody
	ActionResult deleteTestCase(
			@PathVariable(value = "recordID") String recordID) {

		ActionResult actionResult = new ActionResult();
		ActionError error = new ActionError();
		actionResult.setStatus("SUCCESS");
		TestCaseBean testCaseBean = new TestCaseBean();
		BindException errors = new BindException(testCaseBean, "testCaseBean");
		if (!errors.hasErrors())
			testCaseStepDao.deleteRecord(recordID);
		else {
			actionResult.setStatus("ERROR");
			for (ObjectError objError : errors.getAllErrors()) {
				error = new ActionError();
				error.setErrorMessage(resourceBundle.getMessage(
						objError.getCode(), objError.getArguments(), null));
				actionResult.addError(error);
			}
		}
		return actionResult;
	}

	@RequestMapping("/dummy.json")
	public @ResponseBody
	TestCaseStep executeKeyword() {
		try {
			String exePath = "/Drivers/chromedriver.exe";
			boolean testCaseSuccess = true;
			boolean success = true;
			System.setProperty("webdriver.chrome.driver", exePath);
			KeywordBean kBean = keywordExecuterDao.getJavaCode("CLICK_BTN");
			String javaCode = kBean.getJavaCode();
			WebDriver webdriver = new ChromeDriver();
			CompileSourceInMemory.executeJavaCode(javaCode,
					kBean.getClassName(), webdriver);
		} catch (Exception exc) {

		}
		return null;
	}

}
