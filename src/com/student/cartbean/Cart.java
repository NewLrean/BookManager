package com.student.cartbean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * 购物车类
 * @author 蒋志鹏
 *
 */
public class Cart {
	private Map<String, CartItem> map;
	private int	 totalNum;
	private float totlemoney;
	
	
	public Cart(Map<String, CartItem> map) {
		super();
		this.map=map;
		
	}
	public Map<String, CartItem> getMap() {
		
		return map;
	}
	
	public Integer getTotalNum() {
		
		return totalNum;
	}
	
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public void setTotlemoney(float totlemoney) {
		this.totlemoney = totlemoney;
	}
	public float getTotlemoney() {
		return totlemoney;
	}
	
	@Override
	public String toString() {
		return "Cart [map=" + map + ", totalNum=" + totalNum + ", totlemoney=" + totlemoney 
				+ "]";
	}
	
	
	
}
