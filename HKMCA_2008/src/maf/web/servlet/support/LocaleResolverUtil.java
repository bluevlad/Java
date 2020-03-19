package maf.web.servlet.support;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import maf.web.servlet.LocaleResolver;

import org.apache.struts.taglib.tiles.ComponentConstants;

public class LocaleResolverUtil {
	
	/**
	 * Locale 정보를 request 와 JSTL을 위해 저장 한다. 
	 * @param request
	 * @param response
	 * @param locale
	 */
	protected static void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		response.setLocale(locale);
		HttpSession session = request.getSession(false);
		session.setAttribute(ComponentConstants.LOCALE_KEY, locale);
		response.setLocale(locale);
		request.setAttribute(ComponentConstants.LOCALE_KEY, locale);
		request.setAttribute(LocaleResolver.LOCALE_REQUEST_ATTRIBUTE_NAME, locale);
		Config.set(request.getSession(), Config.FMT_LOCALE, locale);
	}
}
