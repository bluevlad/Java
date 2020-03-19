/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tags.support;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import maf.configuration.CodeDetailBean;
import maf.configuration.CodeInfo;
import maf.web.context.MStore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class MafGetCodeNameTagSupport extends TagSupport  {
	private Log logger = LogFactory.getLog(getClass());

	// *********************************************************************
	// Public constants



	// *********************************************************************
	// Protected state
	protected String groupCdAttrValue; // 'groupCd' attribute value
	protected String codeNoAttrValue; // 'codeNo' attribute value
//	protected String siteAttrValue; // 'site' attribute value
	
	protected boolean siteSpecified = false;; // 'key' attribute specified




	// *********************************************************************
	// Constructor and initialization

	public MafGetCodeNameTagSupport() {
		super();

		init();
	}

	private void init() {

		groupCdAttrValue = null;
		codeNoAttrValue = null;
//		siteAttrValue = null;
		siteSpecified = false;
	}


	// *********************************************************************
	// Tag logic

	public int doStartTag() throws JspException {
		//params.clear();
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {




		if ((groupCdAttrValue == null) || groupCdAttrValue.equals("")) {
			try {
				pageContext.getOut().print("??????");
			} catch (IOException ioe) {
				throw new JspTagException(ioe.getMessage());
			}
			return EVAL_PAGE;
		}




		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();


		
		CodeDetailBean rv = null;
//		if(siteAttrValue == null || siteAttrValue.equals("")) {
//			MvcInfo maf_info = (MvcInfo) request.getAttribute("MAF_INFO");
//			SiteInfo t =  maf_info.getSiteInfo();
//			if (t != null) {
//				siteAttrValue = t.getSite();
//			}
//		}
		try {
			CodeInfo t = (CodeInfo) MStore.getInstance().getConfig(MStore.KEY_CODE_DET_MAP);
			rv =  t.getCodeInfo(groupCdAttrValue,  codeNoAttrValue);
		} catch (Throwable t) {

		}

		String message = null;

		if (rv != null && rv != null) {
			message = rv.getCodeName();
		} else {
			message = "???" + groupCdAttrValue + ":" + codeNoAttrValue + "???";
		}

			try {

				pageContext.getOut().print(message);
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
