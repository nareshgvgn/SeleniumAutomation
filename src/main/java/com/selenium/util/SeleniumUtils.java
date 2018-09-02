package com.selenium.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SeleniumUtils {

	public static String mergeInputs(List<String> ids) {
		if (ids == null)
			return "";
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < ids.size(); i++) {
			sb.append(ids.get(i));
			if (i != (ids.size() - 1)) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	public static List<String> splitInputs(String id) {
		if (id == null)
			return null;
		List<String> result = new ArrayList<String>();
		StringTokenizer stk = new StringTokenizer(id);
		while (stk.hasMoreTokens()) {
			String tokenValue = stk.nextToken();
			result.add(tokenValue);
		}
		return result;
	}
}
