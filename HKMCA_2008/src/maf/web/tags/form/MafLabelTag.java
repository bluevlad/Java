/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.tags.form;

import java.io.IOException;
import java.util.MissingResourceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import maf.MafUtil;
import maf.context.exceptions.NoSuchBundleException;
import maf.context.exceptions.NoSuchMessageException;
import maf.context.support.LocaleUtil;
import maf.web.mvc.configuration.FormConfig;
import maf.web.mvc.configuration.InputConfig;
import maf.web.tags.TagWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafLabelTag extends MafAbstractHtmlElementTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(this.getClass());
	private TagWriter tagWriter;
	
	// *********************************************************************
	// Protected state
	protected String nameAttrValue; // 'key' attribute value
	protected boolean nameSpecified = false;; // 'key' attribute specified
	protected String forAttrValue; // 'key' attribute value
	protected boolean forSpecified = false;; // 'key' attribute specified

	public void setName(String key) throws JspTagException {
		this.nameAttrValue = key;
		this.nameSpecified = true;
	}

	public void setFor(String key) throws JspTagException {
		this.forAttrValue = key;
		this.forSpecified = true;
	}
	
	public int writeTagContent(TagWriter tagWriter) throws JspException {
		this.tagWriter = tagWriter;
		String fieldName = null;
		// determine the message key by...
		if (nameSpecified) {
			// ... reading 'key' attribute
			fieldName = nameAttrValue;
		}
		if ((fieldName == null) || fieldName.equals("")) {
			try {
				pageContext.getOut().print("??????");
			} catch (IOException ioe) {
				throw new JspTagException(ioe.getMessage());
			}
			return EVAL_PAGE;
		}

		FormConfig mf = super.getFormConfig();
		
		tagWriter.startTag("label");
		if(forSpecified) {
			tagWriter.writeOptionalAttributeValue("for", super.evaluateString("for", forAttrValue));
		} else {
			tagWriter.writeOptionalAttributeValue("for", fieldName);
		}
		if(mf != null) {
			InputConfig inputConfig = mf.getInputConfig(fieldName);
			if (inputConfig != null && inputConfig.isRequired()) {
				tagWriter.writeOptionalAttributeValue("class", "lbl_required");
			}
		}
		tagWriter.appendValue(super.getFieldLabel(fieldName));
		tagWriter.endTag();
		return EVAL_PAGE;
	}
}
