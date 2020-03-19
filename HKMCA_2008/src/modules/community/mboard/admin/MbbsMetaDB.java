/*
 * Created on 2004. 9. 22.
 *
 */
package modules.community.mboard.admin;

import java.io.File;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import modules.community.mboard.beans.MbbsGroupBean;
import modules.community.mboard.beans.MbbsMetaBean;
import modules.community.mboard.exception.MBoardException;

public class MbbsMetaDB extends CommonDB {

    /**
     * 목록 가져오기
     *
     * @param oDb
     * @param navigator
     * @param param
     * @param sWhere
     * @return
     * @throws MdbException
     */
    public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
        List list = null;
        final String sql = "select *  " +
        "  from (SELECT bid, subject, grp, nrows, last_index, pgid, is_use, admin_usn,       " +
        "               fl_auth, fl_write, fl_read, fl_list, fl_notice, fl_reply, fl_comment, fl_modify_date,   " +
        "               number_attach, max_attach_size, fl_board_type,   " +
        "               m.grant_list, m.grant_view, m.grant_write, m.grant_comment, m.grant_reply,   " +
        "               m.grant_delete, m.grant_html, m.grant_notice, m.grant_secret, " +
        "               (select count(c_index) from mbbs_data where bid = m.bid) as cnt_t,   " +
        "               (select count(c_id) from mbbs_comment where bid = m.bid) as cnt_w,   " +
        "               (select sum(file_size) from mbbs_attach where bid = m.bid) as total_attach_size  " +
        "         FROM MBBS_META  m  " +
        ") x ";

        String sOrder = navigator.getOrderSql();

        oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
        list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
        navigator.setList(list);
        navigator.setTotalCount(getRecordCount(oDb, param, sWhere.toString()));
        navigator.sync();
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
        final String sql = " select count(*) " +
        "  from (SELECT bid, subject, grp, nrows, last_index, pgid, is_use, admin_usn,       " +
        "               fl_auth, fl_write, fl_read, fl_list, fl_notice, fl_reply, fl_comment, fl_modify_date,   " +
        "               number_attach, max_attach_size, fl_board_type,   " +
        "               m.grant_list, m.grant_view, m.grant_write, m.grant_comment, m.grant_reply,   " +
        "               m.grant_delete, m.grant_html, m.grant_notice, m.grant_secret, " +
        "               (select count(c_index) from mbbs_data where bid = m.bid) as cnt_t,   " +
        "               (select count(c_id) from mbbs_comment where bid = m.bid) as cnt_w,   " +
        "               (select sum(file_size) from mbbs_attach where bid = m.bid) as total_attach_size  " +
        "         FROM MBBS_META  m  " +
        "  ) x" + sWhere;

        return oDb.selectOneInt(sql, param);
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
        String sql = "SELECT m.bid, m.subject, m.cnt_t, m.cnt_w, m.grp, m.cnt_d, m.nrows, m.fl_write, " +
        "       m.fl_read, m.fl_list, m.fl_notice, m.fl_auth, m.fl_reply, m.fl_comment, m.fl_html, m.fl_category, " +
        "       m.fl_modify_date, m.number_attach, m.max_attach_size, m.fl_board_type, " +
        "       m.total_attach_size, m.last_dt, m.CATEGORY, pgid, m.admin_usn, " +
        "       m.is_use, m.fl_waste, m.max_attach_limit, g.site, " +
        "       m.header, m.leccode,m.club_id, " +
        "       m.file_header, m.file_footer, ext_usersel, " +
        "       m.grant_list, m.grant_view, m.grant_write,  " +
        "       m.grant_comment, m.grant_reply, m.grant_delete, m.grant_html, m.grant_notice,  " +
        "       m.grant_secret  " +
        "      FROM mbbs_meta m, mbbs_grp g " +
        "  WHERE m.bid = :bid    " +
        "  AND m.grp = g.grp ";
        return (Map) oDb.selectorOne(Map.class, sql, param);
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
    	final String sql = "MERGE INTO MBBS_META new " +
    	"      USING (SELECT :bid bid, :subject subject, :grp grp, :nrows nrows, :fl_reply fl_reply, " +
	    		" :fl_comment fl_comment, :number_attach number_attach, :max_attach_size max_attach_size, " +
	    		" :fl_board_type fl_board_type, :is_use is_use, :category category, :pgid pgid, :admin_usn admin_usn, " +
	    		" :ext_usersel ext_usersel, :fl_html fl_html, :fl_category fl_category, :fl_waste fl_waste, " +
	    		" :max_attach_limit max_attach_limit, :file_header file_header, :file_footer file_footer, " +
	    		" :grant_list grant_list, :grant_view grant_view, :grant_write grant_write, :grant_comment grant_comment,"   +
	    		" :grant_reply grant_reply, :grant_delete grant_delete, :grant_html grant_html,"   +
	    		" :grant_notice grant_notice, :grant_secret grant_secret " +
    	"               FROM DUAL) src " +
    	"         ON (src.bid = new.bid) " +
    	"    WHEN MATCHED THEN " +
    	"    UPDATE SET" +
		" 			subject = src.subject, " +
		" 			grp = src.grp, " +
		" 			nrows = src.nrows," +
		" 			fl_reply = src.fl_reply," +
		" 			fl_comment = src.fl_comment, " +
		" 			number_attach = src.number_attach, " +
		" 			max_attach_size = src.max_attach_size, " +
		" 			fl_board_type = src.fl_board_type, " +
		" 			is_use = src.is_use, " +
		" 			category = src.category, " +
		" 			pgid = src.pgid, " +
		" 			admin_usn = src.admin_usn, " +
		" 			ext_usersel = src.ext_usersel, " +
		" 			fl_html = src.fl_html, " +
		" 			fl_category = src.fl_category, " +
		" 			fl_waste = src.fl_waste, " +
		" 			max_attach_limit = src.max_attach_limit, " +
		" 			file_header = src.file_header, " +
		" 			file_footer = src.file_footer, " +
		" 			grant_list = src.grant_list,"  +
		" 			grant_view = src.grant_view,"   +
		" 			grant_write = src.grant_write,"   +
		" 			grant_comment = src.grant_comment,"   +
		" 			grant_reply = src.grant_reply,"   +
		" 			grant_delete = src.grant_delete,"   +
		" 			grant_html = src.grant_html,"   +
		" 			grant_notice = src.grant_notice,"   +
		" 			grant_secret = src.grant_secret" +
    	"    WHEN NOT MATCHED THEN " +
    	"	 INSERT ( " +
		"             bid, subject, grp,  cnt_t, cnt_w, cnt_d, nrows, fl_reply, fl_comment, number_attach," +
		"             fl_board_type, is_use, category, pgid, admin_usn, ext_usersel, fl_html, fl_category, " +
		"             fl_waste, max_attach_limit,file_header, file_footer " +
		"     ) VALUES ( " +
		"             src.bid, src.subject, src.grp, 0, 0, 0, src.nrows, src.fl_reply, src.fl_comment, src.number_attach," +
		"             src.fl_board_type, src.is_use, src.category, src.pgid, src.admin_usn, src.ext_usersel, src.fl_html, src.fl_category, " +
		"             src.fl_waste, src.max_attach_limit, src.file_header, src.file_footer " +
		" ) ";
    	
    	return oDb.executeUpdate(sql, param);
    		
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
		} else {
		    chk = true;
		}
		if(chk) {
			String sql = "{call MI_BBS.DeleteBBS (?)}"; 
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
