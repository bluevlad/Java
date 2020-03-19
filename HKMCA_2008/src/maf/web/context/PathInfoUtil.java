/*
 * Created on 2006. 7. 3.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.context;

import javax.servlet.http.HttpServletRequest;

public class PathInfoUtil {
	public static PathInfo getPathInfo(HttpServletRequest  _req) {
		PathInfo pathInfo = (PathInfo) _req.getAttribute(PathInfo.ROOT_PATHINFO_ATTRIBUTE);
		if(pathInfo == null) {
			pathInfo = new PathInfo(_req, PathInfo.PATH_MODE_COMMAND);
			_req.setAttribute(PathInfo.ROOT_PATHINFO_ATTRIBUTE, pathInfo);
		}
		return pathInfo;
	}
}

