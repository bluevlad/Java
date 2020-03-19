/*
 * 작성된 날짜: 2005-03-02
 *  
 */
package modules.community.club.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import maf.logger.Logging;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import modules._exceptions.EcampusException;
import modules.community.club.beans.ClubMasterBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 
 * 커뮤니 케이션 DB 작성자 :신규문 작성된 날짜 : 2005-03-02
 */
public class ClubDB {


    static  Log logger = LogFactory.getLog(ClubDB.class);
	/**
	 * c_id 중복 체크
	 * 
	 * @param oDb
	 * @param cid
	 * @return
	 * @throws MdbException
	 */
	public static boolean chkClub(MdbDriver oDb, String cid)
			throws MdbException {

		List at = new ArrayList();

		String sql = " SELECT count(*)   FROM club_master"
				+ "  WHERE c_id = :c_id";

		at.add(cid);

		if (oDb.selectOneInt(sql, at) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 커뮤니티 개설하기
	 * 
	 * @param oDb
	 * @param bean
	 * @throws Exception
	 */
	public static void insertClub(MdbDriver oDb, ClubMasterBean bean)
			throws Exception {

		CallableStatement cstmt = null;

		String sql = "{call MI_CLUB.CREATE_CLUB (?, ?, ?, ?, ?, ?, ?, ? )}";

		try {
			cstmt = oDb.prepareCall(sql);

			int i = 0;
			cstmt.setString(++i, bean.getC_id());
			cstmt.setString(++i, bean.getC_name());
			cstmt.setString(++i, bean.getC_sysopusn());
			cstmt.setString(++i, bean.getC_logo_image());
			cstmt.setString(++i, bean.getC_bg_image());
			cstmt.setString(++i, "T");
			cstmt.setString(++i, bean.getC_purpose());
			cstmt.setString(++i, bean.getC_auto_confirm());

			cstmt.execute();
			//			oitem_no = cstmt.getInt(i);
			//			oitem_no = oitem_no;
			cstmt.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new EcampusException("insertClub Err!!!", e);
		} finally {
			if (cstmt != null)
				try {
					cstmt.close();
				} catch (SQLException ex) {
				}
		}
	}

	public static void CommMenuIns(MdbDriver oDb, String c_id,
			String fl_board_type, String c_title, String usn)
			throws MdbException {

		CallableStatement cstmt = null;

		String sql = "{call MI_CLUB.CREATE_MENU (?, ?, ?, ?)}";

		try {
			cstmt = oDb.prepareCall(sql);

			int i = 0;
			cstmt.setString(++i, c_id);
			cstmt.setString(++i, fl_board_type);
			cstmt.setString(++i, c_title);
			cstmt.setString(++i, usn);

			Logging.log(ClubDB.class, "C_id=" + c_id);
			Logging.log(ClubDB.class, "fl_board_type=" + fl_board_type);
			Logging.log(ClubDB.class, "c_title=" + c_title);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new MdbException(e);
		} finally {
			if (cstmt != null)
				try {
					cstmt.close();
				} catch (SQLException ex) {
				}
		}
	}

	/**
	 * 가입한 클럽의 목록을 가져온다.
	 * 
	 * @param oDb
	 * @param usn
	 * @return
	 */
	public static List getMyClubs(MdbDriver oDb, String usn)
			throws MdbException {
		String sql = "  SELECT   c.*, (select nm from mst_user where usn = c.c_sysopusn) as sysop"   +
		"      FROM club_master c, club_member cm"   +
		"     WHERE cm.usn = :usn"   +
		"  AND c.c_id = cm.c_id"   +
		"  AND c.use_yn = 'Y'"   +
		"  ORDER BY c.c_id"  ;
		List list = null;
		List at = new ArrayList();
//		oDb.setDebug(true);
		at.add(usn);
		try {
			list = oDb.selector(ClubMasterBean.class, sql, at);
		} catch (MdbException e) {
			logger.error(e.getMessage());
			throw e;
		}
		return list;
	}

	/**
	 * 전체 클럽 리스트를 가져 온다.
	 * 
	 * @param oDb
	 * @return
	 */
	public static List getAllClubs(MdbDriver oDb) throws MdbException {
		String sql = " SELECT cm.c_id, cm.c_name, cm.c_sysopusn, cm.c_main_image, cm.c_bg_image, cm.c_bgtype,"
				+ "        cm.c_apply_dt, cm.c_membercnt, cm.c_hit_cnt,"
				+ "        cm.is_public, cm.c_group, cm.c_purpose,  cm.c_last_dt,"
				+ "        cm.c_hit_today, cm.c_auto_confirm, cm.c_logo_image, mu.nm owner_nm"
				+ "   FROM club_master cm, mst_user mu"
				+ "  WHERE cm.use_yn = 'Y'"
				+ "  	   and cm.C_SYSOPUSN = mu.usn order by cm.c_id ";
		List list = null;

		try {
			list = oDb.selector(ClubMasterBean.class, sql);
		} catch (MdbException e) {
			logger.error(e.getMessage());
			throw e;
		}
		return list;
	}

	public static int getAllClubsCount(MdbDriver oDb) throws MdbException {
		String sql = " SELECT count(*)   FROM club_master cm"
				+ "  WHERE cm.use_yn = 'Y'";
		int cnt = 0;

		try {
			cnt = oDb.selectOneInt(sql);
		} catch (MdbException e) {
			logger.error(e.getMessage());
			throw e;
		}
		return cnt;
	}

	/**
	 * 전체 클럽 리스트를 가져 온다. 최근 n개의 club을 가져 온다.
	 * 
	 * @param oDb
	 * @return
	 */
	public static List getRecentClubs(MdbDriver oDb) throws MdbException {
		String sql = " SELECT c.*, mu.nm owner_nm"
				+ "   FROM (SELECT   cm.c_id, cm.c_name, cm.c_sysopusn, cm.c_main_image,"
				+ "                  cm.c_bg_image, cm.c_bgtype, cm.c_apply_dt, cm.c_membercnt,"
				+ "                  cm.c_hit_cnt, cm.is_public, cm.c_group, cm.c_purpose,"
				+ "                  cm.c_last_dt, cm.c_hit_today, cm.c_auto_confirm,"
				+ "                  cm.c_logo_image"
				+ "             FROM club_master cm"
				+ "            WHERE cm.use_yn = 'Y'"
				+ "         ORDER BY c_apply_dt DESC) c,"
				+ "        mst_user mu"
				+ "  WHERE c.c_sysopusn = mu.usn AND ROWNUM < 6";
		List list = null;


		try {
			list = oDb.selector(ClubMasterBean.class, sql);
		} catch (MdbException e) {
			logger.error(e.getMessage());
			throw e;
		}
		return list;
	}

	/**
	 * 하나의 클럽 정보를
	 * 
	 * @param oDb
	 * @return
	 */
	public static ClubMasterBean getClubInfo(MdbDriver oDb, String club_id)
			throws MdbException {
		String sql = " SELECT cm.c_id, cm.c_name, cm.c_sysopusn, cm.c_main_image, cm.c_bg_image, cm.c_bgtype,"
				+ "        cm.c_apply_dt, cm.c_membercnt, cm.c_hit_cnt,cm.c_bgcolor, "
				+ "        cm.is_public, cm.c_group, cm.c_purpose,  cm.c_last_dt, cm.c_describe, "
				+ "        cm.c_hit_today, cm.c_auto_confirm, cm.c_logo_image, mu.nm owner_nm, cm.OPACITY "
				+ "   FROM club_master cm, mst_user mu"
				+ "  WHERE cm.c_id = :c_id "
				+ "  	   and cm.C_SYSOPUSN = mu.usn";
		ClubMasterBean club = null;
		List at = new ArrayList();
		at.add(club_id);

//		oDb.setDebug(true);

		try {
			club = (ClubMasterBean) oDb.selectorOne(ClubMasterBean.class, sql,
					at);
		} catch (MdbException e) {
			logger.error(e.getMessage());
			throw e;
		}
		return club;
	}

	/**
	 * 클럽에 회원이 방문했을때 일어나는 DB 관련 처리
	 * 
	 * @param usn
	 * @param c_id
	 * @throws SQLException
	 */
	public static void visitClub(MdbDriver oDb, String usn, String c_id)
			throws MdbException {

		String sql = "{call MI_CLUB.VISIT_CLUB(:c_id, :usn)}";

		CallableStatement pstmt = null;

		try {
			pstmt = oDb.prepareCall(sql);
			pstmt.setString(1, c_id);
			pstmt.setString(2, usn);
			pstmt.executeQuery();

			pstmt.close();
			pstmt = null;

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
		}

	}

	/**
	 * 클럽정보 수정
	 * 
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public static void clubInfoModify(MdbDriver oDb, ClubMasterBean bean)
			throws MdbException {

		List at = new ArrayList();

		String sql = " UPDATE club_master"
				+ "    SET c_name = ?,  c_purpose = ?,  c_describe = ?,  c_auto_confirm = ?, "
				+ "  		c_bgcolor = ?, opacity = ?  " + "  WHERE c_id = ?";

		at.add(bean.getC_name());
		at.add(bean.getC_purpose());
		at.add(bean.getC_describe());
		at.add(bean.getC_auto_confirm());
		at.add(bean.getC_bgcolor());
		at.add(bean.getOpacity() + "");
		at.add(bean.getC_id());
		try {
			oDb.executeUpdate(sql.toString(), at);

		} catch (MdbException e) {
			logger.error(e.getMessage());
			throw new MdbException(e);
		}
	}

	/**
	 * 배경이미지(Skin) 변경
	 * 
	 * @param oDb
	 * @param c_id
	 * @param c_bgtype
	 * @param bg_image
	 * @throws MdbException
	 */
	public static void changeSkinImage(MdbDriver oDb, String c_id,
			String c_bgtype, String bg_image) throws MdbException {
		List at = new ArrayList();
		String sql = "UPDATE club_master SET c_bgtype = :c_bgtype, c_bg_image = :bg_image "
				+ " WHERE c_id = :c_id";
		at.add(c_bgtype);
		at.add(bg_image);
		at.add(c_id);
		try {
			oDb.executeUpdate(sql, at);

		} catch (MdbException e) {
			logger.error(e.getMessage());
			throw new MdbException(e);
		}

	}

	/**
	 * Logo 이미지 변경
	 * 
	 * @param oDb
	 * @param c_id
	 * @param c_bgtype
	 * @param bg_image
	 * @throws MdbException
	 */
	public static void changeLogoImage(MdbDriver oDb, String c_id,
			String c_logo_image) throws MdbException {
		List at = new ArrayList();
		String sql = "UPDATE club_master SET c_logo_image = :c_logo_image "
				+ " WHERE c_id = :c_id";
		at.add(c_logo_image);
		at.add(c_id);
		try {
			oDb.executeUpdate(sql, at);

		} catch (MdbException e) {
			logger.error(e.getMessage());
			throw new MdbException(e);
		}

	}
}