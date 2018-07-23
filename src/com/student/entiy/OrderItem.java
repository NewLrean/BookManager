package com.student.entiy;

public class OrderItem {
	private String id;
	private String name;
	private int number;
	private float subprice;
	private String  bookid;
	private String  orderid;
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public float getSubprice() {
		return subprice;
	}
	public void setSubprice(float subprice) {
		this.subprice = subprice;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", name=" + name + ", number=" + number + ", subprice=" + subprice + ", bookid="
				+ bookid + ", orderid=" + orderid + "]";
	}
	
	
}	
