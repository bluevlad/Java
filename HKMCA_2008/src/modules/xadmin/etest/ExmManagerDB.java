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
public class ExmManagerDB extends BaseDAO {

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
		final String sql = "select * from (SELECT exmid, exmowner, exmtitle, exmdesc,  " +
		"  exm_sdat, exm_edat, exmcnt5, exmcnt4, exmcnt3, exmcnt2, exmcnt1, " +
		"  s.exmtime, exmtype, exmtops, exmview, exmopen, exmpage,  " +
		"  m.upt_dt, m.upt_id, m.active_yn, " +
		" (select count(usn) from exm_rst where exmid = m.exmid) cnt    " +
		"  FROM exm_mst m, exm_set s " +
		"  WHERE m.setid = s.setid(+)) x ";

		String sOrder = navigator.getOrderSql();
		if (!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(ExmManagerDB.getRecordCount(oDb, param, sWhere.toString()));
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
		final String sql = "select count(*) from (select * " +
				" from EXM_MST m, exm_set s " +
				" where m.setid = s.setid(+)) x " + sWhere;
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
	 * 하나의 레코드를 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int updateOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update EXM_MST set "
		        + "exmtitle = :exmtitle  ,  "
		        + "exmdesc = :exmdesc  ,  "
		        + "exm_sdat = :exm_sdat  ,  "
		        + "exm_edat = :exm_edat  ,  "
		        + "exmtime = :exmtime  ,  "
		        + "exmview = :exmview  ,  "
		        + "exmopen = :exmopen  ,  "
		        + "exmpage = :exmpage  ,  "
                + "active_yn = :active_yn  ,  "
		        + "upt_dt = sysdate  ,  "
		        + "upt_id = :upt_id  "
		        + "where  exmid = :exmid  ";
		return oDb.executeUpdate(sql, param);
	}

	/**
	 * 하나의 시험을 등록 합니다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int insertOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO exm_mst"   +
		"             (exmid, exmowner, exmtitle, exmdesc, exm_sdat, exm_edat, exmtime, exmview,"   +
		"              exmopen, exmpage, reg_id, reg_dt, upt_dt, upt_id, setid)"   +
		"    SELECT seq_exm_mst_id.NEXTVAL, :exmowner, :exmtitle, :exmdesc, :exm_sdat, :exm_edat,"   +
		"           s.exmtime, :exmview, :exmopen, :exmpage, :reg_id, SYSDATE, SYSDATE, :upt_id, :setid"   +
		"      FROM exm_set s"   +
		"     WHERE setid = :setid"  ;
		return oDb.executeUpdate(sql, param);
	}

	/**
	 * 하나의 레코드를  insert or update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
    public static int mergeOne(MdbDriver oDb,  Map param) throws MdbException  {
    	final String sql = "MERGE INTO EXM_MST new " +
    	"      USING (SELECT :exmid exmid, :exmowner exmowner, :exmtitle exmtitle, :exmdesc exmdesc, " +
    	"                    :exm_sdat exm_sdat, :exm_edat exm_edat, :exmtime exmtime, :exmview exmview,"   +
		"                    :exmopen exmopen, :exmpage exmpage, :usn usn, :setid setid, :active_yn active_yn " +
    	"               FROM DUAL) src " +
    	"         ON (src.exmid = new.exmid) " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE SET " +
    	"			exmtitle = src.exmtitle, " +
    	"			exmdesc = src.exmdesc, " +
    	"			exm_sdat = src.exm_sdat, " +
    	"			exm_edat = src.exm_edat, " +
    	"			exmtime = src.exmtime, " +
    	"			exmview = src.exmview, " +
    	"			exmopen = src.exmopen, " +
    	"			exmpage = src.exmpage, " +
    	"			active_yn = src.active_yn, " +
    	"			upt_dt = sysdate, " +
    	"			upt_id = src.usn " +
    	"	 WHEN NOT MATCHED THEN " +
    	"       INSERT (" +
    	"                exmid, exmowner, exmtitle, exmdesc, exm_sdat, exm_edat, exmtime, exmview,"   +
		"                exmopen, exmpage, reg_id, reg_dt, upt_dt, upt_id, setid" +
    	"       ) VALUES (" +
    	"                  seq_exm_mst_id.NEXTVAL, src.exmowner, src.exmtitle, src.exmdesc, src.exm_sdat, src.exm_edat, src.exmtime, src.exmview, " +
    	"				   src.exmopen, src.exmpage, src.usn, SYSDATE, SYSDATE, src.usn, src.setid" +
    	"      ) ";
    	
    	return oDb.executeUpdate(sql, param);
    }

	/**
	 * 응시자 목록 돌려 주기 
	 */
	public static List getRstList(MdbDriver oDb, Map param, String order) throws MdbException {
		final String sql = "SELECT mu.nm, mu.userid, mu.pos_nm, er.* " +
		"  FROM exm_rst er, v_maf_user mu " +
		"  WHERE exmid = :exmid  " +
		"  AND er.usn = mu.usn " + order;

		return oDb.selector(Map.class, sql, param);
	}

	public static void getRstList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
		List list = null;
		final String sql = "select * from ( " +
		"SELECT mu.nm, mu.userid, mu.pos_nm, er.* " +
		"  FROM exm_rst er, v_maf_user mu " +
		"  WHERE exmid = :exmid  " +
		"  AND er.usn = mu.usn) ";
		
		String sOrder = "order by nm asc";

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(ExmManagerDB.getRstListCount(oDb, param, sWhere.toString()));
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
    private static long getRstListCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final String sql = "select count(*) from ( " +
		"SELECT mu.nm, mu.userid, mu.pos_nm, er.* " +
		"  FROM exm_rst er, v_maf_user mu " +
		"  WHERE exmid = :exmid  " +
		"  AND er.usn = mu.usn) " + sWhere;

        return oDb.selectOneInt(sql, param);
    }
	
	/**
	 * 응시자 목록 돌려 주기 (강의 시험용)
	 */
	public static List getExmmListforLecture(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select exm_mst.*, (select count(usn) from exm_rst where exmid = exm_mst.exmid) cnt " +
		" from exm_mst " +
		" where exmowner = :lec_cd ";

		return oDb.selector(Map.class, sql, param);
	}
	
	/**
	 * 응시자 목록 돌려 주기 (강의 시험용)
	 */
	public static List getRstListforLecture(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT l.*, NVL (er.rst_status, 'R') rst_status, er.lang, er.rst_sdt, er.rst_edt, er.rstscore100"   +
		"   FROM (SELECT mu.nm, mu.org_cd, bc.exmid, lr.lec_num, mu.usn , mu.pos_nm  "   +
		"           FROM bas_lec bc, wlc_lec_req lr, v_maf_user mu"   +
		"           WHERE bc.lec_cd = :lec_cd"   +
		"           AND bc.lec_cd = lr.lec_cd"   +
		"           AND lr.usn = mu.usn"   +
		"           AND lr.req_stat IN ('LP', 'LE')) l,"   +
		"        exm_rst er"   +
		"  WHERE l.exmid = er.exmid(+)"   +
		"  AND l.lec_num = er.lec_num(+)"  ;

		return oDb.selector(Map.class, sql, param);
	}
	
	/**
	 * 강의별 응시자 목록 돌려 주기
	 */
	public static void getRstListforLecture(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
		List list = null;
		final String sql = "select * from (  " +
		"SELECT er.exmid, l.nm, l.usn, l.userid, l.lec_num, NVL(er.rst_status, 'R') rst_status, er.lang, er.rst_sdt, er.rst_edt, er.rstscore100  " +
		"   FROM (SELECT bc.lec_cd, mu.nm, mu.usn, mu.userid, bc.exmid, lr.lec_num, mu.pos_nm    " +
		"           FROM bas_lec bc, wlc_lec_req lr, v_maf_user mu  " +
		"           WHERE bc.lec_cd = :lec_cd  " +
		"           AND bc.lec_cd = lr.lec_cd  " +
		"           AND lr.usn = mu.usn  " +
		"           AND lr.req_stat IN ('LP', 'LE')) l,  " +
		"        exm_mst em, exm_rst er  " +
		" WHERE em.exmid = er.exmid " +
		" and l.lec_cd = em.exmowner " +
		" and l.usn = er.usn)";
		
		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(ExmManagerDB.getRstListforLectureCount(oDb, param, sWhere.toString()));
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
    private static long getRstListforLectureCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final String sql = "select count(usn) from ( " +
		"SELECT er.exmid, l.nm, l.usn, l.userid, l.lec_num, NVL(er.rst_status, 'R') rst_status, er.lang, er.rst_sdt, er.rst_edt, er.rstscore100  " +
		"   FROM (SELECT bc.lec_cd, mu.nm, mu.usn, mu.userid, bc.exmid, lr.lec_num, mu.pos_nm    " +
		"           FROM bas_lec bc, wlc_lec_req lr, v_maf_user mu  " +
		"           WHERE bc.lec_cd = :lec_cd  " +
		"           AND bc.lec_cd = lr.lec_cd  " +
		"           AND lr.usn = mu.usn  " +
		"           AND lr.req_stat IN ('LP', 'LE')) l,  " +
		"        exm_mst em, exm_rst er  " +
		" WHERE em.exmid = er.exmid " +
		" and l.lec_cd = em.exmowner " +
        " and l.usn = er.usn)"  + sWhere;

        return oDb.selectOneInt(sql, param);
    }
	
	
	/**
	 * 응시자상태를 등록 상태로 바꿔주기 
	 */
	public static int setContinue(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update exm_rst" +
				" set rst_status = 'R'" +
				" where exmid = :exmid" +
				" and usn = :usn"  ;
		return oDb.executeUpdate( sql, param);
	}
	
	/**
	 * 응시자상태를 등록 상태로 바꿔주기 (작성 답안도 지움)
	 */
	public static int setRetry(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "update exm_rst" +
		" set rst_status = 'R', rst_sdt = null, rst_edt = null, RST_FULLSCORE = 0, RSTSCORE100 = 0" +
		" where exmid = :exmid and usn = :usn"  ;
		
		final String sql_del  = "delete from exm_rst_item  " +
		" where exmid = :exmid and usn = :usn"  ;
		int rv = 0;
		try {
			oDb.executeUpdate( sql_del, param);
			rv = oDb.executeUpdate( sql, param);
		} catch (Exception e) {
			
		}
		return rv;
	}
	
    /**
     * Exm Set List 
     */
    public static List getExmSetList(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select * from exm_set";
        return oDb.selector(Map.class, sql, param);
    }
    
    /**
     * 응시자의 결과를 가져 온다.
     */
    public static List getUserResultItem(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "SELECT i.qseq, i.usransw, DECODE (bl.queid, NULL, b.quetitle, bl.quetitle) quetitle, " +
        "       DECODE (bl.queid, NULL, b.quetext, bl.quetext) quetext, " +
        "       b.queimag, " +
        "       DECODE (bl.queid, NULL, b.queviw1, bl.queviw1) queviw1, " +
        "       DECODE (bl.queid, NULL, b.queviw2, bl.queviw2) queviw2, " +
        "       DECODE (bl.queid, NULL, b.queviw3, bl.queviw3) queviw3, " +
        "       DECODE (bl.queid, NULL, b.queviw4, bl.queviw4) queviw4, " +
        "       DECODE (bl.queid, NULL, b.queviw5, bl.queviw5) queviw5, " +
        "       DECODE (bl.queid, NULL, b.queviw6, bl.queviw6) queviw6, " +
        "       DECODE (bl.queid, NULL, b.queviw7, bl.queviw7) queviw7, " +
        "       DECODE (bl.queid, NULL, b.queviw8, bl.queviw8) queviw8, " +
        "       DECODE (bl.queid, NULL, b.queviw9, bl.queviw9) queviw9, " +
        "       DECODE (bl.queid, NULL, b.queviw10, bl.queviw10) queviw10, " +
        "       DECODE (bl.queid, NULL, b.queansw, bl.queansw) queansw, b.quecount, b.quetype, b.quelevel, " +
        "       b.queid, i.mark_yn,  i.quescore, i.usrscore, i.correct_yn " +
        "  FROM exm_rst_item i, exm_bnk b, (SELECT * " +
        "                                     FROM exm_bnk_lang bl " +
        "                                     WHERE lang = :lang) bl " +
        "  WHERE i.exmid = :exmid " +
        "  AND i.usn = :usn " +
        "  AND i.queid = bl.queid(+) " +
        "  AND i.queid = b.queid " +
        "  ORDER BY qseq ";
        return oDb.selector(Map.class, sql, param);
    }
    
    /**
     * 응시자의 결과를 가져 온다.
     */
    public static Map getUserResult(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "SELECT er.exmid, er.usn, er.rstfinal, er.rstcont, er.rst_sdt, er.rst_edt, er.rstatscore, er.rstatid, " +
        "        er.rstat_dt, er.rstmtscore, er.rstmtid, er.rstmt_dt, er.rstoffscore, er.rstoffid, " +
        "        er.rstoff_dt, er.rstflag, er.reg_id, er.reg_dt, er.upt_dt, er.upt_id, " +
        "        er.result_cont, er.lang, er.rst_status, er.rstscore, er.rst_fullscore, er.rstscore100, " +
        "        er.passing_score, er.passing_yn, mu.nm, mu.userid " +
        "  FROM exm_rst er, v_maf_user mu " +
        "  WHERE er.exmid = :exmid " +
        "  AND er.usn = :usn " +
        "  AND er.usn = mu.usn ";
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }
    
    /**
     * 한 문항의 채점 결과를 update 한다.
     * @param oDb
     * @param param [correct_yn, usrscore,  update_id, exmid, usn,lecnumb, qseq]
     * @return
     * @throws MdbException
     */
    public static int updateRstItems(MdbDriver oDb, Map param) throws MdbException {
    	final String sql = " UPDATE exm_rst_item"   +
    	"  SET correct_yn = :correct_yn,"   +
    	"      usrscore = :usrscore,"   +
    	"      upt_dt = sysdate,"   +
    	"      upt_id = :upt_id"   +
    	"  WHERE exmid = :exmid"   +
    	"  AND usn = :usn"   +
    	"  AND qseq = :qseq"  ;
    	return oDb.executeUpdate(sql, param);
    }
    
    /**
     * 총점 저장 및 채점 완료 처리 
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int updateRst(MdbDriver oDb, Map param) throws MdbException {
	    final String sql_tot = " UPDATE exm_rst r"   +
		"  SET (rstscore, rst_fullscore, rst_status, rstscore100, upt_dt, upt_id) = " +
		"  (SELECT SUM(usrscore), SUM(quescore), round((SUM(usrscore)/SUM(quescore))*100), 'F', sysdate, :upt_id"   +
		"     FROM exm_rst_item i"   +
		"     WHERE r.exmid = i.exmid"   +
		"     AND r.usn = i.usn)"   +
		"  WHERE exmid = :exmid"   +
		"  AND usn = :usn ";
		
		// 합계저장  및 종료 처리 
		return oDb.executeUpdate(sql_tot, param);
    }
    
    /**
	 * 한명의 응시자를 Insert 한다.
	 * 
	 * @param oDb
	 * @param param [ :exmid, :usn, :lecnumb, :insert_id ]
	 * @return
	 * @throws MdbException
	 */
	public static int insertRstOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO exm_rst"   +
		"             (exmid, usn, lec_num, reg_id, reg_dt)"   +
		"      VALUES (:exmid, :usn, :lec_num, :reg_id, SYSDATE )	"  ;
		
/*		final String sql_max = " SELECT nvl(MAX (lec_num)+1,1) m"   +
		"  FROM exm_rst"   +
		"  WHERE exmid = :exmid"   +
		"  AND usn = :usn	"  ;
		param.put("lec_num" , oDb.selectOneInt(sql_max, param)+"");
*/		return oDb.executeUpdate(sql, param);
	}

	/**
	 * 시험지 출력용 목록 가져오기 
	 * @param oDb
	 * @param param [exmid, lang]
	 * @return
	 * @throws MdbException
	 */
	public static List getPrintList(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT /*+INDEX(i pk_exm_rst_item)*/"   +
		"        DECODE (bl.queid, NULL, b.quetitle, bl.quetitle) quetitle,"   +
		"        DECODE (bl.queid, NULL, b.quetext, bl.quetext) quetext,"   +
		"        b.queimag,"   +
		"        DECODE (bl.queid, NULL, b.queviw1, bl.queviw1) queviw1,"   +
		"        DECODE (bl.queid, NULL, b.queviw2, bl.queviw2) queviw2,"   +
		"        DECODE (bl.queid, NULL, b.queviw3, bl.queviw3) queviw3,"   +
		"        DECODE (bl.queid, NULL, b.queviw4, bl.queviw4) queviw4,"   +
		"        DECODE (bl.queid, NULL, b.queviw5, bl.queviw5) queviw5,"   +
		"        DECODE (bl.queid, NULL, b.queviw6, bl.queviw6) queviw6,"   +
		"        DECODE (bl.queid, NULL, b.queviw7, bl.queviw7) queviw7,"   +
		"        DECODE (bl.queid, NULL, b.queviw8, bl.queviw8) queviw8,"   +
		"        DECODE (bl.queid, NULL, b.queviw9, bl.queviw9) queviw9,"   +
		"        DECODE (bl.queid, NULL, b.queviw10, bl.queviw10) queviw10, " +
		"		 b.quecount,"   +
		"        b.quetype quetype"   +
		"   FROM (SELECT d.*, ROW_NUMBER () OVER (ORDER BY DBMS_RANDOM.VALUE) RANK"   +
		"           FROM (SELECT i.queid, b.quescore, b.quelevel,"   +
		"                        ROW_NUMBER () OVER (PARTITION BY quelevel ORDER BY DBMS_RANDOM.VALUE) srank,"   +
		"                        s.exmcnt1, s.exmcnt2, s.exmcnt3, s.exmcnt4, s.exmcnt5"   +
		"                   FROM exm_set_item i, exm_bnk b, exm_set s, exm_mst m"   +
		"                  WHERE m.exmid = :exmid"   +
		"                    AND m.setid = s.setid"   +
		"                    AND s.setid = i.setid"   +
		"                    AND i.queid = b.queid) d"   +
		"          WHERE (    d.quelevel = 1"   +
		"                 AND d.srank <= d.exmcnt1)"   +
		"             OR (    d.quelevel = 2"   +
		"                 AND d.srank <= d.exmcnt2)"   +
		"             OR (    d.quelevel = 3"   +
		"                 AND d.srank <= d.exmcnt3)"   +
		"             OR (    d.quelevel = 4"   +
		"                 AND d.srank <= d.exmcnt4)"   +
		"             OR (    d.quelevel = 5"   +
		"                 AND d.srank <= d.exmcnt5)) i,"   +
		"        exm_bnk b,"   +
		"        (SELECT *"   +
		"           FROM exm_bnk_lang bl"   +
		"          WHERE lang = :lang) bl"   +
		"  WHERE i.queid = bl.queid(+)"   +
		"    AND i.queid = b.queid"  ;
		return oDb.selector(Map.class, sql, param);
	}
}
