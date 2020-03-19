package maf.web.tags.mh;

import javax.servlet.jsp.JspException;

import maf.MafUtil;
import maf.web.tags.support.MafELTagSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;

public class MafFileIconTag extends MafELTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String string = null;
	public MafFileIconTag() {
		super();
		init();
	}

	private void init() {
		string =  null;
	}

	public int doStartTag() throws JspException {
		
		this.value = MafUtil.getIcon(this.string);
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
}
