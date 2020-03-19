/*
 * Created on 2006. 11. 09
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.view;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.web.mvc.configuration.ViewInfoConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlViewer extends AbstractViewer {
	final private Log logger = LogFactory.getLog(this.getClass());
	
	public XmlViewer() {
		//super.setContentType(MimeType.getMimeType("xml"));
		super.setContentType("application/xml");
	}

	/**
	 * 
	 */
	public void render(ViewInfoConfig viewInfo, HttpServletRequest request,
	        HttpServletResponse _res) throws Exception {

		_res.setContentType(super.getContentType());
		_res.setHeader("Cache-Control", "no-cache");
		
		Format f = Format.getCompactFormat();
		f.setEncoding("utf-8");
		f.setIndent("");
		try {
			org.jdom.Document oDoc = (org.jdom.Document) request
			                                                    .getAttribute(ViewerSupport.XML_DOC_KEY);
			XMLOutputter outputter = new XMLOutputter(f);
			
			// outputstream 은 encording을 하지 않아 writer로 교체, jsonview 와 동일
//			ServletOutputStream ouputStream = _res.getOutputStream();
//			
//			outputter.output(oDoc, ouputStream);
//			ouputStream.close();
			
			PrintWriter writer = _res.getWriter();
			try {
				outputter.output(oDoc, writer);
				writer.flush();
			} finally {
				if (writer != null) {
					writer.close();
				}
			}
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}
}
