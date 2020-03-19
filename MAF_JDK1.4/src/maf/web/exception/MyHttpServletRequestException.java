/*
 * 작성자 : 김상준
 * Created on 2004. 10. 22.
 *
 */
package maf.web.exception;

import maf.exception.MafException;

/**
 * @author 김상준(goindole@miraenet.com)
 * Create by 2004. 10. 22.
 * 
 */
public class MyHttpServletRequestException extends MafException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2795545104169967959L;

	/**
     * 
     */
    public MyHttpServletRequestException() {
        super("Request요청중 오류가 발생하였습니다.!!!");
    }

    public MyHttpServletRequestException(String message) {
        super(message);
    }

    public MyHttpServletRequestException(Throwable cause) {
        super(cause);
    }

    public MyHttpServletRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
