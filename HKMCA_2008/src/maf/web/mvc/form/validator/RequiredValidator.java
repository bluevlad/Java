/*
 * Created on 2006. 5. 12.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.form.validator;

/**
 * 필수 항목에 값이 있는지 Check
 * null 또는 "" 이면 false
 * @author bluevlad
 *
 */
public class RequiredValidator extends Validator {
	public boolean validate(String constant, String value) {
		if (value == null || value.equals("")) {
			return false;
		} else {
			return true;
		}
	}
}

