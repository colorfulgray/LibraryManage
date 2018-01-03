package db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;


public class Verification {

	
	public static boolean verification(User user,char judly) throws SQLException
	{
		String url = "jdbc:sqlserver://localhost:1433; DatabaseName=图书管理系统";
		String username = "sa";
		String password = "qwe123";
		try 
		{			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} 
		catch (ClassNotFoundException e) 
		{
		
			e.printStackTrace();
		}
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement st = conn.createStatement();
		
		if(judly=='a'){
		String sql = "select * from 用户  where 学号='"+user.getStudentId()+"'";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		
		if(!user.getPassWord().equals(rs.getString("用户密码")))
		{
			rs.close();
			st.close();	
			conn.close();
			return false;
		}
		else
		{			
			user.setName(rs.getString("姓名"));	
			rs.close();
			st.close();
			conn.close();
			return true;
		}			
	  }
	   else if(judly=='b')
		{
			
			String sql = "select * from 管理员  where 账号='"+user.getStudentId()+"'";
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			
			if(!user.getPassWord().equals(rs.getString("用户密码")))
			{
				rs.close();
				st.close();	
				conn.close();
				return false;
			}
			else
			{			
				user.setName(rs.getString("姓名"));	
				rs.close();
				st.close();
				conn.close();
				return true;
			}	
			
		}
  }

}
