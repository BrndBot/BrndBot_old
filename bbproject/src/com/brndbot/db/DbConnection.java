/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.system.BrndbotException;
import com.brndbot.system.SystemProp;

import snaq.db.ConnectionPool;

/** This embodies a series of interactions with the database. 
 */
public class DbConnection
{
	
	final static Logger logger = LoggerFactory.getLogger(DbConnection.class);
	
//	static final private String ODBC_DRIVER = "jdbc:mysql://localhost:3306/brndbot";
	static final private String DB_CLASS_NAME = "com.mysql.jdbc.Driver";
	static private Driver _driver;
	static private ConnectionPool _pool;

	private Connection _conn;
	private boolean saveAutoCommit;
	
	//Static class instantiation
	public Connection getConnection() 
	                      throws SQLException
	  {
		  return DriverManager.getConnection("jdbc:jdc:jdcpool");
	  }

	private DbConnection() 
	{ 
		_conn = null;
	}

	/** Close the DbConnection. It's mandatory to close after creating
	 *  an object of this class. 
	 */
	public void close()
	{
		try 
		{
			_conn.close();
		} 
		catch (SQLException e) 
		{
			logger.error("Error closing DbConnection: {}", e.getClass().getName());
			e.printStackTrace();
//			System.out.println("Non-graceful database shutdown, continuing...");
		}
	}

	static public DbConnection GetDb() throws BrndbotException
	{
		DbConnection con = null;
		if (_driver == null)
		{
			try
			{
				logger.debug("In GetDb");
				_driver = (Driver)Class.forName(DB_CLASS_NAME).newInstance();
				DriverManager.registerDriver(_driver);
				
				//Logger.getRootLogger().removeAllAppenders();
			} 
			catch (InstantiationException e) 
			{
				logger.error("InstantiationException registering driver: " + e.getMessage());
				e.printStackTrace();
			} 
			catch (IllegalAccessException e) 
			{
				logger.error("IllegalAccessException registering driver: " + e.getMessage());
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) 
			{
				logger.error("ClassNotFoundException registering driver: " + e.getMessage());
				e.printStackTrace();
			} 
			catch (SQLException e) 
			{
				logger.error("SQLException registering driver: " + e.getMessage());
				e.printStackTrace();
			}
			
			if (_driver != null)
			{
				logger.debug ("getting connection pool, URL = {}", SystemProp.get(SystemProp.DB_URL));
				try {
					_pool = new ConnectionPool("gpConnections",
			            3,
			            10,
			            20,
			            28000,
			            "jdbc:mysql://" + SystemProp.get(SystemProp.DB_URL),
			            SystemProp.get(SystemProp.DB_USER),
			            SystemProp.get(SystemProp.DB_PW));
				} catch (Exception e) {
					logger.error (e.getClass().getName());
				} catch (Error ee) {
					logger.error ("ERROR: {}", ee.getClass().getName());
				}
				if (_pool == null) {
					logger.error("_pool is null");
				} else {
					logger.debug("Got _pool");
				}
				con = createViableDbConnection();
/*				int db_schema = SysConfig.isDbSchemaHappy(con); 
				if (db_schema != 0)
				{
					throw new RuntimeException("\nThe database schemas are not synched.  \nThe program has a schema of: " + SysConfig.getCurrentDBSchema() 
							+ "\nThe database has a schema of " + db_schema + "\nRun the scripts in the src\\db\\scripts folder to fix the problem");
				}
*/			}
		}

		if (con == null) {
			con = createViableDbConnection();
		}
		if (con == null) {
			// Failed twice. Throw an exception.
			throw new BrndbotException ("Unable to get database connection");
		}

		return con;
	}

	public void startTransaction() throws SQLException
	{
		saveAutoCommit = _conn.getAutoCommit();
		_conn.setAutoCommit(false);
	}

	public void commit() throws SQLException
	{
		_conn.commit();
		_conn.setAutoCommit(saveAutoCommit);
	}

	public void rollback()
	{
		try 
		{
			_conn.rollback();
			_conn.setAutoCommit(saveAutoCommit);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Database rollback failed! Error: " + e.getMessage());
		}
	}

	static private DbConnection createViableDbConnection()
	{
		DbConnection con = new DbConnection();

		final long timeout = 3000;  // 3 second timeout
		try
		{
			//logger.debug ("Getting connection, Is con null? " + (con == null));
			//logger.debug ("Getting connection, Is _pool null? " + (_pool == null));
		    con._conn = _pool.getConnection(timeout);
		    if (con._conn == null)
		    {
		        logger.error("Database pool get connection failed!!!");
		    }
		}
		catch (SQLException sqlx)
		{
			logger.error("SQLException registering driver: " + sqlx.getMessage());
			sqlx.printStackTrace();
			con = null;
		}
		finally
		{
		}
		return con;
	}
	
	/** Issue a database query. This is protected so that only db classes can
	 *  user it. */
	// For SQL that retrieves some information from the database
	protected ResultSet QueryDB(String sql, Statement stmt)
	{
		ResultSet rs = null;

		try
		{
			// Get a Statement object
			rs = stmt.executeQuery(sql);
		}
		catch (SQLException ex) 
		{
			logger.error("SQLException in QueryDB: {}", ex.getMessage());
		}
		return rs;
	}

	// For SQL that retrieves some information from the database
	protected ResultSet QueryDBException(String sql, Statement stmt) throws SQLException
	{
		ResultSet rs = null;

		// Get a Statement object
		rs = stmt.executeQuery(sql);
		return rs;
	}

	// For SQL that updates the database in some way
/*	public int ExecuteDB(String sql)
	{
		int res = 0;
		try 
		{
			res = _executeDB(sql, false);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return res;
	}
*/	

	protected int ExecuteDB(String sql, boolean tossException)
		throws SQLException
	{
		Statement stmt = null;
		int result = 0;

		try 
		{
			stmt = _conn.createStatement();
			result = stmt.executeUpdate(sql);
		} 
		catch (SQLException ex) 
		{
			logger.error("SQLException in ExecuteDB: " + ex.getMessage());
			if (tossException)
			{
				throw new SQLException(ex.getMessage());
			}
		}
		finally 
		{
			// Release the statement
			DbUtils.close(stmt);
		}
		return result;
	}

	public PreparedStatement createPreparedStatement(String sql)
	{ 
		PreparedStatement statement = null;
		try 
		{
			statement = _conn.prepareStatement(sql);
		} 
		catch (SQLException e) 
		{
			logger.error("SQLException in createPreparedStatement: {}", e.getMessage());
		}
		return statement;
	}

	public Statement createStatement(int scroll, int read_only) 
	{ 
		Statement statement = null;
		try 
		{
			statement = _conn.createStatement(scroll, read_only);
		} 
		catch (SQLException e) 
		{
			logger.error("SQLException in createStatement: ", e.getMessage());
		}
		return statement;
	}
	
	public Statement createStatement() 
	{ 
		Statement statement = null;
		try 
		{
			statement = _conn.createStatement();
		} 
		catch (SQLException e) 
		{
			logger.error("SQLException in createStatement: {}", e.getMessage());
		}
		return statement;
	}
}
