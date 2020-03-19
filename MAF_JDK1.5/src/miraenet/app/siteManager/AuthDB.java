/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package miraenet.app.siteManager;
import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.logger.Logging;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuthDB extends BaseDAO {

	private Log logger = LogFactory.getLog(AuthDB.class);
	
	/**
	 * ��� ��������
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
		
		List list = null;

		final String sql = " select * from (select * from MAF_ROLE) x ";
		
		String sOrder = navigator.getOrderSql();

		oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		oDb.setDebug(true);
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		navigator.setTotalCount(getRecordCount(oDb, param, sWhere.toString()));
		navigator.sync();
		
	}
	
	/**
	 * ���ڵ� ī��Ʈ ��������
	 * 
	 * @param oDb
	 * @param param
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	private static long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final  String sql = "select count(*) from (select * from MAF_ROLE) x " + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	
	/**
	 * �ϳ��� ���ڵ� �о� ����
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select * " 
				+ "from MAF_ROLE " 
		        + " where  role_id = :role_id  ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
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
		final String sql = " update MAF_ROLE set " +
				" role_name = :role_name, " +
				" bigo = :bigo, " +
				" is_super = :is_super, " +
				" is_nologin = :is_nologin, " +
				" bbs_level = :bbs_level, " +
				" fixed = :fixed "   +
	            " where  role_id = :role_id  ";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * �ϳ��� ���ڵ带 Insert �Ѵ�.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int insertOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO MAF_ROLE"   +
		"    ( role_id, role_name, bigo, is_super, is_nologin, bbs_level, fixed)"   +
		"  VALUES"   +
		"    ( :role_id, :role_name, :bigo, :is_super, :is_nologin, :bbs_level, :fixed)"  ;
		
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * �ϳ��� ���ڵ带 ���� �Ѵ�.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int deleteOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " delete from MAF_ROLE " +
	            "where  role_id = :role_id  ";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * ����� ���� ���
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getUserList(MdbDriver oDb, String userid, String nm) throws MdbException {
		final String sql = "  SELECT * "   +
		"   FROM MAF_USER "   +
		"   WHERE userid = :userid "   +
		"   AND nm = :nm "  ;

		return oDb.selector(Map.class, sql);
	}
	
	/**
	 * ����� ���� ���
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getUserRoleList(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "  SELECT b.*"   +
		"   FROM MAF_USER_ROLE a, MAF_USER B"   +
		"   WHERE a.usn = b.usn"   +
		"   AND a.role_id = :role_id"  ;

		return oDb.selector(Map.class, sql, param);
	}
	
	/**
	 * ���ο� ����ڸ� Insert �Ѵ�.
	 */
	public static int insertRole(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO MAF_USER_ROLE"   +
		"    ( role_id, usn)"   +
		"  VALUES"   +
		"    ( :role_id, :usn)"  ;
		
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * ��Ȱ�� �ִ� ����ڸ� ��� �����Ѵ�.
	 */
	public static int deleteRole(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " DELETE from MAF_USER_ROLE " +
				" where role_id = :role_id ";
		
		return oDb.executeUpdate(sql, param);
	}
	
}


		