package com.student.util;



import com.student.dao.AdminDao;
import com.student.dao.impl.AdminDaoImpl;

public class Utils {
	
	

	public static void getTime(){
		AdminDao adminDao=new AdminDaoImpl();
		adminDao.login_log();
	}
}
