package model;

import java.sql.SQLException;
import db.DaoAddUser;

public class AddUser {
	
	
	public boolean AddBook(String a,String b,String c) throws SQLException
	{
		DaoAddUser Add=new DaoAddUser();
		Add.addUser(a,b,c);
		return true;
		
	}
	

}
