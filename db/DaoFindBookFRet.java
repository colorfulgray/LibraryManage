package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class DaoFindBookFRet {
	public String FindForReturn(User user) throws SQLException
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
		String sql = "select * from 借阅  where 姓名='"+user.getName()+"'";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		if(rs.getString("学号")==null)
		{
			rs.close();
			st.close();
			conn.close();
			return null;
			
		}
		rs.close();
		st.close();
		conn.close();
		return "ok";
		
		
	}
	public ResultSet FindAllBookForReturn(User user) throws SQLException
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
		String sqlfind="select 图书名,图书号,借阅日期 from 借阅表 where 学号='"+user.getStudentId()+"'";
		ResultSet rs = st.executeQuery(sqlfind);
		return  rs;
		
		
	}

}
