/*
 * Created on 2004. 9. 22.
 *
 */
package modules.community.mboard.admin;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class MbbsGrpDB extends CommonDB {

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
    public static List getList(MdbDriver oDb, Map param) throws MdbException {

    	final String sql = "select * from mbbs_grp";

    	return oDb.selector(Map.class, sql, param);
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
        "       g.layout, g.c_path, m.header, m.leccode,m.club_id, " +
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
	 * Meta 정보 신규 
	 * @param ob
	 * @return
	 * @throws SQLException
	 */
	public static int insert(MdbDriver oDb, Map param) throws Exception
	{

	    final String sql = "INSERT INTO mbbs_grp ( " +
	    		"grp, grp_desc, seq, site " +
	    		" ) VALUES ( " +
	    		" :grp, :grp_desc, :seq, :site " +
	    		" ) ";

        return  oDb.executeUpdate(sql, param);
	}

	/**
	 * Meta 정보 수정 
	 * @param metaBean
	 * @return
	 */
	public static int update(MdbDriver oDb, Map param) throws MdbException {

	    final String sql = "UPDATE mbbs_grp SET " +
	    		" grp_desc = :grp_desc, " +
	    		" seq = :seq, " +
	    		" site = :site " +
	    		" WHERE grp = :grp ";	    
        	
         return  oDb.executeUpdate(sql, param);
	}
	
    /**
     * 하나의 레코드를 삭제 한다.
     *
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int delete(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "DELETE FROM mbbs_grp";

        return oDb.executeUpdate(sql, param);
    }
    
}