/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tag.support;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import maf.context.NoSuchBundleException;
import maf.context.NoSuchMessageException;
import maf.context.i18n.MafResourceBundle;
import maf.context.i18n.MafResourceLocale;
import maf.context.i18n.MafResourceStore;
import maf.context.support.LocaleUtil;
import maf.web.util.JavaScriptUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.tag.common.core.Util;

public abstract class MafButtonTagSupport extends BodyTagSupport {
	private Log logger = LogFactory.getLog(this.getClass());

	// *********************************************************************
	// Public constants



	// *********************************************************************
	// Protected state

	protected String keyAttrValue; // 'key' attribute value

	protected boolean keySpecified = false;; // 'key' attribute specified

	protected String localeString; // 'locale' attribute value

	protected boolean localeSpecified = false;; // 'locale' attribute specified?

	protected String baseName; // 'bundle' attribute value

	protected boolean bundleSpecified = false;; // 'bundle' attribute specified?

	// *********************************************************************
	// Private state

	private String var; // 'var' attribute

	private int scope; // 'scope' attribute

	private List params;

	// *********************************************************************
	// Constructor and initialization

	public MafButtonTagSupport() {
		super();
		params = new ArrayList();
		init();
	}

	private void init() {
		var = null;
		scope = PageContext.PAGE_SCOPE;
		keyAttrValue = null;
		keySpecified = false;
		baseName = null;
		bundleSpecified = false;
		localeSpecified = false;
	}

	// *********************************************************************
	// Tag attributes known at translation time

	public void setVar(String var) {
		this.var = var;
	}

	public void setScope(String scope) {
		this.scope = Util.getScope(scope);
	}

	protected void paramClear() {
		params.clear();
	}

	// *********************************************************************
	// Collaboration with subtags

	/**
	 * Adds an argument (for parametric replacement) to this tag's message.
	 * 
	 * @see MafParamSupport
	 */
	public void addParam(Object arg) {
		params.add(arg);
	}

	// *********************************************************************
	// Tag logic

	public int doStartTag() throws JspException {
		//params.clear();
		return EVAL_BODY_BUFFERED;
	}

	public int doEndTag() throws JspException {

		String key = null;
		LocalizationContext locCtxt = null;
		Locale locale = null;

		// determine the message key by...
		if (keySpecified) {
			// ... reading 'key' attribute
			key = keyAttrValue;
		} else {
			// ... retrieving and trimming our body
			if (bodyContent != null && bodyContent.getString() != null) key = bodyContent.getString().trim();
		}

		if ((key == null) || key.equals("")) {
			try {
				pageContext.getOut().print("??????");
			} catch (IOException ioe) {
				throw new JspTagException(ioe.getMessage());
			}
			return EVAL_PAGE;
		}

		String prefix = null;

		if (!localeSpecified) {
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
		} else {
			locale = new Locale(localeString);
		}

		String message = null;

		StringBuffer html = new StringBuffer(50);
		html.append(LocaleUtil.getNoMessageErrorString(key));
		if (locale != null) {

			try {

				// prepend 'prefix' attribute from parent bundle
				if (prefix != null) key = prefix + key;

				if (!bundleSpecified) {
					baseName = (String) Config.get(pageContext, MafResourceBundle.FMT_BUNDLE, scope);
				}
//				if(logger.isDebugEnabled()) {
//					logger.debug("locale = " + localeString + ", message = " + message + ", baseName = " + baseName);
//				}
				
				MafResourceLocale mrl = MafResourceStore.getInstance().getMafResourceLocale(baseName, locale, key);
				message  =  MafResourceStore.getInstance().getMessage(baseName, locale, key, null);
				
				
				
				// Perform parametric replacement if required
				if (!params.isEmpty()) {
					Object[] messageArgs = params.toArray();
					MessageFormat formatter = new MessageFormat("");
					if (locale != null) {
						formatter.setLocale(locale);
					}
					formatter.applyPattern(message);
					message = formatter.format(messageArgs);
				}
				if (mrl == null) {
					throw new NoSuchMessageException(key);
				}
				html.setLength(0);
				if (MafResourceLocale.RL_TYPE_TEXT.equals(mrl.getType())) {
					
					html.append("<span style='white-space:nowrap;'><span class='txtbtn txtbtnl'></span><span class='txtbtn txtbtnr'>");
					html.append(message);
					html.append("</span></span>");
				} else {
					html.append(message);
				}
			} catch (MissingResourceException mre) {
				html.setLength(0);
				html.append(LocaleUtil.getNoMessageErrorString(key));
			} catch (NoSuchMessageException mre) {
				html.setLength(0);
				html.append(LocaleUtil.getNoMessageErrorString(key));
			} catch (NoSuchBundleException mre) {
				html.setLength(0);
				html.append(LocaleUtil.getNoBundleErrorString(baseName)); 
			} catch (Exception e) {
				maf.logger.Logging.trace(e);
				logger.debug(e.getMessage());
			}
		}
		
		if (var != null) {
			pageContext.setAttribute(var, html, scope);
		} else {
			try {
				pageContext.getOut().print(html);
			} catch (IOException ioe) {
				logger.debug(ioe.getMessage());
				throw new JspTagException(ioe.getMessage());
			}
		}
		return EVAL_PAGE;
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		init();
	}

}
