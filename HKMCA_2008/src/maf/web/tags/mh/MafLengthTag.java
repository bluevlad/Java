package maf.web.tags.mh;

import javax.servlet.jsp.JspException;

import maf.web.tags.Functions;
import maf.web.tags.support.MafELTagSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;


public class MafLengthTag extends MafELTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MafLengthTag() {
		super();
		init();
	}

	private void init() {

	}

	public int doStartTag() throws JspException {

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
		this.value = Functions.length(MafExpressionEvaluationUtils.evaluate("value", value_, pageContext)) + "";
		this.valueSpecified = true;

	}

}
