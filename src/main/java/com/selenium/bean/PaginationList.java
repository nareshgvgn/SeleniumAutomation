package com.selenium.bean;

import java.util.List;

public class PaginationList {
	private Integer rowCount;
	List<? extends AbstractSeleniumBean> list;
	public Integer getTotalRows() {
		return rowCount;
	}
	public void setTotalRows(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public List<? extends AbstractSeleniumBean> getList() {
		return list;
	}
	public void setList(List<? extends AbstractSeleniumBean> list) {
		this.list = list;
	}
	
}
