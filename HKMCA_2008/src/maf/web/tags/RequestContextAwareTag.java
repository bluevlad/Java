package maf.web.tags;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;

import maf.MafUtil;
import maf.context.exceptions.NoSuchBundleException;
import maf.context.exceptions.NoSuchLocaleMessageException;
import maf.context.exceptions.NoSuchMessageException;
import maf.context.i18n.MafResourceLocale;
import maf.context.i18n.MafResourceStore;
import maf.context.support.LocaleUtil;
import maf.util.StringUtils;
import maf.web.mvc.configuration.FormConfig;
import maf.web.mvc.configuration.InputConfig;
import maf.web.servlet.support.JspAwareRequestContext;
import maf.web.servlet.support.RequestContext;
import maf.web.tags.support.MafExpressionEvaluationUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.tag.common.core.Util;


/**
 * Superclass for all tags that require a .
 * 
 * <p>The <code>RequestContext</code> instance provides easy access
 * to current state like the
 * {@link org.springframework.web.context.WebApplicationContext},
 * the {@link java.util.Locale}, the
 * {@link org.springframework.ui.context.Theme}, etc.
 *
 * <p>Mainly intended for
 * {@link org.springframework.web.servlet.DispatcherServlet} requests;
 * will use fallbacks when used outside <code>DispatcherServlet</code>.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see org.springframework.web.servlet.support.RequestContext
 * @see org.springframework.web.servlet.DispatcherServlet
 */
public abstract class RequestContextAwareTag extends BodyTagSupport implements TryCatchFinally {

	public static final String REQUEST_CONTEXT_PAGE_ATTRIBUTE =
		"maf.web.tags.REQUEST_CONTEXT";
	
	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	private Locale locale = null;
	protected int scope = PageContext.PAGE_SCOPE; // 'scope' attribute
	
	protected String baseName; // 'bundle' attribute value
	protected boolean bundleSpecified = false;; // 'bundle' attribute specified?
	
	private RequestContext requestContext;
	
	public void setLocale(String locale) {
		Locale tmpLocale =StringUtils.parseLocaleString(locale);
		if(tmpLocale != null) {
			this.locale = tmpLocale;
		}
	}
	// for tag attribute
	public void setBundle(String baseName) throws JspTagException , JspException {
		this.baseName =MafExpressionEvaluationUtils.evaluateString("bundle", baseName, pageContext);
		this.bundleSpecified = true;
	}
	
	public void setScope(String scope) {
		this.scope = Util.getScope(scope);
	}
	
	public final int doStartTag() throws JspException {
		this.scope = PageContext.PAGE_SCOPE;
		this.requestContext = (RequestContext) this.pageContext.getAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE);
		
		try {
			if (this.requestContext == null) {
				this.requestContext = new JspAwareRequestContext(this.pageContext);
				this.pageContext.setAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE, this.requestContext);
			}
			return doStartTagInternal();
		}
		catch (JspException ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
		catch (RuntimeException ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
		catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new JspTagException(ex.getMessage());
		}
	}

	/**
	 * Return the current RequestContext.
	 */
	protected final RequestContext getRequestContext() {
		return this.requestContext;
	}


	/**
	 * Called by doStartTag to perform the actual work.
	 * @return same as TagSupport.doStartTag
	 * @throws Exception any exception, any checked one other than
	 * a JspException gets wrapped in a JspException by doStartTag
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag
	 */
	protected abstract int doStartTagInternal() throws Exception;


	public void doCatch(Throwable throwable) throws Throwable {
		maf.logger.Logging.trace(throwable);
		throw throwable;
	}
	public void doFinally() {
		this.requestContext = null;
	}
	
	/**
	 * 현재 PageContext의 Locale 를 돌려줌 
	 * @return
	 */
	public Locale getSafeLocale() {

		return (this.locale != null)?this.locale:getRequestContext().getLocale();
	}
	
	/**
	 * 해당 bundle의 key대한 locale별 메시지를 가져온다.
	 * @param baseName
	 * @param key
	 * @return
	 * @throws NoSuchMessageException
	 * @throws NoSuchBundleException
	 */
	public String getMessage( String key) throws NoSuchMessageException, NoSuchBundleException, NoSuchLocaleMessageException{
		return MafResourceStore.getMessage(this.baseName, getSafeLocale(), key);
	}
	
	/**
	 * 해당 bundle의 key대한 locale별 resource 를 가져온다.
	 * @param baseName
	 * @param key
	 * @return
	 * @throws NoSuchMessageException
	 * @throws NoSuchBundleException
	 */
	public MafResourceLocale getMafResourceLocale( String key) throws NoSuchMessageException, NoSuchBundleException{
		return MafResourceStore.getMafResourceLocale(this.baseName, getSafeLocale(), key);
	}
	
	/**
	 * 현재 request의 FormConfig를 가져온다. 
	 * @return
	 */
	public FormConfig getFormConfig() {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		return (FormConfig) request.getAttribute(FormConfig.FORM_ATTRIBUTE_NAME);
	}
	
	/**
	 * 해당 필드의 header/label을 가져온다. 
	 * @param fieldName
	 * @return
	 */
	public String getFieldLabel(String fieldName) {
		String message = null;
		String messageKey = null;
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		FormConfig fc = (FormConfig) request.getAttribute(FormConfig.FORM_ATTRIBUTE_NAME);
		
		if (fc == null) {
			return "@@ Can't find FormConfig for " + fieldName + "@@";
			
		} else {
			
			String baseName = fc.getBundle();

			InputConfig inputConfig = fc.getInputConfig(fieldName);
			
			if (inputConfig != null) {
				messageKey = inputConfig.getMessage();
				baseName = MafUtil.nvl(inputConfig.getBundle(), baseName);
			} else {
				return "@@ Can't find InputConfig for " + fieldName + "@@";
			}
			try {
				message = MafResourceStore.getMessage(baseName, getSafeLocale(), messageKey);
			} catch (NoSuchLocaleMessageException el) {
				message = LocaleUtil.getNoMessageErrorString(baseName,messageKey,locale);
			} catch (NoSuchMessageException e) {
				return LocaleUtil.getNoMessageErrorString(fc.getBundle(), messageKey);
			} catch (NoSuchBundleException e) {
				return LocaleUtil.getNoMessageErrorString(fc.getBundle(), messageKey);
			}
			
		}
//		return MafUtil.nvl(message, LocaleUtil.getNoMessageErrorString(fc.getBundle(), messageKey, getSafeLocale()));
		return message;
	}
}
