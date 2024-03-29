package week04.data;

import java.sql.Connection;
import java.util.List;

import week04.app.User;

public class DataAccessTest
{

	public static void main(String[] args)
	{
		try
		{
			DataAccess da = new DataAccess();
			da.connect();
			
			Connection conn = da.getConnection();
			
			trace("Connected to database: " + conn.toString());
			
			testAddUser(da);
		}
		catch(AtmDataException ex)
		{
			trace(ex.getMessage());
			ex.getStackTrace();			
		}
		catch(Exception ex)
		{
			trace(ex.getMessage());
			ex.getStackTrace();
		}
	}
	
	private static void testAddUser(DataAccess da)
		throws AtmDataException
	{
		trace("Test adding user ...");
		User user = new User(1,"FirstTest", "LastTest");
		da.saveUser(user);
		
		trace("User saved: " + user.toString());
		
		List<User> userList = da.getUsers();
		
		if( userList.size() > 0 )
		{
			String msg = String.format("successful save: Count=%d", userList.size());
			trace(msg);
			dumpList(userList);
		}	
		else
		{
			trace(" failed to save user");
		}
		
		trace("Test adding user complete");
	}
	
	private static void dumpList(List<User> list)
	{
		for(User user : list)
		{
			trace(user.toString());
		}
	}
	
	private static void trace(String msg)
	{
		System.out.println(msg);
	}

}
