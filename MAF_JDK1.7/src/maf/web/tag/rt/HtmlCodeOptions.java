/*
 * Created on 2006. 6. 26.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tag.rt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspTagException;

import maf.configuration.CodeDetailBean;
import maf.configuration.CodeInfo;
import maf.web.context.MStore;
import maf.web.mvc.beans.MvcInfo;
import maf.web.mvc.beans.SiteInfo;
import maf.web.servlet.tag.RequestContextAwareTag;
import maf.web.tag.MiHtmlTag;



public class HtmlCodeOptions extends  RequestContextAwareTag {
	private String groupCd = null;
	private String value = null;
	private String site = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void setGroupCd(String groupCd) throws JspTagException{
		this.groupCd = groupCd;
	}
	/**
	 * @param site The site to set.
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	protected  int doEndTagInternal() throws Exception{
		ServletRequest req = this.getServletRequest();
		MvcInfo maf_info = (MvcInfo) req.getAttribute("MAF_INFO");
		StringBuffer sHtml = new StringBuffer(100);
		
		List list = null;
		CodeDetailBean ob = null;
		if(site == null) {
			SiteInfo t =  maf_info.getSiteInfo();
			if (t != null) {
				site = t.getSite();
			}
		}

		try {
			CodeInfo t = (CodeInfo) MStore.getInstance().getConfig(MStore.KEY_CODE_DET_MAP);
			list =  t.getCodeList(groupCd, site);
			if(list != null) {
				for(int i = 0; i<list.size() ; i++) {
					ob = (CodeDetailBean) list.get(i);
					sHtml.append(MiHtmlTag.getHtmlOption(ob.getCodeNo(),ob.getCodeName(),value));
				}
			}
		} catch (Throwable t) {
			throw new JspTagException(t.getMessage());
		}
		try {
			pageContext.getOut().print(sHtml.toString());
		} catch (IOException ioe) {
			throw new JspTagException(ioe.getMessage());
		}
		
		return EVAL_PAGE;	
	}
	
}

