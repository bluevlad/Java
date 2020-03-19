package maf.web.tags.fmt;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import maf.MafUtil;
import maf.web.tags.RequestContextAwareTag;

public class MafGetLocale extends RequestContextAwareTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String var = null; // tag attribute
	private String type= null; // tag attribute

	//	 for tag attribute
	public void setVar(String var) {
		this.var = var;
	}
	public void setType(String type) {
		this.type = type;
	}

	protected int doStartTagInternal() throws Exception {
		Locale locale = super.getSafeLocale();
		Object x = null;
		if("lang".equalsIgnoreCase(this.type)) {
			x = locale.getDisplayLanguage(locale);
		} else if("country".equalsIgnoreCase(this.type)) {
			x = locale.getDisplayCountry(locale);
		} else {
			x = locale;
		}
		if(var != null) {
			pageContext.setAttribute(var,x, PageContext.PAGE_SCOPE);
		} else {
			JspWriter writer = pageContext.getOut();
			try {
				
				writer.write(MafUtil.getString(x));

				return EVAL_PAGE;

			} catch (IOException ioe) {
				super.pageContext.getServletContext().log("", ioe);
				throw new JspException(ioe.getMessage());
			}
		}
		return EVAL_PAGE;
	}
}