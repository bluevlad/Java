/*
 * Created on 2006. 07. 26
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.web.mvc.configuration.ViewInfoConfig;


public interface Viewer {
	void render(ViewInfoConfig viewInfo, HttpServletRequest request, HttpServletResponse response) throws Exception;

}

