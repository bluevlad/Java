/*
 * ��û�� Command�� ��Ʈ�ѷ��� �������� ������ �߻���Ű�� ���� Ŭ����
 *  @ author : �����
 *  
 */

package maf.web.mvc.exception;

import maf.exception.MafException;

public class ResourceNotFoundException extends MafException {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
       
    }
}