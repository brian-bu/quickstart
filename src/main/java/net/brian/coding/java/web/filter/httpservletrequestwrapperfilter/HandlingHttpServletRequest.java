package net.brian.coding.java.web.filter.httpservletrequestwrapperfilter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

@WebServlet("/HandlingHttpServletRequest")
public class HandlingHttpServletRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandlingHttpServletRequest() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bizBindMsg = IOUtils.toString(request.getInputStream(), "UTF-8");
		bizBindMsg = URLDecoder.decode(bizBindMsg.toString(), "UTF-8");
		System.out.println("HandlingHttpServletRequest接收到请求为: " + bizBindMsg);
		response.getWriter().write("==========success=========");
	}
}
