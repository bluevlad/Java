/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (??)????? All rights reserved.
 */
package modules.wlc.learner;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class MainDB extends BaseDAO {

    //private Log logger = LogFactory.getLog(this.getClass());

    /**
     * 목록 가져오기
     * @param oDb
     * @param param
     * @param sql
     * @param isAll
     * @return
     * @throws MdbException
     */
    public static List getList(MdbDriver oDb, Map param) throws MdbException {

/*        final String sql = " select * from (  " +
        "         SELECT  a.lec_cd, a.lec_num, a.req_stat,   " +
        "                            a.lec_nm, a.sjt_cd, a.lec_type,  " +
        "                             a.lec_year, a.lec_stat, a.ltype, a.otype,  " +
        "                            a.lec_price, a.learn_time,  " +
        "                            a.lec_capacity, a.lec_min_capacity, a.finished_score, a.limit_study_time,  " +
        "                            a.cnt_type,  a.sample_url, a.sample_cnt_width, a.sample_cnt_height,  " +
        "                            a.test_type, a.exm_condition  " +
        "                 , e.usn, e.nm    " +
        "                 , b.req_sdat, b.req_edat, b.relearn_day_cnt, b.learn_day_cnt  " +
        "                 , c.sch_sdt, c.sch_edt  " +
        "                 , d.sch_sdt as re_sch_sdt, d.sch_edt as re_sch_edt  " +
        "         FROM   " +
        "             (  " +
        "                 -- 과정정보  " +
        "                 SELECT a.lec_cd, a.lec_num, a.req_stat,  " +
        "                            b.lec_nm, b.sjt_cd,  " +
        "                            b.lec_year, b.lec_stat, b.ltype, b.otype, b.lec_type,  " +
        "                            b.lec_price, b.learn_day_cnt, b.learn_time, b.relearn_day_cnt,  " +
        "                            b.lec_capacity, b.lec_min_capacity, b.finished_score, b.limit_study_time,  " +
        "                            b.cnt_type,  b.sample_url, b.sample_cnt_width, b.sample_cnt_height,  " +
        "                            b.test_type, b.exm_condition  " +
        "                 FROM wlc_lec_req a, bas_lec b, bas_sjt sj, bas_crs cr " +
        "                 WHERE a.lec_cd=b.lec_cd AND b.sjt_cd = sj.sjt_cd AND sj.crs_cd = cr.crs_cd  " +
        "                 AND a.usn=:usn AND req_stat in ('LP','LE') " +
        "             ) a,  " +
        "             (  " +
        "                 -- 일반상시과정  " +
        "                 SELECT a.lec_cd, req_sdat, req_edat, b.relearn_day_cnt, b.learn_day_cnt, req_stat  " +
        "                 FROM bas_lec b, wlc_lec_req a  " +
        "                 WHERE a.lec_cd=b.lec_cd AND lec_type='G'  " +
        "                 AND a.usn=:usn AND req_stat in ('LP','LE') " +
        "                 GROUP BY a.lec_cd, req_sdat, req_edat, b.relearn_day_cnt, b.learn_day_cnt, req_stat  " +
        "             ) b,  " +
        "             (  " +
        "                 -- 정규과정 강의기간  " +
        "                 SELECT a.lec_cd,sch_sdt, sch_edt , lec_type, req_stat  " +
        "                 FROM  wlc_lec_req a, bas_lec c, bas_lec_sch b   " +
        "                 WHERE c.lec_cd=b.lec_cd AND c.lec_cd=a.lec_cd  " +
        "                       AND lec_type='R' AND sch_type IN ('C')   " +
        "                     AND a.usn=:usn AND req_stat in ('LP','LE')  " +
        "                 GROUP BY a.lec_cd, sch_sdt, sch_edt, lec_type, req_stat  " +
        "             ) c,  " +
        "             (  " +
        "                 -- 정규과정 복습기간  " +
        "                 SELECT a.lec_cd, sch_sdt, sch_edt,lec_type, req_stat   " +
        "                 FROM  wlc_lec_req a, bas_lec c, bas_lec_sch b   " +
        "                 WHERE c.lec_cd=b.lec_cd AND c.lec_cd=a.lec_cd  " +
        "                       AND lec_type='R' AND sch_type IN ('F')   " +
        "                       AND a.usn=:usn AND req_stat in ('LP','LE')  " +
        "                 GROUP BY  a.lec_cd, sch_sdt, sch_edt, lec_type, req_stat  " +
        "             ) d,  " +
        "             (      " +
        "                 -- 강사정보  " +
        "                 SELECT a.lec_cd, c.usn, c.nm, req_stat  " +
        "                 FROM wlc_lec_req a, bas_lec_prf b, v_jmf_user c  " +
        "                 WHERE a.lec_cd=b.lec_cd(+) AND b.usn=c.usn  " +
        "                 AND a.usn=:usn AND req_stat in ('LP','LE')  " +
        "                 GROUP BY a.lec_cd, c.usn, c.nm, req_stat  " +
        "             ) e  " +
        "         WHERE a.lec_cd=b.lec_cd(+) AND a.lec_cd=c.lec_cd(+)  " +
        "               AND a.lec_cd=d.lec_cd(+) AND a.lec_cd=e.lec_cd(+)  " +
        "               AND (a.req_stat = 'LP' OR (a.req_stat = 'LE' and (d.sch_sdt <= TO_CHAR(SYSDATE,'yyyy-mm-dd') and d.sch_edt >= TO_CHAR(SYSDATE,'yyyy-mm-dd') ) ))  " +
        "         ORDER BY lec_nm, lec_num " +
        " ) x ";
*/
    	final String sql = "SELECT a.*, b.lec_nm, b.lec_year " +
    	" FROM wlc_lec_req a, bas_lec b " +
    	" WHERE a.lec_cd=b.lec_cd " +
    	" AND a.usn=:usn " +
    	" AND req_stat in ('LR','LC','LP','CR','LE') ";

        return oDb.selector(Map.class, sql , param);
        
    }


    /**
     * 수강신청 가능 목록 가져 오기 
     * @param oDb
     * @param param
     * @param sql
     * @param isAll
     * @return
     * @throws MdbException
     */
    public static List getListExt(MdbDriver oDb,  Map param) throws MdbException {

    	final String sql = "select a.*,   " +
    	" (select sch_sdt from bas_lec_sch where lec_cd = a.lec_cd and sch_type = 'a') as sch_s_a,  " +
    	" (select sch_edt from bas_lec_sch where lec_cd = a.lec_cd and sch_type = 'a') as sch_e_a,  " +
    	" (select sch_sdt from bas_lec_sch where lec_cd = a.lec_cd and sch_type = 'c') as sch_a_c,  " +
    	" (select sch_edt from bas_lec_sch where lec_cd = a.lec_cd and sch_type = 'c') as sch_e_c  " +
    	" from bas_lec a, bas_sjt b   " +
    	" where a.sjt_cd = b.sjt_cd " +
    	" and lec_stat = 'O' " +
    	" and a.sjt_cd not in (select a.sjt_cd    " +
    	"                    from bas_lec a, bas_sjt b, wlc_lec_req c   " +
    	"                    where a.sjt_cd = b.sjt_cd   " +
    	"                    and a.lec_cd = c.lec_cd   " +
    	"                    and c.usn = :usn " +
    	"                    and c.req_stat in('LR', 'LC', 'LP', 'CR', 'LE')) ";
    	
        return  oDb.selector(Map.class, sql , param);
    }

//    /**
//     * 레코드 카운트 가져오기
//     *
//     * @param oDb
//     * @param param
//     * @param sWhere
//     * @return
//     * @throws MdbException
//     */
//    private static long getRecordCountExt(MdbDriver oDb, Map param, String sWhere) throws MdbException {
//        final  String sql = "select count(*) from (select * from BAS_CLASS) x " + sWhere;
//        return oDb.selectOneInt(sql, param);
//    }
    
    /**
     * 하나의 레코드 읽어 오기
     *
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select  * "
            + " from BAS_LEC "
            + " where  lec_cd = :lec_cd ";
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 과정.과목 리스트를 가져온다.
     *
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static List getCrsSub(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "  select 'ROOT' as crs_cd, 'ROOT' as crs_nm, "   +
        "  crs_cd as sjt_cd, crs_nm as sjt_nm "   +
        "   from bas_crs"   +
        "  union all"   +
        "  select a.crs_cd, a.crs_nm, b.sjt_cd, b.sjt_nm "   +
        "   from bas_crs a, bas_sjt b "   +
        "   where a.crs_cd = b.crs_cd"  ;
        return oDb.selector(Map.class, sql, param);
    }

}