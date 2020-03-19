package modules.wlc.classroom.tutor;

import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class TestManagerDB {

	/**
	 * 응시자 목록 돌려 주기 (강의 시험용)
	 */
	public static List getExmmListforLecture(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select wlc_exm_mst.*, (select count(usn) from wlc_exm_rst where exmid = wlc_exm_mst.exmid) cnt " +
		" from wlc_exm_mst " +
		" where lec_cd = :lec_cd ";

		return oDb.selector(Map.class, sql, param);
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
		final String sql =" SELECT em.exmid, em.lec_cd, em.exmtitle, em.exmdesc, "   +
		"        exm_sdat, exm_edat, s.exmcnt1, s.exmcnt2, s.exmcnt3, s.exmcnt4, s.exmcnt5,"   +
		"        em.exmtime, s.exmtype, em.exmtops, em.exmview, em.exmopen, em.exmpage,"   +
		"        em.reg_id, em.reg_dt, em.upt_dt, em.upt_id, em.setid, em.active_yn, s.settitle,"   +
		"        NVL (st.cnt_reg, 0) cnt_reg, NVL (st.cnt_i, 0) cnt_i, NVL (st.cnt_s, 0) cnt_s"   +
		"   FROM wlc_exm_mst em,"   +
		"        wlc_exm_set s,"   +
		"        (SELECT   exmid, SUM (1) cnt_reg, SUM (DECODE (rst_status, 'I', 1, 0)) cnt_i,"   +
		"                  SUM (DECODE (rst_status, 'S', 1, 'C', 1, 'F', 1, 0)) cnt_s"   +
		"             FROM wlc_exm_rst"   +
		"            WHERE exmid = :exmid"   +
		"         GROUP BY exmid) st"   +
		"  WHERE em.exmid = :exmid"   +
		"    AND em.exmid = st.exmid(+)"   +
		"    AND em.setid = s.setid(+)"  ;		
		
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
	/**
	 * 자신의 시험정보  가져오기 
	 * 
	 * @param oDb
	 * @param param [usn, lecnumb, exmid ]
	 * @return
	 * @throws MdbException
	 */
	public static Map getMyOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT m.exmid, m.lec_cd, m.exmtitle, m.exmdesc, m.exm_sdat, m.exm_edat, s.exmcnt5, " +
		"       s.exmcnt4, s.exmcnt3, s.exmcnt2, s.exmcnt1, m.exmtime, s.exmtype, m.exmtops, m.exmview, " +
		"       m.exmopen, m.exmpage, m.reg_id, m.reg_dt, m.upt_dt, m.upt_id, m.setid,  " +
		"       case when m.exm_edat < sysdate then 'F' else r.rst_status end rst_status, " +
		"       r.lang, r.rst_sdt, r.rst_edt, " +
		"       ((sysdate - r.rst_sdt)*24*60 - m.exmtime) remain_time " +
		"  FROM wlc_exm_rst r, wlc_exm_mst m, wlc_exm_set s " +
		"  WHERE r.exmid = m.exmid " +
		"  AND r.usn = :usn " +
		"  AND r.exmid = :exmid " +
		"  AND m.setid = s.setid ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
/**
	 * 한 시험의 평균 정보를 구한 다. 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getTestResultByExmId(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "  SELECT rd.*, AVG (DECODE (SIGN (rstrank100 - 0.1), -1, rd.rstscore100, NULL)) OVER () rstavg10, round(cnt_pass/rstcnt,2)*100 passrate"   +
		"    FROM (SELECT r.exmid, r.usn, r.rstscore, rstscore100,"   +
		"                 ROUND (PERCENT_RANK () OVER (ORDER BY rstscore100 DESC), 2) rstrank100,"   +
		"                 SUM (1) OVER () rstcnt, "   +
		" 				ROUND (AVG (r.rstscore100) OVER (), 2) rstavg,"   +
		"                 MAX (r.rstscore100) OVER () rstscoremax, "   +
		" 				MIN (r.rstscore100) OVER () rstscoremin,"   +
		" 				sum(decode(r.passing_yn,'Y',1,0)) OVER () cnt_pass,"   +
		" 				r.passing_yn,"   +
		" 				AVG (DECODE (passing_yn, 'Y', r.rstscore100, NULL)) OVER () passavg"   +
		"            FROM wlc_exm_rst r, wlc_exm_mst m"   +
		"           WHERE r.exmid = :exmid"   +
		"             AND r.exmid = m.exmid"   +
		"             AND r.rst_status = 'F') rd			"   +
		"   WHERE ROWNUM < 2"  ;
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
	/**
	 * 강의별 응시자 목록 돌려 주기
	 */
	public static void getRstListforLecture(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
		List list = null;
		final String sql = "select * from (  " +
		"SELECT er.exmid, l.nm, l.usn, l.userid, NVL(er.rst_status, 'R') rst_status, er.lang, er.rst_sdt, er.rst_edt, er.rstscore100  " +
		"   FROM (SELECT bc.lec_cd, mu.nm, mu.usn, mu.userid, bc.exmid, mu.pos_nm    " +
		"           FROM bas_lec bc, wlc_lec_req lr, v_maf_user mu  " +
		"           WHERE bc.lec_cd = :lec_cd  " +
		"           AND bc.lec_cd = lr.lec_cd  " +
		"           AND lr.usn = mu.usn  " +
		"           AND lr.req_stat IN ('LP', 'LE')) l,  " +
		"        wlc_exm_mst em, wlc_exm_rst er  " +
		" WHERE em.exmid = er.exmid " +
		" and l.lec_cd = em.lec_cd " +
		" and l.usn = er.usn" +
		" and em.exmid = :exmid)";
		
		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(TestManagerDB.getRstListforLectureCount(oDb, param, sWhere.toString()));
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
		"SELECT er.exmid, l.nm, l.usn, l.userid, NVL(er.rst_status, 'R') rst_status, er.lang, er.rst_sdt, er.rst_edt, er.rstscore100  " +
		"   FROM (SELECT bc.lec_cd, mu.nm, mu.usn, mu.userid, bc.exmid, mu.pos_nm    " +
		"           FROM bas_lec bc, wlc_lec_req lr, v_maf_user mu  " +
		"           WHERE bc.lec_cd = :lec_cd  " +
		"           AND bc.lec_cd = lr.lec_cd  " +
		"           AND lr.usn = mu.usn  " +
		"           AND lr.req_stat IN ('LP', 'LE')) l,  " +
		"        wlc_exm_mst em, wlc_exm_rst er  " +
		" WHERE em.exmid = er.exmid " +
		" and l.lec_cd = em.lec_cd " +
        " and l.usn = er.usn" +
        " and em.exmid = :exmid)"  + sWhere;

        return oDb.selectOneInt(sql, param);
    }
    
    /**
     * 응시자의 결과를 가져 온다.
     */
    public static Map getUserResult(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " SELECT er.exmid, er.usn, er.rstfinal, er.rstcont, er.rst_sdt, er.rst_edt, er.rstatscore, er.rstatid,"   +
        "        er.rstat_dt, er.rstmtscore, er.rstmtid, er.rstmt_dt, er.rstoffscore, er.rstoffid,"   +
        "        er.rstoff_dt, er.rstflag, er.reg_id, er.reg_dt, er.upt_dt, er.upt_id,"   +
        "        er.result_cont, er.lang, er.rst_status, er.rstscore, er.rst_fullscore, er.rstscore100,"   +
        "        er.passing_score, er.passing_yn, mu.nm, mu.userid"   +
        "   FROM wlc_exm_rst er, v_maf_user mu"   +
        "  WHERE er.exmid = :exmid"   +
        "    AND er.usn = :usn"   +
        "    AND er.usn = mu.usn";
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }
	
	/**
	 * 시험에서 지원하는 언어 가져 오기 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getLangList(MdbDriver oDb,  Map param ) throws MdbException {
		
		final String sql = "SELECT d.lang, la.allnames " +
		" FROM (SELECT lang " +
		"         FROM wlc_exm_set " +
		"         WHERE setid = (SELECT setid " +
		"                         FROM wlc_exm_mst " +
		"                         WHERE exmid = :exmid) " +
		"      ) d, v_jmf_lang la " +
		" WHERE d.lang = la.code ";

		return oDb.selector(Map.class, sql , param);
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
		"        DECODE (bl.queid, NULL, b.queimag, bl.queimag) queimag,"   +
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
		"                   FROM wlc_exm_set_item i, wlc_exm_bnk b, wlc_exm_set s, wlc_exm_mst m"   +
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
		"        wlc_exm_bnk b,"   +
		"        (SELECT *"   +
		"           FROM wlc_exm_bnk_lang bl"   +
		"          WHERE lang = :lang) bl"   +
		"  WHERE i.queid = bl.queid(+)"   +
		"    AND i.queid = b.queid"  ;
		return oDb.selector(Map.class, sql, param);
	}

	/**
	 * 
	 * 강좌용 시험을 만든다.
	 *
	 * @param oDb
	 * @param param [usn, leccode, exmpage]
	 * @return
	 * @throws MdbException
	 */
	public static int MakeExam(MdbDriver oDb, Map param) throws MdbException {
		
		int rcnt = 0;

    	final String sql_exmid  = " SELECT seq_exm_set_id.NEXTVAL"   +
    	"   FROM DUAL"  ;
    	
    	final String  sql_insert_exm_mst  = " INSERT INTO wlc_exm_mst"   +
    	"             (exmid, lec_cd, exmtitle, exmdesc, exm_sdat_t1, exm_edat_t1, exmtime, exmpage,"   +
    	"              reg_usn, reg_dt, update_dt, update_usn, setid, owntype)"   +
    	"    SELECT :exmid, cl.lec_cd, es.settitle, es.setdesc, cl.lec_sdate, cl.lec_edate,"   +
    	"           es.exmtime, :exmpage, :usn, SYSDATE, SYSDATE, :usn, s.exm_setid, 'L'"   +
    	"      FROM bas_sjt s, wlc_exm_set es, bas_lec cl"   +
    	"     WHERE cl.lec_cd = :lec_cd"   +
    	"       AND s.sjt_cd = cl.sjt_cd"   +
    	"       AND s.exm_setid = es.setid"   +
    	"       AND es.active_yn = 'Y'"  ;
    	
    	
    	final  String sql_update_lec =  " UPDATE bas_lec"   +
	    	"    SET exmid = :exmid"   +
	    	"  WHERE lec_cd = :lec_cd"  ;
    	
    	// exmid 생성 (강좌의 exmid 는 L로 시작됨)
    	String exmid = "L_" + oDb.selectOne(sql_exmid);
    	param.put("exmid", exmid);
    	
		rcnt += oDb.executeUpdate(sql_insert_exm_mst, param);
		oDb.executeUpdate(sql_update_lec, param);
    	
		return rcnt;
	}
	
	
	/**
	 * 해당 과목의 시험 Set 정보를 가져 온다.
	 * @param oDb
	 * @param param [leccode]
	 * @return
	 * @throws MdbException
	 */
	public static Map getExmSet(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT s.exm_setid, es.*"   +
		" FROM bas_sjt s, wlc_exm_set es"   +
		" WHERE s.sjt_cd = (select sjt_cd from bas_lec where lec_cd = :lec_cd ) "   +
		" AND s.exm_setid = es.setid";
		
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
    
	public static Map getTestResultbyUser(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT *"   +
		"   FROM (SELECT rd.*, AVG (DECODE (SIGN (rstrank100 - 0.1), -1, rd.rstscore100, NULL)) OVER ()"   +
		"                                                                                            rstavg10,"   +
		"                ROUND (cnt_pass / rstcnt, 2) * 100 passrate"   +
		"           FROM (SELECT r.exmid, r.usn, q.qcnt, q.qsum, r.rstscore, rstscore100,"   +
		"                        RANK () OVER (ORDER BY r.rstscore100 DESC) rstrank,"   +
		"                        ROUND (PERCENT_RANK () OVER (ORDER BY rstscore100 DESC), 2) rstrank100,"   +
		"                        SUM (1) OVER () rstcnt, ROUND (AVG (r.rstscore100) OVER (), 2) rstavg,"   +
		"                        SUM (DECODE (r.passing_yn, 'Y', 1, 0)) OVER () cnt_pass, r.passing_yn,"   +
		"                        AVG (DECODE (passing_yn, 'Y', r.rstscore100, NULL)) OVER () passavg,"   +
		"                        MAX (r.rstscore100) OVER () rstscoremax,"   +
		"                        MIN (r.rstscore100) OVER () rstscoremin"   +
		"                   FROM wlc_exm_rst r, wlc_exm_mst m, (SELECT   exmid, COUNT (*) qcnt, SUM (quescore) qsum"   +
		"                                                   FROM wlc_exm_rst_item"   +
		"                                                  WHERE exmid = :exmid"   +
		"                                                    AND usn = :usn"   +
		"                                               GROUP BY exmid) q"   +
		"                  WHERE r.exmid = :exmid"   +
		"                    AND r.exmid = m.exmid"   +
		"                    AND r.exmid = q.exmid"   +
		"                    AND r.rst_status = 'F') rd) stat"   +
		"  WHERE usn = :usn"  ;
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
    /**
     * 응시자의 결과를 가져 온다.
     */
    public static List getUserResultItem(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " SELECT i.qseq, i.usransw, DECODE (bl.queid, NULL, b.quetitle, bl.quetitle) quetitle,"   +
        "          DECODE (bl.queid, NULL, b.quetext, bl.quetext) quetext,"   +
        "          DECODE (bl.queid, NULL, b.queimag, bl.queimag) queimag,"   +
        "          DECODE (bl.queid, NULL, b.queviw1, bl.queviw1) queviw1,"   +
        "          DECODE (bl.queid, NULL, b.queviw2, bl.queviw2) queviw2,"   +
        "          DECODE (bl.queid, NULL, b.queviw3, bl.queviw3) queviw3,"   +
        "          DECODE (bl.queid, NULL, b.queviw4, bl.queviw4) queviw4,"   +
        "          DECODE (bl.queid, NULL, b.queviw5, bl.queviw5) queviw5,"   +
        "          DECODE (bl.queid, NULL, b.queviw6, bl.queviw6) queviw6,"   +
        "          DECODE (bl.queid, NULL, b.queviw7, bl.queviw7) queviw7,"   +
        "          DECODE (bl.queid, NULL, b.queviw8, bl.queviw8) queviw8,"   +
        "          DECODE (bl.queid, NULL, b.queviw9, bl.queviw9) queviw9,"   +
        "          DECODE (bl.queid, NULL, b.queviw10, bl.queviw10) queviw10,"   +
        "          DECODE (bl.queid, NULL, b.queansw, bl.queansw) queansw, b.quecount, b.quetype, b.quelevel,"   +
        "          b.queid, i.mark_yn,  i.quescore, i.usrscore, i.correct_yn"   +
        "     FROM wlc_exm_rst_item i, wlc_exm_bnk b, (SELECT *"   +
        "                                        FROM wlc_exm_bnk_lang bl"   +
        "                                       WHERE lang = :lang) bl"   +
        "    WHERE i.exmid = :exmid"   +
        "      AND i.usn = :usn"   +
        "      AND i.queid = bl.queid(+)"   +
        "      AND i.queid = b.queid"   +
        " ORDER BY qseq"  ;
        return oDb.selector(Map.class, sql, param);
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
		final String sql = " INSERT INTO wlc_exm_mst"   +
		"             (exmid, lec_cd, exmtitle, exmdesc, exm_sdat, exm_edat, exmtime, exmview,"   +
		"              exmopen, exmpage, reg_id, reg_dt, upt_dt, upt_id, setid)"   +
		"    SELECT seq_exm_mst_id.NEXTVAL, :lec_cd, :exmtitle, :exmdesc, :exm_sdat, :exm_edat,"   +
		"           s.exmtime, :exmview, :exmopen, :exmpage, :reg_id, SYSDATE, SYSDATE, :upt_id, :setid"   +
		"      FROM wlc_exm_set s"   +
		"     WHERE setid = :setid"  ;
		return oDb.executeUpdate(sql, param);
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
		final String sql = " INSERT INTO wlc_exm_rst"   +
		"             (exmid, usn, reg_id, reg_dt)"   +
		"      VALUES (:exmid, :usn, :reg_id, SYSDATE )	"  ;
		
		/*		final String sql_max = " SELECT nvl(MAX (lec_num)+1,1) m"   +
				"  FROM exm_rst"   +
				"  WHERE exmid = :exmid"   +
				"  AND usn = :usn	"  ;
				param.put("lec_num" , oDb.selectOneInt(sql_max, param)+"");
		*/		return oDb.executeUpdate(sql, param);
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
		final String sql = " update wlc_exm_mst set "
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
		        + "where exmid = :exmid  ";
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
	    final String sql_tot = "UPDATE wlc_exm_rst r " +
	    " SET (rstscore, rst_fullscore, rst_status, upt_dt, upt_id) =  " +
	    "  (SELECT SUM(usrscore), SUM(quescore) , 'F', sysdate, :upt_id " +
	    "     FROM wlc_exm_rst_item i " +
	    "     WHERE r.exmid = i.exmid " +
	    "     AND r.usn = i.usn) " +
	    " WHERE exmid = :exmid " +
	    " AND usn = :usn";
		
		// 합계저장  및 종료 처리 
		return oDb.executeUpdate(sql_tot,param);
    }
    
    /**
     * 한 문항의 채점 결과를 update 한다.
     * @param oDb
     * @param param [correct_yn, usrscore,  update_id, exmid, usn,lecnumb, qseq]
     * @return
     * @throws MdbException
     */
    public static int updateRstItems(MdbDriver oDb, Map param) throws MdbException {
    	final String sql = " UPDATE wlc_exm_rst_item"   +
    	"  SET correct_yn = :correct_yn,"   +
    	"      usrscore = :usrscore,"   +
    	"      upt_dt = sysdate,"   +
    	"      upt_id = :upt_id"   +
    	"  WHERE exmid = :exmid"   +
    	"  AND usn = :usn"   +
    	"  AND qseq = :qseq"  ;
    	return oDb.executeUpdate(sql,param);
    }
	
	/**
	 * 응시자상태를 등록 상태로 바꿔주기 (작성 답안도 지움)
	 */
	public static int setRetry(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update wlc_exm_rst" +
		" set rst_status = 'R'," +
		" rst_sdt = null," +
		" rst_edt = null," +
		" passing_yn = null," +
		" RST_FULLSCORE = 0," +
		" RSTSCORE100 = 0" +
		" where exmid = :exmid" +
		" and usn = :usn";
		
		final String sql_del  = " delete from wlc_exm_rst_item" +
		" where exmid = :exmid" +
		" and usn = :usn";
		
		int rv = 0;
		try {
			oDb.executeUpdate(sql_del, param);
			rv = oDb.executeUpdate(sql, param);
		} catch (Exception e) {
			
		}
		return rv;
	}
	
	/**
	 * 응시자상태를 등록 상태로 바꿔주기 
	 */
	public static int setContinue(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update wlc_exm_rst " +
				" set rst_status = 'R' " +
				" where exmid = :exmid " +
				" and usn = :usn ";
		return oDb.executeUpdate( sql, param);
	}
	
	/**
	 * 하나의 답을 update 한다.
	 * @param oDb
	 * @param param [usransw, exmid : 시험 id,usn :응시자 usn, qseq ]
	 * @return
	 * @throws MdbException
	 */
	public static int updateAnswerOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " UPDATE wlc_exm_rst_item"   +
			" SET usransw = :usransw"   +
			" WHERE exmid = :exmid " +
			" AND usn = :usn " +
			" and  qseq = :qseq";
		return oDb.executeUpdate(sql,param);
	}
	/**
	 * 안푼 답의 수를 가져온다. 
	 * @param oDb
	 * @param param [usransw, exmid : 시험 id,usn :응시자 usn, qseq ]
	 * @return
	 * @throws MdbException
	 */
	public static int getLeftCount(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " select count(*) cnt from wlc_exm_rst_item"   +
			"  WHERE exmid = :exmid AND usn = :usn and usransw is null"  ;
		return oDb.selectOneInt(sql, param);
	}

	/**
	 * 하나의 Mark을 update 한다.
	 * @param oDb
	 * @param param [mark_yn, exmid : 시험 id,usn :응시자 usn, qseq ]
	 * @return
	 * @throws MdbException
	 */
	public static int updateMarkOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " UPDATE wlc_exm_rst_item"   +
			"    SET mark_yn = :mark_yn"   +
			"  WHERE exmid = :exmid AND usn = :usn and qseq = :qseq"  ;
		return oDb.executeUpdate(sql,param);
	}
}