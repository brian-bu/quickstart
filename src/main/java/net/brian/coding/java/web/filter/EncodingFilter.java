package net.brian.coding.java.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
/**
 * 
 * ±àÂë¹ýÂËÆ÷
 *
 */
@WebFilter(urlPatterns = { "*" }, initParams = { @WebInitParam(name = "encoding", value = "utf-8") })
public class EncodingFilter implements Filter {
	private String defaultEncoding = "utf-8";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(defaultEncoding);
		resp.setCharacterEncoding(defaultEncoding);
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String encoding = config.getInitParameter("encoding");
		if (encoding != null) {
			defaultEncoding = encoding;
		}
	}
}