package maf.lib.calendar.configure;

/**
 * ConfigurationDigester가 XML 문서를 파싱하는 동안에
 * 문제가 있을 때 발생하는 예외
 * 
 * @author 최범균
 */
public class HolidayDigesterException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -431042550577869865L;

	public HolidayDigesterException(Throwable ex) {
        super(ex.getMessage(), ex);
    }
}