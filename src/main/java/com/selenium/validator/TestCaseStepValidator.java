package com.selenium.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.selenium.bean.TestCaseStep;
@Component
public class TestCaseStepValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestCaseStep.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "testStepId", "testStepId.required");
		ValidationUtils.rejectIfEmpty(errors, "sequenceNo", "sequenceNo.required");
		ValidationUtils.rejectIfEmpty(errors, "keyword", "keyword.required");
		ValidationUtils.rejectIfEmpty(errors, "description", "description.required");
		TestCaseStep user = (TestCaseStep) target;
	}

}
