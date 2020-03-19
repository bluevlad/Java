/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.xadmin.etest;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

/**
 * 시험관리용 DAO
 * @author UBQ
 *
 */
public class CrtManagerDB extends BaseDAO {

	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
		List list = null;
		final String sql = "select *  " +
		"  from (SELECT mu.nm, mu.userid, mu.pos_nm, er.*  " +
		"          FROM exm_rst er, v_maf_user mu  " +
		"          WHERE exmid = :exmid   " +
		"          AND er.usn = mu.usn " +
		"  ) x ";

		String sOrder = navigator.getOrderSql();
		if (!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(CrtManagerDB.getRecordCount(oDb, param, sWhere.toString()));
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
	 * @return
	 * @throws MdbException
	 */
	private static long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final String sql = "select count(*) " +
		"  from (SELECT mu.nm, mu.userid, mu.pos_nm, er.*  " +
		"          FROM exm_rst er, v_maf_user mu  " +
		"          WHERE exmid = :exmid   " +
		"          AND er.usn = mu.usn " +
		"  ) x " + sWhere;
		return oDb.selectOneInt(sql, param);
	}

	/**
	 * 하나의 시험정보 읽어 오기
	 * 
	 * @param oDb
	 * @param param [ exmid]
	 * @return
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT em.exmid, em.exmowner, em.exmtitle, em.exmdesc,  " +
		"        em.exm_sdat, em.exm_edat, em.exmtime, s.exmtype, em.exmtops, em.exmview, em.exmopen, em.exmpage,  " +
		"        em.reg_id, em.reg_dt, em.upt_dt, em.upt_id, em.setid, em.active_yn,  " +
		"        s.settitle, s.passing_score, s.exmtype, s.exmtime, " +
		"        s.exmcnt1, s.exmcnt2, s.exmcnt3, s.exmcnt4, s.exmcnt5,   " +
		"        NVL (st.cnt_reg, 0) cnt_reg, NVL (st.cnt_i, 0) cnt_i, NVL (st.cnt_s, 0) cnt_s  " +
		"  FROM exm_mst em, exm_set s,  " +
		"        (SELECT exmid, SUM (1) cnt_reg, SUM (DECODE (rst_status, 'I', 1, 0)) cnt_i,  " +
		"                SUM (DECODE (rst_status, 'S', 1, 'C', 1, 'F', 1, 0)) cnt_s  " +
		"           FROM exm_rst  " +
		"           WHERE exmid = :exmid  " +
		"           GROUP BY exmid) st  " +
		"  WHERE em.exmid = :exmid  " +
		"  AND em.exmid = st.exmid(+)  " +
		"  AND em.setid = s.setid(+) ";

		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 점수 계급 분포표를 작성한다.
	 */
	public static int makeLank(MdbDriver oDb, Map param) throws MdbException {
        String sql = "{call RANK.SP_MAKE_LANK (:exmid)}";

		return oDb.executeUpdate(sql, param);
	}

	/**
	 * 점수 계급 분포표를 작성한다.
	 */
	public static int makeCrtVar(MdbDriver oDb, Map param) throws MdbException {
        String sql = "{call RANK.SP_MAKE_CRT_VAR (:exmid)}";

		return oDb.executeUpdate(sql, param);
	}

	/**
	 * Lank 정보를 삭제한다.
	 * 
	 */
	public static int deleteLank(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " delete from crt_lank "
		        + " where exmid = :exmid  ";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * Lank 정보를 삭제한다.
	 * 
	 */
	public static int setNum(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update crt_var " +
		" set last_num = :last_num " +
  		" WHERE exmid = :exmid " +
  		" AND score = :score";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * Lank 정보를 입력한다.
	 * 
	 */
	public static int setLank(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " insert into crt_lank (exmid, lank, score)"
		        + " values(:exmid, :lank, :score) ";
		return oDb.executeUpdate(sql, param);
	}

	public static List getRateList(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select *  " +
		"  from crt_log_tbl " +
		"  where score < 91 " +
		"  order by score asc ";

		return oDb.selector(Map.class, sql, param);
	}

	public static List getRstList(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select rslt, count(rslt) cnt " +
		"  FROM crt_var " +
		"  WHERE exmid = :exmid  " +
		"  group by rslt " +
		"  order by rslt desc ";

		return oDb.selector(Map.class, sql, param);
	}

	public static int getStdCnt(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select count(er.usn)  " +
        "  from exm_rst er, v_maf_user mu   " +
        "  WHERE exmid = :exmid    " +
        "  AND er.usn = mu.usn ";
		return oDb.selectOneInt(sql, param);
	}

	public static int get1stCnt(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select sum(prv_num) " +
        "  FROM crt_var " +
        "  WHERE exmid = :exmid ";
		return oDb.selectOneInt(sql, param);
	}

}