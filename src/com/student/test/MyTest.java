			package com.student.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

import org.junit.Test;
import org.springframework.util.DigestUtils;

import com.student.dao.AdminDao;
import com.student.dao.impl.AdminDaoImpl;
import com.student.entiy.Admin;
import com.student.entiy.Student;
import com.student.proxy.DynamicProxy;
import com.student.service.StudentService;
import com.student.service.impl.StudentServiceImpl;
import com.student.util.SecurityUtils;

public class MyTest {
	
	
	
	@Test
	public void show(){
		StudentService studentService=new StudentServiceImpl();
		Student student=new Student();
		List<Student> list = studentService.findAll(student);
		System.out.println(list);
	}
	
	@Test
	public void showadmin(){
		
		AdminDao adminDao=new AdminDaoImpl();
		Admin admin=new Admin();
		admin.setName("admin_root");
		admin.setPassword("f1e9380b998e8f63aae91a3484f70766");
		if(adminDao.login(admin)!=null){
			System.out.println("登陆成功");
		}
		
	}
	
	@Test
	public void showtime(){
		AdminDao adminDao=new AdminDaoImpl();
		InvocationHandler handler=new DynamicProxy(adminDao);
		
		AdminDao dao=(AdminDao) Proxy.newProxyInstance(handler.getClass().getClassLoader(), adminDao.getClass().getInterfaces(),
				handler);
		Admin admin=new Admin();
		admin.setName("admin_root");
		admin.setPassword("123456789");
		dao.login(admin);
	
	}
	
	@Test
	public void Md5Test(){
		String md5 = SecurityUtils.MD5("clearlove");
		System.out.println(md5);
		System.out.println(DigestUtils.md5DigestAsHex("clearlove".getBytes()));
		System.out.println(SecurityUtils.BASE64("clearlove"));
		System.out.println(SecurityUtils.BASE64D(SecurityUtils.BASE64("clearlove")));
	}
}
