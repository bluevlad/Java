package maf.web.tags.mh;

import javax.servlet.jsp.JspException;

import maf.web.tags.mh.support.MafImportSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;
import maf.web.tags.support.NullAttributeException;

/**
 * C:import ´ëÄ¡¿ë 
 * @author UBQ
 *
 */
public class MafImportTag extends MafImportSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String context_; // stores EL-based property

	private String charEncoding_; // stores EL-based property

	private String url_; // stores EL-based property

	//*********************************************************************
	// Constructor

	/**
	 * Constructs a new ImportTag.  As with TagSupport, subclasses
	 * should not provide other constructors and are expected to call
	 * the superclass constructor
	 */
	public MafImportTag() {
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
	public void setUrl(String url_) {
		this.url_ = url_;
	}

	public void setContext(String context_) {
		this.context_ = context_;
	}

	public void setCharEncoding(String charEncoding_) {
		this.charEncoding_ = charEncoding_;
	}

	//*********************************************************************
	// Private (utility) methods

	// (re)initializes state (during release() or construction)
	private void init() {
		// null implies "no expression"
		url_ = context_ = charEncoding_ = null;
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
		url = MafExpressionEvaluationUtils.evaluateString("url", url_,pageContext);
//		url = (String) ExpressionUtil.evalNotNull("import", "url", url_, String.class, this, pageContext);
		if (url == null || url.equals("")) throw new NullAttributeException("import", "url");

		context  = MafExpressionEvaluationUtils.evaluateString("context", context_,pageContext);
		charEncoding  = MafExpressionEvaluationUtils.evaluateString("charEncoding", charEncoding_,pageContext);
//		context = (String) ExpressionUtil.evalNotNull("import", "context", context_, String.class, this, pageContext);
//		charEncoding = (String) ExpressionUtil
//												.evalNotNull("import", "charEncoding", charEncoding_, String.class, this,
//																pageContext);
	}
}
