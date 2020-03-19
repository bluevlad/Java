/*
 * Created on 2006. 08. 21
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.pager;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.base.BaseHttpSession;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.drivers.MdbOCI8;
import maf.mdb.exceptions.MdbException;
import miraenet.app.msg.beans.MessageBean;
import miraenet.app.msg.beans.MsgAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PagerDB {

	private static Log logger = LogFactory.getLog(PagerDB.class);
	
	
	/**
	 * 받은쪽지 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public static void getListRcv(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
		
		List list = null;

		final String sql = " SELECT   *"   +
			"     FROM (SELECT mu.userid msg_snd_userid, mu.nm msg_snd_nm, a.*"   +
			"             FROM maf_pager a, v_maf_user mu"   +
			"            WHERE a.msg_rcv_isdel = 'N' AND a.msg_snd_usn = mu.usn) p"  ;
		
		String sOrder = navigator.getOrderSql();

		oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		oDb.setDebug(true);
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		navigator.setTotalCount(PagerDB.getRecordCountRcv(oDb, param, sWhere.toString()));
		navigator.sync();
		 
	}
	
	/**
	 * 받은 쪽지 레코드 카운트 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	private static long getRecordCountRcv(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final  String sql = " SELECT   count(*) cnt "   +
		"     FROM (SELECT mu.userid msg_snd_userid, mu.nm msg_snd_nm, a.*"   +
		"             FROM maf_pager a, v_maf_user mu"   +
		"            WHERE a.msg_rcv_isdel = 'N' AND a.msg_snd_usn = mu.usn) p"   + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	/**
	 * 보낸쪽지 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public static void getListSnd(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
		
		List list = null;

		final String sql = " SELECT   *"   +
			"     FROM (SELECT mu.userid msg_rcv_userid, mu.nm msg_rcv_nm, a.*"   +
			"             FROM maf_pager a, v_maf_user mu"   +
			"            WHERE a.msg_snd_isdel = 'N' AND a.msg_rcv_usn = mu.usn) p"  ;
		
		String sOrder = navigator.getOrderSql();

		oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		oDb.setDebug(true);
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		navigator.setTotalCount(PagerDB.getRecordCountSnd(oDb, param, sWhere.toString()));
		navigator.sync();
		
	}
	
	/**
	 * 보낸 쪽지 레코드 카운트 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	private static long getRecordCountSnd(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final  String sql = " SELECT   count(*) cnt "   +
		"     FROM (SELECT mu.userid msg_rcv_userid, mu.nm msg_rcv_nm, a.*"   +
		"             FROM maf_pager a, v_maf_user mu"   +
		"            WHERE a.msg_snd_isdel = 'N' AND a.msg_rcv_usn = mu.usn) p"   + sWhere;
		return oDb.selectOneInt(sql, param);
	}	
	/**
	 * 하나의 레코드 읽어 오기
	 * !!! 보낸사람과 받는사람외에는 못읽어 온다.
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param, String usn) throws MdbException {
		final String sql = " SELECT mu_s.nm msg_snd_nm, mu_s.userid msg_snd_userid,"   +
			" 	   mu_r.nm msg_rcv_nm, mu_r.userid msg_rcv_userid,"   +
			" 	   p.*"   +
			"   FROM maf_pager p,"   +
			"   	   v_maf_user mu_r, v_maf_user mu_s"   +
			"  WHERE p.MSG_RCV_USN = mu_r.usn "   +
			"  	   and p.MSG_SND_USN = mu_s.usn "   +
			" 	   and p.msg_no = :msg_no AND (p.msg_rcv_usn = :owner OR p.msg_snd_usn = :owner)"  ;
		final String updatesql = "update MAF_PAGER set MSG_READ_DT = nvl(MSG_READ_DT,sysdate ) " 
            + "where   msg_no = :msg_no  ";
		param.put("owner", usn);
		
		Map rec = (Map) oDb.selectorOne(Map.class, sql, param);
		
		if(usn.equals(rec.get("msg_rcv_usn"))) {
			oDb.executeUpdate(updatesql,param);	
		}
		
		return rec ;
	}

	
	/**
	 * 하나의 레코드를 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int updateOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update MAF_PAGER set "   +
			"msg_no = :msg_no  ,  " +
			"msg_rc_id = :msg_rc_id  ,  " +
			"msg_rc_nm = :msg_rc_nm  ,  " +
			"msg_snd_id = :msg_snd_id  ,  " +
			"msg_snd_nm = :msg_snd_nm  ,  " +
			"msg_title = :msg_title  ,  " +
			"msg_info = :msg_info  ,  " +
			"file_name = :file_name  ,  " +
			"msg_dt = :msg_dt  ,  " +
			"msg_read_dt = :msg_read_dt  ,  " +
			"msg_rc_isdel = :msg_rc_isdel  ,  " +
			"msg_snd_isdel = :msg_snd_isdel  ,  " +
			"update_dt = :update_dt  ,  " +
			"update_id = :update_id   " +
			
	            "where  msg_no = :msg_no  ";
		return oDb.executeUpdate(sql, param);
	}
	
//	/**
//	 * 하나의 쪽지를 보낸다.
//	 * 
//	 * @param oDb
//	 * @param param
//	 * @return
//	 * @throws MdbException
//	 */
//	public static int insertOne(MdbDriver oDb, Map param) throws MdbException {
//		final String sql = " INSERT INTO MAF_PAGER"   +
//		"    (  msg_rcv_usn, msg_snd_usn, msg_title, msg_info, file_name, msg_no_p)"   +
//		"  VALUES"   +
//		"    ( :msg_rcv_usn,  :msg_snd_usn, :msg_title, :msg_info, :file_name, :msg_no_p)"  ;
//		
//		return oDb.executeUpdate(sql, param);
//	}
	/**
	 * 쪽지를 발송 한다.
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int sendMessage(MdbDriver oDb, MessageBean msgs, BaseHttpSession session) throws MdbException {
		final String sql = " INSERT INTO MAF_PAGER"   +
		"    (  msg_rcv_usn, msg_snd_usn, msg_title, msg_info)"   +
		"  VALUES"   +
		"    ( :msg_rcv_usn,  :msg_snd_usn, :msg_title, :msg_info)"  ;
		PreparedStatement stmt = null;
    	int cnt = 0;
    	
        try {
        	boolean oldAutoCommit = oDb.getAutoCommit();
            if(msgs != null ) {
                MsgAddress[] to = msgs.getTo();
                if (to != null) {
                	oDb.setAutoCommit(false);

                	
                	stmt = oDb.prepareStatement(sql);
	    			for(int i =0; i<to.length; i++ ) {
	    				int col = 1;
		            	stmt.setString(col++, to[i].getUsn());
		            	stmt.setString(col++, msgs.getFrom().getUsn());
		            	stmt.setString(col++, msgs.getSubject());
		            	stmt.setString(col++, msgs.getContents());
		            	cnt+= stmt.executeUpdate();
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
        	if (stmt != null) {
        		try {stmt.close();} catch (Exception ignore) {};
        		stmt = null;
        	}
        }
        return cnt;
		
	}
	/**
	 * 넘겨온 userid의 배열을 확인해서 돌려 준다. 
	 * @param oDb
	 * @param usn
	 * @return
	 * @throws MdbException
	 */
	public static List chkRcvUserids(MdbDriver oDb, String[] userids) throws MdbException {
		List usnList = new ArrayList();
		String sql = "select userid, usn, nm from v_maf_user where userid = :userid";
		Map param = new HashMap();
		for(int i =0; i < userids.length; i++) {
			param.put("userid", userids[i]);
			logger.debug(i + ";" + userids[i]);
			Map rec = (Map) oDb.selectorOne(Map.class, sql, param);
			if(rec!= null ) {
				if(rec.isEmpty()) {
					rec.put("userid", userids[i]);
					rec.put("valid", "F");
				} else {
					rec.put("valid", "T");
				}
			} else {
				rec = new HashMap();
				rec.put("userid", userids[i]);
				rec.put("valid", "F");
			}
			usnList.add(rec);		
		}
		
		return usnList;
	}
	/**
	 * 받은쪽지를 삭제 한다.
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int deleteOneRcv(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "update MAF_PAGER set msg_rcv_isdel = 'Y' " +
				"	where msg_no = :msg_no " +
				"		and msg_rcv_usn = :msg_rcv_usn";
		return oDb.executeUpdate(sql, param);
	}
	/**
	 * 보낸쪽지를 삭제 한다.
	 * %% 수신자자 읽지 않았을 경우는 수신자에게도 삭제된것으로 처리함.
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int deleteOneSnd(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "update MAF_PAGER set msg_snd_isdel = 'Y', msg_rcv_isdel = decode(msg_read_dt,null,'Y',msg_rcv_isdel) " +
				"	where msg_no = :msg_no " +
				"		and msg_snd_usn = :msg_snd_usn";
		
		return oDb.executeUpdate(sql, param);
	}
	
}


		