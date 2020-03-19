package maf.web.servlet;

import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.Config;
import maf.context.support.MafLocaleEditor;
import maf.web.context.MafConfig;
import maf.web.context.support.BasemafHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafLocaleChangeInterceptor extends BasemafHandler {
	private final Log logger = LogFactory.getLog(this.getClass());
	/**
	 * Default name of the locale specification parameter: "locale".
	 */
	public static final String DEFAULT_PARAM_NAME = "hl";

	private String paramName = DEFAULT_PARAM_NAME;

	public MafLocaleChangeInterceptor() {
		
	}

	/**
	 * Set the name of the parameter that contains a locale specification
	 * in a locale change request. Default is "locale".
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamName() {
		return this.paramName;
	}

	public boolean setLocale(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		String newLocale = request.getParameter(this.paramName);

		if (newLocale != null) {
			LocaleResolver localeResolver = MafConfig.getLocaleResolver();
			if (localeResolver == null) {
				logger.error("No LocaleResolver found: not in a DispatcherServlet request?");
				throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
			}
			
			MafLocaleEditor localeEditor = new MafLocaleEditor();
			localeEditor.setAsText(newLocale);
			localeResolver.setLocale(request, response, localeEditor.getLocale());
			
			
			
//			PageContext pcontext = (PageContext) Config.get(request, PageContext.PAGECONTEXT);
//			logger.debug("pcontext : " + pcontext);
//			Config.set(request, Config.FMT_LOCALE, localeEditor.getLocale());
//			logger.debug(", SCOPE:" + PageContext.SESSION_SCOPE +
//			             ", FMT_LOCALE : " + Config.FMT_LOCALE + "," + Config.FMT_LOCALE);
			//Config.set((PageContext)request.getAttribute(PageContext.PAGECONTEXT), Config.FMT_LOCALE, localeEditor.getLocale(), PageContext.SESSION_SCOPE);
		} else {
			logger.debug("new locale is invalue [" + newLocale +"]");
		}

//		if(logger.isDebugEnabled()) {
//			logger.debug("locale change to " + newLocale);
//		}
		// Proceed in any case.
		return true;
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		if(request.getParameterMap().containsKey(this.paramName)) {
			try { 
			this.setLocale(request, response);
			} catch (Throwable e) {
				maf.logger.Logging.trace(e);
			}
		}
		
		
	}
}
