package maf.web.tags.sql;

import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import maf.web.tags.sql.support.MiQueryTagSupport;

public class MiQueryTag extends MiQueryTagSupport {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

	private String sqlEL;
	private String startRowEL;
	private String maxRowsEL;

	//*********************************************************************
	// Constructor
	/**
	 * Constructs a new QueryTag.  As with TagSupport, subclasses
	 * should not provide other constructors and are expected to call
	 * the superclass constructor
	 */
	public MiQueryTag() {
		super();
	}



	/**
	 * The index of the first row returned can be
	 * specified using startRow.
	 */
	public void setStartRow(String startRowEL) {
		this.startRowEL = startRowEL;
	}

	/**
	 * Query result can be limited by specifying
	 * the maximum number of rows returned.
	 */
	public void setMaxRows(String maxRowsEL) {
		this.maxRowsEL = maxRowsEL;
		this.maxRowsSpecified = true;
	}

	/**
	 * Setter method for the SQL statement to use for the
	 * query. The statement may contain parameter markers
	 * (question marks, ?). If so, the parameter values must
	 * be set using nested value elements.
	 */
	public void setSql(String sqlEL) {
		this.sqlEL = sqlEL;
	}

	public int doStartTag() throws JspException {
		evaluateExpressions();
		return super.doStartTag();
	}

	//*********************************************************************
	// Private utility methods
	// Evaluates expressions as necessary
	private void evaluateExpressions() throws JspException {
		Integer tempInt = null;

		if (sqlEL != null) {
			sql = (String) ExpressionEvaluatorManager.evaluate("sql", sqlEL,
			                                                   String.class, this,
			                                                   pageContext);
		}
		if (startRowEL != null) {
			tempInt = (Integer) ExpressionEvaluatorManager.evaluate("startRow",
			                                                        startRowEL,
			                                                        Integer.class, this,
			                                                        pageContext);
			if (tempInt != null) startRow = tempInt.intValue();
		}
		if (maxRowsEL != null) {
			tempInt = (Integer) ExpressionEvaluatorManager.evaluate("maxRows", maxRowsEL,
			                                                        Integer.class, this,
			                                                        pageContext);
			if (tempInt != null) maxRows = tempInt.intValue();
		}
	}
}
