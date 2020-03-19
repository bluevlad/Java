/*
 * Created on 2006. 5. 16.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.message;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ErrorMessageSet {
	public static final String ATTRIBUTE_NAME = "JAVACAN:ERROR_MESSAGE_SET:NAME";

	/** ErrorMessage의 목록을 저장하는 리스트 */
	private List errorMessageList = new java.util.ArrayList(4);

	public ErrorMessageSet() {
	}

	public void addErrorMessage(ErrorMessage error) {
		errorMessageList.add(error);
	}

	public List getErrorMessageList() {
		return errorMessageList;
	}

	public void putInto(HttpServletRequest request) {
		request.setAttribute(ATTRIBUTE_NAME, this);
	}

	public static ErrorMessageSet getFrom(HttpServletRequest request) {
		return (ErrorMessageSet) request.getAttribute(ATTRIBUTE_NAME);
	}
}
