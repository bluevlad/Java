/*
 * �ۼ��� ��¥: 2004-12-29
 *

 */
package miraenet.app.msg.beans;

/**
 * ���� �ּҷ� Bean
 * 
 * �ۼ��� : �ڱ���
 * �ۼ��� ��¥ : 2005-01-25
 */
public class ContactBean {
	
	private String usn = "";			//����� �ڵ� ( �ּҷ� ������ )
	private String c_group = ""; //�׷��
	private String c_usn = "";		//�ּҷ� ����� �ڵ�
	private String nm = "";			//�ּҷ� ����� �̸�


    /**
     * @return c_group�� �����մϴ�.
     */
    public String getC_group() {
        return c_group;
    }
    /**
     * @param c_group �����Ϸ��� c_group.
     */
    public void setC_group(String c_group) {
        this.c_group = c_group;
    }
    /**
     * @return c_usn�� �����մϴ�.
     */
    public String getC_usn() {
        return c_usn;
    }
    /**
     * @param c_usn �����Ϸ��� c_usn.
     */
    public void setC_usn(String c_usn) {
        this.c_usn = c_usn;
    }
    /**
     * @return usn�� �����մϴ�.
     */
    public String getUsn() {
        return usn;
    }
    /**
     * @param usn �����Ϸ��� usn.
     */
    public void setUsn(String usn) {
        this.usn = usn;
    }
	/**
	 * @return Returns the nm.
	 */
	public String getNm() {
		return nm;
	}
	/**
	 * @param nm The nm to set.
	 */
	public void setNm(String nm) {
		this.nm = nm;
	}
}
