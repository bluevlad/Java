/*
 * Created on 2006. 6. 22.
 *
 * Copyright (c) 2006 UBQ  All rights reserved.
 */
package modules.wlc.classroom.tutor;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 강의안 관리 
 * @author bluevlad
 *
 */
public class StdListAction extends BaseTutorClassAction {
	private final String MESSAGE_BUNDLENAME = "common.message";
    final private Log  logger = LogFactory.getLog(this.getClass());

	/**
	 * 목록 조회
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {"s_userid", "s_nm"};

		// Order default 값 설정 시
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("lec_cd", super.lectureInfo.getLec_cd());
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();

    	wb.addCond(Where.like("userid",":s_userid"));  
    	wb.addCond(Where.like("nm",":s_nm"));      

		StdDB.getList(super.oDb, navigator, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}

}