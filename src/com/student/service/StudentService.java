package com.student.service;

import java.util.List;

import com.student.entiy.PageEntiy;
import com.student.entiy.Student;

public interface StudentService {
	public List<Student> findAll(PageEntiy entiy);
	
	public boolean deleteById(int id);
	
	public boolean addstu(Student student);
	
	public boolean updateById(Student student);
	
	public int findtotalstu();
}
