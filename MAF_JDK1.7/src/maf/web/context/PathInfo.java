/*
 * Created on 2006. 4. 13.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 * maf의 command의 접근 정보
 * URI 부문
 * requestURI : /ehrd/sample/t/default.do
 * contextPath : /ehrd
 * servletPath : /sample maf-config 의 구분 단위 (web.xml참조)
 * contextURI : /t/default.do maf-config 의 uri, command 구분 단위
 */
package maf.web.context;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import maf.base.BaseObject;
import maf.web.mvc.ControllerServlet;
import maf.web.mvc.beans.SiteInfo;
import miraenet.MiConfig;

public class PathInfo extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url = null;
//	String host = null;
	private String requestURI = null;
	private String mUri = null;
	private String contextPath = null;
	private String servletPath = null;
	private String serverName = null;
	private String protocol = null;
	private  String requestPath = null;
	private  Locale locale = null;
	
	private int serverPort = 80;
	private String contextURI = null;
	private String[] dirs = null;
	private SiteInfo siteInfo = null;
	private String jspFile = null;
	
	public static final int PATH_MODE_COMMAND = 0;
	public static final int PATH_MODE_ETC = 1;
	private static final String DEBUG_SEP = "\n";
	public static final String ROOT_PATHINFO_ATTRIBUTE = PathInfo.class + ".REQ";
	
	public PathInfo(HttpServletRequest _req, int mode) {
		this.requestURI = _req.getRequestURI();
		this.contextPath = _req.getContextPath();
		this.serverName = _req.getServerName();
		this.serverPort = _req.getServerPort();
		this.mUri = requestURI.substring(this.contextPath.length());
		this.locale = MafConfig.getInstance().resolveLocale(_req);
		this.protocol = _req.getProtocol();
		if(PathInfo.PATH_MODE_COMMAND==mode) {
			this.url = _req.getRequestURL().toString();
			
			if (this.contextPath.compareTo("") != 0) {
				this.contextURI = this.requestURI.substring(contextPath.length());
			} else {
				this.contextURI = this.requestURI;
			}
			
			this.dirs = contextURI.split("\\/");
//			Logging.showArray(this.getClass(),dirs) ;
			if (this.dirs.length > 2) {
				this.servletPath =  dirs[1];
				this.contextURI = this.contextURI.substring(this.servletPath.length()+2);
			} else {
				this.servletPath = ControllerServlet.GLOBAL_CONFIG;
			}
		}else{
			this.url = _req.getRequestURL().toString();
			this.dirs = this.requestURI.split("\\/");
//			Logging.showArray(this.getClass(),dirs) ;
			this.jspFile = this.dirs[dirs.length - 1];
			this.requestPath = this.requestURI.substring(0,this.requestURI.length()-this.jspFile.length());
			this.contextURI = this.requestURI.substring((this.contextPath+MiConfig.MVC_SERVLET_PATH).length());
		}
		
		
		this.siteInfo = MiConfig.MAF_SITE_RESOLVER.resolveSite(_req);

        
		if (MiConfig.DEBUG) {
			System.out.println("# PathInfo # url : " + url + 
			                   "  protocol : " + protocol +  "  serverName : " + serverName + PathInfo.DEBUG_SEP +
		                   "  Locale : " + locale + 
		                   "  site : " + ((this.siteInfo == null)? " null" : this.siteInfo.getSite()) + 
		                   "  requestURI : " + requestURI +
		                   "  mUri : " + mUri + PathInfo.DEBUG_SEP +
		                   "  contextPath : " + contextPath + 
		                   "  servletPath : " + servletPath + 
		                   "  contextURI : " + contextURI + PathInfo.DEBUG_SEP +
		                   "  requestPath : " + requestPath + 
		                   "  jspFile : " + jspFile + "" +
		                   "");
		}
	}

	/**
	 * Context Path를 돌려준다
	 * (예)
	 * /ehrd/sample/default.do => /ehrd
	 * @return Returns the contextPath.
	 */
	public String getContextPath() {
		return this.contextPath;
	}

	/**
	 * contextURI 를 돌려준다 실제 maf-command의 uri
	 * (예)
	 * /ehrd/sample/default.do => /default.do
	 * /ehrd/sample/t/default.do => /t/default.do
	 * @return Returns the contextURI.
	 */
	public String getContextURI() {
		return this.contextURI;
	}

	/**
	 * @return Returns the dirs.
	 */
	public String[] getDirs() {
		return this.dirs;
	}

	/**
	 * requestURI를 돌려준다
	 * (예)
	 * /ehrd/sample/default.do => /ehrd/sample/default.do
	 * @return Returns the requestURI.
	 */
	public String getRequestURI() {
		return this.requestURI;
	}

	/**
	 * getServletPath를 돌려준다 (maf-config의 분류 단위)
	 * (예)
	 * /ehrd/sample/default.do
	 * /sample
	 * @return Returns the servletPath.
	 */
	public String getServletPath() {
		return this.servletPath;
	}

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return this.url;
	}


	/**
	 * @return Returns the oSite.
	 */
	public final SiteInfo getSiteInfo() {
		return siteInfo;
	}

	/**
	 * @return Returns the serverName.
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @return Returns the serverPort.
	 */
	public int getServerPort() {
		return serverPort;
	}

	/**
	 * @return Returns the jspFile.
	 */
	public String getJspFile() {
		return jspFile;
	}

	/**
	 * http:// [host]:[port][/requestPath]?[get방식의 경우queryString]  에서 /requestPath 을 돌려줌.
	 * @return Returns the requestPath.
	 */
	public String getRequestPath() {
		return requestPath;
	}

	/**
	 * @return Returns the mUri.
	 */
	public String getMUri() {
		return mUri;
	}
	

	
	
}

