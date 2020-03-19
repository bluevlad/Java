package maf.web.servlet;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LocaleResolver {
	public static final String LOCALE_REQUEST_ATTRIBUTE_NAME = LocaleResolver.class.getName() + ".LOCALE";
	
	Locale resolveLocale(HttpServletRequest request);

	void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale);

}
