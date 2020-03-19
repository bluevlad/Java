package maf.context.exceptions;

import java.util.Locale;

public class NoSuchLocaleMessageException extends MafResourceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create a new exception.
	 * @param code code that could not be resolved for given locale
	 * @param locale locale that was used to search for the code within
	 */
	public NoSuchLocaleMessageException(Locale locale) {
		super("No message found under locale '" + locale );
	}
	
	public NoSuchLocaleMessageException(String locale) {
		super("No message found under locale '" + locale +"'" );
	}
}
