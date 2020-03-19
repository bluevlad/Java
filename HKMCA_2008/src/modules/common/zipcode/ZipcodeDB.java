/*
 * Created on 2005. 7. 12.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.zipcode;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


/**
 * 우편번호 검색용 DAO 
 **/
public class ZipcodeDB extends BaseDAO {
	
    public static List getList(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select zipcode, addr, kwd, (sido ||' '|| gu ||' '|| dong ||' '|| nm) as r_addr " +
        " FROM zipcode_kr " +
        " where instr(kwd, :keyword) > 0 ";
        return oDb.selector(Map.class, sql, param);
    }
}
