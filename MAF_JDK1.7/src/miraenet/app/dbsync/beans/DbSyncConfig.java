/*
 * Created on 2005. 6. 29.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package miraenet.app.dbsync.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * @author goindole
 * Table ����ȭ�� ���� Map ������ ������ Bean
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

