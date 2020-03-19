/*
 * Created on 2006. 7. 12.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.servlet.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.web.mvc.beans.SiteInfo;
import maf.web.servlet.SiteResolver;
import maf.web.util.WebUtils;

public class SessionSiteResolver extends SiteResolver{
	public static final String SITE_SESSION_ATTRIBUTE_NAME = SessionSiteResolver.class.getName() + ".SITE";

	public  SiteInfo resolveSite(HttpServletRequest request) {
		SiteInfo site = (SiteInfo) WebUtils.getSessionAttribute(request, SITE_SESSION_ATTRIBUTE_NAME);
		// specific locale, or fallback to accept header locale?
		return site;
	}

	public  void setSite(HttpServletRequest request, HttpServletResponse response, SiteInfo site) {
		WebUtils.setSessionAttribute(request, SITE_SESSION_ATTRIBUTE_NAME, site);
	}

}

