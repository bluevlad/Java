/*
 * Created on 2006. 9. 22.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.learner;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ChaptersDB extends CommonDB {
	
	private static Log logger = LogFactory.getLog(ChaptersDB.class);

	/**
	 * 목록 가져오기
	 * 
	 * @param oDb
	 * @param navigator
	 * @param param
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean all) throws MdbException {
		List list = null;
		final String sql = "SELECT a.lec_cd, a.sjt_cd, a.htmcode, a.itm_id, itm_sequence, b.cenname itm_title, b.htmurl launch_data,  " +
		"       bb.total_time, bb.prgcont, bb.prgsdat, bb.prgedat, bb.lesson_status,  " +
		"       itm_lvl, itm_pid, itm_prerequisites, itm_type, week, chpnumb,  " +
		"       b.cnt_width, b.cnt_height, b.totpage, b.cnt_type,  " +
		"       chp_sdat, chp_edat, a.itm_maxtimeallowed  " +
		"  FROM wlc_lec_chp a, wlc_inx_lst b, (select wcp.lec_cd, wcp.htmcode, wcp.itm_id, wcp.total_time, wcp.prgcont, wcp.prgsdat, wcp.prgedat, wcp.lesson_status   " +
		"                         from wlc_lec_chp wlc, wlc_chp_prg wcp   " +
		"                         where wlc.lec_cd = wcp.lec_cd(+)   " +
		"                         and wlc.htmcode = wcp.htmcode(+)   " +
		"                         and wcp.lec_cd = :lec_cd   " +
		"                         and wcp.usn = :usn) bb   " +
		"  WHERE a.sjt_cd = b.sjt_cd  " +
		"  and a.htmcode = b.htmcode  " +
		"  and a.lec_cd = bb.lec_cd(+)   " +
		"  and a.htmcode = bb.htmcode(+)   " +
		"  and a.lec_cd = :lec_cd ";

		String sOrder = navigator.getOrderSql();
		if (null == sOrder || "".equals(sOrder)
				|| (sOrder.trim()).equals((("ORDER BY null").trim()))) {
			sOrder = " order by itm_sequence asc ";
		}

		if (!all) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(getRecordCount(oDb, param, sWhere.toString()));
			navigator.sync();
		}
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
	}

	/**
	 * 레코드 카운트 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @param sWhere
	 * @return long
	 * @throws MdbException
	 */
	private static  long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final String sql = "SELECT count(itm_id) from wlc_lec_chp  " + sWhere;
		return oDb.selectOneInt(sql, param);
	}

	/**
	 * 하나의 레코드 읽어 오기
	 * 
	 * @param oDb
	 * @param param
	 * @return Map
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = "select * from wlc_lec_chp where lec_cd = :lec_cd and itm_id = :itm_id ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 하나의 레코드 읽어 오기
	 * 
	 * @param oDb
	 * @param param
	 * @return Map
	 * @throws MdbException
	 */
	public static Map getChapterOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = "select a.lec_cd, a.htmcode, b.htmurl,  " +
		"       b.cnt_width, b.cnt_height,  " +
		"       chp_sdat, chp_edat, b.lrntime " +
		" from wlc_lec_chp a, wlc_inx_lst b " +
		" where a.sjt_cd = b.sjt_cd " +
		" and a.htmcode = b.htmcode " +
		" and a.lec_cd = :lec_cd " +
		" and a.itm_id = :itm_id ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 목록 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @return List
	 * @throws MdbException
	 */
	public static List getLecChpList(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT itm_id, itm_title, itm_lvl "
				+ "   FROM wlc_lec_chp WHERE lec_cd = :lec_ce"
				+ " order by itm_sequence ";
		return oDb.selector(Map.class, sql, param);
	}

	/**
	 * 과목 목록 가져오기
	 * 
	 * @param oDb
	 * @param leccode
	 * @return String
	 * @throws MdbException
	 */
	public static String getContent_type(MdbDriver oDb, String lec_cd)
			throws MdbException {
		final String sql = " SELECT cnt_type FROM bas_lec "
				+ "  WHERE lec_cd = '" + lec_cd + "' ";
		return (oDb.selectOne(sql));
	}

	/**
	 * 개설과목코드 통해서 개설과목의 최소학습시간 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @return String
	 * @throws MdbException
	 */
	public static String getLimit_study_time(MdbDriver oDb, Map param)
			throws MdbException {
		final String sql = "select NVL(limit_study_time,'0') as limit_study_time "
				+ "  FROM bas_lec WHERE lec_cd=:lec_cd ";
		return (String) (oDb.selectOne(sql, param));
	}

	/**
	 * 개설과목의 진도 기준 정보 읽어 오기
	 * 
	 * @param oDb
	 * @param param
	 * @return Map
	 * @throws MdbException
	 */
	public static Map getWlaratmst(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select *  FROM wlc_rat_mst "
				+ "  WHERE lec_cd=:lec_cd ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 총학습시간 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @return String
	 * @throws MdbException
	 */
	public static String getLearningTotalTime(MdbDriver oDb, Map param)
			throws MdbException {
		final String sql = "SELECT FN_GET_TOTAL_TIME_H(sum(FN_GET_TOTAL_SEC(total_time))) as total_time"
				+ "  FROM wlc_chp_prg "
				+ "  WHERE lec_cd=:lec_cd  AND usn =:usn";
		return (String) (oDb.selectOne(sql, param));
	}

	/**
	 * 학습진도 기준 정보 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @return String[]
	 * @throws MdbException
	 */
	public static Map getLecType(MdbDriver oDb, Map param) throws MdbException {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		String[] datas = new String[5];

		String sql = "SELECT prg_type, lec_wetm, lec_datm, lec_weck, exm_condition  " +
		"  FROM bas_lec  " +
		"  WHERE lec_cd=:lec_cd ";
		
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 일일 최대 학습 횟수 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @return String
	 * @throws MdbException
	 */
	public static String getDayCount(MdbDriver oDb, Map param) throws MdbException {
		String days = "";
		String sql = "SELECT count(itm_id) FROM wlc_chp_prg "
				+ "    WHERE to_char(prgsdat,'YYYY-MM-DD') = to_char(sysdate,'YYYY-MM-DD') "
				+ "         AND usn=:usn AND lec_cd=:lec_cd";
		days = (String) (oDb.selectOne(sql, param));

		if (null == days || "".equals(days)) {
			sql = "SELECT count(itm_id) FROM wlc_page_prg "
					+ "    WHERE to_char(page_open_dt,'YYYY-MM-DD') = to_char(sysdate,'YYYY-MM-DD') "
					+ "         AND usn=:usn AND lec_cd=:lec_cd";
			days = (String) (oDb.selectOne(sql, param));
		}
		return days;
	}

	/**
	 * 일일 최대 학습 시간 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @return String
	 * @throws MdbException
	 */
	public static String getDayTime(MdbDriver oDb, Map param) throws MdbException {
		String days = "";
		String sql = "SELECT nvl(SUM((FN_GET_LEARNING_TOTAL_TIME(total_time))),0) as total_time  " +
		"  FROM wlc_chp_prg  " +
		"  WHERE to_char(prgsdat,'YYYY-MM-DD') = to_char(sysdate,'YYYY-MM-DD')  " +
		"  AND usn = :usn  " +
		"  AND lec_cd = :lec_cd ";
		days = (String) (oDb.selectOne(sql, param));

		if (null == days || "".equals(days)) {
			sql = "SELECT nvl(sum(FN_GET_LEARNING_TOTAL_TIME(page_time)),0) as page_time  " +
			"  FROM wlc_page_prg  " +
			"  where to_char(page_open_dt,'YYYY-MM-DD') = to_char(sysdate,'YYYY-MM-DD')  " +
			"  AND usn = :usn  " +
			"  AND lec_cd = :lec_cd ";
			days = (String) (oDb.selectOne(sql, param));
		}
		return days;
	}

	/**
	 * SCORM 컨텐츠 목차 및 진도 정보 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @return String
	 * @throws MdbException
	 */
	public static String getScormProgInfo(MdbDriver oDb, Map param) throws MdbException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		String return_value = ",";

		String sql = "SELECT c.htmcode  " +
		"  FROM (SELECT b.htmcode  " +
		"          FROM wlc_chp_prg a, wlc_lec_chp b  " +
		"          WHERE a.lec_cd = b.lec_cd  " +
		"          AND a.htmcode = b.htmcode  " +
		"          AND a.usn = :usn  " +
		"          AND a.lec_cd = :lec_cd " +
		"    ORDER BY b.itm_sequence ASC) c ";

		try {
			pstmt = oDb.prepareStatement(sql, param);
			rs = pstmt.executeQuery();
			int chpcode = 0;
			while (rs.next()) {
				chpcode = 0;
				chpcode = rs.getInt(1);

				return_value += chpcode + "@,";
			}
		} catch (SQLException s) {
			oDb.rollback();
			logger.error(s.getMessage());
		} finally {
			release(rs);
			release(pstmt);
		}

		if (null == return_value || "".equals(return_value)
				|| "@,".equals(return_value)) {
			sql = "SELECT c.htmcode  " +
			"  FROM (SELECT b.htmcode  " +
			"          FROM wlc_page_prg a, wlc_lec_chp b  " +
			"          WHERE a.lec_cd = b.lec_cd  " +
			"          AND a.htmcode = b.htmcode  " +
			"          AND a.usn = :usn  " +
			"          AND a.lec_cd = :lec_cd " +
			"          ORDER BY b.itm_sequence ASC) c ";

			try {
				pstmt2 = oDb.prepareStatement(sql, param);
				rs2 = pstmt2.executeQuery();
				int chpcode = 0;
				while (rs2.next()) {
					chpcode = 0;
					chpcode = rs2.getInt(1);

					return_value += chpcode + "@,";
				}
			} catch (SQLException s) {
				oDb.rollback();
				logger.error(s.getMessage());
			} finally {
				release(rs2);
				release(pstmt2);
			}
		}
		return return_value;
	}

	/**
	 * 일반 컨텐츠 목차 및 진도 정보 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @return String
	 * @throws MdbException
	 */
	public static String getProgInfo(MdbDriver oDb, Map param) throws MdbException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		String return_value = ",";

		String sql = "SELECT c.htmcode " +
		"  FROM (SELECT b.htmcode " +
		"          FROM wlc_chp_prg a, wlc_lec_chp b " +
		"          WHERE a.lec_cd = b.lec_cd " +
		"          AND a.htmcode = b.htmcode " +
		"          AND a.usn = :usn " +
		"          AND a.lec_cd = :lec_cd " +
		"  ORDER BY b.itm_sequence ASC) c ";
		try {
			pstmt = oDb.prepareStatement(sql, param);
			rs = pstmt.executeQuery();
			int chpcode = 0;
			while (rs.next()) {
				chpcode = 0;
				chpcode = rs.getInt(1);
				return_value += chpcode + "@,";
			}
		} catch (SQLException s) {
//			logger.error(s.getMessage());
		} finally {
			release(rs);
			release(pstmt);
		}

		if (null == return_value || "".equals(return_value)
				|| "@,".equals(return_value)) {
			sql = "SELECT c.htmcode  " +
			"  FROM (SELECT b.htmcode  " +
			"          FROM wlc_page_prg a, wlc_lec_chp b  " +
			"          WHERE a.lec_cd = b.lec_cd  " +
			"          AND a.htmcode = b.htmcode  " +
			"          AND a.usn =:usn  " +
			"          AND a.lec_cd =:lec_cd " +
			"          ORDER BY b.itm_sequence ASC) c ";

			try {
				pstmt2 = oDb.prepareStatement(sql, param);
				rs2 = pstmt2.executeQuery();
				int chpcode = 0;
				while (rs2.next()) {
					chpcode = 0;
					chpcode = rs2.getInt(1);

					return_value += chpcode + "@,";
				}
			} catch (SQLException s) {
				oDb.rollback();
				logger.error(s.getMessage());
			} finally {
				release(rs2);
				release(pstmt2);
			}
		}
		return return_value;
	}

	/**
	 * 학생이 학습할 강의안 가져오기 - 강의컨텐츠 URL정보
	 * 
	 * @param oDb
	 * @param param
	 * @return Map
	 * @throws MdbException
	 */
	public static Map getLearningInfo(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select lec_cd, itm_id, launch_data , itm_title, cnt_width, cnt_height "
				+ "  FROM wlc_lec_chp "
				+ "  WHERE lec_cd=:lec_cd and itm_id=:itm_id ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 페이지진도 목록정보를 그래프형태로 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @return Map
	 * @throws MdbException
	 */
	public static Map getLbPagePrgGraph(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT fn_get_page_prg(page_cnt,totpage,'prog1') AS page_prog1, " +
				" fn_get_page_prg(page_cnt,totpage,'prog2') AS page_prog2, totpage, page_cnt "
				+ "  FROM "
				+ "         ( SELECT COUNT(userid) as page_cnt FROM wlc_page_prg "
				+ "           WHERE lec_cd=:lec_cd AND usn=:usn AND itm_id=:itm_id  "
				+ "            AND page_nm NOT LIKE '%left%' "
				+ "            AND page_nm NOT LIKE '%right%'"
				+ "            AND page_nm NOT LIKE '%bottom%'"
				+
				// " AND page_nm NOT LIKE '%top%'" +
				"            AND page_nm NOT LIKE '%frame%'"
				+ "    ) a,"
				+ "    ( SELECT NVL(totpage,0) AS totpage FROM wlc_lec_chp"
				+ "      WHERE lec_cd=:lec_cd AND itm_id=:itm_id AND place_type='ON'  "
				+ "    ) b  ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 목록 가져오기
	 * 
	 * @param oDb
	 * @param navigator
	 * @param param
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	public static void getSeList(MdbDriver oDb, NavigatorInfo navigator, Map param,
			String sWhere) throws MdbException {
		List list = null;
		final String sql = "select sjt_cd, htmcode,  htmname, daecode, daename, impdat, isuse, " +
				"	htmurl, cnt_width, cnt_height, totpage "
				+ " from wlc_inx_lst ";

		String sOrder = navigator.getOrderSql();

		oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		navigator.setTotalCount(getSeRecordCount(oDb, param, sWhere
				.toString()));
		navigator.sync();
	}

	/**
	 * 레코드 카운트 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	private static long getSeRecordCount(MdbDriver oDb, Map param, String sWhere)
			throws MdbException {
		final String sql = "select count(htmcode) from wlc_inx_lst  " + sWhere;
		return oDb.selectOneInt(sql, param);
	}

	/**
	 * 오프라인 강의 정보 읽어 오기
	 * 
	 * @param oDb
	 * @param param
	 * @return Map
	 * @throws MdbException
	 */
	public static Map getOfflineOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = "select itm_id, itm_title, off_place, off_date_info "
				+ " from wlc_lec_chp a  "
				+ " where lec_cd=:lec_cd and itm_id=:itm_id ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 신청자별 신청 정보 읽어 오기
	 * 
	 * @param oDb
	 * @param param
	 * @return Map
	 * @throws MdbException
	 */
	public static Map getOfflineUserOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = "select * from wlc_off_req where lec_cd=:lec_cd and itm_id=:itm_id and usn=:usn ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 오프라인 강의 신청자 목록 가져오기
	 * 
	 * @param oDb
	 * @param navigator
	 * @param param
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	public static void getWlboffreqList(MdbDriver oDb, NavigatorInfo navigator,
			Map param, String sWhere) throws MdbException {
		List list = null;
		final String sql = "SELECT a.*, b.nm, b.email, b.hp, b.cust_cd  "
				+ "  FROM wlc_off_req a , v_jmf_user b  "
				+ "  WHERE a.usn = b.usn " + "  AND a.lec_cd = :lec_cd "
				+ "  AND a.itm_id = :itm_id  and a.userid = :usn";

		String sOrder = navigator.getOrderSql();
		if (null == sOrder || "".equals(sOrder)
				|| (sOrder.trim()).equals((("ORDER BY null").trim()))) {
			sOrder = " order by b.userid ";
		}

		oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		list = oDb.selector(Map.class, sql + sOrder, param);
		navigator.setList(list);
		navigator.setTotalCount(getWlboffreqRecordCount(oDb, param, ""));
		navigator.sync();
	}

	/**
	 * 오프라인 강의 신청자 레코드 카운트 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @param sWhere
	 * @return long
	 * @throws MdbException
	 */
	private static long getWlboffreqRecordCount(MdbDriver oDb, Map param, String sWhere)
			throws MdbException {
		final String sql = "SELECT count(a.usn) "
				+ "  FROM wlc_off_req a , v_jmf_user b "
				+ "  WHERE a.usn = b.usn " + "  AND a.lec_cd = :lec_cd "
				+ "  AND a.itm_id = :itm_id  " + sWhere;
		return oDb.selectOneInt(sql, param);
	}

	/**
	 * 오프라인 강의를 신청한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return long
	 * @throws MdbException
	 */
	public static long insertOffOne(MdbDriver oDb, Map param) throws MdbException {
		int retValue = 0;
		CallableStatement cstmt = null;
		String sql = "{call SP_WLB_OFF_REQ (:itm_id, :seq_no, :lec_cd, :off_score, :usn, :flag, ?)}";

		try {
			cstmt = oDb.prepareCall(sql, param);
			cstmt.registerOutParameter(8, java.sql.Types.INTEGER);

			cstmt.executeUpdate();
			retValue = cstmt.getInt(8);
			if (retValue < 0) {
				retValue = -1;
			}
		} catch (SQLException s) {
			retValue = 0;
			oDb.rollback();
			logger.error(s.getMessage());
		} finally {
			release(cstmt);
		}
		return retValue;
	}

	/**
	 * 오프라인 강의신청을 수정한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return int
	 * @throws MdbException
	 */
	public static int updateOffOne(MdbDriver oDb, Map param) throws MdbException {
		int retValue = 0;
		CallableStatement cstmt = null;
		String sql = "{call SP_WLB_OFF_REQ (:itm_id, :seq_no, :lec_cd, :off_score, :usn, :flag, ?)}";

		try {
			cstmt = oDb.prepareCall(sql, param);
			cstmt.registerOutParameter(8, java.sql.Types.INTEGER);

			cstmt.executeUpdate();
			retValue = cstmt.getInt(8);
			if (retValue < 0) {
				retValue = -1;
			} else {
				retValue = 1;
			}
		} catch (SQLException s) {
			retValue = 0;
			oDb.rollback();
			logger.error(s.getMessage());
		} finally {
			release(cstmt);
		}
		return retValue;
	}

	/**
	 * 오프라인 강의신청을 취소한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return int
	 * @throws MdbException
	 */
	public static int deleteOffOne(MdbDriver oDb, Map param) throws MdbException {
		int retValue = 0;
		CallableStatement cstmt = null;
		String sql = "{call SP_WLB_OFF_REQ (:itm_id, :seq_no, :lec_cd, :off_score, :usn, :flag, ?)}";

		try {
			cstmt = oDb.prepareCall(sql, param);
			cstmt.registerOutParameter(8, java.sql.Types.INTEGER);

			cstmt.executeUpdate();
			retValue = cstmt.getInt(8);
			if (retValue < 0) {
				retValue = -1;
			} else {
				retValue = 1;
			}
		} catch (SQLException s) {
			retValue = 0;
			oDb.rollback();
			logger.error(s.getMessage());
		} finally {
			release(cstmt);
		}
		return retValue;
	}

	/**
	 * 시험 응시 조건을 가져 온다.
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getExmReq(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT lec_cd, :usn usn, (jindo_time_score*(jindo_time/100) + jindo_page_score*(jindo_page/100)) tot, " +
		"       jindo_time_score, jindo_page_score, exm_condition, jindo_time, jindo_page " +
		" FROM (SELECT mst.lec_cd, grd.usn, " +
		"              NVL (fn_get_wlcjindo_time (mst.lec_cd, :usn), 0) AS jindo_time_score, " +
		"              NVL (fn_get_wlcjindo_page (mst.lec_cd, :usn), 0) AS jindo_page_score, " +
		"              (SELECT NVL (exm_condition, 0) FROM bas_lec WHERE lec_cd = mst.lec_cd) exm_condition, " +
		"              jindo_time, jindo_page " +
		"          FROM wlc_rat_mst mst, " +
		"               (SELECT lec_cd, usn, tot_cnt, score10, tot_score, grad " +
		"                  FROM wlc_fnl_grd " +
		"                  WHERE lec_cd = :lec_cd " +
		"                  AND usn = :usn) grd " +
		" WHERE mst.lec_cd = grd.lec_cd(+) " +
		" AND mst.lec_cd = :lec_cd) t ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
}