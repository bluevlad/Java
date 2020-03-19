/*
 * 작성된 날짜: 2004-12-29
 *

 */
package miraenet.app.msg.beans;

/**
 * 쪽지 주소록 Bean
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class ContactBean {
	
	private String usn = "";			//사용자 코드 ( 주소록 소유자 )
	private String c_group = ""; //그룹명
	private String c_usn = "";		//주소록 등록자 코드
	private String nm = "";			//주소록 등록자 이름


    /**
     * @return c_group을 리턴합니다.
     */
    public String getC_group() {
        return c_group;
    }
    /**
     * @param c_group 설정하려는 c_group.
     */
    public void setC_group(String c_group) {
        this.c_group = c_group;
    }
    /**
     * @return c_usn을 리턴합니다.
     */
    public String getC_usn() {
        return c_usn;
    }
    /**
     * @param c_usn 설정하려는 c_usn.
     */
    public void setC_usn(String c_usn) {
        this.c_usn = c_usn;
    }
    /**
     * @return usn을 리턴합니다.
     */
    public String getUsn() {
        return usn;
    }
    /**
     * @param usn 설정하려는 usn.
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
