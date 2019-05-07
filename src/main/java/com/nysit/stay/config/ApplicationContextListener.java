package com.nysit.stay.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
//@WebListener
public class ApplicationContextListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext applicationContext=sce.getServletContext();
		String s=applicationContext.getContextPath();
		applicationContext.setAttribute("APP_PATH", s);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
