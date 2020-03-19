/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tag.support;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class MafHtmlTagSupport extends MafBodyTag{
	
	private Log logger = LogFactory.getLog(MafHtmlTagSupport.class);
	
    protected static final String UNDEFINED_STR = null;
    
    protected abstract boolean hasBody();
    protected abstract String getTagName();
    protected abstract boolean wellFormed();
    
    
    /** Holds value of property ID. */
    protected String id = UNDEFINED_STR;
    
    public void writeAttributes(Writer wr){
        try {
            appendAttributeDeclaration(wr, getId(), "ID");

        } catch(IOException ioex){
            ioex.printStackTrace();
        }
    }
    
    protected void appendAttributeDeclaration(Writer wr, String attr, String name) throws IOException {
        if(attr != UNDEFINED_STR){
            wr.write(" " + name + "=\"" + attr + "\"");
        }
    }


	protected void reset() {
		this.id = UNDEFINED_STR;
	}


	public int doStartTag() throws JspException {
		JspWriter writer = pageContext.getOut();
        try {
            writer.write("<" + getTagName());
            writeAttributes(writer);
            if(hasBody()){
                writer.write(">");
                return EVAL_PAGE;
            } else {
                return SKIP_BODY;
            }
        } catch(IOException ioe){
        	super.pageContext.getServletContext().log("",ioe);
            throw new JspException(ioe.getMessage());
        }
	}

	public int doEndTag() throws JspException {
		JspWriter writer = pageContext.getOut();
        try{
            if(hasBody()){
                writer.write("</" + getTagName() + ">");
            } else {
                if(wellFormed()){
                    writer.write(" /");
                }
                writer.write(">");
            }
            return doAfterEndTag(EVAL_PAGE);
        } catch(IOException ioe){
        	super.pageContext.getServletContext().log("",ioe);
            throw new JspException(ioe.getMessage());
        }

	}


}
