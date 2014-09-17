/**
 * 
 */
package week04.util;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class AtmLogger

{
	private static FileHandler fileHTML;
	private static FileHandler fileTxt;
	private static Formatter formatterHTML;
	private static Formatter formatterTxt;

	public static void addAtmHandler(Logger logger)
	{
		logger.addHandler(fileTxt);
		logger.addHandler(fileHTML);
	}

	public static void setup()
		throws IOException
	{
		//get the global logger to configure it
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

		// suppresses the logging output to the console
		Handler[] handlers = logger.getHandlers();
		if(handlers.length > 0)
		{
		     if(handlers[0] instanceof ConsoleHandler)
		     {
		           logger.removeHandler(handlers[0]);
		     }

		}

		logger.setLevel(Level.INFO);
		fileTxt = new FileHandler("Logging.txt");
		fileHTML = new FileHandler("Logging.html");
		
		//create a TXT formatter
		formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		logger.addHandler(fileTxt);
		
		//create an HTML formatter
		formatterHTML = new AtmHtmlLoggingFormatter();
		fileHTML.setFormatter(formatterHTML);
		logger.addHandler(fileHTML);
	}

}
