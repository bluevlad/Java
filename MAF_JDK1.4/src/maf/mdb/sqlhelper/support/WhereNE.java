/*
 * Created on 2006. 07. 19
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.mdb.sqlhelper.support;

import maf.mdb.sqlhelper.WhereCond;

public class WhereNE extends WhereCond {


	public  WhereNE(String fieldName, String paramName, boolean force) {
		this.fieldName = fieldName;
		this.paramName = paramName;
		this.force = force;
	}
	
	public String getWhereString() {
		return this.fieldName + " <> " + this.paramName; 
	}
	

}

