/*
 * Created on 2005. 12. 13.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
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
 * Pooling을 관리
 * @author goindole
 *
 */
public class MvcResourcePool {
	private MafMvcPoolFactory objFactory = null;
	private GenericKeyedObjectPool objectPool = null;
	private Log logger = LogFactory.getLog(getClass());
	
	private static MvcResourcePool _instance;
	
	private MvcResourcePool() {
    	objFactory = new MafMvcPoolFactory();
    	objectPool = new GenericKeyedObjectPool(objFactory);
    	objectPool.setWhenExhaustedAction(GenericKeyedObjectPool.WHEN_EXHAUSTED_FAIL);
    	objectPool.setTestWhileIdle(true);
    	objectPool.setMaxIdle(100);
    	objectPool.setMaxActive(200);
    	// 	maxActive개수를 넘기면 maxWait만큼 기다린 후 예외발생
    	objectPool.setMaxWait(10000);
//    	objectPool.setTimeBetweenEvictionRunsMillis(10*60*60*1000);
    	objectPool.setTimeBetweenEvictionRunsMillis(10*60*1000);
	}
	/**
	 * 자신의 인스턴스를 생성하여 반환한다. 싱글톤
	 * @return
	 */
	public static synchronized MvcResourcePool getInstance() {
		if (_instance == null)
			_instance = new MvcResourcePool();
		return _instance;
	}
	/**
	 *  해당 클래스를 빌려온다.
	 * @param className
	 * @return
	 */
	public MafCommand getObject(Class className)  throws MafException {
		MafCommand obj = null;

		try {
			obj = (MafCommand) objectPool.borrowObject(className);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MafException(e.getMessage());
		}
		return obj;
	}
	/**
	 * 사용이 끝난 클래스를 pool로 반환한다.
	 * @param className
	 * @param obj
	 */
	public void returnObject(Class className, Object obj) {
		try{
			objectPool.returnObject(className, obj);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
//		logger.info("Active / MaxActive = " +objectPool.getNumActive()+"/" +objectPool.getMaxActive()+
//		            	", NumIdle / MaxIdle = "  + objectPool.getNumIdle()+"/" +objectPool.getMaxIdle());
	}
	
	
	public final Map getStatus() {
		Map statusMap = new HashMap();
		
		statusMap.put("MaxActive", objectPool.getMaxActive()+"");
		statusMap.put("NumActive", objectPool.getNumActive()+"");
		statusMap.put("MaxIdle", objectPool.getMaxIdle()+"");
		statusMap.put("NumIdle", objectPool.getNumIdle()+"");
		statusMap.put("TimeBetweenEvictionRunsMillis", objectPool.getTimeBetweenEvictionRunsMillis()+"");
		return statusMap;
	}
}

