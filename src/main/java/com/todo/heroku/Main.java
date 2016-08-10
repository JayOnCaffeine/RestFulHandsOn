package com.todo.heroku;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

	public static void main(String args[]) throws Exception{
		String webport = System.getenv("PORT");
		if(webport == null || webport.isEmpty()) {
			webport = "8080";
		}

		final Server server = new Server(Integer.parseInt(webport));
		final WebAppContext webAppContext = new WebAppContext();

		webAppContext.setContextPath("/");
		webAppContext.setParentLoaderPriority(true);

		final String webappDirLocation = "src/main/webapp/";
		webAppContext.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
		webAppContext.setResourceBase(webappDirLocation);

		server.setHandler(webAppContext);

		server.start();
		server.join();
	}

}
