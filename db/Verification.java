package db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;


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
		String sql = "select * from �û�  where ѧ��='"+user.getStudentId()+"'";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		
		if(!user.getPassWord().equals(rs.getString("�û�����")))
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
