/*
 * Copyright 2002-2005 the original author or authors.
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

package maf.web.servlet.tag;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;

import maf.web.mvc.beans.SiteInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract class RequestContextAwareTag extends BodyTagSupport implements TryCatchFinally {



	protected final Log logger = LogFactory.getLog(getClass());
	protected SiteInfo siteInfo = null;

	public int doStartTag() throws JspException {
		//params.clear();
		return EVAL_BODY_BUFFERED;
	}
	
	/**
	 * Create and expose the current RequestContext.
	 * Delegates to <code>doStartTagInternal</code> for actual work.
	 * @see #doStartTagInternal
	 * @see #REQUEST_CONTEXT_PAGE_ATTRIBUTE
	 * @see org.springframework.web.servlet.support.JspAwareRequestContext
	 */
	public final int doEndTag() throws JspException {
		
		try {

			return doEndTagInternal();
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


	protected ServletRequest getServletRequest() {
		return pageContext.getRequest();
	}

	/**
	 * Called by doStartTag to perform the actual work.
	 * @return same as TagSupport.doStartTag
	 * @throws Exception any exception, any checked one other than
	 * a JspException gets wrapped in a JspException by doStartTag
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag
	 */
	protected abstract int doEndTagInternal() throws Exception;


	public void doCatch(Throwable throwable) throws Throwable {
		throw throwable;
	}

	public void doFinally() {
		siteInfo = null;
	}

}
