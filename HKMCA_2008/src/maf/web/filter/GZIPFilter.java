/*
 * @(#) GZIPFilter.java 2005-02-20
 * 
 * by http://www.onjava.com/pub/a/onjava/2003/11/19/filters.html
 * 
 * Copyright (c) 2005 UBQ All rights reserved.
 * 
 * must set web.xml
 *  
 * 
 */
/* modify web.xml
 <filter>
    <filter-name>Compress</filter-name>
    <filter-class>miraenet.servlet.filter.GZIPFilter</filter-class>
 </filter> 
 <filter-mapping>
    <filter-name>Compress</filter-name>
    <url-pattern>*.jsp</url-pattern>
  </filter-mapping>
  <filter-mapping> 
  	<filter-name>Compress</filter-name>
  	<url-pattern>*.html</url-pattern>
  </filter-mapping>
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
import javax.servlet.http.HttpServletResponse;

import maf.web.wrapper.GZIPResponseWrapper;

/**
 * @author bluevlad
 * @version 1.0
 * @modifydate 2005-02-20
 */
public class GZIPFilter implements Filter {

    public void init(FilterConfig arg0)
        throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            String ae = request.getHeader("accept-encoding");
            if (ae != null && ae.indexOf("gzip") != -1) {
                //System.out.println("GZIP supported, compressing.");
                GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(response);
                chain.doFilter(req, wrappedResponse);
                wrappedResponse.finishResponse();
                return;
            }
            chain.doFilter(req, res);
        }
    }

    public void destroy() {

    }

}