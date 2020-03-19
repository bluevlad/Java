/*
 * Created on 2006. 11. 09
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.web.mvc.configuration.ViewInfoConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Render {
	private final Log logger = LogFactory.getLog(Render.class);

	ViewInfoConfig viewInfo = null;

	Viewer viewer = null;

	public Render(ViewInfoConfig viewInfo) throws Exception {
		if(viewInfo == null) {
			throw new Exception("View info is null!!!");
		}
		this.viewInfo = viewInfo;
		viewer = MafView.getViewer(viewInfo.getType());
//		if(MiConfig.DEBUG){
//			logger.debug("viewer : " + viewer.getClass());
//		}
	}

	public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (viewer != null) {
			viewer.render(viewInfo, request, response);
		}
	}
}
