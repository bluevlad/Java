/*
 * Created on 2006. 07. 19
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.mdb.sqlhelper.support;

import maf.mdb.sqlhelper.WhereCond;

public class WhereBetween extends WhereCond {

	private String minParamName = null;
	private String maxParamName = null;

	
	public  WhereBetween(String fieldName, String minParamName, String maxParamName, boolean force) {
		this.fieldName = fieldName;
		this.minParamName = minParamName;
		this.maxParamName = maxParamName;
		this.paramName = minParamName + " , " +maxParamName;
	}
	
	public String getWhereString() {
		return this.fieldName + " between " + this.minParamName + " and " + this.maxParamName; 
	}
	


}

