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
		String sql = "select * from ����  where ����='"+user.getName()+"'";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		if(rs.getString("ѧ��")==null)
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
		String sqlfind="select ͼ����,ͼ���,�������� from ���ı� where ѧ��='"+user.getStudentId()+"'";
		ResultSet rs = st.executeQuery(sqlfind);
		return  rs;
		
		
	}

}
