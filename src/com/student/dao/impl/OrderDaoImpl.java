package com.student.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.student.dao.OrderDao;
import com.student.entiy.OrderItem;
import com.student.entiy.Orders;
import com.student.util.Dbconn;
import com.student.util.IdGenrtor;
import com.sun.org.apache.bcel.internal.generic.IfInstruction;

public class OrderDaoImpl implements OrderDao {

	QueryRunner runner= new QueryRunner(Dbconn.getDataSource());

	@Override
	public boolean addOrder(Orders order) {
		// TODO Auto-generated method stub
		String sql="insert into orders(id,ordernum,totalNum,totalmoney,userid,ordertime) value(?,?,?,?,?,?)";
		
		List<Object> list=new ArrayList<>();
		String guid = "ord"+IdGenrtor.getGUID();
		if(order!=null){
			list.add(IdGenrtor.getGUID());
			list.add(guid);
			list.add(order.getTotalNum());
			list.add(order.getTotalmoney());
			list.add(order.getUserid());
			list.add(order.getOrdertime());
		}
		try {
			int update = runner.update(sql,list.toArray());
			if(update>0){
				List<OrderItem> items = order.getItems();
				if(items!=null){
				for (OrderItem orderItem : items) {
					runner.update("insert into orderitem(id,name,number,subprice,bookid,orderid) value (?,?,?,?,?,?) ",
							IdGenrtor.getGUID(),orderItem.getName(),orderItem.getNumber(),orderItem.getSubprice(),
							orderItem.getBookid(),guid);
					}
				}
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<Orders> findAllOrders(String id) {
		// TODO Auto-generated method stub
		try {
			List<Orders> list = runner.query("select * from orders where tag=1 and userid=?	",new BeanListHandler<Orders>(Orders.class), id);
			if(list!=null){
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<OrderItem> findaboutitems(String id) {
		// TODO Auto-generated method stub
		try {
			List<OrderItem> list = runner.query("select * from orderitem where orderid= ?", new BeanListHandler<OrderItem>(OrderItem.class), id);
			if(list!=null){
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
