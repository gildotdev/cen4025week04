package week04.app;

import java.sql.Connection;

import week04.data.DataAccess;

public class AppTest
{

	public static void main(String[] args)
	{
		try
		{
			testUserClassEquals();	
			testUserManager();
		}
		catch(Exception ex)
		{
			trace(ex.getMessage());
			ex.getStackTrace();
		}
	}
	
	private static void testUserManager()
	{
	
	}
	
	private static void testUserClassEquals()
	{
		trace("Testing User class and equals");
		boolean equalTest = true;
		
		// simple user creation
		User user = new User();						
		trace(user.toString());
		
		User user2 = new User();						
		trace(user2.toString());
		
		// expect to be same
		if( !user.equals(user2))
		{
			equalTest = false;
		}
		
		user2.setFirstName("Jim");
		user2.setLastName("Bo");
		user2.setUserId(1);					
		trace(user2.toString());

		// expect to be different
		if( user.equals(user2))
		{
			equalTest = false;
		}
		
		User user3 = new User(user2.getUserId(), user2.getFirstName(), user2.getLastName());		
		trace(user3.toString());
		
		// expect to be same
		if( !user3.equals(user2))
		{
			equalTest = false;
		}
		
		if(equalTest)
		{
			trace("User.equals test passed");
		}
		else
		{
			trace("User.equals test failed");
		}
	}
	
	private static void trace(String msg)
	{
		System.out.println(msg);
	}

}
