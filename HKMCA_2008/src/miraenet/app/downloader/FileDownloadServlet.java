/*
 * @(#) DownLoad.java 2005-02-28
 * 
 * Copyright (c) 2005 UBQ All rights reserved.
 */

package miraenet.app.downloader;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import maf.web.downloader.HttpFileSender;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 호출된 파일 다운 받기
 * 
 * @author bluevlad
 * @version 1.0
 * @modifydate 2010-02-28
 */
public class FileDownloadServlet extends HttpServlet {
	private Log logger = LogFactory.getLog(this.getClass());

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fullfileName = request.getParameter("file");

		String fileName = fullfileName.substring(fullfileName.lastIndexOf("/") + 1);
		String filePath = fullfileName.substring(0, fullfileName.lastIndexOf("/"));

		String fullpath = getServletContext().getRealPath(filePath);

		File file = new File(fullpath, fileName);

		HttpFileSender sm = new HttpFileSender(request, response);

		sm.send(file);
	}

}