package maf.web.tags.mh;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class CPath extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspException {
		JspWriter writer = pageContext.getOut();
		try {
			
			writer.write(maf.web.context.MafConfig.getContextPath());

			return SKIP_BODY;

		} catch (IOException ioe) {
			super.pageContext.getServletContext().log("", ioe);
			throw new JspException(ioe.getMessage());
		}
	}
}
