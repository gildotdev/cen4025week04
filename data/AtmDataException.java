package week04.data;

import week04.AtmException;

/**
 * Data Access Exception class
 * @author scottl
 *
 */
public class AtmDataException extends AtmException
{
	/**
	 * Default constructor
	 */
	public AtmDataException()
	{
		
	}

	/**
	 * Extends base class exception
	 * @param arg0
	 */
	public AtmDataException(String arg0)
	{
		super(arg0);
	}

	/**
	 * Extends base class exception
	 * @param arg0
	 */
	public AtmDataException(Throwable arg0)
	{
		super(arg0);
	}

	/**
	 * Extends base class exception
	 * @param arg0
	 */
	public AtmDataException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

	/**
	 * Extends base class exception
	 * @param arg0
	 */
	public AtmDataException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3)
	{
		super(arg0, arg1, arg2, arg3);
	}

}
