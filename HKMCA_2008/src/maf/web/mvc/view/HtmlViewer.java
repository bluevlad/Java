/*
 * Created on 2006. 11. 09
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.web.mvc.configuration.ViewInfoConfig;

public class HtmlViewer extends AbstractViewer {
	public HtmlViewer() {

	}

	/**
	 * 
	 */
	public void render(ViewInfoConfig viewInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher dispatcher = null;
		if (viewInfo.isRedirect()) {
			response.sendRedirect(request.getContextPath() + "/" + response.encodeRedirectURL(viewInfo.getUri()));

		} else {
			try {
				response.setContentType(super.getContentType());
				
				dispatcher = request.getRequestDispatcher(viewInfo.getRenderFile());
				dispatcher.forward(request, response);
			} finally {
				dispatcher = null;
			}
		}
	}
}
