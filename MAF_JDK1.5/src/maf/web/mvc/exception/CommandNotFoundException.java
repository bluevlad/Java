/*
 * 요청한 Command가 콘트롤러에 존재하지 않을때 발생시키는 예외 클래스
 *  @ author : 김상준
 *  
 */

package maf.web.mvc.exception;

import maf.exception.MafException;


public class CommandNotFoundException extends MafException {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommandNotFoundException() {
        super();
    }

    public CommandNotFoundException(String msg) {
        super(msg);
       
    }
}