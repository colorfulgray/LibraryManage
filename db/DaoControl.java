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
		String sqlsumbook="select * from ͼ��  where";
		ResultSet rs = st.executeQuery(sqlsumbook);
		rs.next();
		int number=rs.getInt("�����");
		number--;
		String sqlborrow ="";
		String sqlchangebook="update ͼ���  set ��� ='"+number+"'";
		ResultSet rc = st.executeQuery(sqlchangebook);
		
		
		
		
		rs.close();
		rc.close();
		st.close();
		conn.close();
	}
	public void Returnbook(String Booknumber,User user) throws SQLException
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
		String sqldeletbook="delete ���ı�  where ͼ���='"+Booknumber+"'";				
		ResultSet rs = st.executeQuery(sqldeletbook);
		
		
		
		String sqladdbook="select ����� from ͼ��  where";
		ResultSet ra = st.executeQuery(sqladdbook);
		
		
		
		rs.close();
		ra.close();
		st.close();
		conn.close();
			
	}
	

}
