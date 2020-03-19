package maf.web.tags.sql;

import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import maf.web.tags.sql.support.MiParamTagSupport;

public class MiParamTag extends MiParamTagSupport {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
	private String valueEL;

    public void setValue(String valueEL) {
	this.valueEL = valueEL;
    }

    public int doStartTag() throws JspException {
	if (valueEL != null) {
	    value = (Object) 
		ExpressionEvaluatorManager.evaluate("value", valueEL, 
		    Object.class, this, pageContext);
	}
	return super.doStartTag();
    }
}