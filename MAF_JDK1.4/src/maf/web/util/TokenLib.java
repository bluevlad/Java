/*
 * Created on 2005. 8. 10.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import maf.MafProperties;
import maf.lib.bean.TokenBean;
import maf.web.TokenProcessor;

/**
 *
 */
public class TokenLib {
	public static TokenBean getToken(HttpServletRequest request) {
		TokenBean tb = new TokenBean();
		
		String token = TokenProcessor.getInstance().generateToken(request);
		TokenProcessor.getInstance().saveToken(request);
		HttpSession session = request.getSession(false);
		session.setAttribute(MafProperties.TRANSACTION_TOKEN_KEY, token);
		
		tb.setKey(MafProperties.TRANSACTION_TOKEN_KEY);
		tb.setValue(token);
		return tb;
	}
}

