package com.student.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.student.dao.BookDao;
import com.student.dao.impl.BookDaoImpl;
import com.student.entiy.Book;
import com.student.entiy.PageEntiy;
import com.student.util.IdGenrtor;

public class BookTest {
	BookDao bookDao=new BookDaoImpl();
	PageEntiy entiy=new PageEntiy();
	
	
		@Test
		public void outpu(){
			System.out.println("123123123");
		}
	
		@Test
		public void findBook(){
			List<Book> findSomeBook = bookDao.findSomeBook(entiy);
			System.out.println(findSomeBook);
		}
		
		@Test
		public void deleteBook(){
			System.out.println(bookDao.deleteByBookId("3"));
		}
		
		@Test
		public void saveBook() {
			Book book=new Book();
			book.setId("8QU0B6PWR4RKX9M7Z6702TRN4R68GA2A_");
			book.setTitle("admin");
			book.setAuthor("爱我范围");
			book.setPublishDate("2018-12-13");
			book.setPublisherId("75");
			book.setIsbn("qmwepnrp");
			
			book.setWordsCount(7);
			book.setUnitPrice(19);
			book.setContentDescription("sefesfe");
			book.setAurhorDescription("dfghxd");
			book.setEditorComment("hrtdhtrf");
			book.setTOC("hrtdhtrf");
			book.setCategoryId("18");
			book.setQuantity(56);
			book.setAddress("李家沱");
			book.setBaoyou("包邮");
			System.out.println(bookDao.addBook(book));
		}
		
		@Test
		public void updateBook(){
			Book book=new Book();
			book.setId("2");
			book.setTitle("钢铁");
			book.setAuthor("111111111");
			book.setPublishDate("1111111111");
			book.setPublisherId("2");
			book.setIsbn("1111111111");
			book.setCategoryId("2");
			
			System.out.println(bookDao.updateByBookId(book));
		}
		
		
		@Test
		public void findTitle(){
			List<Book> findTitleBook = bookDao.findTitleBook("C++", entiy);
			System.out.println(findTitleBook);
		}
		
		@Test
		public void ttTest(){
			Map <String,String> data=new HashMap<String,String>();
			data.put("empno","1001");
			data.put("ename","scott");
			data.put("job","添坑队长");
			
			Iterator<String> iterator = data.keySet().iterator();  
	        while(iterator.hasNext()){  
	            String key = iterator.next();  
	            System.out.println(key+":"+data.get(key));  
	        }
		}
		
		@Test
		public void total(){
			int findtotalbooks = bookDao.findTitletotal("C", entiy);
			System.out.println(findtotalbooks);
		}
}
