/*
 * Created on 2006. 5. 17.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import maf.web.exception.ActionConfigurationNotFoundException;
import maf.web.mvc.exception.CommandNotFoundException;
import maf.web.mvc.exception.MethodNotFoundException;
import maf.web.mvc.exception.ResourceNotFoundException;
import maf.web.session.exception.SessionInvalidatedException;
import maf.web.session.exception.UnAuthorizedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ErrorProcess {
	private static Log logger = LogFactory.getLog(ErrorProcess.class);
	public static void sendError(HttpServletResponse response, Throwable e) throws ServletException {
		sendError(response, e, e.getMessage());
	}

	public static void sendError(HttpServletResponse response, Throwable e, String msg) throws ServletException {


		int httpError = 0;
		if (e instanceof CommandNotFoundException) {
			httpError = HttpServletResponse.SC_BAD_REQUEST;
		} else if (e instanceof SessionInvalidatedException) {
			httpError = HttpServletResponse.SC_UNAUTHORIZED;
		} else if (e instanceof UnAuthorizedException) {
			httpError = HttpServletResponse.SC_UNAUTHORIZED;
			throw new UnAuthorizedException(e);
		} else if( e instanceof ResourceNotFoundException) {
			httpError = HttpServletResponse.SC_NOT_FOUND;
		} else if( e instanceof MethodNotFoundException) {
			httpError = HttpServletResponse.SC_NOT_FOUND;
		} else if( e instanceof ActionConfigurationNotFoundException) {
			httpError = HttpServletResponse.SC_NOT_FOUND;			
		} else if ( e instanceof IllegalStateException){
			logger.debug("download cancled");
		} else {
			httpError = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			logger.debug("=========================\n Error : " + httpError + "," + e.getClass() + ":" + msg );
			//throw new ServletException(e);
		}
		

		 try {
			 if (httpError != 0) { 
				 response.reset();
			
				response.sendError(httpError, msg);
			}
		} catch (Exception ei) {
			//throw new ServletException(ei);
		}

	}
}
