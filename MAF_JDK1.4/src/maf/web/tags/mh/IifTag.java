package maf.web.tags.mh;

import javax.servlet.jsp.JspException;
import maf.MafUtil;
import maf.web.tags.support.MafELTagSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;

public class IifTag extends MafELTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String trueValue = null;
	private String test = null;
	private String falseValue = null;
	public IifTag() {
		super();
		init();
	}

	private void init() {
		trueValue = falseValue = test = null;
	}

	public int doStartTag() throws JspException {
		boolean test = MafExpressionEvaluationUtils.evaluateBoolean("test", this.getTest(), pageContext);
		this.valueSpecified = true;
		if (test) {
			this.value = MafExpressionEvaluationUtils.evaluate("trueValue", this.getTrueValue(), pageContext);
		} else {
			if(falseValue != null) {
				this.value = MafExpressionEvaluationUtils.evaluate("falseValue", this.getFalseValue(), pageContext);
			} else {
				this.value = "";
			}
		}
		return super.doStartTag();
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		super.release();
		init();
	}

	public String getFalseValue() {
    	return falseValue;
    }

	public void setFalseValue(String falseValue) {
    	this.falseValue = falseValue;
    }

	public String getTest() {
    	return test;
    }

	public void setTest(String test) {
    	this.test = test;
    }

	public String getTrueValue() {
    	return trueValue;
    }

	public void setTrueValue(String trueValue) {
    	this.trueValue = trueValue;
    }

	
}
