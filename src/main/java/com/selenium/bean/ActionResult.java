package com.selenium.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmahajan on 11/4/2015.
 */
public class ActionResult
{
    private String errorCode;
    private String errorMessage;
    private String infoMessage;
    private String status; // SUCCESS OR FAIL
    private List<ActionError> errors;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public static ActionResult handleErrors(Exception exe)
    {
        ActionResult actionResult = new ActionResult();
        actionResult.setStatus("FAILED");
        // Need to Add Errors Specific to Exception
        return actionResult;
    }

    public List<ActionError> getErrors()
    {
        return errors;
    }

    public void setErrors(List<ActionError> errors)
    {
        this.errors = errors;
    }

	public void addError(ActionError error) 
	{
		if(this.errors == null)
		{
			this.errors = new ArrayList<ActionError>();
		}
		if(null != this.errors)
		{
			this.errors.add(error);
		}
	}
}
