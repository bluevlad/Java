/*
 * Created on 2006. 5. 9.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.multipart;

import javax.servlet.http.HttpServletRequest;

public class MultiPartChecker {
	public static boolean isMultiPart(HttpServletRequest hRequest) {
		return (hRequest.getHeader("content-type") != null && 
				   hRequest.getHeader("content-type").indexOf("multipart/form-data") != -1);
	}
}

