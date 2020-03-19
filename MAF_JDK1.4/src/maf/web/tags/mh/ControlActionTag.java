package maf.web.tags.mh;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import maf.web.filter.RequestMonFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlActionTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(this.getClass());
	
	public int doStartTag() throws JspException {
		JspWriter writer = pageContext.getOut();
		try {
			ServletRequest req = pageContext.getRequest();
			String action = (String) req.getAttribute(RequestMonFilter.KEY_CONTROLACTIN);
			writer.write(action);

			return SKIP_BODY;

		} catch (IOException ioe) {
			super.pageContext.getServletContext().log("", ioe);
			logger.error(ioe.getMessage());
			throw new JspException(ioe.getMessage());
		}
	}
}
