package maf.web.tags.support;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.apache.taglibs.standard.resources.Resources;
import org.apache.taglibs.standard.tag.common.core.Util;

public class MafELTagSupport extends BodyTagSupport {

	//*********************************************************************
	// Internal state

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Object value; // tag attribute

	protected boolean valueSpecified; // status

	protected Object target; // tag attribute

	protected String property; // tag attribute

	private String var; // tag attribute

	private int scope; // tag attribute

	private boolean scopeSpecified; // status

	//*********************************************************************
	// Construction and initialization

	/**
	 * Constructs a new handler.  As with TagSupport, subclasses should
	 * not provide other constructors and are expected to call the
	 * superclass constructor.
	 */
	public MafELTagSupport() {
		super();
		init();
	}

	// resets local state
	private void init() {
		value = var = null;
		scopeSpecified = valueSpecified = false;
		scope = PageContext.PAGE_SCOPE;
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		super.release();
		init();
	}

	//*********************************************************************
	// Tag logic

	public int doEndTag() throws JspException {

		Object result; // what we'll store in scope:var

		// determine the value by...
		if (value != null) {
			// ... reading our attribute
			result = value;
		} else if (valueSpecified) {
			// ... accepting an explicit null
			result = null;
		} else {
			// ... retrieving and trimming our body
			if (bodyContent == null || bodyContent.getString() == null) result = "";
			else
				result = bodyContent.getString().trim();
		}

		// decide what to do with the result
		if (var != null) {

			/*
			 * Store the result, letting an IllegalArgumentException
			 * propagate back if the scope is invalid (e.g., if an attempt
			 * is made to store something in the session without any
			 * HttpSession existing).
			 */
			if (result != null) {
				pageContext.setAttribute(var, result, scope);
			} else {
				if (scopeSpecified) pageContext.removeAttribute(var, scope);
				else
					pageContext.removeAttribute(var);
			}

		} else if (target != null) {

			// save the result to target.property
			if (target instanceof Map) {
				// ... treating it as a Map entry
				if (result == null) ((Map) target).remove(property);
				else
					((Map) target).put(property, result);
			} else {
				// ... treating it as a bean property
				try {
					PropertyDescriptor pd[] = Introspector.getBeanInfo(target.getClass()).getPropertyDescriptors();
					boolean succeeded = false;
					for (int i = 0; i < pd.length; i++) {
						if (pd[i].getName().equals(property)) {
							Method m = pd[i].getWriteMethod();
							if (m == null) {
								throw new JspException(Resources.getMessage("SET_NO_SETTER_METHOD", property));
							}
							if (result != null) {
								m.invoke(target,
											new Object[] { ExpressionEvaluatorManager.coerce(result, m.getParameterTypes()[0]) });
							} else {
								m.invoke(target, new Object[] { null });
							}
							succeeded = true;
						}
					}
					if (!succeeded) {
						throw new JspTagException(Resources.getMessage("SET_INVALID_PROPERTY", property));
					}
				} catch (IllegalAccessException ex) {
					throw new JspException(ex);
				} catch (IntrospectionException ex) {
					throw new JspException(ex);
				} catch (InvocationTargetException ex) {
					throw new JspException(ex);
				}
			}
		} else {
			try {
				out(pageContext, result.toString());

			} catch (IOException ex) {
				throw new JspException(ex.getMessage(), ex);
			}
		}

		return EVAL_PAGE;
	}

	//*********************************************************************
	// Accessor methods

	// for tag attribute
	public void setVar(String var) {
		this.var = var;
	}

	// for tag attribute
	public void setScope(String scope) {
		this.scope = Util.getScope(scope);
		this.scopeSpecified = true;
	}
	
    public static void out(PageContext pageContext, String text) throws IOException {
		JspWriter w = pageContext.getOut();
		w.write(text);
	}
    
	public void setProperty(String property_) throws JspException {
		this.property =
			MafExpressionEvaluationUtils.evaluateString("property", property_, pageContext);
	}
	
	public void setTarget(String target_) throws JspException {
		this.target =
			MafExpressionEvaluationUtils.evaluateString("target", target_, pageContext);
	}	
}
