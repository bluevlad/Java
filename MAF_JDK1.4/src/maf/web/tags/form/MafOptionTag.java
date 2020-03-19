package maf.web.tags.form;

import javax.servlet.jsp.JspException;
import maf.MafUtil;
import maf.util.StringUtils;
import maf.web.tags.TagWriter;

public class MafOptionTag extends MafAbstractHtmlElementTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TagWriter tagWriter;

	private String value;

	private String curvalue;

	private String text;

	private boolean selected = false;

	private boolean textSpecified = false;

	public void setValue(String value_) throws JspException {
		this.value = value_;
	}

	public String getValue() {
		return this.value;
	}

	public void setText(String value_) throws JspException {
		this.text = value_;
		this.textSpecified = true;
	}

	public String getText() {
		return this.text;
	}

	public void setCurValue(String value_) throws JspException {
		this.curvalue = value_;
	}

	public String getCurValue() {
		return this.curvalue;
	}

	public void setSelected(String t) {
		this.selected = StringUtils.toBoolean(t);
	}

	protected boolean getSelected() {
		return this.selected;
	}

	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		this.tagWriter = tagWriter;

		tagWriter.startTag("option");

		String v = evaluateString("value", getValue());

		if (this.getSelected()) {
			writeOptionalAttribute(tagWriter, "selected", "true");
		} else {
			String t = evaluateString("curvalue", getCurValue());

			if (!MafUtil.empty(v)) {
				if (v.equals(t)) {

					writeOptionalAttribute(tagWriter, "selected", "true");

				}

			}
		}

		tagWriter.writeAttribute("value", v);

		if (this.textSpecified) {
			this.tagWriter.appendValue(evaluateString("text", getText()));
			this.tagWriter.endTag();

			return EVAL_PAGE;
		} else {
			this.tagWriter.forceBlock();

			return EVAL_BODY_INCLUDE;
		}
	}

	public int doEndTag() throws JspException {
		if (!this.textSpecified) {
			this.tagWriter.endTag();

			return EVAL_PAGE;
		}
		return EVAL_PAGE;
	}

	public void doFinally() {
		super.doFinally();
		this.pageContext = null;

	}
}
