package modules.wlc.exceptions;

import maf.exception.MafException;

/**
 * 강의실 프로그램에서 강의실 코드가 없을 경우 발생 하는 
 * @author bluevlad
 *
 */
public class Lec_cdInvalidException  extends MafException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key = null;
	public Lec_cdInvalidException() {
	}

	public Lec_cdInvalidException(String key) {
		super("parameter " + key + " not summited");
		this.key = key;
	}

	public Lec_cdInvalidException(Throwable e) {
		super(e);
	}

	public Lec_cdInvalidException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public String getKey() {
		return this.key;
	}
}
