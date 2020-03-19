package maf.web.mvc.message.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import maf.web.mvc.form.FormData;

public class FormDataTag extends TagSupport {
    
    private String name;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int doStartTag() throws JspException {
        try {
            FormData formData = FormData.getFrom((HttpServletRequest)pageContext.getRequest());
            if (name != null && formData != null) {
                String value = formData.getParameter(name);
                if (value != null) pageContext.getOut().print(value);
            }
        } catch(IOException ex) {
            throw new JspException(ex);
        }
        return SKIP_BODY;
    }

    public int doEndTag() {
        name = null;
        return EVAL_PAGE;
    }

}