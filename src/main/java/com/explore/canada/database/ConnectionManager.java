package com.explore.canada.database;

//import org.apache.log4j.Logger;

import com.explore.canada.config.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton for retrieving connections.
public class ConnectionManager
{
	private static ConnectionManager uniqueInstance = null;
	
	private String dbUrl;
	private String dbUserName;
	private String dbPassword;
	//private Logger logger = Logger.getLogger(this.getClass());
	
	public ConnectionManager()
	{
		IDatabaseConfiguration config = Configuration.instance().getDatabaseConfiguration();
		dbUrl = config.getDatabaseUrl();
		dbUserName = config.getDatabaseUserName();
		dbPassword = config.getDatabasePassword();
	}
	
	public static ConnectionManager instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new ConnectionManager();
		}
		return uniqueInstance;
	}
	
	public Connection getDBConnection() throws SQLException
	{
		return DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
	}
}
