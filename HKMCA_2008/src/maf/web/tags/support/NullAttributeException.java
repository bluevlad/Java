package maf.web.tags.support;

import javax.servlet.jsp.JspTagException;

import org.apache.taglibs.standard.resources.Resources;

public class NullAttributeException extends JspTagException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a NullAttributeException with appropriate information.
	 *
	 * @param tag The name of the tag in which the error occurred.
	 * @param att The attribute value for which the error occurred.
	 */
	public NullAttributeException(String tag, String att) {
		super(Resources.getMessage("TAG_NULL_ATTRIBUTE", att, tag));
	}
}
