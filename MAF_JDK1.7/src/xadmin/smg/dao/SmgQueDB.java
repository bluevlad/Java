package xadmin.smg.dao;

import java.sql.CallableStatement;
import java.util.List;
import java.util.Map;

import maf.logger.Logging;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import xadmin.smg.beans.SmgQueBean;

/**
 * SmgQueDB.java (온라인 설문 관리)
 *
 * @ author : 이성수
 * @ e-mail : poison29@miraenet.com
 * @ create-date : 2003-07-31
 * @ modify-date : 
 */

public class SmgQueDB extends CommonDB 
{

    private static SmgQueDB instance;
    private SmgQueDB() {    	
    }
//    private String DB_SID [] = Header.DB_SID;

    // 싱글톤 클래스로 구현 
    // 생성자를 private 로 선언
    public static synchronized SmgQueDB getInstance() {
        if (instance == null)
            instance = new SmgQueDB();
        return instance;
    }

    // 온라인 설문 질문 등록
    public synchronized int insert(MdbDriver oDb, SmgQueBean bean, String update_id, int ans_count, String[] ans_desc) 
    throws MdbException
    {
        String sql = "{CALL MI_SMG.SP_SMGQUE_INS (?,?,?,?,?,?,?)}";
//        Connection con = null;
        CallableStatement cstmt = null;
        int ret_val = -1;
        String quetype = bean.getQuetype();
    	Logging.log(this.getClass(), "quetype1==>>" + quetype);
        int quenumb = 0;
        int suc_count = 0;

        try {
//            con = getConnection(false);
            cstmt = oDb.prepareCall(sql);
            cstmt.setString(1, bean.getLeccode());
            cstmt.setInt(2, bean.getMngnumb());
            cstmt.setString(3, bean.getQuetext());
            cstmt.setString(4, quetype);
            cstmt.setString(5, update_id);
            cstmt.registerOutParameter(6, java.sql.Types.INTEGER);
            cstmt.registerOutParameter(7, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            ret_val = cstmt.getInt(6);  //0:성공, -1:설문마스터 없음, -2:설문참여자 있음, 그외:에러
            quenumb = cstmt.getInt(7);          
            cstmt.close();

            if((quetype != null && (quetype.equals("1") || quetype.equals("2"))) && quenumb > 0) 
            {
            	Logging.log(this.getClass(), "quetype2==>>" + quetype);
                sql = "{CALL MI_SMG.SP_SMGANS_INS (?,?,?,?,?)}";
                cstmt = oDb.prepareCall(sql);
                for(int i=0; i < ans_count; i++) {
                    cstmt.clearParameters();
                    cstmt.setString(1, bean.getLeccode());
                    cstmt.setInt(2, bean.getMngnumb());
                    cstmt.setInt(3, quenumb);
                    cstmt.setString(4, ans_desc[i]);
                    cstmt.registerOutParameter(5, java.sql.Types.INTEGER);
                    cstmt.executeUpdate();
                    ret_val = cstmt.getInt(5);  //0:성공, 1:문제가 없음, 그외:에러
                    if(ret_val == 0) suc_count++;
                    else {
                        oDb.rollback();
                        break;
                    }
                }//end for
                cstmt.close();

                if(suc_count == ans_count) oDb.commit();
                else oDb.rollback();
            } else {
                if(ret_val == 0) oDb.commit();
                else oDb.rollback();
            }//end if

        } catch(Exception e) {
            oDb.rollback();
            throw new MdbException(e.getMessage());
        } finally {
            release(cstmt);
//            freeConnection(con);
        }
        return ret_val;
    }

    // 온라인 설문 질문 수정
    public synchronized int update(MdbDriver oDb, SmgQueBean bean, String update_id, int ans_count, String[] ans_desc) 
    throws MdbException {
        String sql = "{CALL MI_SMG.SP_SMGQUE_UPT (?,?,?,?,?,?,?)}";
//        Connection con = null;
        CallableStatement cstmt = null;
        int ret_val = -1;
        String quetype = bean.getQuetype();
        int quenumb = bean.getQuenumb();
        int suc_count = 0;

        try {
//            con = getConnection(false);
            cstmt = oDb.prepareCall(sql);
            cstmt.setString(1, bean.getLeccode());
            cstmt.setInt(2, bean.getMngnumb());
            cstmt.setInt(3, quenumb);
            cstmt.setString(4, bean.getQuetext());
            cstmt.setString(5, quetype);
            cstmt.setString(6, update_id);
            cstmt.registerOutParameter(7, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            ret_val = cstmt.getInt(7);  //0:성공, -1:설문마스터 없음, -2:설문참여자 있음, 그외:에러
            cstmt.close();

            if((quetype != null && (quetype.equals("1") || quetype.equals("2"))) && ret_val == 0) 
            {
                sql = "{CALL MI_SMG.SP_SMGANS_DEL (?,?,?,?)}";
                cstmt = oDb.prepareCall(sql);
                cstmt.setString(1, bean.getLeccode());
                cstmt.setInt(2, bean.getMngnumb());
                cstmt.setInt(3, quenumb);
                cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
                cstmt.executeUpdate();
                ret_val = cstmt.getInt(4);  //0:성공, 그외:에러
                cstmt.close();
                
                sql = "{CALL MI_SMG.SP_SMGANS_INS (?,?,?,?,?)}";
                cstmt = oDb.prepareCall(sql);
                for(int i=0; i < ans_count; i++) {
                    cstmt.clearParameters();
                    cstmt.setString(1, bean.getLeccode());
                    cstmt.setInt(2, bean.getMngnumb());
                    cstmt.setInt(3, quenumb);
                    cstmt.setString(4, ans_desc[i]);
                    cstmt.registerOutParameter(5, java.sql.Types.INTEGER);
                    cstmt.executeUpdate();
                    ret_val = cstmt.getInt(5);  //0:성공, 1:문제가 없음, 그외:에러
                    if(ret_val == 0) suc_count++;
                    else {
                        oDb.rollback();
                        break;
                    }
                }//end for
                cstmt.close();

                if(suc_count == ans_count) oDb.commit();
                else oDb.rollback();
            } else {
                if(ret_val == 0) oDb.commit();
                else oDb.rollback();
            }//end if

        } catch(Exception e) {
            oDb.rollback();
            throw new MdbException(e.getMessage());
        } finally {
            release(cstmt);
//            freeConnection(con);
        }
        return ret_val;
    }

    // 온라인 설문 질문 삭제
    public synchronized int delete(MdbDriver oDb, String leccode, int mngnumb, int quenumb) 
    throws MdbException {
        String sql = "{CALL MI_SMG.SP_SMGQUE_DEL (?,?,?,?)}";
//        Connection con = null;
        CallableStatement cstmt = null;
        int ret_val = -1;

        try {
//            con = getConnection(false);
            cstmt = oDb.prepareCall(sql);
            cstmt.setString(1, leccode);
            cstmt.setInt(2, mngnumb);
            cstmt.setInt(3, quenumb);
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            ret_val = cstmt.getInt(4);  //0:성공, -1:설문마스터 없음, -2:설문참여자 있음, 그외:에러
            cstmt.close();
            oDb.commit();

        } catch(Exception e) {
            oDb.rollback();
            throw new MdbException(e.getMessage());
        } finally {
            release(cstmt);
//            freeConnection(con);
        }
        return ret_val;
    }

    // 온라인 설문 참여유무
    public synchronized int getJoinCount(MdbDriver oDb, String leccode, int mngnumb, String stdcode) 
    throws MdbException {
        String sql = "{CALL MI_SMG.SP_SMGUSR_SEL (?,?,?,?)}";
//        Connection con = null;
        CallableStatement cstmt = null;
        int ret_val = -1;

        try {
//            con = getConnection(false);
            cstmt = oDb.prepareCall(sql);
            cstmt.setString(1, leccode);
            cstmt.setInt(2, mngnumb);
            cstmt.setString(3, stdcode);
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            ret_val = cstmt.getInt(4);
            cstmt.close();
            oDb.commit();

        } catch(Exception e) {
            oDb.rollback();
            throw new MdbException(e.getMessage());
        } finally {
            release(cstmt);
//            freeConnection(con);
        }
        return ret_val;
    }

    // 해당 설문에 등록된 모든 질문 리턴
    public List getAllRec(MdbDriver oDb, String leccode, String mngnumb)
       throws MdbException {
//        Selecter s = new Selecter(DB_SID[0], "com.miraenet.beans.SmgQueBean", true);
//        Collection col = s.execute("SELECT * FROM SMG_QUE WHERE LECCODE='"+leccode+"' AND MNGNUMB="+mngnumb+" ORDER BY QUENUMB", Selecter.NULL);
//      	return (SmgQueBean[])col.toArray(new SmgQueBean[0]); 
    	String sql = "SELECT * FROM SMG_QUE WHERE LECCODE='"+leccode+"' AND MNGNUMB="+mngnumb+" ORDER BY QUENUMB";
    	return oDb.selector(Map.class, sql);
    }
    
    // 해당 문제에 등록된 보기 리턴 (객관식)
    public List getAllRecAns(MdbDriver oDb, String leccode, String mngnumb)
       throws MdbException {
//        Selecter s = new Selecter(DB_SID[0], "com.miraenet.beans.SmgAnsBean", true);
//        Collection col = s.execute("SELECT * FROM SMG_ANS WHERE LECCODE='"+leccode+"' AND MNGNUMB="+mngnumb+" ORDER BY QUENUMB, ANSNUMB", Selecter.NULL);
//        return (SmgAnsBean[])col.toArray(new SmgAnsBean[0]); 
    	String sql = "SELECT * FROM SMG_ANS WHERE LECCODE='"+leccode+"' AND MNGNUMB="+mngnumb+" ORDER BY QUENUMB, ANSNUMB";
    	return oDb.selector(Map.class, sql);
    }

    // 해당 차시에 답변된 온라인 설문 리턴 (주관식)
    public List getAllRecAnt(MdbDriver oDb, String leccode, String mngnumb)
       throws MdbException {
//        Selecter s = new Selecter(DB_SID[0], "com.miraenet.beans.SmgAntBean", true);
//        Collection col=null;
//        col = s.execute("SELECT * FROM SMG_ANT WHERE LECCODE='"+leccode+"' AND MNGNUMB="+mngnumb+" ORDER BY QUENUMB", Selecter.NULL);
//        return (SmgAntBean[])col.toArray(new SmgAntBean[0]); 
    	String sql = "SELECT * FROM SMG_ANT WHERE LECCODE='"+leccode+"' AND MNGNUMB="+mngnumb+" ORDER BY QUENUMB";
    	return oDb.selector(Map.class, sql);
    }

    // 온라인설문 질문 리턴
    public Map getRecode(MdbDriver oDb, String leccode, String mngnumb, String quenumb) 
       throws MdbException {
//        Selecter s = new Selecter(DB_SID[0], "com.miraenet.beans.SmgQueBean", true);
//        Collection col = s.execute("SELECT * FROM SMG_QUE WHERE LECCODE='"+leccode+"' AND MNGNUMB="+mngnumb+" AND QUENUMB="+quenumb, Selecter.NOT_NULL);
//        return ((SmgQueBean[])col.toArray(new SmgQueBean[0]))[0];
    	String sql = "SELECT * FROM SMG_QUE WHERE LECCODE='"+leccode+"' AND MNGNUMB="+mngnumb+" AND QUENUMB="+quenumb;
    	return (Map)oDb.selectorOne(Map.class, sql);
    }

    // 온라인설문 질문 보기 리턴
    public List getAnsRecode(MdbDriver oDb, String leccode, String mngnumb, String quenumb) 
       throws MdbException {
//        Selecter s = new Selecter(DB_SID[0], "com.miraenet.beans.SmgAnsBean", true);
//        Collection col = s.execute("SELECT * FROM SMG_ANS WHERE LECCODE='"+leccode+"' AND MNGNUMB="+mngnumb+" AND QUENUMB="+quenumb+" ORDER BY ANSNUMB", Selecter.NULL);
//        return (SmgAnsBean[])col.toArray(new SmgAnsBean[0]);
    	String sql = "SELECT * FROM SMG_ANS WHERE LECCODE='"+leccode+"' AND MNGNUMB="+mngnumb+" AND QUENUMB="+quenumb+" ORDER BY ANSNUMB";
    	return oDb.selector(Map.class,sql);
    }
}