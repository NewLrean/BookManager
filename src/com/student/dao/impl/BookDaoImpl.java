package com.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.student.dao.BookDao;
import com.student.entiy.Book;
import com.student.entiy.Category;
import com.student.entiy.PageEntiy;
import com.student.entiy.Publisher;
import com.student.util.Dbconn;

public class BookDaoImpl implements BookDao {
	private QueryRunner runner=new QueryRunner(Dbconn.getDataSource());
	
	
	@Override
	public List<Book> findSomeBook(PageEntiy entiy) {
		// TODO Auto-generated method stub
		String sql = "select * from books where tag=1 limit ?,?";
		
			try {
				List<Book> books = runner.query(sql,new BeanListHandler<Book>(Book.class),entiy.getPageNum()*entiy.getPageSize(),entiy.getPageSize());
					
				return books;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
	}

	@Override
	public boolean deleteByBookId(String id) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pst=null;
	
		boolean flag=false;
		try {
			conn=Dbconn.getConnection();
			String sql = "update books set tag=0 where id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			if(0<pst.executeUpdate()){
				flag=true;
			}
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Dbconn.closeConn(pst, null, conn);
		}
		return flag;
	}

	@Override
	public boolean addBook(Book book) {
		
		
		
		boolean flag=false;
		String sql="insert into books(id,title,author,publisherId,publishDate,isbn,wordsCount,unitPrice,contentDescription,aurhorDescription,editorComment,TOC,categoryId,clicks,booksImages,quantity,souhuo,address,baoyou)"
				+ "value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Object> list=new ArrayList<Object>();
		if(book!=null){
			list.add(book.getId());
			list.add(book.getTitle());
			list.add(book.getAuthor());
			list.add(book.getPublisherId());
			list.add(book.getPublishDate());
			list.add(book.getIsbn());
			list.add(book.getWordsCount());
			list.add(book.getUnitPrice());
			list.add(book.getContentDescription());
			list.add(book.getAurhorDescription());
			list.add(book.getEditorComment());
			list.add(book.getTOC());
			list.add(book.getCategoryId());
			list.add(book.getClicks());
			list.add(book.getBooksImages());
			list.add(book.getQuantity());
			list.add(book.getSouhuo());
			list.add(book.getAddress());
			if(book.getBaoyou()=="0"){
				book.setBaoyou("不包邮");
			}else{
				book.setBaoyou("包邮");
			}
			list.add(book.getBaoyou());
		}
		try {
			
			int update = runner.update(sql, list.toArray());
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
	public int findtotalbooks() {
		// TODO Auto-generated method stub
		
		String sql="select count(*) from books where tag=1";
		
		try {
			Object query = runner.query(sql, new ScalarHandler(1));
			Long long1=(Long)query;
			return long1.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean updateByBookId(Book book) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String sql="update books set title=?,author=?,publisherId=?,publishDate=?,isbn=?,wordsCount=?,unitPrice=?,contentDescription=?,aurhorDescription=?,editorComment=?,TOC=?,categoryId=?,booksImages=?,quantity=?,address=?,baoyou=? "
				+ "where id=?";
		List<Object> list=new ArrayList<Object>();
		if(book!=null){
			list.add(book.getTitle());
			list.add(book.getAuthor());
			list.add(book.getPublisherId());
			list.add(book.getPublishDate());
			list.add(book.getIsbn());
			list.add(book.getWordsCount());
			list.add(book.getUnitPrice());
			list.add(book.getContentDescription());
			list.add(book.getAurhorDescription());
			list.add(book.getEditorComment());
			list.add(book.getTOC());
			list.add(book.getCategoryId());
			
			list.add(book.getBooksImages());
			list.add(book.getQuantity());
			
			list.add(book.getAddress());
			if(book.getBaoyou()=="0"){
				book.setBaoyou("不包邮");
			}else{
				book.setBaoyou("包邮");
			}
			list.add(book.getBaoyou());
			list.add(book.getId());
			
			
			
		}
		
		try {
			int update = runner.update(sql, list.toArray());
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
	public List<Book> findTitleBook(String title,PageEntiy entiy) {
		String sql="select * from books where tag=1 and title like ? limit ?,?";
		
		try {
			List<Book> books = runner.query(sql,new BeanListHandler<Book>(Book.class),"%"+title+"%",entiy.getPageNum()*entiy.getPageSize(),entiy.getPageSize());
			return books;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int findTitletotal(String title, PageEntiy entiy) {
		// TODO Auto-generated method stub
		
		String sql="select count(*) from books where tag=1 and title like ? ";
		
		try {
			Object query = runner.query(sql, new ScalarHandler(1),"%"+title+"%");
			Long long1=(Long)query;
			return long1.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Book findOneBook(String id) {
		// TODO Auto-generated method stub
		
		String sql="select * from books where id= ?";
		String sql2 ="select * from categories where id=?";
		String sql3 ="select * from publishers where id=?";
		try {
			Book book = runner.query(sql, new BeanHandler<Book>(Book.class), id);
			Category category = runner.query(sql2, new BeanHandler<Category>(Category.class), book.getCategoryId());
			Publisher publisher = runner.query(sql3, new BeanHandler<Publisher>(Publisher.class), book.getPublisherId());
			book.setPublisher(publisher);
			book.setCategory(category);
			if(book!=null)
			return book;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
