/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tags.support;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import maf.context.exceptions.NoSuchBundleException;
import maf.context.exceptions.NoSuchLocaleMessageException;
import maf.context.exceptions.NoSuchMessageException;
import maf.context.i18n.MafResourceBundle;
import maf.context.i18n.MafResourceLocale;
import maf.context.i18n.MafResourceStore;
import maf.context.support.LocaleUtil;
import maf.web.context.MafConfig;
import maf.web.tags.TagWriter;
import maf.web.util.TagUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class MafSubmitTagSupport extends BodyTagSupport {
	private Log logger = LogFactory.getLog(this.getClass());

	// *********************************************************************
	// Public constants

	// *********************************************************************
	// Protected state

	protected String keyAttrValue; // 'key' attribute value

	//	private Map dynamicAttr = null;

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

	protected String iconAttrValue; // 

	protected boolean iconSpecified = false;

	// *********************************************************************
	// Constructor and initialization

	public MafSubmitTagSupport() {
		super();

		init();
	}

	private void init() {

		keyAttrValue = null;
		keySpecified = false;
		baseName = null;
		bundleSpecified = false;
		localeSpecified = false;
		nameAttrValue = null;
		nameSpecified = false;
		valueAttrValue = null;
		valueSpecified = false;
		iconAttrValue = null;
		iconSpecified = false;

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
		TagWriter tagWriter = new TagWriter(super.pageContext.getOut());

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

		//		if (!localeSpecified) {
		//			Tag t = findAncestorWithClass(this, MafBundleSupport.class);
		//			if (t != null) {
		//				// use resource bundle from parent <bundle> tag
		//				MafBundleSupport parent = (MafBundleSupport) t;
		//				locCtxt = parent.getLocalizationContext();
		//				prefix = parent.getPrefix();
		//			} else {
		//				locCtxt = MafBundleSupport.getLocalizationContext(pageContext);
		//			}
		//			if (locCtxt != null) {
		//				locale = locCtxt.getLocale();
		//			}
		//		}
		locale = MafConfig.resolveLocale((HttpServletRequest) super.pageContext.getRequest());

		String message = LocaleUtil.getNoMessageErrorString(baseName ,key);
		boolean ok = false;
		if (locale != null) {

			try {

				// prepend 'prefix' attribute from parent bundle
				if (prefix != null) key = prefix + key;

				if (!bundleSpecified) {
					baseName = (String) Config.get(pageContext, MafResourceBundle.FMT_BUNDLE,
													TagUtils.getScope(TagUtils.SCOPE_PAGE));
				}
				if (logger.isDebugEnabled()) {
					logger.debug("locale = " + locale.getCountry() + ", message = " + message + ", baseName = " + baseName);
				}
				MafResourceLocale mrl = MafResourceStore.getMafResourceLocale(baseName, locale, key);
				if (mrl == null) {
					throw new NoSuchMessageException(key);
				}

				StringBuffer sHtml = new StringBuffer(100);
				tagWriter.startTag("input");
				tagWriter.writeAttribute("name", nameAttrValue);
				tagWriter.writeAttribute("id", nameAttrValue);

				if ("RESET".equals(typeAttrValue)) {
					tagWriter.writeAttribute("type", "RESET");
				} else if ("BUTTON".equals(typeAttrValue)) {
					sHtml.append(" type='BUTTON' ");
				} else {

					if (MafResourceLocale.RL_TYPE_TEXT.equals(mrl.getType())) {
						sHtml.append(" type='SUBMIT' class='frmButton'");
						tagWriter.writeAttribute("type", "SUBMIT");
						tagWriter.writeAttribute("class", "frmButton " + iconAttrValue);

					} else {
						String imgPath = null;
						if (mrl.isFilenameSpecified()) {
							imgPath = mrl.getImagePath() + mrl.getFilename();
						} else {
							imgPath = mrl.getImagePath() + key + "_" + LocaleUtil.locale2String(locale) + "." + mrl.getType();
						}
						tagWriter.writeAttribute("type", "IMAGE");
						tagWriter.writeAttribute("class", "frmImgButton");
						tagWriter.writeAttribute("src", imgPath);
						tagWriter.writeAttribute("alt", mrl.getValue());
						tagWriter.writeAttribute("align", "absmiddle");
						tagWriter.writeAttribute("hspace", "0");
						tagWriter.writeAttribute("vspace", "0");
						tagWriter.writeAttribute("border", "0");

					}
				}

				tagWriter.writeAttribute("value", (valueSpecified) ? valueAttrValue : mrl.getValue());
				tagWriter.endTag();
				ok = true;
			} catch (NoSuchLocaleMessageException el) {
				message = LocaleUtil.getNoMessageErrorString(baseName,key,locale);
			} catch (MissingResourceException mre) {
				message = LocaleUtil.getNoMessageErrorString(baseName, key);
			} catch (NoSuchMessageException mre) {
				message = LocaleUtil.getNoMessageErrorString(baseName, key);
			} catch (NoSuchBundleException mre) {
				message = LocaleUtil.getNoBundleErrorString(baseName);
			}

		}

		try {
			if (!ok) {
				pageContext.getOut().print(message);
			}
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
