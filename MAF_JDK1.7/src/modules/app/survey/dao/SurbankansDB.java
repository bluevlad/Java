/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.app.survey.dao;

import java.util.List;
import java.util.Map;

import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SurbankansDB {
	private static SurbankansDB instance;
	private Log logger = LogFactory.getLog(this.getClass());
	
	private SurbankansDB() {
	}

	public static synchronized SurbankansDB getInstance() {
		if (instance == null) instance = new SurbankansDB();
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
	public void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String[] searchFields) throws MdbException {
		
		List list = null;

		final String sql = " select * from sur_bank_ans ";
		StringBuffer sWhere = new StringBuffer();
		String t = null;
		
		for(int i = 0 ;i < searchFields.length; i++) {
			t = (String) param.get(searchFields[i]);
			if(!MafUtil.empty(t)) {
				if (sWhere.length() > 0 ) {
					sWhere.append(" and " );
				} else {
					sWhere.append(" where ");
				}
				sWhere.append( " instr(" + searchFields[i] + ", :" + searchFields[i] + ") > 0" );
			}
		}
		
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
		final  String sql = "select count(*) from sur_bank_ans " + sWhere;
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
	public List getOne(MdbDriver oDb, Map param) throws MdbException {
		List list = null;
		final String sql = "select * from sur_bank_ans where bank_id = :bank_id and gubun = :gubun and comp_id = :comp_id order by bank_ans_id";
		list = oDb.selector(Map.class, sql, param);
		return list;
	}

	
	/**
	 * 하나의 레코드를 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int updateOne(MdbDriver oDb, Map param,int bank_ans_id, String reply,String score) throws MdbException {
		String sql = " update sur_bank_ans set "   +
		 " update_id = '테스트' ," +
		 " reply = :reply ," +
		 " score = :score ," +
		 " r_image = :r_image ," +
		 " r_audio = :r_audio ," +
		 " update_dt = sysdate " +
		 " where  gubun = :gubun and bank_id = :bank_id and bank_ans_id = :bank_ans_id and comp_id = :comp_id";
		param.put("reply",reply);
		param.put("bank_ans_id",""+bank_ans_id);
		param.put("score",""+score);
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
	public int InsertOne(MdbDriver oDb, Map param,int bank_ans_id, String reply,String score) throws MdbException {
		String sql = " INSERT INTO sur_bank_ans"   +
		"    (gubun,bank_id,bank_ans_id,comp_id,update_id,reply,r_image,r_audio,update_dt,score)"   +
		"  VALUES"   +
		"    (:gubun,:bank_id,:bank_ans_id,:comp_id,'',:reply,'','',sysdate,:score)"  ;
		
		param.put("reply",reply);
		param.put("bank_ans_id",""+bank_ans_id);
		param.put("score",""+score);
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 레코드를 delete 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int deleteAll(MdbDriver oDb, Map param) throws MdbException {
		String sql = " delete from sur_bank_ans"   +
		" where  gubun = :gubun and bank_id = :bank_id and comp_id = :comp_id";
		
		return oDb.executeUpdate(sql, param);
	}
	
}

			