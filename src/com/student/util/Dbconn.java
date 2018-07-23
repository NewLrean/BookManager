package com.student.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;





public class Dbconn {
	static String className=null;
	static String url = null;
	static String username=null;
	static String password =null;
	static Connection  conn = null; 
	static ResultSet rs = null;  
    static PreparedStatement ps =null; 
    static DataSource dataSource=null;
   
    static{
    	try {
    		ClassLoader cl=Dbconn.class.getClassLoader();
			InputStream in=cl.getResourceAsStream("jdbc.properties");
			Properties properties=new Properties();
			properties.load(in);
			dataSource=BasicDataSourceFactory.createDataSource(properties);
			/*className=properties.getProperty(Content.CLASS_NAME);
			url=properties.getProperty(Content.URL);
			username=properties.getProperty(Content.USERNAME);
			password=properties.getProperty(Content.PASSWORD);
			Class.forName(className);
			
			conn = DriverManager.getConnection(url,username,password);*/
		} catch (Exception e) {
			System.out.println("init 执行失败");
            e.printStackTrace();
		}
    }
    
    public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
    
    public static DataSource getDataSource() {
		return dataSource;
	}
    
    public static int addUpdDel(String sql){
    	int i=0;
    	PreparedStatement ps;
    	Connection connection;
		try {
			connection=Dbconn.getConnection();
			ps = connection.prepareStatement(sql);
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("sql增删改查执行失败");
			e.printStackTrace();
		}
		return i;
    	
    }
    
    public static ResultSet selectSql(String sql){  
    	Connection connection;
        try {  
        	connection=Dbconn.getConnection();
            ps =  connection.prepareStatement(sql);  
            rs =  ps.executeQuery(sql);  
        } catch (SQLException e) {  
            System.out.println("sql查询失败");  
            e.printStackTrace();  
        }  
        return rs;  
    }  
    public static void closeConn(PreparedStatement ps,ResultSet rs,Connection  conn){  
        try {  
        	if(ps!=null)
        	ps.close();
        	if(rs!=null)
        	rs.close();
        	if(conn!=null)
        		conn.close();
        } catch (SQLException e) {  
            System.out.println("sql关闭连接");  
            e.printStackTrace();  
        }  
    }  
}
