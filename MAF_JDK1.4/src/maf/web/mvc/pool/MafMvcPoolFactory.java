/*
 * Created on 2005. 12. 12.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.pool;

import maf.base.BasePoolableObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;

public class MafMvcPoolFactory extends BaseKeyedPoolableObjectFactory {

	Log logger = LogFactory.getLog(MafMvcPoolFactory.class);

	/**
	 * 풀에 의해 관리될 객체를 생성한다.
	 */
	public Object makeObject(Object key) throws Exception {

//		Class commandClass = Class.forName((String) key);
//		return  commandClass.newInstance();
		return ((Class)key).newInstance();
	}

	/**
	 * 풀에서 사용될 객체를 활성화 한다.
	 */
	public void activateObject(Object key, Object obj) throws Exception {
		((BasePoolableObject) obj).setActive(true);
	}

	/**
	 * 풀에 되돌아가는객체를 비활성화 시킨다.
	 */
	public void passivateObject(Object key, Object obj) throws Exception {
		((BasePoolableObject) obj).setActive(false);
	}

	/**
	 * 풀에 객체를 다시 넣기 전에 객체를 안전하게 풀에 넣을 수 있는지 검사한다.
     * 객체를 다시 넣을 수 없는 경우 false를 리턴한다.
	 */
	public boolean validateObject(Object key, Object obj) {
		//		if(logger.isDebugEnabled()) {
		//			logger.debug(" < " +key+ " validate Object : [" + chk + "]");
		//		}
		return ((BasePoolableObject) obj).isTimeOut();
	}

	public void destroyObject(Object key, Object obj) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug(" > " + key + " destroyObject.");
		}
		super.destroyObject(key, obj);
	}
}
