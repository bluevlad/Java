/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.tags.form;

import java.io.IOException;
import java.util.MissingResourceException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import maf.MafUtil;
import maf.context.exceptions.NoSuchBundleException;
import maf.context.exceptions.NoSuchLocaleMessageException;
import maf.context.exceptions.NoSuchMessageException;
import maf.context.i18n.MafResourceLocale;
import maf.context.support.LocaleUtil;
import maf.util.StringUtils;
import maf.web.tags.TagWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafSubmitTag extends MafAbstractHtmlElementTag {

	private Log logger = LogFactory.getLog(this.getClass());

	protected String keyAttrValue; // 'key' attribute value

	protected String nameAttrValue; // 

	protected String valueAttrValue; // 

	protected String typeAttrValue; // 

	protected String iconAttrValue; // 

	boolean keySpecified = false;

	private int scope; // 'scope' attribute

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// *********************************************************************
	// Accessor methods

	// for tag attribute
	public void setKey(String key) throws JspTagException {
		this.keyAttrValue = key;
		this.keySpecified = true;
	}

	public void setName(String name) throws JspTagException {
		this.nameAttrValue = name;
	}

	public void setValue(String value) throws JspTagException {
		this.valueAttrValue = value;
	}

	public void setType(String type) throws JspTagException {
		this.typeAttrValue = StringUtils.toUpperCase(type);
	}

	public void setIcon(String type) throws JspTagException {
		this.iconAttrValue = type;
	}

	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		String key = null;

		// determine the message key by...
		if (keySpecified) {
			// ... reading 'key' attribute
			key = keyAttrValue;
//		} else {
//			// ... retrieving and trimming our body
//			if (bodyContent != null && bodyContent.getString() != null) key = bodyContent.getString().trim();
		}

//		if ((key == null) || key.equals("")) {
//			try {
//				pageContext.getOut().print("??????");
//			} catch (IOException ioe) {
//				throw new JspTagException(ioe.getMessage());
//			}
//			return EVAL_PAGE;
//		}
		
		String message = LocaleUtil.getNoMessageErrorString(baseName, key);
		boolean ok = false;

		try {
			MafResourceLocale mrl = null;
			if(keySpecified) {
				 mrl = super.getMafResourceLocale(key);
			}
			tagWriter.startTag("input");
			writeOptionalAttribute(tagWriter, "name", nameAttrValue);
			writeOptionalAttribute(tagWriter, "id", nameAttrValue);
			tagWriter.writeAttribute("class", "frmButton " + iconAttrValue);
			if ("RESET".equals(typeAttrValue)) {
				tagWriter.writeAttribute("type", "RESET");

			} else if ("BUTTON".equals(typeAttrValue)) {

				tagWriter.writeAttribute("type", "BUTTON");

			} else {
				if (keySpecified) {
					if (MafResourceLocale.RL_TYPE_TEXT.equals(mrl.getType())) {
						//						sHtml.append(" type='SUBMIT' class='frmButton'");
						tagWriter.writeAttribute("type", "SUBMIT");
	
					} else {
						String imgPath = null;
						if (mrl.isFilenameSpecified()) {
							imgPath = mrl.getImagePath() + mrl.getFilename();
						} else {
							imgPath = mrl.getImagePath() + key + "_" + LocaleUtil.locale2String(super.getSafeLocale()) + "." + mrl.getType();
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
				} else {
					tagWriter.writeAttribute("type", "SUBMIT");
				}
			}
			if(mrl != null) {
				tagWriter.writeAttribute("value", MafUtil.nvl(valueAttrValue, mrl.getValue()));
			}
			tagWriter.endTag();
			ok = true;
		} catch (NoSuchLocaleMessageException el) {
			message = LocaleUtil.getNoMessageErrorString(super.baseName,key,super.getSafeLocale());
		} catch (MissingResourceException mre) {
			message = LocaleUtil.getNoMessageErrorString(super.baseName, key);
		} catch (NoSuchMessageException mre) {
			message = LocaleUtil.getNoMessageErrorString(super.baseName, key);
		} catch (NoSuchBundleException mre) {
			message = LocaleUtil.getNoBundleErrorString(baseName);
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

	public int doEndTag() throws JspException {

		return EVAL_PAGE;
	}

	public void doFinally() {
		super.doFinally();
		this.pageContext = null;

	}
}
