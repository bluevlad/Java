/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.xadmin.member;
import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class AuthDB extends BaseDAO {

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
	 * 레코드 카운트 가져오기
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
	 * 하나의 레코드 읽어 오기
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select * " +
				" from MAF_ROLE " +
				" where role_id = :role_id";
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
		final String sql = " update MAF_ROLE set " +
				" role_name = :role_name, " +
				" comments = :comments, " +
				" is_super = :is_super, " +
				" site = :site, " +
				" bbs_level = :bbs_level "   +
	            " where  role_id = :role_id  ";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 레코드를 Insert 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int insertOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO MAF_ROLE"   +
		"    ( role_id, role_name, comments, is_super, site, bbs_level)"   +
		"  VALUES"   +
		"    ( :role_id, :role_name, :comments, :is_super, :site, :bbs_level)"  ;
		
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 레코드를 삭제 한다.
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
	 * 사용자 권한 목록
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
	 * 사용자 권한 목록
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getUserRoleList(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "  SELECT b.*, a.role_id, c.role_name"   +
		"   FROM MAF_USER_ROLE a, MAF_USER B, maf_role c"   +
		"   WHERE a.usn = b.usn" +
		" and a.role_id = c.role_id"   +
		"   AND a.role_id = :role_id"  ;

		return oDb.selector(Map.class, sql, param);
	}
	
	/**
	 * 새로운 사용자를 Insert 한다.
	 */
	public static int insertRole(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO MAF_USER_ROLE"   +
		"    ( role_id, usn)"   +
		"  VALUES"   +
		"    ( :role_id, :usn)"  ;
		
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 역활에 있던 사용자를 모두 삭제한다.
	 */
	public static int deleteRole(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " DELETE from MAF_USER_ROLE " +
				" where role_id = :role_id ";
		
		return oDb.executeUpdate(sql, param);
	}
	

	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public static void getNotRoleList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
		
		List list = null;

		final String sql = " select * from (select * from maf_user where usn not in (" +
				"SELECT a.usn" +
				" FROM maf_user a, maf_user_role b" +
				" where a.usn = b.usn" +
				" and b.role_id = :role_id" +
				" group by a.usn )) x ";
		
		String sOrder = navigator.getOrderSql();

		oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		oDb.setDebug(true);
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		navigator.setTotalCount(getNotRoleRecordCount(oDb, param, sWhere.toString()));
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
	private static long getNotRoleRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final  String sql = "select count(*) from (select * from maf_user where usn not in (" +
				"SELECT a.usn" +
				" FROM maf_user a, maf_user_role b" +
				" where a.usn = b.usn" +
				" and b.role_id = :role_id" +
				" group by a.usn )) x " + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	
}	