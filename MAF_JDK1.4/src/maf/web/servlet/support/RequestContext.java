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

package maf.web.servlet.support;

import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import maf.web.context.MafConfig;
import maf.web.util.UrlPathHelper;



/**
 * Context holder for request-specific state, like current web application
 * context, current locale, current theme, and potential binding errors.
 * Provides easy access to localized messages and Errors instances.
 *
 * <p>Suitable for exposition to views, and usage within JSP's "useBean" tag,
 * JSP scriptlets, JSTL EL, Velocity templates, etc. Necessary for views
 * that do not have access to the servlet request, like Velocity templates.
 *
 * <p>Can be instantiated manually, or automatically exposed to views as
 * model attribute via AbstractView's "requestContextAttribute" property.
 *
 * <p>Will also work outside DispatcherServlet requests, accessing the root
 * WebApplicationContext and using an appropriate fallback for the locale
 * (the JSTL locale if available, or the HttpServletRequest locale else).
 *
 * @author Juergen Hoeller
 * @since 03.03.2003
 * @see org.springframework.web.servlet.DispatcherServlet
 * @see org.springframework.web.servlet.view.AbstractView#setRequestContextAttribute
 * @see org.springframework.web.servlet.view.UrlBasedViewResolver#setRequestContextAttribute
 * @see #getFallbackLocale
 */
public class RequestContext {

//	/**
//	 * Default theme name used if the RequestContext cannot find a ThemeResolver.
//	 * Only applies to non-DispatcherServlet requests.
//	 * <p>Same as AbstractThemeResolver's default, but not linked in here to
//	 * avoid package interdependencies.
//	 * @see org.springframework.web.servlet.theme.AbstractThemeResolver#ORIGINAL_DEFAULT_THEME_NAME
//	 */
//	public final static String DEFAULT_THEME_NAME = "theme";

	/**
	 * JSTL locale attribute, as used by JSTL implementations to expose their
	 * current locale. Used as fallback in non-DispatcherServlet requests; if not
	 * available, the accept-header locale is used (<code>request.getLocale</code).
	 * <p>Same as the FMT_LOCALE constant in JSTL's Config class, but not linked
	 * in here to avoid a hard-coded dependency on JSTL. RequestContext does not
	 * depend on JSTL except for this fallback check of JSTL's locale attribute.
	 * @see javax.servlet.jsp.jstl.core.Config#FMT_LOCALE
	 * @see javax.servlet.http.HttpServletRequest#getLocale
	 */
	public final static String JSTL_LOCALE_ATTRIBUTE = "javax.servlet.jsp.jstl.fmt.locale";


	/** JSTL suffix for request-scoped attributes */
	protected static final String REQUEST_SCOPE_SUFFIX = ".request";

	/** JSTL suffix for session-scoped attributes */
	protected static final String SESSION_SCOPE_SUFFIX = ".session";

	/** JSTL suffix for application-scoped attributes */
	protected static final String APPLICATION_SCOPE_SUFFIX = ".application";


	private HttpServletRequest request;

	private Map model;



	private Locale locale;


	private boolean defaultHtmlEscape;

	private UrlPathHelper urlPathHelper;



	/**
	 * Create a new RequestContext for the given request,
	 * using the request attributes for Errors retrieval.
	 * <p>This only works with InternalResourceViews, as Errors instances
	 * are part of the model and not normally exposed as request attributes.
	 * It will typically be used within JSPs or custom tags.
	 * <p><b>Will only work within a DispatcherServlet request.</b> Pass in a
	 * ServletContext to be able to fallback to the root WebApplicationContext.
	 * @param request current HTTP request
	 * @see org.springframework.web.servlet.DispatcherServlet
	 * @see #RequestContext(javax.servlet.http.HttpServletRequest, javax.servlet.ServletContext)
	 */
	public RequestContext(HttpServletRequest request) {
		initContext(request, null, null);
	}

	/**
	 * Create a new RequestContext for the given request,
	 * using the request attributes for Errors retrieval.
	 * <p>This only works with InternalResourceViews, as Errors instances
	 * are part of the model and not normally exposed as request attributes.
	 * It will typically be used within JSPs or custom tags.
	 * <p>If a ServletContext is specified, the RequestContext will also
	 * work with the root WebApplicationContext (outside a DispatcherServlet).
	 * @param request current HTTP request
	 * @param servletContext the servlet context of the web application
	 * (can be <code>null</code>; necessary for fallback to root WebApplicationContext)
	 * @see org.springframework.web.context.WebApplicationContext
	 * @see org.springframework.web.servlet.DispatcherServlet
	 */
	public RequestContext(HttpServletRequest request, ServletContext servletContext) {
		initContext(request, servletContext, null);
	}

	/**
	 * Create a new RequestContext for the given request,
	 * using the given model attributes for Errors retrieval.
	 * <p>This works with all View implementations.
	 * It will typically be used by View implementations.
	 * <p><b>Will only work within a DispatcherServlet request.</b> Pass in a
	 * ServletContext to be able to fallback to the root WebApplicationContext.
	 * @param request current HTTP request
	 * @param model the model attributes for the current view
	 * (can be <code>null</code>, using the request attributes for Errors retrieval)
	 * @see org.springframework.web.servlet.DispatcherServlet
	 * @see #RequestContext(javax.servlet.http.HttpServletRequest, javax.servlet.ServletContext, Map)
	 */
	public RequestContext(HttpServletRequest request, Map model) {
		initContext(request, null, model);
	}

	/**
	 * Create a new RequestContext for the given request,
	 * using the given model attributes for Errors retrieval.
	 * <p>This works with all View implementations.
	 * It will typically be used by View implementations.
	 * <p>If a ServletContext is specified, the RequestContext will also
	 * work with a root WebApplicationContext (outside a DispatcherServlet).
	 * @param request current HTTP request
	 * @param servletContext the servlet context of the web application
	 * (can be <code>null</code>; necessary for fallback to root WebApplicationContext)
	 * @param model the model attributes for the current view
	 * (can be <code>null</code>, using the request attributes for Errors retrieval)
	 * @see org.springframework.web.context.WebApplicationContext
	 * @see org.springframework.web.servlet.DispatcherServlet
	 */
	public RequestContext(HttpServletRequest request, ServletContext servletContext, Map model) {
		initContext(request, servletContext, model);
	}

	/**
	 * Default constructor for subclasses.
	 */
	protected RequestContext() {
	}


	/**
	 * Initialize this context with the given request,
	 * using the given model attributes for Errors retrieval.
	 * <p>Delegates to <code>getFallbackLocale</code> and <code>getFallbackTheme</code>
	 * for determining the fallback locale and theme, respectively, if no LocaleResolver
	 * and/or ThemeResolver can be found in the request.
	 * @param request current HTTP request
	 * @param servletContext the servlet context of the web application
	 * (can be <code>null</code>; necessary for fallback to root WebApplicationContext)
	 * @param model the model attributes for the current view
	 * (can be <code>null</code>, using the request attributes for Errors retrieval)
	 * @see #getFallbackLocale
	 * @see #getFallbackTheme
	 * @see org.springframework.web.servlet.DispatcherServlet#LOCALE_RESOLVER_ATTRIBUTE
	 * @see org.springframework.web.servlet.DispatcherServlet#THEME_RESOLVER_ATTRIBUTE
	 */
	protected void initContext(HttpServletRequest request, ServletContext servletContext, Map model) {
		this.request = request;
		this.model = model;


		this.locale = MafConfig.resolveLocale(request);
		if (this.locale == null) {
			// No LocaleResolver available -> try fallback.
			this.locale = getFallbackLocale();
		}



		// Determine default HTML escape setting from the "defaultHtmlEscape"
		// context-param in web.xml, if any.
		this.defaultHtmlEscape = true;

		this.urlPathHelper = new UrlPathHelper();
	}

	/**
	 * Determine the fallback locale for this context.
	 * <p>Default implementation checks for a JSTL locale attribute
	 * in request, session or application scope; if not found,
	 * returns the <code>HttpServletRequest.getLocale()</code>.
	 * @return the fallback locale (never <code>null</code>)
	 * @see javax.servlet.http.HttpServletRequest#getLocale
	 */
	protected Locale getFallbackLocale() {
		Locale locale = (Locale) getRequest().getAttribute(JSTL_LOCALE_ATTRIBUTE);
		if (locale == null) {
			locale = (Locale) getRequest().getAttribute(JSTL_LOCALE_ATTRIBUTE + REQUEST_SCOPE_SUFFIX);
			if (locale == null) {
				HttpSession session = getRequest().getSession(false);
				if (session != null) {
					locale = (Locale) session.getAttribute(JSTL_LOCALE_ATTRIBUTE);
					if (locale == null) {
						locale = (Locale) session.getAttribute(JSTL_LOCALE_ATTRIBUTE + SESSION_SCOPE_SUFFIX);
					}
				}

			}
		}
		return locale;
	}



	/**
	 * Return the underlying HttpServletRequest.
	 * Only intended for cooperating classes in this package.
	 */
	protected final HttpServletRequest getRequest() {
		return this.request;
	}

	
	/**
	 * Return the current locale.
	 */
	public final Locale getLocale() {
		return this.locale;
	}



	/**
	 * (De)activate default HTML escaping for messages and errors, for the scope
	 * of this RequestContext. The default is the application-wide setting
	 * (the "defaultHtmlEscape" context-param in web.xml).
	 * @see org.springframework.web.util.WebUtils#isDefaultHtmlEscape
	 */
	public void setDefaultHtmlEscape(boolean defaultHtmlEscape) {
		this.defaultHtmlEscape = defaultHtmlEscape;
	}

	/**
	 * Is default HTML escaping active?
	 */
	public boolean isDefaultHtmlEscape() {
		return this.defaultHtmlEscape;
	}

	/**
	 * Set the UrlPathHelper to use for context path and request URI decoding.
	 * Can be used to pass a shared UrlPathHelper instance in.
	 * <p>A default UrlPathHelper is always available.
	 */
	public void setUrlPathHelper(UrlPathHelper urlPathHelper) {
		this.urlPathHelper = (urlPathHelper != null ? urlPathHelper : new UrlPathHelper());
	}

	/**
	 * Return the UrlPathHelper used for context path and request URI decoding.
	 * Can be used to configure the current UrlPathHelper.
	 * <p>A default UrlPathHelper is always available.
	 */
	public UrlPathHelper getUrlPathHelper() {
		return this.urlPathHelper;
	}


	/**
	 * Return the context path of the original request,
	 * that is, the path that indicates the current web application.
	 * This is useful for building links to other resources within the application.
	 * <p>Delegates to the UrlPathHelper for decoding.
	 * @see javax.servlet.http.HttpServletRequest#getContextPath
	 * @see #getUrlPathHelper
	 */
	public String getContextPath() {
		return this.urlPathHelper.getOriginatingContextPath(this.request);
	}

	/**
	 * Return the request URI of the original request, that is, the invoked URL
	 * without parameters. This is particularly useful as HTML form action target,
	 * possibly in combination with the original query string.
	 * <p><b>Note this implementation will correctly resolve to the URI of any
	 * originating root request in the presence of a forwarded request. However, this
	 * can only work when the Servlet 2.4 'forward' request attributes are present.
	 * For use in a Servlet 2.3- environment, you can rely on
	 * {@link org.springframework.web.servlet.view.InternalResourceView}
	 * to add these prior to dispatching the request.</b>
	 * <p>Delegates to the UrlPathHelper for decoding.
	 * @see #getQueryString
	 * @see org.springframework.web.util.UrlPathHelper#getOriginatingRequestUri
	 * @see #getUrlPathHelper
	 */
	public String getRequestUri() {
		return this.urlPathHelper.getOriginatingRequestUri(this.request);
	}

	/**
	 * Return the query string of the current request, that is, the part after
	 * the request path. This is particularly useful for building an HTML form
	 * action target in combination with the original request URI.
	 * <p><b>Note this implementation will correctly resolve to the query string of any
	 * originating root request in the presence of a forwarded request. However, this
	 * can only work when the Servlet 2.4 'forward' request attributes are present.
	 * For use in a Servlet 2.3- environment, you can rely on
	 * {@link org.springframework.web.servlet.view.InternalResourceView}
	 * to add these prior to dispatching the request.</b>
	 * <p>Delegates to the UrlPathHelper for decoding.
	 * @see #getRequestUri
	 * @see org.springframework.web.util.UrlPathHelper#getOriginatingQueryString
	 * @see #getUrlPathHelper
	 */
	public String getQueryString() {
		return this.urlPathHelper.getOriginatingQueryString(this.request);
	}





	/**
	 * Retrieve the model object for the given model name,
	 * either from the model or from the request attributes.
	 * @param modelName the name of the model object
	 * @return the model object
	 */
	protected Object getModelObject(String modelName) {
		if (this.model != null) {
			return this.model.get(modelName);
		}
		else {
			return this.request.getAttribute(modelName);
		}
	}





}
