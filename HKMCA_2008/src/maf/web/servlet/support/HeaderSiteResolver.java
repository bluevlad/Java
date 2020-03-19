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
	 * HttpServletRequest의 header의 hostname 정보를 이용 site명을 가져 온다.
	 */
	public SiteInfo resolveSite(HttpServletRequest request) {
		Sites SM = Sites.getInstance();
		
		return  SM.getSiteByName( request.getServerName());
	}
}

