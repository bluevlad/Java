/*
 * Created on 2006. 08. 22
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.beans.support;

import org.apache.commons.lang.StringEscapeUtils;

import maf.beans.NavigatorOrderInfo;

public class NavigatorOrderInfoUtils {
	public static String getOrderSql(NavigatorOrderInfo orderInfo) {
		if(orderInfo != null && orderInfo.getOrder() != null) {
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

