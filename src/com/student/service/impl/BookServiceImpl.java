package com.student.service.impl;

import java.util.List;

import com.student.dao.BookDao;
import com.student.dao.impl.BookDaoImpl;
import com.student.entiy.Book;
import com.student.entiy.PageEntiy;
import com.student.service.BookService;

public class BookServiceImpl implements BookService {

	BookDao bookDao=new BookDaoImpl();
	@Override
	public List<Book> findSomeBook(PageEntiy entiy) {
		// TODO Auto-generated method stub
		return bookDao.findSomeBook(entiy);
	}

	@Override
	public boolean deleteByBookId(String id) {
		// TODO Auto-generated method stub
		return bookDao.deleteByBookId(id);
	}

	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return bookDao.addBook(book);
	}

	@Override
	public boolean updateByBookId(Book book) {
		// TODO Auto-generated method stub
		return bookDao.updateByBookId(book);
	}

	@Override
	public int findtotalbooks() {
		// TODO Auto-generated method stub
		return bookDao.findtotalbooks();
	}

	@Override
	public List<Book> findTitleBook(String title,PageEntiy entiy) {
		// TODO Auto-generated method stub
		return bookDao.findTitleBook(title,entiy);
	}

	@Override
	public int findTitletotal(String title, PageEntiy entiy) {
		// TODO Auto-generated method stub
		return bookDao.findTitletotal(title, entiy);
	}

	@Override
	public Book findOneBook(String id) {
		// TODO Auto-generated method stub
		return bookDao.findOneBook(id);
	}

}
