/*
 * Created on 2006. 6. 29.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.servlet.support;

import javax.servlet.http.HttpServletRequest;

import maf.web.context.Sites;
import maf.web.mvc.beans.SiteInfo;
import maf.web.servlet.SiteResolver;


public class HeaderSiteResolver extends SiteResolver {
	/**
	 * HttpServletRequest�� header�� hostname ������ �̿� site���� ���� �´�.
	 */
	public SiteInfo resolveSite(HttpServletRequest request) {
		Sites SM = Sites.getInstance();
		
		return  SM.getSiteByName( request.getServerName());
	}
}

