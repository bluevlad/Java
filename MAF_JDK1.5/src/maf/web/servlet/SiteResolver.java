/*
 * Created on 2006. 6. 29.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.servlet;

import javax.servlet.http.HttpServletRequest;

import maf.web.mvc.beans.SiteInfo;

public abstract class SiteResolver {
	public abstract SiteInfo resolveSite(HttpServletRequest request) ;
}

