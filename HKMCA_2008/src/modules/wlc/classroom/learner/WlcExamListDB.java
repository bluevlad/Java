package modules.wlc.classroom.learner;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class WlcExamListDB extends BaseDAO {
	/**
	 * ��� ��������
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	public static List getList(MdbDriver oDb, Map param) throws MdbException {

		final String sql = "select * from wlc_exm_mst WHERE lec_cd = :lec_cd "   ;

		return oDb.selector(Map.class, sql , param);
	}
	
	/**
	 * ���迡�� �����ϴ� ��� ���� ���� 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getLangList(MdbDriver oDb,  Map param ) throws MdbException {
		
		final String sql = " SELECT d.lang, la.allnames"   +
		"   FROM (SELECT lang"   +
		"           FROM wlc_exm_set"   +
		"          WHERE setid = (SELECT setid"   +
		"                         FROM wlc_exm_mst"   +
		"                         WHERE exmid = :exmid)"   +
		"         ) d, v_maf_lang la"   +
		"  WHERE d.lang = la.code"  ;

		return oDb.selector(Map.class, sql , param);
	}
	
	/**
	 * ������ ���� ��� ���� ���� 
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getRList(MdbDriver oDb, Map param) throws MdbException {
		final String sql =" SELECT r.exmid, r.usn, m.exmtitle, m.exm_sdat, m.exm_edat, " +
				" r.rst_status, r.lang, r.rst_sdt, r.rst_edt"   +
		"   FROM wlc_exm_rst r, wlc_exm_mst m"   +
		"  WHERE r.exmid = m.exmid"   +
		"    AND r.usn = :usn"   +
		"    AND r.rst_status = 'R'"  ;
		return oDb.selector(Map.class, sql, param);
	}
	
	
	/**
	 * �ڽ��� ��������  �������� 
	 * 
	 * @param oDb
	 * @param param [usn, lecnumb, exmid ]
	 * @return
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT m.exmid, m.lec_cd, m.exmtitle, m.exmdesc, m.exm_sdat, m.exm_edat, s.exmcnt5,  " +
		"       s.exmcnt4, s.exmcnt3, s.exmcnt2, s.exmcnt1, m.exmtime, s.exmtype, m.exmtops, m.exmview,  " +
		"       m.exmopen, m.exmpage, m.reg_id, m.reg_dt, m.upt_dt, m.upt_id, m.setid,   " +
		"       case when m.exm_edat < sysdate then 'F' else r.rst_status end rst_status,  " +
		"       r.lang, r.rst_sdt, r.rst_edt,  " +
		"       ((sysdate - r.rst_sdt)*24*60 - m.exmtime) remain_time  " +
		" FROM wlc_exm_rst r, wlc_exm_mst m, wlc_exm_set s  " +
		" WHERE r.exmid = m.exmid  " +
		" AND r.usn = :usn  " +
		" AND r.exmid = :exmid  " +
		" AND m.setid = s.setid ";

		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

    /**
	 * �Ѹ��� �����ڸ� Insert �Ѵ�.
	 * 
	 * @param oDb
	 * @param param [ :exmid, :usn, :lecnumb, :insert_id ]
	 * @return
	 * @throws MdbException
	 */
	public static int insertRstOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "INSERT INTO wlc_exm_rst " +
		" (exmid, lec_cd, usn, lang, reg_id, reg_dt)"   +
		" VALUES (:exmid, :lec_cd, :usn, :lang, :usn, SYSDATE)";
		
		return oDb.executeUpdate(sql, param);
	}
		
	/**
	 * �ѻ���� �ѽ����� ���� ��� 
	 *USN	
	 *qcnt	: ���׼�
	 *qsum	: ����
	 *rstscore	: ���ε���
	 *rstscore100	: 100�� ȯ������
	 *rstrank	: ����
	 *rstrank100	: ���������
	 *rstcnt	: �������ڼ�
	 *rstavg	: ��ü���
	 *rstscoremax	: �ְ����
	 *rstscoremin	: ��������
	 *rstavg10 		: ���� 10% ��� 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getTestResultbyUser(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT * " +
		"  FROM (SELECT rd.*, AVG (DECODE (SIGN (rstrank100 - 0.1), -1, rd.rstscore100, NULL)) OVER () rstavg10, " +
		"               ROUND (cnt_pass / rstcnt, 2) * 100 passrate " +
		"          FROM (SELECT r.exmid, r.usn, q.qcnt, q.qsum, r.rstscore, rstscore100, " +
		"                       RANK () OVER (ORDER BY r.rstscore100 DESC) rstrank, " +
		"                       ROUND (PERCENT_RANK () OVER (ORDER BY rstscore100 DESC), 2) rstrank100, " +
		"                       SUM (1) OVER () rstcnt, ROUND (AVG (r.rstscore100) OVER (), 2) rstavg, " +
		"                       SUM (DECODE (r.passing_yn, 'Y', 1, 0)) OVER () cnt_pass, r.passing_yn, " +
		"                       AVG (DECODE (passing_yn, 'Y', r.rstscore100, NULL)) OVER () passavg, " +
		"                       MAX (r.rstscore100) OVER () rstscoremax, " +
		"                       MIN (r.rstscore100) OVER () rstscoremin " +
		"                   FROM wlc_exm_rst r, wlc_exm_mst m, (SELECT exmid, COUNT (*) qcnt, SUM (quescore) qsum " +
		"                                                         FROM wlc_exm_rst_item " +
		"                                                         WHERE exmid = :exmid " +
		"                                                         AND usn = :usn " +
		"                                                         GROUP BY exmid) q " +
		"                   WHERE r.exmid = :exmid " +
		"                   AND r.exmid = m.exmid " +
		"                   AND r.exmid = q.exmid " +
		"                   AND r.rst_status = 'F') rd) stat " +
		"  WHERE usn = :usn ";

		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
}