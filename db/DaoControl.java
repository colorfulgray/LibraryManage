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
		String sqlsumbook="select * from 图书  where 图书号='"+Booknumber+"'";
		ResultSet rs = st.executeQuery(sqlsumbook);
		rs.next();
		
		//把数据插入到借阅表中
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format( now ); 
		String sqlInsertToReturn="execute insert_return '"+user.getStudentId()+"','"+Booknumber+"','"+rs.getString("图书名")+"','"+time+"'";
		st.executeUpdate(sqlInsertToReturn);
		
		//减去图书表中的库存
		String sqlsumbook2="select * from 图书  where 图书号='"+Booknumber+"'";
		ResultSet ra = st.executeQuery(sqlsumbook2);
		ra.next();
		int number=ra.getInt("库存量");
		number--;
		String sqlchangebook="update 图书  set 库存量 ='"+number+"'"+"where 图书号='"+ra.getString("图书号")+"'";
		st.executeUpdate(sqlchangebook);
		
				
		rs.close();
		ra.close();
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
		String sqldeletbook="delete 借阅  where 图书号='"+Booknumber+"'";				
		ResultSet rs = st.executeQuery(sqldeletbook);
		
		
		
		String sqladdbook="select 库存量 from 图书  where";
		ResultSet ra = st.executeQuery(sqladdbook);
		
		
		
		rs.close();
		ra.close();
		st.close();
		conn.close();
			
	}
	

}
