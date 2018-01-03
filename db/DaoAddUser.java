package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoAddUser {
	
	
	public void addUser(String a, String b, String c)  throws SQLException
	{
		
		String url = "jdbc:sqlserver://localhost:1433; DatabaseName=图书管理系统";
		String username = "sa";
		String password = "123456";
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
		
		
		String sqlAddbook="insert into 用户(学号,姓名,用户密码) values ('"+a+"','"+b+"','"+c+"')";
		st.executeUpdate(sqlAddbook);
		
		
	}

}
