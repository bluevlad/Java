/*
 * 작성된 날짜: 2005-01-27
 *
 */
package miraenet.app.msg;


/**
 * @author goindole
 *
 */
public class SMSException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1835839238858833107L;
	public SMSException(Throwable e) {
        super(e);
    }
    public SMSException(String msg) {
        super(msg);
    }
    public SMSException(String msg, Throwable e) {
        super(msg, e);
    }
}
