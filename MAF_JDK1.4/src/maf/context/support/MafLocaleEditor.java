package maf.context.support;

import java.util.Locale;

import maf.util.StringUtils;


public class MafLocaleEditor {
	private Locale locale = null;
	public void setAsText(String text) {
		locale = StringUtils.parseLocaleString(text);
	}

	public String getAsText() {
		
		return (locale != null ? locale.toString() : "");
	}
	
	public Locale getLocale() {
		return this.locale;
	}
}
