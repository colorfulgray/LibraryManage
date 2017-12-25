package model;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DaoFindBook;
public class FindBook {
	public boolean FindOneBook(String booknumber) throws SQLException
	{
		DaoFindBook find=new DaoFindBook();
		if(find.FindOne(booknumber)==null)
		{
			return false;
			
		}
		else
		{
			
			return true;
		}
		
	}
		
	public ResultSet  FindOneBookByInput(String bookinfomation) throws SQLException
	{
		DaoFindBook find=new DaoFindBook();
		return find.FindOneBookFInp(bookinfomation);
		
	}
	
	
	public ResultSet FindAllBook() throws SQLException
	{
		
		DaoFindBook find=new DaoFindBook();
		return find.Findall();	
		
	}

}
