/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tags.fmt;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import maf.MafUtil;
import maf.context.exceptions.NoSuchBundleException;
import maf.context.exceptions.NoSuchLocaleMessageException;
import maf.context.exceptions.NoSuchMessageException;
import maf.context.support.LocaleUtil;
import maf.web.tags.MafResourceAwareTag;
import maf.web.tags.fmt.support.MafParamSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;

public class MafMessageTag extends MafResourceAwareTag {
	private Log logger = LogFactory.getLog(getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// *********************************************************************
	// Public constants
	// *********************************************************************
	// Protected state
	protected String keyAttrValue; // 'key' attribute value
	protected boolean keySpecified = false;; // 'key' attribute specified
	// *********************************************************************
	// Private state
	private String var; // 'var' attribute
	private List params = new ArrayList();

	protected void paramClear() {
		params.clear();
	}
	
	public void setRemark(String remark) {
    	// dummy
    }
	// *********************************************************************
	// Accessor methods
	// for tag attribute
	public void setKey(String key) throws JspTagException, JspException {
		this.paramClear();
		this.keyAttrValue = MafExpressionEvaluationUtils.evaluateString("key", key, pageContext);
		this.keySpecified = true;
	}

	// for tag attribute
	public void setParam(Object param) throws JspTagException, JspException {
		if (param instanceof Object[]) {
			for (int i = 0; i < ((Object[]) param).length; i++) {
				this.addParam(MafExpressionEvaluationUtils.evaluateString("param", MafUtil.getString(((Object[]) param)[i]),
																			pageContext));
			}
		} else if (param instanceof Collection) {
			Iterator li = ((Collection) param).iterator();
			while (li.hasNext()) {
				this.addParam(MafExpressionEvaluationUtils.evaluateString("param", MafUtil.getString(li.next()), pageContext));
			}
		} else {
			this.addParam(MafExpressionEvaluationUtils.evaluateString("param", MafUtil.getString(param), pageContext));
		}
	}



	// *********************************************************************
	// Tag attributes known at translation time
	public void setVar(String var) {
		this.var = var;
	}

	//	 *********************************************************************
	// Collaboration with subtags
	/**
	 * Adds an argument (for parametric replacement) to this tag's message.
	 * 
	 * @see MafParamSupport
	 */
	public void addParam(Object arg) {
		params.add(arg);
	}

	public int doEndTag() throws JspException {
		String key = null;
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
		String message = LocaleUtil.getNoMessageErrorString(super.baseName, key, super.getSafeLocale());
		try {
			message = super.getMessage(key);
			// Perform parametric replacement if required
			if (!params.isEmpty() && message != null) {
				Object[] messageArgs = params.toArray();
				MessageFormat formatter = new MessageFormat("");
				formatter.setLocale(super.getSafeLocale());
				formatter.applyPattern(message);
				message = formatter.format(messageArgs);
			}
		} catch (NoSuchLocaleMessageException mre) {
			message = LocaleUtil.getNoMessageErrorString(super.baseName, key, super.getSafeLocale());
		} catch (MissingResourceException mre) {
			message = LocaleUtil.getNoMessageErrorString(super.baseName, key);
		} catch (NoSuchMessageException mre) {
			message = LocaleUtil.getNoMessageErrorString(super.baseName, key);
		} catch (NoSuchBundleException mre) {
			message = LocaleUtil.getNoBundleErrorString(baseName);
		} catch (Exception e) {
			maf.logger.Logging.trace(e);
			logger.debug(e.getMessage());
		}
		if (var != null) {
			pageContext.setAttribute(var, message, scope);
		} else {
			try {
				pageContext.getOut().print(MafUtil.nvl(message,""));
			} catch (IOException ioe) {
				logger.debug(ioe.getMessage());
				throw new JspTagException(ioe.getMessage());
			}
		}
		return EVAL_PAGE;
	}
}
