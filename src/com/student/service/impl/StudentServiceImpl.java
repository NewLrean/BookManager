package com.student.service.impl;

import java.util.List;


import com.student.dao.StudentDao;
import com.student.dao.impl.StudentDaoImpl;
import com.student.entiy.PageEntiy;
import com.student.entiy.Student;
import com.student.service.StudentService;

public class StudentServiceImpl implements StudentService {

	StudentDao studentDao=new StudentDaoImpl();
	@Override
	public List<Student> findAll(PageEntiy entiy) {
		// TODO Auto-generated method stub
		return studentDao.findAll(entiy);
	}
	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return studentDao.deleteById(id);
	}
	@Override
	public boolean addstu(Student student) {
		// TODO Auto-generated method stub
		return studentDao.addstu(student);
	}
	@Override
	public boolean updateById(Student student) {
		// TODO Auto-generated method stub
		return studentDao.updateById(student);
	}
	@Override
	public int findtotalstu() {
		// TODO Auto-generated method stub
		return studentDao.findtotalstu();
	}
	
	
	
	
	

}
