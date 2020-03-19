package maf.web.tags.mh;

import javax.servlet.jsp.JspException;

import maf.web.context.MafCodeUtil;
import maf.web.tags.support.MafELTagSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;

public class MafCodeTag extends MafELTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codeGroup = null;
	
	public MafCodeTag() {
		super();
		init();
	}

	private void init() {
		codeGroup = null;
	}

	public int doStartTag() throws JspException {

		return super.doStartTag();
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		super.release();
		init();
	}
	
	public void setCodeGroup(String codeGroup) throws JspException {
		this.codeGroup =MafExpressionEvaluationUtils.evaluateString("codeGroup", codeGroup, pageContext);
		this.value = MafCodeUtil.getCodeList(this.codeGroup);
	}
	
}
