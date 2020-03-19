package miraenet.etc.fusionChart;


import maf.MafUtil;

import org.jdom.Content;
import org.jdom.Element;

public abstract class BeanXml {
	Element elm = null;
	
	public Element getElement() {
	    return elm;
    }
	
	public void setAttribute(String key, String value) {
		setOptionAttribute( key, value);
    }
	
	protected void setOptionAttribute( String key , String value) {
		if(!MafUtil.empty(value)) {
			elm.setAttribute(key, value);
		}
	}
	
	protected void setOptionAttribute(Element c, String key , String value) {
		if(!MafUtil.empty(value)) {
			c.setAttribute(key, value);
		}
	}
	
	public void addContent(Content obj) {
		elm.addContent(obj);
	}
}
