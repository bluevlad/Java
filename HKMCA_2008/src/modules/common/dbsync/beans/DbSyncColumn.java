/*
 * Created on 2005. 6. 29.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.dbsync.beans;

/**
 * @author bluevlad
 *
 */
public class DbSyncColumn {
	private String s_nm = null;
	private String t_nm = null;
	private String pk = null;
	private String s_value = null;
	private String t_value = null;
	
	public DbSyncColumn() {
		
	}
	
	
	/**
	 * @return Returns the source_nm.
	 */
	public String getS_nm() {
		return s_nm;
	}
	/**
	 * @param source_nm The source_nm to set.
	 */
	public void setS_nm(String source_nm) {
		if(source_nm != null) {
			this.s_nm = source_nm.toLowerCase();
		}
	}
	/**
	 * @return Returns the target_nm.
	 */
	public String getT_nm() {
		return (t_nm==null)?s_nm:t_nm;
	}
	/**
	 * @param target_nm The target_nm to set.
	 */
	public void setT_nm(String target_nm) {
		if(target_nm != null) {
			this.t_nm = target_nm.toLowerCase();
		}
	}
	
	/**
	 * @return Returns the pk.
	 */
	public String getPk() {
		return pk;
	}
	
	public boolean isPkey() {
		return ("T".equals(pk)?true:false);
	}
	
	/**
	 * @param pk The pk to set.
	 */
	public void setPk(String pk) {
//		miraenet.logger.Logging.log(this.getClass(), "pk="+pk);
		this.pk = pk;
	}
	
	
	/**
	 * @return Returns the value.
	 */
	public String getT_value() {
		return t_value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setT_value(String value) {
		this.t_value = value;
	}
	
	
	/**
	 * @return Returns the s_value.
	 */
	public String getS_value() {
		return s_value;
	}
	/**
	 * @param s_value The s_value to set.
	 */
	public void setS_value(String s_value) {
		this.s_value = s_value;
	}
}

