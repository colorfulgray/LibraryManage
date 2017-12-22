package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;

public class DaoControl {
	public void addBook(String Booknumber,User user) throws SQLException
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
		String sqlsumbook="select * from 图书  where";
		ResultSet rs = st.executeQuery(sqlsumbook);
		rs.next();
		int number=rs.getInt("库存量");
		number--;
		String sqlborrow ="";
		String sqlchangebook="update 图书表  set 库存 ='"+number+"'";
		ResultSet rc = st.executeQuery(sqlchangebook);
		
		
		
		
		rs.close();
		rc.close();
		st.close();
		conn.close();
	}
	public void Returnbook(String Booknumber,User user) throws SQLException
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
		String sqldeletbook="delete 借阅表  where 图书号='"+Booknumber+"'";				
		ResultSet rs = st.executeQuery(sqldeletbook);
		
		
		
		String sqladdbook="select 库存量 from 图书  where";
		ResultSet ra = st.executeQuery(sqladdbook);
		
		
		
		rs.close();
		ra.close();
		st.close();
		conn.close();
			
	}
	

}
