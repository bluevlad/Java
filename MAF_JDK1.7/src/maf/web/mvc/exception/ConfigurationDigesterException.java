package maf.web.mvc.exception;

/**
 * ConfigurationDigester�� XML ������ �Ľ��ϴ� ���ȿ�
 * ������ ���� �� �߻��ϴ� ����
 * 
 * @author �ֹ���
 */
public class ConfigurationDigesterException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4708495303946464258L;

	public ConfigurationDigesterException(Throwable ex) {
        super(ex.getMessage(), ex);
    }
}