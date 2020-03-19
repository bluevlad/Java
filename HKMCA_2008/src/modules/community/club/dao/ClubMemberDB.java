/*
 * ClubMemberDB.java, @ 2005-03-19
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.club.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import modules.community.club.beans.ClubMemberBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author UBQ
 *  
 */
public class ClubMemberDB {

    static  Log logger = LogFactory.getLog(ClubMemberDB.class);
	/**
	 * Ŭ��ȸ�� ���ο��� ("T" : ���� , "F" : ���Խ�û����, null : ��ȸ��)
	 * 
	 * @param oDb
	 * @param c_id
	 * @param usn
	 * @return
	 * @throws MdbException
	 */
	public static String checkClubMember(MdbDriver oDb, String c_id, String usn)
			throws MdbException {
		String sql = " SELECT IS_AUTH  FROM club_member"
				+ "  WHERE c_id = :c_id AND usn = :usn";
		List at = new ArrayList();
		at.add(c_id);
		at.add(usn);

		return oDb.selectOne(sql, at);
	}

	/**
	 * Ŭ���� ȸ����
	 * 
	 * @param oDb
	 * @param club_id
	 * @return
	 */
	public static int getMemberCount(MdbDriver oDb, String club_id)
			throws MdbException {
		String sql = " SELECT COUNT (usn) AS cnt  FROM club_member WHERE c_id = :c_id";

		List at = new ArrayList();
		at.add(club_id);

		return oDb.selectOneInt(sql, at);
	}

	/**
	 * �˻��� ȸ����
	 * 
	 * @param c_id
	 * @return
	 * @throws Exception
	 */
	public static int getSearchMemberCount(MdbDriver oDb, String club_id,
			String key, String srch) throws Exception {
		String sql = " SELECT COUNT (cm.usn) AS cnt    FROM club_member cm, mst_user mu"
				+ "   WHERE cm.usn = mu.usn AND c_id = :c_id "
				+ "   AND decode(:key, 'USERID', decode(USERID,:srch,1,0),1) = 1 "
				+ "   AND decode(:key,'UNAME',decode(INSTR(NM,:srch),0,0,1),1) = 1";
		List at = new ArrayList();
		at.add(club_id);
		at.add(key);
		at.add(srch);
		at.add(key);
		at.add(srch);
		return oDb.selectOneInt(sql, at);
	}

	/**
	 * Ŭ�� ȸ�� ����Ʈ
	 * 
	 * @param row4Page
	 *            �������� ������ ȸ����
	 * @param limit
	 * @param c_id
	 *            Ŭ��ID
	 * @return
	 * @throws Exception
	 */
	public static ClubMemberBean[] getMemberList(MdbDriver oDb, int row4Page,
			int page, String club_id) throws Exception {

		String sql = " SELECT cm.c_id, cm.usn, cm.c_joindt, cm.is_auth, cm.c_desc, cm.c_lastdt,"
				+ "        cm.c_attendance, cm.c_authdt, mu.userid, mu.nm "
				+ "   FROM club_member cm, mst_user mu"
				+ "  WHERE cm.usn = mu.usn AND c_id = :c_id ";

		List at = new ArrayList();
		at.add(club_id);

		oDb.setPage(page, row4Page);

		List list = oDb.selector(ClubMemberBean.class, sql, at);

		return (ClubMemberBean[]) list.toArray(new ClubMemberBean[0]);
	}

	/**
	 * Ŭ���� ȸ�� �˻�
	 * 
	 * @param row4Page
	 * @param limit
	 * @param c_id
	 * @param item
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public static ClubMemberBean[] searchClubMember(MdbDriver oDb,
			int row4Page, int page, String club_id, String key, String srch)
			throws Exception {

		String sql = "  SELECT cm.c_id, cm.usn, cm.c_joindt, cm.is_auth, cm.c_desc, cm.c_lastdt,"
				+ "         cm.c_attendance, cm.c_authdt, mu.userid, mu.nm"
				+ "    FROM club_member cm, mst_user mu"
				+ "   WHERE cm.usn = mu.usn AND c_id = :c_id  "
				+ "   AND decode(:key, 'USERID', decode(USERID,:srch,1,0),1) = 1 "
				+ "  	AND decode(:key, 'UNAME', decode(INSTR(NM,:srch),0,0,1),1) = 1 ";

		List at = new ArrayList();
		at.add(club_id);
		at.add(key);
		at.add(srch);
		at.add(key);
		at.add(srch);

		oDb.setPage(page, row4Page);

		List list = oDb.selector(ClubMemberBean.class, sql, at);

		return (ClubMemberBean[]) list.toArray(new ClubMemberBean[0]);
	}

	/**
	 * Club ȸ�� ���� ���� ����
	 * 
	 * @param oDb
	 * @param club_id
	 * @param usn
	 * @return
	 * @throws Exception
	 */
	public static ClubMemberBean getClubMember(MdbDriver oDb, String club_id,
			String usn) throws Exception {
		String sql = " SELECT cm.c_id, cm.usn, cm.c_joindt, cm.is_auth, cm.c_desc, cm.c_lastdt,"
				+ "        cm.c_attendance, cm.c_authdt, mu.userid, mu.email, mu.nm "
				+ "   FROM club_member cm, mst_user mu"
				+ "  WHERE cm.usn = mu.usn AND cm.c_id = :c_id AND cm.usn = :usn";
		List at = new ArrayList();
		at.add(club_id);
		at.add(usn);
		return (ClubMemberBean) oDb.selectorOne(ClubMemberBean.class, sql, at);
	}

	/**
	 * Ŭ��ȸ�� ����
	 * 
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public static void memberJoin(MdbDriver oDb, ClubMemberBean bean)
			throws MdbException {

		String sql = "{call MI_CLUB.MEMBER_JOIN (?, ?, ?)}";

		CallableStatement pstmt = null;
		try {
			pstmt = oDb.prepareCall(sql);
			int i = 0;

			pstmt.setString(++i, bean.getC_id());
			pstmt.setString(++i, bean.getUsn());
			pstmt.setString(++i, bean.getC_desc());

			pstmt.executeQuery();

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new MdbException(e);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException s) {
				}
			}
		} //end try
	}

	//	/*
	//	 * ȸ�� ����(Ż��)
	//	 *
	//	 * @param c_id
	//	 * @param usn
	//	 * @return
	//	 * @throws SQLException
	//	 */

	/**
	 * ȸ�� ����(Ż��)
	 */
	public static void secede(MdbDriver oDb, String c_id, String usn)
			throws MdbException {
		String sql = "    DELETE FROM club_member WHERE c_id = :cid AND usn=:usn";
		List at = new ArrayList();
		at.add(c_id);
		at.add(usn);
		try {
			oDb.executeUpdate(sql, at);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new MdbException(e);
		}
	}

	/**
	 * ȸ�� ���� ����
	 * 
	 * @param oDb
	 * @param c_id
	 * @param usn
	 * @throws MdbException
	 */
	public static void memberAuth(MdbDriver oDb, String c_id, String usn,
			String is_auth) throws MdbException {
		String sql = "    UPDATE club_member set IS_AUTH	= :is_auth WHERE c_id = :cid AND usn=:usn";
		List at = new ArrayList();
		at.add(is_auth);
		at.add(c_id);
		at.add(usn);
		try {
			oDb.executeUpdate(sql, at);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new MdbException(e);
		}
	}

}