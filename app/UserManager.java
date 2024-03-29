package week04.app;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import week04.AtmException;
import week04.TestLogging;
import week04.util.AtmLogger;

/**
 * @author scottl
 * @version 1.0
 * @created 31-Aug-2014 10:31:08 PM
 */
public class UserManager 
{
	private final static Logger logger = Logger.getLogger(UserManager.class.getName());
	
	/**
	 * Default Constructor
	 */
	public UserManager()
	{
		m_users = new ArrayList<User>();
		AtmLogger.addAtmHandler(logger);// This classes logger
		logger.info("UserManager initialized");		
	}
	
	/**
	 * Adds a user
	 * Verifies the user id, first and last name are provided.
	 * Empty strings are not allowed and a -1 for the id is invalid
	 * 
	 * @param user
	 * @throws AtmException if the User reference is invalid.
	 */
	public void addUser(User user) 
		throws AtmException
	{
		// verify the user has all required data
		verifyUserInfo(user);
				
		m_users.add(user);
		
		logger.info("Added user");
	}

	/**
	 * Return the current list of users
	 * @return List of users.
	 */
	public List<User> getUserList()
	{
		return m_users;
	}
	
	/**
	 * Helper method to verify users content
	 * 
	 * @param user
	 * @throws AtmException
	 */
	private void verifyUserInfo(User user)
			throws AtmException
	{
		if(user.getFirstName() == "" ||
		   user.getLastName() == ""	||	
		   user.getUserId() == -1 )
		{
			throw new AtmException("Invalid User instance. Missing data");
		}
	}
		
	/**
	 * List of active users
	 */
	private List<User> m_users;
	
}//end UserManager