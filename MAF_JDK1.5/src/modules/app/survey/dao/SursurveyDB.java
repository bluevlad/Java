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

public class SursurveyDB {
	private static SursurveyDB instance;

	private Log logger = LogFactory.getLog(this.getClass());

	private SursurveyDB() {
	}

	public static synchronized SursurveyDB getInstance() {
		if (instance == null)
			instance = new SursurveyDB();
		return instance;
	}

	/**
	 * 목록 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public void getList(MdbDriver oDb, NavigatorInfo navigator, Map param,
			String[] searchFields) throws MdbException {

		List list = null;

		final String sql = " select * from sur_survey ";
		StringBuffer sWhere = new StringBuffer();
		String t = null;

		for (int i = 0; i < searchFields.length; i++) {
			t = (String) param.get(searchFields[i]);
			if (!MafUtil.empty(t)) {
				if (sWhere.length() > 0) {
					sWhere.append(" and ");
				} else {
					sWhere.append(" where ");
				}
				sWhere.append(" instr(" + searchFields[i] + ", :" + searchFields[i] + ") > 0");
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
	private long getRecordCount(MdbDriver oDb, Map param, String sWhere)
			throws MdbException {
		final String sql = "select count(*) from sur_survey " + sWhere;
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
		String sql = "select gubun, survey_id, comp_id , (SELECT comp_nm FROM BAS_COMP WHERE BAS_COMP.comp_id = SUR_SURVEY.comp_id) comp_nm, update_id, update_dt, leccode, title, content," +
					" status, fdt, edt, target, sur_type, company, (SELECT comp_nm FROM BAS_COMP WHERE BAS_COMP.comp_id = SUR_SURVEY.comp_id) company_nm"+
					" from sur_survey "
				+ "where  gubun = :gubun and survey_id = :survey_id and comp_id = :comp_id ";
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
		String sql = " update sur_survey set " 
				+ " update_id = :update_id ," 
				+ " update_dt = sysdate ,"
				+ " leccode = :leccode ," 
				+ " title = :title ,"
				+ " content = :content ," 
				+ " status = :status ,"
				+ " fdt = :fdt ," 
				+ " edt = :edt ," 
				+ " target = :target ,"
				+ " sur_type = :sur_type ," 
				+ " company = :company " 
				+ "where  gubun = :gubun and survey_id = :survey_id and comp_id = :comp_id";
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
		final  String sql = "select SEQ_SURVEY_ID.nextval from dual ";
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
		String sql = " INSERT INTO sur_survey"
				+ "    (gubun,survey_id,comp_id,update_id,update_dt,leccode,title,content,status,fdt,edt,target,sur_type,company)"
				+ "  VALUES"
				+ "    (:gubun,:survey_id,:comp_id,:update_id,sysdate,:leccode,:title,:content,:status,:fdt,:edt,:target,:sur_type,:company)";

		return oDb.executeUpdate(sql, param);
	}

}
