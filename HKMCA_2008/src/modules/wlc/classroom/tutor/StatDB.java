package modules.wlc.classroom.tutor;

import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class StatDB {

	/**
	 * 하나의 시험정보 읽어 오기
	 * 
	 * @param oDb
	 * @param param [ exmid]
	 * @return
	 * @throws MdbException
	 */
	public static List getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT s.sc_range * 10 sc_range,  " +
		"       COUNT (rstscore10) cnt  " +
		" FROM (SELECT r.lec_cd, FLOOR (decode(r.tot_score,100,99.9,r.tot_score)  / 10)+1 rstscore10  " +
		"         FROM wlc_fnl_grd r " +
		"         WHERE r.lec_cd = :lec_cd) x,  " +
		"      (SELECT ROWNUM sc_range  " +
		"         FROM all_objects  " +
		"         WHERE ROWNUM <= 10) s  " +
		" WHERE s.sc_range = x.rstscore10(+)  " +
		" GROUP BY s.sc_range  " +
		" order by sc_range  ";

		return oDb.selector(Map.class, sql, param);
	}

	/**
	 * 한 시험의 평균 정보를 구한 다. 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getResultByLec(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT rd.*, AVG (DECODE (SIGN (rank100 - 0.1), -1, rd.tot_score, NULL)) OVER () rstavg10, round(cnt_pass/rstcnt,2)*100 passrate " +
		" FROM (select wr.lec_cd, wr.usn, wg.tot_score, " +
		"       ROUND (PERCENT_RANK () OVER (ORDER BY wg.tot_score DESC), 2) rank100, " +
		"       SUM (1) OVER () rstcnt, " +
		"       ROUND (AVG (wg.tot_score) OVER (), 2) rstavg, " +
		"       MAX (wg.tot_score) OVER () rstscoremax, " +
		"       MIN (wg.tot_score) OVER () rstscoremin, " +
		"       sum(decode(wr.is_grad,'G',1,0)) OVER () cnt_pass, " +
		"       count(wr.usn) over () cnt_usn, " +
		"       wr.is_grad, " +
		"       AVG (DECODE (wr.is_grad, 'G', wg.tot_score, NULL)) OVER () passavg " +
		" from wlc_lec_req wr, wlc_fnl_grd wg " +
		" where wr.lec_cd = wg.lec_cd " +
		" and wr.usn = wg.usn " +
		" and wr.req_stat in ('LP', 'LE') " +
		" and wr.lec_cd = :lec_cd) rd " +
		"WHERE ROWNUM < 2 ";

		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
}
