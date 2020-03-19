package maf.web.tags.mh;

import javax.servlet.jsp.JspException;

import maf.MafUtil;
import maf.util.StringUtils;
import maf.web.tags.support.MafELTagSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;


public class CapsuleTag extends MafELTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String left = null;
	private String right = null;
	public CapsuleTag() {
		super();
		init();
	}

	private void init() {
		value = left = right = null;
	}

	public int doStartTag() throws JspException {
		String lValue = MafExpressionEvaluationUtils.evaluateString("value", MafUtil.getString(value), pageContext);
		String lRight = MafExpressionEvaluationUtils.evaluateString("right", MafUtil.getString(right), pageContext);
		String lLeft = MafExpressionEvaluationUtils.evaluateString("left", MafUtil.getString(left), pageContext);
		this.value = StringUtils.capsule(lLeft, lValue, lRight);
		this.valueSpecified = true;
		return super.doStartTag();
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		super.release();
		init();
	}

	protected String getLeft() {
    	return left;
    }

	public void setLeft(String left) {
    	this.left = left;
    }

	protected String getRight() {
    	return right;
    }

	public void setRight(String right) {
    	this.right = right;
    }

	

	public void setValue(String value) {
    	this.value = value;
    }

	

	
}