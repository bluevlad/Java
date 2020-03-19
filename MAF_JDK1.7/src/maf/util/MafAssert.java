/*
 * Created on 2006. 10. 10
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.util;

import maf.MafUtil;
import maf.web.exception.MissingParameterException;
import maf.web.http.MyHttpServletRequest;

/**
 * �ʿ������� ������ Exception�� throw��. 
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
	 * request�� ���� �Ѱ��� �Դ��� Ȯ�� 
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

