/*
 * Created on 2006. 08. 22
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.beans.support;

import maf.beans.NavigatorOrderInfo;

import org.apache.commons.lang.StringEscapeUtils;


public class NavigatorOrderInfoUtils {
	public static String getOrderSql(NavigatorOrderInfo orderInfo) {
		if(orderInfo != null && orderInfo.getOrder() != null && orderInfo.getKey() != null) {
			if ("A".equals(orderInfo.getOrder())) {
				return " ORDER BY " + StringEscapeUtils.escapeSql(orderInfo.getKey()) ;
			} else {
				return " ORDER BY " + StringEscapeUtils.escapeSql(orderInfo.getKey()) + " DESC ";
			}
		} else {
			return "";
		}
	}
}

