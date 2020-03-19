package maf.web.tags.support;

import java.text.DateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.taglibs.standard.resources.Resources;

public class Util {

	private static final String REQUEST = "request";

	private static final String SESSION = "session";

	private static final String APPLICATION = "application";

	private static final String DEFAULT = "default";

	private static final String SHORT = "short";

	private static final String MEDIUM = "medium";

	private static final String LONG = "long";

	private static final String FULL = "full";

	/*
	 * Converts the given string description of a scope to the corresponding
	 * PageContext constant.
	 *
	 * The validity of the given scope has already been checked by the
	 * appropriate TLV.
	 *
	 * @param scope String description of scope
	 *
	 * @return PageContext constant corresponding to given scope description
	 */
	public static int getScope(String scope) {
		int ret = PageContext.PAGE_SCOPE; // default

		if (REQUEST.equalsIgnoreCase(scope)) ret = PageContext.REQUEST_SCOPE;
		else if (SESSION.equalsIgnoreCase(scope)) ret = PageContext.SESSION_SCOPE;
		else if (APPLICATION.equalsIgnoreCase(scope)) ret = PageContext.APPLICATION_SCOPE;

		return ret;
	}

	/*
	 * Converts the given string description of a formatting style for
	 * dates and times to the corresponding java.util.DateFormat constant.
	 *
	 * @param style String description of formatting style for dates and times
	 * @param errCode Error code to throw if given style is invalid
	 *
	 * @return java.util.DateFormat constant corresponding to given style
	 *
	 * @throws JspException if the given style is invalid
	 */
	public static int getStyle(String style, String errCode) throws JspException {
		int ret = DateFormat.DEFAULT;

		if (style != null) {
			if (DEFAULT.equalsIgnoreCase(style)) {
				ret = DateFormat.DEFAULT;
			} else if (SHORT.equalsIgnoreCase(style)) {
				ret = DateFormat.SHORT;
			} else if (MEDIUM.equalsIgnoreCase(style)) {
				ret = DateFormat.MEDIUM;
			} else if (LONG.equalsIgnoreCase(style)) {
				ret = DateFormat.LONG;
			} else if (FULL.equalsIgnoreCase(style)) {
				ret = DateFormat.FULL;
			} else {
				throw new JspException(Resources.getMessage(errCode, style));
			}
		}

		return ret;
	}
}
