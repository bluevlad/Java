/*
 * ��û�� Command�� ��Ʈ�ѷ��� �������� ������ �߻���Ű�� ���� Ŭ����
 *  @ author : �����
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