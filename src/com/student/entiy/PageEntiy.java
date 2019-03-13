package com.student.entiy;

public class PageEntiy {
	private Integer pageNum=0;
	
	private Integer PageSize=4;

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
