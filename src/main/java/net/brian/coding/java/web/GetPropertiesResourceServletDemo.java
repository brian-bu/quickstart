package net.brian.coding.java.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

public final class GetPropertiesResourceServletDemo {
	private GetPropertiesResourceServletDemo() {}
	/**
	 * One way to get resource files.
	 * 
	 * @param context:
	 *            To pass this.getServletContext() as parameter here
	 * @param propPath:
	 *            The propPath should be a string start with /WEB-INF cause this
	 *            API is only for web based functionalities.
	 */
	public static void getRealPathByServletContext(ServletContext context, String propPath) {
		FileInputStream in2 = null;
		try {
			String path = context.getRealPath(propPath);
			in2 = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			System.err.println("getPropertyResourceByServlet:: " + e);
		}
		// This is like a map.
		Properties prop = new Properties();
		try {
			prop.load(in2);
		} catch (IOException e) {
			System.err.println("getPropertyResourceByServlet:: " + e);
		}
		String url = prop.getProperty("url");
		System.out.println(url);
	}
	
	public static void getResourceAsStreamByServletContext(ServletContext context, String propPath) {
		InputStream in1 = context.getResourceAsStream(propPath);
		// This is like a map.
		Properties prop = new Properties();
		try {
			prop.load(in1);
		} catch (IOException e) {
			System.err.println("getPropertyResourceByServlet:: " + e);
		}
		String url = prop.getProperty("url");
		System.out.println(url);
	}
			
}