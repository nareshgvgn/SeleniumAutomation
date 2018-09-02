package com.selenium.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.selenium.bean.CommonBean;
import com.selenium.bean.Keyword;
import com.selenium.bean.PaginationList;
import com.selenium.bean.TestCaseBean;
import com.selenium.dao.TestCaseDao;
import com.selenium.dao.UtilDao;
import com.selenium.validator.TestCaseValidator;

@RestController
public class TestCaseService {

	@Autowired
	private TestCaseDao testCaseDao;
	
	@Autowired
	UtilDao utilDao;
	
	@Autowired
	TestCaseValidator testCaseValidator;
	
	@Autowired
	@Qualifier("messageSource")
	ReloadableResourceBundleMessageSource resourceBundle;
	
	@RequestMapping("/addTestCase.json")
	public @ResponseBody ActionResult addTestCase(@RequestBody TestCaseBean testCaseBean)
	{
		ActionResult actionResult = new ActionResult();
		ActionError error = new ActionError();
		actionResult.setStatus("SUCCESS");
		BindException errors = new BindException(testCaseBean, "testCaseBean");
		testCaseBean.setRecordId(utilDao.getUniqueId());
		testCaseBean.setTestDesinedBy("Naresh");
		testCaseValidator.validate(testCaseBean, errors);
		if(!errors.hasErrors())
			testCaseDao.addRecord(testCaseBean);
		else{
			actionResult.setStatus("ERROR");
			for(ObjectError objError : errors.getAllErrors()){
				error = new ActionError();
				error.setErrorMessage(resourceBundle.getMessage(objError.getCode(), objError.getArguments(), null));
				actionResult.addError(error);
			}
		}
		return actionResult;
		
	}
	
	@RequestMapping("/updateTestCase.json")
	public @ResponseBody ActionResult updateTestCase(@RequestBody TestCaseBean testCaseBean)
	{

		ActionResult actionResult = new ActionResult();
		ActionError error = new ActionError();
		actionResult.setStatus("SUCCESS");
		BindException errors = new BindException(testCaseBean, "testCaseBean");
		testCaseBean.setRecordId(utilDao.getUniqueId());
		testCaseBean.setTestDesinedBy("Naresh");
		testCaseValidator.validate(testCaseBean, errors);
		if(!errors.hasErrors())
			testCaseDao.updateRecord(testCaseBean);
		else{
			actionResult.setStatus("ERROR");
			for(ObjectError objError : errors.getAllErrors()){
				error = new ActionError();
				error.setErrorMessage(resourceBundle.getMessage(objError.getCode(), objError.getArguments(), null));
				actionResult.addError(error);
			}
		}
		return actionResult;
	}
	
	@RequestMapping("/deleteTestCase/{recordID}.json")
	public @ResponseBody ActionResult deleteTestCase(@PathVariable(value="recordID") String recordID)
	{

		ActionResult actionResult = new ActionResult();
		ActionError error = new ActionError();
		actionResult.setStatus("SUCCESS");
		TestCaseBean testCaseBean = new TestCaseBean();
		BindException errors = new BindException(testCaseBean, "testCaseBean");
		if(!errors.hasErrors())
			testCaseDao.deleteRecord(recordID);
		else{
			actionResult.setStatus("ERROR");
			for(ObjectError objError : errors.getAllErrors()){
				error = new ActionError();
				error.setErrorMessage(resourceBundle.getMessage(objError.getCode(), objError.getArguments(), null));
				actionResult.addError(error);
			}
		}
		return actionResult;
	}
	
	@RequestMapping("/testcase/{recordID}.json")
	public @ResponseBody TestCaseBean readTestCase(@PathVariable(value="recordID") String recordID)
	{
		return testCaseDao.readRecord(recordID);
	}
	
	@RequestMapping("/testCaseList.json")
	public @ResponseBody PaginationList testCaseList(@RequestBody TestCaseFilter testCaseFilterBean)
	{
		PaginationList paginationList = new PaginationList();
		Map<String,Object> result = new HashMap<String, Object>();
		List<TestCaseBean> list = new ArrayList<TestCaseBean>();
		paginationList = testCaseDao.readTestCaseList(testCaseFilterBean);
		return paginationList;
		
	}
	
	@RequestMapping("/valuelist/{keyValue}.json")
	public @ResponseBody List<CommonBean> getValueList(@PathVariable String keyValue, CommonBean filterBean)
	{
		List<CommonBean> lstresults = testCaseDao.getValueList(keyValue, filterBean);
		return lstresults;
	}
	
	@RequestMapping("/keywordList.json")
	public @ResponseBody List<CommonBean> getKeywordList(CommonBean filterBean)
	{
		List<CommonBean> keywordList = new ArrayList<CommonBean>();
		Keyword oprList[] = Keyword.values();
		for(Keyword operation : oprList)
		{
			if(operation.isExternal())
				keywordList.add(new CommonBean(operation.getCode(), operation.getDescription()));
		}
		List<CommonBean> lstresults = testCaseDao.getValueList("KEYWORD", filterBean);
		keywordList.addAll(lstresults);
		return keywordList;
	}
}
