package maf.lib.calendar.configure;

/**
 * ConfigurationDigester�� XML ������ �Ľ��ϴ� ���ȿ�
 * ������ ���� �� �߻��ϴ� ����
 * 
 * @author �ֹ���
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