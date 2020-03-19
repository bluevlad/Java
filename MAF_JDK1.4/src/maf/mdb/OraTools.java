package maf.mdb;

import java.util.HashMap;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.drivers.MdbOCI8;
import maf.web.multipart.UploadedFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OraTools {

//    public static boolean updateClob(Connection conn, String table, String column, MdbParameters p)
//    throws SQLException {
//        boolean chk = false;
//
//        String sql = "Select " +  column + " from " + table + " where ";
//        PreparedStatement stmt = null;
//        OracleResultSet rs = null;
//        try {
//            conn.setAutoCommit(false);
//            
//            for(int i = 0 ; i< p.getSize() ; i++) {
//                sql += p.getColumn(i) + " = ? ";
//                if(i < p.getSize()) {
//                    sql += " and ";
//                }
//            }
//            sql += "for update";
//            stmt = conn.prepareStatement(sql);
//            for(int i = 0 ; i< p.getSize() ; i++) {
//               stmt.setObject(i, p.getValue(i));
//            }
//            rs = (OracleResultSet) stmt.executeQuery();
//
//        } finally {
//            if (stmt != null)try {stmt.close(); stmt =null;} catch (SQLException ex) {}
//        }
//        conn.setAutoCommit(true);
//        return chk;
//    }
//    
//    public PreparedStatement mPrepareStatement(Connection conn, String sql, MdbParameters p) 
//    throws SQLException{
//        PreparedStatement stmt = null;
//        try {
//            if(p != null && p.getSize() > 0 ) {
//                sql += " where ";
//	            for(int i = 0 ; i< p.getSize() ; i++) {
//	                sql += p.getColumn(i) + " = ? ";
//	                if(i < p.getSize()) {
//	                    sql += " and ";
//	                }
//	            }
//	            
//	            // 값들을 할당 한다.
//	            stmt = conn.prepareStatement(sql);
//	            for(int i = 0 ; i< p.getSize() ; i++) {
//	               stmt.setObject(i, p.getValue(i));
//	            }
//            }
//        } catch ( SQLException e) {
//            if (stmt != null) try {stmt.close(); stmt =null;} catch (SQLException ex) {}
//            throw new SQLException(e.getMessage(), e.getSQLState(), e.getErrorCode());
//        }
//        return stmt;
//    }
    
    /**
     * BLOB 등록 또는 Update 
     * @param oDb
     * @param blob
     * @param src_table
     * @param src_id
     * @param sessionBean
     * @return
     */
	public static boolean  UpdateMstBlob(MdbDriver oDb, UploadedFile blob, String src_table, String src_id) {
    	boolean chk = false;
    	
    	final String sqlChk = "select count(*) cnt from MST_BLOB " +
			" where src_table = :src_table and src_id = :src_id";
    	final String sqlUpdate = "Update MST_BLOB SET Update_dt = sysdate, " +
    			" Update_id = :update_id," +
    			" file_name = :file_name," +
    			" file_length = :file_length," +
    			" mime_type = :mime_type "+
			" where src_table = :src_table and src_id = :src_id ";
    	final String sqlInsert = "insert into MST_BLOB (BLOB_ID, SRC_TABLE, SRC_ID)" +
    			"values (SEQ_MST_BLOB.NEXTVAL, :src_table, :src_id) ";
    	try {
	    	oDb.setAutoCommit(false);
	    	//oDb.setDebug(true);
	    	Map param = new HashMap();
	    	param.put("src_id", src_id);
	    	param.put("src_table", src_table);
	    	
	    	int cnt = oDb.selectOneInt(sqlChk, param);
	    	
	    	if(cnt < 1) {
	    		oDb.executeUpdate(sqlInsert, param);
	    	}
	    	
	    	MdbParameters mParam = new MdbParameters();
	        mParam.add("src_id", src_id);
	    	mParam.add("src_table", src_table);
	        chk = ((MdbOCI8) oDb).updateBLOB("MST_BLOB", "BLOB_DATA", blob.getFileInputStream(), mParam);
	        

	        param.put("file_name", blob.getOriginalFileName());
	        param.put("file_length", blob.getFileSize()+"");
	        param.put("mime_type", blob.getContentType());

	        oDb.executeUpdate(sqlUpdate, param);
	        
	        oDb.commit();
    	} catch (Throwable e) {
    		oDb.rollback();
    		Log logger = LogFactory.getLog(OraTools.class);
    		logger.error(e.getMessage());
    	}
    	return chk;
    }
    /**
     * BLOB 삭제
     * @param oDb
     * @param src_table
     * @param src_id
     * @return
     */
	public static boolean DeleteMstBlob(MdbDriver oDb, String src_table, String src_id) {
    	boolean chk = false;
    	
    	final String sql = "delete from MST_BLOB " +
			" where src_table = :src_table and src_id = :src_id";
    	try {

	    	Map param = new HashMap();
	    	param.put("src_id", src_id);
	    	param.put("src_table", src_table);

    		int cnt = oDb.executeUpdate(sql, param);
    		chk = (cnt > 0 )?true:false;
    	} catch (Throwable e) {
    		Log logger = LogFactory.getLog(OraTools.class);
    		logger.error(e.getMessage());
    	}
    	return chk;
    }
}
