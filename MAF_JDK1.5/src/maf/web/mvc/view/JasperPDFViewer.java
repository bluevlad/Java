/*
 * Created on 2006. 11. 09
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.view;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.web.context.MafConfig;
import maf.web.http.MimeType;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.mvc.view.support.ViewDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

public class JasperPDFViewer extends AbstractViewer {


	public JasperPDFViewer() {
		super.setContentType(MimeType.getMimeType("pdf"));
	}

	public void render(ViewInfoConfig viewInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List list = (List) request.getAttribute(ViewerSupport.DATA_SOURCE_KEY);
		Map parameters = (Map) request.getAttribute(ViewerSupport.PARAM_KEY);

		byte[] bytes = null;

		JasperReport jasperReport = getCompiledReport(viewInfo.getUri());

		ViewDataSource ds = new ViewDataSource(list);

		bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, ds);

		if (bytes != null && bytes.length > 0) {
			response.setContentType(super.getContentType());
			response.setContentLength(bytes.length);
			response.setHeader( "Content-Disposition", "attachment; filename=\"report.pdf\"");
			response.setHeader( "Content-Transfer-Encoding", "binary" );
			ServletOutputStream ouputStream = response.getOutputStream();
			try {
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
			} finally {
				if (ouputStream != null) {try {ouputStream.close();} catch (IOException ex) {}}
			}
		}
	}

	private JasperReport getCompiledReport(String fileName) throws JRException {
		File reportFile = new File(MafConfig.getRealPath(fileName));
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
		return jasperReport;
	}
}
