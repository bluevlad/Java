/*
 * Created on 2006. 08. 23
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.pager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import maf.MafUtil;
import maf.base.BaseHttpSession;
import maf.mdb.Mdb;


public class PagerUtils {
	/**
	 * 새로운 메시지의 갯수를 리턴한다.
	 * @param Usn
	 * @return
	 */
	public static int getNewMsgCount(BaseHttpSession sessionBean) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		int cnt = 0;

		final String sql = " SELECT COUNT (*) cnt"
				+ "   FROM maf_pager"
				+ "  WHERE msg_rc_id = :usn "
				+ "   AND msg_rc_isdel = 'N' "
				+ "   AND msg_read_dt IS NULL";
		if (!MafUtil.empty(sessionBean.getUsn())) {
			try {
				conn = Mdb.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, sessionBean.getUsn());
				rs = stmt.executeQuery();
				if (rs.next()) {
					cnt = rs.getInt(1);
				}
			} catch (Throwable e) {

			} finally {
				if (rs != null) {try {rs.close();} catch (Exception _ignore) {};}
				rs = null;
				if (stmt != null) {	try {stmt.close();} catch (Exception _ignore) {};}
				stmt = null;
				if (conn != null) {try {conn.close();} catch (Exception _ignore) {};}
				conn = null;
			}

		}
		return cnt;
	}	

}

