/*
 * Created on 2006. 07. 19
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.mdb.sqlhelper;

import maf.mdb.sqlhelper.support.WhereBetween;
import maf.mdb.sqlhelper.support.WhereEQ;
import maf.mdb.sqlhelper.support.WhereIN;
import maf.mdb.sqlhelper.support.WhereISNOTNULL;
import maf.mdb.sqlhelper.support.WhereISNULL;
import maf.mdb.sqlhelper.support.WhereLESS;
import maf.mdb.sqlhelper.support.WhereLike;
import maf.mdb.sqlhelper.support.WhereMORE;
import maf.mdb.sqlhelper.support.WhereNE;
import maf.mdb.sqlhelper.support.WhereNotIN;

public class Where {
	/**
	 * Equal
	 * @param fieldName
	 * @param paramName
	 * @return
	 */
	public static WhereCond eq (String fieldName, String paramName) {
		return new WhereEQ(fieldName, paramName, false);
	}
	/**
	 * Equal
	 * @param fieldName
	 * @param paramName
	 * @return
	 */	
	public static WhereCond eq (String fieldName, String paramName, boolean force) {
		return new WhereEQ(fieldName, paramName, force);
	}
	/**
	 * Not Equal
	 * @param fieldName
	 * @param paramName
	 * @return
	 */
	public static WhereCond ne (String fieldName, String paramName) {
		return new WhereNE(fieldName, paramName, false);
	}
	/**
	 * Not Equal
	 * @param fieldName
	 * @param paramName
	 * @param force
	 * @return
	 */
	public static WhereCond ne (String fieldName, String paramName, boolean force) {
		return new WhereNE(fieldName, paramName, force);
	}
	/**
	 * in ("name" , "('abc','bcd','efg')" )
	 * in ("name", oDb.getInString(Object[]))
	 * @param fieldName
	 * @param paramName
	 * @return
	 */
	public static WhereCond in (String fieldName, String paramName) {
		return new WhereIN(fieldName, paramName, false);
	}
	public static WhereCond in (String fieldName, String paramName, boolean force) {
		return new WhereIN(fieldName, paramName, force);
	}
	public static WhereCond notin (String fieldName, String paramName) {
		return new WhereNotIN(fieldName, paramName, false);
	}
	public static WhereCond notin (String fieldName, String paramName, boolean force) {
		return new WhereNotIN(fieldName, paramName, force);
	}	
	/**
	 * 대소문자 구분 안함 
	 * @param fieldName
	 * @param paramName
	 * @return
	 */
	public static WhereCond like (String fieldName, String paramName) {
		return new WhereLike(fieldName, paramName, false);
	}
	/**
	 * 대소문자 구분 안함 
	 * @param fieldName
	 * @param paramName
	 * @param force
	 * @return
	 */
	public static WhereCond like (String fieldName, String paramName, boolean force) {
		return new WhereLike(fieldName, paramName, force);
	}
	public static WhereCond between (String fieldName, String minParam, String maxParam) {
		return new WhereBetween(fieldName, minParam, maxParam, false);
	}
	public static WhereCond between (String fieldName, String minParam, String maxParam, boolean force) {
		return new WhereBetween(fieldName, minParam, maxParam, force);
	}
	public static WhereCond more (String fieldName, String paramName) {
		return new WhereMORE(fieldName, paramName, false);
	}
	public static WhereCond more (String fieldName, String paramName, boolean force) {
		return new WhereMORE(fieldName, paramName, force);
	}
	public static WhereCond less (String fieldName, String paramName) {
		return new WhereLESS(fieldName, paramName, false);
	}
	public static WhereCond less (String fieldName, String paramName, boolean force) {
		return new WhereLESS(fieldName, paramName, force);
	}
    public static WhereCond isnull (String fieldName) {
        return new WhereISNULL(fieldName, false);
    }
    public static WhereCond isnull (String fieldName, boolean force) {
        return new WhereISNULL(fieldName, force);
    }
    public static WhereCond isnotnull (String fieldName) {
        return new WhereISNOTNULL(fieldName, false);
    }
    public static WhereCond isnotnull (String fieldName, boolean force) {
        return new WhereISNOTNULL(fieldName, force);
    }
}

