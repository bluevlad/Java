/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tag.support;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import maf.MafUtil;
import maf.context.NoSuchBundleException;
import maf.context.NoSuchMessageException;
import maf.context.i18n.MafResourceStore;
import maf.context.support.LocaleUtil;
import maf.web.mvc.configuration.FormConfig;
import maf.web.mvc.configuration.InputConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class MafFormInputTagSupport extends TagSupport implements DynamicAttributes  {
	private Log logger = LogFactory.getLog(MafFormInputTagSupport.class);

	// *********************************************************************
	// Public constants



	// *********************************************************************
	// Protected state
	private Map dynamicAttr = null;
	protected String nameAttrValue; // 'key' attribute value

	protected String valueAttrValue; // 'key' attribute value



	// *********************************************************************
	// Constructor and initialization

	public MafFormInputTagSupport() {
		super();

		init();
	}

	private void init() {
		dynamicAttr = new HashMap();
		nameAttrValue = null;
		valueAttrValue = null;

	}
	public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
		if(localName != null) {
			dynamicAttr.put(localName.toLowerCase(), value);
		}
	}


	// *********************************************************************
	// Tag logic

	public int doStartTag() throws JspException {
		//params.clear();
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {

		String fieldName = null;
		LocalizationContext locCtxt = null;
		Locale locale = null;

		// determine the message key by...

		fieldName = nameAttrValue;


		if ((fieldName == null) || fieldName.equals("")) {
			try {
				pageContext.getOut().print("??????");
			} catch (IOException ioe) {
				throw new JspTagException(ioe.getMessage());
			}
			return EVAL_PAGE;
		}
		
		String prefix = null;


		Tag t = findAncestorWithClass(this, MafBundleSupport.class);
		if (t != null) {
			// use resource bundle from parent <bundle> tag
			MafBundleSupport parent = (MafBundleSupport) t;
			locCtxt = parent.getLocalizationContext();
			prefix = parent.getPrefix();
		} else {
			locCtxt = MafBundleSupport.getLocalizationContext(pageContext);
		}
		if (locCtxt != null) {
			locale = locCtxt.getLocale();
		}

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		FormConfig mf = (FormConfig) request.getAttribute(FormConfig.FORM_ATTRIBUTE_NAME) ;
		if(mf == null) {
			try {
				pageContext.getOut().print("?????form????");
			} catch (IOException ioe) {
				throw new JspTagException(ioe.getMessage());
			}
			return EVAL_PAGE;
		}
		String message = LocaleUtil.getNoMessageErrorString(fieldName);
		StringBuffer sHtml = new StringBuffer(20);

		if (locale != null && mf != null) {
			String baseName = mf.getBundle();
			InputConfig inputConfig = mf.getInputConfig(fieldName);
			String messageKey = null;
			if(inputConfig != null) {
				messageKey = inputConfig.getMessage();
			} else {
				try {
					pageContext.getOut().print("?????"  + mf.getBundle() + "." + fieldName + "????");
				} catch (IOException ioe) {
					throw new JspTagException(ioe.getMessage());
				}
				return EVAL_PAGE;
			}

			try {

				if (prefix != null) fieldName = prefix + fieldName;


				message = MafResourceStore.getInstance().getMessage(baseName, locale, messageKey, null);
				
				if("textarea".equals(inputConfig.getType())) {
					sHtml.append("<textarea ");
				} else {
					sHtml.append("<input type=\"" + inputConfig.getType() + "\"  ");
				}
				if (inputConfig.isRequired()) {
					sHtml.append( " required class=\"required\" ");
				}
				sHtml.append(" hname=\"" + message + "\" ");
				sHtml.append(" name=\"" + fieldName + "\" ");
				
				if(inputConfig.getMaxlength()>0 && !dynamicAttr.containsKey("maxlength")) {
					sHtml.append(" maxlength=\"" + inputConfig.getMaxlength() + "\" ");
				}
				
				if(!dynamicAttr.isEmpty()) {
					Iterator i = dynamicAttr.entrySet().iterator();
					while(i.hasNext() ) {
						Map.Entry me = (Map.Entry) i.next();
						sHtml.append( me.getKey() );
						sHtml.append( "=\"" );
						sHtml.append( me.getValue() );
						sHtml.append( "\" " );
					}
				}
				
				if("textarea".equals(inputConfig.getType())) {
					sHtml.append(" >");
					sHtml.append(MafUtil.nvl(valueAttrValue,""));
					sHtml.append("</textarea>");
				} else {
					sHtml.append(" value=\"" + MafUtil.nvl(valueAttrValue,"") + "\" ");							
					sHtml.append(" />");
				}
			} catch (MissingResourceException mre) {
				message = LocaleUtil.getNoMessageErrorString(fieldName);
			} catch (NoSuchMessageException mre) {
				message = LocaleUtil.getNoMessageErrorString(fieldName);
			} catch (NoSuchBundleException mre) {
				message = LocaleUtil.getNoBundleErrorString(baseName); 
			}
		}


		try {

			pageContext.getOut().print(sHtml.toString());
		} catch (IOException ioe) {
			throw new JspTagException(ioe.getMessage());
		}


		return EVAL_PAGE;
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		init();
	}

}
