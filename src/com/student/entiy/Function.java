package com.student.entiy;

import java.util.ArrayList;
import java.util.List;

public class Function {
	private int id;
	private String name;
	private String uri;
	private String description;
	
	private List<Role> list=new ArrayList<Role>();
	
	
	
	public List<Role> getList() {
		return list;
	}
	public void setList(List<Role> list) {
		this.list = list;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Function [id=" + id + ", name=" + name + ", uri=" + uri + ", description=" + description + "]";
	}
	
	
}
