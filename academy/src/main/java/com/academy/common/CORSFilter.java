package com.academy.common;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
/**
 * Servlet Filter implementation class CORSFilter
 */
// Enable it for Servlet 3.x implementations
/* @ WebFilter(asyncSupported = true, urlPatterns = { "/*" }) */
public class CORSFilter implements Filter {
 
    /**
     * Default constructor.
     */
    public CORSFilter() {
        // TODO Auto-generated constructor stub
    }
 
    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }
 
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
 
      ((HttpServletResponse) servletResponse).setHeader("Access-Control-Max-Age", "3600"); 
      ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, origin, content-type, accept");
      ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Credentials", "true");
      ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Origin", "*");
      ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE, OPTIONS");
    		
      
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //System.out.println("CORSFilter HTTP Request: " + request.getMethod());
 
        // Authorize (allow) all domains to consume the content
 
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
 
        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (request.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
 
        // pass the request along the filter chain
        chain.doFilter(request, servletResponse);
    }
 
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
 
}