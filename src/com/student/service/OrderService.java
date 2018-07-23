package com.student.service;

import java.util.List;

import com.student.entiy.OrderItem;
import com.student.entiy.Orders;

public interface OrderService {
	boolean addOrder(Orders order);
	
	List<Orders> findAllOrders(String id);
	
	List<OrderItem> findaboutitems(String id);
}
