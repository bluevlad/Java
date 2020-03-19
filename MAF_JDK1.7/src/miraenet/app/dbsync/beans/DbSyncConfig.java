/*
 * Created on 2005. 6. 29.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.dbsync.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * @author goindole
 * Table 동기화를 위한 Map 정보를 가지는 Bean
 */
public class DbSyncConfig {
	Map tables = null;
	
	public DbSyncConfig() {
		tables = new HashMap();
	}
	
	public void addTable(DbSyncTable bean) {
		tables.put(bean.getSource_table(), bean);
	}
	
	public DbSyncTable get(String table_nm) {
		return (DbSyncTable) tables.get(table_nm);
	}
	
	/**
	 * @return Returns the tables.
	 */
	public Map getTables() {
		return tables;
	}
}

