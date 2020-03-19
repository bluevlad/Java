/*
 * Created on 2006. 08. 24
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.pager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AddressBookDB {
	private static AddressBookDB  instance;
	private Log logger = LogFactory.getLog(AddressBookDB.class);
	
	private AddressBookDB() {
	}

	public static synchronized AddressBookDB  getInstance() {
		if (instance == null) instance = new AddressBookDB();
		return instance;
	}
	
	/**
	 * 소유자의 보유 주소그룹 목록을 돌려 준다.
	 * @param oDb
	 * @param ownerUsn
	 * @return
	 * @throws MdbException
	 */
	public List getAddressGroup(MdbDriver oDb, String ownerUsn) throws MdbException {
		Map param = new HashMap();
		param.put("owner_usn", ownerUsn);
		final String sql = " SELECT msg_grp_id, msg_grp_nm, owner_usn, isuse, update_dt, update_id"   +
			"   FROM maf_pager_grp"   +
			"  WHERE owner_usn = :owner_usn"  ; 
		return oDb.selector(Map.class, sql, param);
	}
	
	/**
	 * 주소그룹안의 사용자 목록을 돌려 준다.
	 * @param oDb
	 * @param ownerUsn
	 * @return
	 * @throws MdbException
	 */
	public List getAddressGroupItem(MdbDriver oDb, String msgGroupId) throws MdbException {
		Map param = new HashMap();
		param.put("msg_grp_id", msgGroupId);
		final String sql = " SELECT   gi.msg_grp_id, gi.msg_rd_id, gi.reg_dt, gi.update_dt, gi.update_id,"   +
		"          mu.usn, mu.userid, mu.nm, mu.nm_eng"   +
		"     FROM maf_pager_grp_item gi, v_maf_user mu"   +
		"    WHERE gi.msg_grp_id = :msg_grp_id AND gi.msg_rd_id = mu.usn"   +
		" ORDER BY mu.nm"  ;  ; 
		return oDb.selector(Map.class, sql, param);
	}
	
}

