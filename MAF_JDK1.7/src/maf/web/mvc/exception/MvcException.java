package maf.web.mvc.exception;

import maf.exception.MafException;
import maf.logger.Trace;



/**
 * ConfigurationDigester가 XML 문서를 파싱하는 동안에
 * 문제가 있을 때 발생하는 예외
 */
public class MvcException extends MafException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 22860867820264922L;

    public MvcException(String msg) {
        super(msg);
        logging(msg,null);
    }
    public MvcException(Throwable e) {
        super(e);
        logging("", e);
    }
    public MvcException(String msg, Throwable e) {
        super(msg, e);
        logging(msg, e);
    }
    
    private  void logging ( String msg, Throwable e) {

        if (e != null) {
            System.out.println("==" + msg + "\n" + Trace.getStackTrace(e));
        } else {
        	System.out.println("==" + msg + "\n");
        }
       
    }
}