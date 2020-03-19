/*
 * Created on 2005. 5. 17.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.logger.Logging;
import maf.logger.Trace;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xadmin.cmst.beans.LecMstBean;
import xadmin.cmst.beans.lec_prfBean;

/**
 * @author goindole
 * 
 * 개설강좌 관리
 */
public class Lec_ManagerDB extends CommonDB {
    private static Lec_ManagerDB instance;
	private  Log logger = LogFactory.getLog(this.getClass());
	
    private Lec_ManagerDB() {
    }

    public static synchronized Lec_ManagerDB getInstance() {
        if (instance == null)
            instance = new Lec_ManagerDB();
        return instance;
    }

    public List getLecList(MdbDriver oDb, int page, int page_size) throws MdbException {
    	return getLecList(oDb, null, page, page_size);
    }
    
    public List getLecList(MdbDriver oDb, Map param, int page, int page_size) 
        throws MdbException {

//        List at = new ArrayList();
        List list = null;
	    String sql = "  SELECT A.*, D.SJTNAME, F.CRSNAME, (select count(*) from lec_req lq where lq.leccode = a.leccode) leccnt"   +
	        "  FROM LEC_MST a , SJT_MST D, CRS_MST F 	"   +
			"  where a.SJTCODE = d.SJTCODE"   +
			"  and d.CRSCODE = f.CRSCODE";
        
            if(!"".equals(param.get("v_crscode")) && (param.get("v_crscode") != null)){
              sql = sql + " and d.crscode = :v_crscode";
            }   
            if(!"".equals(param.get("v_key")) && (param.get("v_key") != null)){
                if("code".equals(param.get("v_key"))){
                	sql = sql + " and instr(upper(a.leccode), upper(:v_srch)) >0";
                }
                else if("name".equals(param.get("v_key"))){
                    sql = sql + " and instr(upper(a.lecname), upper(:v_srch)) >0";
                }
            }   
			sql = sql + "  order by a.START_DT desc"  ;
	    
        oDb.setPage(page, page_size);
        list = oDb.selector(Map.class, sql, param);

        return list;

    }

    public int getLecCount(MdbDriver oDb) throws MdbException {
    	return getLecCount(oDb, null);
    }
    
    public int getLecCount(MdbDriver oDb, Map param) throws MdbException {
//        List at = new ArrayList();

        String sql = " SELECT count(*) " + "         FROM LEC_MST a , SJT_MST D, CRS_MST F" + " where a.SJTCODE = d.SJTCODE"
                + " 	  and d.CRSCODE = f.CRSCODE";
                if(!"".equals(param.get("v_crscode")) && (param.get("v_crscode") != null)){
                    sql = sql + " and d.crscode = :v_crscode";
                }   
                if(!"".equals(param.get("v_key")) && (param.get("v_key") != null)){
                    if("code".equals(param.get("v_key"))){
                        sql = sql + " and instr(upper(a.leccode), upper(:v_srch)) >0";
                    }
                    else if("name".equals(param.get("v_key"))){
                        sql = sql + " and instr(upper(a.lecname), upper(:v_srch)) >0";
                    }
                }   
        return oDb.selectOneInt(sql, param);
    }

    /**
     * FunctionName : insert(CrsMstBean bean) Description : 개설과목 정보 등록
     * 
     * @param :
     * @return : String 메시지
     * @exception :
     *                DBHandlerException, DBConnectionFailedException, ClassNotFoundException, IllegalSqlException,
     *                DataNotFoundException
     */
    public int insert(MdbDriver oDb, LecMstBean bean) throws MdbException {
        PreparedStatement pstmt = null;
//        StringBuffer qry = new StringBuffer();

        int ret = 0;

        try {
        	String sql = "INSERT INTO LEC_MST (" 
        		  + "leccode, sjtcode, lecname, lecyear, lecterm, " 
        		  + " bunban, lecquot, admcode, lecobjt, lectttr, " 
        		  + " lecwtfg, lecstat, lectime, lecgigan, start_dt, " 
        		  + " lecbound, leclink, lec_notice, lecsample, isfree, " 
        		  + " update_dt, update_id, rstart_dt, rfinish_dt, lectype, " 
        		  + " finish_dt, lecenam, cnt_id, attach_file, attach_filename," 
        		  + " exam_rate_j, exam_rate_n " 
        		  + " ) "
        		  + " VALUES ("
        		  + " :leccode, :sjtcode, :lecname, :lecyear, :lecterm, " 
        		  + " :bunban, :lecquot, :admcode, :lecobjt, :lectttr, " 
        		  + " :lecwtfg, :lecstat, :lectime, :lecgigan, :start_dt, " 
        		  + " :lecbound, :leclink, :lec_notice, :lecsample, :isfree, " 
        		  + " :update_dt, :update_id, :rstart_dt, :rfinish_dt, :lectype, " 
        		  + " :finish_dt, :lecenam, :cnt_id, :attach_file, :attach_filename,"
        		  + " :exam_rate_j, :exam_rate_n "
        		  + ") ";


            pstmt = oDb.prepareStatement(sql, bean);

            ret = pstmt.executeUpdate();

            pstmt.close();
            oDb.commit();

        } catch (Exception e) {
            oDb.rollback();
            logger.error(e.getMessage());
            throw new MdbException(e.getMessage());
        } finally {
            release(pstmt);
        }
        return ret;
    }

    /**
     * 등록되어 있는 강의 정보을 리턴
     * 
     * @param leccode :
     *            강의 코드
     * @return LecMstBean
     */
    public LecMstBean getLecMstBean(MdbDriver oDb, String leccode) throws MdbException {
        String sql = " SELECT a.*, d.sjtname, f.crsname, d.crscode, " 
        	+ " (select count(*) from lec_req lr where a.leccode = lr.leccode) rcnt "
        	+ "   FROM lec_mst a, sjt_mst d, crs_mst f"
            + "  WHERE a.sjtcode = d.sjtcode" 
			+ "    AND f.crscode = d.crscode" 
			+ "    AND a.leccode = :leccode";
        List at = new ArrayList();
        at.add(leccode);
        return (LecMstBean) oDb.selectorOne(LecMstBean.class, sql, at);
    }

    /**
     * 강의 차시정보 가져오기
     * 
     * @param oDb
     * @param leccode
     * @return
     * @throws MdbException
     */
    public List getChpList(MdbDriver oDb, String leccode) throws MdbException {
        String sql =
        	" SELECT   lc.*,"   +
			"          (SELECT comments"   +
			"             FROM bank_set"   +
			"            WHERE set_no = ("   +
			"                     SELECT exam_set_no set_no"   +
			"                       FROM exm_qmst eq, exm_qmst_set eqs"   +
			"                      WHERE eq.leccode = eqs.leccode"   +
			"                        AND eq.exm_seq = eqs.exm_seq"   +
			"                        AND eq.leccode = lc.leccode"   +
			" 					   AND eq.exm_seq = lc.exam_seq"   +
			" 					   )"   +
			" 			) AS examset_name"   +
			"     FROM lec_chp lc"   +
			"    WHERE leccode = :leccode"   +
			" ORDER BY chpcode"  ;
        
        List at = new ArrayList();
        at.add(leccode);
        return oDb.selector(Map.class, sql, at);
    }

    /**
     * 강의 차시 정보 수정 
     * @param oDb
     * @param leccode
     * @param param
     * @throws MdbException
     */
    public void updateChapterInfo(MdbDriver oDb, String leccode, Map param) throws MdbException {
    	String sql = "  UPDATE lec_chp lc "   +
    	"  		SET (chpweek, start_dt, finish_dt)  = "   +
		" 			(select :chpweek, "   +
		" 					decode(:chpweek," +
		"								0,lm.start_dt," +
		"								1,lm.start_dt," +
		"								ecampus.firstdayofweekExt (lm.start_dt, (:chpweek-1) * 1, lm.start_dt, lm.finish_dt)) st,"   +
		" 					decode(:chpweek," +
		"								0,lm.finish_dt," +
		"								ecampus.LastDayofWeekExt (lm.start_dt, (:chpweek-1) * 1, lm.start_dt, lm.finish_dt)) fi"   +
		" 				from lec_mst lm"   +
		" 				where lm.leccode = lc.leccode)"   +
		"  WHERE leccode = :leccode"   +
		"  		AND chpcode = :chpcode"  ;
    	oDb.executeUpdate(sql, param);    	
    }
    
    /**
     * FunctionName : update(CrsMstBean bean) Description : 과정 정보 수정
     * 
     * @param :
     * @return : String 메시지
     * @exception :
     *                DBHandlerException, DBConnectionFailedException, ClassNotFoundException, IllegalSqlException,
     *                DataNotFoundException
     */
    public synchronized int update(MdbDriver oDb, Map bean) throws MdbException {
        int ret = 0;
        try {
        	String sql = "UPDATE  LEC_MST "
        		  + " SET " 
        		  + " lecobjt = :lecobjt, "
        		  + " lecstat = :lecstat,  " 
        		  + " update_id = :update_id  " 
        		  + " WHERE  leccode = :leccode ";
            ret = oDb.executeUpdate(sql, bean);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new MdbException(e.getMessage());
        }
        return ret;
    }

    public synchronized int updateFile(MdbDriver oDb, Map bean) throws MdbException {
        int ret = 0;
        try {
            String sql = "UPDATE  LEC_MST " 
            	+ " SET " 
            	+ " attach_file = :attach_file,  " 
            	+ " attach_filename = :attach_filename  "
            	+" WHERE  leccode = :leccode ";
            
            ret = oDb.executeUpdate(sql, bean);
        } catch (Exception e) {
            logger.error(e.getMessage());
            oDb.rollback();
            throw new MdbException(e.getMessage());
        }
        return ret;
    }

    /**
     * 개설 강좌 삭제
     * 
     * @param oDb
     * @param bean
     * @return
     * @throws MdbException
     */
    public synchronized int delete(MdbDriver oDb, String leccode) throws MdbException {
        int ret = 0;
    	String sql = "{call Ecampus.delLec_Mst(:leccode)}";
    	CallableStatement stmt = null;
        try {

            stmt = oDb.prepareCall(sql);
            stmt.setString(1, leccode);
            stmt.execute();
            ret = 1;
        } catch (Exception e) {
            logger.error(Trace.getStackTrace(e));
        } finally {
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            ;
        }
        return ret;
    }
    
    public synchronized int createBoard(MdbDriver oDb, String leccode) {
    	
    	String sql = " INSERT INTO mbbs_meta"   +
			"                   (bid, subject, grp, nrows, fl_write, fl_html, fl_category,"   +
			"                    fl_waste, fl_auth, fl_reply, fl_comment, fl_modify_date,"   +
			"                    number_attach, max_attach_size, fl_board_type, is_use,"   +
			"                    CATEGORY, admin_usn, max_attach_limit, header, leccode)"   +
			"            VALUES (:boardid, :subject, 'course', '15', :auth, 'T', 'T',"   +
			"                    'F', 'T', 'T', :vcomment, 'S',"   +
			"                    5, 100, 'normal', 'T',"   +
			"                    '', 1, 100, '', :leccode)"  ;
    	Map param = new HashMap();
    	int cnt = 0;
    	oDb.setDebug(true);

    	return cnt;
    }
    
    

    /**
     * 과목에 해당하는 시험문제 Set
     * 
     * @author 김윤철
     * @version 1.0
     * @modifydate 2005. 7. 1.
     *
     * @param oDb
     * @param page
     * @param page_size
     * @param sjtcode
     * @return
     * @throws MdbException
     */
    public List getExamSetList(MdbDriver oDb, int page, int page_size, String sjtcode) throws MdbException {

	    String sql =" SELECT *  FROM bank_set WHERE sjtcode = :sjtcode"  ;
        
	    oDb.setPage(page, page_size);
        
        Map param = new HashMap();
        param.put("sjtcode", sjtcode);
        
        return  oDb.selector(Map.class, sql, param);
    }
    
    
    /**
     * 과목에 해당하는 시험문제 Set Count
     * 
     * @author 김윤철
     * @version 1.0
     * @modifydate 2005. 7. 1.
     *
     * @param oDb
     * @param page
     * @param page_size
     * @param sjtcode
     * @return
     * @throws MdbException
     */
    public int getExamSetCount(MdbDriver oDb, String sjtcode) throws MdbException {

	    String sql =" SELECT count(sjtcode)  FROM bank_set WHERE sjtcode = :sjtcode"  ;
        
	    Map param = new HashMap();
        param.put("sjtcode", sjtcode);
        
        return  oDb.selectOneInt(sql, param);
    }
     
    
    
    
    
    /**
     * 개설과목별 차시구성에 지정된 시험Set 정보 저장
     * 
     * @author 김윤철
     * @version 1.0
     * @modifydate 2005. 7. 1.
     * @param oDb
     * @param leccode
     * @param chpcode
     * @param examset_no
     * @param examSeq 
     *
     * @return
     * @throws MdbException
     */
    public synchronized int updateLecChpExamSet(MdbDriver oDb, String leccode, String chpcode, String examset_no, String examSeq) 
    	throws MdbException {
        
    	int ret = 0;
    	

    	oDb.setAutoCommit(false);
    	
        try {


        	String sql = "{call MI_CONTENTS.updateLecChpExamSet(?,?,?,?,?)}";
        	
        	
        	CallableStatement cstmt = oDb.prepareCall(sql);
        	cstmt.setString(1, leccode);
        	cstmt.setString(2, chpcode);
        	cstmt.setString(3, examset_no);
        	cstmt.setString(4, examSeq);
        	cstmt.registerOutParameter(5, Types.VARCHAR);
        	
        	ret = cstmt.executeUpdate();
        	logger.debug("ret: " + ret);
        	String returnVal = cstmt.getString(5);
        	if ("SUCCESS".equals(returnVal)) {
        		ret = 1;
        	} else {
        		ret = 0;
        	}
        	
        	oDb.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            oDb.rollback();
            throw new MdbException(e.getMessage());
        } finally {
        	oDb.setAutoCommit(true);
        }
        return ret;
    }

    /**
     * 교수자 가져오기
     * 
     * @param oDb
     * @return
     * @throws MdbException
     */
    public List getPrfList(MdbDriver oDb) throws MdbException {
        String sql =
            " select usn, nm, utype "   +
            " from mst_user "   +
            " where utype in ('P', 'A') "   +
            " ORDER BY nm asc"  ;
        
        List at = new ArrayList();
        return oDb.selector(Map.class, sql, at);
    }

    /**
     * 강의 담당교수 가져오기
     * 
     * @param oDb
     * @param leccode
     * @return
     * @throws MdbException
     */
    public List getLecPrfList(MdbDriver oDb, String leccode) throws MdbException {
        String sql =
            " select a.usn, b.nm, "   +
            " a.flag "   +
            " from lec_prf a, mst_user b"   +
            " where a.leccode = :leccode " +
            " and a.usn = b.usn "   +
            " ORDER BY nm asc"  ;
        
        List at = new ArrayList();
        at.add(leccode);
        return oDb.selector(Map.class, sql, at);
    }

    /**
     * 담당 교수를 추가한다.
     * @param oDb
     * @param bean
     * @return
     * @throws Exception
     */
    public int insLecPrf (MdbDriver oDb, lec_prfBean bean)
            throws Exception {
     
            int retValue = 0;

            PreparedStatement pstmt = null;

            try {
                    String sql = "INSERT INTO LEC_PRF ( " +
                                    " leccode, usn, " +
                                    " flag, " +
                                    " update_dt, update_id " +
                                    " ) " +
                                    " VALUES ( " +
                                    " :leccode, :usn, " +
                                    " :flag, " +
                                    " SYSDATE, :update_id " +
                                    " ) ";

                    pstmt = oDb.prepareStatement(sql, bean);

                    retValue = pstmt.executeUpdate();

                    pstmt.close();
                    oDb.commit();

            } catch (Exception e) {
                oDb.rollback();
            	Logging.log(this.getClass(), e.getMessage());
            	throw new MdbException(e.getMessage());
            } finally {
                release(pstmt);
            }
            
            return retValue;
        }
    /**
     * 담당 교수를 삭제한다.
     * @param oDb
     * @param bean
     * @return
     * @throws Exception
     */
    public int delLecPrf (MdbDriver oDb, lec_prfBean bean)
            throws Exception {
     
            int retValue = 0;

            PreparedStatement pstmt = null;

            try {
                    String sql = "delete from LEC_PRF " +
                                    " where leccode = :leccode and usn =  :usn ";

                    pstmt = oDb.prepareStatement(sql, bean);

                    retValue = pstmt.executeUpdate();

                    pstmt.close();
                    oDb.commit();

            } catch (Exception e) {
                oDb.rollback();
            	Logging.log(this.getClass(), e.getMessage());
                throw new MdbException(e.getMessage());
            } finally {
                release(pstmt);
            }
            
            return retValue;
        }
}
