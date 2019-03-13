package com.student.entiy;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String description;
	
	private List<Book> listBooks;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Book> getListBooks() {
		return listBooks;
	}
	public void setListBooks(List<Book> listBooks) {
		this.listBooks = listBooks;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", listBooks=" + listBooks
				+ "]";
	}
	
	
}
