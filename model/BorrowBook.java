package model;
import java.sql.SQLException;
import model.User;
import db.DaoFindBook;
import db.DaoControl;
public class BorrowBook {
	
	public boolean  Borrow (String booknumber,User user) throws SQLException
	{
		DaoFindBook find =new DaoFindBook();
		DaoControl control=new DaoControl();
		if(find.FindOne(booknumber)==null)
		{
			
			return false;
		}
		else 
		{
			control.addBook(booknumber, user);
			
			return true;
		}
		
	}
	
	
	

}
