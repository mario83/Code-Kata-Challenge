/**
 * 
 */

package it.manza.bbc.challenge;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * @author mario
 */
public class App {

	public static void main(String[] args) throws Exception {
	
		Server server = new Server(2222);
		
		ServletContextHandler context =
				new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		
		context.setContextPath("/");
		server.setHandler(context);
		
		ServletHolder servletHolder = 
				context.addServlet(ServletContainer.class, "/*");
		servletHolder.setInitOrder(1);
		servletHolder.setInitParameter("jersey.config.server.provider.packages", 
                "it.manza.bbc.challenge");

		try {
			server.start();
			server.join();
		}
		finally {
			server.destroy();
		}
	}
}
