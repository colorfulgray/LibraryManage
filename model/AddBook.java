package model;

import java.sql.SQLException;

import db.DaoAddBook;

public class AddBook {

	public boolean AddBook(String a, String b, int sum,String d,String e,String f) throws SQLException
	{
		DaoAddBook Add=new DaoAddBook();
				
		Add.addBook(a,b,sum,d,e,f);
		return true;
		
	}
	
	
	
}
