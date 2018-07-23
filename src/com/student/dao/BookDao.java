package com.student.dao;

import java.util.List;

import com.student.entiy.Book;
import com.student.entiy.PageEntiy;



public interface BookDao {
	public List<Book> findSomeBook(PageEntiy entiy);
	
	public boolean deleteByBookId(String id);

	public boolean addBook(Book book);

	public boolean updateByBookId(Book book);

	public int findtotalbooks();
	
	public List<Book> findTitleBook(String title,PageEntiy entiy);
	
	public int findTitletotal(String title,PageEntiy entiy);
	
	public Book findOneBook(String id);
	
}
