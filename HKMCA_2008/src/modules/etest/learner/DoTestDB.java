package modules.etest.learner;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class DoTestDB extends BaseDAO {
	/**
	 * 문제풀이 화면의 navigation정보를 돌려 준다.
	 * @param oDb
	 * @param param [exmid : 시험 id,usn :응시자 usn ]
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	public static List getNavi(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT /*+INDEX(i pk_exm_rst_item)*/ " +
		"       i.exmid, i.pageno, i.qseq, i.queid, i.mark_yn, NVL2 (i.usransw, 'Y', 'N') answerd,  " +
		" 	   decode(i.pageno,:pageno,'navi_cur',  decode(mod(i.pageno,2), 1, 'navi_odd','navi_even')) navi_class " +
		"  FROM exm_rst_item i " +
		"  WHERE i.exmid = :exmid AND i.usn = :usn  " +
		"  order by qseq asc ";
		return oDb.selector(Map.class, sql, param);
	}
	
	
	/**
	 * 한페이지의 문제를 가져온다.
	 * @param oDb
	 * @param param[exmid : 시험 id,usn :응시자 usn, pageno :보여줄 페이지 ]
	 * @return
	 * @throws MdbException
	 */
	public static List getOnePage(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT /*+INDEX(i pk_exm_rst_item)*/ " +
		"       i.usransw,  " +
		"       DECODE (bl.queid, NULL, b.quetitle, bl.quetitle) quetitle,  " +
		"       DECODE (bl.queid, NULL, b.quetext, bl.quetext) quetext,  " +
		"       b.queimag,  " +
		"       DECODE (bl.queid, NULL, b.queviw1, bl.queviw1) queviw1,  " +
		"       DECODE (bl.queid, NULL, b.queviw2, bl.queviw2) queviw2,  " +
		"       DECODE (bl.queid, NULL, b.queviw3, bl.queviw3) queviw3,  " +
		"       DECODE (bl.queid, NULL, b.queviw4, bl.queviw4) queviw4,  " +
		"       DECODE (bl.queid, NULL, b.queviw5, bl.queviw5) queviw5,  " +
		"       DECODE (bl.queid, NULL, b.queviw6, bl.queviw6) queviw6,  " +
		"       DECODE (bl.queid, NULL, b.queviw7, bl.queviw7) queviw7,  " +
		"       DECODE (bl.queid, NULL, b.queviw8, bl.queviw8) queviw8,  " +
		"       DECODE (bl.queid, NULL, b.queviw9, bl.queviw9) queviw9,  " +
		"       DECODE (bl.queid, NULL, b.queviw10,bl.queviw10) queviw10,  " +
		"       b.quecount, b.quetype, b.quelevel, b.queid, i.qseq, i.mark_yn, i.PAGENO " +
		"  FROM exm_rst_item i, exm_bnk b, (SELECT * " +
		"                                     FROM exm_bnk_lang bl " +
		"                                     WHERE lang = :lang) bl " +
		"  WHERE i.exmid = :exmid " +
		"  AND i.usn = :usn " +
		"  AND i.pageno = :pageno " +
		"  AND i.queid = bl.queid(+) " +
		"  AND i.queid = b.queid " +
		"  ORDER BY qseq ";
		return oDb.selector(Map.class, sql, param);
	}
	/**
	 * 하나의 답을 update 한다.
	 * @param oDb
	 * @param param [usransw, exmid : 시험 id,usn :응시자 usn, qseq ]
	 * @return
	 * @throws MdbException
	 */
	public static int updateAnswerOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " UPDATE exm_rst_item"   +
			"    SET usransw = :usransw"   +
			"  WHERE exmid = :exmid AND usn = :usn AND qseq = :qseq"   ;
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
		final String sql = " select count(*) cnt from exm_rst_item"   +
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
		final String sql = " UPDATE exm_rst_item"   +
			"    SET mark_yn = :mark_yn"   +
			"  WHERE exmid = :exmid AND usn = :usn and qseq = :qseq"  ;
		return oDb.executeUpdate(sql,param);
	}
	/**
	 * 답안지를 시험중 상태로 바꾸고 답안지를 생성 한다.
	 */
	public static int updateRst(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " UPDATE exm_rst"   +
			"    SET rst_status = 'I' , rst_sdt = sysdate , lang = :lang "   +
			"  WHERE exmid = :exmid AND usn = :usn and rst_status = 'R' "  ;
		
		final String sql_cnt = "select count(*) cnt from exm_rst_item where exmid = :exmid AND usn = :usn";
		
		final String sql_make = "INSERT INTO exm_rst_item"   +
		"             (exmid, usn, qseq, queid, quescore, pageno)"   +
		"    SELECT x.exmid, x.usn, x.RANK, x.queid, x.quescore, CEIL (common.divide (x.RANK, :npage)) page"   +
		"      FROM (SELECT m.exmid, i.setid, i.queid, i.qseq, b.quescore, b.quelevel, r.usn,"   +
		"                   ROW_NUMBER () OVER (ORDER BY DBMS_RANDOM.VALUE) RANK"   +
		"              FROM exm_set_item i, exm_bnk b, exm_set s, exm_mst m, exm_rst r"   +
		"             WHERE r.exmid = :exmid"   +
		"               AND r.usn = :usn"   +
		"               AND r.exmid = m.exmid"   +
		"               AND m.setid = s.setid"   +
		"               AND s.setid = i.setid"   +
		"               AND i.queid = b.queid) x"   +
		"     WHERE RANK <= :qnumber"  ;
		
		final String sql_make_new_x = " INSERT INTO exm_rst_item"   +
		"             (exmid, qseq, queid, quescore, pageno, usn)"   +
		"    SELECT p.exmid, p.RANK, p.queid, p.quescore, CEIL (common.divide (p.RANK, p.exmpage)) page, :usn"   +
		"      FROM (SELECT d.*, ROW_NUMBER () OVER (ORDER BY DBMS_RANDOM.VALUE) RANK"   +
		"              FROM (SELECT m.exmid, i.setid, i.queid, i.qseq, b.quescore, b.quelevel,"   +
		"                           NVL (m.exmpage, 0) exmpage,"   +
		"                           ROW_NUMBER () OVER (PARTITION BY quelevel ORDER BY DBMS_RANDOM.VALUE) srank,"   +
		"                           s.exmcnt1, s.exmcnt2, s.exmcnt3, s.exmcnt4, s.exmcnt5"   +
		"                      FROM exm_set_item i, exm_bnk b, exm_set s, exm_mst m"   +
		"                     WHERE m.exmid = :exmid"   +
		"                       AND m.setid = s.setid"   +
		"                       AND s.setid = i.setid"   +
		"                       AND i.queid = b.queid) d"   +
		"             WHERE (    d.quelevel = 1"   +
		"                    AND d.srank <= d.exmcnt1)"   +
		"                OR (    d.quelevel = 2"   +
		"                    AND d.srank <= d.exmcnt2)"   +
		"                OR (    d.quelevel = 3"   +
		"                    AND d.srank <= d.exmcnt3)"   +
		"                OR (    d.quelevel = 4"   +
		"                    AND d.srank <= d.exmcnt4)"   +
		"                OR (    d.quelevel = 5"   +
		"                    AND d.srank <= d.exmcnt5)) p"  ;
		
		// 전체 문항에 대해서 문제  순서만 변경한다.
		final String  sql_make_new = " INSERT INTO exm_rst_item"   +
		"             (exmid, qseq, queid, quescore, pageno, usn)"   +
		" SELECT p.exmid, p.RANK, p.queid, p.quescore, " +
		"       CEIL (common.divide (p.RANK, p.exmpage)) page, :usn " +
		"  FROM (SELECT d.*, " +
		"               ROW_NUMBER () OVER (ORDER BY DECODE (exmtype, " +
		"                                                    'r', DBMS_RANDOM.VALUE, " +
		"                                                    qseq " +
		"                                                   )) RANK " +
		"          FROM (SELECT m.exmid, i.setid, i.queid, i.qseq, b.quescore, " +
		"                       b.quelevel, NVL (m.exmpage, 0) exmpage, exmtype, " +
		"                       ROW_NUMBER () OVER (PARTITION BY quelevel ORDER BY DBMS_RANDOM.VALUE) srank, " +
		"                       s.exmcnt1, s.exmcnt2, s.exmcnt3, s.exmcnt4, s.exmcnt5 " +
		"                  FROM exm_set_item i, exm_bnk b, exm_set s, exm_mst m " +
		"                 WHERE m.exmid = :exmid " +
		"                   AND m.setid = s.setid " +
		"                   AND s.setid = i.setid " +
		"                   AND i.queid = b.queid) d " +
		"         WHERE (d.quelevel = 1 AND d.srank <= d.exmcnt1) " +
		"            OR (d.quelevel = 2 AND d.srank <= d.exmcnt2) " +
		"            OR (d.quelevel = 3 AND d.srank <= d.exmcnt3) " +
		"            OR (d.quelevel = 4 AND d.srank <= d.exmcnt4) " +
		"            OR (d.quelevel = 5 AND d.srank <= d.exmcnt5)) p ";

		// 답안지 문항 생성 여부 확인
		int cnt = oDb.selectOneInt(sql_cnt, param);
		if(cnt < 1 ) {
			oDb.executeUpdate(sql_make_new, param);
		}
		return oDb.executeUpdate(sql,param);
	}
	
	/**
	 * 답안지를 완료로 바꾼다 .
	 */
	public static int setFin(MdbDriver oDb, Map param) throws MdbException {
        String sql = "{call ETEST.etest_finish (:exmid, :usn)}";

		return oDb.executeUpdate(sql, param);
	}
	
	/**
	public static int setFin(MdbDriver oDb, Map param) throws MdbException {
		final String sql_chk = " SELECT COUNT (*) cnt"   +
		" FROM exm_rst_item ri, exm_bnk b"   +
		" WHERE ri.exmid = :exmid"   +
		" AND ri.usn = :usn"   +
		" AND ri.queid = b.queid"   +
		" AND b.quetype NOT IN ('s', 'm')"  ;
		
		// 선택 문제외에 다른형태가 없으면 자동으로 채점 완료로 바꾼다.
		int chk = oDb.selectOneInt(sql_chk, param);
		if(chk > 0 ) {
			param.put("rst_stat", "S");
		} else {
			param.put("rst_stat", "F");
		}
		
		final String sql_tot = " UPDATE exm_rst r"   +
		"    SET (rstscore, rst_fullscore, rst_status, rst_edt) = (SELECT SUM(usrscore), SUM(quescore), :rst_stat, sysdate"   +
		"                                       FROM exm_rst_item i"   +
		"                                      WHERE r.exmid = i.exmid"   +
		"                                        AND r.usn = i.usn)"   +
		"  WHERE exmid = :exmid"   +
		"    AND usn = :usn";
		
		// 합계저장  및 종료 처리 
		oDb.executeUpdate(sql_tot,param);
		
		return  1;
	}
	 */

	/**
	 * 한개인의 현 시험에 대한 응시정보를 돌려 줌 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */

	public static Map  getRstInfo(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT er.rst_sdt, (SYSDATE - er.rst_sdt) * 60 * 24 dur, em.exmtime,"   +
		"       ceil( em.exmtime - (SYSDATE - er.rst_sdt) * 60 * 24) remain,"   +
		"        (SELECT MAX (pageno)"   +
		"           FROM exm_rst_item"   +
		"          WHERE exmid = er.exmid"   +
		"            AND usn = er.usn) maxpage"   +
		"   FROM exm_rst er, exm_mst em"   +
		"  WHERE er.exmid = :exmid"   +
		"    AND er.usn = :usn"   +
		"    AND er.exmid = em.exmid"  ;
		return (Map) oDb.selectorOne(Map.class, sql,param);
	}
}
