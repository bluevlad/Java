package modules.etest.learner;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class ExamListDB extends BaseDAO {
	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param,
	        String sWhere, boolean isAll) throws MdbException {
		List list = null;
		final String sql = "select * from " +
		" (SELECT exm_mst.*, (select rst_status from exm_rst where exmid =  exm_mst.exmid and usn = :usn) rst_status " +
		"   from exm_mst " +
		"   WHERE active_yn = 'Y') x ";
		
		String sOrder = navigator.getOrderSql();
		if (!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(ExamListDB.getRecordCount(oDb, param,
			                                                  sWhere.toString()));
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
	private static long getRecordCount(MdbDriver oDb, Map param, String sWhere)
	        throws MdbException {
		final String sql = "select count(*) from " +
				" (select * " +
				"	from exm_mst " +
				"	where active_yn = 'Y') x " + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	
	/**
	 * 시험에서 지원하는 언어 가져 오기 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getLangList(MdbDriver oDb,  Map param ) throws MdbException {
		
		final String sql = " SELECT d.lang, la.allnames"   +
		"   FROM (SELECT lang"   +
		"           FROM exm_set"   +
		"          WHERE setid = (SELECT setid"   +
		"                         FROM exm_mst"   +
		"                         WHERE exmid = :exmid)"   +
		"         ) d, v_maf_lang la"   +
		"  WHERE d.lang = la.code"  ;

		return oDb.selector(Map.class, sql , param);
	}
	
	/**
	 * 봐야할 시험 목록 가져 오기 
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getRList(MdbDriver oDb, Map param) throws MdbException {
		final String sql =" SELECT r.exmid, r.usn, r.lec_num,  m.exmtitle, m.exm_sdat_t1, m.exm_edat_t1, " +
				" r.rst_status, r.lang, r.rst_sdt, r.rst_edt"   +
		"   FROM exm_rst r, exm_mst m"   +
		"  WHERE r.exmid = m.exmid"   +
		"    AND r.usn = :usn"   +
		"    AND r.rst_status = 'R'"  ;
		return oDb.selector(Map.class, sql, param);
	}
	
	
	/**
	 * 자신의 시험정보  가져오기 
	 * 
	 * @param oDb
	 * @param param [usn, lecnumb, exmid ]
	 * @return
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT m.exmid, m.exmowner, m.exmtitle, m.exmdesc, m.exm_sdat, m.exm_edat, s.exmcnt5,  " +
		"       s.exmcnt4, s.exmcnt3, s.exmcnt2, s.exmcnt1, m.exmtime, s.exmtype, m.exmtops, m.exmview,  " +
		"       m.exmopen, m.exmpage, m.reg_id, m.reg_dt, m.upt_dt, m.upt_id, m.setid,   " +
		"       case when m.exm_edat < to_char(sysdate, 'YYYY-MM-DD') then 'F' else r.rst_status end rst_status,  " +
		"       r.lang, r.rst_sdt, r.rst_edt,  " +
		"       round((sysdate - r.rst_sdt)*24*60 - m.exmtime) remain_time  " +
		" FROM exm_rst r, exm_mst m, exm_set s  " +
		" WHERE r.exmid = m.exmid  " +
		" AND r.usn = :usn  " +
		" AND r.exmid = :exmid  " +
		" AND m.setid = s.setid ";

		return (Map) oDb.selectorOne(Map.class, sql, param);
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
		final String sql = "INSERT INTO exm_rst " +
		" (exmid, usn, reg_id, reg_dt)"   +
		" VALUES (:exmid, :usn, :usn, SYSDATE)";
		
		return oDb.executeUpdate(sql, param);
	}
		
}
