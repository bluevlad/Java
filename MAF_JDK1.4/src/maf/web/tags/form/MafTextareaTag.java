/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tags.form;

import javax.servlet.jsp.JspException;
import maf.MafUtil;
import maf.util.StringUtils;
import maf.web.mvc.configuration.FormConfig;
import maf.web.mvc.configuration.InputConfig;
import maf.web.tags.TagWriter;
import maf.web.tags.support.MafHtmlTagSupport;

public class MafTextareaTag extends MafHtmlTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TagWriter tagWriter;
	/**
	 * The name of the '<code>alt</code>' attribute.
	 */
	public static final String ALT_ATTRIBUTE = "alt";
	/**
	 * The name of the '<code>onselect</code>' attribute.
	 */
	public static final String ONSELECT_ATTRIBUTE = "onselect";
	/**
	 * The name of the '<code>readonly</code>' attribute.
	 */
	public static final String READONLY_ATTRIBUTE = "readonly";
	private String alt;
	private String onselect;
	private String readonly;
	private String value;
	private String name;
	private String rows;
	private String cols;
	private boolean required;

	public void setAlt(String alt) {
		this.alt = alt;
	}

	protected String getAlt() {
		return this.alt;
	}

	public void setOnselect(String onselect) {
		this.onselect = onselect;
	}

	protected String getOnselect() {
		return this.onselect;
	}

	public void setRequired(String t) {
		this.required = StringUtils.toBoolean(t);
	}

	protected boolean getRequired() {
		return this.required;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	protected String getReadonly() {
		return this.readonly;
	}

	public void setValue(String size) {
		this.value = size;
	}

	protected String getValue() {
		return this.value;
	}

	public void setName(String size) {
		this.name = size;
	}

	protected String getName() {
		return this.name;
	}

	public void setCols(String size) {
		this.cols = size;
	}

	protected String getCols() {
		return this.cols;
	}

	public void setRows(String size) {
		this.rows = size;
	}

	protected String getRows() {
		return this.rows;
	}

	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		this.tagWriter = tagWriter;
		tagWriter.startTag("textarea");
		writeDefaultAttributes(tagWriter);
		writeOptionalAttribute(tagWriter, "rows", getRows());
		writeOptionalAttribute(tagWriter, "cols", getCols());
		writeOptionalAttribute(tagWriter, ALT_ATTRIBUTE, getAlt());
		writeOptionalAttribute(tagWriter, ONSELECT_ATTRIBUTE, getOnselect());
		writeOptionalAttribute(tagWriter, READONLY_ATTRIBUTE, getReadonly());
		if (this.getRequired()) {
			writeOptionalAttribute(tagWriter, "required", "true");
		}
		//		 확장된 부문 
		FormConfig fc = super.getFormConfig();
		if (fc != null) {
			if (MafUtil.empty(getHname())) {
				try {
					writeOptionalAttribute(tagWriter, "hname", super.getFieldLabel(getName()));
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			} else {
				writeOptionalAttribute(tagWriter, "hname", getHname());
			}
			
		} else {
			writeOptionalAttribute(tagWriter, "hname", getHname());
			if (this.getRequired()) {
				writeOptionalAttribute(tagWriter, "required", "true");
			}
		}
		tagWriter.appendValue(getDisplayString(evaluate("value", getValue())));
		tagWriter.endTag();
		return EVAL_PAGE;
	}

	public void doFinally() {
		super.doFinally();
		this.pageContext = null;
	}
	//	/**
	//	 * Writes the '<code>value</code>' attribute to the supplied {@link TagWriter}.
	//	 * Subclasses may choose to override this implementation to control exactly
	//	 * when the value is written.
	//	 */
	//	protected void writeValue(TagWriter tagWriter) throws JspException {
	//		tagWriter.writeAttribute("value", this.getValues());
	//	}
}