/*
 * 작성된 날짜: 2005-02-18
 *
 */
package maf.web.exception;

/**
 * 
 *
 * 작성자 :신규문
 * 작성된 날짜 : 2005-02-18
 */
public class UploadedFileException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4040689436755349941L;

	/**
     * 
     */
    public UploadedFileException() {
        super();
    }

    /**
     * @param message
     */
    public UploadedFileException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public UploadedFileException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public UploadedFileException(String message, Throwable cause) {
        super(message, cause);
    }

}
