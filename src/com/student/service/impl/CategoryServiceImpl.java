package com.student.service.impl;

import java.util.List;

import com.student.dao.CategoryDao;
import com.student.dao.impl.CategoryDaoImpl;
import com.student.entiy.Category;
import com.student.entiy.PageEntiy;
import com.student.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	CategoryDao categoryDao=new CategoryDaoImpl();
	
	@Override
	public List<Category> findAllCategory(PageEntiy entiy) {
		// TODO Auto-generated method stub
		return categoryDao.findAllCategory(entiy);
	}

	@Override
	public boolean deleteByCategoryId(String id) {
		// TODO Auto-generated method stub
		return categoryDao.deleteByCategoryId(id);
	}

	@Override
	public boolean addCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.addCategory(category);
	}

	@Override
	public boolean updateByCategoryId(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.updateByCategoryId(category);
	}

	@Override
	public int findtotalCategories(String id) {
		// TODO Auto-generated method stub
		return categoryDao.findtotalCategories( id);
	}

	@Override
	public Category findOneCategory(String id,PageEntiy entiy) {
		// TODO Auto-generated method stub
		return categoryDao.findOneCategory(id,entiy);
	}

}
