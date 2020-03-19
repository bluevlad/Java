/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.wlc.admin.course;

import java.util.List;
import java.util.Map;
import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import modules.wlc.classroom.beans.LectureInfo;

public class LectureMngDB extends BaseDAO {

	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
		
		List list = null;

		final String sql = " SELECT * " +
		"   FROM (SELECT c.*, sj.crs_cd, " +
		"                (SELECT COUNT (*) " +
		"                   FROM wlc_lec_req " +
		"                  WHERE lec_cd = c.lec_cd " +
		"                    AND req_stat = 'LR') cnt_lr, " +
		"                (SELECT COUNT (*) " +
		"                   FROM wlc_lec_req " +
		"                  WHERE lec_cd = c.lec_cd " +
		"                    AND req_stat = 'LP') cnt_lp, " +
		"                (SELECT COUNT (*) " +
		"                   FROM wlc_lec_req " +
		"                  WHERE lec_cd = c.lec_cd " +
		"                    AND req_stat = 'LE') cnt_le " +
		"           FROM bas_lec c, bas_sjt sj, bas_crs cr " +
		"          WHERE c.sjt_cd = sj.sjt_cd " +
		"            AND sj.crs_cd = cr.crs_cd) x ";	
		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(LectureMngDB.getRecordCount(oDb, param, sWhere.toString()));
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
	private static long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final  String sql = " select count(*) " +
		" from ( select c.*, sj.crs_cd "   +
		"   	   from bas_lec c, bas_sjt sj, bas_crs cr "   +
		"   	   where c.sjt_cd = sj.sjt_cd"   +
		"            AND sj.crs_cd = cr.crs_cd"  +
		"		) x " + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	
	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	public static List getList(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		
		final String sql = "   select *"   +
        "   from ( select ltype, (select code_name from jmf_code_det where group_cd = 'LTYPE' and code_no = a.ltype) as ltype_name,"   +
        "                 crt_lvl, (select code_name from jmf_code_det where group_cd = 'CRT_LVL' and code_no = crt_lvl) as cert_name,"   +
        "                 lec_cd, lec_nm, field, a.sjt_cd,"   +
        "                 lecstat, (select code_name from jmf_code_det where group_cd = 'LECSTAT' and code_no = a.lecstat) as lecstat_name,"   +
        "                 a.otype, (select code_name from jmf_code_det where group_cd = 'OTYPE' and code_no = a.otype) as otype_name"   +
        "          from bas_lec a, bas_sjt b, bas_crs c"   +
        "          where a.sjt_cd = b.sjt_cd"   +
        "          and b.crs_cd = c.crs_cd ) x"  ;
		
		return oDb.selector(Map.class, sql + sWhere, param);
	}
	
	/**
	 * 하나의 레코드 읽어 오기
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT c.*, (SELECT COUNT (*)"   +
		"                FROM wlc_lec_req"   +
		"               WHERE lec_cd = c.lec_cd"   +
		"                 AND req_stat = 'LR') cnt_lr, "   +
		" 				(SELECT COUNT (*)"   +
		"                                                 FROM wlc_lec_req"   +
		"                                                WHERE lec_cd = c.lec_cd"   +
		"                                                  AND req_stat = 'LP') cnt_lp,"   +
		"        (SELECT COUNT (*)"   +
		"           FROM wlc_lec_req"   +
		"          WHERE lec_cd = c.lec_cd"   +
		"            AND req_stat = 'LE') cnt_le"   +
		"   FROM bas_lec c"   +
		"  WHERE lec_cd = :lec_cd";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	public static LectureInfo getLectureInfo(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select  * " 
				+ " from BAS_LEC " 
		        + " where lec_cd = :lec_cd ";
		return (LectureInfo) oDb.selectorOne(LectureInfo.class, sql, param);
	}

	/**
	 * 하나의 레코드를  insert or update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
    public static int mergeOne(MdbDriver oDb,  Map param) throws MdbException  {
    	final String sql = "MERGE INTO BAS_LEC new " +
    	"      USING (SELECT :lec_cd lec_cd, :sjt_cd sjt_cd, :lec_year lec_year, :lec_num lec_num, :lec_nm lec_nm, " +
    	"                    :lec_stat lec_stat, :ltype ltype, :otype otype, :lec_type lec_type, :learn_day_cnt learn_day_cnt, " +
    	"                    :learn_time learn_time, :relearn_day_cnt relearn_day_cnt, :lec_target lec_target," +
    	"                    :lec_capacity lec_capacity, :finished_score finished_score, :limit_study_time limit_study_time, " +
    	"					 :is_approval is_approval, :adminid adminid, :usn usn, :lec_price lec_price, :place place" +
    	"               FROM DUAL) src " +
    	"         ON (src.lec_cd = new.lec_cd) " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE SET " +
		" 			lec_nm = src.lec_nm," +
		" 			lec_stat = src.lec_stat," +
		" 			ltype = src.ltype," +
		" 			otype = src.otype," +
		" 			lec_type = src.lec_type," +
		" 			lec_target = src.lec_target," +
		" 			learn_day_cnt = src.learn_day_cnt," +
		" 			learn_time = src.learn_time," +
		" 			relearn_day_cnt = src.relearn_day_cnt," +
		" 			lec_capacity = src.lec_capacity," +
		" 			finished_score = src.finished_score," +
		" 			limit_study_time = src.limit_study_time," +
		" 			is_approval = src.is_approval," +
		" 			adminid = src.adminid," +
		" 			upt_dt = sysdate," +
		" 			upt_id = src.usn," +
		" 			lec_price = src.lec_price, " +
		" 			place = src.place " +
    	"    WHEN NOT MATCHED THEN " +
    	"       INSERT (" +
    	"               lec_cd, sjt_cd, lec_year, lec_num, lec_nm, lec_stat, ltype, " +
		"	  			otype, lec_type, learn_day_cnt, learn_time, relearn_day_cnt, lec_target," +
		"				lec_capacity, finished_score, limit_study_time, is_approval, " +
		"				adminid, reg_dt, upt_dt, upt_id, lec_price, place" +
    	"       ) VALUES (" +
    	"                  	src.lec_cd, src.sjt_cd, src.lec_year, src.lec_num, src.lec_nm, src.lec_stat, src.ltype, " +
		"	  				src.otype, src.lec_type, src.learn_day_cnt, src.learn_time, src.relearn_day_cnt, src.lec_target," +
		"	  				src.lec_capacity, src.finished_score, src.limit_study_time, src.is_approval, " +
		"	  				src.adminid, sysdate, sysdate, src.usn, src.lec_price, src.place" +
    	"      ) ";
    	
    	return oDb.executeUpdate(sql, param);
    }
	
	/**
	 * 하나의 레코드를 삭제 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	 
	public static int deleteOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql_1 = "delete from BAS_LEC_SCH where  lec_cd = :lec_cd ";
		final String sql_2 = "delete from wlc_rat_mst where  lec_cd = :lec_cd ";
		final String sql_3 = "delete from wlc_lec_chp where  lec_cd = :lec_cd ";
		final String sql = "delete from BAS_LEC where lec_cd = :lec_cd ";
		
		int cnt = 0;
		cnt +=  oDb.executeUpdate(sql_1, param);
		cnt +=  oDb.executeUpdate(sql_2, param);
		cnt +=  oDb.executeUpdate(sql_3, param);
		cnt +=  oDb.executeUpdate(sql, param);
		
		return cnt;
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
		final String sql = " SELECT sjt_cd, sjt_nm " +
		" FROM bas_sjt"   +
		" order by sjt_nm "  ;
		return oDb.selector(Map.class, sql, param);
	}

	/**
	 * 개설강좌 정보를 복사한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int copyOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " call SP_LEC_COPY (:lec_cd, :lec_year, :lec_num, :lec_nm, :usn)";
		
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 내가 수강 또는 수강 가능한 강좌 리스트를 가져온다.
	 * 
	 * @param oDb
	 * @param param (usn)
	 * @return
	 * @throws MdbException
	 */
	public static List getMyClass(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " select * " +
				" from bas_lec "  ;
		return oDb.selector(Map.class, sql, param);
	}

    /**
     * 강의일정을 등록한다.
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int insertClassSchedule(MdbDriver oDb, Map param) throws MdbException {
        final String sql_class_schedule_a = " INSERT INTO BAS_LEC_SCH"   +
        "    ( lec_cd, sch_type, sch_sdt, sch_edt, upt_dt, upt_id)"   +
        "  VALUES"   +
        "    ( :lec_cd, 'a', :sch_sdt_a, :sch_edt_a, sysdate,  :usn)"  ;
        final String sql_class_schedule_c = " INSERT INTO BAS_LEC_SCH"   +
        "    ( lec_cd, sch_type, sch_sdt, sch_edt, upt_dt, upt_id)"   +
        "  VALUES"   +
        "    ( :lec_cd, 'c', :sch_sdt_c, :sch_edt_c, sysdate,  :usn)"  ;
        final String sql_class_schedule_f = " INSERT INTO BAS_LEC_SCH"   +
        "    ( lec_cd, sch_type, sch_sdt, sch_edt, upt_dt, upt_id)"   +
        "  VALUES"   +
        "    ( :lec_cd, 'f', :sch_sdt_f, :sch_edt_f, sysdate,  :usn)"  ;

        int cnt = 0;
        cnt = oDb.executeUpdate(sql_class_schedule_a,param);
        cnt += oDb.executeUpdate(sql_class_schedule_c,param);
        cnt += oDb.executeUpdate(sql_class_schedule_f,param);
        
        return cnt;
    }
    
    /**
     * 하나의 레코드를 Insert 한다.
     * 강좌 개설 시 진도 기준에 대한 기본 값을 설정한다.
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int updatePrgBasis(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "UPDATE bas_lec "   +
        " SET prg_type = :prg_type, lec_datm = :lec_datm, lec_weck = :lec_weck, lec_wetm = :lec_wetm " +
        " WHERE lec_cd = :lec_cd ";
        
        return oDb.executeUpdate(sql, param);
    }

    /**
     * 하나의 레코드를 Insert 한다.
     * 강좌 개설 시 평가 기준에 대한 기본 값을 설정한다.
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int insertRatBasis(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " INSERT INTO wlc_rat_mst "   +
        "    (lec_cd, ratjindo, jindo_time, jindo_page, ratattend, ratexam, ratreport  " +
        "     , ratproject, ratforum, ratetc, ratoffline, ratadd, upt_id, upt_dt) " +
        "  VALUES"   +
        "    (:lec_cd, 0, 0, 0, 0, 100, 0, " +
        "     0, 0, 0, 0, 0, :usn, sysdate )"  ;
        
        return oDb.executeUpdate(sql, param);
    }

    /**
     *  레코드리스트를 select 한다.
     * 강의일정 정보 조회
     * @param oDb
     * @param param
     * @return List
     * @throws MdbException
     */
    public static List getSchedule(MdbDriver oDb ,Map param) throws MdbException {

        final String sql = "select * from BAS_LEC_SCH where lec_cd = :lec_cd " ;
        return  oDb.selector(Map.class, sql,param);
    }       

    public static List getLecTarget(MdbDriver oDb, Map param) throws MdbException {
        
        final String sql = "SELECT code_no, code_name, seq,  " +
        "       (select code_no from bas_lec where lec_cd = :lec_cd and instr(lec_target, code_no) <> 0) chk  " +
        "  FROM maf_code_det  " +
        "  WHERE group_cd = 'LEC.TARGET' " +
        "  order by seq ";
        
        return oDb.selector(Map.class, sql, param);
    }

    /**
     * 하나의 레코드를 Update 한다.
     * 강의일정을 수정한다.
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int updateClassSchedule(MdbDriver oDb, Map param) throws MdbException {
        final String sql_class_schedule_delete = "DELETE FROM BAS_LEC_SCH where lec_cd = :lec_cd ";

        final String sql_class_schedule_a = " INSERT INTO BAS_LEC_SCH"   +
        "    ( lec_cd, sch_type, sch_sdt, sch_edt, upt_dt, upt_id)"   +
        "  VALUES"   +
        "    ( :lec_cd, 'a', :sch_sdt_a, :sch_edt_a, sysdate, :usn)"  ;

        //수강취소기간 주석 처리
        final String sql_class_schedule_b = " INSERT INTO BAS_LEC_SCH"   +
        "    ( lec_cd, sch_type, sch_sdt, sch_edt, upt_dt, upt_id)"   +
        "  VALUES"   +
        "    ( :lec_cd, 'b', :sch_sdt_b, :sch_edt_b, sysdate, :usn)"  ;

        final String sql_class_schedule_c = " INSERT INTO BAS_LEC_SCH"   +
        "    ( lec_cd, sch_type, sch_sdt, sch_edt, upt_dt, upt_id)"   +
        "  VALUES"   +
        "    ( :lec_cd, 'c', :sch_sdt_c, :sch_edt_c, sysdate, :usn)"  ;

        //성적공고기간 주석 처리
        final String sql_class_schedule_d = " INSERT INTO BAS_LEC_SCH"   +
        "    ( lec_cd, sch_type, sch_sdt, sch_edt, upt_dt, upt_id)"   +
        "  VALUES"   +
        "    ( :lec_cd, 'd', :sch_sdt_d, :sch_edt_d, sysdate, :usn)"  ;

        //성적이의신청기간 주석 처리
        final String sql_class_schedule_e = " INSERT INTO BAS_LEC_SCH"   +
        "    ( lec_cd, sch_type, sch_sdt, sch_edt, upt_dt, upt_id)"   +
        "  VALUES"   +
        "    ( :lec_cd, 'e', :sch_sdt_e, :sch_edt_e, sysdate, :usn)"  ;

        final String sql_class_schedule_f = " INSERT INTO BAS_LEC_SCH"   +
        "    ( lec_cd, sch_type, sch_sdt, sch_edt, upt_dt, upt_id)"   +
        "  VALUES"   +
        "    ( :lec_cd, 'f', :sch_sdt_f, :sch_edt_f, sysdate, :usn)"  ;

        int cnt = 0;
        cnt = oDb.executeUpdate(sql_class_schedule_delete,param);
        cnt += oDb.executeUpdate(sql_class_schedule_a,param);
        cnt += oDb.executeUpdate(sql_class_schedule_b,param);
        cnt += oDb.executeUpdate(sql_class_schedule_c,param);
        cnt += oDb.executeUpdate(sql_class_schedule_d,param);
        cnt += oDb.executeUpdate(sql_class_schedule_e,param);
        cnt += oDb.executeUpdate(sql_class_schedule_f,param);
        
        return cnt;
    }
    
    /**
     * 하나의 레코드 읽어 오기(강의차수, 과목코드)
     *
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static Map getOneLecnumb(MdbDriver oDb, Map param) throws MdbException {
        String sql = "SELECT NVL(to_char(lec_num),'') lec_num, sjt_cd FROM bas_lec WHERE lec_cd = :lec_cd ";
        return (Map) oDb.selectorOne(Map.class,sql, param);
    }   

    /**
     * 수강신청 등록 입력
     *
     * @param oDb
     * @param param
     * @param userids[]
     * @return int
     * @exception DBHandlerException, DBConnectionFailedException
     */
    public static int enroll(MdbDriver oDb, Map param,  String[] userids) throws MdbException  {

        int  result = 0;
        final String sql_r = "SELECT NVL (sch_sdt, TO_CHAR (SYSDATE, 'YYYYMMDD')) v_req_sdat,"   +
	        " NVL (sch_edt, TO_CHAR (SYSDATE + 60, 'YYYYMMDD')) v_req_edat"   +
	        " FROM bas_lec_sch"   +
	        " WHERE lec_cd = :lec_cd"   +
	        " AND LOWER (sch_type) = LOWER ('C')"  ;
        
        final String sql_i = "SELECT TO_CHAR (SYSDATE, 'YYYYMMDD') v_req_sdat,"   +
	        " TO_CHAR (SYSDATE + NVL (learn_day_cnt, 0), 'YYYYMMDD') v_req_edat"   +
	        " FROM bas_lec"   +
	        " WHERE lec_cd = :lec_cd"  ;
        
        LectureInfo lecture = getLectureInfo(oDb, param);
        Map p2 = null;
        if("R".equals(lecture.getLec_type())) {
        	p2 = (Map) oDb.selectorOne(Map.class, sql_r, param);
        } else {
        	p2 = (Map) oDb.selectorOne(Map.class, sql_i, param);
        }
        

        
        final String sql_ins = "INSERT INTO wlc_lec_req"   +
	        " (usn, order_id, lec_cd, lec_num, req_dt, req_stat, req_kind, company_id, req_sdat,"   +
	        "  req_edat, upt_dt, upt_id, is_pay, is_grad)"   +
	        "  SELECT usn, seq_order_id.NEXTVAL, :lec_cd, :lec_num, SYSDATE, :req_stat, 'S', org_cd,"   +
	        "         :v_req_sdat, :v_req_edat, SYSDATE, :usn, :is_pay, 'N'"   +
	        "   FROM v_jmf_user"   +
	        "   WHERE usn = :usn"  ;
        
        final String sql_lecnumb = " SELECT NVL (MAX (lecnumb), 0) + 1 lec_num"   +
        " FROM wlc_lec_req"   +
        " WHERE lec_cd = :lec_cd"   +
        " AND userid = :userid"  ;
        
        param.put("v_req_sdat", p2.get("v_req_sdat"));
        param.put("v_req_edat", p2.get("v_req_edat"));
        for(int j=0 ; j< userids.length; j++) {
        	param.put("userid",userids[j]);
        	param.put("lec_num", oDb.selectOne(sql_lecnumb, param));
          
          result += oDb.executeUpdate(sql_ins, param);
          
        }

        return result;
    }
    
    /**
     * 해당 강좌의 lecnumb를 구해 온다.
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
	public static String getMaxLecNum(MdbDriver oDb, Map param) throws MdbException {

		final String sql = "  SELECT NVL (MAX (lec_num), 0) + 1 nmax"   +
		" FROM bas_lec"   +
		" WHERE lec_cd LIKE :lec_cd"  ;
		return oDb.selectOne(sql, param);
	}
	
}		