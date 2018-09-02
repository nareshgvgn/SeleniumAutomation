package com.selenium.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.selenium.bean.PropertyDetailBean;
import com.selenium.bean.PropertySetMstBean;
import com.selenium.bean.TestCaseStep;
@Component
public class PropertySetValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestCaseStep.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof PropertySetMstBean)
		{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "propertysetid.required");
			ValidationUtils.rejectIfEmpty(errors, "name", "propertysetname.required");
		}
		if(target instanceof PropertyDetailBean)
		{
			ValidationUtils.rejectIfEmpty(errors, "propertyName", "propertyname.required");
			ValidationUtils.rejectIfEmpty(errors, "propertyValue", "propertyvalue.required");
		}
	}

}
