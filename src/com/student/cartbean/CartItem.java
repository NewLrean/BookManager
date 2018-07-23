package com.student.cartbean;

public class CartItem {
	private String id;//物品id
	private String name;//物品名称
	private float price;//物品单价
	private int number=1;//物品数量
	private float subtotal;//物品小计
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public float getSubtotal() {
		return number*price;
	}
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", name=" + name + ", price=" + price + ", number=" + number + ", subtotal="
				+ subtotal + "]";
	}
	
	
	
}
