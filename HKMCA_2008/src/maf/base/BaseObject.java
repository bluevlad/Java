/*
 * Created on 2006. 5. 9.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.base;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		try {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		} catch (Exception e) {
			return "";
		}
	}
	
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
}

