package maf.web.tags.mh;

import java.util.Locale;
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
import maf.web.util.HTMLUtil;
import maf.web.util.JavaScriptUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.tag.common.core.OutSupport;

public class MafOutTag extends OutSupport implements TryCatchFinally{
	
	final  private Log logger = LogFactory.getLog(this.getClass());
	//*********************************************************************
	// 'Private' state (implementation details)

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String value_; // stores EL-based property

	private String default_; // stores EL-based property

	private String escapeXml_; // stores EL-based property
	
	private String escapeJS_; // stores EL-based property
	
	/*
	 * 확장된 속성
	 * JSP 2.0의 function에서 하던것을 out에 추가.
	 */
	private boolean _td = false; 
	private String _case = null;; 
	private boolean _nl2br = false;
	private boolean _removenl = false;
	private boolean _formatSpecified = false;
	private String _format = null;;
	
	private String _codeGroup = null;; 
	private boolean _codeGroupSpecified = false;
	
	private boolean _indexSpecified = false;
	private boolean _bytesSpecified = false;
	private boolean _repeateSpecified = false;
	
	private int beginIndex = 0;
	private int endIndex = 0;
	private int bytes = 0;
	private int repeate = 0;
	
	//  *********************************************************************

	// Constructor

	public MafOutTag() {
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
	}

	public void setDefault(String default_) {
		this.default_ = default_;
	}

	public void setEscapeXml(String escapeXml_) {
		this.escapeXml_ = escapeXml_;
	}
	public void setEscapeJS(String escapeJS_) {
		this.escapeJS_ = escapeJS_;
	}
	public void setTd(String td_) {
		this._td = StringUtils.toBoolean(td_);
//		logger.debug("_td : " + _td + ", p = " + td_);
	}
	public void setNl2br(String td_) {
		this._nl2br = StringUtils.toBoolean(td_);
	}
	public void setRemovenl(String td_) {
		this._removenl = StringUtils.toBoolean(td_);
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
		this.bytes=MafExpressionEvaluationUtils.evaluateInteger("bytes", bytes, pageContext) ;
		_bytesSpecified = true;
	}
	public void setRepeate(String repeate) throws JspException {
		this.repeate=MafExpressionEvaluationUtils.evaluateInteger("repeate", repeate, pageContext) ;
		_repeateSpecified = true;
	}
	
	public void setCodeGroup(String value_) {
		this._codeGroup = value_;
		this._codeGroupSpecified = true;
	}
	//*********************************************************************
	// Private (utility) methods

	// (re)initializes state (during release() or construction)
	private void init() {
		// null implies "no expression"
		value_ = default_ = escapeXml_ = escapeJS_ =  _format = _codeGroup = null;
		
		// 
		_td =  _formatSpecified = _removenl =_indexSpecified= _bytesSpecified = _repeateSpecified = _codeGroupSpecified = false;
		_nl2br = false;
		_case = null;
		beginIndex = endIndex = bytes = repeate = 0;
	}

	/* Evaluates expressions as necessary */
	private void evaluateExpressions() throws JspException {
		 RequestContext requestContext = (RequestContext) this.pageContext.getAttribute(RequestContextAwareTag.REQUEST_CONTEXT_PAGE_ATTRIBUTE);
		 Locale locale = null;
		 if(requestContext != null) {
			 locale = requestContext.getLocale();
		 } else {
			 locale =this.pageContext.getRequest().getLocale();
		 }
//		try {
//			value = ExpressionUtil.evalNotNull("out", "value", value_, Object.class, this, pageContext);
//		} catch (NullAttributeException ex) {
//			// explicitly allow 'null' for value
//			value = null;
//		}
		value = MafExpressionEvaluationUtils.evaluate("value", value_, pageContext);
		def = MafExpressionEvaluationUtils.evaluateString("default", default_, pageContext);
		
		if(_codeGroupSpecified) {
			String t = MafUtil.nvl(MafCodeUtil.getCodeName(this._codeGroup, MafUtil.getString(value), locale),"");
			if(!_td && MafUtil.empty(t)) {
				value=def;
			} else	if(_td && MafUtil.empty(t)) {
				escapeXml = false;
				value=MafUtil.nvl(def,"&nbsp;");
			} else {
				value = t;
			}
			
		}
		
		if(_repeateSpecified) {
			value = StringUtils.strRepeat(MafUtil.getString(value), this.repeate);
		}
		

		
		
		escapeXml = MafExpressionEvaluationUtils.evaluateBoolean ("escapeXml", escapeXml_, pageContext);
		

		if(_formatSpecified) {
			value = MafTagUtil.getFormatString(this.value, this._format);
		}
		if(_case != null) {
			if("upper".equals(_case)) {
				value = StringUtils.toUpperCase(MafUtil.getString(value));
			} else if ("lower".equals(_case)) {
				value = StringUtils.toLowerCase(MafUtil.getString(value));
			} else if ("capital".equals(_case)) {
				value = StringUtils.capitalize(MafUtil.getString(value));
			}
		}

		if(_td) {
			escapeXml = false;
			def="&nbsp;";
		}
		
		
		
		
		
		if(this._indexSpecified) {
			value = Functions.substring(MafUtil.getString(value), beginIndex, endIndex);
		}
		if(this._bytesSpecified) {
			try {
			value = StringUtils.getByteCut(MafUtil.getString(value), this.bytes);
			} catch (Exception e) {
				logger.error(e);
				value = "N/A";
			}
		}
		if(_nl2br) {
			escapeXml = false;
			value = HTMLUtil.nl2br(MafUtil.getString(this.value));
		}
		if(this._removenl) {
			//escapeXml = false;
			value = HTMLUtil.removenl(MafUtil.getString(this.value));
		}
		
		
		boolean escapeJS = MafExpressionEvaluationUtils.evaluateBoolean("escapeJS", escapeJS_, pageContext);
//		escape = ((Boolean) ExpressionUtil.evalNotNull("out", "escapeJS", escapeJS_, Boolean.class, this, pageContext));
		
		if(escapeJS) {
			value=JavaScriptUtils.javaScriptEscape(MafUtil.getString(this.value));
		}
		if(MafUtil.empty(value)) {
			value = null;
		}
	}

	public void doCatch(Throwable throwable) throws Throwable {
		maf.logger.Logging.trace(throwable);
		throw throwable;
		
	}

	public void doFinally() {
		
		
	}
	
	
}
