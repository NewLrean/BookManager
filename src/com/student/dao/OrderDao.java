package com.student.dao;


import java.util.List;

import com.student.entiy.OrderItem;
import com.student.entiy.Orders;

public interface OrderDao {

	boolean addOrder(Orders order);

	List<Orders> findAllOrders(String id);

	List<OrderItem> findaboutitems(String id);
}
