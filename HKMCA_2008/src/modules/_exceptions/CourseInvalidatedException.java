/*
 * �ۼ��� ��¥: 2005-02-15
 *
 */
package modules._exceptions;

import javax.servlet.ServletException;

/**
 * @author bluevlad, 2005-02-15
 *
 */
public class CourseInvalidatedException extends ServletException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7302234267767287383L;

	/**
	 * 
	 */
	public CourseInvalidatedException() {
		super("�α��� ������ �����ϴ�. �α��� �Ͻñ� �ٶ��ϴ�.");
	}

	/**
	 * @param message
	 */
	public CourseInvalidatedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CourseInvalidatedException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CourseInvalidatedException(String message, Throwable cause) {
		super(message, cause);
	}
}
