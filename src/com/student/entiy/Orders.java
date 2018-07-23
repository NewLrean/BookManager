package com.student.entiy;

import java.util.List;

public class Orders {
	private String id;
	private String ordernum;
	private int totalNum;
	private float totalmoney;
	private String userid;
	private int status;
	private String ordertime;
	
	
	List<OrderItem> items=null;
	
	public Orders() {
		super();
	}
	
	
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}


	public String getOrdertime() {
		return ordertime;
	}

	public int getStatus() {
		return status;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Orders(String id, String ordernum, int totalNum, float totalmoney, String userid) {
		super();
		this.id = id;
		this.ordernum = ordernum;
		this.totalNum = totalNum;
		this.totalmoney = totalmoney;
		this.userid = userid;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getOrdernum() {
		return ordernum;
	}



	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}



	public int getTotalNum() {
		return totalNum;
	}



	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}



	public float getTotalmoney() {
		return totalmoney;
	}



	public void setTotalmoney(float totalmoney) {
		this.totalmoney = totalmoney;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", ordernum=" + ordernum + ", totalNum=" + totalNum + ", totalmoney=" + totalmoney
				+ ", userid=" + userid + ", status=" + status + ", ordertime=" + ordertime + "]";
	}



	
	
}
