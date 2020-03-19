/*
 * Created on 2006. 08. 21
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.pager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

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

		final String sql = "SELECT *  " +
		"  FROM (SELECT mu.userid msg_snd_userid, mu.nm msg_snd_nm, " +
		"               a.msg_no, a.msg_rcv_usn, a.msg_snd_usn, a.msg_title, a.msg_info, a.file_name, " +
		"               a.msg_dt, a.msg_read_dt, decode(a.msg_read_dt, null, 'close', 'open') as msg_open,  " +
		"               a.msg_rcv_isdel, a.msg_snd_isdel, a.msg_no_p  " +
		"          FROM pager a, v_jmf_user mu  " +
		"          WHERE a.msg_rcv_isdel = 'N'  " +
		"          AND a.msg_snd_usn = mu.usn) p ";
		
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
		final  String sql = " SELECT count(*) cnt "   +
		"  FROM (SELECT mu.userid msg_snd_userid, mu.nm msg_snd_nm, " +
		"               a.msg_no, a.msg_rcv_usn, a.msg_snd_usn, a.msg_title, a.msg_info, a.file_name, " +
		"               a.msg_dt, a.msg_read_dt, decode(a.msg_read_dt, null, 'close', 'open') as msg_open,  " +
		"               a.msg_rcv_isdel, a.msg_snd_isdel, a.msg_no_p  " +
		"          FROM pager a, v_jmf_user mu  " +
		"          WHERE a.msg_rcv_isdel = 'N'  " +
		"          AND a.msg_snd_usn = mu.usn) p " + sWhere;
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

		final String sql = "SELECT *  " +
		"  FROM (SELECT mu.userid msg_snd_userid, mu.nm msg_snd_nm, " +
		"               a.msg_no, a.msg_rcv_usn, a.msg_snd_usn, a.msg_title, a.msg_info, a.file_name, " +
		"               a.msg_dt, a.msg_read_dt, decode(a.msg_read_dt, null, 'close', 'open') as msg_open,  " +
		"               a.msg_rcv_isdel, a.msg_snd_isdel, a.msg_no_p  " +
		"          FROM pager a, v_jmf_user mu  " +
		"          WHERE a.msg_snd_isdel = 'N'  " +
		"          AND a.msg_rcv_usn = mu.usn) p ";
		
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
		"  FROM (SELECT mu.userid msg_snd_userid, mu.nm msg_snd_nm, " +
		"               a.msg_no, a.msg_rcv_usn, a.msg_snd_usn, a.msg_title, a.msg_info, a.file_name, " +
		"               a.msg_dt, a.msg_read_dt, decode(a.msg_read_dt, null, 'close', 'open') as msg_open,  " +
		"               a.msg_rcv_isdel, a.msg_snd_isdel, a.msg_no_p  " +
		"          FROM pager a, v_jmf_user mu  " +
		"          WHERE a.msg_snd_isdel = 'N'  " +
		"          AND a.msg_rcv_usn = mu.usn) p"   + sWhere;
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
	public static Map getSndOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT p.*, " +
		"       (select userid from v_jmf_user where usn = p.msg_rcv_usn) msg_rcv_id, " +
		"       (select nm from v_jmf_user where usn = p.msg_rcv_usn) msg_rcv_nm " +
		"  FROM pager p " +
		"  WHERE msg_no = :msg_no";

		final String updatesql = "update PAGER set MSG_READ_DT = nvl(MSG_READ_DT, sysdate) " 
            + "where msg_no = :msg_no";

		oDb.executeUpdate(updatesql, param);	
		
        return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 하나의 레코드 읽어 오기
	 * !!! 보낸사람과 받는사람외에는 못읽어 온다.
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getRcvOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT p.*, " +
		"       (select userid from v_jmf_user where usn = p.msg_snd_usn) msg_snd_id, " +
		"       (select nm from v_jmf_user where usn = p.msg_snd_usn) msg_snd_nm " +
		"  FROM pager p " +
		"  WHERE msg_no = :msg_no";

		final String updatesql = "update PAGER set MSG_READ_DT = nvl(MSG_READ_DT, sysdate) " 
            + "where msg_no = :msg_no";

		oDb.executeUpdate(updatesql, param);	
		
        return (Map) oDb.selectorOne(Map.class, sql, param);
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
		final String sql = " update JMF_PAGER set "   +
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

	/**
	 * 쪽지를 발송 한다.
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int sendMessage(MdbDriver oDb, Map param) throws MdbException {
		String sql = "INSERT INTO PAGER" +
		" (msg_rcv_usn, msg_snd_usn, msg_title, msg_info)" +
		" VALUES" +
		" (:msg_rcv_usn, :msg_snd_usn, :msg_title, :msg_info)";

        return oDb.executeUpdate(sql, param);
	}

	/**
	 * 넘겨온 userid의 배열을 확인해서 돌려 준다. 
	 * @param oDb
	 * @param usn
	 * @return
	 * @throws MdbException
	 */
	public static String RcvUserids(MdbDriver oDb, Map param) throws MdbException {
		String sql = "select usn from v_jmf_user where userid = :userid";
		return oDb.selectOne(sql, param);
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
		String sql = "select userid, usn, nm from v_jmf_user where userid = :userid";
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
		final String sql = "update PAGER set msg_rcv_isdel = 'Y' " +
				" where msg_no = :msg_no " +
				" and msg_rcv_usn = :msg_rcv_usn";
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
		final String sql = "update PAGER set msg_snd_isdel = 'Y', msg_rcv_isdel = decode(msg_read_dt,null,'Y',msg_rcv_isdel) " +
				" where msg_no = :msg_no " +
				" and msg_snd_usn = :msg_snd_usn";
		
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 소유자의 보유 주소그룹 목록을 돌려 준다.
	 * @param oDb
	 * @param ownerUsn
	 * @return
	 * @throws MdbException
	 */
	public static List getAddressGroup(MdbDriver oDb, String ownerUsn) throws MdbException {
		Map param = new HashMap();
		param.put("owner_usn", ownerUsn);
		final String sql = "SELECT msg_grp_id, msg_grp_nm, owner_usn, isuse, upt_dt, upt_id" +
		" FROM pager_grp" +
		" WHERE owner_usn = :owner_usn"; 
		return oDb.selector(Map.class, sql, param);
	}
	
	/**
	 * 주소그룹안의 사용자 목록을 돌려 준다.
	 * @param oDb
	 * @param ownerUsn
	 * @return
	 * @throws MdbException
	 */
	public static List getAddressGroupItem(MdbDriver oDb, String msgGroupId) throws MdbException {
		Map param = new HashMap();
		param.put("msg_grp_id", msgGroupId);
		final String sql = "SELECT gi.msg_grp_id, gi.msg_rd_id, gi.reg_dt, gi.upt_dt, gi.upt_id," +
		"          mu.usn, mu.userid, mu.nm, mu.nm_eng" +
		"   FROM pager_grp_item gi, v_jmf_user mu" +
		"   WHERE gi.msg_grp_id = :msg_grp_id AND gi.msg_rd_id = mu.usn" +
		" ORDER BY mu.nm"; 
		return oDb.selector(Map.class, sql, param);
	}
	
}	