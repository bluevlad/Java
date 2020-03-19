/*
 * Created on 2004-05-28
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package maf.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * File Name : SetCharacterEncodingFilter.java <br>
 * Description : <br>
 * Charactor Encoding Servlet Filter
 * 
 * @inheritdoc
 * @author ±Ë¿±√∂
 * @version 1.0
 * @modifydate 2004-05-28
 */
public class SetCharacterEncodingFilter implements Filter {
	// ----------------------------------------------------- Instance Variables
	/**
	 * The default character encoding to set for requests that pass through this filter.
	 */
	protected String encoding = null;
	/**
	 * The filter configuration object we are associated with. If this value is null, this filter instance is not currently
	 * configured.
	 */
	protected FilterConfig filterConfig = null;
	/**
	 * Should a character encoding specified by the client be ignored?
	 */
	protected boolean forceEncoding = true;

	// --------------------------------------------------------- Public Methods
	/**
	 * Take this filter out of service.
	 */
	public void destroy() {
		System.out.println("### " + this.getClass() + " destroy ### ");
		this.encoding = null;
		this.filterConfig = null;
	}

	/**
	 * Select and set (if specified) the character encoding to be used to interpret request parameters for this request.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param result
	 *            The servlet response we are creating
	 * @param chain
	 *            The filter chain we are processing
	 * 
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet error occurs
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Conditionally select and set the character encoding to be used
		if (forceEncoding || (request.getCharacterEncoding() == null)) {
			String encoding = selectEncoding(request);
			if (encoding != null) {
				request.setCharacterEncoding(encoding);
//				System.out.println("filter:" + this.encoding + ", request :" + request.getCharacterEncoding());
			}
		}
		// Pass control on to the next filter
		chain.doFilter(request, response);
	}

	/**
	 * Place this filter into service.
	 * 
	 * @param filterConfig
	 *            The filter configuration object
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null) this.forceEncoding = true;
		else if (value.equalsIgnoreCase("true")) this.forceEncoding = true;
		else if (value.equalsIgnoreCase("yes")) {
			this.forceEncoding = true;
		} else {
			this.forceEncoding = false;
		}
		System.out.println("### " + this.getClass() + " / " +  this.encoding + " / forceEncoding : " + this.forceEncoding + " init ### ");
	}

	// ------------------------------------------------------ Protected Methods
	protected String selectEncoding(ServletRequest request) {
		return (this.encoding);
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig cfg) {
		filterConfig = cfg;
	}
}
