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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafInputTag extends MafHtmlTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(this.getClass());
	private TagWriter tagWriter;
	private String maxlength;
	private String alt;
	private String onselect;
	private String size;
	private String readonly;
	private String value;
	private String type;
	private String name;
	private String option;
	private String curvalue;
	private String checked;
	private String match;
	private boolean required;

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	
	protected String getMaxlength() {
		return this.maxlength;
	}

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

	public void setSize(String size) {
		this.size = size;
	}

	protected String getSize() {
		return this.size;
	}

	public String getChecked() {
    	return checked;
    }

	public void setChecked(String checked) {
    	this.checked = checked;
    }

	public void setReadonly(String readonly) {
		try {
			this.readonly = evaluateString("readonly", readonly);
			;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	protected boolean getReadonly() {
		return StringUtils.toBoolean(this.readonly);
	}

	public void setType(String type) {
		this.type = StringUtils.toLowerCase(type);
	}

	protected String getType() {
		return this.type;
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

	public void setRequired(String t) {
		this.required = StringUtils.toBoolean(t);
	}

	protected boolean getRequired() {
		return this.required;
	}

	public void setOption(String size) {
		this.option = size;
	}

	protected String getOption() {
		return this.option;
	}

	public void setCurValue(String size) {
		this.curvalue = size;
	}

	protected String getCurValue() {
		return this.curvalue;
	}
	
	public void setMatch(String match) {
		this.match = match;
	}
	public String getMatch() {
		return this.match;
	}
	/**
	 * Writes the '<code>input</code>' tag to the supplied {@link TagWriter}.
	 * Uses the value returned by {@link #getType()} to determine which
	 * type of '<code>input</code>' element to render.
	 */
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		this.tagWriter = tagWriter;
		tagWriter.startTag("input");
		tagWriter.writeAttribute("type", getType());
		writeDefaultAttributes(tagWriter);
		writeOptionalAttribute(tagWriter, "size", getSize());
		writeOptionalAttribute(tagWriter, "maxlength", getMaxlength());
		writeOptionalAttribute(tagWriter, "alt", getAlt());
		writeOptionalAttribute(tagWriter, "checked", getChecked());
		writeOptionalAttribute(tagWriter, "onselect", getOnselect());
		if (getReadonly()) {
			tagWriter.writeOptionalAttributeValue("readonly", "true");
		}
		// 확장된 부문 
		FormConfig fc = super.getFormConfig();
		if (fc != null) {
			if (MafUtil.empty(getHname())) {
				try {
					String tt= super.getFieldLabel(getName());
					if(tt != null && !tt.startsWith("@@")) {
					     writeOptionalAttribute(tagWriter, "hname",tt );
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			} else {
				writeOptionalAttribute(tagWriter, "hname", getHname());
			}
			InputConfig ic = fc.getInputConfig(getName());
			if (ic != null) {
				if(ic.isRequired() || this.getRequired()) {
					writeOptionalAttribute(tagWriter, "required", "true");
				}
			} else {
				if( this.getRequired()) {
					writeOptionalAttribute(tagWriter, "required", "true");
				}
			}
			//Todo : constraiont 추가 
		} else {
			writeOptionalAttribute(tagWriter, "hname", getHname());
			if (this.getRequired()) {
				writeOptionalAttribute(tagWriter, "required", "true");
			}
		}
		// for JS Validation 
		writeOptionalAttribute(tagWriter, "option", getOption());
		writeOptionalAttribute(tagWriter, "match", getMatch());
		
		String t = evaluateString("curvalue", getCurValue());
		String v = evaluateString("value", getValue());
		if (!MafUtil.empty(v)) {
			if (v.equals(t)) {
				if ("radio".equals(type) || "checkbox".equals(type) ) {
					writeOptionalAttribute(tagWriter, "checked", "true");
				}
			}
		}
		if ("date".equals(type)) {
			writeOptionalAttribute(tagWriter, "dojoType", "DropdownDatePicker");
		}
		tagWriter.writeAttribute("value", v);
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