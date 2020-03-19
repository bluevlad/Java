/*
 * Crs_ManagerDB.java, @ 2005-05-12
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xadmin.cmst.beans.CrsMstBean;

/**
 * @author goindole
 *  
 */
public class Crs_ManagerDB extends CommonDB {
	private static Crs_ManagerDB instance;
	private  Log logger = LogFactory.getLog(this.getClass());
	private Crs_ManagerDB() {
	}

	public static synchronized Crs_ManagerDB getInstance() {
		if (instance == null)
			instance = new Crs_ManagerDB();
		return instance;
	}

	/**
	 * 과정 코드 리스트 (페이징)
	 * @param oDb
	 * @param page
	 * @param page_size
	 * @return
	 * @throws MdbException
	 */
	public List getCrsList(MdbDriver oDb, int page, int page_size)
			throws MdbException {
		List list = null;
		String sql = "SELECT CRSCODE, CRSNAME, CRSENAME, UPDATE_ID, UPDATE_DT FROM CRS_MST ORDER BY CRSCODE";
		oDb.setPage(page, page_size);
		list = oDb.selector(CrsMstBean.class, sql);

		return list;
	}

	/**
	 * 과정 코드 리스트를 리턴 
	 * @param oDb
	 * @return
	 * @throws MdbException
	 */
	public static List getCrsList(MdbDriver oDb)
			throws MdbException {
		List list = null;
		final String sql = "SELECT CRSCODE, CRSNAME, CRSENAME, UPDATE_ID, UPDATE_DT "
			+ " FROM CRS_MST ORDER BY CRSNAME";
		list = oDb.selector(CrsMstBean.class, sql);

		return list;
	}

	public int getCrsCount(MdbDriver oDb) throws MdbException {
		List at = new ArrayList();

		final String sql = " select count(*) from CRS_MST";

		return oDb.selectOneInt(sql, at);
	}

	/**
	 * 하나의 교과과정 정보를 가져 온다.
	 * 
	 * @param oDb
	 * @param crscode
	 * @return
	 * @throws MdbException
	 */

	public CrsMstBean getCrsView(MdbDriver oDb, String crscode)
			throws MdbException {
		final String sql = " SELECT * FROM CRS_MST WHERE CRSCODE=:crscode";
		List at = new ArrayList();
		at.add(crscode);
		return (CrsMstBean) oDb.selectorOne(CrsMstBean.class, sql, at);
	}

	/**
	 * FunctionName : update(CrsMstBean bean) Description : 과정 정보 수정
	 * 
	 * @param :
	 * @return : String 메시지
	 * @exception :
	 *                DBHandlerException, DBConnectionFailedException,
	 *                ClassNotFoundException, IllegalSqlException,
	 *                DataNotFoundException
	 */
	public int update(MdbDriver oDb, CrsMstBean bean)
			throws MdbException {
		int ret = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE CRS_MST SET   CRSNAME=:crsname, CRSENAME=:crsename, "
					+ " UPDATE_DT=SYSDATE, UPDATE_ID=:update_id "
					+ "  WHERE CRSCODE = :crscode ";
			pstmt = oDb.prepareStatement(sql, bean);

//			pstmt.setString(1, bean.getCrsname());
//			pstmt.setString(2, bean.getCrsename());
//			pstmt.setString(3, bean.getUpdate_id());
//			pstmt.setString(4, bean.getCrscode());

			ret = pstmt.executeUpdate();
			pstmt.close();
			oDb.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			oDb.rollback();
			throw new MdbException(e.getMessage());
		} finally {
			release(pstmt);
		}
		return ret;
	}

	/**
	 * FunctionName : insert(CrsMstBean bean) Description : 과정 정보 등록
	 * 
	 * @param :
	 * @return : String 메시지
	 * @exception :
	 *                DBHandlerException, DBConnectionFailedException,
	 *                ClassNotFoundException, IllegalSqlException,
	 *                DataNotFoundException
	 */
	public int insert(MdbDriver oDb, CrsMstBean bean)
			throws MdbException {
		PreparedStatement pstmt = null;

		int ret = 0;

		try {
			String sql = "INSERT INTO CRS_MST (CRSCODE, CRSNAME,CRSENAME,UPDATE_DT,UPDATE_ID) "
				+ " VALUES(?,?,?,SYSDATE,?)";
			pstmt = oDb.prepareStatement(sql);

			pstmt.setString(1, bean.getCrscode());
			pstmt.setString(2, bean.getCrsname());
			pstmt.setString(3, bean.getCrsename());
			pstmt.setString(4, bean.getUpdate_id());
			ret = pstmt.executeUpdate();

			pstmt.close();
			oDb.commit();

		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			throw new MdbException(e.getMessage());
		} finally {
			release(pstmt);
		}
		return ret;
	}

	/**
	 * FunctionName : remove(String crscode) Description : 과정 정보 삭제
	 * 
	 * @param :
	 * @return : String 메시지
	 * @exception :
	 *                DBHandlerException, DBConnectionFailedException,
	 *                ClassNotFoundException, IllegalSqlException,
	 *                DataNotFoundException
	 */
	public int remove(MdbDriver oDb, String crscode)
			throws MdbException {
		PreparedStatement pstmt = null;

		int ret = 0;
		try {
			pstmt = oDb.prepareStatement("DELETE FROM CRS_MST WHERE CRSCODE=?");
			pstmt.setString(1, crscode);
			ret = pstmt.executeUpdate();
			pstmt.close();
			oDb.commit();

		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			throw new MdbException(e.getMessage());
		} finally {
			release(pstmt);
		}
		return ret;
	}
}
