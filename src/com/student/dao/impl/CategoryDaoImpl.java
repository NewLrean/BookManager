package com.student.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.student.dao.CategoryDao;
import com.student.entiy.Book;
import com.student.entiy.Category;
import com.student.entiy.PageEntiy;
import com.student.util.Dbconn;

public class CategoryDaoImpl implements CategoryDao {

	QueryRunner runner=new QueryRunner(Dbconn.getDataSource());
	@Override
	public List<Category> findAllCategory(PageEntiy entiy) {
		// TODO Auto-generated method stub
		String sql="select * from categories where tag=1";
		try {
			List<Category> Categories = runner.query(sql, new BeanListHandler<Category>(Category.class));
			/*String sql2="select * from books where tag=1 and CategoryId=? limit ?,?";
			for (Category category : Categories) {
				List<Book> books = runner.query(sql2, new BeanListHandler<Book>(Book.class), category.getId(),entiy.getPageNum()*entiy.getPageSize(),entiy.getPageSize());
				category.setListBooks(books);
			}*/
			
			return Categories;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteByCategoryId(String id) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String sql ="update categories set tag=0 where id = ?";
		
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
	public boolean addCategory(Category category) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String sql="insert into categories(id,name,description) value (?,?,?)";
		List<Object> list=new ArrayList<Object>();
		
		if(category!=null){
			list.add(category.getId());
			list.add(category.getName());
			list.add(category.getDescription());
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
	public boolean updateByCategoryId(Category category) {
		// TODO Auto-generated method stub
		
		boolean flag=false;
		String sql="update categories set name=?,description=? where id=?";
		
		List<Object> list=new ArrayList<Object>();
		if(category!=null){
			list.add(category.getName());
			list.add(category.getDescription());
			list.add(category.getId());
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
	public int findtotalCategories(String id) {
		// TODO Auto-generated method stub
		String sql="select count(*) from books where tag=1 and CategoryId=?";
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
	public Category findOneCategory(String id,PageEntiy entiy) {
		// TODO Auto-generated method stub
			String sql="select * from categories where tag=1 and id=?";
		
		try {
			Category category = runner.query(sql, new BeanHandler<Category>(Category.class), id);
			String sql2="select * from books where tag=1 and CategoryId=? limit ?,?";
			List<Book> books = runner.query(sql2, new BeanListHandler<Book>(Book.class), id,entiy.getPageNum()*entiy.getPageSize(),entiy.getPageSize());
			category.setListBooks(books);
			return category;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
