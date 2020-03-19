/*
 * 게시판의 Data 관련 처리 작성자 : 김상준 Created on 2004. 9. 23.
 *  
 */
package miraenet.app.mboard.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import maf.MafUtil;
import maf.logger.Logging;
import maf.mdb.MdbParameters;
import maf.mdb.drivers.MdbOCI8;
import miraenet.app.mboard.MBoardManager;
import miraenet.app.mboard.beans.MbbsDataBean;
import miraenet.app.mboard.exception.MBoardException;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 김상준(goindole@miraenet.com) Create by 2004. 9. 23.
 *  
 */
public class MbbsDataDB {
    private  Log logger = LogFactory.getLog(MbbsDataDB.class);
    private static MbbsDataDB _instance = null;

    public static synchronized MbbsDataDB get_instance() {
    	if (_instance == null) {
    		_instance = new MbbsDataDB();
    	}
        return _instance;
    }

    private MbbsDataDB() {
    }

    /**
     * 게시물 List를 MbbsDataBean으로 묵어서 보냄
     * 
     * @param conn
     * @param bid
     * @param v_pagedown
     * @param v_page
     * @param v_max_index
     * @param v_min_index
     * @param v_numrows
     * @param v_key
     * @param v_srch
     * @return
     * @throws SQLException
     */
    public List getList(MBoardManager oMbbs, int v_page, String v_key, String v_srch, String v_status)
        throws Exception {
        CallableStatement stmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();

        try {
            String sql = "{call MI_BBS.GetList(:v_cur , " 
            		+ " :v_BID,  :v_page, :v_numrows,"
                    + " :v_status,  :v_key, :v_srch )}";

            stmt = oMbbs.getODB().prepareCall(sql);
            int i = 0;
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.setString(++i, oMbbs.getBid());
            stmt.setInt(++i, v_page);
            stmt.setInt(++i, oMbbs.getBoard().getNrows());
            stmt.setString(++i, v_status);
            stmt.setString(++i, v_key);
            stmt.setString(++i, v_srch);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            i = 0;
            while (rs.next()) {
                if (i == 0) {
                    oMbbs.setListDataCount(rs.getInt("totalCount"));
                }
                i++;
                MbbsDataBean oB = new MbbsDataBean();
                oB.setC_index(rs.getString("c_index")); // c_index
                oB.setC_subject(rs.getString("c_subject").trim()); // 제목
                oB.setC_date(rs.getTimestamp("c_date")); // 등록일시
                oB.setC_visit(rs.getInt("c_visit")); // 조회수
                oB.setC_ref(rs.getString("c_ref")); // 원글 c_index
                oB.setC_level(rs.getInt("c_level")); // 글의 depth
                oB.setC_step(rs.getInt("c_step")); // c_step
                oB.setC_grp(rs.getString("c_grp")); // 시조원글의 c_index
                oB.setC_status(rs.getString("c_status")); // 글의 상태
                oB.setUsn(rs.getString("usn")); // 글쓴이 User Sequence
                                                          // Number
                oB.setWname(rs.getString("wname").trim()); // 필명
                oB.setC_ccnt(rs.getInt("c_ccnt")); // 덧글수
                oB.setC_email(rs.getString("c_email")); // 글쓴이
                                                                    // E-Mail
                oB.setC_rcnt(rs.getInt("c_rcnt")); // 하부 리플의 수
                oB.setC_category(rs.getString("c_category")); // Category
                oB.setC_image(rs.getString("c_image"));
                oB.setU_type(rs.getString("u_type")); // 글쓴이 유형
                oB.setUserid(rs.getString("userid")); // 글쓴이 id
                oB.setIs_notice(rs.getString("is_notice"));
                oB.setC_image(rs.getString("c_image"));
                list.add(oB);
            }
        } catch (Exception e) {
            throw new MBoardException("게시물목록을 읽어 오는중  중 오류가 발생하였습니다(DB).!!!", e);
        } finally {
            if (rs != null)try {rs.close();} catch (Exception e) {}
			if (stmt != null)try {stmt.close();	} catch (Exception e) {}
			rs = null;
			stmt = null;
        }
        return list;
    }

    /**
     * 공지 목록 가져오기
     * @param oMbbs
     * @return
     * @throws Exception
     */
    public List getNoticeList(MBoardManager oMbbs) throws Exception {
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();

		try {
			String sql = "{call MI_BBS.GetNoticeList(:v_cur ,  :v_BID )}";

			stmt = oMbbs.getODB().prepareCall(sql);
			int i = 0;
			stmt.registerOutParameter(++i, OracleTypes.CURSOR);
			stmt.setString(++i, oMbbs.getBid());
			stmt.execute();
			rs = (ResultSet) stmt.getObject(1);

			i = 0;
			while (rs.next()) {
				i++;
				MbbsDataBean oB = new MbbsDataBean();
				oB.setC_index(rs.getString("c_index")); // c_index
				oB.setC_subject(rs.getString("c_subject").trim()); // 제목
				oB.setC_date(rs.getTimestamp("c_date")); // 등록일시
				oB.setC_visit(rs.getInt("c_visit")); // 조회수
				oB.setC_ref(rs.getString("c_ref")); // 원글 c_index
				oB.setC_level(rs.getInt("c_level")); // 글의 depth
				oB.setC_step(rs.getInt("c_step")); // c_step
				oB.setC_grp(rs.getString("c_grp")); // 시조원글의 c_index
				oB.setC_status(rs.getString("c_status")); // 글의 상태
				oB.setUsn(rs.getString("usn")); // 글쓴이 User Sequence
				// Number
				oB.setWname(rs.getString("wname").trim()); // 필명
				oB.setC_ccnt(rs.getInt("c_ccnt")); // 덧글수
				oB.setC_email(rs.getString("c_email")); // 글쓴이
				// E-Mail
				oB.setC_rcnt(rs.getInt("c_rcnt")); // 하부 리플의 수
				oB.setC_category(rs.getString("c_category")); // Category
				oB.setC_image(rs.getString("c_image"));
				oB.setU_type(rs.getString("u_type")); // 글쓴이 유형
				oB.setUserid(rs.getString("userid")); // 글쓴이 id
				oB.setIs_notice(rs.getString("is_notice"));
				oB.setC_image(rs.getString("c_image"));
				list.add(oB);
			}
		} catch (Exception e) {
			throw new MBoardException("게시물목록을 읽어 오는중  중 오류가 발생하였습니다(DB).!!!", e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			rs = null;
			stmt = null;
		}
		return list;
	}
    
    public MbbsDataBean getOne(MBoardManager oMbbs, String c_index)
        throws Exception {
        String sql1 = " UPDATE MBBS_DATA SET  C_VISIT = C_VISIT + 1 " + " WHERE BID = ? AND C_INDEX = ?";
        List at = new ArrayList();
        at.add(oMbbs.getBid());
        at.add(c_index);
        oMbbs.getODB().executeUpdate(sql1, at);

        String sql2 = " SELECT d.bid, d.c_index, d.c_subject, d.c_date, d.c_visit, d.c_ref, d.c_level,"   +
	        "        d.c_grp, d.c_step, d.c_status, d.usn, d.wname, d.c_passwd, d.c_ccnt,"   +
	        "        d.c_ip, d.c_type, d.c_email, d.c_rcnt, d.c_cnt_p, d.c_cnt_m,"   +
	        "        d.c_category, d.is_notice, d.c_content,"   +
	        "         get.UserID(d.usn) AS userid," +
	        "		 d.is_notice "   +
	        "   FROM MBBS_DATA d"   +
	        "  WHERE d.bid = :bid AND d.c_index = :cindex"  ;
        return (MbbsDataBean) oMbbs.getODB().selectorOne(MbbsDataBean.class, sql2, at);
    }

    /**
     * 하나의 게시물을 내용과 제목 없이 usn, 암호, 상태만 가져 온다.(삭제 확인용)
     * 
     * @param oMbbs
     * @param c_index
     * @return
     * @throws Exception
     */
    public MbbsDataBean getOneSimple(MBoardManager oMbbs, String c_index)
        throws Exception {

        int i = 1;

        CallableStatement stmt = null;
        OracleResultSet rset = null;
        MbbsDataBean oB = null;

        String sql = "{call MI_BBS.GetOneSimple(:v_cur, :bid, :c_index )}";

        try {

            stmt = oMbbs.getODB().prepareCall(sql);
            stmt.registerOutParameter(i++, OracleTypes.CURSOR);
            stmt.setString(i++, oMbbs.getBid());
            stmt.setString(i++, c_index);
            stmt.execute();
            rset = (OracleResultSet) stmt.getObject(1);

            while (rset.next()) {
                oB = new MbbsDataBean();
                oB.setBid(oMbbs.getBid());
                oB.setC_index(c_index);
                oB.setC_status(rset.getString("c_status"));
                oB.setUsn(rset.getString("usn"));
                oB.setC_passwd(rset.getString("C_PASSWD"));
            }
        } catch (SQLException ex) {
            throw new MBoardException("게시물 읽어 오는중  중 오류가 발생하였습니다(DB).!!!", ex);
        } finally {
            if (rset != null)
                try {
                    rset.close();
                    rset = null;
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception e) {
                }
        }
        return oB;
    }

    /**
     * 게시판에 글을 등록
     * 
     * @param oMbbs
     * @param item
     * @return
     */
    public String addItem(MBoardManager oMbbs, MbbsDataBean item)
        throws Exception {
        String c_index = null;
        CallableStatement stmt = null;

        int i = 1;

        try {
            if (MafUtil.empty(item.getC_ref())) {
                String sql = "{call MI_BBS.WriteData(:v_INDEX," + ":BID, :v_USN, :v_WNAME, :v_PASSWD, "
                        + " :v_EMAIL, :v_SUBJECT,  :v_CONTENT, " + " :v_C_TYPE, :v_IP, :v_Category, :v_is_notice )} ";
                stmt = oMbbs.getODB().prepareCall(sql);
                stmt.registerOutParameter(i++, OracleTypes.VARCHAR);
                stmt.setString(i++, item.getBid());
                stmt.setString(i++, item.getUsn());
                stmt.setString(i++, item.getWname());
                stmt.setString(i++, item.getC_passwd());
                stmt.setString(i++, item.getC_email());
                stmt.setString(i++, item.getC_subject());
//                String data = "";//item.getC_content();
//                StringReader sr = new StringReader(data);
//                stmt.setCharacterStream(i ++, sr, data.length());
                stmt.setString(i++, "");
                //stmt.setString(i++, item.getC_content());
                //stmt.setStringForClob(i++, item.getC_content());
                stmt.setString(i++, item.getC_type());
                stmt.setString(i++, item.getC_ip());
                stmt.setString(i++, item.getC_category()); // Category
                stmt.setString(i++, item.getIs_notice()); // Category
                stmt.execute();
            } else {
                logger.debug("Data Reply of " + item.getC_ref());
                String sql = "{call MI_BBS.WriteDatabyRef(:v_INDEX, " 
                    	+ " :BID, :v_REF, :v_USN, :v_WNAME, :v_PASSWD, "
                        + " :v_EMAIL, :v_SUBJECT, :v_CONTENT,  " 
                        + " :v_C_TYPE, :v_IP, :v_Category )} ";
                stmt = oMbbs.getODB().prepareCall(sql);
                stmt.registerOutParameter(i++, OracleTypes.VARCHAR);
                stmt.setString(i++, item.getBid());
                stmt.setString(i++, item.getC_ref());
                stmt.setString(i++, item.getUsn());
                stmt.setString(i++, item.getWname());
                stmt.setString(i++, item.getC_passwd());
                
                stmt.setString(i++, item.getC_email());
                stmt.setString(i++, item.getC_subject());
//                String data = item.getC_content();
//                StringReader sr = new StringReader(data);
//                stmt.setCharacterStream(i ++, sr, data.length());
                //stmt.setString(i++, item.getC_content());
                //	        stmt.setStringForClob(i++, item.getC_content());
                stmt.setString(i++, "");
                stmt.setString(i++, item.getC_type());
                stmt.setString(i++, item.getC_ip());
                stmt.setString(i++, item.getC_category()); // Category
                stmt.execute();
            }
            c_index = stmt.getString(1);
            stmt.close();
            stmt = null;
            
            //updateCLOB(String table, String cLobColumn, String clobString,
            // MdbParameters p) throws MdbException;
            MdbParameters p = new MdbParameters();
            p.add("BID", item.getBid());
            p.add("C_INDEX", c_index);
//            logger.debug(" class MdbOCI8 = "  + MdbOCI8.class);
//            logger.debug(" class oMbbs.getODB() = "  + oMbbs.getODB().getClass());
            ((MdbOCI8) oMbbs.getODB()).updateCLOB("mbbs_data", "C_CONTENT", item.getC_content(), p);
        } catch (Exception ex) {
 	       Logging.trace(ex);
            throw new MBoardException("게시물 추가 중 오류가 발생하였습니다(DB).!!!", ex);
        } finally {
            if (stmt != null) {
                try {stmt.close();} catch (Exception e) {}
            }
            stmt = null;
        }
        
        return c_index;
    }

    /**
     * 게시판글 Update
     * 
     * @param oMbbs
     * @param item
     * @return
     */
    public void UpdateItem(MBoardManager oMbbs, MbbsDataBean item)
        throws Exception {

        CallableStatement stmt = null;

        int i = 1;
        try {
            String sql = "{call MI_BBS.MODIFY_DATA(:BID," + ":v_C_index, :v_USN, :v_WNAME, :v_PASSWD, "
                    + " :v_EMAIL, :v_SUBJECT, :v_CONTENT, " + " :v_C_TYPE, :v_IP, :v_Category, :v_is_notice )} ";
            stmt = oMbbs.getODB().prepareCall(sql);
            stmt.setString(i++, item.getBid());
            stmt.setString(i++, item.getC_index());
            stmt.setString(i++, item.getUsn());
            stmt.setString(i++, item.getWname());
            stmt.setString(i++, item.getC_passwd());
            stmt.setString(i++, item.getC_email());
            stmt.setString(i++, item.getC_subject());
            stmt.setString(i++, "");
            stmt.setString(i++, item.getC_type());
            stmt.setString(i++, item.getC_ip());
            stmt.setString(i++, item.getC_category()); // Category
            stmt.setString(i++, item.getIs_notice()); 

            stmt.execute();
            stmt.close();
            stmt = null;
            MdbParameters p = new MdbParameters();
            p.add("BID", item.getBid());
            p.add("C_INDEX", item.getC_index());
           
            ((MdbOCI8) oMbbs.getODB()).updateCLOB("mbbs_data", "C_CONTENT", item.getC_content(), p);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            logger.error(ex.getMessage());
            throw new MBoardException("게시물 수정 중 오류가 발생하였습니다(DB).!!!");
        } finally {
            if (stmt != null)
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception e) {
                }
        }
    }

    /**
     * 게시물 삭제
     * 
     * @param oMbbs
     * @param c_index
     * @param mode
     * @return
     */
    public void DeleteItem(MBoardManager oMbbs, String c_index, String mode)
        throws Exception {
        CallableStatement stmt = null;
        //OracleCallableStatement stmt = null;
        int i = 1;

        try {
            String sql = "{call MI_BBS.Delete(:BID," + ":v_C_index, :v_MODE )} ";
            stmt = oMbbs.getODB().prepareCall(sql);
            stmt.setString(i++, oMbbs.getBid());
            stmt.setString(i++, c_index);
            stmt.setString(i++, mode);
            stmt.execute();
        } catch (Exception ex) {
            throw new MBoardException("게시물 삭제 중 오류가 발생하였습니다(DAO).!!!", ex);
        } finally {
            if (stmt != null)
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception e) {
                }
        }
    }

}