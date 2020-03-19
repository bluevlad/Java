/*
 * Created on 2006. 11. 08
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.jasper;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.web.mvc.view.support.ViewDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JasperReportServlet extends HttpServlet implements Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(this.getClass());

	/**
	 * @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest,
	 javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest,
	 javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map param = new HashMap();
		param.put("user_name","김상준");
		try {
			//	 Get a database connection

			String REPORT_FILE_NAME = "diploma";


			JasperReport jasperReport = getCompiledReport(REPORT_FILE_NAME);
			
			generatePDFOutput(resp, param, jasperReport);
		} catch (Exception e) {
			req.setAttribute("exception", e);
			getServletContext().getRequestDispatcher("/error/error_view.jsp").forward(req, resp);
			maf.logger.Logging.trace(e);
		} finally {

		}
	}

	private void generatePDFOutput(HttpServletResponse response, Map parameters, JasperReport jasperReport) throws JRException,
			NamingException, SQLException, IOException {
		byte[] bytes = null;
		Connection conn = maf.mdb.DBResource.getConnection();
		try {
			
			List list = new ArrayList();
			for(int i = 1 ; i < 3; i++) {
				Map record = new HashMap();
				record.put("nm","nm " + i);
				record.put("pin","pin " + i);
				list.add(record);
			}
			ViewDataSource ds  = new ViewDataSource(list);
//			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, new JREmptyDataSource());
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, ds);
		} finally {
			if(conn !=null) { try {conn.close();} catch (Exception _ignore) {}; }
			conn = null;
		}
		if (bytes != null && bytes.length > 0) {

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			try {
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
			} finally {
				if (ouputStream != null) {try {ouputStream.close();} catch (IOException ex) {}}
			}
		}

	}

	/**
	 * @see javax.servlet.GenericServlet#void ()
	 */
	public void init() throws ServletException {
		super.init();
//		ServletContext context = getServletContext();
//		//			 Set class path for compiling XML templates
//		System.setProperty("jasper.reports.compile.class.path", context.getRealPath("/WEB-INF/lib/jasperreports-1.2.7.jar")
//				+ System.getProperty("path.separator")
//				+ context.getRealPath("/WEB-INF/classes/"));
//		//			 Specify a default folder for storing
//		//			 compiled XML templates
//		System.setProperty("jasper.reports.compile.temp", context.getRealPath("/reports/"));
	}




	private JasperReport getCompiledReport(String fileName) throws JRException {
		File reportFile = new File(getServletContext().getRealPath("/reports/" + fileName + ".jasper"));
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
		return jasperReport;
	}

}
