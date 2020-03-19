/*
 * Created on 2005. 12. 13.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.web.mvc.pool;

import java.util.HashMap;
import java.util.Map;

import maf.exception.MafException;
import maf.web.mvc.action.MafCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
/**
 * Pooling�� ����
 * @author goindole
 *
 */
public class MvcResourcePool {
	private MafMvcPoolFactory objFactory = null;
	private GenericKeyedObjectPool objectPool = null;
	private Log logger = LogFactory.getLog(getClass());
	
	private static MvcResourcePool _instance  = new MvcResourcePool();
	
	private MvcResourcePool() {
    	objFactory = new MafMvcPoolFactory();
    	objectPool = new GenericKeyedObjectPool(objFactory);
    	objectPool.setWhenExhaustedAction(GenericKeyedObjectPool.WHEN_EXHAUSTED_FAIL);
    	objectPool.setTestWhileIdle(true);
    	objectPool.setMaxIdle(100);
    	objectPool.setMaxActive(200);
    	// 	maxActive������ �ѱ�� maxWait��ŭ ��ٸ� �� ���ܹ߻�
    	objectPool.setMaxWait(10000);
//    	objectPool.setTimeBetweenEvictionRunsMillis(10*60*60*1000);
    	objectPool.setTimeBetweenEvictionRunsMillis(10*60*1000);
	}
//	/**
//	 * �ڽ��� �ν��Ͻ��� �����Ͽ� ��ȯ�Ѵ�. �̱���
//	 * @return
//	 */
//	public static synchronized MvcResourcePool getInstance() {
//		if (_instance == null)
//			_instance = new MvcResourcePool();
//		return _instance;
//	}
	/**
	 *  �ش� Ŭ������ �����´�.
	 * @param className
	 * @return
	 */
	public static MafCommand getObject(Class className)  throws MafException {
		MafCommand obj = null;

		try {
			obj = (MafCommand) _instance.objectPool.borrowObject(className);
		} catch (Exception e) {
			_instance.logger.error(e.getMessage(), e);
			throw new MafException(e.getMessage());
		}
		return obj;
	}
	/**
	 * ����� ���� Ŭ������ pool�� ��ȯ�Ѵ�.
	 * @param className
	 * @param obj
	 */
	public static  void returnObject(Class className, Object obj) {
		try{
			_instance.objectPool.returnObject(className, obj);
		} catch(Exception e) {
			_instance.logger.error(e.getMessage(), e);
		}
//		logger.info("Active / MaxActive = " +objectPool.getNumActive()+"/" +objectPool.getMaxActive()+
//		            	", NumIdle / MaxIdle = "  + objectPool.getNumIdle()+"/" +objectPool.getMaxIdle());
	}
	
	
	public static final Map getStatus() {
		Map statusMap = new HashMap();
		
		statusMap.put("MaxActive", _instance.objectPool.getMaxActive()+"");
		statusMap.put("NumActive", _instance.objectPool.getNumActive()+"");
		statusMap.put("MaxIdle", _instance.objectPool.getMaxIdle()+"");
		statusMap.put("NumIdle", _instance.objectPool.getNumIdle()+"");
		statusMap.put("TimeBetweenEvictionRunsMillis", _instance.objectPool.getTimeBetweenEvictionRunsMillis()+"");
		return statusMap;
	}
}

