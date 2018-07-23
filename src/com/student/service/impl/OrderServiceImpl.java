package com.student.service.impl;


import java.util.List;

import com.student.dao.OrderDao;
import com.student.dao.impl.OrderDaoImpl;
import com.student.entiy.OrderItem;
import com.student.entiy.Orders;
import com.student.service.OrderService;

public class OrderServiceImpl implements OrderService {

	OrderDao orderDao=new OrderDaoImpl();
	@Override
	public boolean addOrder(Orders order) {
		// TODO Auto-generated method stub
		return orderDao.addOrder(order);
	}
	@Override
	public List<Orders> findAllOrders(String id) {
		// TODO Auto-generated method stub
		return orderDao.findAllOrders(id);
	}
	@Override
	public List<OrderItem> findaboutitems(String id) {
		// TODO Auto-generated method stub
		return orderDao.findaboutitems(id);
	}

}
