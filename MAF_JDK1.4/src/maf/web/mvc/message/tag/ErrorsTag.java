package maf.web.mvc.message.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import maf.web.mvc.message.ErrorMessageSet;

public class ErrorsTag extends TagSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 사용할 MessageBundleFactory의 이름 */
    private String factoryName;
    /** 사용할 메시지 번들의 이름 */
    private String bundleName;
    /** Locale을 나타내는 코드 */
    private String localeName;
    /** 관련된 ErrorMessageSet */
    private ErrorMessageSet errors;
    
    public void setFactory(String factoryName) {
        this.factoryName = factoryName;
    }
    public void setBundle(String bundle) {
        this.bundleName = bundle;
    }
    public void setLocale(String locale) {
        this.localeName = locale;
    }
    
    String getFactory() {
        return factoryName;
    }
    String getBundle() {
        return bundleName;
    }
    String getLocale() {
        return localeName;
    }
    ErrorMessageSet getErrorMessageSet() {
        return errors;
    }
    
    public int doStartTag() {
        errors = ErrorMessageSet.getFrom((HttpServletRequest)pageContext.getRequest());
        if (errors != null) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
    
    public int doEndTag() {
        factoryName = null;
        bundleName = null;
        localeName = null;
        errors = null;
        return EVAL_PAGE;
    }
}
