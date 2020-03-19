/*
 * Created on 2006. 09. 27
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.weblogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import maf.mdb.DBResource;
import maf.util.StringUtils;
import maf.web.mvc.beans.SiteInfo;
import miraenet.MiConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WebLoggerDB {
    private static WebLoggerDB instance;

    private Log logger = LogFactory.getLog(WebLoggerDB.class);

    private WebLoggerDB() {

    }

    public static synchronized WebLoggerDB getInstance() {
        if (instance == null)
            instance = new WebLoggerDB();
        return instance;
    }
    
    public void insertHitLog(HttpServletRequest req ) {
    	ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		String site = null;
		String sql = " INSERT INTO EHRD.MAF_LOG_CONNECT ("   +
		"    ip, referer,useragent, url, site) "   +
		" VALUES ( :ip,  :referer, :useragent, :url, :site)"  ;
		
		try {
			String referer = StringUtils.getByteCut(req.getHeader("referer"),200);
			String ip = req.getRemoteAddr();
			String useragent = StringUtils.getByteCut(req.getHeader("user-agent"),200);
			String url = StringUtils.getByteCut(req.getRequestURL().toString(),200);
			SiteInfo siteInfo = MiConfig.MAF_SITE_RESOLVER.resolveSite(req);
			if(siteInfo != null) {
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
			if (rs != null)try {rs.close();} catch (Exception e) {}
			if (stmt != null)try {stmt.close();} catch (Exception e) {}
			if (conn != null)try {conn.close();} catch (Exception e) {}
			rs = null;
			stmt = null;
			conn = null;
		}
    }
    
}

