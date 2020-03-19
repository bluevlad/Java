/*
 * @(#) DownLoad.java 2005-02-28
 * 
 * Copyright (c) 2005 (주)미래넷 All rights reserved.
 */

package modules.downloader;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.lib.system.FileInfoBean;

/**
 * 자료실 다운 받기
 * 
 * @author goindole
 * @version 1.0
 * @modifydate 2005-02-28
 */
public class CommonDownloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3478980056162558735L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// String requestURI = request.getRequestURI().substring( request.getContextPath().length() );

		String cmd = request.getParameter("cmd");
		String os = request.getHeader("USER_AGENT");
		String fullfileName = request.getParameter("file");
		String rfilename = request.getParameter("filename");

		if (os == null) {
			os = request.getHeader("user-agent");
		}
		
		String fileName = fullfileName.substring(fullfileName.lastIndexOf("/") + 1);
		String filePath = fullfileName.substring(0, fullfileName.lastIndexOf("/"));

		String fullpath = getServletContext().getRealPath(filePath);
		// fileName = fileName.replaceAll( "%20", " " );
		// fileName = URLDecoder.decode( fileName, "UTF-8" );
		FileInfoBean fb = new FileInfoBean();
//		Logging.log(this.getClass(), rfilename + "," + filePath + ", " + fileName);
		fb.setFilename(MafUtil.empty(rfilename) ? fileName : rfilename);

		File file = new File(fullpath, fileName);

//		Logging.log(this.getClass(), file.getAbsolutePath());
		HttpFileSender sm = new HttpFileSender(request, response);
		sm.send(file, fb, cmd);
	}
}
