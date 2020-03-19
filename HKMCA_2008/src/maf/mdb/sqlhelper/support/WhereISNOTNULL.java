/*
 * Created on 2006. 07. 19
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.mdb.sqlhelper.support;

import maf.mdb.sqlhelper.WhereCond;

public class WhereISNOTNULL extends WhereCond {


	public  WhereISNOTNULL(String fieldName, boolean force) {
		this.fieldName = fieldName;
		this.force = force;
	}

	public String getWhereString() {
		return this.fieldName + " IS NOT NULL ";
	}


}

