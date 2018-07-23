package com.student.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.student.dao.PublisherDao;
import com.student.entiy.Book;

import com.student.entiy.PageEntiy;
import com.student.entiy.Publisher;
import com.student.util.Dbconn;

public class PublisherDaoImpl implements PublisherDao{
	QueryRunner runner=new QueryRunner(Dbconn.getDataSource());
	@Override
	public List<Publisher> findAllPublisher(PageEntiy entiy) {
		// TODO Auto-generated method stub
		String sql="select * from publishers where tag=1";
		try {
			List<Publisher> publishers = runner.query(sql, new BeanListHandler<Publisher>(Publisher.class));
			/*String sql2="select * from books where tag=1 and PublisherId=? limit ?,?";
			for (Publisher publisher : publishers) {
				List<Book> books = runner.query(sql2, new BeanListHandler<Book>(Book.class), publisher.getId(),entiy.getPageNum()*15,15);
				publisher.setListBooks(books);
			}*/
			
			return publishers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteByPublisherId(String id) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String sql ="update publishers set tag=0 where id = ?";
		
		try {
			int update = runner.update(sql, id);
			if(update>0){
				flag=true;
			}
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addPublisher(Publisher publisher) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String sql="insert into publishers(id,name,description) value (?,?,?)";
		List<Object> list=new ArrayList<Object>();
		
		if(publisher!=null){
			list.add(publisher.getId());
			list.add(publisher.getName());
			list.add(publisher.getDescription());
		}
		try {
			int update = runner.update(sql,list.toArray());
			if(update>0){
				flag=true;
			}
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateByPublisherId(Publisher publisher) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String sql="update publishers set name=?,description=? where id=?";
		
		List<Object> list=new ArrayList<Object>();
		if(publisher!=null){
			list.add(publisher.getName());
			list.add(publisher.getDescription());
			list.add(publisher.getId());
		}
		try {
			int update = runner.update(sql,list.toArray());
			if(update>0){
				flag=true;
			}
			
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int findtotalPublishers(String id) {
		// TODO Auto-generated method stub
		String sql="select count(*) from books where tag=1 and publisherId=?";
		try {
			Object query = runner.query(sql, new ScalarHandler<Object>(1), id);
			Long long1=(Long) query;
			return long1.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Publisher findOnePublisher(String id, PageEntiy entiy) {
		// TODO Auto-generated method stub
String sql="select * from publishers where tag=1 and id=?";
		
		try {
			Publisher publisher = runner.query(sql, new BeanHandler<Publisher>(Publisher.class), id);
			String sql2="select * from books where tag=1 and PublisherId=? limit ?,?";
			List<Book> books = runner.query(sql2, new BeanListHandler<Book>(Book.class), id,entiy.getPageNum()*entiy.getPageSize(),entiy.getPageSize());
			publisher.setListBooks(books);
			return publisher;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
