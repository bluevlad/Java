/*
 * �ۼ��� : �����
 * Created on 2004. 10. 22.
 *
 */
package miraenet.app.mboard.exception;


/**
 * @author �����(goindole@miraenet.com)
 * Create by 2004. 10. 22.
 * 
 */
public class MBoardException extends Exception {
/**
	 * 
	 */
	private static final long serialVersionUID = -7282425962037161533L;

//    private static Logger logger = Logger.getLogger(MBoardException.class); 
    /**
     * 
     */
    public MBoardException() {
        super("�Խ��ǿ����Դϴ�.\n");
    }

    /**
     * @param message
     */
    public MBoardException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public MBoardException(Throwable cause) {
        super(cause);
//        logger.error(miraenet.etc.Trace.getStackTrace(cause));
    }

    /**
     * @param message
     * @param cause
     */
    public MBoardException(String message, Throwable cause) {
        super(message, cause);
//        logger.error(miraenet.etc.Trace.getStackTrace(cause));
    }

}
