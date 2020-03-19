package maf.mdb.sqlhelper.support;

import maf.mdb.sqlhelper.WhereCond;

public class WhereNotIN extends WhereCond {


	public  WhereNotIN(String fieldName, String paramName, boolean force) {
		this.fieldName = fieldName;
		this.paramName = paramName;
		this.force = force;
	}
	
	public String getWhereString() {
		return this.fieldName + " not in " + this.paramName; 
	}
	

}

