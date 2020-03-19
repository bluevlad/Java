/*
 * Created on 2006. 11. 09
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.view;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.exception.MafException;
import maf.lib.xml.XMLDigester;
import maf.web.context.MafConfig;
import maf.web.http.HttpHeaderSender;
import maf.web.http.MimeType;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.mvc.view.excel.ExcelDrawer;
import maf.web.mvc.view.support.ViewDataSource;
import maf.web.mvc.view.support.ViewerInfoBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExcelViewer extends AbstractViewer {
	private final String digestFile = "/WEB-INF/maf-config/ViewerDigestRules.xml";

	private Log logger = LogFactory.getLog(ExcelViewer.class);

	public ExcelViewer() {
		super.setContentType(MimeType.getMimeType("xls"));
	}

	/**
	 * 
	 */
	public void render(ViewInfoConfig viewInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {

		List list = (List) request.getAttribute(ViewerSupport.DATA_SOURCE_KEY);
		Map parameters = (Map) request.getAttribute(ViewerSupport.PARAM_KEY);
		ViewDataSource ds = new ViewDataSource(list);
		ViewerInfoBean info = (ViewerInfoBean) XMLDigester.digest(MafConfig.getRealPath(viewInfo.getUri()),
																	MafConfig.getRealPath(this.digestFile));
		if (info == null) {
			throw new MafException("info is null!!!  viewInfo : " + viewInfo.getUri());
		} else {
			ExcelDrawer dr = new ExcelDrawer(info, ds, parameters);

			String fileName = MafUtil.nvl( viewInfo.getFilename(),MafUtil.nvl(info.getFilename(), "report.xls"));
			//			response.setContentType(super.getContentType());
			//	
			//			String fileName = MafUtil.nvl(info.getFilename(),"report.xls");
			//			fileName = i18n.convertTo(fileName,"UTF-8","8859_1") ;
			//			//response.setHeader( "Content-type","application/x-msdownload" );
			//			response.setHeader( "Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			//			response.setHeader( "Content-Transfer-Encoding", "binary" );
//			logger.debug("filename : [" + fileName + "]");
			HttpHeaderSender.setDownLoad(request, response, fileName, true, true);
			ServletOutputStream ouputStream = response.getOutputStream();
			try {
				dr.write(ouputStream);
				ouputStream.flush();
			} finally {
				if (ouputStream != null) {
					try {
						ouputStream.close();
					} catch (IOException ex) {
					}
				}
			}
		}
	}
}
