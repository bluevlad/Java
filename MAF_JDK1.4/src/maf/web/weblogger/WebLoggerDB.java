/*
 * Created on 2006. 09. 27
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.weblogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import maf.mdb.DBResource;
import maf.mdb.drivers.MdbDriver;
import maf.util.StringUtils;
import maf.web.filter.RequestMonFilter;
import maf.web.mvc.beans.SiteInfo;

public class WebLoggerDB {

	private static Log logger = LogFactory.getLog(WebLoggerDB.class);

	/**
	 * HitLogger를 DB에 등록 
	 * @param req
	 */
	public static void insertHitLog(HttpServletRequest req) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		String site = null;
		String sql = " INSERT INTO MAF_LOG_CONNECT ("
		        + "    ip, referer,useragent, url, site) "
		        + " VALUES ( :ip,  :referer, :useragent, :url, :site)";
		try {
			String referer = StringUtils.getByteCut(req.getHeader("referer"), 200);
			String ip = req.getRemoteAddr();
			String useragent = StringUtils.getByteCut(req.getHeader("user-agent"), 200);
			String url = StringUtils.getByteCut(req.getRequestURL().toString(), 200);
			SiteInfo siteInfo = (SiteInfo) req
			                                  .getAttribute(RequestMonFilter.KEY_SITEINFO);
			if (siteInfo != null) {
				site = siteInfo.getSite();
			}
			conn = DBResource.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ip);
			stmt.setString(2, referer);
			stmt.setString(3, useragent);
			stmt.setString(4, url);
			stmt.setString(5, site);
			stmt.execute();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (rs != null) try {
				rs.close();
			} catch (Exception e) {
			}
			if (stmt != null) try {
				stmt.close();
			} catch (Exception e) {
			}
			if (conn != null) try {
				conn.close();
			} catch (Exception e) {
			}
			rs = null;
			stmt = null;
			conn = null;
		}
	}

	/**
	 * User_Log에 접속 정보를 담든다.
	 * 
	 * @param oDb
	 * @param userid
	 * @param usn
	 * @param status
	 * @param remoteIP
	 */
	public static void userlog(MdbDriver oDb, String userid, String usn, String status,
	        String remoteIP, String site) {
		Map param = new HashMap();
		try {
			String sql = "insert into MAF_LOG_USER (USERID, USN,  STATUS, IP, SITE) "
			        + "	values(:userid, :usn, :status, :ip, :site) ";
			param.put("userid", userid);
			param.put("usn", usn);
			param.put("status", status);
			param.put("ip", remoteIP);
			param.put("site", site);
			oDb.executeUpdate(sql, param);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
	}
}
