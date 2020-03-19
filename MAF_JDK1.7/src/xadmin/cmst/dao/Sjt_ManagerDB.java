/*
 * Sjt_ManagerDB.java, @ 2005-05-12
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

import xadmin.cmst.beans.SjtMstBean;

/**
 * @author goindole
 *  
 */
public class Sjt_ManagerDB extends CommonDB {
	private static Sjt_ManagerDB instance;
	private  Log logger = LogFactory.getLog(this.getClass());
	private Sjt_ManagerDB() {
	}

	public static synchronized Sjt_ManagerDB getInstance() {
		if (instance == null)
			instance = new Sjt_ManagerDB();
		return instance;
	}

	public List getSjtList(MdbDriver oDb, int page, int page_size,
			String v_key, String v_srch, String v_sort, String v_crscode)
			throws MdbException {

		String sql = " SELECT s.*, f.crsname"
				+ "   FROM sjt_mst s INNER JOIN crs_mst f ON s.crscode = f.crscode"
				+ " WHERE DECODE (:crscode, 'ALL',1,DECODE (:crscode, f.crscode, 1, 0))=1 "
				+ "    		 AND DECODE (:v_key, 'code', DECODE(INSTR(upper(s.sjtcode), upper(:v_srch)), 0, 0, 1 ), 1) = 1"
				+ "    		 AND DECODE (:v_key, 'name', DECODE(INSTR(upper(s.sjtname), upper(:v_srch)), 0, 0, 1 ), 1) = 1"
				+ " order by f.crsname, s.sjtcode";
		oDb.setPage(page, page_size);
		List at = new ArrayList();
		at.add(v_crscode);
		at.add(v_crscode);
		at.add(v_key);
		at.add(v_srch);
		at.add(v_key);
		at.add(v_srch);

		return oDb.selector(SjtMstBean.class, sql, at);
	}

	/**
	 * 과목 전체 리스트 돌려줌
	 * 
	 * @param oDb
	 * @return
	 * @throws MdbException
	 */
	public static List getSjtList(MdbDriver oDb) throws MdbException {
		String sql = " SELECT   s.*, f.crsname"
				+ "     FROM sjt_mst s INNER JOIN crs_mst f ON s.crscode = f.crscode"
				+ " ORDER BY s.crscode, s.sjtcode";
		;
		return oDb.selector(SjtMstBean.class, sql);
	}

	public int getCrsCount(MdbDriver oDb, String v_key, String v_srch,
			String v_sort, String v_crscode) throws MdbException {

		final String sql = " select count(*) from sjt_mst s"
				+ " WHERE DECODE (:crscode, 'ALL',1,DECODE (:crscode, s.crscode, 1, 0))=1 "
				+ "    		 AND DECODE (:v_key, 'code', DECODE(INSTR(upper(s.sjtcode), upper(:v_srch)), 0, 0, 1 ), 1) = 1"
				+ "    		 AND DECODE (:v_key, 'name', DECODE(INSTR(upper(s.sjtname), upper(:v_srch)), 0, 0, 1 ), 1) = 1";

		List at = new ArrayList();
		at.add(v_crscode);
		at.add(v_crscode);
		at.add(v_key);
		at.add(v_srch);
		at.add(v_key);
		at.add(v_srch);
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

	public SjtMstBean getSjtView(MdbDriver oDb, String sjtcode)
			throws MdbException {
		final String sql = " SELECT S.*, F.CRSNAME "
				+ " FROM SJT_MST S INNER JOIN CRS_MST F ON S.CRSCODE=F.CRSCODE        "
				+ " WHERE S.SJTCODE= :sjtcode";
		List at = new ArrayList();
		at.add(sjtcode);
		return (SjtMstBean) oDb.selectorOne(SjtMstBean.class, sql, at);
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
	public synchronized int update(MdbDriver oDb, SjtMstBean bean)
			throws MdbException {
		int ret = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE  SJT_MST "
				  + " SET " 
				  + " crscode = :crscode,  " 
				  + " sjtname = :sjtname,  " 
				  + " sjtenam = :sjtenam,  " 
				  + " sjtpoint = :sjtpoint,  " 
				  + " lec_point = :lec_point,  " 
				  + " trn_point = :trn_point,  " 
				  + " eq_sjtcode = :eq_sjtcode,  " 
				  + " eq_sjtname = :eq_sjtname,  " 
				  + " od_sjtcode = :od_sjtcode,  " 
				  + " od_sjtname = :od_sjtname,  " 
				  + " update_dt = :update_dt,  " 
				  + " update_id = :update_id,  " 
				  + " sjttarget = :sjttarget,  " 
				  + " sjtdesc = :sjtdesc,  " 
				  + " reqsjt = :reqsjt "
				  + " WHERE  sjtcode = :SJTCODE ";

			pstmt = oDb.prepareStatement(sql, bean);


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
	public synchronized int insert(MdbDriver oDb, SjtMstBean bean)
			throws MdbException {
		PreparedStatement pstmt = null;

		int ret = 0;

		try {
			String sql = "INSERT INTO SJT_MST (" 
				  + "sjtcode, crscode, sjtname, sjtenam, sjtpoint, " 
				  + " lec_point, trn_point, eq_sjtcode, eq_sjtname, od_sjtcode, "
				  + " od_sjtname, update_dt, update_id, sjttarget, sjtdesc, reqsjt " 
				  + " ) "
				  + " VALUES ("
				  + " :sjtcode, :crscode, :sjtname, :sjtenam, :sjtpoint, "
				  + " :lec_point, :trn_point, :eq_sjtcode, :eq_sjtname, :od_sjtcode, "
				  + " :od_sjtname, :update_dt, :update_id, :sjttarget, :sjtdesc, :reqsjt "
				  + ") ";

			pstmt = oDb.prepareStatement(sql, bean);

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
	public synchronized int remove(MdbDriver oDb, String sjtcode)
			throws MdbException {
		PreparedStatement pstmt = null;

		int ret = 0;
		try {
			pstmt = oDb.prepareStatement("DELETE FROM SJT_MST WHERE sjtcode=?");
			pstmt.setString(1, sjtcode);
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
//
//	/**
//	 * 과목 컨텐츠 리스트
//	 * 
//	 * @param oDb
//	 * @return
//	 * @throws MdbException
//	 */
//	public List getInxList(MdbDriver oDb, String sjtcode) throws MdbException {
//		final String sql = "select c.*" + " from INX_LST c "
//				+ " where sjtcode = :sjtcode " + "  order by sjtcode, chasi ";
//		List at = new ArrayList();
//		at.add(sjtcode);
//		return oDb.selector(Inx_lstBean.class, sql, at);
//	}
//
//	public Inx_lstBean getInxItem(MdbDriver oDb, String sjtcode, String chasi)
//			throws MdbException {
//		final String sql = "select c.*" + " from INX_LST c "
//				+ " where sjtcode = :sjtcode and chasi = :chasi";
//		List at = new ArrayList();
//		at.add(sjtcode);
//		at.add(chasi);
//		return (Inx_lstBean) oDb.selectorOne(Inx_lstBean.class, sql, at);
//	}
//
//	/**
//	 * FunctionName : update(CrsMstBean bean) Description : 과정 정보 수정
//	 * 
//	 * @param :
//	 * @return : String 메시지
//	 * @exception :
//	 *                DBHandlerException, DBConnectionFailedException,
//	 *                ClassNotFoundException, IllegalSqlException,
//	 *                DataNotFoundException
//	 */
//	public synchronized int inxUpdate(MdbDriver oDb, Inx_lstBean bean)
//			throws MdbException {
//		int ret = 0;
//		PreparedStatement pstmt = null;
//		try {
//			oDb.setDebug(true);
//			String sql = "UPDATE  INX_LST " + " SET " + " week = :week,  "
//					+ " chasi = :chasi,  " + " sjtname = :sjtname,  "
//					+ " mediaid = :mediaid,  " + " htmname = :htmname,  "
//					+ " issample = :issample,  " + " update_id = :update_id,  "
//					+ " chptime = :chptime "
//					+ " WHERE  sjtcode = :sjtcode AND chasi = :oldchasi ";
//
//			pstmt = oDb.prepareStatement(sql, bean);
//
//			ret = pstmt.executeUpdate();
//			pstmt.close();
//			oDb.commit();
//		} catch (Exception e) {
//			Logger logger = Logger.getLogger(this.getClass());
//			logger.error(e.getMessage());
//			oDb.rollback();
//			throw new MdbException(e.getMessage());
//		} finally {
//			release(pstmt);
//		}
//		return ret;
//	}
//
//	public synchronized int inxInsert(MdbDriver oDb, Inx_lstBean bean)
//			throws MdbException {
//		int ret = 0;
//		PreparedStatement pstmt = null;
//		try {
//			oDb.setDebug(true);
//			String sql = "INSERT INTO INX_LST (sjtcode, week, chasi, sjtname, mediaid, "
//				+ " htmname, issample, update_dt, update_id, chptime ) "
//				+ " VALUES (:sjtcode, :week, :chasi, :sjtname, :mediaid, "
//				+ " :htmname, :issample, :update_dt, :update_id, :chptime ) ";
//
//			pstmt = oDb.prepareStatement(sql, bean);
//
//			ret = pstmt.executeUpdate();
//			pstmt.close();
//			oDb.commit();
//		} catch (Exception e) {
//			Logger logger = Logger.getLogger(this.getClass());
//			logger.error(e.getMessage());
//			oDb.rollback();
//			throw new MdbException(e.getMessage());
//		} finally {
//			release(pstmt);
//		}
//		return ret;
//	}
}
