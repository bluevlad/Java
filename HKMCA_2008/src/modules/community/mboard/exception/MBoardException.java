/*
 * 작성자 : bluevlad
 * Created on 2004. 10. 22.
 *
 */
package modules.community.mboard.exception;


/**
 * @author bluevlad
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
        super("게시판오류입니다.\n");
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
