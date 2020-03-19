/*
 * 작성자 : bluevlad
 * Created on 2004. 10. 7.
 *
 */
package modules.community.mboard.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import maf.util.FileUtils;
import modules.community.mboard.MBoardManager;
import modules.community.mboard.beans.MbbsAttachBean;
import modules.community.mboard.exception.MBoardException;



/**
 * @author bluevlad
 * Create by 2004. 10. 7.
 * 
 */
public class MbbsAttachDB {


    //static String ThumbNailPath = "thumb";
    static FileUtils fu = new FileUtils();
    

    
	/**
	 * 
	 * @param oMbbs
	 * @param c_index
	 * @param attList : MbbsAttachBean[]
	 * @return
	 * @throws Exception
	 */
    public static boolean AddItems(
            MBoardManager oMbbs,
            String c_index,
            MbbsAttachBean[] attList) throws Exception{
        
        // 첨부파일 저장용
        final String insertSql = " INSERT INTO MBBS_ATTACH ("   +
    			    "    BID, C_INDEX, SEQ, THUMBNAIL, FILE_SIZE, CONTENT_TYPE, "   +
    			    "    REAL_FILENAME, ORI_FILENAME) "   +
    			    " VALUES ( :BID , :C_INDEX, S_MBBS_ATTACH_SEQ.NEXTVAL ,"   +
    			    "     :THUMBNAIL, :FILE_SIZE, :CONTENT_TYPE,  "   +
    			    "     :REAL_FILENAME, :ORI_FILENAME)"  ;        
        boolean chk = false;
        int affected_rows = 0;
        PreparedStatement stmt = null;
        try {
            stmt = oMbbs.getODB().prepareStatement(insertSql);
            if (attList != null) {
                for (int i = 0; i < attList.length; i++) {
                    int j = 1;
                    MbbsAttachBean tb = attList[i];
                    stmt.setString(j++, oMbbs.getBid());
                    stmt.setString(j++, c_index);
                    stmt.setString(j++, tb.getThumbnail());
                    stmt.setLong(j++, tb.getFile_size() / 1000);
                    stmt.setString(j++, tb.getContent_type());
                    stmt.setString(j++, tb.getReal_filename());
                    stmt.setString(j++, tb.getOri_filename());
                    affected_rows = stmt.executeUpdate();
                    if (affected_rows == 0) {
                        chk = false;
                        break;
                    }
                    chk = true;
                }
            }
        } catch (Exception e) {
            throw new MBoardException("첨부파일 목록을 저장 중 오류가 발생하였습니다(DB).!!!", e);
        } finally {
            if (stmt != null)try {stmt.close();} catch (Exception e) {}
        }
	    return chk;	        

    }
    
    /**
     * 첨부파일 삭제 
     * @param oMbbs
     * @param c_index
     * @param uFile
     * @param MakeThumb
     * @return
     */
    public static List DelItems(
            MBoardManager oMbbs,
            String c_index,
            String[] attList) 
    throws MBoardException {
        List delFIles = new ArrayList();
        List at = new ArrayList();
        // 첨부파일 저장용
        final String deleteSql = " DELETE FROM MBBS_ATTACH " +
		" WHERE BID = :BID AND C_INDEX = :C_INDEX AND SEQ=:SEQ "  ;        
        final String sql = " SELECT REAL_FILENAME  FROM MBBS_ATTACH " +
		" WHERE BID = :BID AND C_INDEX = :C_INDEX AND SEQ=:SEQ "  ;        
        
        try{
            for(int i = 0; i < attList.length; i++) {
                at.clear();
                at.add(oMbbs.getBid());
                at.add(c_index);
                at.add(attList[i]);
                String rfilename = oMbbs.getODB().selectOne(sql, at);
                delFIles.add(rfilename);
			    oMbbs.getODB().executeUpdate(deleteSql, at);
            }
	    } catch (Exception e) {

            throw new MBoardException("첨부파일 목록을 삭제 중 오류가 발생하였습니다(DB).!!!", e);
	    }
	    return delFIles;	        

    }    
    
    /**
     * c_index 게시물의 첨부파일 리스트를 가져온다.
     * @param oMbbs
     * @param c_index
     * @return
     */
    public static MbbsAttachBean[] getList( 
            			MBoardManager oMbbs,
            			String c_index)
    throws Exception {
        
        final String sql = " SELECT BID, C_INDEX, SEQ, "   +
		        "    THUMBNAIL, FILE_SIZE, CONTENT_TYPE , "   +
		        "    REAL_FILENAME, ORI_FILENAME, IS_MAJOR "   +
		        " FROM MBBS_ATTACH"   +
		        " WHERE  BID=:BID AND C_INDEX = :C_INDEX "  ;
        
        List list = null;
        List ht = new ArrayList();
        ht.add( oMbbs.getBid());
        ht.add( c_index);
        try {
            list = oMbbs.getODB().selector(MbbsAttachBean.class, sql, ht);
        } catch (Exception e) {
            throw new MBoardException("첨부파일 목록을 읽어 오는 중 오류가 발생하였습니다(DB).!!!", e);
        }
		if(list != null) {
		    return (MbbsAttachBean[]) list.toArray(new MbbsAttachBean[0]);
		} else {
		    return null;
		}
    }

    
    
}