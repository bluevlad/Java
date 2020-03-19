package maf.context.i18n;


import maf.MafUtil;

import org.jdom.Element;

public class Util {
	
	protected static void setOptionalAttribute(Element elm , String name, String value) {
		if(! MafUtil.empty(value )) {
			elm.setAttribute(name, value);
		}
	}
}
