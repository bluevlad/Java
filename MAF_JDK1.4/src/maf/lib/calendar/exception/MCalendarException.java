/*
 * Created on 2005-01-10
 */
package maf.lib.calendar.exception;

/**
 *  
 */
public class MCalendarException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3919155584843619152L;

	/**
	 * 
	 */
	public MCalendarException() {
		super();
	}

	/**
	 * @param message
	 */
	public MCalendarException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MCalendarException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MCalendarException(String message, Throwable cause) {
		super(message, cause);
	}
}