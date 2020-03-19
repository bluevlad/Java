/*
 * Created on 2006. 5. 17.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.context;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class BaseHttpServlet extends HttpServlet{
    protected Log logger = LogFactory.getLog(getClass());
    
	public void init(ServletConfig config) throws ServletException {
		System.out.println("### " + getClass() + " init ### ");
		super.init(config);
	}
	
	public void destroy() {
		System.out.println("### " + getClass() + " destroy ### ");
		super.destroy();
	}
	

    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	preProcess(request, response);
	}

	protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		preProcess(request, response);
	}

	/**
	 * 실제 servlet 수행전 처리 작업
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void preProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}
	
	protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
}

