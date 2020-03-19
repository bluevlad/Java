package maf.web.tags.sql.support;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.sql.SQLExecutionTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.taglibs.standard.resources.Resources;

public class MiParamTagSupport extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Object value;

	//*********************************************************************
	// Tag logic
	public int doEndTag() throws JspException {
		SQLExecutionTag parent = (SQLExecutionTag) findAncestorWithClass(
		                                                                 this,
		                                                                 SQLExecutionTag.class);
		if (parent == null) {
			throw new JspTagException(Resources.getMessage("SQL_PARAM_OUTSIDE_PARENT"));
		}
		Object paramValue = null;
		if (value != null) {
			paramValue = value;
		} else if (bodyContent != null) {
			paramValue = bodyContent.getString().trim();
			if (((String) paramValue).trim().length() == 0) {
				paramValue = null;
			}
		}
		parent.addSQLParameter(paramValue);
		return EVAL_PAGE;
	}
}
