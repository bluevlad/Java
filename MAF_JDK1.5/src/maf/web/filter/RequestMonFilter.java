/*
 * Created on 2005. 5. 27.
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
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
import javax.servlet.http.HttpSession;

import maf.web.requestMon.ServiceTrace;
import maf.web.weblogger.WebLoggerDB;
import miraenet.MiConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;

public class RequestMonFilter implements Filter {
	public static final String KEY_SITEINFO = "SITEINFO";
	private final Log logger = LogFactory.getLog(RequestMonFilter.class);

	public static final RE reg_PAGES = new RE("\\.do$|\\.htm$|\\.html$|\\.jsp$|\\/$", RE.MATCH_CASEINDEPENDENT);
//	private String cpath = null;
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//		boolean chk = false;
//		chk = this.checkTarget(req);


			ServiceTrace trace = null;
			HttpServletRequest hreg = (HttpServletRequest) req;
			// jsp등의 path 
			hreg.setAttribute("CPATH", this.getCpath(hreg));
			// 일반 image path 
			hreg.setAttribute("IPATH", this.getCpath(hreg));
			// Locale 지원 image path
			hreg.setAttribute("LIPATH", this.getCpath(hreg));
			
			hreg.setAttribute(KEY_SITEINFO, MiConfig.MAF_SITE_RESOLVER.resolveSite(hreg));
			hreg.setAttribute("control_action",hreg.getRequestURL().toString());
			hreg.setAttribute("controlaction",hreg.getRequestURL().toString());

			try {			
				trace = ServiceTrace.getInstance();
				if(trace != null) {
					trace.startTrace(hreg);
				}
				
				HttpSession s = hreg.getSession();
				if(s != null && s.isNew()) {
					WebLoggerDB.getInstance().insertHitLog(hreg);
				}
				chain.doFilter(req, res);
			} finally {
				try{
					if(trace != null) {
						trace.endTrace(hreg);
					}
				} catch (Throwable _ignore) {} ;
				trace = null;
			}
			

	}

	public void destroy() {
		System.out.println(">>> " + this.getClass() + " destroy...");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println(">>> " + this.getClass() + " init...");
	}
	

	
	/*
	 * WEB.XML에서 filter를 걸음 .jsp 와 .do에 대해서 
	 * 그렇지 않을 경우 Chek 필요 
	 * 
	 */	
	private boolean checkTarget(HttpServletRequest req) {
		boolean chk = false;
		String file = req.getRequestURI();
		if (file != null) {
			chk = reg_PAGES.match(file);
		}
		logger.debug(" Check Target : " +file + ", " +chk);
		return chk;
	}
	
	private String getCpath(HttpServletRequest req) {
		StringBuffer url = new StringBuffer(20);
		url.append("http://");
		url.append(req.getHeader("host"));
		url.append(req.getContextPath());
		return url.toString();
	}

}
