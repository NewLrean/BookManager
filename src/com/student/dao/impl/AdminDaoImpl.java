package com.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.student.dao.AdminDao;
import com.student.entiy.Admin;
import com.student.entiy.Function;
import com.student.entiy.Role;
import com.student.util.Dbconn;

public class AdminDaoImpl implements AdminDao {
	QueryRunner runner=new QueryRunner(Dbconn.getDataSource());
	@Override
	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		try {
			return runner.query("select * from admin where name=? and password=?", new BeanHandler<Admin>(Admin.class),admin.getName(),admin.getPassword());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean login_log() {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn = Dbconn.getConnection();
			String sql="INSERT INTO `admin_login_log`(login_time ) VALUE (?)";
			Date day=new Date();    

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

			System.out.println(df.format(day));
			
			 ps=conn.prepareStatement(sql);
			ps.setString(1, df.format(day));
			ps.executeUpdate();
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Dbconn.closeConn(ps, null, conn);
		}
		
		
		return flag;
	}

	@Override
	public String getlogin_time() {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			 conn=Dbconn.getConnection();
			String sql="SELECT * FROM `admin_login_log` WHERE id=(SELECT MAX(id) FROM `admin_login_log`)-1";
			 ps=conn.prepareStatement(sql);
			 resultSet = ps.executeQuery();
			String time="";
			if(resultSet.next()!=false){
				time =resultSet.getString("login_time");
				
			}
			return time;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Dbconn.closeConn(ps, resultSet, conn);
		}
		return null;
	}

	@Override
	public List<Function> functionsByRole(Role role) {
		// TODO Auto-generated method stub
		try {
			 List<Function> list = runner.query("SELECT * FROM functions WHERE id IN (SELECT functionId FROM roles_functions WHERE roleId = ?)", new BeanListHandler<Function>(Function.class), role.getId());
			if(list!=null){
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Role> findAllRolesByAdmin(Admin admin) {
		// TODO Auto-generated method stub
		try {
			List<Role> list = runner.query("select * from roles where id in (select roleId from admin_roles where adminId = ?)", new BeanListHandler<Role>(Role.class), admin.getId());
			if(list!=null){
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
