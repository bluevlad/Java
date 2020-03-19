/*
 * Created on 2005. 6. 29.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.dbsync.beans;

import java.util.HashMap;
import java.util.Map;



import maf.MafUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bluevlad
 *
 */
public class DbSyncTable {
    private  Log logger = LogFactory.getLog(DbSyncTable.class);
	private String source_table = null;
	private String target_table = null;
	private Map columns = null;
	private Map tColumns = null;
	
	private String where_select = null;
	private String where_target = null;
	
	private String selectSql = null;
	private String updateSql = null;
	private String deleteSql = null;
	private String insertSql = null;
	
	public DbSyncTable() {
		columns = new HashMap();
		tColumns = new HashMap();
	}
	
	public void addColumn(DbSyncColumn bean) {
		if(bean.isPkey()) {
			if(where_select == null) {
				where_select = " WHERE " + bean.getS_nm() + " = :" + bean.getS_nm();
			} else {
				where_select += " AND " + bean.getS_nm() + " = :" + bean.getS_nm();
			}
			if(where_target == null) {
				where_target = " WHERE " + bean.getT_nm() + " = :" + bean.getS_nm();
			} else {
				where_target += " AND " + bean.getT_nm() + " = :" + bean.getS_nm();
			}			
		}
		if(MafUtil.empty(bean.getS_nm())) {
			// taget table에 기본 값만 넣는경우
			tColumns.put(bean.getT_nm(), bean.getT_value());
		} else {
			// Source table에서 taget에 table에 값을 넣는 경우.
			columns.put(bean.getS_nm(), bean);
		}
	}
	
	public String getSelectSql() {
		if(selectSql == null) {
			String keys[] = (String[]) columns.keySet().toArray(new String[0]);
			String sql = null;
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				DbSyncColumn bean = (DbSyncColumn) columns.get(key);
				if(sql == null) {
					sql = "SELECT  " + MafUtil.nvl(bean.getS_value(),"") + " " +  key;
				} else {
					sql += ", " + MafUtil.nvl(bean.getS_value(),"") + " " + key;
				}
			}
			selectSql = sql + " FROM " + source_table + where_select;
		} 
		return selectSql;
	}
	
	public String getUpdateSql() {
		if(updateSql == null) {
			String keys[] = (String[]) columns.keySet().toArray(new String[0]);
			String sql = null;
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				DbSyncColumn bean = (DbSyncColumn) columns.get(key);
				if (!bean.isPkey()) {
					if(sql == null) {
						sql = "UPDATE " + this.getTarget_table() + " SET " + bean.getT_nm() + " = :" + key;
					} else {
						sql += ", " + bean.getT_nm() + " = :" + key;
					}
				}
			}
			
			updateSql = sql + where_target;
		} 
		return updateSql;
	}
	
	public String getInsertSql() {
		if(insertSql == null) {
			String sCol = null;
			String tCol = null;
			String keys[] = (String[]) columns.keySet().toArray(new String[0]);
	
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				DbSyncColumn bean = (DbSyncColumn) columns.get(key);
				if(sCol == null) {
					sCol = ":" + key;
					tCol = bean.getT_nm();
				} else {
					sCol += ", :" + key;
					tCol += ", " + bean.getT_nm();
				}
			}
			
			// default value 처리 
			if(tColumns.size() > 0 ) {
				String tKeys[] = (String[]) tColumns.keySet().toArray(new String[0]);
				for (int i = 0; i < tKeys.length; i++) {
					String key = tKeys[i];
					String def_value = (String) tColumns.get(key);
					if(def_value != null) {
						if(sCol == null) {
							tCol = key;
							sCol = def_value;
						} else {
							tCol += ", " + key;
							sCol += ", " + def_value;
						}
					} else {
						logger.error("Key not found : " + key );
					}
				}
			}
			insertSql = "INSERT INTO " + this.getTarget_table() + " ( " + tCol + ") VALUES (" + sCol + " )" ;
		} 

		return insertSql;
	}
	
	public String getDeleteSql() {
		if(deleteSql == null) {
			return "DELETE FROM " + this.getTarget_table() + where_target;
		}
		return deleteSql;
	}
	
	public Map getColumns() {
		return this.columns;
	}
	/////////////////////////////////////////////////////////
	/**
	 * @return Returns the source_table.
	 */
	public String getSource_table() {
		return source_table;
	}
	/**
	 * @param source_table The source_table to set.
	 */
	public void setSource_table(String source_table) {
		if(source_table != null) {
			this.source_table = source_table.toLowerCase();
		}
	}
	/**
	 * @return Returns the target_table.
	 */
	public String getTarget_table() {
		return (target_table == null)?source_table:target_table;
	}
	/**
	 * @param target_table The target_table to set.
	 */
	public void setTarget_table(String target_table) {
		if(target_table != null) {
			this.target_table = target_table.toLowerCase();
		}
	}
}

