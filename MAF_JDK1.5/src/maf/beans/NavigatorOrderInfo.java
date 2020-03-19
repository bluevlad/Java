/*
 * Created on 2006. 5. 30.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.beans;

import maf.base.BaseObject;
import maf.beans.support.NavigatorOrderInfoUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NavigatorOrderInfo extends BaseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(this.getClass());
	private String order = "A";
	private String orderParam = "";
	private String key = null;
	private boolean ordSpecified = false;
	
	/**
	 * 
	 * @param param "(A|D):FieldName"
	 */
	public NavigatorOrderInfo(String param) {
		try{
			this.orderParam = param;
			if (orderParam != null) {
				String[]  t = orderParam.split(":",2);
				
				if(t.length > 1) {
					this.order = ("A".equals(t[0])) ? "A" : "D";
					this.key = t[1];
					if (key != null) {
						key = key.replaceAll(" ","");
						ordSpecified = true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("param = [" + orderParam  + "]" +e.getMessage(),e);
		}
	}
	
	public NavigatorOrderInfo(String fieldname, boolean isdescending) {
		this.key = fieldname;
		this.order = (isdescending)?"D":"A";
	}
	/**
	 * @return Returns the key.
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key The key to set.
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return Returns the ord.
	 */
	public String getOrder() {
		return order;
	}
	/**
	 * @param ord The ord to set.
	 */
	public void setOrder(String ord) {
		this.order = ord;
	}
	
	/**
	 * order option을 String으로 전환 
	 * @return
	 */
	public String getParam() {
		return this.orderParam;
	}
	
	public String getOrderSql() {
		return NavigatorOrderInfoUtils.getOrderSql(this);
	}
	/**
	 * @return the ordSpecified
	 */
	public boolean isOrdSpecified() {
		return ordSpecified;
	}
	

}

