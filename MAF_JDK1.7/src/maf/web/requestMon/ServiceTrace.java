/*
 * Created on 2006. 6. 26.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.requestMon;

import java.util.Hashtable;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

public class ServiceTrace {
	private static ServiceTrace instance = null;

	private Map activelist = null;;
	private TreeMap activeStat = null;

	private long DELAY_TIME;

	private int DUMP_TRIGGER;

	private long dumped_time;
	
	private ServiceTrace() {
		DUMP_TRIGGER = 90;
		dumped_time = 0L;
		activelist = new Hashtable();
		activeStat = new TreeMap();
		DELAY_TIME = 3000L;
		try {
			DELAY_TIME = Long.parseLong(System.getProperty("trace.delaytime", "3000"));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		DUMP_TRIGGER = 90;
		try {
			DUMP_TRIGGER = Integer.parseInt(System.getProperty("trace.dumptrigger", "90"));
		} catch (Exception exception1) {
			exception1.printStackTrace();
		}
	}

	public static synchronized ServiceTrace getInstance() {
		if (instance == null) instance = new ServiceTrace();
		return instance;
	}

	public void endTrace(HttpServletRequest _req) {
		TraceObject traceobject = (TraceObject) activelist.get(_req);
		if (traceobject == null) return;
		int i = traceobject.getDepth();
		if (i > 0) {
			traceobject.setDepth(--i);
			return;
		}
		activelist.remove(_req);
		
		try {
			long l = System.currentTimeMillis() - traceobject.getStartTime();
			if (l > DELAY_TIME) {
				Logger.println("WARNING:" + l + ":" + traceobject.getURI());
			}
			String uri = _req.getRequestURI();
			RequestStat st = (activeStat.containsKey(uri))?(RequestStat) activeStat.get(uri):new RequestStat(uri);
			st.execute(l);
			activeStat.put(uri, st);

		} catch (Exception _ex) {
		}
		ObjectPool objectpool = ObjectPool.getInstance();
		objectpool.freeObject(traceobject);
	}

	public Map getActiveList() {
		return activelist;
	}
	
	public SortedMap getActiveStat() {
		return activeStat;
	}
	
	public void startTrace(HttpServletRequest _req) {
		TraceObject traceobject = (TraceObject) activelist.get(_req);
		if (traceobject != null) {
			traceobject.increaseDepth();
			return;
		}
		ObjectPool objectpool = ObjectPool.getInstance();
		traceobject = objectpool.getObject();
		try {
			traceobject.setData(_req.getQueryString() != null ? _req.getRequestURI() + '?'
					+ _req.getQueryString() : _req.getRequestURI(), _req.getRemoteAddr(),
								System.currentTimeMillis(), 0);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		activelist.put(_req, traceobject);
		if (activelist.size() >= DUMP_TRIGGER) {
			long l = System.currentTimeMillis();
			if (l - dumped_time > 0x2bf20L) {
				dumped_time = l;
				DumpThread dumpthread = new DumpThread(activelist);
				dumpthread.start();
			}
		}
	}


}
