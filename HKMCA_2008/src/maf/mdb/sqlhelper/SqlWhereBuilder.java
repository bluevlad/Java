/*
 * Created on 2006. 07. 19
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.mdb.sqlhelper;

import java.util.Map;



public interface SqlWhereBuilder {

	SqlWhereBuilder addCond(WhereCond cond);
	
	String getWhereString(Map param);	

	boolean hasConds();

}

