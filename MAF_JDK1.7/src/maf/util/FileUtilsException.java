
package maf.util;

public class FileUtilsException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7193758891977921975L;

	/**
     * 
     */
    public FileUtilsException() {
        super();
    }

    /**
     * @param message
     */
    public FileUtilsException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public FileUtilsException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public FileUtilsException(String message, Throwable cause) {
        super(message, cause);
    }

}
