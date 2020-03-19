/*
 * Created on 2005. 7. 12.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.zipcode.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import miraenet.app.zipcode.dao.ZipcodeDB;

/**
 * 우편번호 검색 
 */
public class Zipcode extends MafAction{
	public void doWork(MyHttpServletRequest _req,
			HttpServletResponse _res) throws MafException{
		try {
			String keyword = _req.getP("keyword","");
			int v_page = MafUtil.parseInt(_req.getParameter("v_page"),1);
			int page_size = 200;
			if(MafUtil.empty(keyword)) {
				_req.setAttribute("message", new ResultMessage("검색어를 한자이상 넣어 주세요"));
			} else {
				Map param = new HashMap();
				param.put("keyword",keyword.trim()+"%");
				
				List list = ZipcodeDB.getZipcodeList(oDb, param, v_page, page_size);
//				int count = ZipcodeDB.getZipcodeCount(oDb, param);
				param = null;
//				_req.setAttribute("navigator", MafUtil.getNavigator(v_page, page_size, count));
				_req.setAttribute("list", list);
				
			}
			result.setForward("default");
		} catch (Exception e) {
			result.setError(e, new ResultMessage("우변번호 검색 오류"));
		}
	}

}

