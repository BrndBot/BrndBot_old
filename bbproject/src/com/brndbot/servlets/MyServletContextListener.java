package com.brndbot.servlets;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
//import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;

public class MyServletContextListener implements ServletContextListener {

	//@SuppressWarnings("deprecation")
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {}
        }
//        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//        Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
//        for(Thread t:threadArray) {
//            if(t.getName().contains("Abandoned connection cleanup thread")) {
//                synchronized(t) {
//                    t.stop(); //don't complain, it works
//                }
//            }
//        }
        try {
        	AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// No action needed.
	}

}
