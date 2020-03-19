package maf.web.tags.mh;

import javax.servlet.jsp.JspException;
import maf.web.tags.Functions;
import maf.web.tags.support.MafELTagSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;



public class MafIndexOfTag extends MafELTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String string = null;
	private String needle = null;
	public MafIndexOfTag() {
		super();
		init();
	}

	private void init() {
		string = needle = null;
	}

	public int doStartTag() throws JspException {
		int x = Functions.indexOf(string, needle);
		this.value = x+"";
		this.valueSpecified = true;
		return super.doStartTag();
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		super.release();
		init();
	}

	//*********************************************************************
	// Accessor methods

	public void setValue(String value_) throws JspException {
		this.string = MafExpressionEvaluationUtils.evaluateString("value", value_, pageContext);
		
	}
	public void setNeedle(String needle) throws JspException {
		this.needle = MafExpressionEvaluationUtils.evaluateString("needle", needle, pageContext);
		
	}

}
