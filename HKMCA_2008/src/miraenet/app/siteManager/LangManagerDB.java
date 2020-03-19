/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package miraenet.app.siteManager;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class LangManagerDB extends BaseDAO {

	/**
	 * ��� ��������
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	/**
	 * ��� ��������
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
		List list = null;
		final String sql = " SELECT code, allnames, active_yn, local_name, code2 FROM maf_lang_iso639_1";
		String sOrder = navigator.getOrderSql();
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
	}

	/**
	 * �ϳ��� ���ڵ带 Update �Ѵ�.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int updateOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update MAF_LANG_ISO639_1 set "
				+ "code2 = :code2  ,  "
				+ "allnames = :allnames  ,  "
				+ "active_yn = :active_yn  ,  "
				+ "local_name = :local_name,   "
				+ "update_usn = :update_usn,   "
				+ "update_dt = sysdate   "
				+ "where  code = :code  ";
		return oDb.executeUpdate(sql, param);
	}
}
