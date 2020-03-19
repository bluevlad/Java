/*
 * Forward.java, @ 2005-05-02
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.MafProperties;
import maf.logger.Trace;
import maf.web.context.MafConfig;
import maf.web.mvc.beans.MvcInfo;
import maf.web.mvc.beans.SiteInfo;
import maf.web.mvc.configuration.TemplateInfoConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author goindole
 *  Servlet을 위한 Controller Servlet 과 jsp를 위한 
 *  SitesTemplate 에서 생성 된 정보를 layout로 forward 해준다.
 */
public class Forwarder {
	private static final Log logger = LogFactory.getLog(Forwarder.class);
	
    public static void forward(HttpServletRequest request, HttpServletResponse response, MvcInfo content, String layout) 
    throws ServletException, IOException {
    	RequestDispatcher dispatcher = null;
    	SiteInfo siteInfo = MafConfig.resolveSite(request);
        try {
            request.setAttribute( "MAF_INFO", content );

            TemplateInfoConfig templateInfo = ControllerServlet.getTemplate(layout);
            
            if(templateInfo != null) {
            	logger.debug(layout + " : template is " + templateInfo.getUri(siteInfo));
	            dispatcher = request.getRequestDispatcher( templateInfo.getUri(siteInfo) );
	            dispatcher.forward( request, response );
            } else {
            	logger.debug(layout + " : template info is null");
	            dispatcher = request.getRequestDispatcher( content.getFile() );
	            
	            dispatcher.forward( request, response ); 	
	            
            }
        } catch (Exception e) {
        	
            logger.error( Trace.getStackTrace( e ) );
            response.reset();
            response.sendError( HttpServletResponse.SC_BAD_REQUEST );
        } finally {
        	dispatcher = null;
        }
        
    }
}
