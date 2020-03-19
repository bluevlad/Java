package modules.wlc.classroom.support;

import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import modules.wlc.classroom.beans.LectureInfo;
import modules.wlc.exceptions.Lec_cdInvalidException;

public class LecUtil {
	/**
	 * 하나의 강의실정보를 DB에서 가져 온다. 
	 * @param lecture
	 * @return
	 * @throws LeccodeInvalidException
	 * @throws MdbException
	 */
	public static LectureInfo getLectureInfo(MdbDriver oDb, Map param) throws Lec_cdInvalidException, MdbException {
		final String sql = "select sjt_cd, a.lec_cd, lec_nm, lec_num, lec_type, finished_score, " +
		" sch_sdt lec_sdate, sch_edt lec_edate/*, " +
		" (select exmid from wlc_exm_mst where lec_cd = a.lec_cd) exmid*/ " +
		" from bas_lec a, bas_lec_sch b " +
		" where a.lec_cd = b.lec_cd " +
		" and sch_type = 'c'  " +
		" and a.lec_cd = :lec_cd ";
		
		LectureInfo bean = (LectureInfo) oDb.selectorOne(LectureInfo.class, sql, param);
		if (bean == null) {
			throw new Lec_cdInvalidException();
		}
		return (LectureInfo) oDb.selectorOne(LectureInfo.class, sql, param);
	}
}
