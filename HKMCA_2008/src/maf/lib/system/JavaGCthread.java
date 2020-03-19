/*
 * Created on 2006. 2. 20.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.lib.system;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JavaGCthread implements Runnable {
	private boolean active = false;
	private Thread t = null;
	static private JavaGCthread instance = null;

	private final int sleepTime = 60 * 1000; // milisecond

	static Log logger = LogFactory.getLog(JavaGCthread.class);

	private JavaGCthread() {
		logger.info(this.getClass() + " is folked!!!");
	}

	public void run() {
		try {
			while (active) {
				this.runGC();
				Thread.sleep(sleepTime);
			}
		} catch (InterruptedException i) {
			logger.error( i.getMessage());
		}
	}
	
	/**
	 * Get Single Instance
	 */
	public static synchronized JavaGCthread getInstance() {
		if (instance == null) {
			instance = new JavaGCthread();
		}
		return instance;
	}
	
	
	public boolean start() {

		if(t == null) {
			t = new Thread(this);
		}
		if(t != null) {
			active = true;
			if(!t.isAlive()) {
				t.start();
			}
			return t.isAlive();
		} else {
			return false;
		}
	}
	
	public boolean isAlive() {
		if (t == null) {
			return false;
		} else {
			return t.isAlive();
		}
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
		logger.info(">> GC usertRatio =  " + usedRatio );
		if ((usedRatio) > 60) {
			logger.info(">> GC start ");
			System.gc();
			System.runFinalization();
			logger.info("<< GC Finish !!!");
		}
	}
}
