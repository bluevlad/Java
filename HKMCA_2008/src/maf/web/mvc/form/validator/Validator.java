/*
 * Created on 2006. 5. 12.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.form.validator;

/**
 * �߻� Ŭ�����μ� ��������� �����ϴ� ��� Ŭ������ ��� �޾ƾ���
 * 
 */
public abstract class Validator {
	private String messageId;

	private String name;

	public final void setName(String name) {
		this.name = name;
	}

	public final String getName() {
		return this.name;
	}
	public final void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public final String getMessageId() {
		return this.messageId;
	}
	
	public abstract boolean validate(String constant, String value);

}
