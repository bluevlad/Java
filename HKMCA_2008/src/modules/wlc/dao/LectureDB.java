package modules.wlc.dao;

import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

/**
 * 강의실 관련 공통 DB
 * @author bluevlad
 *
 */
public class LectureDB {
	/**
     * 하나의 강의실 정보  읽어 오기
     *
     * @param oDb
     * @param param [leccode]
     * @return
     * @throws MdbException
     */
    public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "SELECT bas_lec.*, " +
        " (select sch_sdt from bas_lec_sch where lec_cd = bas_lec.lec_cd and sch_type = 'a') as aschsdt,  " +
        " (select sch_edt from bas_lec_sch where lec_cd = bas_lec.lec_cd and sch_type = 'a') as aschedt " +
        " FROM bas_lec " +
        " WHERE lec_cd = :lec_cd ";

        return (Map) oDb.selectorOne(Map.class, sql, param);
    }
	
    /**
     * 개설강의 스켸쥴 정보를 가져온다.
     */
    public static List getLecSch(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " select * "   +
        " from bas_lec_sch "   +
        " where lec_cd = :lec_cd"  ;
        return oDb.selector(Map.class, sql, param);
    }

}
