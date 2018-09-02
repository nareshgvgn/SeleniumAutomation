package com.selenium.bean;

public enum Keyword
{

	CUSTOM(null, "", false),
	CLICK("CLICK", "Click", true),
	SETTEXT("SETTEXT", "Set Text", true),
	GOTOURL("GOTOURL", "Go to Url", true),
	GETTEXT("GETTEXT", "Get Text", true),
	WAITFORPAGELOAD("WAITFORPAGELOAD", "Wait For Page Load", true),
	SELECTRADIOBUTTON("SELECTRADIOBUTTON", "Select Radio Button", true),
	SELECTCHECKBOXES("SELECTCHECKBOXES", "Select Checkboxes", true),
	SELECTDROPDOWN("SELECTDROPDOWN", "Select drop down", true),
	VERIFY_TEXT("VERIFY_TEXT", "Verify text", true),
	CREATE_VARIABLE("CREATE_VARIABLE", "Create Variable", true),
	VERIFY_SMARTGRID_TEXT("VERIFY_SMARTGRID_TEXT", "Verify Smart Grid Text",
			true),
	CREATE_SMARTGRID_LIST_VARIABLE("CREATE_SMARTGRID_LIST_VARIABLE",
			"Create Smart Grid Text", true),
	FILTER_EXT_AUTOCOMPLETER("FILTER_EXT_AUTOCOMPLETER",
					"Filter Auto Completer Value", true),
	JQUERY_MULTISEL_CHECKALL("JQUERY_MULTISEL_CHECKALL", "Jquery Multi Select Check All", true)				;

	String code;
	String description;
	boolean isExternal;

	Keyword(String code, String description, boolean isExternal)
	{
		this.code = code;
		this.description = description;
		this.isExternal = isExternal;
	}

	public static Keyword valueOfKeyword(String keyword)
	{
		Keyword result = null;
		try
		{
			result = Keyword.valueOf(keyword);
		}
		catch (Exception exc)
		{
			result = Keyword.CUSTOM;
		}
		return result;
	}

	public String getCode()
	{
		return code;
	}

	public String getDescription()
	{
		return description;
	}

	public boolean isExternal()
	{
		return isExternal;
	}
}
