package model;

import java.sql.SQLException;
import model.User;
import db.DaoFindBook;
import db.DaoControl;

public class ReturnBook {
	
	public boolean  Return(String booknumber,User user) throws SQLException
	{
		DaoFindBook find =new DaoFindBook();
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
}