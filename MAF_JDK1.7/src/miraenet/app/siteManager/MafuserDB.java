/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.siteManager;

import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafuserDB {
	private static MafuserDB  instance;
	private Log logger = LogFactory.getLog(MafuserDB.class);
	
	private MafuserDB() {
	}

	public static synchronized MafuserDB  getInstance() {
		if (instance == null) instance = new MafuserDB();
		return instance;
	}
	
	
	
	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
		
		List list = null;

		final String sql =  " SELECT *"   +
							"   FROM (SELECT a.*, b.kor_biz_nm AS cust_nm, c.dept_nm_kor AS dept_nm"   +
							"           FROM maf_user a, stb_cust b, stb_cust_org c"   +
							"          WHERE a.cust_cd = b.cust_cd(+) AND a.cust_cd = c.cust_cd(+)"   +
							"                AND a.dept_cd = c.dept_cd(+)) x"  ;
		
		String sOrder = navigator.getOrderSql();

		oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		oDb.setDebug(true);
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		navigator.setTotalCount(this.getRecordCount(oDb, param, sWhere.toString()));
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
	private long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final  String sql = " SELECT count(*)"   +
							"   FROM (SELECT a.*, b.kor_biz_nm AS cust_nm, c.dept_nm_kor AS dept_nm"   +
							"           FROM maf_user a, stb_cust b, stb_cust_org c"   +
							"          WHERE a.cust_cd = b.cust_cd(+) AND a.cust_cd = c.cust_cd(+)"   +
							"                AND a.dept_cd = c.dept_cd(+)) x" + sWhere;
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
	public Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql =  " SELECT a.*, b.kor_biz_nm AS cust_nm, c.dept_nm_kor AS dept_nm"   +
							"   FROM maf_user a, stb_cust b, stb_cust_org c"   +
							"  WHERE a.cust_cd = b.cust_cd(+) AND a.cust_cd = c.cust_cd(+)"   +
							"        AND a.dept_cd = c.dept_cd(+) AND a.usn = :usn"  ;
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
	public int updateOne(MdbDriver oDb, Map param) throws MdbException {
		int result = 0;
		final String sql =  " UPDATE maf_user"   +
							"    SET userid = :userid,"   +
							"        passwd = :passwd,"   +
							"        nm = :nm,"   +
//							"        nick_nm = :nick_nm,"   +
							"        nm_eng = :nm_eng,"   +
							"        valid_yn = :valid_yn,"   +
							"        cust_cd = :cust_cd,"   +
							"        resident_no = :resident_no,"   +
							"        emp_no = :emp_no,"   +
							"        dept_cd = :dept_cd,"   +
							"        dept_tm_yn = :dept_tm_yn,"   +
							"        pos_nm = :pos_nm,"   +
							"        hp = :hp,"   +
							"        tel = :tel,"   +
							"        fax = :fax,"   +
							"        email = :email,"   +
							"        addr = :addr,"   +
							"        remarks = :remarks"   +
							"  WHERE usn = :usn"  ;
		try {
			result = oDb.executeUpdate(sql, param);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 하나의 레코드를 Insert 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int insertOne(MdbDriver oDb, Map param) throws MdbException {
		int result = 0;
		final String sql =  " INSERT INTO maf_user"   +
							"             (usn, userid, passwd, nm, nm_eng, valid_yn,"   +
							"              cust_cd, resident_no, emp_no, dept_cd, dept_tm_yn, pos_nm,"   +
							"              hp, tel, fax, email, addr, remarks"   +
							"             )"   +
							"      VALUES (seq_usn.NEXTVAL, :userid, :passwd, :nm, :nm_eng, :valid_yn,"   +
							"              :cust_cd, :resident_no, :emp_no, :dept_cd, :dept_tm_yn, :pos_nm,"   +
							"              :hp, :tel, :fax, :email, :addr, :remarks"   +
							"             )"  ;
		try {
			result = oDb.executeUpdate(sql, param); 
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 하나의 레코드를 delete한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int deleteOne(MdbDriver oDb, Map param) throws MdbException {
		int result = 0;	
		
		final String sql =  " DELETE FROM maf_user"   +
							"       WHERE usn = :usn"  ;
		try {
			result = oDb.executeUpdate(sql, param);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return result;	
	}
	
}


	