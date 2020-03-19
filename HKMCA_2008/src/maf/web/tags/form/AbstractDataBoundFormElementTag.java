/*
 * Copyright 2002-2007 the original author or authors.
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

import javax.servlet.jsp.JspException;

import maf.util.Assert;
import maf.util.ObjectUtils;
import maf.util.StringUtils;
import maf.web.tags.TagWriter;

import org.apache.struts.taglib.html.FormTag;


/**
 * Base tag for all data-binding aware JSP form tags.
 * 
 * <p>Provides the common {@link #setPath path} and {@link #setId id} properties.
 * Provides sub-classes with utility methods for accessing the {@link BindStatus}
 * of their bound value and also for {@link #writeOptionalAttribute interacting}
 * with the {@link TagWriter}.
 *
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @since 2.0
 */
public abstract class AbstractDataBoundFormElementTag extends MafAbstractFormTag {

	/**
	 * The '<code>id</code>' attribute of the rendered HTML tag.
	 */
	public static final String ID_ATTRIBUTE = "id";


	/**
	 * The value of the '<code>id</code>' attribute.
	 */
	private String id;

	/**
	 * The property path from the {@link FormTag#setCommandName command object}.
	 */
	private String path;


	/**
	 * Set the property path from the {@link FormTag#setCommandName command object}.
	 * May be a runtime expression. Required.
	 * @throws IllegalArgumentException if the supplied path is <code>null</code> or composed wholly of whitespace 
	 */
	public void setPath(String path) {
		Assert.hasText(path, "'path' must not be empty");
		this.path = path;
	}

	/**
	 * Get the {@link #evaluate resolved} property path for the
	 * {@link FormTag#setCommandName command object}.
	 */
	protected final String getPath() throws JspException {
		return (String) evaluate("path", this.path);
	}

	/**
	 * Set the value of the '<code>id</code>' attribute.
	 * <p>Defaults to the value of {@link #getName}; may be a runtime expression.
	 * <p>Note that the default value may not be valid for certain tags.
	 * @param id the value of the '<code>id</code>' attribute
	 */
	public void setId(String id) {
		Assert.notNull(id, "'id' must not be null");
		this.id = id;
	}

	/**
	 * Get the value of the '<code>id</code>' attribute.
	 * <p>May be a runtime expression.
	 * @return the value of the '<code>id</code>' attribute
	 */
	public String getId() {
		return this.id;
	}


	/**
	 * Writes the default set of attributes to the supplied {@link TagWriter}.
	 * Further abstract sub-classes should override this method to add in
	 * any additional default attributes but <strong>must</strong> remember
	 * to call the <code>super</code> method.
	 * <p>Concrete sub-classes should call this method when/if they want
	 * to render default attributes.
	 * @param tagWriter the {@link TagWriter} to which any attributes are to be {@link TagWriter#writeAttribute(String, String) written}
	 */
	protected void writeDefaultAttributes(TagWriter tagWriter) throws JspException {
		String id = getId();
		if (StringUtils.hasText(id)) {
			tagWriter.writeAttribute(ID_ATTRIBUTE, ObjectUtils.getDisplayString(evaluate(ID_ATTRIBUTE, id)));
		}
		else {
			writeOptionalAttribute(tagWriter, ID_ATTRIBUTE, autogenerateId());
		}
		writeOptionalAttribute(tagWriter, "name", getName());
	}

	/**
	 * Autogenerate the '<code>id</code>' attribute value for this tag. The default
	 * implementation simply delegates to {@link #getName}.
	 */
	protected String autogenerateId() throws JspException {
		return getName();
	}

	/**
	 * Get the value for the HTML '<code>name</code>' attribute.
	 * <p>The default implementation simply delegates to
	 * {@link #getCompletePath} to use the property path as the name.
	 * For the most part this is desirable as it links with the
	 * server-side expectation for databinding. However, some
	 * subclasses may wish to change the value of the '<code>name</code>'
	 * attribute without changing the bind path.
	 * @return the value for the HTML '<code>name</code>' attribute
	 */
	protected String getName() throws JspException {
		return getCompletePath();
	}

	
	/**
	 * Build the complete path for this tag, including the nested path.
	 * @see #getNestedPath()
	 * @see #getPath()
	 */
	protected String getCompletePath() throws JspException {
		
		return "";
	}
	
	/**
	 * Get the final bind path including the exposed {@link FormTag command name} and
	 * any {@link NestedPathTag nested paths}.
	 */
	private String getBindPath(String resolvedSubPath) {
		StringBuffer sb = new StringBuffer();
		sb.append(getCommandName());

		if (resolvedSubPath != null) {
			if(sb.charAt(sb.length() - 1) != '.') {
				sb.append('.');
			}
			sb.append(resolvedSubPath);
		}
		return sb.toString();
	}





	private String getCommandName() {
		return (String) MafFormTag.COMMAND_NAME_VARIABLE_NAME;
	}

	/**
	 * Disposes of the {@link BindStatus} instance.
	 */
	public void doFinally() {
		super.doFinally();
		
	}

}
