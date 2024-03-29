package week04;

import test.AbstractTestCase;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import week04.util.AtmLogger;
import week04.app.UserManager;

/**
 * Tests the logging subsystem
 * @author scottl
 *
 */
public class TestLogging extends AbstractTestCase
{
	// use the classname for the logger, this way you can refactor
	private final static Logger LOGGER = Logger.getLogger(Logger.class.getName());
	private final static Logger testLogger = Logger.getLogger(TestLogging.class.getName());
	//private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	/**
	 * Protected constructor
	 */
	protected TestLogging()
	{
		super("TestLogging");
	}

	@Override
	protected boolean runTest()
	{
		return testLogger();
	}

	private boolean testLogger()
	{
		boolean result = true;
		try
		{
			AtmLogger.setup();
			AtmLogger.addAtmHandler(LOGGER);	// Logger class
			AtmLogger.addAtmHandler(testLogger);// This class

			// set the LogLevel to Severe, only severe Messages will be written
			LOGGER.setLevel(Level.SEVERE);
			LOGGER.severe("Info Log");
			LOGGER.warning("Info Log");
			LOGGER.info("Info Log");
			LOGGER.finest("Really not important");

			// set the LogLevel to Info, severe, warning and info will be
			// written
			// finest is still not written
			LOGGER.setLevel(Level.INFO);
			LOGGER.severe("Info Log");
			LOGGER.warning("Info Log");
			LOGGER.info("Info Log");
			LOGGER.finest("Really not important");

			// set the LogLevel to Severe, only severe Messages will be written
			testLogger.setLevel(Level.SEVERE);
			testLogger.severe("Info Log");
			testLogger.warning("Info Log");
			testLogger.info("Info Log");
			testLogger.finest("Really not important");

			// set the LogLevel to Info, severe, warning and info will be
			// written
			// finest is still not written
			testLogger.setLevel(Level.INFO);
			testLogger.severe("Info Log");
			testLogger.warning("Info Log");
			testLogger.info("Info Log");
			testLogger.finest("Really not important");
			
			UserManager mgr = new UserManager();
		}
		catch(Exception ex)
		{
			result = false;
			this.addResultMessage("Error testing logger");
			this.addResultMessage(ex.getMessage());
		}
		
		return result;
	}
}
