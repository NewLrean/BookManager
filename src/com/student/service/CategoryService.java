package com.student.service;

import java.util.List;


import com.student.entiy.Category;
import com.student.entiy.PageEntiy;

public interface CategoryService {
	public List<Category> findAllCategory(PageEntiy entiy);
	
	public boolean deleteByCategoryId(String id);

	public boolean addCategory(Category category);

	public boolean updateByCategoryId(Category category);

	public int findtotalCategories(String id);
	
	public Category findOneCategory(String id,PageEntiy entiy);
}
