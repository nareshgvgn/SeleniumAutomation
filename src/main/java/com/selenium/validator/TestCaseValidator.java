package com.selenium.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.selenium.bean.TestCaseBean;

@Component
public class TestCaseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestCaseBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "testCaseId", "testCaseId.required");
		ValidationUtils.rejectIfEmpty(errors, "testCaseTitle", "testCaseTitle.required");
		ValidationUtils.rejectIfEmpty(errors, "moduleName", "component.required");
		ValidationUtils.rejectIfEmpty(errors, "testcaseDescription", "testcaseDescription.required");
		ValidationUtils.rejectIfEmpty(errors, "projectId", "projectId.required");
		TestCaseBean user = (TestCaseBean) target;
	}

}
