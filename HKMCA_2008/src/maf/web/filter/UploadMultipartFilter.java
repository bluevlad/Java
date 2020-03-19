/*
 * Created on 2006. 5. 9.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import maf.web.http.MyHttpServletRequest;

public class UploadMultipartFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hRequest = (HttpServletRequest) request;

		MyHttpServletRequest wrapper = new MyHttpServletRequest(hRequest);
		chain.doFilter(wrapper, response);

	}

	public void destroy() {

	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
