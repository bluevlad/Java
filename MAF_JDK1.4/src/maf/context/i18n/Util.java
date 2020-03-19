package maf.context.i18n;

import org.jdom.Element;
import maf.MafUtil;

public class Util {
	
	protected static void setOptionalAttribute(Element elm , String name, String value) {
		if(! MafUtil.empty(value )) {
			elm.setAttribute(name, value);
		}
	}
}
