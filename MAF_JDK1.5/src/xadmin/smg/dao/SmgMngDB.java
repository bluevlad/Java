package xadmin.smg.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import maf.MafUtil;
import maf.logger.Logging;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import xadmin.smg.beans.SmgMngBean;

/**
 * SmgMngDB.java (온라인 설문 관리)
 *
 * @ author : 이성수
 * @ e-mail : poison29@miraenet.com
 * @ create-date : 2003-07-30
 * @ modify-date : 
 */

public class SmgMngDB extends CommonDB 
{

    private static SmgMngDB instance;
    private SmgMngDB() {
    }
//    private String DB_SID [] = Header.DB_SID;
    int mngnumb = 0;

    // 싱글톤 클래스로 구현 
    // 생성자를 private 로 선언
    public static synchronized SmgMngDB getInstance(){
        if (instance == null)
            instance = new SmgMngDB();
        return instance;
    }

    // 설문 체크 및 UV_SMG_USR 등록
    public synchronized int setSmgUsr(MdbDriver oDb, String leccode, int mngnumb, String stdcode)
    throws MdbException {
        String sql = "{CALL MI_SMG.SP_SMGUSR_INS (?,?,?,?)}";
//        Connection con = null;
        CallableStatement cstmt = null;
        int ret_val = -5;
        
        try {
//            con = getConnection(false);
            cstmt = oDb.prepareCall(sql);
            cstmt.setString(1, leccode);
            cstmt.setInt(2, mngnumb);
            cstmt.setString(3, stdcode);
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            ret_val = cstmt.getInt(4);  //0:성공, -1:설문이 없음, -2:설문기간 오류, -3:이미 설문응시, 그외:에러
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

    // 설문 응시 등록
    public synchronized int setSmgMngApply(MdbDriver oDb, String leccode, int mngnumb, String stdcode, int quecount, String[] quenumb, String[] quetype, String[] anttext)
    throws MdbException 
    {
        String sql = "{CALL MI_SMG.SP_SMGANS_UPT (?,?,?,?,?)}";
        String sql2 = "{CALL MI_SMG.SP_SMGANT_INS (?,?,?,?,?,?)}";
//        Connection con = null;
//        Connection con2 = null;
        CallableStatement cstmt = null;
        CallableStatement cstmt2 = null;
        int ret_val = -5;
//        Logger logger = Logger.getLogger(this.getClass());
        
        try {
        	oDb.setAutoCommit(false);
            cstmt = oDb.prepareCall(sql);
            cstmt2 = oDb.prepareCall(sql2);
            
            for(int i=0; i<quecount; i++) {
            	
                if(quetype[i].equals("1")) {    //선택형
                    cstmt.clearParameters();
                    cstmt.setString(1, leccode);
                    cstmt.setInt(2, mngnumb);
                    cstmt.setInt(3, Integer.parseInt(quenumb[i]));
                    cstmt.setInt(4, MafUtil.parseInt(anttext[i]));
                    cstmt.registerOutParameter(5, java.sql.Types.INTEGER);
                    cstmt.executeUpdate();
                    ret_val = cstmt.getInt(5);  //0:성공, 그외:에러
                    
                    if(ret_val != 0) {
                        throw new SmgException("smg error");
                    }

                } else if(quetype[i].equals("2")) { //복수선택형
                    
                    int length = anttext[i].length();
                    if(length > 0) anttext[i] = anttext[i].substring(0, length-1);
                    StringTokenizer st = new StringTokenizer(anttext[i], ",");
                    while(st.hasMoreTokens()) {//st.nextToken();
                        cstmt.clearParameters();
                        cstmt.setString(1, leccode);
                        cstmt.setInt(2, mngnumb);
                        cstmt.setInt(3, Integer.parseInt(quenumb[i]));
                        cstmt.setInt(4, Integer.parseInt(st.nextToken()));
                        cstmt.registerOutParameter(5, java.sql.Types.INTEGER);
                        cstmt.executeUpdate();
                        ret_val = cstmt.getInt(5);  //0:성공, 그외:에러
                        if(ret_val != 0) {
                            throw new SmgException("smg error");
                        }
                    }//end while
                    if(ret_val != 0) break;
                } else {    //논술형
                 	
                    cstmt2.clearParameters();
                    cstmt2.setString(1, leccode);
                    cstmt2.setInt(2, mngnumb);
                    cstmt2.setInt(3, Integer.parseInt(quenumb[i]));
                    cstmt2.setString(4, stdcode);
                    cstmt2.setString(5, anttext[i]);
                    cstmt2.registerOutParameter(6, java.sql.Types.INTEGER);
                    cstmt2.executeUpdate();
                    ret_val = cstmt2.getInt(6);  //0:성공, 그외:에러
                    if(ret_val != 0) {
                        throw new SmgException("smg error");
                    }
                }
            }//end for

            cstmt.close();
            cstmt2.close();
            oDb.commit();
        } catch (SmgException e) {
        	oDb.rollback();
            setRollback(oDb, leccode, mngnumb, stdcode);
            throw new MdbException(e.getMessage());
        } catch (Exception e) {
            oDb.rollback();
            setRollback(oDb, leccode, mngnumb, stdcode);
            Logging.trace(e);
            throw new MdbException(e.getMessage());
        } finally {
            release(cstmt);
            release(cstmt2);
        	oDb.setAutoCommit(true);
        }
        return ret_val;
    }

    public synchronized void setRollback(MdbDriver oDb, String leccode, int mngnumb, String stdcode)
        throws MdbException {
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM SMG_USR WHERE LECCODE=? AND MNGNUMB=? AND usn=?";

        try {
            pstmt = oDb.prepareStatement(sql);                    
            pstmt.setString(1, leccode);
            pstmt.setInt(2, mngnumb);
            pstmt.setString(3, stdcode);
            pstmt.executeUpdate();
            pstmt.close();
            oDb.commit();
        } catch(Exception e) {
            oDb.rollback();
            throw new MdbException(e.getMessage());
        } finally {
        	
            release(pstmt);
        }
    }

    //설문 참여자수 가져오기
    public int getSmgUsrCount(MdbDriver oDb, String leccode, String mngnumb)
        throws MdbException {
//        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM SMG_USR WHERE LECCODE=? AND MNGNUMB=?";
        int count = 0;

        try {
//            con = getConnection();
            pstmt = oDb.prepareStatement(sql);                    
            pstmt.setString(1, leccode);
            pstmt.setString(2, mngnumb);
            rs = pstmt.executeQuery();
            if(rs.next()) count = rs.getInt(1);
            rs.close();
            pstmt.close();

        } catch(Exception e) {
            throw new MdbException(e.getMessage());
        } finally {
            release(pstmt);
//            freeConnection(DB_SID[0], con);
        }
        return count;
    }

    /**
     * 메인화면에 진행중인 가장 최근의 온라인 설문 가져오기
     * @param oDb
     * @return
     * @throws MdbException
     */
    public SmgMngBean getMainSmgNow(MdbDriver oDb) throws MdbException {
    	String sql = " SELECT x.*"   +
				    	"   FROM (SELECT   *"   +
						"             FROM smg_mng"   +
						"            WHERE leccode = 'SYSTEM'"   +
						"              AND SYSDATE >= mngsdat"   +
						"              AND SYSDATE <= mngedat"   +
						"         ORDER BY mngsdat DESC) x"   +
						"  WHERE ROWNUM = 1"  ;
    	return (SmgMngBean)oDb.selectorOne(SmgMngBean.class, sql);
    }
    
    // 온라인설문 설문관리 정보
    public Map getRecode(MdbDriver oDb, String leccode, String mngnumb) 
       throws MdbException {
//        Selecter s = new Selecter(DB_SID[0], "com.miraenet.beans.SmgMngBean", true);
//        Collection col = s.execute("select * from SMG_MNG where LECCODE='"+leccode+"' and MNGNUMB="+mngnumb, Selecter.NOT_NULL);
//        return ((SmgMngBean[])col.toArray(new SmgMngBean[0]))[0]; 
    	String sql = "select * from SMG_MNG where LECCODE='"+leccode+"' and MNGNUMB="+mngnumb;
    	return (Map)oDb.selectorOne(Map.class, sql);
    }

    public int getMngnumb() {
        return mngnumb;
    }

    // 온라인 설문 관리 등록
    public synchronized int insert(MdbDriver oDb, SmgMngBean bean, String update_id) 
    	throws MdbException {
        String sql = "{CALL MI_SMG.SP_SMGMNG_INS (?,?,?,?,?,?,?)}";
//        Connection con = null;
        CallableStatement cstmt = null;
        int ret_val = -1;
        String sDate=bean.getMngsdat1() + "-" + bean.getMngsdat2() + "-" + bean.getMngsdat3() + " 00:00:01";
        String eDate=bean.getMngedat1() + "-" + bean.getMngedat2() + "-" + bean.getMngedat3() + " 23:59:59";    
        Logging.log(this.getClass(), "sDate : " + sDate);
        Logging.log(this.getClass(), "eDate : " + eDate);

        try {
//            con = getConnection(false);
            cstmt = oDb.prepareCall(sql);
            cstmt.setString(1, bean.getLeccode());
           cstmt.setString(2, sDate);
           cstmt.setString(3, eDate);
            cstmt.setString(4, bean.getMngtitl());
            cstmt.setString(5, update_id);
            cstmt.registerOutParameter(6, java.sql.Types.INTEGER);
            cstmt.registerOutParameter(7, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            ret_val = cstmt.getInt(6);  //0:성공, 1:강의가 없음, 그외:에러
            mngnumb = cstmt.getInt(7);
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

    // 온라인 설문 관리 수정
    public synchronized int update(MdbDriver oDb, SmgMngBean bean, String update_id) throws MdbException {
        String sql = "{CALL MI_SMG.SP_SMGMNG_UPT (?,?,?,?,?,?,?)}";
//        Connection con = null;
        CallableStatement cstmt = null;
        int ret_val = -1;
        String sDate=bean.getMngsdat1() + "-" + bean.getMngsdat2() + "-" + bean.getMngsdat3() + " 00:00:01";
        String eDate=bean.getMngedat1() + "-" + bean.getMngedat2() + "-" + bean.getMngedat3() + " 23:59:59";

        try {
//            con = getConnection(false);
            cstmt = oDb.prepareCall(sql);
            cstmt.setString(1, bean.getLeccode());
            cstmt.setInt(2, bean.getMngnumb());
            cstmt.setString(3, sDate);
            cstmt.setString(4, eDate);
            cstmt.setString(5, bean.getMngtitl());
            cstmt.setString(6, update_id);
            cstmt.registerOutParameter(7, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            ret_val = cstmt.getInt(7);  //0:성공, 1:강의가 없음, 2:설문에 응한 회원이 존재, 그외:에러
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

    // 온라인 설문 관리 삭제
    public synchronized int delete(MdbDriver oDb, String leccode, int mngnumb) throws MdbException {
        String sql = "{CALL MI_SMG.SP_SMGMNG_DEL (?,?,?)}";
//        Connection con = null;
        CallableStatement cstmt = null;
        int ret_val = -1;

        try {
//            con = getConnection(false);
            cstmt = oDb.prepareCall(sql);
            cstmt.setString(1, leccode);
            cstmt.setInt(2, mngnumb);
            cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            ret_val = cstmt.getInt(3);  //0:성공, 1:강의가 없음, 그외:에러
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

    //  과목에 해당하는 온라인 설문목록 리턴
    public List getAllRec(MdbDriver oDb, String leccode)
       throws MdbException {
    	Map at = new HashMap();
    	at.put("leccode", leccode);
    	final String sql = " SELECT sm.*, " +
    		" (select count(*) from smg_usr where leccode = sm.leccode and mngnumb = sm.mngnumb) cnt"   +
	    	" FROM SMG_MNG sm"   +
			"  WHERE sm.LECCODE=:leccode"   +
			"  ORDER BY sm.MNGNUMB DESC"  ;
    	
    	return oDb.selector(Map.class, sql, at);
    }
    
    // 학생이 자신의 과목에 해당하는 온라인 설문목록 리턴
    public List getStdAllRec(MdbDriver oDb, String leccode, String usn, String lecnumb)
       throws MdbException {
    	Map at = new HashMap();
    	at.put("leccode", leccode);
    	at.put("usn", usn);
    	at.put("lecnumb", lecnumb);
    	final String sql = "  SELECT sm.*, "   +
    	"  (select count(*) from smg_usr " +
    	"		where leccode = sm.leccode " +
    	"				and mngnumb = sm.mngnumb " +
    	"				and usn =:usn " +
    	"				and lecnumb = :lecnumb) cnt"   +
		"  FROM SMG_MNG sm"   +
		"   WHERE sm.LECCODE=:leccode"   +
		"   ORDER BY sm.MNGNUMB DESC"  ;
    	
    	return oDb.selector(Map.class, sql, at);
    }    
    /**
     * 해당설문의 응답수를 구한다. 
     * @param oDb
     * @param leccode
     * @param mngnumb
     * @return
     * @throws MdbException
     */
    public int getSmgRec(MdbDriver oDb, String leccode, String mngnumb)  throws MdbException {

    	String sql = "select count(*) cnt from SMG_USR where leccode = :leccode and mngnumb = :mngnumb" ;
    	Map at = new HashMap();
    	at.put("leccode", leccode);
    	at.put("mngnumb", mngnumb );
    	return oDb.selectOneInt(sql, at);
    }
}