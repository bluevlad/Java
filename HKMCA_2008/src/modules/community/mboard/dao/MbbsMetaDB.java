/*
 * Created on 2004. 9. 22.
 *
 */
package modules.community.mboard.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import modules.community.mboard.beans.MbbsGroupBean;
import modules.community.mboard.beans.MbbsMetaBean;
import modules.community.mboard.exception.MBoardException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class MbbsMetaDB {
	private  Log logger = LogFactory.getLog(this.getClass());
    
	private static MbbsMetaDB instance = null;
	//private Connection conn = null;
	
    private MbbsMetaDB() {

    }
    
    public static synchronized MbbsMetaDB getInstance() {
    	if (instance == null) {
    		instance = new MbbsMetaDB();
    	}
        return instance;
    }
    
	/**
	 * MBBS_META 정보 조회
	 * @param h_idx
	 * @return
	 * @throws SQLException
	 */
	public MbbsMetaBean view(MdbDriver oDb, String v_BID) throws MdbException {
	    
        MbbsMetaBean oB = null;
        if (v_BID != null) {
            String sql = "SELECT m.bid, m.subject, m.cnt_t, m.cnt_w, m.grp, m.cnt_d, m.nrows, m.fl_write, " +
            		"       m.fl_read, m.fl_list, m.fl_notice, m.fl_auth, m.fl_reply, m.fl_comment, m.fl_html, m.fl_category, " +
            		"       m.fl_modify_date, m.number_attach, m.max_attach_size, m.fl_board_type, " +
            		"       m.total_attach_size, m.last_dt, m.CATEGORY, pgid, m.admin_usn, " +
            		"       m.is_use, m.fl_waste, m.max_attach_limit, g.site, " +
            		"       g.layout, g.c_path, m.header, m.leccode,m.club_id, " +
            		"       m.file_header, m.file_footer, ext_usersel, " +
            		"       m.grant_list, m.grant_view, m.grant_write,  " +
            		"       m.grant_comment, m.grant_reply, m.grant_delete, m.grant_html, m.grant_notice,  " +
            		"       m.grant_secret  " +
            		"FROM mbbs_meta m, mbbs_grp g " +
            		"WHERE m.bid = :v_bid    " +
            		"AND m.grp = g.grp "
;

                ArrayList at = new ArrayList();
                at.add(v_BID);
                oB =(MbbsMetaBean) oDb.selectorOne(MbbsMetaBean.class, sql, at);
                if(oB != null) {
                	oB.setOk(true);
                }
        }
        return oB;
    }	
	
	
	/**
	 * Meta 정보 신규 
	 * @param ob
	 * @return
	 * @throws SQLException
	 */
	public int insert(MdbDriver oDb, MbbsMetaBean ob) throws Exception
	{
	    int affected_rows = -1;
	    final String sql = "INSERT INTO MBBS_META (" 
	    	  + "bid, subject, cnt_t, cnt_w, grp, " 
	    	  + " cnt_d, nrows, last_index,   " 
	    	  + " fl_reply, fl_comment, fl_modify_date, number_attach, max_attach_size, " 
	    	  + " fl_board_type, total_attach_size, last_dt, is_use, category, " 
	    	  + " pgid, admin_usn, ext_usersel, fl_html, fl_category, " 
	    	  + " fl_waste, max_attach_limit, header, " 
	    	  + " file_header, file_footer" 
	    	  + "  " 
	    	  + " ) "
	    	  + " VALUES ("
	    	  + " :bid, :subject, :cnt_t, :cnt_w, :grp, " 
	    	  + " :cnt_d, :nrows, :last_index,   " 
	    	  + " :fl_reply, :fl_comment, :fl_modify_date, :number_attach, :max_attach_size, " 
	    	  + " :fl_board_type, :total_attach_size, :last_dt, :is_use, :category, " 
	    	  + " :pgid, :admin_usn, :ext_usersel, :fl_html, :fl_category, " 
	    	  + " :fl_waste, :max_attach_limit, :header, " 
	    	  + " :file_header, :file_footer " 
	    	  + "  "
	    	  + ") ";

	    try {
	    	affected_rows = oDb.executeUpdate(sql, ob);
	    } catch ( Throwable e) {
	    	logger.error(e.getMessage());
	    	throw new MBoardException("게시판 처리중  오류가 발생하였습니다.!!!",e);
	    }
	
	    return affected_rows;
	}

	/**
	 * Meta 정보 수정 
	 * @param metaBean
	 * @return
	 */
	public int update (MdbDriver oDb, Map param) throws MdbException {

	    final String sql = "UPDATE  MBBS_META "
	    	  + " SET " 
	    	  + " subject = :subject,  " 
	    	  + " grp = :grp,  " 
	    	  + " nrows = :nrows,  "
	    	  + " fl_reply = :fl_reply,  " 
	    	  + " fl_comment = :fl_comment,  " 
	    	  + " number_attach = :number_attach,  " 
	    	  + " max_attach_size = :max_attach_size,  " 
	    	  + " fl_board_type = :fl_board_type,  " 
	    	  + " is_use = :is_use,  " 
	    	  + " category = :category,  " 
	    	  + " pgid = :pgid,  " 
	    	  + " admin_usn = :admin_usn,  " 
	    	  + " ext_usersel = :ext_usersel,  " 
	    	  + " fl_html = :fl_html,  " 
	    	  + " fl_category = :fl_category,  " 
	    	  + " fl_waste = :fl_waste,  " 
	    	  + " max_attach_limit = :max_attach_limit,  " 
	    	  + " file_header = :file_header, "
	    	  + " file_footer = :file_footer, "
	    	  + " grant_list = :grant_list,"   +
	    	  "        grant_view = :grant_view,"   +
	    	  "        grant_write = :grant_write,"   +
	    	  "        grant_comment = :grant_comment,"   +
	    	  "        grant_reply = :grant_reply,"   +
	    	  "        grant_delete = :grant_delete,"   +
	    	  "        grant_html = :grant_html,"   +
	    	  "        grant_notice = :grant_notice,"   +
	    	  "        grant_secret = :grant_secret"     	  
	    	  + " WHERE  bid = :bid ";	    
        	
         return  oDb.executeUpdate(sql, param);	    
	}
	
	/**
	 * 하나의 게시판 삭제 (첨부파일 포함)
	 * @param oDb
	 * @param BID
	 * @param filePath /pds/board의 경로 
	 * @throws Exception
	 */
	public void delete (MdbDriver oDb, String BID, String filePath) 
	throws Exception {
	    boolean chk = false;
	    

	    if (BID == null || "".equals(BID)) {
	        throw new MBoardException("게시판 삭제중 오류가 발생하였습니다.!!!");
	    }
	    StringBuffer sPath = new StringBuffer(50);
		sPath.append(filePath);
		sPath.append(java.io.File.separator);
		sPath.append(BID);
		sPath.append(java.io.File.separator);

		// 모든 첨부파일 삭제
		java.io.File f = new java.io.File(sPath.toString());
		if(f.isDirectory()){
		    chk = dirDelete(sPath.toString() );
		    logger.debug("bbs dir is " + sPath.toString());
		} else {
		    chk = true;
		}
		if(chk) {
			String sql = "{call MI_BBS.DeleteBBS(?)}"; 
			CallableStatement stmt = null;
			try {
				stmt = oDb.prepareCall(sql);
				int i=0;
				
				stmt.setString(++i, BID);
				stmt.executeUpdate();
				oDb.commit();
			} catch (Exception e) {
	            throw new MBoardException("게시판 삭제중 오류가 발생하였습니다.!!!",e);
	        } finally {
	            if (stmt != null)  try { stmt.close(); stmt = null; } catch (Exception e) {};
	        }
		} else {
		    throw new MBoardException("게시판 삭제중 오류가 발생하였습니다.!!!" );
		}
	}
	   
    private static  boolean dirDelete(String path) {
		java.io.File f = new java.io.File(path);
		if (f.isDirectory()) {
			if (!f.delete()) {
				File[] list = f.listFiles();
				for (int i=0; i < list.length; i++) {
					dirDelete(list[i].getAbsolutePath());
				}
				return false;
			} else {
				return true;
			}
		} else {
		 	return f.delete();
		}
	}
    
    public List getBbsGrp(MdbDriver oDb){
        List listItems = null;
        String sql = " SELECT   grp, grp_desc, seq, site, layout, c_path" 
        	+ "     FROM MBBS_GRP" 
        	+ " ORDER BY seq, grp";

        try {
            listItems = oDb.selector(MbbsGroupBean.class, sql);
        } catch (Exception e) {
        }
        
        return listItems;
    }
    
    public List getBbsMetaBeans(MdbDriver oDb,  String v_grp, String v_srch) {
        List ListItem = null;
        String sql = "SELECT bid, subject,         " +
        "       (select count(c_index) from mbbs_data where bid = m.bid) as cnt_t,  " +
        "       (select count(c_id) from mbbs_comment where bid = m.bid) as cnt_w,  " +
        "       grp, nrows, last_index,  " +
        "       fl_auth, fl_write, fl_read, fl_list, fl_notice, fl_reply, fl_comment, fl_modify_date,  " +
        "       number_attach, max_attach_size, fl_board_type,  " +
        "       (select sum(file_size) from mbbs_attach where bid = m.bid) as total_attach_size,  " +
        "       pgid, is_use, admin_usn, " +
        "	   m.grant_list, m.grant_view, m.grant_write, m.grant_comment, m.grant_reply,  " +
        "       m.grant_delete, m.grant_html, m.grant_notice, m.grant_secret  " +
        " FROM MBBS_META  m   " +
        " WHERE DECODE (:vgrp, 'ALL', 1, DECODE (grp, :vgrp, 1, 0)) = 1 " +
        " AND DECODE (:vsrch, NULL, 1, DECODE (INSTR (subject, :vsrch), 0, 0, 1)) = 1 " +
        " ORDER BY subject, bid ";
        
        ArrayList at = new ArrayList();
        at.add(v_grp);
        at.add(v_grp);
        at.add(v_srch);
        at.add(v_srch);
        try {
            ListItem = oDb.selector(MbbsMetaBean.class, sql, at);
        } catch (Exception e) {
        }
        return ListItem;
    }
}
