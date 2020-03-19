/*
 * Created on 2006. 9. 27.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.tutor;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import oracle.jdbc.OracleTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProgressBasisDB extends CommonDB {
    
    private static Log logger = LogFactory.getLog(ProgressBasisDB.class);


    /**
     * 하나의 레코드 읽어 오기
     *
     * @param oDb
     * @param leccode
     * @return
     * @throws MdbException
     */
    public static Map getLecOne(MdbDriver oDb, Map param) throws MdbException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * " +
            " FROM bas_lec" +
            " WHERE lec_cd = :lec_cd";

        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 진도기준정보 등록/수정하기
     * Description  : 진도 관리 방식
     * @param       : leccode - 강의코드
     * @param       : dtime - 하루최대학습시간
     * @param       : prog - 진도유형
     * @param       : weck - 기준학습시간
     * @param       : wetm - 강의평가(학습이해도)
     * @return message 처리결과
     * @exception DBHandlerException, DBConnectionFailedException
     */
    public static  int update(MdbDriver oDb, String lec_cd, String dtime, String[] prog, String weck, int wetm) throws MdbException {
        String sql = "";
        PreparedStatement   pstmt    = null;

        int retValue = 0;
        String lecweck = "N";
        int lecwetm = 1;
        int lectypes=0;

        //실학습시간이 기준시간 이상이어야 이수
        if(weck!=null && !"".equals(weck)) {
            if(!weck.equals("Y")) {
                lecweck = "N";
            } else {
                lecweck = "Y";
            }
        }

        if(wetm !=1 ) {
            lecwetm = 0;
        } else {
            lecwetm = 1;
        }

        //하루최대학습 시간
        if(dtime==null || dtime.equals("") || dtime=="") {
            dtime = "0";
        }

        try {
            lectypes=0;

            if(prog != null) {
                for(int i=0; i<prog.length; i++) {
                    lectypes += Integer.parseInt(prog[i]);
                }
            }

            sql =  "UPDATE bas_lec " +
            " SET prg_type = "+lectypes+", lec_datm="+dtime+", lec_weck='"+lecweck+"', lec_wetm="+lecwetm+
            "  WHERE lec_cd = '"+lec_cd+"' ";

            pstmt = oDb.prepareCall(sql);
            retValue = pstmt.executeUpdate();

            if(retValue < 0) {
                retValue = -1;
            } else {
                retValue = 1;
            }
        } catch (SQLException s) {
            retValue = 0;
            logger.error(s.getMessage());
        }finally{
            release(pstmt);
        }
        return retValue;
    }

    /**
     * 진도기준정보 등록/수정하기
     * Description  : 진도 관리 방식
     * @param       : leccode - 강의코드
     * @param       : dtime - 하루최대학습시간
     * @param       : prog - 진도유형
     * @param       : weck - 기준학습시간
     * @param       : wetm - 강의평가(학습이해도)
     * @param       : exm_condition - 시험응시조건
     * @return message 처리결과
     * @exception DBHandlerException, DBConnectionFailedException
     */
    public static  int update(MdbDriver oDb, String lec_cd, String dtime, String[] prog, String weck, int wetm, String exm_condition) throws MdbException {
        String sql = "";
        PreparedStatement   pstmt    = null;

        int retValue = 0;
        String lecweck = "N";
        int lecwetm = 1;
        int lectypes=0;

        //실학습시간이 기준시간 이상이어야 이수
        if(weck!=null && !"".equals(weck)) {
            if(!weck.equals("Y")) {
                lecweck = "N";
            } else {
                lecweck = "Y";
            }
        }

        if(wetm !=1 ) {
            lecwetm = 0;
        } else {
            lecwetm = 1;
        }

        //하루최대학습 시간
        if(dtime==null || dtime.equals("") || dtime=="" ) {
            dtime = "0";
        }

        //시험응시 조건
        if(exm_condition==null || exm_condition.equals("") || exm_condition=="") {
            exm_condition = "0";
        }

        try {
            lectypes=0;

            if(prog != null) {
                for(int i=0; i<prog.length; i++) {
                    lectypes += Integer.parseInt(prog[i]);
                }
            }
            if(!(4==lectypes || 5==lectypes || 6==lectypes || 7==lectypes ||
                    12==lectypes || 13==lectypes || 14==lectypes || 15==lectypes )) {
                dtime = "0";
            }
            if(!(2==lectypes || 3==lectypes || 6==lectypes || 7==lectypes ||
                    10==lectypes || 11==lectypes || 14==lectypes || 15==lectypes )) {
                exm_condition = "0";
            }

            sql =  "UPDATE bas_lec " +
            " SET prg_type = "+lectypes+", lec_datm="+dtime+", lec_weck='"+lecweck+
            "', lec_wetm="+lecwetm+", exm_condition="+exm_condition +
            "  WHERE lec_cd = '"+lec_cd+"' ";

            pstmt = oDb.prepareCall(sql);
            retValue = pstmt.executeUpdate();

            if(retValue < 0) {
                retValue = -1;
            } else {
                retValue = 1;
            }
        } catch (SQLException s) {
            retValue = 0;
            logger.error(s.getMessage());
        }finally{
            release(pstmt);
        }
        return retValue;
    }
    
    /**
     * 하나의 레코드 읽어 오기
     *
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static  Map getOne(MdbDriver oDb, Map param) throws MdbException {
        String sql = "SELECT * " +
            " FROM wlc_rat_mst" +
            " WHERE lec_cd = :lec_cd";
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 평가기준정보 등록/수정하기
     *
     * @param bean[] 평가기준정보 bean
     * @return message 처리결과
     * @exception DBHandlerException, DBConnectionFailedException
     */
    public static  int update(MdbDriver oDb, Map param) throws MdbException {
        int retValue = 0;
        int i = 1;
        String sql = "{call sp_wla_rat_mst (:lec_cd, :ratjindo, :jindo_time, :jindo_page, :ratattend, " +
        		" :ratexam, :ratreport, :ratproject, :ratdiscuss, :ratforum, " +
        		" :ratoffline, :ratetc, :ratadd, :upt_id, ?)}";
        CallableStatement cstmt = null;

        try {
            cstmt = oDb.prepareCall(sql, param);
            cstmt.registerOutParameter(15,OracleTypes.INTEGER);

            cstmt.executeUpdate();
            if(retValue < 0) {
                retValue = -1;
            } else {
                retValue = 1;
            }
        } catch (SQLException s) {
            retValue = 0;
            oDb.rollback();
			logger.error(s.getMessage());
        }finally{
            release(cstmt);
        }
        return retValue;
    }
}