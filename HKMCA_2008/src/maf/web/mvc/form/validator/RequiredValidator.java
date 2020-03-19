/*
 * Created on 2006. 5. 12.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.form.validator;

/**
 * �ʼ� �׸� ���� �ִ��� Check
 * null �Ǵ� "" �̸� false
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

