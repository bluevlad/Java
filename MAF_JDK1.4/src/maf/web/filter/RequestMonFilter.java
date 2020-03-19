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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;
import maf.MafProperties;
import maf.web.context.MafConfig;
import maf.web.context.support.MafWebListner;
import maf.web.requestMon.ServiceTrace;
import maf.web.weblogger.WebLoggerDB;

public class RequestMonFilter implements Filter {
	public static final String KEY_SITEINFO = "MAFCONFIG.SITEINFO";
	public static final String KEY_CONTROLACTIN = "controlaction";
	private final Log logger = LogFactory.getLog(this.getClass());

	public static final RE reg_PAGES = new RE("\\.do$|\\.htm$|\\.html$|\\.jsp$|\\/$", RE.MATCH_CASEINDEPENDENT);
//	private String cpath = null;
	
	public RequestMonFilter() {
		
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//		boolean chk = false;
//		chk = this.checkTarget(req);


		
			ServiceTrace trace = null;
			HttpServletRequest hreq = (HttpServletRequest) req;
			
			// jsp등의 path 
			hreq.setAttribute("CPATH", this.getCpath(hreq));
			// 일반 image path 
			hreq.setAttribute("IPATH", this.getCpath(hreq));
//			// Locale 지원 image path
//			hreg.setAttribute("LIPATH", this.getCpath(hreg));
			
			hreq.setAttribute(KEY_SITEINFO, MafConfig.resolveSite(hreq));
			hreq.setAttribute("control_action",hreq.getRequestURL().toString());
			hreq.setAttribute(KEY_CONTROLACTIN,hreq.getRequestURL().toString());
			MafWebListner.process(hreq, (HttpServletResponse) res);
			try {	
				
				if (MafProperties.USE_SERVICE_TRACE_YN) {
					trace = ServiceTrace.getInstance();
					if(trace != null) {
						trace.startTrace(hreq);
					}
				}
				
				if(MafProperties.LOG_CONNECT_YN) {
					HttpSession s = hreq.getSession();
					if(s != null && s.isNew()) {
						WebLoggerDB.insertHitLog(hreq);
					}
				}
				chain.doFilter(req, res);
			} finally {
				try{
					if(trace != null) {
						trace.endTrace(hreq);
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
