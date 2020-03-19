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

public class SurQuestionDB {
	private static SurQuestionDB instance;
	private Log logger = LogFactory.getLog(this.getClass());
	
	private SurQuestionDB() {
	}

	public static synchronized SurQuestionDB getInstance() {
		if (instance == null) instance = new SurQuestionDB();
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

		final String sql = " select * from sur_question ";
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
		final  String sql = "select count(*) from sur_question " + sWhere;
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
		final String sql = "select * from sur_question " 
            + "where  gubun = :gubun and survey_id = :survey_id and comp_id = :comp_id order by to_number(que_id)";
		list = oDb.selector(Map.class, sql, param);
		return list;
	}
	/*public Map getOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = "select * from sur_question " 
		             + "where  gubun = :gubun and survey_id = :survey_id and comp_id = :comp_id and que_id = :que_id";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}*/

	
	/**
	 * 하나의 레코드를 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int updateOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = " update sur_question set "   +
		 " bank_id = :bank_id ," +
		 " title = :title ," +
		 " question = :question ," +
		 " q_image = :q_image ," +
		 " q_audio = :q_audio ," +
		 " update_id = :update_id ," +
		 " update_dt = :update_dt ," +
		 " job_id = :job_id ," +
		 " role_id = :role_id ," +
		 " etc_yn = :etc_yn ," +
		 " comment_yn = :comment_yn ," +
		 " skill_id = :skill_id " +
		 "where  gubun = :gubun and survey_id = :survey_id and comp_id = :comp_id and que_id = :que_id";
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
	public int InsertOne(MdbDriver oDb, Map param,int que_id, String ls_question, String ls_que_type, String ls_etc_yn, String ls_comment_yn,String ls_bank_id) throws MdbException {
		String sql = " INSERT INTO sur_question"   +
		"    (gubun,survey_id,comp_id,que_id,title,question,update_id,update_dt,etc_yn,comment_yn,que_type,bank_id)"   +
		"  VALUES"   +
		"    (:gubun,:survey_id,:comp_id,:que_id,:question,:question,'test',sysdate,:etc_yn,:comment_yn,:que_type,nvl(:bank_id,''))"  ;
		param.put("que_id",""+que_id);
		param.put("question",ls_question);
		param.put("que_type",ls_que_type);
		param.put("etc_yn",ls_etc_yn);
		param.put("comment_yn",ls_comment_yn);
		param.put("bank_id",ls_bank_id);
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
		String sql = " delete from sur_question"   +
		" where  gubun = :gubun and survey_id = :survey_id and comp_id = :comp_id";
		
		return oDb.executeUpdate(sql, param);
	}
}
