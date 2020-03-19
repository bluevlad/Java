/*
 * Created on 2006. 6. 26.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.requestMon;

public class RequestStat {
	String requestURI = null;
	long elaspedTime = 0;
	long executeCount = 0;
	long maxTime = 0;

	public RequestStat(String requestURI) {
		this.requestURI = requestURI;
	}
	/**
	 * @return Returns the requestURI.
	 */
	public String getRequestURI() {
		return requestURI;
	}

	/**
	 * @return Returns the elaspedTime.
	 */
	public long getElaspedTime() {
		return elaspedTime;
	}
	/**
	 * @return Returns the executeCount.
	 */
	public long getExecuteCount() {
		return executeCount;
	}
	

	public void execute(long executeTime) {
		this.executeCount ++;
		this.elaspedTime += executeTime;
		
		if(elaspedTime > maxTime) {
			maxTime= elaspedTime;
		}
	}
	/**
	 * @return Returns the maxTime.
	 */
	public long getMaxTime() {
		return maxTime;
	}
	
	
}

