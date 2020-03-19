package maf.web.tags.mh;

import javax.servlet.jsp.JspException;
import maf.web.tags.support.MafELTagSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;

public class MafLastIndexOfTag extends MafELTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String string = null;
	private String needle = null;
	public MafLastIndexOfTag() {
		super();
		init();
	}

	private void init() {
		string = needle = null;
	}

	public int doStartTag() throws JspException {
		int x =0;
		if(string != null) {
			x= string.lastIndexOf(needle);
		}
		
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
