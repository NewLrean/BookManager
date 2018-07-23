package com.student.dao;

import java.util.List;

import com.student.entiy.PageEntiy;
import com.student.entiy.Student;

public interface StudentDao {
	public List<Student> findAll(PageEntiy entiy);
	
	public boolean deleteById(int id);

	public boolean addstu(Student student);

	public boolean updateById(Student student);

	public int findtotalstu();
	
	
}
