package maf.web.tags.mh;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import maf.beans.NavigatorInfo;
import maf.web.tags.old.MiRoutine;
import maf.web.tags.support.MafExpressionEvaluationUtils;

public class ListSeqTag extends TagSupport {
	private Log  logger = LogFactory.getLog(this.getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NavigatorInfo navigator = null;
	private String count = null;
	
	public void setNavigator(Object obj) throws JspException {
		this.navigator = (obj instanceof String) ?  (NavigatorInfo) MafExpressionEvaluationUtils.evaluate("navigator", (String)obj, pageContext):(NavigatorInfo) obj;
//		this.navigator = (NavigatorInfo) MafExpressionEvaluationUtils.evaluate("navigator", navi, pageContext);
		
	}
	
	public void setCount(String count) throws JspException {
		this.count =  MafExpressionEvaluationUtils.evaluateString("count", count, pageContext);
	}
	
	
	public ListSeqTag() {
		super();
		init();
	}

	private void init() {
		navigator =  null;
		count = null;
		
	}

	public int doStartTag() throws JspException {

		JspWriter writer = pageContext.getOut();
		try {
			//logger.debug(this.navigator.getTotalCount() + "," + this.count);
			writer.write(MiRoutine.getListSeq(this.navigator, this.count));

			return SKIP_BODY;

		} catch (IOException ioe) {
			super.pageContext.getServletContext().log("", ioe);
			throw new JspException(ioe.getMessage());
		}
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		super.release();
		init();
	}

}
