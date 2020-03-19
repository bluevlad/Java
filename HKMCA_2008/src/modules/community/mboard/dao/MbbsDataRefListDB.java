/*
 * 작성자 : bluevlad
 * Created on 2004. 10. 6.
 *
 */
package modules.community.mboard.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modules.community.mboard.MBoardManager;
import modules.community.mboard.beans.MbbsDataRefListBean;
import modules.community.mboard.exception.MBoardException;
import oracle.jdbc.OracleTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * 게시물 관련글 가져 오는 DAO
 * @author bluevlad
 * Create by 2004. 10. 6.
 * 
 */
public class MbbsDataRefListDB {
    private  Log logger = LogFactory.getLog(this.getClass());
    
    private static MbbsDataRefListDB instance = new MbbsDataRefListDB();
    
    public static synchronized MbbsDataRefListDB getInstance() {
        return instance;
    }
    
    private MbbsDataRefListDB() {
        
    }
    
    /**
     * 게시물의 앞뒤 각 ref_cnt 만큼 가져온다
     * @param oMbbs
     * @param c_index
     * @param ref_cnt
     * @return
     */
    public MbbsDataRefListBean[] getList(MBoardManager oMbbs, String c_index, int ref_cnt) throws Exception {

		CallableStatement		stmt	= null;
		ResultSet				rs		= null;
		List 					list 	= null; //new ArrayList();       
		
		String sql = "{call MI_BBS.GetRefList(:v_cur ,:v_bid, :v_index, :v_status, :ref_cnt )}";  
		try{
			stmt = oMbbs.getODB().prepareCall(sql);
			int i = 0;
			stmt.registerOutParameter(++i,OracleTypes.CURSOR);
		    stmt.setString(++i,oMbbs.getBid());
		    stmt.setString(++i,c_index);
		    stmt.setString(++i,"T");
		    stmt.setInt(++i,ref_cnt);
		    stmt.execute();
		    rs = (ResultSet)stmt.getObject(1);
		    list = oMbbs.getODB().selector(MbbsDataRefListBean.class, rs);
//			while(rs.next()) {
//			    MbbsDataRefListBean oB = new MbbsDataRefListBean();
//			    // c_index, c_subject, WNAME, USN, C_DATE, C_CCNT, C_level, C_IMAGE,C_VISIT, 'N' as "PrevNext"
//			    oB.setC_index( rs.getString("c_index"));	// c_index
//			    oB.setC_subject( rs.getString("c_subject"));			// comment id
//			    oB.setUsn( rs.getString("USN"));			// comment id
//			    oB.setWname( rs.getString("wname"));	// 필명
//			    oB.setC_date(MDate.getDate( rs.getTimestamp("c_date")));		// 등록일시	    
//			    oB.setC_ccnt( rs.getInt("C_CCNT"));	// 댓글수
//			    oB.setC_level( rs.getInt("C_level"));	// 댓글수
//			    oB.setC_status( rs.getString("C_STATUS"));	// 글의 상태 
//			    oB.setC_visit( rs.getInt("C_VISIT"));		// 대표이미지 파일 명
//			    oB.setC_image( rs.getString("C_IMAGE"));		// 대표이미지 파일 명
//			    oB.setPrevnext( rs.getString("PrevNext"));	// 이전글 다음글 구분 
//			    
//			    list.add(oB);
//			}
			//logger.debug("Size : " + list.size());
	    } catch (SQLException e) {
	        logger.error(maf.logger.Trace.getStackTrace(e));
	        throw new MBoardException(e);
	    } finally{
	        if (rs != null)try {rs.close();} catch (SQLException ex) {}
	        if (stmt != null)try {stmt.close();} catch (SQLException ex) {}
	    }
		
		if(list != null) {
		    return (MbbsDataRefListBean[]) list.toArray(new MbbsDataRefListBean[0]);
		} else {
		    return null;
		}
    }
}
