package modules.wlc.classroom.learner;

import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class BasClassInfoDB {
	/**
	 * 강의 정보 가져 오기 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select a.lec_cd, a.lec_nm,  " +
		"       LEC_OBJ, LEC_USN, LEC_SUMMARY, LEC_INF, LEC_BOOK, LEC_ETC " +
		"  FROM bas_lec a, bas_lec_inf b " +
		"  WHERE a.lec_cd = b.lec_cd(+) " +
		"  and a.lec_cd = :lec_cd ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	

}
