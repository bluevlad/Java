package modules.etest.stat;

import java.util.List;
import java.util.Map;

import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class EtestStatDB {
	/**
	 * 한사용자 한시험의 성적 결과 
	 *USN	
	 *qcnt	: 문항수
	 *qsum	: 배점
	 *rstscore	: 본인득점
	 *rstscore100	: 100점 환산점수
	 *rstrank	: 석차
	 *rstrank100	: 백분위석차
	 *rstcnt	: 총응시자수
	 *rstavg	: 전체평균
	 *rstscoremax	: 최고득점
	 *rstscoremin	: 최저득점
	 *rstavg10 		: 상위 10% 평균 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
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
		"                   FROM exm_rst r, exm_mst m, (SELECT   exmid, COUNT (*) qcnt, SUM (quescore) qsum"   +
		"                                                   FROM exm_rst_item"   +
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
		"            FROM exm_rst r, exm_mst m"   +
		"           WHERE r.exmid = :exmid"   +
		"             AND r.exmid = m.exmid"   +
		"             AND r.rst_status = 'F') rd			"   +
		"   WHERE ROWNUM < 2"  ;
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
}
