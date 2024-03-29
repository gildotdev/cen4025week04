package week04.app;

public class Account
{
	/**
	 * Default constructor
	 */
	public Account()
	{
		this(-1, new User(), "Default Account", 0.0);
	}
	
	/**
	 * Parameterized constructors
	 * @param id account id
	 * @param user User reference
	 * @param name account name
	 * @param balanace Account balance
	 */
	public Account(long id, User user, String name, double balance)
	{
		m_accountId = id;
		m_user = user;
		m_accountName = name;
		m_balance = balance;
	}
	
	/**
	 * @return the m_userId
	 */
	public long getAccountId()
	{
		return m_accountId;
	}
	
	public User getUser()
	{
		return m_user;
	}
	
	/**
	 * @return the m_userId
	 */
	public long getUserId()
	{
		return m_user.getUserId();
	}
	
	/**
	 * @param m_userId the m_userId to set
	 */
	public void setUser(User user)
	{
		this.m_user = user;
	}
		
	/**
	 * @return the m_firstName
	 */
	public String getName()
	{
		return m_accountName;
	}

	/**
	 * @param m_firstName the m_firstName to set
	 */
	public void setName(String name)
	{
		this.m_accountName = name;
	}
	
	/**
	 * @return the m_balance
	 */
	public double getBalance()
	{
		return m_balance;
	}

	/**
	 * @param m_balance the m_balance to set
	 */
	public void setBalance(double m_balance)
	{
		this.m_balance = m_balance;
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean result = true;
		
		if( obj == null )
		{
			result = false;
		}
		else if( getClass() != obj.getClass())
		{
			result = false;
		}
		else
		{
			// valid User object, check the contents
			final Account otherUser = (Account) obj;
			if( !this.m_accountName.equals(otherUser.m_accountName) ||
				!this.m_user.equals(otherUser.m_user) ||
				!(this.m_balance == otherUser.m_balance) ||
				!(this.m_accountId == otherUser.m_accountId))
			{
				result = false;
			}
		}
				
		return result;
	}
	
	@Override
	public String toString()
	{
		String fmt = String.format("ID: %d, %s %f", this.m_accountId, this.m_accountName, this.m_balance);
		return fmt;
	}
	
	private long m_accountId;
	private User m_user;
	private String m_accountName;
	private double m_balance;


}
