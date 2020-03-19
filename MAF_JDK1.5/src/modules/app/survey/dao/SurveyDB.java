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

public class SurveyDB {
	private static SurveyDB instance;
	private Log logger = LogFactory.getLog(this.getClass());
	
	private SurveyDB() {
	}

	public static synchronized SurveyDB getInstance() {
		if (instance == null) instance = new SurveyDB();
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

		final String sql = " select GUBUN, BANK_ID, COMP_ID, UPDATE_ID, UPDATE_DT, PRODUCT_ID, JOB_ID, ROLE_ID, TITLE, QUESTION, Q_IMAGE, Q_AUDIO, decode(ETC_YN,'1','Y','N') etc_yn, etc_yn etc_yn_nm, decode(COMMENT_YN,'1','Y','N') comment_yn,comment_yn comment_yn_nm, SKILL_ID, decode(que_type,'1','N지선다형','2','다중선택','3','단답형','4','서술형') que_type, que_type que_type_nm" +
				" from SUR_BANK";
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
	 * 문제은행찾기 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public void getBankList(MdbDriver oDb, NavigatorInfo navigator, Map param, String[] searchFields) throws MdbException {
		
		List list = null;

		final String sql = " select GUBUN, BANK_ID, COMP_ID, UPDATE_ID, UPDATE_DT, PRODUCT_ID, JOB_ID, ROLE_ID, TITLE, QUESTION, Q_IMAGE, Q_AUDIO, decode(ETC_YN,'1','Y','N') etc_yn, etc_yn etc_yn_nm, decode(COMMENT_YN,'1','Y','N') comment_yn,comment_yn comment_yn_nm, SKILL_ID, decode(que_type,'1','N지선다형','2','다중선택','3','단답형','4','서술형') que_type, que_type que_type_nm" +
				" from SUR_BANK";
		StringBuffer sWhere = new StringBuffer();
		String t = null;
		//나중에 세션에서 회사아이디 가져오기
		sWhere.append(" where comp_id='1'");
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
		final  String sql = "select count(*) from sur_bank " + sWhere;
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
		String sql = " select GUBUN, BANK_ID, COMP_ID, (select comp_nm from bas_comp where bas_comp.comp_id = sur_bank.comp_id) comp_nm, UPDATE_ID, UPDATE_DT, PRODUCT_ID, JOB_ID, ROLE_ID, TITLE, QUESTION, Q_IMAGE, Q_AUDIO, etc_yn, comment_yn, SKILL_ID, que_type" +
		" from SUR_BANK where gubun = :gubun and bank_id = :bank_id and comp_id = :comp_id";			
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
		String sql = " UPDATE SUR_BANK set "   +
						" UPDATE_DT = SYSDATE, "   +
						" TITLE = :title, "   +
						" QUESTION = :question, "   +
						" ETC_YN = :etc_yn, "   +
						" que_type = :que_type, "   +
						" COMMENT_YN = :comment_yn"   +
						" WHERE BANK_ID = :bank_id and gubun = :gubun and comp_id = :comp_id"  ;
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 시퀀스가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public long getSeq(MdbDriver oDb) throws MdbException {
		final  String sql = "select SEQ_SUR_BANK_ID.nextval from dual ";
		return oDb.selectOneInt(sql);
	}
	
	/**
	 * 하나의 레코드를 Insert 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int InsertOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = " INSERT INTO SUR_BANK"   +
		"    (GUBUN, COMP_ID, BANK_ID, UPDATE_DT, TITLE, QUESTION, ETC_YN, COMMENT_YN,que_type)"   +
		"  VALUES"   +
		"    (:GUBUN, :comp_id, :BANK_ID, SYSDATE, :TITLE, :QUESTION, :ETC_YN, :COMMENT_YN,:que_type)"  ;
		
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
	public int InsertBankOne(MdbDriver oDb, Map param, String ls_title, String ls_question, String ls_etc_yn, String ls_comment,String ls_quetype) throws MdbException {
		String sql = " INSERT INTO SUR_BANK"   +
		"    (GUBUN, COMP_ID, BANK_ID, UPDATE_DT, TITLE, QUESTION, ETC_YN, COMMENT_YN,que_type)"   +
		"  VALUES"   +
		"    (:GUBUN, :comp_id, :BANK_ID, SYSDATE, :TITLE, :QUESTION, :ETC_YN, :COMMENT_YN,:que_type)"  ;
		
		param.put("title",ls_title);
		param.put("question",ls_question);
		param.put("etc_yn",ls_etc_yn);
		param.put("comment_yn",ls_comment);
		param.put("que_type",ls_quetype);
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * Table의 Column 정보를 돌려준다.
	 * 
	 * @param oDb
	 * @param owner
	 * @param table_name
	 * @return ColumnBean List
	 * @throws MdbException
	 */
	public void getColumnInfo(MdbDriver oDb, NavigatorInfo navigator,Map param)
			throws MdbException {
		
		
		List list = null;

		final String sql = " SELECT   cols.column_id AS ID, cols.column_name AS NAME, nullable,"
			+ "          data_type AS TYPE,"
			+ "          DECODE (data_type,"
			+ "                  'CHAR', char_col_decl_length,"
			+ "                  'VARCHAR', char_col_decl_length,"
			+ "                  'VARCHAR2', char_col_decl_length,"
			+ "                  'NCHAR', char_col_decl_length,"
			+ "                  'NVARCHAR', char_col_decl_length,"
			+ "                  'NVARCHAR2', char_col_decl_length,"
			+ "                  NULL"
			+ "                 ) nchar_length,"
			+ "          DECODE (data_type,"
			+ "                  'NUMBER', data_precision + data_scale,"
			+ "                  data_length"
			+ "                 ) LENGTH,"
			+ "          data_precision PRECISION, data_scale scale, data_length dlength,"
			+ "          data_default, SUBSTR (comments, 1, 240) comments, c1.POSITION pk"
			+ "     FROM SYS.DBA_COL_COMMENTS coms,"
			+ "          SYS.DBA_TAB_COLUMNS cols,"
			+ "          (SELECT a1.owner, a1.table_name, c1.column_name, c1.POSITION"
			+ "             FROM SYS.ALL_CONS_COLUMNS c1, SYS.ALL_CONSTRAINTS a1"
			+ "            WHERE a1.owner = c1.owner"
			+ "              AND a1.table_name = c1.table_name"
			+ "              AND a1.constraint_name = c1.constraint_name"
			+ "              AND a1.constraint_type = 'P'"
			+ " 			 AND a1.table_name = UPPER(:table_name)"
			+ "              AND a1.owner = UPPER(:owner) ) c1"
			+ "    WHERE coms.owner = cols.owner"
			+ "      AND coms.table_name = cols.table_name"
			+ "      AND coms.column_name = cols.column_name"
			+ "      AND coms.owner = c1.owner (+)"
			+ "      AND coms.table_name = c1.table_name(+)"
			+ "      AND coms.column_name = c1.column_name(+)"
			+ "      AND cols.table_name = upper(:table_name)"
			+ "      AND cols.owner = upper(:owner) ORDER BY column_id";
		
		
		StringBuffer sWhere = new StringBuffer();
		String sOrder = navigator.getOrderSql();

		//oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		oDb.setDebug(true);
		list = oDb.selector(Map.class, sql, param);
		navigator.setList(list);
		navigator.setTotalCount(this.getRecordCount(oDb, param, sWhere.toString()));
		navigator.sync();
		
	}
	
}
