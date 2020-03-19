/*
 * Created on 2005. 12. 12.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.common.actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import modules.downloader.BlobDownloader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileDownload extends BlobDownloader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5995232954295707183L;
	private  Log logger = LogFactory.getLog(FileDownload.class);
	public String process(HttpServletRequest _req, HttpServletResponse _res) throws Throwable {
		MdbDriver oDb = null;

		String blob_id = _req.getParameter("blob_id");
		String req_src_table = _req.getParameter("src_table");

		final String sql = "select src_table from mst_blob " + " where blob_id = :blob_id";

		String src_table = null;

		Map param = new HashMap();
		param.put("blob_id", blob_id);
		
		this.vMode = "D"; // Download
		
		try {
			oDb = Mdb.getMdbDriver();
			src_table = (String) oDb.selectOne(sql, param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServletException(e);
		} finally {
			if (oDb != null) try {oDb.close();} catch (Exception ex) {}
			oDb = null;
		}

		if (!src_table.equals(req_src_table)) {
			_res.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

		return blob_id;
	}
}
