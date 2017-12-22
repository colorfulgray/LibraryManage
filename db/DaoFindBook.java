package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class DaoFindBook {
	public ResultSet Findall() throws SQLException {
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
		String sql = "select * from 图书表 ";
		ResultSet rs = st.executeQuery(sql);
		
		return rs;
	}
	public String  FindOne(String booknumber) throws SQLException
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
		String sql="select * from 图书  where 图书编号='"+booknumber+"'";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		if(rs.getInt("库存量")==0)
		{
			rs.close();
			st.close();
			conn.close();
			return null;
			
		}
		rs.close();
		st.close();
		conn.close();
		return rs.getString("图书");
		
	}
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
		String sql = "select * from 借阅表 where 姓名='"+user.getName()+"'";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		if(rs.getString("用户编号")==null)
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
	

}
