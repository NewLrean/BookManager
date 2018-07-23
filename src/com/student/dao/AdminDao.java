package com.student.dao;

import java.util.List;

import com.student.entiy.Admin;
import com.student.entiy.Function;
import com.student.entiy.Role;

public interface AdminDao {
	public Admin login(Admin admin);
	
	public boolean login_log();
	
	public String getlogin_time();

	public List<Function> functionsByRole(Role role);

	public List<Role> findAllRolesByAdmin(Admin admin);
}
