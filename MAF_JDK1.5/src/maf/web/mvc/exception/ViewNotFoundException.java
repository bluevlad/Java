/*
 * ��û�� Command�� ��Ʈ�ѷ��� �������� ������ �߻���Ű�� ���� Ŭ����
 *  @ author : �����
 *  
 */

package maf.web.mvc.exception;

import maf.exception.MafException;


public class ViewNotFoundException extends MafException {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewNotFoundException() {
        super();
    }

    public ViewNotFoundException(String msg) {
        super(msg);
       
    }
}