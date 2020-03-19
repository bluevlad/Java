/*
 * Created on 2005. 7. 12.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.zipcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;



/**
 * 우편번호 검색 
 */
public class Zipcode extends MafAction{
	public void doWork(MyHttpServletRequest _req,
			HttpServletResponse _res) throws MafException{
		try {
			Map param = new HashMap();
			String keyword = _req.getP("keyword","");
			String form = _req.getP("form","form");
			String post = _req.getP("post","post");
			String addr1 = _req.getP("addr1","addr1");
			String addr2 = _req.getP("addr2","addr2");

			if(MafUtil.empty(keyword)) {
				_req.setAttribute("message", new ResultMessage("검색어를 한자이상 넣어 주세요"));
			} else {
				param.put("keyword", keyword);
				List list = ZipcodeDB.getList(oDb, param);
				_req.setAttribute("list", list);
				_req.setAttribute("keyword", param.get("keyword").toString());
			}
			_req.setAttribute("form", form);
			_req.setAttribute("post", post);
			_req.setAttribute("addr1", addr1);
			_req.setAttribute("addr2", addr2);
			result.setForward("default");
		} catch (Exception e) {
			result.setError(e, new ResultMessage("우편번호 검색 오류"));
		}
	}

}

