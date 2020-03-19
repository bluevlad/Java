package maf.web.tags.mh;

import javax.servlet.jsp.JspException;
import maf.util.StringUtils;
import maf.web.tags.support.MafELTagSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;



public class MafStrRepeatTag extends MafELTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String string = null;
	private int count = 0;
	public MafStrRepeatTag() {
		super();
		init();
	}

	private void init() {
		string =  null;
		count = 0;
	}

	public int doStartTag() throws JspException {
		
		this.value = StringUtils.strRepeat(this.string, this.count);
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
		this.string = MafExpressionEvaluationUtils.evaluateString("value", value_, pageContext) ;
		
	}
	public void setCount(String count) throws JspException {
		this.count = MafExpressionEvaluationUtils.evaluateInteger("count", count, pageContext);
		
	}

}
