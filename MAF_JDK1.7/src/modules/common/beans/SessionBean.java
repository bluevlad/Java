/*
 * Created on 2004. 12. 21.
 *
 * Login ���� ��� ����� Session ������ ������ �ٴ�
 */
package modules.common.beans;

import maf.base.BaseHttpSession;
import modules.common.beans.UsrMstBean;

/**
 * Login�� ���������� ������ �ٴ� Tomcat Clustering�� ���� ���� implements java.io.Serializable �Ұ�
 */
public class SessionBean extends BaseHttpSession {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String cust_cd = null;  //���� �ڵ�
    private String sjt_cd = null; //�����ڵ�(���� ���̺��� )
    private String leccode = null; //�����ڵ�
    private String lecnumb = null; //���� ����(���� ��û�� ���� ������û Ƚ���� ��)
    private String email = null;  //email

    //    String auth = null; // �ӽ� ���� : ��Ȳ�� ���� ������ ����; ���ǽǰ�� �л��� ���������� ������ ����

    //    Map courses = new HashMap();
    //    SimpleCourseBean[] courses = null;

    public SessionBean(UsrMstBean ob, String loginIP) {
        this.setUsn(ob.getUsn());
        this.setUserid( ob.getUserid());
        this.setNm( ob.getNm());
        this.setLoginIP(loginIP);
        this.cust_cd = ob.getCust_cd();
        this.email = ob.getEmail();
    }

    /**
     * @return the compID
     */
    public String getCust_cd() {
        return cust_cd;
    }

    /**
     * @return the leccode
     */
    public String getLeccode() {
        return leccode;
    }

    /**
     * @param leccode
     */
    public void setLeccode(String leccode) {
        this.leccode = leccode;
    }

    /**
     * @return lecnumb
     */
    public String getLecnumb() {
        return lecnumb;
    }

    /**
     * @param lecnumb
     */
    public void setLecnumb(String lecnumb) {
        this.lecnumb = lecnumb;
    }

    /**
     * @return sjt_cd
     */
    public String getSjt_cd() {
        return sjt_cd;
    }

    /**
     * @param sjt_cd
     */
    public void setSjt_cd(String sjt_cd) {
        this.sjt_cd = sjt_cd;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}