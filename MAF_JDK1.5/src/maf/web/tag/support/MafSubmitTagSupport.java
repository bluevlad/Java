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

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.Tag;

import maf.context.NoSuchBundleException;
import maf.context.NoSuchMessageException;
import maf.context.i18n.MafResourceBundle;
import maf.context.i18n.MafResourceLocale;
import maf.context.i18n.MafResourceStore;
import maf.context.support.LocaleUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.tag.common.core.Util;

public abstract class MafSubmitTagSupport extends BodyTagSupport  implements DynamicAttributes  {
	private Log logger = LogFactory.getLog(this.getClass());

	// *********************************************************************
	// Public constants



	// *********************************************************************
	// Protected state

	protected String keyAttrValue; // 'key' attribute value
	private Map dynamicAttr = null;
	protected boolean keySpecified = false;; // 'key' attribute specified

	protected String localeString; // 'locale' attribute value

	protected boolean localeSpecified = false;; // 'locale' attribute specified?

	protected String baseName; // 'bundle' attribute value

	protected boolean bundleSpecified = false;; // 'bundle' attribute specified?

	protected String nameAttrValue; // 
	protected boolean nameSpecified = false; //
	protected String valueAttrValue; // 
	protected boolean valueSpecified = false; //
	protected String typeAttrValue; // 
	protected boolean typeSpecified = false; //

	// *********************************************************************
	// Private state

	private String var; // 'var' attribute

	private int scope; // 'scope' attribute



	// *********************************************************************
	// Constructor and initialization

	public MafSubmitTagSupport() {
		super();

		init();
	}

	private void init() {
		var = null;
		scope = PageContext.PAGE_SCOPE;
		dynamicAttr = new HashMap();
		keyAttrValue = null;
		keySpecified = false;
		baseName = null;
		bundleSpecified = false;
		localeSpecified = false;
		nameAttrValue=null;
		nameSpecified = false;
		valueAttrValue=null;
		valueSpecified = false;
	}

	// *********************************************************************
	// Tag attributes known at translation time

	public void setVar(String var) {
		this.var = var;
	}

	public void setScope(String scope) {
		this.scope = Util.getScope(scope);
	}

	public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
		dynamicAttr.put(localName, value);
	}

	// *********************************************************************
	// Collaboration with subtags



	// *********************************************************************
	// Tag logic

	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}
	public int doAfterTag() throws JspException {
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

		String message = LocaleUtil.getNoMessageErrorString(key);

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
				if (mrl == null) {
					throw new NoSuchMessageException(key);
				}

				StringBuffer sHtml = new StringBuffer(100);
				sHtml.append("<input ");
				if (nameSpecified) {
					sHtml.append(" name='" + nameAttrValue + "' id='"+ nameAttrValue +"' ");
				}
				
				if("RESET".equals(typeAttrValue)) {
					sHtml.append(" type='RESET' class='frmButton'");
				} else if("BUTTON".equals(typeAttrValue)) {
					sHtml.append(" type='BUTTON' class='frmButton'");
				}else  {

						if (MafResourceLocale.RL_TYPE_TEXT.equals(mrl.getType())) {
							sHtml.append(" type='SUBMIT' class='frmButton'");
						} else {
							String imgPath = null;
							if(mrl.isFilenameSpecified()) {
								imgPath = mrl.getImagePath()+ mrl.getFilename();
							} else {
								imgPath = mrl.getImagePath() + key + "_" + LocaleUtil.locale2String(locale) + "." + mrl.getType();
							}
							sHtml.append(" type='IMAGE' class='frmImgButton'");
							sHtml.append(" src='" + imgPath + "' alt='" + mrl.getValue() +"' align='absmiddle' hspace='0' vspace='0' border='0' ");
						}
				}
				if(!dynamicAttr.isEmpty()) {
					Iterator i = dynamicAttr.entrySet().iterator();
					while(i.hasNext() ) {
						Map.Entry me = (Map.Entry) i.next();
						sHtml.append(" ");
						sHtml.append( me.getKey() );
						sHtml.append( "=\"" );
						sHtml.append( me.getValue() );
						sHtml.append( "\"" );
					}
				}
				sHtml.append(" value='");
				sHtml.append((valueSpecified)?valueAttrValue:mrl.getValue());
				sHtml.append("'/>");
				message = sHtml.toString();
			} catch (MissingResourceException mre) {
				message = LocaleUtil.getNoMessageErrorString(key);
			} catch (NoSuchMessageException mre) {
				message = LocaleUtil.getNoMessageErrorString(key);
			} catch (NoSuchBundleException mre) {
				message = LocaleUtil.getNoBundleErrorString(baseName); 
			}
		}

		if (var != null) {
			pageContext.setAttribute(var, message, scope);
		} else {
			try {
				pageContext.getOut().print(message);
			} catch (IOException ioe) {
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
