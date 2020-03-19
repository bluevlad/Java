/*
 * �ۼ��� ��¥: 2004-12-29
 *
 */
package miraenet.app.msg.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import maf.base.BaseHttpSession;
import maf.logger.Logging;
import maf.logger.Trace;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.drivers.MdbOCI8;
import maf.mdb.exceptions.MdbException;
import miraenet.app.msg.beans.MessageBean;
import miraenet.app.msg.beans.MsgAddress;
import miraenet.app.msg.beans.SimpleMessageBean;
import modules.common.beans.UsrMstBean;
import oracle.jdbc.OracleTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * ���� ������ Dao
 * 
 * �ۼ��� : �ڱ���
 * �ۼ��� ��¥ : 2005-01-25
 */
public class MessageDB {

	private static MessageDB instance;
	private  Log logger = LogFactory.getLog(MessageDB.class);

	private MessageDB() {
	}

	public static synchronized MessageDB getInstance() {
		if (instance == null)
			instance = new MessageDB();
		return instance;
	}

	/**
	 * ���� �޽��� ����Ʈ �������� 
	 * 
	 * @param usn_rcv
	 * @param numRows
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public List getMsgSndlist(MdbDriver oDb, String usn_snd, int numRows, int offset)
			throws MdbException {
	    List inputarray = new ArrayList();
		String sql =  " SELECT a.msgid, a.usn_snd, b.usn_rcv, b.title, b.reg_dt,b.nm_rcv,a.deleted"
					+ " FROM msg_snd a, msg_rcv b"
					+ " WHERE a.usn_snd = ?  AND a.usn_snd = b.usn_snd AND a.msgid = b.msgid AND a.deleted is null"
					+ " order by msgid desc";
		inputarray.add(usn_snd);
		//logger.debug("usn_snd===========>"+usn_snd);
		oDb.setLimit(numRows, offset);
		return  oDb.selector(SimpleMessageBean.class, sql, inputarray);
	}

	/**
	 * �����޼��� ����Ʈ ��������
	 * 
	 * @param usn_rcv
	 * @param numRows
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public List getMsgRcvlist(MdbDriver oDb, String usn_rcv, int numRows, int offset)
			throws MdbException {
		ArrayList inputarray = new ArrayList();

		String sql = " select b.msgid,a.usn_rcv,a.title, a.reg_dt, a.rcv_dt, a.nm_snd, a.deleted"
					+ " from msg_rcv a, msg_snd b"
					+ " where a.usn_rcv = ? AND a.usn_snd = b.usn_snd AND a.msgid = b.msgid AND a.deleted is null"
					+ " order by msgid desc";
 
		inputarray.add(usn_rcv + "");

		oDb.setLimit(numRows, offset);
		return  oDb.selector(SimpleMessageBean.class, sql, inputarray);
	}

	/*   *//**
			  * ���� �޼��� ����
			  * 
			  * @param usn_rcv
			  * @param msgid
			  * @return (MsgMstBean) list.get(0);
			  * @throws Exception
			  */
	/*
	 * public MsgMstBean getMsgRcvView(String usn_rcv, String msgid) throws
	 * Exception { String sql = null; String sql2 = null; List coll = null; Mdb
	 * odb = null; ArrayList inputarray = new ArrayList(); List list = null;
	 * 
	 * try {
	 * 
	 * sql =" SELECT b.msgid, a.usn_rcv, a.title, a.contents, a.reg_dt,
	 * a.nm_rcv, a.nm_snd" + " FROM msg_rcv a, msg_snd b" + " WHERE a.usn_rcv = ?" + "
	 * AND a.msgid = ?" + " AND a.usn_snd = b.usn_snd" + " AND a.msgid =
	 * b.msgid" ; inputarray.add(usn_rcv); inputarray.add(msgid); MsgMstBean
	 * msgbean = new MsgMstBean(); odb = new Mdb(); odb.setDebug(true); list =
	 * odb.selecter(msgbean, sql, inputarray);
	 * 
	 * if( list != null & list.size() > 0 ) { return (MsgMstBean) list.get(0); }
	 * else { return null; } } catch (Exception e) {
	 * logger.error(Trace.getStackTrace(e)); throw new Exception("DB����"); }
	 * finally { if (odb != null) try { odb.close(); } catch (Exception ex) {} } }
	 */
	
	/**
	 * �����޼��� ����
	 * 
	 * @param usn_rcv
	 * @param usn_snd,msgid
	 * @return (MsgMstBean) list.get(0);
	 * @throws Exception
	 */
	public SimpleMessageBean getMsgSndview(MdbDriver oDb, String usn_snd, String msgid)
			throws MdbException {
		ArrayList inputarray = new ArrayList();

		String sql = " SELECT a.msgid ,a.usn_snd, b.usn_rcv, b.title, b.contents, "
		    + " b.reg_dt, b.rcv_dt, b.nm_snd, b.nm_rcv"
			+ " FROM msg_snd a, msg_rcv b"
			+ " WHERE a.usn_snd = :usn_snd and a.msgid = :msgid "
			+ "		AND a.usn_snd = b.usn_snd AND a.msgid = b.msgid";
			inputarray.add(usn_snd);
			inputarray.add(msgid);
			return  (SimpleMessageBean) oDb.selectorOne(SimpleMessageBean.class, sql, inputarray);
	}

	/**
	 * �����޼���ī��Ʈ
	 * 
	 * @param usn_rcv
	 * @return count
	 * @throws Exception
	 */
	public int getRcvCount(MdbDriver oDb, String usn_rcv) throws MdbException {
		ArrayList inputarray = new ArrayList();
		String sql = "select count(*) from msg_rcv where usn_rcv = ? AND deleted is null";
		inputarray.add(usn_rcv);
		return  oDb.selectOneInt(sql, inputarray);
	}

	/**
	 * �����޼��� ī��Ʈ
	 * 
	 * @param usn_snd
	 * @return
	 * @throws Exception
	 */
	public int getSndCount(MdbDriver oDb, String usn_snd) throws MdbException {
		List inputarray = new ArrayList();
/*
		String sql = "SELECT count(*) FROM msg_snd a, msg_rcv b  "
				+ " WHERE a.usn_snd = ?  AND a.usn_snd = b.usn_snd "
				+ "		AND a.msgid = b.msgid  AND a.deleted is null";
*/
		String sql = "SELECT count(*) FROM msg_snd a  "
				+ " WHERE a.usn_snd = ?  AND a.deleted is null";				
		inputarray.add(usn_snd);

		return oDb.selectOneInt(sql, inputarray);
	}

	/**
	 * �޼��� ������
	 * 
	 * @param msg
	 * @throws Exception
	 */
	public void insert(MdbDriver oDb, SimpleMessageBean msg, String contents) throws Exception {

		ArrayList at = new ArrayList();


		try {
			//oDb.setDebug(true);
			msg.setMsgid( ( (MdbOCI8) oDb).getSequence("msg_mst_seq"));
			String sql1 = " INSERT INTO MSG_SND ( MSGID,  USN_SND) VALUES ( ?, ? )";
			at.add(msg.getMsgid());
			at.add(msg.getUsn_snd());
			oDb.executeUpdate(sql1, at);

			String sql2 = "INSERT INTO MSG_RCV ( " + "MSGID, USN_SND, USN_RCV,"
					+ "NM_SND, NM_RCV, TITLE, " + "CONTENTS)"
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
			at.clear();
			at.add(msg.getMsgid());
			at.add(msg.getUsn_snd());
			at.add(msg.getUsn_rcv());
			at.add(msg.getNm_snd());
			at.add(msg.getNm_rcv());
			at.add(msg.getTitle());
			at.add(contents);

			oDb.executeUpdate(sql2, at);
		} catch (Exception ex) {
			logger.error(Trace.getStackTrace(ex));
			throw new MdbException(ex.getMessage());
		}
		
	}

//	�������� ���� ������
	/**
	 * ������ ���� ���� ������ 
	 */
    public int sendMessage(MdbDriver oDb, MessageBean msg, BaseHttpSession session) 
    throws MdbException{
    	ArrayList at = new ArrayList();
    	ArrayList atfrom = new ArrayList();
//    	SimpleMessageBean smsg = new SimpleMessageBean();
    	int s = 0;
    	
        try {
        	boolean oldAutoCommit = oDb.getAutoCommit();
            if(msg != null ) {
                MsgAddress[] to = msg.getTo();
                if (to != null) {
                	oDb.setAutoCommit(false);
                	//logger.debug("length : " + to.length);
                	
	    			String sql1 = " INSERT INTO MSG_SND ( MSGID,  USN_SND) VALUES ( ?, ? )";
	    			String sql2 = "INSERT INTO MSG_RCV ( " 
    					+"MSGID, USN_SND, USN_RCV,"
    					+ "NM_SND, NM_RCV, TITLE, " + "CONTENTS)"
    					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?)";

	    			for(int i =0; i<to.length; i++ ) {

		            	try{
			            	String msgid = ( (MdbOCI8) oDb).getSequence("msg_mst_seq");
			    			atfrom.clear();
			    			atfrom.add(msgid);
			    			atfrom.add(msg.getFrom().getAddress());
			    			oDb.executeUpdate(sql1, atfrom);
	
			    			at.clear();
			    			at.add(msgid);
			    			at.add(msg.getFrom().getAddress());	// ������ ��� usn
			    			at.add(to[i].getAddress());	// �޴»�� usn
			    			at.add(msg.getFrom().getNm());
			    			at.add(to[i].getNm());	//  �޴»�� �̸�
			    			at.add(msg.getSubject());
			    			at.add(msg.getContents());
	
			    			oDb.executeUpdate(sql2, at);
			    			s++;
		            	} catch (MdbException e) {
		            	    
		            	}

		            }
		            oDb.commit();
		            oDb.setAutoCommit(oldAutoCommit);
		            //logger.debug(to.length + " Memo message's sent!!!");

                } else {
                    logger.debug("receiver is null !!!");
                } 
            }else {
                logger.debug("msg is null !!!");
            }
        } catch (Exception e ) {
            throw new MdbException(e.getMessage(), e);
        } finally {
            if (oDb != null)try {oDb.close();} catch (Exception ex) {}

        }
        return s;
    }
	/**
	 * �����޼��� �����
	 * 
	 * @param msgid
	 * @param usn_rcv
	 * @throws Exception
	 */
	public void rcvdelete(MdbDriver oDb, String msgid, String usn_rcv) throws Exception {

		CallableStatement cstmt = null;

		//SELECT msgid, usn_snd, usn_rcv, nm_snd, nm_rcv, title, CONTENTS,
		// reg_dt, rcv_dt, deleted
		String sql = "{call MI_MSG.delete_rcvmsg (:RCNT, :MSGID, :USN_RCV )}";
		try {
			cstmt = oDb.prepareCall(sql);

			int i = 0;
			cstmt.registerOutParameter(++i, OracleTypes.VARCHAR);
			cstmt.setString(++i, msgid);
			cstmt.setString(++i, usn_rcv);
			cstmt.execute();

			cstmt.close();
		} catch (Exception e) {
			logger.error(Trace.getStackTrace(e));
			throw new Exception("DB����");
		} finally {
			if (cstmt != null)try {cstmt.close();} catch (SQLException ex) {}
		}
		//return ob;
	}

	/**
	 * �����޼��� �����
	 * 
	 * @param msgid
	 * @param usn_snd
	 * @throws Exception
	 */
	public void snddelete(MdbDriver oDb, String msgid, String usn_snd) throws Exception {
		CallableStatement cstmt = null;
		
		String sql = "{call MI_MSG.delete_sndmsg (:RCNT, :MSGID, :USN_SND )}";
		try {
			cstmt = oDb.prepareCall(sql);

			int i = 0;
			cstmt.registerOutParameter(++i, OracleTypes.VARCHAR);
			cstmt.setString(++i, msgid);
			cstmt.setString(++i, usn_snd);
			cstmt.execute();
			cstmt.close();
		} catch (Exception e) {
			logger.error(Trace.getStackTrace(e));
			throw new MdbException("DB����");
		} finally {
			if (cstmt != null)try {cstmt.close();} catch (SQLException ex) {}
		}
	}

	/**
	 * �����޼��� ����
	 * 
	 * @param usn_rcv
	 * @param msgid
	 * @return
	 * @throws Exception
	 */
	public SimpleMessageBean getMsgRcvView(MdbDriver oDb, String usn_rcv, String msgid)
			throws MdbException {

		CallableStatement cstmt = null;
		ResultSet rs = null;
		SimpleMessageBean ob = null;

		//SELECT msgid, usn_snd, usn_rcv, nm_snd, nm_rcv, title, CONTENTS,
		// reg_dt, rcv_dt, deleted
		String sql = "{call MI_MSG.READMSG ( :CUR, :MSGID, :USN_RCV )}";
		try {
			cstmt = oDb.prepareCall(sql);

			int i = 0;
			cstmt.registerOutParameter(++i, OracleTypes.CURSOR);
			cstmt.setString(++i, msgid);
			cstmt.setString(++i, usn_rcv);
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(1);
//			if (rs.next()) {				
//				ob = new MessageBean();
//				ob.setMsgid(rs.getString("msgid"));
//				ob.setUsn_rcv(rs.getString("usn_rcv"));
//				ob.setTitle(rs.getString("title"));
//				ob.setContents(rs.getString("contents"));
//				ob.setReg_dt(rs.getDATE("reg_dt").timestampValue());
//				ob.setRcv_dt(rs.getDATE("rcv_dt").timestampValue());
//				ob.setNm_rcv(rs.getString("nm_rcv"));
//				ob.setNm_snd(rs.getString("nm_snd"));
//				ob.setUsn_snd(rs.getString("usn_snd"));
//			}
			//MdbSelecter sel = new MdbSelecter(MessageBean.class);
			//ob = (MessageBean) sel.tokenResultSetOne(rs);
			ob = (SimpleMessageBean) oDb.selectorOne(SimpleMessageBean.class, rs);
			rs.close();
			cstmt.close();
		} catch (SQLException e) {
			Logging.trace(e);
			throw new MdbException("DB����");
		} finally {
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
			if (cstmt != null)try {cstmt.close();} catch (SQLException ex) {}
		}
		return ob;
	}

	/**
	 * ������ ��� �˻��ϱ�
	 * 
	 * @param selname
	 * @param nm
	 * @return
	 * @throws Exception
	 */
	public List getMsgSearchList(MdbDriver oDb, String selname, String nm) throws MdbException {
		String sql = null;

		ArrayList inputarray = new ArrayList();

		if (selname.equals("1")) {
			//sql = " select usn, nm from com.vusrmst where instr(nm, ?) >
			// 0 ";
			sql = "SELECT USN,NM,userid  FROM MST_USER WHERE NM = ?";
			inputarray.add(nm);
		} else {
			sql = "";//�а� ���̺�� ���ΰɾ����
		}
		return oDb.selector(UsrMstBean.class, sql, inputarray);

	}

}