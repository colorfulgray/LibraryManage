package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoAddUser {
	
	
	public void addUser(String a, String b, String c)  throws SQLException
	{
		
		String url = "jdbc:sqlserver://localhost:1433; DatabaseName=ͼ�����ϵͳ";
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
		
		
		String sqlAddbook="insert into �û�(ѧ��,����,�û�����) values ('"+a+"','"+b+"','"+c+"')";
		st.executeUpdate(sqlAddbook);
		
		
	}

}
