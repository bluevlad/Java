/*
 * SitesTemplate.java, @ 2005-04-22
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.MafProperties;
import maf.base.BaseHttpSession;
import maf.menu.MenuItem;
import maf.menu.TreeMenu;
import maf.util.FileUtils;
import maf.util.StringUtils;
import maf.web.context.BaseHttpServlet;
import maf.web.context.MafConfig;
import maf.web.context.PathInfo;
import maf.web.mvc.beans.MvcInfo;
import maf.web.mvc.beans.SiteInfo;
import maf.web.mvc.exception.ResourceNotFoundException;
import maf.web.session.MySession;
import maf.web.session.exception.SessionInvalidatedException;
import maf.web.session.exception.UnAuthorizedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;

/**
 * @author bluevlad JSP파일에 template관리
 *  
 */
/**
 * @author bluevlad
 *
 */
public class SitesTemplate extends BaseHttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final RE reg_PAGES = new RE("\\.htm$|\\.html$|\\.jsp$|\\/$", RE.MATCH_CASEINDEPENDENT);
	public static final String PROTOCOL = "http://"; 
	private final Log logger = LogFactory.getLog(SitesTemplate.class);
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String URI = request.getRequestURI(); //  =
        String jspfile = StringUtils.getFilename(URI);
        
        java.io.File f = null;
        try {
            
            // Directory 인지 체크
            if (jspfile.lastIndexOf( "." ) < 0) {// page에 . 이 없을경우 Directory로 인정
                // Directory에 /로 안끝 낫으면 "/" 붙혀서 redirect
                if (!MafConfig.reg_LASTSLASH.match( URI )) {
                    logger.debug( "sendRedirect to : " + PROTOCOL + request.getHeader( "HOST" ) + URI + "/" );
                    response.sendRedirect( PROTOCOL + request.getHeader( "HOST" ) + URI + "/" );
                    return;
                } else {
                	PathInfo pathInfo = new PathInfo(request, PathInfo.PATH_MODE_ETC);
                	String body = MafProperties.MVC_SRC_PATH + "/" + pathInfo.getSiteInfo().getSite()+pathInfo.getContextURI();
	                String absPath = getServletContext().getRealPath( body );
	                String[] welcomeFiles = MafConfig.getInstance().getWelcomeFiles();
	                for(int i = 0 ; i < welcomeFiles.length; i++ ) {
	                	if(FileUtils.existFile(absPath, welcomeFiles[i])) {
	                		response.sendRedirect( PROTOCOL + request.getHeader( "HOST" ) + URI + welcomeFiles[i]);
	                        return;
	                	}
	                }
                }

            }
            
            
            if (reg_PAGES.match( jspfile)) {
            	PathInfo pathInfo = new PathInfo(request, PathInfo.PATH_MODE_ETC);
            	StringBuffer body = new StringBuffer(20);
            	body.append(MafProperties.MVC_SRC_PATH);
            	body.append("/");
            	body.append(pathInfo.getSiteInfo().getSite());
            	body.append(pathInfo.getContextURI());
                String path = null;
                String layout = null;
                path = pathInfo.getRequestPath();            	
                path = pathInfo.getContextURI();            	

                // Menu 관리용 mUri 생성
                String mUri = null;
                if ("/".equals( path )) {
                    mUri = "/" + jspfile;
                } else {
                    mUri = path;// + jspfile;
                }
//				String absPath = getServletContext().getRealPath("/") + body.substring(1);
                String absPath = getServletContext().getRealPath( body.toString() );
                logger.debug("absPath: " + absPath);
                f = new java.io.File( absPath );

                if (f.exists()) {
//                    content = new MvcInfo();
                	SiteInfo oSite = null;
                    oSite = pathInfo.getSiteInfo();
                    layout = oSite.getLayout();
                	MvcInfo mvcInfo = new MvcInfo(request,response);
                	mvcInfo.setLastModified( f.lastModified() );
                	mvcInfo.setFile( body.toString() );
                	
                	mvcInfo.setMuri( URI.replaceFirst(MafProperties.MVC_SRC_PATH, MafProperties.MVC_SERVLET_PATH) );
//                	mvcInfo.setMuri( mUri );
                	mvcInfo.setSiteInfo(oSite);

                    TreeMenu oTm = TreeMenu.getInstance( oSite.getSite() );
//                    oTm.setServlet_path(request.getContextPath() + Config.MVC_SERVLET_PATH);
                    MenuItem oM = oTm.findNode( mvcInfo.getMuri());
                    if (oM != null) {
                    	mvcInfo.setPgid( oM.getPgid() );// oTm.findPGID(mUri);
//                    	mvcInfo.setTreeMenu( oTm );
                    	mvcInfo.setCurMenu(oM);
/*                    	if(oM.isLayoutSpecified()) {
                    		String t = "Layout : old=" + oSite.getLayout();
                    		layout = oM.getLayout();
                    		t = t+", new = " + oSite.getLayout();
                    		logger.debug(t);
                    	} else {
                    		logger.debug("layout ================");
                    	}
*/
                    }
                	mvcInfo.setSiteInfo(oSite);
/*                    logger.debug( "body : " + body + "\n absPath :" + absPath + "\n " +
                                  "getLayout : " + oSite.getLayout() + " , " +
                                  " mUri: " + mvcInfo.getMuri() + "\n" + 
                                  " pgid : " + oM.getPgid() + "," + oM.getUrl() + "\n" + 
                              	"c_uri = " + URI.replaceFirst(MafProperties.MVC_SERVLET_PATH, MafProperties.MVC_SRC_PATH));*/
                    
                    BaseHttpSession s = MySession.getSessionBean( request, response );
                    if(oM!= null && oM.isSession_chk()) {
                    	if(s != null) {
                    		if(! oTm.chkAuth_READ(oM.getPgid(), s)) {
                    			throw new UnAuthorizedException(" PAGE 접근 권한이 없습니다. ");
                    		}
                    	} else {
                    		throw new SessionInvalidatedException(" Loin이 필요합니다. ");
                    	}
                    }
                    
                    

                     Forwarder.forward(request, response, mvcInfo, layout);
                } else {
                	throw new ResourceNotFoundException("File Not Found!!!" +pathInfo.getContextURI() + " : " + absPath);
                }

            } else {
                response.sendRedirect( PROTOCOL + request.getHeader("HOST") + URI.replaceFirst(MafProperties.MVC_SERVLET_PATH, MafProperties.MVC_SRC_PATH));
            }

        } catch (Exception e) {
        	ErrorProcess.sendError(response, e);
        } finally {

        	f = null;
         }
    }

    

}
