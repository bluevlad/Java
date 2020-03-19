package maf.mdb.exceptions;

import maf.exception.MafException;

/**
 * Adodb와 관련된 에러가 발생할 경우 발생하는 예외.
 *
 */
public class MdbException  extends MafException  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 840733396525289553L;


	/**
	 * @param message
	 */
	public MdbException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MdbException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MdbException(String message, Throwable cause) {
		super(message, cause);
	}
}
