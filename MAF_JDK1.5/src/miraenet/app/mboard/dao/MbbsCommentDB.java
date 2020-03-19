/*
 * �ۼ��� : �����
 * Created on 2004. 10. 4.
 *
 */
package miraenet.app.mboard.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.List;

import miraenet.app.mboard.MBoardManager;
import miraenet.app.mboard.beans.MbbsCommentBean;
import miraenet.app.mboard.exception.MBoardException;
import oracle.jdbc.OracleTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author �����(goindole@miraenet.com)
 * Create by 2004. 10. 4.
 * 
 */
public class MbbsCommentDB {
    private  Log logger = LogFactory.getLog(this.getClass());

    
    
    /**
     * �Խ��� ID�� Ŀ��Ʈ ������� ���� ����
     * @param oMbbs
     * @param c_index
     * @return
     * @param oMbbs
     * @param c_index
     * @return
     * @throws Exception
     */
    public static MbbsCommentBean[] getList(
            	MBoardManager oMbbs,
            	String c_index
            	) 
    throws Exception {
        
        CallableStatement stmt	= null;
        ResultSet		rs		= null;
        List 				list 	= 	null;   
        
        String sql = "{call MI_BBS.Cmt_GetList(:v_cur, :v_bid, :v_index )}";
//        String sql = "{call MI_BBS.Cmt_GetList(?,?,? )}";
        
		try{

			stmt = oMbbs.getODB().prepareCall(sql);

			stmt.registerOutParameter(1,OracleTypes.CURSOR);
		    stmt.setString(2,oMbbs.getBid());
		    stmt.setString(3,c_index);
		    stmt.execute();
		    rs = (ResultSet)stmt.getObject(1);
		    list = oMbbs.getODB().selector(MbbsCommentBean.class, rs);
//			while(rs.next()) {
//			    MbbsCommentBean oB = new MbbsCommentBean();
//			    oB.setC_index( rs.getString("c_index"));	// c_index
//			    oB.setC_id( rs.getInt("c_id"));			// comment id
//			    oB.setUsn( rs.getString("usn"));			// comment id
//			    oB.setWname( rs.getString("wname"));	// �ʸ�
//			    oB.setC_date(MDate.getDate( rs.getTimestamp("c_date")));		// ����Ͻ�	    
//			    oB.setC_passwd( rs.getString("c_passwd"));	// ���� ��ȣ
//			    oB.setC_memo( rs.getString("c_memo"));		// ����
//			    oB.setC_ip( rs.getString("c_ip"));			// �۾��� IP
//			    oB.setC_opnion(rs.getString("C_OPNION"));
//			    list.add(oB);
//			}
		} catch(Exception ex){
	        throw new MBoardException("�ڸ�Ʈ ����� ����������  ������ �߻��Ͽ����ϴϴ�.", ex);
		} finally {
			if (rs != null) try { rs.close(); rs = null; } catch (Exception e) {}
			if (stmt != null) try { stmt.close(); stmt = null; } catch (Exception e) {}
		} 
		if(list != null) {
		    return (MbbsCommentBean[]) list.toArray(new MbbsCommentBean[0]);
		} else {
		    return null;
		}

	}

    /**
     * 	Ŀ��Ʈ �߰� 
     * @param oMbbs
     * @param item
     * @throws Exception
     */
    public static void addItem( 
            			MBoardManager oMbbs,
            			MbbsCommentBean item) 
    throws Exception 
    {
        CallableStatement			stmt	= null;
        final String sql = "{call MI_BBS.Cmt_WriteData( :v_BID," +
					":v_INDEX, " + 
					":v_usn, :v_wname, :v_passwd, " +
					":v_memo, :v_ip, :v_OPNION )} " ;
		try{
	        int i=1;
			stmt = oMbbs.getODB().prepareCall(sql);
	        stmt.setString(i++, oMbbs.getBid());
	        stmt.setString(i++, item.getC_index());
	        stmt.setString(i++, item.getUsn());
	        stmt.setString(i++, item.getWname());
	        stmt.setString(i++, item.getC_passwd());
	        stmt.setString(i++, item.getC_memo());
	        stmt.setString(i++, item.getC_ip());
	        stmt.setString(i++, item.getC_opnion());
			stmt.execute();
		} catch(Exception ex){
	        throw new MBoardException("�ڸ�Ʈ ��� �� ������ �߻��Ͽ����ϴϴ�.", ex);
		} finally {
			if (stmt != null) try { stmt.close(); stmt = null; } catch (Exception e) {}
		}
    }
    
    /**
     * oMbbs�� Ŀ��Ʈ�� ���� 
     * @param oMbbs
     * @param c_index
     * @param c_id
     * @return
     */
    public static void delItem( 
			MBoardManager oMbbs,
			String c_index,
			String c_id) 
	throws Exception 
	{
        CallableStatement	stmt = null;
		final String sql = "{call MI_BBS.Cmt_Delete( :v_BID," +
						":v_INDEX, :v_cid )} " ;
		try{
	        int i=1;
			stmt = oMbbs.getODB().prepareCall(sql);
	        stmt.setString(i++, oMbbs.getBid());
	        stmt.setString(i++, c_index);
	        stmt.setString(i++, c_id);
			stmt.execute();
		} catch(Exception ex){
	        throw new MBoardException("�ڸ�Ʈ ���� �� ������ �߻��Ͽ����ϴϴ�.",ex);
		} finally {
			if (stmt != null) try { stmt.close(); stmt = null; } catch (Exception e) {}
		}
    }
}
