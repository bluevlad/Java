/*
 * Created on 2006. 2. 20.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.lib.system;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JavaGCreq {
	static private JavaGCreq _instance = null;
	static Log logger = null;
	private int cnt = 1;
	
	private JavaGCreq() {
		logger = LogFactory.getLog(JavaGCreq.class);
		logger.info(this.getClass() + " is folked!!!");
	}
	
	/**
	 * Get Single Instance
	 */
	public static JavaGCreq getInstance() {
		if (_instance == null) {
			_instance = new JavaGCreq();
		}
		return _instance;
	}
	
	
	public void chk() {
		if(cnt % 1000 == 0) {
			this.runGC();
			cnt = 0;
		};
		cnt ++;		
	}
	/**
	 * 
	 * 
	 */
	private synchronized void runGC() {
		Runtime rt = Runtime.getRuntime();
		
		long free = rt.freeMemory();
		long total = rt.totalMemory();
		long usedRatio = (total - free) * 100 / total;

		if ((usedRatio) > 60) {
			logger.info(">> GC start ");
			System.gc();
			System.runFinalization();
			logger.info("<< GC Finish !!!");
		}
	}
	
}

