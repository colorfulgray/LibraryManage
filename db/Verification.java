package db;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Verification {

	
	public static boolean verification(User user) throws SQLException
	{
		String url = "jdbc:sqlserver://localhost:1433; DatabaseName=ͼ�����ϵͳ";
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
		String sql = "select id,name,password,email,birthday from users";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		if(rs.getString("����").isEmpty())
		{
			rs.close();
			st.close();
			conn.close();
			return false;						
		}
		if(user.getPassWord()!=rs.getString("����"))
		{
			rs.close();
			st.close();
			conn.close();
			return false;
		}
		else
		{			
			user.setName(rs.getString("����"));	
			rs.close();
			st.close();
			conn.close();
			return true;
		}			
	
	}

}
