/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 bluevlad.
 */
package maf.web.tags.form;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import maf.beans.NavigatorInfo;
import maf.util.StringUtils;
import maf.web.tags.TagWriter;
import maf.web.tags.support.MafHtmlTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafHeaderTag extends MafHtmlTagSupport {
	final String IMG_SORT_A = "/maf_images/navigator/sort-a.gif";
	final String IMG_SORT_D = "/maf_images/navigator/sort-d.gif";
	final String IMG_SORT_A_C = "/maf_images/navigator/sort-a_c.gif";
	final String IMG_SORT_D_C = "/maf_images/navigator/sort-d_c.gif";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(this.getClass());
	protected String nameAttrValue; // 'key' attribute value
	protected boolean sortAttrValue = false; // '

	public void setName(String key) throws JspTagException {
		this.nameAttrValue = key;
	}

	public String getName() throws JspTagException {
		return nameAttrValue;
	}

	public void setSort(String sort) throws JspTagException {
		this.sortAttrValue = StringUtils.toBoolean(sort);
	}

	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		//		String fieldName = null;
		String fieldName = super.evaluateString("name", this.getName());
		
		boolean chk = false;
		if (fieldName != null) {
			try {
				String message = super.getFieldLabel(fieldName);
				if (sortAttrValue) {
					String imgSortA = IMG_SORT_A;
					String imgSortD = IMG_SORT_D;
					HttpServletRequest req = (HttpServletRequest) super.pageContext.getRequest();
					NavigatorInfo navigator = (NavigatorInfo) req.getAttribute("navigator");
					if (navigator != null) {
						if (fieldName.equals(navigator.getOrder().getKey())) { // 현재 key가 sort 중이면 
							String toSort=null;
							if ("A".equals(navigator.getOrder().getOrder())) {
								imgSortA = IMG_SORT_A_C;
								toSort = "D";
							} else {
								toSort = "A";
								imgSortA = IMG_SORT_D_C;
							}
							//
							tagWriter.startTag("A");
							tagWriter.writeOptionalAttributeValue("href", "javascript:mnavi_Sort('"+toSort+"','" + fieldName + "')");
							tagWriter.appendValue(message);
							tagWriter.startTag("img");
							tagWriter.writeAttribute("src", req.getContextPath() + imgSortA);
							tagWriter.endTag();
							tagWriter.endTag();
							chk = true;
						} else {
							tagWriter.startTag("A");
							tagWriter.writeOptionalAttributeValue("href", "javascript:mnavi_Sort('A','" + fieldName + "')");
							tagWriter.appendValue(message);
							tagWriter.startTag("img");
							tagWriter.writeAttribute("src", req.getContextPath() + imgSortA);
							tagWriter.endTag();
							tagWriter.endTag();
							chk = true;
						}
						
					}
					
				}
				if (!chk) {
					pageContext.getOut().print(message);
				}
			} catch (IOException ioe) {
				throw new JspTagException(ioe.getMessage());
			}
		}
		
		return EVAL_PAGE;
	}
}
