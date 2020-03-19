/*
 * Created on 2005. 10. 28.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.downloader;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.lib.i18n;
import maf.mdb.DBResource;
import maf.web.http.MyHttpServletRequest;
import maf.web.session.exception.SessionInvalidatedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BlobDownloader extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -535046243464316043L;
	protected String vMode = "V"; 
	private  Log logger = LogFactory.getLog(BlobDownloader.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	void processRequest(HttpServletRequest _req, HttpServletResponse _res) throws ServletException, IOException {
		try {
			String blobid = process(_req, _res);
			if (blobid != null) {
				this.sendPhoto( blobid, _req, _res);
			} else {
				_res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (SessionInvalidatedException es) {
			final String url = "/error/session_invalid.jsp";
			RequestDispatcher dispatcher = _req.getRequestDispatcher(url);
			dispatcher.forward(_req, _res);
		} catch (Throwable e) {
			
			Log logger = LogFactory.getLog(this.getClass());
			logger.error(e.getMessage());
			throw new ServletException(e);

		}
	}

	public void destroy() {
		super.destroy();
	}

	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);

	}


	/**
	 * Oracle에서 BLOB를 읽어와 response로 보낸다.
	 * 
	 * @param _req
	 * @param _res
	 * @param usn
	 * @throws ServletException
	 */
	private void sendPhoto( String blob_id, HttpServletRequest _req, HttpServletResponse _res)
			throws ServletException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final String sql = " select blob_data, file_name, file_length, mime_type " 
			+ " from MST_BLOB where blob_id=?";

		try {
			ServletOutputStream outStream = _res.getOutputStream();
			conn =  DBResource.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, blob_id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				InputStream inStream = rs.getBinaryStream(1);
				String file_name = rs.getString(2);
				String file_length = rs.getString(3);
				String mime_type = rs.getString(4);

				byte buffer[] = new byte[16 * 1024];
				int bytesRead;

				_res.setHeader("Content-Transfer-Encoding", "binary");
				_res.setHeader("Pragma", "cache");
				if (!MafUtil.empty(file_name)) {
					String rawFileName = i18n.convertTo(file_name, "euc-kr", "8859_1");
					rawFileName = "filename=\"" + rawFileName.replaceAll("\"", "'") + "\"";
					if ("V".equals(this.vMode)) {
						_res.setHeader("Content-Disposition", "inline; " + rawFileName);
					} else {
						_res.setHeader("Content-Disposition", "attachment; " + rawFileName);
					}
				}
				if (!MafUtil.empty(file_length)) {
					_res.setHeader("Content-Length", file_length);
				}
				if (!MafUtil.empty(mime_type)) {
					_res.setContentType(mime_type);
				}

				while ((bytesRead = inStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inStream.close();
			} else {
				_res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}

		} catch (Throwable e) {

			logger.error(e.getMessage());
			throw new ServletException(e);
		} finally {
			try {if (rs != null) {rs.close();}} catch (Exception ex) {}
			try {if (stmt != null) {stmt.close();}} catch (Exception ex) {}
			try {if (conn != null) {conn.close();}} catch (Exception ex) {}
			rs = null;
			stmt = null;
			conn = null;
		}
	}

	/**
	 * 모든 하위 Class에서 구성해야할 프로세스 blob_id를 돌려 주어야 함.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Throwable
	 */
	protected String process(HttpServletRequest _req, HttpServletResponse _res) throws Throwable {

		logger.error("No process defined!!!");
		return null;
	}

	protected String errorPage(MyHttpServletRequest request, String message) {
		request.setAttribute("message", message);
		return "error";
	}
}
