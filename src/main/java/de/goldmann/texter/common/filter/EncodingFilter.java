package de.goldmann.texter.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter sets encoding to "utf-8".
 * 
 * @author goldmannm
 *
 */
public class EncodingFilter implements Filter {

	private String encoding = "utf-8";

	@Override
	public void destroy() {
		// nothing todo

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {

		String encodingParam = config.getInitParameter("encoding");
		if (encodingParam != null) {
			encoding = encodingParam;
		}
	}
}
