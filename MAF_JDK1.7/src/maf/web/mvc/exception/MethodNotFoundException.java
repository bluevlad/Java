/*
 * ��û�� Command�� ��Ʈ�ѷ��� �������� ������ �߻���Ű�� ���� Ŭ����
 *  @ author : �����
 *  
 */

package maf.web.mvc.exception;

import maf.exception.MafException;


public class MethodNotFoundException extends MafException {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MethodNotFoundException() {
        super();
    }

    public MethodNotFoundException(String msg) {
        super(msg);
       
    }
    public MethodNotFoundException(String msg, Exception e) {
        super(msg, e);
       
    }
}