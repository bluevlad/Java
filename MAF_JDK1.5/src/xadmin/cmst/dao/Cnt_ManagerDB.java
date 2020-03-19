/*
 * Created on 2005. 6. 8.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.dao;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import maf.web.http.MyHttpServletRequest;
import xadmin.cmst.beans.Cnt_ItemBean;
import xadmin.cmst.beans.Cnt_MstBean;

/**
 * @author goindole
 *  
 */
public class Cnt_ManagerDB {
	private static Cnt_ManagerDB instance;

	private Cnt_ManagerDB() {
	}

	public static synchronized Cnt_ManagerDB getInstance() {
		if (instance == null)
			instance = new Cnt_ManagerDB();
		return instance;
	}

	public List getCntList(MdbDriver oDb, int page, int page_size,
			String v_key, String v_srch, String v_sort, String v_crscode)
			throws MdbException {
		String sql = "  SELECT s.*, f.sjtname"
				+ "    FROM cnt_mst s INNER JOIN sjt_mst f ON s.sjtcode = f.sjtcode"
				+ " WHERE DECODE (:crscode, 'ALL',1,DECODE (:crscode, f.crscode, 1, 0))=1 "
				+ "  order by s.sjtcode, s.cnt_id";
		oDb.setPage(page, page_size);
		List at = new ArrayList();
		at.add(v_crscode);
		at.add(v_crscode);

		return oDb.selector(Cnt_MstBean.class, sql, at);
	}
	
	/**
	 * 등록된 컨텐츠 수를 구함 
	 * @param oDb
	 * @param v_key
	 * @param v_srch
	 * @param v_sort
	 * @param v_crscode
	 * @return
	 * @throws MdbException
	 */
	public int getCntCount(MdbDriver oDb, 
			String v_key, String v_srch, String v_sort, String v_crscode)
			throws MdbException {
		String sql = "  SELECT count(*) "
				+ "    FROM cnt_mst s INNER JOIN sjt_mst f ON s.sjtcode = f.sjtcode"
				+ " WHERE DECODE (:crscode, 'ALL',1,DECODE (:crscode, f.crscode, 1, 0))=1 ";
		List at = new ArrayList();
		at.add(v_crscode);
		at.add(v_crscode);

		return oDb.selectOneInt( sql, at);
	}	
	
	public Cnt_MstBean getCntItem(MdbDriver oDb,  String sjtcode, String cnt_id)
			throws MdbException {
		String sql = "  SELECT s.*, f.sjtname"
				+ "    FROM cnt_mst s INNER JOIN sjt_mst f ON s.sjtcode = f.sjtcode"
				+ " WHERE s.sjtcode = :sjtcode and s.cnt_id = :cnt_id";
		List at = new ArrayList();
		at.add(sjtcode);
		at.add(cnt_id);

		return (Cnt_MstBean) oDb.selectorOne(Cnt_MstBean.class, sql, at);
	}
	
	public int insertMst(MdbDriver oDb, Cnt_MstBean bean, MyHttpServletRequest _req)
	throws MdbException {
		String sql = "INSERT INTO CNT_MST (" 
			  + "sjtcode, cnt_id, chptitle, chpdesc, prof, " 
			  + " reg_dt, update_id, update_dt " 
			  + " ) "
			  + " VALUES ("
			  + " :sjtcode, :cnt_id, :chptitle, :chpdesc, :prof, " 
			  + " :reg_dt, :update_id, :update_dt "
			  + ") ";
		return oDb.executeUpdate(sql, bean);

	}
	
	public int updateMst(MdbDriver oDb, Cnt_MstBean bean)
	throws MdbException {
		String sql = "UPDATE  CNT_MST "
			  + " SET " 
			  + " chptitle = :chptitle,  " 
			  + " chpdesc = :chpdesc,  " 
			  + " prof = :prof,  " 
			  + " reg_dt = :reg_dt,  " 
			  + " update_id = :update_id  " 
			  + " WHERE  sjtcode = :sjtcode AND cnt_id = :cnt_id ";

		return oDb.executeUpdate(sql, bean);

	}	
	
	public int insertMst(MdbDriver oDb, Cnt_MstBean bean)
	throws MdbException {
		String sql = "INSERT INTO CNT_MST (" 
			  + "sjtcode, cnt_id, chptitle, chpdesc, prof, " 
			  + " update_id " 
			  + " ) "
			  + " VALUES ("
			  + " :sjtcode, :cnt_id, :chptitle, :chpdesc, :prof, " 
			  + " :update_id "
			  + ") ";

		return oDb.executeUpdate(sql, bean);

	}	
	
	/**
	 * 강의 컨텐츠를 merge 한다..(Oracle 9이상)
	 * @param oDb
	 * @param bean
	 * @return
	 * @throws MdbException
	 */
	public int mergeContentItem(MdbDriver oDb, Cnt_ItemBean bean) 
	throws MdbException {
		String sql = " MERGE INTO CNT_ITEM  t1"   +
			" USING (SELECT :sjtcode sjtcode, :cnt_id cnt_id, :chasi chasi, "   +
			" 	  		  :filename filename, :chp_title chp_title, :chp_time chp_time,"   +
			" 			  :chapters chapters"   +
			" 	  		  from dual ) s1"   +
			" ON ( t1.sjtcode = s1.sjtcode  and t1.cnt_id = s1.cnt_id and t1.chasi = s1.chasi)"   +
			" WHEN MATCHED THEN UPDATE "   +
			"     SET t1.filename = s1.filename,"   +
			" 		t1.chp_title = s1.chp_title,"   +
			" 		t1.chp_time = s1.chp_time,"   +
			" 		t1.chapters = s1.chapters"   +
			" WHEN NOT MATCHED THEN "   +
			" INSERT ( sjtcode, cnt_id, chasi, "   +
			" 	   	 filename, chp_title, chp_time, "   +
			" 		 chapters)"   +
			" values ( s1.sjtcode, s1.cnt_id, s1.chasi,"   +
			" 	   	 s1.filename, s1.chp_title, s1.chp_time,"   +
			" 		 s1.chapters)"  ;
		return oDb.executeUpdate(sql, bean);
	}
	/**
	 * 모든 컨텐츠 삭제 
	 * @param oDb
	 * @param bean
	 * @return
	 * @throws MdbException
	 */
	public int deleteAllContentItem(MdbDriver oDb, Cnt_ItemBean bean) throws MdbException {
		String sql = "DELETE FROM CNT_ITEM "
				+ " where sjtcode = :sjtcode "
				+ " 	and cnt_id= :cnt_id ";
		return oDb.executeUpdate(sql, bean);
	}
	public List getContentsList(MdbDriver oDb, Cnt_MstBean cm) 
	throws MdbException {
		String sql = "SELECT sjtcode,  cnt_id,  chasi,  filename,  chp_title,  " 
			  + " chp_time,  chapters,  reg_dt,  update_id,  update_dt " 
			  + " FROM CNT_ITEM "
			  + " WHERE  sjtcode = :sjtcode AND cnt_id = :cnt_id "
			  + " ORDER BY chasi" ;
		List at = new ArrayList();
		at.add(cm.getSjtcode());
		at.add(cm.getCnt_id());
		
		return oDb.selector( Cnt_ItemBean.class, sql, at);
	}
	
	
	
	
	
	/**
	 * 개설과목관리의 컨텐츠가져오기 기능을 사용하면
	 * 과목에 등록된 n개의 컨텐츠중에 하나를 선택할 수 있도록 
	 * 과목에 해당하는 컨텐츠 목록을 보여준다.  
	 * 
	 * @author 김윤철
	 * @version 1.0
	 * @modifydate 2005. 6. 25.
	 *
	 * @param oDb
	 * @param page	현재 페이지
	 * @param page_size	화면당 보여줄 게시물수
	 * @param sjtcode 	과목코드
	 * @return
	 * @throws MdbException
	 */
	public List getCntMstListBySjtcode(MdbDriver oDb, int page, int page_size, String sjtcode)	
		throws MdbException {
		
		String sql = " SELECT *  FROM cnt_mst  WHERE sjtcode = :sjtcode";
		oDb.setPage(page, page_size);
		List at = new ArrayList();
		at.add(sjtcode);		

		return oDb.selector(Cnt_MstBean.class, sql, at);
	}

	
	/**
	 * 페이징처리를 위해서 목록 수 필요
	 * 
	 * @author 김윤철
	 * @version 1.0
	 * @modifydate 2005. 6. 25.
	 *
	 * @param oDb
	 * @param sjtcode
	 * @return
	 * @throws MdbException
	 */
	public int getCntMstCountBySjtcode(MdbDriver oDb,String sjtcode)	
		throws MdbException {
		
		String sql = " SELECT count(cnt_id)  FROM cnt_mst  WHERE sjtcode = :sjtcode";
		
		List at = new ArrayList();
		at.add(sjtcode);		
	
		return oDb.selectOneInt( sql, at);
	}
	
	
	
	public String insertContentsIntoLecChp (MdbDriver oDb, String sjtcode, String cnt_id, String leccode)
		throws MdbException {
		
		String sql = "{call MI_CONTENTS.contentsImport(?, ?, ?, ?)}";

		CallableStatement cstmt = null;
		
		String rValue = "";
		
		try {
			cstmt = oDb.prepareCall(sql);
			cstmt.setString(1, leccode);
			cstmt.setString(2, sjtcode);
			cstmt.setString(3, cnt_id);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			
			cstmt.execute();
			
			rValue = cstmt.getString(4);
			
			oDb.commit();
			
		} catch (Exception e) {
			oDb.rollback();
		}
		
		return rValue;
		
	}
	
	/**
	 * 해당 컨텐츠를 사용하는 강좌 목록을 가져온다.
	 * @param oDb
	 * @param cnt_id
	 * @return
	 * @throws MdbException
	 */
	public List getLecture(MdbDriver oDb, String cnt_id) throws MdbException {
		String sql = " SELECT lm.cnt_id, lm.leccode, lm.lecenam"   +
			"   FROM cnt_mst cm, lec_mst lm"   +
			"  WHERE cm.cnt_id = lm.cnt_id" +
			"		and cm.cnt_id = :cnt_id" ;
		Map param = new HashMap();
		param.put("cnt_id", cnt_id);
		return oDb.selector(Map.class, sql, param);
	}
}
