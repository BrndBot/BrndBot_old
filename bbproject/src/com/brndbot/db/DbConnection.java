package com.brndbot.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import snaq.db.ConnectionPool;

public class DbConnection
{
	static final private String ODBC_DRIVER = "jdbc:mysql://localhost:3306/brndbot";
	static final private String DB_CLASS_NAME = "com.mysql.jdbc.Driver";
	static private Driver _driver;
	static private ConnectionPool _pool;

	private Connection _conn;
	
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

	public void close()
	{
		try 
		{
			_conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
//			System.out.println("Non-graceful database shutdown, continuing...");
		}
	}

	static public DbConnection GetDb()
	{
		DbConnection con = null;
		if (_driver == null)
		{
			try
			{
				_driver = (Driver)Class.forName(DB_CLASS_NAME).newInstance();
				DriverManager.registerDriver(_driver);
				
				//Logger.getRootLogger().removeAllAppenders();
			} 
			catch (InstantiationException e) 
			{
				System.out.println("InstantiationException registering driver: " + e.getMessage());
				e.printStackTrace();
			} 
			catch (IllegalAccessException e) 
			{
				System.out.println("IllegalAccessException registering driver: " + e.getMessage());
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) 
			{
				System.out.println("ClassNotFoundException registering driver: " + e.getMessage());
				e.printStackTrace();
			} 
			catch (SQLException e) 
			{
				System.out.println("SQLException registering driver: " + e.getMessage());
				e.printStackTrace();
			}
			
			if (_driver != null)
			{
				_pool = new ConnectionPool("gpConnections",
			            3,
			            10,
			            20,
			            28000,
			            ODBC_DRIVER,
			            "root",
			            "brnd");
				if (_pool == null) System.out.println("_pool is null");
				con = createViableDbConnection();
/*				int db_schema = SysConfig.isDbSchemaHappy(con); 
				if (db_schema != 0)
				{
					throw new RuntimeException("\nThe database schemas are not synched.  \nThe program has a schema of: " + SysConfig.getCurrentDBSchema() 
							+ "\nThe database has a schema of " + db_schema + "\nRun the scripts in the src\\db\\scripts folder to fix the problem");
				}
*/			}
		}

		if (con == null)
		{
			con = createViableDbConnection();
		}

		return con;
	}

	public void startTransaction() throws SQLException
	{
		_conn.setAutoCommit(false);
	}

	public void commit() throws SQLException
	{
		_conn.commit();
	}

	public void rollback()
	{
		try 
		{
			_conn.rollback();
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
		    con._conn = _pool.getConnection(timeout);
		    if (con._conn == null)
		    {
		        System.out.println("Database pool get connection failed!!!");
		    }
		}
		catch (SQLException sqlx)
		{
			System.out.println("SQLException registering driver: " + sqlx.getMessage());
			sqlx.printStackTrace();
			con = null;
		}
		finally
		{
		}
		return con;
	}
	
	// For SQL that retrieves some information from the database
	public ResultSet QueryDB(String sql, Statement stmt)
	{
		ResultSet rs = null;

		try
		{
			// Get a Statement object
			rs = stmt.executeQuery(sql);
		}
		catch (SQLException ex) 
		{
			System.out.println("*-*-*: " + sql);
			System.out.println("SQLException in QueryDB: " + ex.getMessage());
		}
		return rs;
	}

	// For SQL that retrieves some information from the database
	public ResultSet QueryDBException(String sql, Statement stmt) throws SQLException
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

	public int ExecuteDB(String sql, boolean tossException)
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
			System.out.println("SQLException in ExecuteDB: " + ex.getMessage());
			System.out.println("*-*-*: " + sql);
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
			System.out.println("SQLException in createPreparedStatement: " + e.getMessage());
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
			System.out.println("SQLException in createStatement: " + e.getMessage());
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
			System.out.println("SQLException in createStatement: " + e.getMessage());
		}
		return statement;
	}
}
