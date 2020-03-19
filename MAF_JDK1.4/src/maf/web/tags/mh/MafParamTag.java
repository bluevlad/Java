package maf.web.tags.mh;

import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import maf.web.tags.mh.support.MafParamSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;

public class MafParamTag extends MafParamSupport {

    //*********************************************************************
    // 'Private' state (implementation details)

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
	private String name_;                       // stores EL-based property
    private String value_;			// stores EL-based property


    //*********************************************************************
    // Constructor

    /**
     * Constructs a new ParamTag.  As with TagSupport, subclasses
     * should not provide other constructors and are expected to call
     * the superclass constructor
     */
    public MafParamTag() {
        super();
        init();
    }


    //*********************************************************************
    // Tag logic

    // evaluates expression and chains to parent
    public int doStartTag() throws JspException {

        // evaluate any expressions we were passed, once per invocation
        evaluateExpressions();

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

    // for EL-based attribute
    public void setName(String name_) {
        this.name_ = name_;
    }

    public void setValue(String value_) {
        this.value_ = value_;
    }


    //*********************************************************************
    // Private (utility) methods

    // (re)initializes state (during release() or construction)
    private void init() {
        // null implies "no expression"
	name_ = value_ = null;
    }

    /* Evaluates expressions as necessary */
    private void evaluateExpressions() throws JspException {
        /* 
         * Note: we don't check for type mismatches here; we assume
         * the expression evaluator will return the expected type
         * (by virtue of knowledge we give it about what that type is).
         * A ClassCastException here is truly unexpected, so we let it
         * propagate up.
         */
    	name = MafExpressionEvaluationUtils.evaluateString("name",name_,pageContext);
    	value = MafExpressionEvaluationUtils.evaluateString("value",value_,pageContext);
//	name = (String) ExpressionUtil.evalNotNull(
//	    "import", "name", name_, String.class, this, pageContext);
//	value = (String) ExpressionUtil.evalNotNull(
//	    "import", "value", value_, String.class, this, pageContext);
    }
}
