package maf.web.tags.form;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import maf.util.Assert;
import maf.util.ObjectUtils;
import maf.util.StringUtils;
import maf.web.tags.TagWriter;
import maf.web.tags.support.MafHtmlTagSupport;
import maf.web.util.HtmlUtils;

public class MafFormTag extends MafHtmlTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TagWriter tagWriter;
	
	public static final String COMMAND_NAME_VARIABLE_NAME = "cmd";
	
	public static final String ACTION_ATTRIBUTE = "action";
	public static final String METHOD_ATTRIBUTE = "method";
	public static final String ENCTYPE_ATTRIBUTE = "enctype";
	public static final String ONSUBMIT_ATTRIBUTE = "onsubmit";
	public static final String ONRESET_ATTRIBUTE = "onreset";

	
	private static final String DEFAULT_METHOD = "post";
	
	private String method = DEFAULT_METHOD;
	private String action;
	private String enctype;
	private String onsubmit;
	private String onreset;
	private String name;

	/**
	 * Set the value of the '<code>method</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setMethod(String method) {
		Assert.hasText(method, "'method' must not be empty");
		this.method = method;
	}
	
	/**
	 * Set the value of the '<code>action</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setAction(String action) {
		this.action = (action != null ? action : "");
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setOnSubmit(String onsubmit) {
		this.onsubmit = onsubmit;
	}
	public void setEnctype(String onsubmit) {
		this.enctype = onsubmit;
	}
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		this.tagWriter = tagWriter;
		this.tagWriter.startTag("form");
		writeDefaultAttributes(tagWriter);

		this.tagWriter.writeAttribute(METHOD_ATTRIBUTE,this.method  );
		this.tagWriter.writeAttribute(ACTION_ATTRIBUTE, resolveAction());
		writeOptionalAttribute(tagWriter, ENCTYPE_ATTRIBUTE, this.enctype);
		writeOptionalAttribute(tagWriter, ONSUBMIT_ATTRIBUTE, this.onsubmit);
		writeOptionalAttribute(tagWriter, ONRESET_ATTRIBUTE, this.onreset);

		this.tagWriter.forceBlock();


		return EVAL_BODY_INCLUDE;
	}

	
	public int doEndTag() throws JspException {
		this.tagWriter.endTag();
		
		return EVAL_PAGE;
	}
	
	public void doFinally() {
		super.doFinally();
		this.pageContext = null;
		
	}
	
	/**
	 * Resolve the value of the '<code>action</code>' attribute.
	 * <p>If the user configured an '<code>action</code>' value then
	 * the result of evaluating this value is used. Otherwise, the
	 * {@link org.springframework.web.servlet.support.RequestContext#getRequestUri() originating URI}
	 * is used.
	 * @return the value that is to be used for the '<code>action</code>' attribute
	 */
	private String resolveAction() throws JspException {
		if (StringUtils.hasText(this.action)) {
			return ObjectUtils.getDisplayString(evaluate(ACTION_ATTRIBUTE, this.action));
		} else {
			String requestUri = getRequestContext().getRequestUri();
			ServletResponse response = this.pageContext.getResponse();
			if (response instanceof HttpServletResponse) {
				requestUri = ((HttpServletResponse) response).encodeURL(requestUri);
				String queryString = getRequestContext().getQueryString();
				if (StringUtils.hasText(queryString)) {
					requestUri += "?" + HtmlUtils.htmlEscapeQueryStringParameters(queryString);
				}
			}
			if (StringUtils.hasText(requestUri)) {
				return requestUri;
			} else {
				throw new IllegalArgumentException("Attribute 'action' is required. Attempted to resolve " +
						"against current request URI but request URI was null.");
			}
		}
	}
}
