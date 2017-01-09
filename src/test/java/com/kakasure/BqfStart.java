
package com.kakasure;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 启动jetty
 * 
 * @author zhangb
 *
 */
public class BqfStart {

	public static void main(String[] args) throws Exception {
		Server server = new Server(10003);
		ServletContextHandler context = new WebAppContext("src/main/webapp", "/");
		server.setHandler(context);

		try {
			System.out.println(">>>>>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
			server.start();
			System.in.read();
			System.out.println(">>>>>> STOPPING EMBEDDED JETTY SERVER");
			while (System.in.available() == 0) {
				Thread.sleep(5000);
			}
			server.stop();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}
}
