package maf.web.tags.mh;

/*
 * Copyright 1999,2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TryCatchFinally;

import maf.MafUtil;
import maf.util.StringUtils;
import maf.web.context.MafCodeUtil;
import maf.web.servlet.support.RequestContext;
import maf.web.tags.Functions;
import maf.web.tags.MafTagUtil;
import maf.web.tags.RequestContextAwareTag;
import maf.web.tags.support.MafExpressionEvaluationUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.tag.common.core.SetSupport;

/**
 * <p>A handler for &lt;set&gt;, which redirects the browser to a
 * new URL.
 *
 * @author Shawn Bayern
 */
public class MafSetTag extends SetSupport implements TryCatchFinally{
	final private Log logger = LogFactory.getLog(this.getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//*********************************************************************
	// 'Private' state (implementation details)
	private String value_; // stores EL-based property
	private String target_; // stores EL-based property
	private String property_; // stores EL-based property
	/*
	 * 확장된 속성
	 * JSP 2.0의 function에서 하던것을 out에 추가.
	 */
	private String _case = null;;
	private boolean _formatSpecified = false;
	private boolean _indexSpecified = false;
	private boolean _bytesSpecified = false;
	private String _format = null;;
	private int beginIndex = 0;
	private int endIndex = 0;
	private int bytes = 0;

	private String _codeGroup = null;; 
	private boolean _codeGroupSpecified = false;
	
	//*********************************************************************
	// Constructor
	public MafSetTag() {
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
	public void setValue(String value_) {
		this.value_ = value_;
		this.valueSpecified = true;
	}

	public void setTarget(String target_) {
		this.target_ = target_;
	}

	public void setProperty(String property_) {
		this.property_ = property_;
	}

	public void setCase(String lower_) {
		this._case = lower_;
	}

	public void setFormat(String value_) {
		this._format = value_;
		_formatSpecified = true;
	}

	public void setBeginIndex(String value_) {
		this.beginIndex = MafUtil.parseInt(value_);
		_indexSpecified = true;
	}

	public void setEndIndex(String value_) {
		this.endIndex = MafUtil.parseInt(value_);
		_indexSpecified = true;
	}

	public void setBytes(String bytes) throws JspException {
		this.bytes = MafExpressionEvaluationUtils.evaluateInteger("bytes", bytes, pageContext);
		_bytesSpecified = true;
	}

	//*********************************************************************
	// Private (utility) methods
	// (re)initializes state (during release() or construction)
	private void init() {
		// null implies "no expression"
		value_ = target_ = property_ = _format = _codeGroup = null;
		_formatSpecified = _indexSpecified = _bytesSpecified = _codeGroupSpecified = false;
		_case = null;
		beginIndex = endIndex = bytes = 0;
	}

	/* Evaluates expressions as necessary */
	private void evaluateExpressions() throws JspException {
		 RequestContext requestContext = (RequestContext) this.pageContext.getAttribute(RequestContextAwareTag.REQUEST_CONTEXT_PAGE_ATTRIBUTE);


//		try {
//			value = ExpressionUtil.evalNotNull("set", "value", value_, Object.class, this, pageContext);
//		} catch (NullAttributeException ex) {
//			// explicitly let 'value' be null
//			value = null;
//		}
		 
		value = MafExpressionEvaluationUtils.evaluateString("value", value_, pageContext);
		if(_codeGroupSpecified) {
			value = MafCodeUtil.getCodeName(this._codeGroup, MafUtil.getString(value),requestContext.getLocale());
		}
		
		// 'target'
		target = MafExpressionEvaluationUtils.evaluateString("target", target_, pageContext);
//		target = ExpressionUtil.evalNotNull("set", "target", target_, Object.class, this, pageContext);
		// 'property'
//		try {
//			property = (String) ExpressionUtil.evalNotNull("set", "property", property_, String.class, this, pageContext);
//		} catch (NullAttributeException ex) {
//			// explicitly let 'property' be null
//			property = null;
//		}
		property = MafExpressionEvaluationUtils.evaluateString("property", property_, pageContext);
		
		if (_formatSpecified) {
			value = MafTagUtil.getFormatString(this.value, this._format);
		}
		if (_case != null) {
			if ("upper".equals(_case)) {
				value = StringUtils.toUpperCase(MafUtil.getString(value));
			} else if ("lower".equals(_case)) {
				value = StringUtils.toLowerCase(MafUtil.getString(value));
			} else if ("capital".equals(_case)) {
				value = StringUtils.capitalize(MafUtil.getString(value));
			}
		}
		if (this._indexSpecified) {
			value = Functions.substring(MafUtil.getString(value), beginIndex, endIndex);
		}
		if (this._bytesSpecified) {
			try {
				value = StringUtils.getByteCut(MafUtil.getString(value), this.bytes);
			} catch (Exception e) {
				logger.error(e);
				value = "N/A";
			}
		}
	}
	
	public void doCatch(Throwable throwable) throws Throwable {
		maf.logger.Logging.trace(throwable);
		throw throwable;
		
	}

	public void doFinally() {
		
		
	}
}
