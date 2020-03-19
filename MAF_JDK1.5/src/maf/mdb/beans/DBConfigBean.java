/*
 * Created on 2005. 11. 21.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.mdb.beans;

import java.util.ArrayList;
import java.util.List;

public class DBConfigBean {
	private List connList = null;
	private String defaultConn = null;
	
	public DBConfigBean(){
		connList = new ArrayList();
	}
	
	public void addConn(DbcpInfoBean bean) {
		connList.add(bean);
		System.out.println("  Connection : " + bean.getName() + " Configured. ");
		System.out.println("    getDriverClassName : " + bean.getDriverClassName());
		System.out.println("    getMaxActive : " + bean.getMaxActive());
		System.out.println("    getMaxIdle : " + bean.getMaxIdle());
		System.out.println("    getMinIdle : " + bean.getMinIdle());
		System.out.println("    getMaxWait : " + bean.getMaxWait());
		System.out.println("    getUrl : " + bean.getUrl());
		System.out.println("    isDefaultAutoCommit : " + bean.isDefaultAutoCommit());
		System.out.println("    isDefaultReadOnly : " + bean.isDefaultReadOnly());
	}
	
	public List getConnList() {
		return this.connList;
	}

	/**
	 * @return Returns the defaultConn.
	 */
	public String getDefaultConn() {
		return defaultConn;
	}

	/**
	 * @param defaultConn The defaultConn to set.
	 */
	public void setDefaultConn(String defaultConn) {
		this.defaultConn = defaultConn;
	}
	
}

