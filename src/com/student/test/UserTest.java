package com.student.test;

import java.util.List;

import org.junit.Test;

import com.student.entiy.Orders;
import com.student.service.OrderService;
import com.student.service.impl.OrderServiceImpl;

public class UserTest {
	OrderService orderService=new OrderServiceImpl();
	
	@Test
	public void ShowOrder(){
		List<Orders> findAllOrders = orderService.findAllOrders("JGG1SP0YT51IN6R7PFDGRI1ZK6Q0JD94__uid");
		for (Orders orders : findAllOrders) {
			System.out.println(orders);
		}
	}
}
