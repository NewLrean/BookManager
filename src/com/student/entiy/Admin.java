package com.student.entiy;

import java.util.ArrayList;
import java.util.List;

public class Admin {
	private Integer id;
	private String name;
	private String password;
	
	private List<Role> list=new ArrayList<Role>();
	
	
	
	public List<Role> getList() {
		return list;
	}
	public void setList(List<Role> list) {
		this.list = list;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
	
	
	
}
