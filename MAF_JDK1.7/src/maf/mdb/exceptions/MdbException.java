package maf.mdb.exceptions;

import maf.exception.MafException;

/**
 * Adodb�� ���õ� ������ �߻��� ��� �߻��ϴ� ����.
 *
 */
public class MdbException  extends MafException  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 840733396525289553L;


	/**
	 * @param message
	 */
	public MdbException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MdbException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MdbException(String message, Throwable cause) {
		super(message, cause);
	}
}
