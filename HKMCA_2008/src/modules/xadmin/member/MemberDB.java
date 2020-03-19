/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.xadmin.member;

import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class MemberDB {
	
	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
		
		List list = null;

		final String sql =  " SELECT * "   +
		"   FROM (SELECT * "   +
		"           FROM maf_user) x"  ;
		
        String sOrder = navigator.getOrderSql();

        oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
        list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
        navigator.setList(list);
        navigator.setTotalCount(getRecordCount(oDb, param, sWhere.toString()));
        navigator.sync();
	}
	
	/**
	 * 레코드 카운트 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	private static long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final  String sql = " SELECT count(*)"   +
							"   FROM (SELECT * "   +
		"           FROM maf_user) x" + sWhere;
		return oDb.selectOneInt(sql, param);
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
		final String sql =  " SELECT * "   +
					"           FROM maf_user "   +
					"          WHERE usn = :usn"  ;
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 하나의 레코드를  insert or update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
    public static int mergeOne(MdbDriver oDb,  Map param) throws MdbException  {
    	final String sql = "MERGE INTO maf_user new " +
    	"      USING (SELECT :usn usn, :userid userid, :passwd passwd, :nm nm, " +
    	"                    :pos_nm pos_nm, :hp hp, :tel tel, :fax fax, " +
    	"                    :email email, :zip zip, :addr1 addr1, :addr2 addr2, NVL(:active_yn, 'Y') active_yn " +
    	"               FROM DUAL) src " +
    	"         ON (src.usn = new.usn) " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE SET" +
		"           userid = src.userid,"   +
		"           passwd = src.passwd,"   +
		"           nm = src.nm,"   +
		"           pos_nm = src.pos_nm,"   +
		"           hp = src.hp,"   +
		"           tel = src.tel,"   +
		"           fax = src.fax,"   +
		"           email = src.email,"   +
		"           zip = src.zip,"   +
		"           addr1 = src.addr1,"   +
		"           addr2 = src.addr2"   +
    	"    WHEN NOT MATCHED THEN " +
    	"       INSERT (" +
    	"                usn, userid, passwd, nm, pos_nm, hp," +
    	"                tel, fax, email, zip, addr1, addr2, active_yn" +
    	"       ) VALUES (" +
    	"                  seq_usn.NEXTVAL || '_' || :userid, src.userid, src.passwd, src.nm, src.pos_nm, src.hp," +
    	"                  src.tel, src.fax, src.email, src.zip, src.addr1, src.addr2, src.active_yn" +
    	"      ) ";
    	
    	return oDb.executeUpdate(sql, param);
    }

	/**
	 * 하나의 레코드를 delete한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int deleteOne(MdbDriver oDb, Map param) throws MdbException {
		
		final String sql =  " DELETE FROM maf_user"   +
							"       WHERE usn = :usn"  ;
		
        return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 중복 아이디 검색
	 */
	public static int getchkId(MdbDriver oDb, Map param) throws MdbException {
		
		final String sql =  " SELECT count(*) " +
		" FROM maf_user " +
		" WHERE userid = :userid ";
		
		return oDb.selectOneInt(sql, param);
	}
	
	/**
	 * 아이디 검색
	 */
	public static Map getFindid(MdbDriver oDb, Map param) throws MdbException {
		
		final String sql =  " SELECT userid " +
		" FROM maf_user " +
		" WHERE pin = :pin and nm =:nm ";
		
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
	/**
	 * 비밀번호 검색
	 */
	public static Map getFindpwd(MdbDriver oDb, Map param) throws MdbException {
		
		final String sql =  " SELECT passwd " +
		" FROM maf_user " +
		" WHERE userid = :userid ";
		
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
}	