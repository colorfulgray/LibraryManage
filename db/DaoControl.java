package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar; 
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
		String sqlsumbook="select * from ͼ��  where ͼ���='"+Booknumber+"'";
		ResultSet rs = st.executeQuery(sqlsumbook);
		rs.next();
		
		//�����ݲ��뵽���ı���
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format( now ); 
		String sqlInsertToReturn="execute insert_return '"+user.getStudentId()+"','"+Booknumber+"','"+rs.getString("ͼ����")+"','"+time+"'";
		st.executeUpdate(sqlInsertToReturn);
		
		//��ȥͼ����еĿ��
		String sqlsumbook2="select * from ͼ��  where ͼ���='"+Booknumber+"'";
		ResultSet ra = st.executeQuery(sqlsumbook2);
		ra.next();
		int number=ra.getInt("�����");
		number--;
		String sqlchangebook="update ͼ��  set ����� ='"+number+"'"+"where ͼ���='"+ra.getString("ͼ���")+"'";
		st.executeUpdate(sqlchangebook);
		
				
		rs.close();
		ra.close();
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
		String sqldeletbook="delete ����  where ͼ���='"+Booknumber+"'";				
		ResultSet rs = st.executeQuery(sqldeletbook);
		
		
		
		String sqladdbook="select ����� from ͼ��  where";
		ResultSet ra = st.executeQuery(sqladdbook);
		
		
		
		rs.close();
		ra.close();
		st.close();
		conn.close();
			
	}
	

}
