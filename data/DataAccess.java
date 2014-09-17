package week04.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

import week04.app.Account;
import week04.app.User;


import java.sql.SQLException;

/**
 * Provides the interface to the datastore
 * For this implementation that is a MySql database
 * 
 * @author scottl
 *
 */
public class DataAccess
{
	/**
	 * Default Constructor
	 */
	public DataAccess()
	{
		
	}
	
	/**
	 * Saves an account
	 * @param account The account to save
	 * @throws AtmDataException if there is a save error
	 */
	public void saveAccount(Account account)
		throws AtmDataException
	{
		Calendar now = Calendar.getInstance();   // Gets the current date and time.
		Date updateDate = new java.sql.Date(now.getTime().getTime());
		
		try
		{
			m_saveAccountStatement.setLong(1, account.getAccountId());
			m_saveAccountStatement.setLong(2, account.getUserId());
			m_saveAccountStatement.setString(3, account.getName());
			m_saveAccountStatement.setDouble(4, account.getBalance());
			m_saveAccountStatement.setDate(5, updateDate);
			m_saveAccountStatement.executeUpdate();
		}
		catch(SQLException ex)
		{
			throw new AtmDataException(ex);
		}				
	}
	
	/**
	 * Get the requested Account
	 * @param accountId Account ID to retrieve
	 * @return An Account reference
	 * @throws AtmDataException
	 */
	public Account getAccount(int accountId)
		throws AtmDataException
	{
		Account account = null;
		ResultSet resultSet = null;
		
		try
		{
			m_selectAccountStatement.setInt(1,accountId); 
			resultSet = m_selectSingleAccountStatement.executeQuery();

			long userId = resultSet.getLong("user_id");
			String name = resultSet.getString("name");
			double balance =  resultSet.getDouble("balance");
			
			User user = getUser(userId);
			
			account = new Account(accountId, user, name, balance);
		}
		catch(SQLException ex)
		{
			// log error
			throw new AtmDataException(ex);
		}
		
		return account;
	}
	
	/**
	 * Gets all the accounts in a list
	 * @return List of Accounts
	 * @throws AtmDataException
	 */
	public List<Account> getAllAccounts()
		throws AtmDataException
	{
		List<Account> accounts = new ArrayList<Account>();
		ResultSet resultSet = null;
		
		try
		{
			resultSet = m_selectAccountStatement.executeQuery();

			while(resultSet.next())
			{
				long accountId = resultSet.getLong("id");
				long userId = resultSet.getLong("user_id");
				String name = resultSet.getString("name");
				double balance =  resultSet.getDouble("balance");
				
				User user = getUser(userId);
				accounts.add(new Account(accountId, user, name, balance));
			}
		}
		catch(SQLException ex)
		{
			// log error
			throw new AtmDataException(ex);
		}
		
		return accounts;
	}
	
	public void saveUser(User user)
		throws AtmDataException
	{
		Calendar now = Calendar.getInstance();   // Gets the current date and time.
		Date updateDate = new java.sql.Date(now.getTime().getTime());
		
		try
		{
			m_saveUserStatement.setLong(1, user.getUserId());
			m_saveUserStatement.setString(2, user.getFirstName());
			m_saveUserStatement.setString(3, user.getLastName());
			m_saveUserStatement.setDate(4, updateDate);
			m_saveUserStatement.executeUpdate();
		}
		catch(SQLException ex)
		{
			throw new AtmDataException(ex);
		}		
	}
	
	public List<User> getUsers()
		throws AtmDataException
	{
		List<User> userList = new ArrayList<User>();
		ResultSet resultSet = null;
		
		try
		{
			resultSet = m_selectUserStatement.executeQuery();

			while(resultSet.next())
			{
				long userId = resultSet.getLong("id");
				String first = resultSet.getString("first_name");
				String last =  resultSet.getString("last_name");
				
				userList.add(new User(userId, first, last));
			}
		}
		catch(SQLException ex)
		{
			// log error
			throw new AtmDataException(ex);
		}
		
		return userList;
	}

	public User getUser(long userId)
		throws AtmDataException
	{
		User user = null;
		ResultSet resultSet = null;
		
		try
		{
			m_selectSingleUserStatement.setLong(1,userId); 
			resultSet = m_selectSingleUserStatement.executeQuery();

			while(resultSet.next())
			{
				String first = resultSet.getString("first_name");
				String last =  resultSet.getString("last_name");
				
				user = new User(userId, first, last);
			}
		}
		catch(SQLException ex)
		{
			// log error
			throw new AtmDataException(ex);
		}
		
		return user;
	}	
	
	public void connect()
		throws AtmDataException
	{
		try
		{
			// this will load the MySQL driver, each DB has its own driver
			  Class.forName("com.mysql.jdbc.Driver");
			  
			  // setup the connection with the DB.
			  m_connect = DriverManager
				      .getConnection("jdbc:mysql://localhost/atm?"
				    		  + "user=root&password=mainroot");
			  // 
			  // pre-compile prepared statements
			  //
			  m_saveUserStatement = m_connect.prepareStatement(INSERT_USER_SQL);
			  m_selectUserStatement = m_connect.prepareStatement(SELECT_USER_SQL);
			  m_selectSingleUserStatement = m_connect.prepareStatement(SELECT_SINGLEUSER_SQL);
			  m_saveAccountStatement = m_connect.prepareStatement(INSERT_ACCOUNT_SQL);
			  m_selectAccountStatement = m_connect.prepareStatement(SELECT_ACCOUNT_SQL);
			  m_selectSingleAccountStatement = m_connect.prepareStatement(SELECT_SINGLEACCOUNT_SQL);
			  
		}
		catch(SQLException ex)
		{
			// log exception 
			System.out.println(ex.getMessage());
			throw new AtmDataException (ex);			
		}
		catch(Exception ex)
		{
			// log exception
			System.out.println(ex.getMessage());
			throw new AtmDataException (ex);		
		}
	}
		
	
	public Connection getConnection()
	{
		return m_connect;
	}
	
	private Connection m_connect = null;
	private PreparedStatement m_saveUserStatement;
	private PreparedStatement m_selectUserStatement;
	private PreparedStatement m_selectSingleUserStatement;
	private PreparedStatement m_saveAccountStatement;
	private PreparedStatement m_selectAccountStatement;
	private PreparedStatement m_selectSingleAccountStatement;
	
	private String INSERT_USER_SQL = "insert into  atm.user values (?, ?, ?, ?)";
	private String SELECT_USER_SQL = "SELECT id, first_name, last_name from atm.user";
	private String SELECT_SINGLEUSER_SQL = "SELECT first_name, last_name from atm.user WHERE id=?";
	private String INSERT_ACCOUNT_SQL = "insert into  atm.account values (?, ?, ?, ?, ?)";
	private String SELECT_ACCOUNT_SQL = "SELECT id, user_id, name, balance from atm.account";
	private String SELECT_SINGLEACCOUNT_SQL = "SELECT user_id, name, balance from atm.account WHERE id=?";

}
