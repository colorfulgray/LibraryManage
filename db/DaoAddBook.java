package db;

import java.sql.*;
import model.FindBook;
public class DaoAddBook {

	public void addBook(String a, String b, int sum,String d,String e1,String f)  throws SQLException
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
		
	    String sqlAddbook="insert into ͼ��(ͼ����,ͼ���,�����,������,��������,����) values ('"+a+"','"+b+"','"+sum+"','"+d+"','"+e1+"','"+f+"')";
		st.executeUpdate(sqlAddbook);
		
		
		
		
		
		
	}
	
	
	
	
	
	
	

}
