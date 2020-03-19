package maf.web.tags.mh;

import java.util.Date;
import javax.servlet.jsp.JspException;
import maf.lib.calendar.MCalUtil;
import maf.web.tags.support.MafELTagSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;


public class MafBetweenHourTag extends MafELTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MafBetweenHourTag() {
		super();
		init();
	}

	private void init() {

	}

	public int doStartTag() throws JspException {

		return super.doStartTag();
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		super.release();
		init();
	}

	//*********************************************************************
	// Accessor methods

	public void setValue(String value_) throws JspException {
		Object t= MafExpressionEvaluationUtils.evaluate("value", value_, pageContext);
		if(t instanceof java.sql.Date) {
			this.value = MCalUtil.betweenHour((Date) t) + "";
			this.valueSpecified = true;
		 } else  if(t instanceof oracle.sql.DATE) {
            oracle.sql.DATE td = (oracle.sql.DATE) t;
            this.value=MCalUtil.betweenHour(td.dateValue()) + "";
		} else if(t instanceof Date) {
			this.value = MCalUtil.betweenHour((Date) t) + "";
			this.valueSpecified = true;
		} else {
//			System.out.println("date : " + t + ", type : " + t.getClass());
			this.valueSpecified = false;;
		}
	}

}
