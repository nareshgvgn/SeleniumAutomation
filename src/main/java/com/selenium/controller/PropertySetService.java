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
import com.selenium.bean.PropertyDetailBean;
import com.selenium.bean.PropertySetMstBean;
import com.selenium.dao.PropertySetDao;
import com.selenium.dao.UtilDao;
import com.selenium.validator.PropertySetValidator;

@RestController
public class PropertySetService {

	@Autowired
	PropertySetDao dao;
	
	@Autowired
	UtilDao utilDao;
	
	@Autowired
	PropertySetValidator validator;
	
	@Autowired
	@Qualifier("messageSource")
	ReloadableResourceBundleMessageSource resourceBundle;
	
	@RequestMapping("/propertySetList.json")
	public @ResponseBody Map<String,Object> testCaseList(TestCaseFilter testCaseFilterBean)
	{
		Map<String,Object> result = new HashMap<String, Object>();
		List<PropertySetMstBean> list = new ArrayList<PropertySetMstBean>();
		list = dao.readPropertySetList(testCaseFilterBean);
		result.put("list",list);
		if(list != null)
		result.put("totalRows",list.size());
		return result;
	}
	
	@RequestMapping("/addPropertySet.json")
	public @ResponseBody ActionResult addTestCase(@RequestBody PropertySetMstBean testCaseBean)
	{
		ActionResult actionResult = new ActionResult();
		ActionError error = new ActionError();
		actionResult.setStatus("SUCCESS");
		BindException errors = new BindException(testCaseBean, "testCaseBean");
		testCaseBean.setId(utilDao.getUniqueId());
		actionResult.setInfoMessage(testCaseBean.getId());
		testCaseBean.setModifiedBy("Naresh");
		validator.validate(testCaseBean, errors);
		if(!errors.hasErrors())
			dao.addRecord(testCaseBean);
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
	
	@RequestMapping("/updatePropertySet.json")
	public @ResponseBody ActionResult updateTestCase(@RequestBody PropertySetMstBean testCaseBean)
	{
		ActionResult actionResult = new ActionResult();
		ActionError error = new ActionError();
		actionResult.setStatus("SUCCESS");
		BindException errors = new BindException(testCaseBean, "testCaseBean");
		testCaseBean.setModifiedBy("Naresh");
		validator.validate(testCaseBean, errors);
		if(!errors.hasErrors())
			dao.updateRecord(testCaseBean);
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
	
	@RequestMapping("/propertySet/{id}.json")
	public @ResponseBody PropertySetMstBean updateTestCase(@PathVariable(value="id") String recordID)
	{
		PropertySetMstBean mstBean = dao.readRecord(recordID);
		return mstBean;
	}
	
	@RequestMapping("/propertyList/{id}.json")
	public @ResponseBody Map<String,Object> propertyListList(@PathVariable(value="id") String recordID, TestCaseFilter testCaseFilterBean)
	{
		Map<String,Object> result = new HashMap<String, Object>();
		List<PropertyDetailBean> list = new ArrayList<PropertyDetailBean>();
		list = dao.readPropertyList(recordID, testCaseFilterBean);
		result.put("list",list);
		if(list != null)
		result.put("totalRows",list.size());
		return result;
	}
	
	@RequestMapping("/addProperty.json")
	public @ResponseBody ActionResult saveProperty(@RequestBody PropertyDetailBean testCaseBean)
	{
		ActionResult actionResult = new ActionResult();
		ActionError error = new ActionError();
		actionResult.setStatus("SUCCESS");
		BindException errors = new BindException(testCaseBean, "testCaseBean");
		validator.validate(testCaseBean, errors);
		if(!errors.hasErrors())
			dao.addDetailRecord(testCaseBean);
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
	
	@RequestMapping("/updateProperty.json")
	public @ResponseBody ActionResult updateProperty(@RequestBody PropertyDetailBean testCaseBean)
	{
		ActionResult actionResult = new ActionResult();
		ActionError error = new ActionError();
		actionResult.setStatus("SUCCESS");
		BindException errors = new BindException(testCaseBean, "testCaseBean");
		validator.validate(testCaseBean, errors);
		if(!errors.hasErrors())
			dao.updateDetailRecord(testCaseBean);
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
	
	@RequestMapping("/clonePropertySet.json")
	public @ResponseBody ActionResult clonePropertySet(@RequestBody PropertySetMstBean testCaseBean)
	{
		String sourceRecordKey = testCaseBean.getId();
		ActionResult actionResult = new ActionResult();
		ActionError error = new ActionError();
		actionResult.setStatus("SUCCESS");
		BindException errors = new BindException(testCaseBean, "testCaseBean");
		testCaseBean.setId(utilDao.getUniqueId());
		actionResult.setInfoMessage(testCaseBean.getId());
		testCaseBean.setModifiedBy("Naresh");
		validator.validate(testCaseBean, errors);
		if(!errors.hasErrors()){
			dao.addRecord(testCaseBean);
			dao.cloneProperties(testCaseBean, sourceRecordKey);
		}
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
}
