/*
 * Created on 2005. 12. 12.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.web.mvc.pool;

import maf.base.BasePoolableObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;

public class MafMvcPoolFactory extends BaseKeyedPoolableObjectFactory {

	Log logger = LogFactory.getLog(MafMvcPoolFactory.class);

	/**
	 * Ǯ�� ���� ������ ��ü�� �����Ѵ�.
	 */
	public Object makeObject(Object key) throws Exception {

//		Class commandClass = Class.forName((String) key);
//		return  commandClass.newInstance();
		return ((Class)key).newInstance();
	}

	/**
	 * Ǯ���� ���� ��ü�� Ȱ��ȭ �Ѵ�.
	 */
	public void activateObject(Object key, Object obj) throws Exception {
		((BasePoolableObject) obj).setActive(true);
	}

	/**
	 * Ǯ�� �ǵ��ư��°�ü�� ��Ȱ��ȭ ��Ų��.
	 */
	public void passivateObject(Object key, Object obj) throws Exception {
		((BasePoolableObject) obj).setActive(false);
	}

	/**
	 * Ǯ�� ��ü�� �ٽ� �ֱ� ���� ��ü�� �����ϰ� Ǯ�� ���� �� �ִ��� �˻��Ѵ�.
     * ��ü�� �ٽ� ���� �� ���� ��� false�� �����Ѵ�.
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
