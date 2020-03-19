/*
 * Created on 2006. 6. 27.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tags.support;

/**
 * Support for tag handlers for &lt;setBundle&gt;, the JSTL 1.0 tag that loads
 * a resource bundle and stores it in a scoped variable.
 *
 * @author Jan Luehe
 */
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.tagext.TagSupport;

import maf.context.i18n.MafResourceBundle;

import org.apache.taglibs.standard.tag.common.core.Util;

public class MafSetBundleSupport extends TagSupport {

	//*********************************************************************
	// Protected state

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String basename; // 'basename' attribute

	//*********************************************************************
	// Private state

	private int scope; // 'scope' attribute

	private String var; // 'var' attribute

	//*********************************************************************
	// Constructor and initialization

	public MafSetBundleSupport() {
		super();
		init();
	}

	private void init() {
		basename = null;
		scope = PageContext.PAGE_SCOPE;
	}

	//*********************************************************************
	// Tag attributes known at translation time

	public void setVar(String var) {
		this.var = var;
	}

	public void setScope(String scope) {
		this.scope = Util.getScope(scope);
	}

	//*********************************************************************
	// Tag logic

	public int doEndTag() throws JspException {
		LocalizationContext locCtxt = MafBundleSupport.getLocalizationContext(pageContext, basename);
	
		if (var != null) {
			pageContext.setAttribute(var, locCtxt, scope);
		} else {
			Config.set(pageContext, Config.FMT_LOCALIZATION_CONTEXT, locCtxt, scope);
			Config.set(pageContext, MafResourceBundle.FMT_BUNDLE, basename, scope);
		}

		return EVAL_PAGE;
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		init();
	}
}
