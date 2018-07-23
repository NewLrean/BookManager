package com.student.entiy;

import java.io.Serializable;

public class Student extends PageEntiy implements Serializable{
	private Integer id;
	private String name;
	private String password;
	private String sex;
	private String phone;
	private String address;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", password=" + password + ", sex=" + sex + ", phone=" + phone
				+ ", address=" + address + "]";
	}
	public Student(Integer id, String name, String password, String sex, String phone, String address) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		this.address = address;
	}
	public Student() {
		super();
	}
	
	
}
