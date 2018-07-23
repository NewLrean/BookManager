package com.student.entiy;

import javax.persistence.Transient;

public class PageEntiy {
	@Transient
	private Integer pageNum=0;
	
	@Transient
	private Integer PageSize=4;

	@Transient
	private Integer pages;
	
	private String objfan;
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return PageSize;
	}

	public void setPageSize(Integer pageSize) {
		PageSize = pageSize;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public String getObjfan() {
		return objfan;
	}

	public void setObjfan(String objfan) {
		this.objfan = objfan;
	}
	
	
	
}
