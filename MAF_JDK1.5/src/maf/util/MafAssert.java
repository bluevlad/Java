/*
 * Created on 2006. 10. 10
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.util;

import maf.MafUtil;
import maf.web.exception.MissingParameterException;
import maf.web.http.MyHttpServletRequest;

/**
 * 필요조건을 따져서 Exception을 throw함. 
 * @author goindole
 *
 */
public class MafAssert {
	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}
	public static void chkParam(MyHttpServletRequest _req, String key) throws MissingParameterException {
		if( ! _req.containsKey(key)) {
			throw new MissingParameterException( key );
		}
	}
	/**
	 * request에 값이 넘겨져 왔는지 확인 
	 * @param _req
	 * @param key
	 * @throws MissingParameterException
	 */
	public static void chkEmpty(MyHttpServletRequest _req, String key) throws MissingParameterException {
		if( ! _req.containsKey(key)) {
			throw new MissingParameterException( key );
		} else if (MafUtil.empty(_req.getP(key))){
			throw new MissingParameterException( key );
		}
	}
	
}

