package com.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.student.dao.StudentDao;
import com.student.entiy.PageEntiy;
import com.student.entiy.Student;
import com.student.util.Dbconn;

public class StudentDaoImpl implements StudentDao {

	QueryRunner runner=new QueryRunner(Dbconn.getDataSource());
	@Override
	public List<Student> findAll(PageEntiy entiy) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			conn=Dbconn.getConnection();
			String sql="select * from student where tag=1 limit ?,?";
			 ps=conn.prepareStatement(sql);
			ps.setInt(1, entiy.getPageNum()*entiy.getPageSize());
			ps.setInt(2, entiy.getPageSize());
			resultSet = ps.executeQuery();
			List<Student> list=new ArrayList<Student>();
			while (resultSet.next()) {
				Student student2=new Student();
				student2.setId(resultSet.getInt("id"));
				student2.setName(resultSet.getString("name"));
				student2.setPassword(resultSet.getString("password"));
				student2.setSex(resultSet.getString("sex"));
				student2.setPhone(resultSet.getString("phone"));
				student2.setAddress(resultSet.getString("address"));
				list.add(student2);
			}
				
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Dbconn.closeConn(ps, resultSet, conn);
			
		}
		return null;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		
		try {
			conn=Dbconn.getConnection();
			String sql="update student set tag=0 where id=?";
			ps= conn.prepareStatement(sql);
			ps.setInt(1, id);;
			ps.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Dbconn.closeConn(ps, null, conn);
		}
		return false;
	}

	@Override
	public boolean addstu(Student student) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		
		try {
			conn=Dbconn.getConnection();
			String sql="insert into student(name,password,sex,phone,address) value (?,?,?,?,?)";
			 ps=conn.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getPassword());
			if(Integer.valueOf(student.getSex())==0){
				ps.setString(3, "男");
			}else{
				ps.setString(3, "女");
			}
			ps.setString(4, student.getPhone());
			ps.setString(5, student.getAddress());
			ps.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Dbconn.closeConn(ps, null, conn);
		}
		
		return false;
	}

	@Override
	public boolean updateById(Student student) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		
		try {
			conn=Dbconn.getConnection();
			String sql="update student set name=?,sex=?,phone=?,address=? where id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, student.getName());
			
			if(Integer.valueOf(student.getSex())==0){
				ps.setString(2, "男");
			}else{
				ps.setString(2, "女");
			}
			ps.setString(3, student.getPhone());
			ps.setString(4, student.getAddress());
			ps.setInt(5, student.getId());
			ps.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Dbconn.closeConn(ps, null, conn);
		}
		
		return false;
	}

	@Override
	public int findtotalstu() {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			 conn=Dbconn.getConnection();
			String sql="select count(*) from student";
			 ps=conn.prepareStatement(sql);
			 resultSet = ps.executeQuery();
			while(resultSet.next()){
				int count=resultSet.getInt(1);
				
				return count;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Dbconn.closeConn(ps, resultSet, conn);
			
		}
		return 0;
	}

	

	
	

}
