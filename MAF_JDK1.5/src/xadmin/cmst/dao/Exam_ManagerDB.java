/*
 * PrdManagerDB.java, @ 2005-05-03
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import maf.logger.Logging;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import oracle.jdbc.OracleTypes;
import xadmin.cmst.beans.bank_ansBean;
import xadmin.cmst.beans.bank_examBean;
import xadmin.cmst.beans.bank_exam_setBean;
import xadmin.cmst.beans.bank_setBean;
import xadmin.cmst.beans.bank_sjtBean;

/**
 * @author goindole
 *
 */
public class Exam_ManagerDB  extends CommonDB{
    private static Exam_ManagerDB instance;


    private Exam_ManagerDB() {
    }

    public static synchronized Exam_ManagerDB getInstance() {
        if (instance == null)
            instance = new Exam_ManagerDB();
        return instance;
    }

    //문제은행 교과목별 리스트를 가져온다.
    public List getExamList(MdbDriver oDb,  String sort, int page, int page_size, String sjtcode, String etype, String chapter) 
    throws MdbException {
        
        List at = new ArrayList();

        String sql = " select a.exam_no, a.title,"   +
                        "  a.etype, a.reg_dt," +
                        "  a.sort_no, b.chapter, " +
                        "  a.note, a.score "   +
                        "  from bank_exam a, bank_sjt b"   +
                        "  where a.EXAM_NO = b.EXAM_NO " +
                        " AND b.sjtcode = :sjtcode";
            at.add(sjtcode);
        if (!"".equals(etype) && (etype != null)){
            sql = sql + " AND a.ETYPE = :etype";
            at.add(etype);
        }
        if (!"".equals(chapter) && (chapter != null)){
            sql = sql + " AND b.chapter = :chapter";
            at.add(chapter);
        }
        sql = sql + " ORDER BY b.chapter asc, a.TITLE ASC";
        
        oDb.setPage(page,page_size);
        return oDb.selector(Map.class, sql, at);
    }

    //문제은행 교과목별 리스트를 가져온다.
    public List getExamList(MdbDriver oDb,  String sort, int page, int page_size, String sjtcode, String etype, String chapter, String set_no) 
    throws MdbException {
        
        List at = new ArrayList();

        String sql = " select a.exam_no, a.title,"   +
                        "  a.etype, a.reg_dt," +
                        "  a.sort_no, b.chapter, " +
                        "  a.note, a.score "   +
                        "  from bank_exam a, bank_sjt b"   +
                        "  where a.EXAM_NO = b.EXAM_NO " +
                        " AND b.sjtcode = :sjtcode "+
                        "   and a.exam_no not in (SELECT B.EXAM_NO"   +
                        "                        FROM BANK_SJT A, BANK_EXAM B, bank_exam_set c"   +
                        "                        WHERE A.EXAM_NO = B.EXAM_NO"   +
                        "                        AND A.SJTCODE = :sjtcode"   +
                        "                        and b.exam_no = c.exam_no"   +
                        "                        and c.sjtcode = a.sjtcode"   +
                        "                        and c.set_no = :set_no)"  ;
        at.add(sjtcode);
        at.add(sjtcode);
        at.add(set_no);

        if (!"".equals(etype) && (etype != null)){
            sql = sql + " AND a.ETYPE = :etype";
            at.add(etype);
        }
        if (!"".equals(chapter) && (chapter != null)){
            sql = sql + " AND b.chapter = :chapter";
            at.add(chapter);
        }
        sql = sql + " ORDER BY b.chapter asc, a.TITLE ASC";
        
        oDb.setPage(page,page_size);
        return oDb.selector(Map.class, sql, at);
    }

    //문제은행 교과목별 리스트를 가져온다.
    public int getExamCount(MdbDriver oDb, String sjtcode, String etype, String chapter, String set_no) 
            throws MdbException {

        List at = new ArrayList();
                
        String sql = " select count(a.exam_no) "   +
                        "  from bank_exam a, bank_sjt b"   +
                        "  where a.EXAM_NO = b.EXAM_NO"+
						" AND b.sjtcode = :sjtcode" +
        "   and a.exam_no not in (SELECT B.EXAM_NO"   +
        "                        FROM BANK_SJT A, BANK_EXAM B, bank_exam_set c"   +
        "                        WHERE A.EXAM_NO = B.EXAM_NO"   +
        "                        AND A.SJTCODE = :sjtcode"   +
        "                        and b.exam_no = c.exam_no"   +
        "                        and c.sjtcode = a.sjtcode"   +
        "                        and c.set_no = :set_no)"  ;
            at.add(sjtcode);
            at.add(sjtcode);
            at.add(set_no);

        if (!"".equals(etype) && (etype != null)){
            sql = sql + " AND a.ETYPE = :etype";
            at.add(etype);
        }
        if (!"".equals(chapter) && (chapter != null)){
            sql = sql + " AND b.chapter = :chapter";
            at.add(chapter);
        }

        return oDb.selectOneInt(sql, at);
    }

    public Map getExamView(MdbDriver oDb, int exam_no) 
            throws MdbException {
        
        List at = new ArrayList();

        String sql = " SELECT EXAM_NO, sort_no, " +
                        " TITLE, SCORE, "   +
                        "  ECONTENTS, ETYPE,"   +
                        "  STATUS, ANSWER, " +
                        "  IMG, "   +
                        "  REG_DT, REG_ID,"   +
                        "  UP_DT, UP_ID"   +
                        "  FROM BANK_EXAM "+
                        "  WHERE EXAM_NO = :exam_no";

        at.add(exam_no+"");
        return (Map)oDb.selectorOne(Map.class, sql, at);
    }

    // 보기 리스트를 가져온다.
    public List getAnsList(MdbDriver oDb, int exam_no) 
        throws MdbException {
        
        List at = new ArrayList();

        final String sql = " SELECT EXAM_NO, ANS_NO," +
                                " TITLE "+
                                " FROM BANK_ANS "+
                                " WHERE EXAM_NO = :exam_no";

        at.add(exam_no+"");
        return oDb.selector(Map.class, sql, at);
    }

    // 교과목에 등록된 문제은행 리스트를 가져온다.
    public List getSjtExamList(MdbDriver oDb, String sjtcode, String module) 
        throws MdbException {
        
        List at = new ArrayList();

        final String sql = " SELECT B.EXAM_NO, B.sort_no, " +
                                " B.TITLE, B.NOTE, "+
		                        " B.ETYPE, B.STATUS, "+
                                " A.REG_DT, B.NOTE, " +
                                " A.CHAPTER, b.score "+
								" FROM BANK_SJT A, BANK_EXAM B "+
								" WHERE A.EXAM_NO = B.EXAM_NO "+
                                " AND A.SJTCODE = :sjtcode" +
                                " AND A.CHAPTER = :module" +
                                " ORDER BY B.sort_no ASC";

        at.add(sjtcode);
        at.add(module);
        return oDb.selector(Map.class, sql, at);
    }

    // 교과목에 등록된 chapter 리스트를 가져온다.
    public List getChpList(MdbDriver oDb, String sjtcode) 
        throws MdbException {
        
        List at = new ArrayList();

        final String sql = " SELECT chapter"   +
                                "  FROM BANK_SJT "   +
                                "  WHERE SJTCODE = :sjtcode " +
                                "  GROUP BY chapter ";

        at.add(sjtcode);
        return oDb.selector(Map.class, sql, at);
    }

    // 교과목에 등록된 문제은행 세트 리스트를 가져온다.
    public List getSetExamList(MdbDriver oDb, String sjtcode, String set_no) 
        throws MdbException {
        
        List at = new ArrayList();

        final String sql = " SELECT D.EXAM_NO, D.sort_no, " +
                                " D.TITLE, D.NOTE, "   +
                                "  D.ETYPE, D.STATUS,"   +
                                "  A.REG_DT"   +
                                "  FROM BANK_SET A, BANK_EXAM_SET B, BANK_SJT C, BANK_EXAM D"   +
                                "  WHERE A.SET_NO = B.SET_NO"   +
                                "  AND B.SJTCODE = C.SJTCODE"   +
                                "  AND B.EXAM_NO = C.EXAM_NO"   +
                                "  AND C.EXAM_NO = D.EXAM_NO"   +
                                "  AND B.SJTCODE = :sjtcode"   +
                                "  AND A.SET_NO = :set_no"   +
                                "  ORDER BY A.SET_NO ASC";

        at.add(sjtcode);
        at.add(set_no);
        return oDb.selector(Map.class, sql, at);
    }

    // 교과목에 등록된 세트 리스트를 가져온다.
    public List getSetList(MdbDriver oDb, String sjtcode) 
        throws MdbException {
        
        List at = new ArrayList();

        final String sql = " SELECT set_no, comments, " +
                                "  reg_dt "   +
                                "  FROM BANK_SET "   +
                                "  WHERE SJTCODE = :sjtcode " +
                                "  ORDER BY set_no asc ";

        at.add(sjtcode);
        return oDb.selector(Map.class, sql, at);
    }

    //문제를 등록한다.
    public int insertExam (MdbDriver oDb,  bank_examBean bean) throws Exception {
        int retValue = 0;

        String sql = "{call PROC_BANK_EXAM_INS (" +
                            " :title, :econtents, " +
                            " :answer, :etype, " +
                            " :usn, :note," +
                            " :img, " +
                            " :sort_no, :score, " +
                            " :exam_no"+
                         " )}";

        CallableStatement   cstmt    = null;
        try {
            int i = 1;

            cstmt = oDb.prepareCall(sql);
            cstmt.setString(i++, bean.getTitle());
            cstmt.setString(i++, bean.getEcontents());
            cstmt.setInt(i++, bean.getAnswer());
            cstmt.setString(i++, bean.getEtype());
            cstmt.setString(i++, bean.getReg_id());
            cstmt.setString(i++, bean.getNote());
            cstmt.setString(i++, bean.getImg());
            cstmt.setInt(i++, bean.getSort_no());
            cstmt.setInt(i++, bean.getScore());
            cstmt.registerOutParameter(i,OracleTypes.NUMBER);
            
            cstmt.execute();

            retValue = cstmt.getInt(i);

        } catch (SQLException s) {
            throw s;
        }finally{
            release(cstmt);
        }

        return retValue;
    }

    // 보기를 등록한다.
    public int insertAns (MdbDriver oDb, bank_ansBean bean) throws Exception {

        int ret = 0;

        PreparedStatement pstmt = null;
 
        try {
                String sql = "INSERT INTO BANK_ANS ("+
                              " exam_no, ans_no, "+
                              " title, "+
                              " reg_dt, reg_id "+
                              " ) "+
                              " VALUES ("+
                              " :exam_no, :ans_no, " +
                              " :title, "+
                              " SYSDATE, :reg_id "+
                              ") ";

                pstmt = oDb.prepareStatement(sql, bean);

                ret = pstmt.executeUpdate();

                pstmt.close();
                oDb.commit();

        } catch (Exception e) {
            oDb.rollback();
            throw new MdbException(e.getMessage());
        } finally {
            release(pstmt);
        }
        
        return ret;
    }

    // 문제은행 세트를 등록한다.
    public int insertSet (MdbDriver oDb, bank_setBean bean) throws Exception {

        int ret = 0;

        PreparedStatement pstmt = null;
 
        try {
                String sql = "INSERT INTO BANK_SET ("+
                              " set_no, sjtcode, " +
                              " comments, "+
                              " reg_dt, reg_id "+
                              " ) "+
                              " VALUES ("+
                              " SEQ_SET.nextval, :sjtcode, " +
                              " :comments, " +
                              " SYSDATE, :reg_id "+
                              ") ";

                pstmt = oDb.prepareStatement(sql, bean);

                ret = pstmt.executeUpdate();

                pstmt.close();
                oDb.commit();

        } catch (Exception e) {
            oDb.rollback();
            throw new MdbException(e.getMessage());
        } finally {
            release(pstmt);
        }
        
        return ret;
    }

    //문제은행 세트명을 수정한다.
    public int updateSet (MdbDriver oDb,  int set_no, String comments) throws Exception {
        int retValue = 0;

        String sql = "UPDATE BANK_SET " +
                       " SET comments = :comments " +
                       " WHERE EXAM_NO = :exam_no ";
        
        CallableStatement   cstmt    = null;
        try {
            int i = 1;

            cstmt = oDb.prepareCall(sql);
            cstmt.setString(i++, comments);
            cstmt.setInt(i++, set_no);
            
            cstmt.execute();

        } catch (SQLException s) {
            throw s;
        }finally{
            retValue = 1;
            release(cstmt);
        }

        return retValue;
    }

    //답변을 삭제한다.
    public int deleteAns (MdbDriver oDb,  int exam_no) throws Exception {
        int retValue = 0;

        String sql = "DELETE BANK_ANS " +
                       " WHERE EXAM_NO = :exam_no ";
        
        CallableStatement   cstmt    = null;
        try {
            int i = 1;

            cstmt = oDb.prepareCall(sql);
            cstmt.setInt(i++, exam_no);
            
            cstmt.execute();

        } catch (SQLException s) {
            throw s;
        }finally{
            retValue = 1;
            release(cstmt);
        }

        return retValue;
    }

    // 문제를 삭제한다.
    public int deleteExam (MdbDriver oDb, int exam_no, String usn) throws Exception {
        int retValue = 0;

        String sql = " UPDATE BANK_EXAM SET STATUS = '9'," +
                        " UP_DT = SYSDATE, " +
                        " UP_ID = :usn" +
                        " WHERE EXAM_NO = :exam_no ";
        
        CallableStatement   cstmt    = null;
        try {
            int i = 1;

            cstmt = oDb.prepareCall(sql);

            cstmt.setString(i++, usn);
            cstmt.setInt(i++, exam_no);

            cstmt.execute();

        } catch (SQLException s) {
            throw s;
        }finally{
            retValue = 1;
            release(cstmt);
        }
        return retValue;
    }

    // 교과목에 문제를 등록한다.
    public int insertExamSjt (MdbDriver oDb, bank_sjtBean bean) throws Exception {

        int ret = 0;

        PreparedStatement pstmt = null;
 
        try {
                String sql = "INSERT INTO BANK_SJT ("+
                              " exam_no, sjtcode, " +
                              " chapter, "+
                              " reg_dt, reg_id "+
                              " ) "+
                              " VALUES ("+
                              " :exam_no, :sjtcode, " +
                              " :chapter, " +
                              " SYSDATE, :reg_id "+
                              ") ";

                pstmt = oDb.prepareStatement(sql, bean);

                ret = pstmt.executeUpdate();

                pstmt.close();
                oDb.commit();

        } catch (Exception e) {
            oDb.rollback();
            throw new MdbException(e.getMessage());
        } finally {
            release(pstmt);
        }
        
        return ret;
    }

    // 교과목에 등록된 문제를 삭제한다.
    public int deleteExamSjt (MdbDriver oDb, int exam_no, String sjtcode) throws MdbException {
        int retValue = 0;

        String sql = " DELETE BANK_SJT " +
                        " WHERE EXAM_NO = :exam_no " +
                        " AND SJTCODE = :sjtcode";
        
        CallableStatement   cstmt    = null;
        try {
            int i = 1;

            cstmt = oDb.prepareCall(sql);

            cstmt.setInt(i++, exam_no);
            cstmt.setString(i++, sjtcode);

            cstmt.execute();

        } catch (SQLException s) {
            Logging.log(this.getClass(), s.getMessage());
            throw new MdbException(s);
        }finally{
            retValue = 1;
            release(cstmt);
        }
        return retValue;
    }

    // 문제은행 세트를 구성한다.
    public int insertExamSet (MdbDriver oDb, bank_exam_setBean bean) throws MdbException {

        int ret = 0;

        PreparedStatement pstmt = null;
 
        try {
                String sql = "INSERT INTO BANK_EXAM_SET ("+
                              " set_no, exam_no, " +
                              " sjtcode, " +
                              " reg_dt, reg_id "+
                              " ) "+
                              " VALUES ("+
                              " :set_no, :exam_no, " +
                              " :sjtcode, " +
                              " SYSDATE, :reg_id "+
                              ") ";

                pstmt = oDb.prepareStatement(sql, bean);

                ret = pstmt.executeUpdate();

                pstmt.close();
                oDb.commit();

        } catch (Exception e) {
            oDb.rollback();
            throw new MdbException(e.getMessage());
        } finally {
            release(pstmt);
        }
        
        return ret;
    }

    // 문제은행 세트에 등록된 문제를 삭제한다.
    public int deleteExamSet (MdbDriver oDb, int exam_no, String sjtcode, int set_no) throws Exception {
        int retValue = 0;

        String sql = " DELETE BANK_EXAM_SET " +
                        " WHERE EXAM_NO = :exam_no " +
                        " AND SJTCODE = :sjtcode" +
                        " and set_no = :set_no";
        
        CallableStatement   cstmt    = null;
        try {
            int i = 1;

            cstmt = oDb.prepareCall(sql);

            cstmt.setInt(i++, exam_no);
            cstmt.setString(i++, sjtcode);
            cstmt.setInt(i++, set_no);

            cstmt.execute();

        } catch (SQLException s) {
            throw s;
        }finally{
            retValue = 1;
            release(cstmt);
        }
        return retValue;
    }

}
