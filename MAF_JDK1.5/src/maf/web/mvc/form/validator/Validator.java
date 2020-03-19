/*
 * Created on 2006. 5. 12.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.form.validator;

/**
 * 추상 클래스로서 검증기능을 제공하는 모든 클래스가 상속 받아야함
 * 
 * @author 최범균
 * # 2006.5.12 김상준 maf로 변환 적용 
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
