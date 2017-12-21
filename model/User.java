package model;

public class User {
	private  String StudentId;
	private  String PassWord;
	private  String Name;
	
	public void setStudentId(String StudentId)
	{
		
		this.StudentId=StudentId;
		
	}
	public void setPassWord(String PassWord)
	{
		
		this.PassWord=PassWord;
		
	}
	public void setName(String Name)
	{
		
		this.Name=Name;
		
	}
	public String getName()
	{
		
		return Name;
		
	}
	public String getPassWord()
	{
		
		return PassWord;
		
	}
	public String getStudentId()
	{
		
		return StudentId;
		
	}
	
	

}
