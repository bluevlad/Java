/*
 * Copyright 2002-2006 the original author or authors.
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

package maf.web.tags.form;

import java.beans.PropertyEditor;
import javax.servlet.jsp.JspException;

import maf.util.StringUtils;
import maf.web.tags.TagWriter;
import maf.web.tags.support.MafExpressionEvaluationUtils;


/**
 * Base class for databinding-aware JSP tags that render HTML element. Provides
 * a set of properties corresponding to the set of HTML attributes that are common
 * across elements.
 * 
 * @author Rob Harrop
 * @since 2.0
 */
public abstract class MafAbstractHtmlElementTag  extends AbstractDataBoundFormElementTag {
	private final ValueFormatter valueFormatter = new ValueFormatter();
	
	/**
	 * The name of the '<code>class</code>' attribute.
	 */
	public static final String CLASS_ATTRIBUTE = "class";

	/**
	 * The name of the '<code>style</code>' attribute.
	 */
	public static final String STYLE_ATTRIBUTE = "style";

	/**
	 * The name of the '<code>lang</code>' attribute.
	 */
	public static final String LANG_ATTRIBUTE = "lang";

	/**
	 * The name of the '<code>title</code>' attribute.
	 */
	public static final String TITLE_ATTRIBUTE = "title";

	/**
	 * The name of the '<code>dir</code>' attribute.
	 */
	public static final String DIR_ATTRIBUTE = "dir";

	/**
	 * The name of the '<code>tabindex</code>' attribute.
	 */
	public static final String TABINDEX_ATTRIBUTE = "tabindex";

	/**
	 * The name of the '<code>onclick</code>' attribute.
	 */
	public static final String ONCLICK_ATTRIBUTE = "onclick";

	/**
	 * The name of the '<code>ondblclick</code>' attribute.
	 */
	public static final String ONDBLCLICK_ATTRIBUTE = "ondblclick";

	/**
	 * The name of the '<code>onmousedown</code>' attribute.
	 */
	public static final String ONMOUSEDOWN_ATTRIBUTE = "onmousedown";

	/**
	 * The name of the '<code>onmouseup</code>' attribute.
	 */
	public static final String ONMOUSEUP_ATTRIBUTE = "onmouseup";

	/**
	 * The name of the '<code>onmouseover</code>' attribute.
	 */
	public static final String ONMOUSEOVER_ATTRIBUTE = "onmouseover";

	/**
	 * The name of the '<code>onmousemove</code>' attribute.
	 */
	public static final String ONMOUSEMOVE_ATTRIBUTE = "onmousemove";

	/**
	 * The name of the '<code>onmouseout</code>' attribute.
	 */
	public static final String ONMOUSEOUT_ATTRIBUTE = "onmouseout";

	/**
	 * The name of the '<code>onkeypress</code>' attribute.
	 */
	public static final String ONKEYPRESS_ATTRIBUTE = "onkeypress";

	/**
	 * The name of the '<code>onkeyup</code>' attribute.
	 */
	public static final String ONKEYUP_ATTRIBUTE = "onkeyup";

	/**
	 * The name of the '<code>onkeydown</code>' attribute.
	 */
	public static final String ONKEYDOWN_ATTRIBUTE = "onkeydown";
	
	
	
	/**
	 * The name of the '<code>onkeydown</code>' attribute.
	 */
	public static final String DOJOTYPE_ATTRIBUTE = "dojoType";
	
	/**
	 * The name of the '<code>disable</code>' attribute.
	 */
	public static final String DISABLED_ATTRIBUTE = "disable";


	/**
	 * The value of the '<code>class</code>' attribute.
	 */
	private String cssClass;

	/**
	 * The CSS class to use when the field bound to a particular tag has errors.
	 */
	private String cssErrorClass;

	/**
	 * The value of the '<code>style</code>' attribute.
	 */
	private String cssStyle;

	/**
	 * The value of the '<code>lang</code>' attribute.
	 */
	private String lang;

	/**
	 * The value of the '<code>title</code>' attribute.
	 */
	private String title;

	/**
	 * The value of the '<code>dir</code>' attribute.
	 */
	private String dir;

	/**
	 * The value of the '<code>tabindex</code>' attribute.
	 */
	private String tabindex;

	/**
	 * The value of the '<code>onclick</code>' attribute.
	 */
	private String onclick;

	/**
	 * The value of the '<code>ondblclick</code>' attribute.
	 */
	private String ondblclick;

	
	/**
	 * The value of the '<code>onmousedown</code>' attribute.
	 */
	private String onmousedown;

	/**
	 * The value of the '<code>onmouseup</code>' attribute.
	 */
	private String onmouseup;

	/**
	 * The value of the '<code>onmouseover</code>' attribute.
	 */
	private String onmouseover;

	/**
	 * The value of the '<code>onmousemove</code>' attribute.
	 */
	private String onmousemove;

	/**
	 * The value of the '<code>onmouseout</code>' attribute.
	 */
	private String onmouseout;

	/**
	 * The value of the '<code>onkeypress</code>' attribute.
	 */
	private String onkeypress;

	/**
	 * The value of the '<code>onkeyup</code>' attribute.
	 */
	private String onkeyup;

	/**
	 * The value of the '<code>onkeydown</code>' attribute.
	 */
	private String onkeydown;

	/**
	 * The value of the '<code>onchange</code>' attribute.
	 */
	private String onchange;
	
	/**
	 * The value of the '<code>onblur</code>' attribute.
	 */
	private String onblur;
	
	/**
	 * The value of the '<code>onfocus</code>' attribute.
	 */
	private String onfocus;
	
	private String hname;
	
	private String remark;
	
	/**
	 * The value of the '<code>dojoType</code>' attribute.
	 */
	private String dojoType;
	
	
	/**
	 * Sets the value of the '<code>class</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * Gets the value of the '<code>class</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getCssClass() {
		return this.cssClass;
	}

	/**
	 * The CSS class to use when the field bound to a particular tag has errors.
	 * May be a runtime expression.
	 */
	public void setCssErrorClass(String cssErrorClass) {
		this.cssErrorClass = cssErrorClass;
	}

	/**
	 * The CSS class to use when the field bound to a particular tag has errors.
	 * May be a runtime expression.
	 */
	protected String getCssErrorClass() {
		return this.cssErrorClass;
	}

	/**
	 * Sets the value of the '<code>style</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	/**
	 * Gets the value of the '<code>style</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getCssStyle() {
		return this.cssStyle;
	}

	/**
	 * Sets the value of the '<code>lang</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * Gets the value of the '<code>lang</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getLang() {
		return this.lang;
	}

	/**
	 * Sets the value of the '<code>title</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the value of the '<code>title</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getTitle() {
		return this.title;
	}

	/**
	 * Sets the value of the '<code>dir</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * Gets the value of the '<code>dir</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getDir() {
		return this.dir;
	}

	/**
	 * Sets the value of the '<code>tabindex</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setTabindex(String tabindex) {
		this.tabindex = tabindex;
	}

	/**
	 * Gets the value of the '<code>tabindex</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getTabindex() {
		return this.tabindex;
	}

	/**
	 * Sets the value of the '<code>onclick</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	/**
	 * Gets the value of the '<code>onclick</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getOnclick() {
		return this.onclick;
	}

	/**
	 * Sets the value of the '<code>ondblclick</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setOndblclick(String ondblclick) {
		this.ondblclick = ondblclick;
	}

	/**
	 * Gets the value of the '<code>ondblclick</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getOndblclick() {
		return this.ondblclick;
	}

	/**
	 * Sets the value of the '<code>onmousedown</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setOnmousedown(String onmousedown) {
		this.onmousedown = onmousedown;
	}

	/**
	 * Gets the value of the '<code>onmousedown</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getOnmousedown() {
		return this.onmousedown;
	}

	/**
	 * Sets the value of the '<code>onmouseup</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setOnmouseup(String onmouseup) {
		this.onmouseup = onmouseup;
	}

	/**
	 * Gets the value of the '<code>onmouseup</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getOnmouseup() {
		return this.onmouseup;
	}

	/**
	 * Sets the value of the '<code>onmouseover</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setOnmouseover(String onmouseover) {
		this.onmouseover = onmouseover;
	}

	/**
	 * Gets the value of the '<code>onmouseover</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getOnmouseover() {
		return this.onmouseover;
	}

	/**
	 * Sets the value of the '<code>onmousemove</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setOnmousemove(String onmousemove) {
		this.onmousemove = onmousemove;
	}

	/**
	 * Gets the value of the '<code>onmousemove</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getOnmousemove() {
		return this.onmousemove;
	}

	/**
	 * Sets the value of the '<code>onmouseout</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}
	/**
	 * Gets the value of the '<code>onmouseout</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getOnmouseout() {
		return this.onmouseout;
	}

	/**
	 * Sets the value of the '<code>onkeypress</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setOnkeypress(String onkeypress) {
		this.onkeypress = onkeypress;
	}

	/**
	 * Gets the value of the '<code>onkeypress</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getOnkeypress() {
		return this.onkeypress;
	}

	/**
	 * Sets the value of the '<code>onkeyup</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setOnkeyup(String onkeyup) {
		this.onkeyup = onkeyup;
	}

	/**
	 * Gets the value of the '<code>onkeyup</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getOnkeyup() {
		return this.onkeyup;
	}

	/**
	 * Sets the value of the '<code>onkeydown</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setOnkeydown(String onkeydown) {
		this.onkeydown = onkeydown;
	}

	/**
	 * Gets the value of the '<code>onkeydown</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getOnkeydown() {
		return this.onkeydown;
	}

	
	public String getDojoType() {
		return dojoType;
	}

	public void setDojoType(String dojoType) {
		this.dojoType = dojoType;
	}



	public void setRemark(String remark) {
    	this.remark = remark;
    }

	/**
	 * Writes the default attributes configured via this base class to the supplied {@link TagWriter}.
	 * Subclasses should call this when they want the base attribute set to be written to the output.
	 */
	protected void writeDefaultAttributes(TagWriter tagWriter) throws JspException {
		super.writeDefaultAttributes(tagWriter);
		writeOptionalAttribute(tagWriter, LANG_ATTRIBUTE, getLang());
		writeOptionalAttribute(tagWriter, TITLE_ATTRIBUTE, getTitle());
		writeOptionalAttribute(tagWriter, DIR_ATTRIBUTE, getDir());
		writeOptionalAttribute(tagWriter, TABINDEX_ATTRIBUTE, getTabindex());
		writeOptionalAttribute(tagWriter, ONCLICK_ATTRIBUTE, getOnclick());
		writeOptionalAttribute(tagWriter, ONDBLCLICK_ATTRIBUTE, getOndblclick());
		writeOptionalAttribute(tagWriter, ONMOUSEDOWN_ATTRIBUTE, getOnmousedown());
		writeOptionalAttribute(tagWriter, ONMOUSEUP_ATTRIBUTE, getOnmouseup());
		writeOptionalAttribute(tagWriter, ONMOUSEOVER_ATTRIBUTE, getOnmouseover());
		writeOptionalAttribute(tagWriter, ONMOUSEMOVE_ATTRIBUTE, getOnmousemove());
		writeOptionalAttribute(tagWriter, ONMOUSEOUT_ATTRIBUTE, getOnmouseout());
		writeOptionalAttribute(tagWriter, ONKEYPRESS_ATTRIBUTE, getOnkeypress());
		writeOptionalAttribute(tagWriter, ONKEYUP_ATTRIBUTE, getOnkeyup());
		writeOptionalAttribute(tagWriter, ONKEYDOWN_ATTRIBUTE, getOnkeydown());
		writeOptionalAttribute(tagWriter, STYLE_ATTRIBUTE, this.getCssStyle());
		writeOptionalAttribute(tagWriter, CLASS_ATTRIBUTE, this.getCssClass());
		writeOptionalAttribute(tagWriter, DOJOTYPE_ATTRIBUTE, getDojoType());
		writeOptionalAttribute(tagWriter, "HNAME", getHname());
		writeOptionalAttribute(tagWriter, "onChange", getOnchange());
		writeOptionalAttribute(tagWriter, "onBlur", getOnblur());
		writeOptionalAttribute(tagWriter, "onFocus", getOnfocus());

	}



	/**
	 * Gets the display value of the supplied <code>Object</code>, HTML escaped
	 * as required. This version is <strong>not</strong> {@link PropertyEditor}-aware.
	 * @see ValueFormatter
	 */
	protected String getDisplayString(Object value) {
		return this.valueFormatter.getDisplayString(value, isHtmlEscape());
	}
	
	
	/**
	 * Evaluates the supplied value for the supplied attribute name. If the supplied value
	 * is <code>null</code> then <code>null</code> is returned, otherwise evaluation is
	 * handled using {@link ExpressionEvaluationUtils#evaluate(String, String, javax.servlet.jsp.PageContext)}.
	 */
	protected Object evaluate(String attributeName, Object value) throws JspException {
		if (value instanceof String) {
			return MafExpressionEvaluationUtils.evaluate(attributeName, (String)value, this.pageContext);
		}
		else {
			return value;
		}
	}
	
	protected String evaluateString(String attributeName, String value) throws JspException {
		if (value instanceof String) {
			return MafExpressionEvaluationUtils.evaluateString(attributeName, (String)value, this.pageContext);
		}
		else {
			return value;
		}
	}

	protected String getHname() {
		return hname;
	}

	public void setHname(String hname) {
		this.hname = hname;
	}

	protected String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	protected String getOnblur() {
		return onblur;
	}

	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}
	
	protected String getOnfocus() {
		return onfocus;
	}

	public void setOnfocus(String onfocus) {
		this.onfocus = onfocus;
	}
}
