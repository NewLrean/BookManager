package com.student.test;

import java.util.List;

import org.junit.Test;

import com.student.dao.CategoryDao;
import com.student.dao.PublisherDao;
import com.student.dao.impl.CategoryDaoImpl;
import com.student.dao.impl.PublisherDaoImpl;
import com.student.entiy.Category;
import com.student.entiy.PageEntiy;
import com.student.entiy.Publisher;

public class CategoryTest {
	CategoryDao CategoryDao=new CategoryDaoImpl();
	PublisherDao publisherDao=new PublisherDaoImpl();
	PageEntiy entiy=new PageEntiy();
	@Test
	public void findAll(){
		List<Category> findAllCategory = CategoryDao.findAllCategory(entiy);
		for (Category category : findAllCategory) {
			System.out.println(category.getName()+category.getListBooks());
		}
	}
	
	@Test
	public void findOnepublisher(){
		Publisher findOnePublisher = publisherDao.findOnePublisher("1", entiy);
		System.out.println(findOnePublisher);
	}
	
	@Test
	public void savePublisher(){
		Publisher publisher=new Publisher();
		
		publisher.setId(":hqcbeibc");
		publisher.setName("cnwoenf");
		publisher.setDescription("长宁区返利网");
		publisherDao.addPublisher(publisher);
	}
}
