package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import db.DaoFindBookFRet;
import db.DaoControl;

public class ReturnBook {
	
	public boolean  Return(String booknumber,User user) throws SQLException
	{
		DaoFindBookFRet find =new DaoFindBookFRet();
		DaoControl control=new DaoControl();
		if(find.FindForReturn(user)==null)
		{
			return false;		
		}
		else
		{
			control.Returnbook(booknumber, user);
			return true;		
		}
	
	}
	
	
	public ResultSet FindAllBookForRet(User user) throws SQLException
	{
		
		DaoFindBookFRet find =new DaoFindBookFRet();
		return find.FindAllBookForReturn(user);
		
		
	}
}
