package maf.web.mvc.exception;

import maf.exception.MafException;
import maf.logger.Trace;



/**
 * ConfigurationDigester�� XML ������ �Ľ��ϴ� ���ȿ�
 * ������ ���� �� �߻��ϴ� ����
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