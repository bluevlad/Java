package maf.web.tags.mh;

import javax.servlet.jsp.JspException;

import maf.MafUtil;
import maf.util.StringUtils;
import maf.web.tags.support.MafELTagSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;


public class MafReplaceTag extends MafELTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String obj;

	private String before_;

	private String after_;

	//*********************************************************************
	// Constructor

	public MafReplaceTag() {
		super();
		init();
	}

	private void init() {
		obj = before_ = after_ = null;
	}

	//*********************************************************************
	// Tag logic

	// evaluates expression and chains to parent
	public int doStartTag() throws JspException {

		// evaluate any expressions we were passed, once per invocation
		try {
			String t = MafUtil.getString(obj);
			if (t != null) {
				value = StringUtils.replace(t, before_, after_);
			}
		} catch (Exception e) {
			maf.logger.Logging.trace(e);
		}

		// chain to the parent implementation
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

		this.obj = MafExpressionEvaluationUtils.evaluateString("value", value_, pageContext);
		this.valueSpecified = true;

	}

	public void setBefore(String target_) {
		this.before_ = target_;
	}

	public void setAfter(String property_) {
		this.after_ = property_;
	}

	//*********************************************************************
	// Private (utility) methods

	// (re)initializes state (during release() or construction)

}
