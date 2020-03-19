/*
 * Created on 2006. 4. 6.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.app._sample;

import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Sample1DB {
	private static Sample1DB instance;
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	private Sample1DB() {
	}

	public static synchronized Sample1DB getInstance() {
		if (instance == null) instance = new Sample1DB();
		return instance;
	}

	/**
	 * 목록 가져오기
	 * 
	 * @param oDb
	 * @return
	 * @throws MdbException
	 */
	public void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
		List list = null;

		final String sql = "select * from test_01";


		String sOrder = navigator.getOrderSql();

		oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		oDb.setDebug(true);
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		navigator.setTotalCount(this.getRecordCount(oDb, param, sWhere));
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
		final  String sql = "select count(*) from test_01 " + sWhere;
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
		String sql = "select * from test_01 where id = :id";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 하나의 레코르를 insert 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int insertOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = "INSERT INTO TEST_01 (" 
			  + "id, title, contents, reg_dt, stard_date, " 
			  + " end_date, category, reg_nm " 
			  + " ) "
			  + " VALUES ("
			  + " :id, :title, :contents, sysdate,  to_date(:stard_date,'YYYY-MM-DD'), " 
			  + "  to_date(:end_date,'YYYY-MM-DD'), :category, :reg_nm "
			  + ") ";

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
	public int deleteOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = "delete from test_01 where id = :id";
		return oDb.executeUpdate(sql, param);
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
		String sql = "UPDATE  TEST_01 "
				+ " SET "
				+ " title = :title,  "
				+ " contents = :contents,  "
				+ " stard_date = to_date(:stard_date,'YYYY-MM-DD'),  "
				+ " end_date = to_date(:end_date,'YYYY-MM-DD'),  "
				+ " category = :category, "
				+ " reg_nm = :reg_nm "
				+ " WHERE  id = :id ";
		return oDb.executeUpdate(sql, param);
	}
}
