package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class DaoFindBook {
	public ResultSet Findall() throws SQLException {
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
		String sql = "select * from ͼ�� ";
		ResultSet rs = st.executeQuery(sql);
		
		return rs;
	}
	public String  FindOne(String booknumber) throws SQLException
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
		String sql="select * from ͼ��  where ͼ���='"+booknumber+"'";
		ResultSet as = st.executeQuery(sql);
		as.next();
		
		if(as.getInt("�����")==0)
		{
			as.close();
			st.close();
			conn.close();
			return "no";			
		}
		
		return as.getString("ͼ����");
		
	}
	
	public ResultSet FindOneBookFInp(String bookinformation) throws SQLException {
		
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
		String sql="select * from ͼ��  where ͼ���  like '%"+bookinformation+"%' or"+"ͼ����  like '%"+bookinformation+"%'" ;
		ResultSet as = st.executeQuery(sql);
		return as;
		
		
		
	}
	
	

}
