/*
 * Created on 2004. 12. 21.
 *
 * Login 했을 경우 사용자 Session 정보를 가지고 다님
 */
package modules.common.beans;

import maf.base.BaseHttpSession;
import modules.common.beans.UsrMstBean;

/**
 * Login된 세션정보를 가지고 다님 Tomcat Clustering을 위해 필히 implements java.io.Serializable 할것
 */
public class SessionBean extends BaseHttpSession {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String cust_cd = null;  //고객사 코드
    private String sjt_cd = null; //과목코드(과목 테이블의 )
    private String leccode = null; //강의코드
    private String lecnumb = null; //강의 차수(수강 신청에 따라 수강신청 횟수가 됨)
    private String email = null;  //email

    //    String auth = null; // 임시 권한 : 상황에 따라 권한이 변함; 강의실경우 학생이 교수권한을 가질수 있음

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