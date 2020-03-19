/*
 * SjtMstBean.java, @ 2005-05-12
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.beans;

import java.util.Date;

/**
 * @author goindole
 *
 */
public class SjtMstBean {
	private String crsname = null;
	
	/**
	 * @return Returns the crsname.
	 */
	public String getCrsname() {
		return crsname;
	}
	/**
	 * @param crsname The crsname to set.
	 */
	public void setCrsname(String crsname) {
		this.crsname = crsname;
	}
	//==========================================
	//== 자동 생성 
	//==========================================
	
	/**
	* 교과목코드
	*/	
	private String sjtcode = null;

	/**
	* 과정코드
	*/	
	private String crscode = null;

	/**
	* 교과목 한글명칭
	*/	
	private String sjtname = null;

	/**
	* 교과목 영문명칭
	*/	
	private String sjtenam = null;

	/**
	* 학점
	*/	
	private String sjtpoint = null;

	/**
	* 강의시간
	*/	
	private int lec_point = 0;

	/**
	* 실습시간
	*/	
	private int trn_point = 0;

	/**
	* 동일과목코드
	*/	
	private String eq_sjtcode = null;

	/**
	* 동일교과목명
	*/	
	private String eq_sjtname = null;

	/**
	* 대체교과목코드
	*/	
	private String od_sjtcode = null;

	/**
	* 대체교과목명
	*/	
	private String od_sjtname = null;

	/**
	* 최종수정일시
	*/	
	private Date update_dt = null;

	/**
	* 최종수정자 ID
	*/	
	private String update_id = null;

	/**
	* 수강대상
	*/	
	private String sjttarget = null;

	/**
	* 과목소개
	*/	
	private String sjtdesc = null;

	/**
	* 선이수과목
	*/	
	private String reqsjt = null;


	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get sjtcode : 교과목코드
	* DB TYPE : VARCHAR2
	*/
	public String getSjtcode(){
		return this.sjtcode;
	}
	/**
	* Set sjtcode : 교과목코드
	* DB TYPE : VARCHAR2
	*/
	public void setSjtcode(String sjtcode){
		this.sjtcode = sjtcode;
	}

	/**
	* Get crscode : 과정코드
	* DB TYPE : VARCHAR2
	*/
	public String getCrscode(){
		return this.crscode;
	}
	/**
	* Set crscode : 과정코드
	* DB TYPE : VARCHAR2
	*/
	public void setCrscode(String crscode){
		this.crscode = crscode;
	}

	/**
	* Get sjtname : 교과목 한글명칭
	* DB TYPE : VARCHAR2
	*/
	public String getSjtname(){
		return this.sjtname;
	}
	/**
	* Set sjtname : 교과목 한글명칭
	* DB TYPE : VARCHAR2
	*/
	public void setSjtname(String sjtname){
		this.sjtname = sjtname;
	}

	/**
	* Get sjtenam : 교과목 영문명칭
	* DB TYPE : VARCHAR2
	*/
	public String getSjtenam(){
		return this.sjtenam;
	}
	/**
	* Set sjtenam : 교과목 영문명칭
	* DB TYPE : VARCHAR2
	*/
	public void setSjtenam(String sjtenam){
		this.sjtenam = sjtenam;
	}

	/**
	* Get sjtpoint : 학점
	* DB TYPE : VARCHAR2
	*/
	public String getSjtpoint(){
		return this.sjtpoint;
	}
	/**
	* Set sjtpoint : 학점
	* DB TYPE : VARCHAR2
	*/
	public void setSjtpoint(String sjtpoint){
		this.sjtpoint = sjtpoint;
	}

	/**
	* Get lec_point : 강의시간
	* DB TYPE : NUMBER
	*/
	public int getLec_point(){
		return this.lec_point;
	}
	/**
	* Set lec_point : 강의시간
	* DB TYPE : NUMBER
	*/
	public void setLec_point(int lec_point){
		this.lec_point = lec_point;
	}

	/**
	* Get trn_point : 실습시간
	* DB TYPE : NUMBER
	*/
	public int getTrn_point(){
		return this.trn_point;
	}
	/**
	* Set trn_point : 실습시간
	* DB TYPE : NUMBER
	*/
	public void setTrn_point(int trn_point){
		this.trn_point = trn_point;
	}

	/**
	* Get eq_sjtcode : 동일과목코드
	* DB TYPE : VARCHAR2
	*/
	public String getEq_sjtcode(){
		return this.eq_sjtcode;
	}
	/**
	* Set eq_sjtcode : 동일과목코드
	* DB TYPE : VARCHAR2
	*/
	public void setEq_sjtcode(String eq_sjtcode){
		this.eq_sjtcode = eq_sjtcode;
	}

	/**
	* Get eq_sjtname : 동일교과목명
	* DB TYPE : VARCHAR2
	*/
	public String getEq_sjtname(){
		return this.eq_sjtname;
	}
	/**
	* Set eq_sjtname : 동일교과목명
	* DB TYPE : VARCHAR2
	*/
	public void setEq_sjtname(String eq_sjtname){
		this.eq_sjtname = eq_sjtname;
	}

	/**
	* Get od_sjtcode : 대체교과목코드
	* DB TYPE : VARCHAR2
	*/
	public String getOd_sjtcode(){
		return this.od_sjtcode;
	}
	/**
	* Set od_sjtcode : 대체교과목코드
	* DB TYPE : VARCHAR2
	*/
	public void setOd_sjtcode(String od_sjtcode){
		this.od_sjtcode = od_sjtcode;
	}

	/**
	* Get od_sjtname : 대체교과목명
	* DB TYPE : VARCHAR2
	*/
	public String getOd_sjtname(){
		return this.od_sjtname;
	}
	/**
	* Set od_sjtname : 대체교과목명
	* DB TYPE : VARCHAR2
	*/
	public void setOd_sjtname(String od_sjtname){
		this.od_sjtname = od_sjtname;
	}

	/**
	* Get update_dt : 최종수정일시
	* DB TYPE : DATE
	*/
	public Date getUpdate_dt(){
		return this.update_dt;
	}
	/**
	* Set update_dt : 최종수정일시
	* DB TYPE : DATE
	*/
	public void setUpdate_dt(Date update_dt){
		this.update_dt = update_dt;
	}

	/**
	* Get update_id : 최종수정자 ID
	* DB TYPE : VARCHAR2
	*/
	public String getUpdate_id(){
		return this.update_id;
	}
	/**
	* Set update_id : 최종수정자 ID
	* DB TYPE : VARCHAR2
	*/
	public void setUpdate_id(String update_id){
		this.update_id = update_id;
	}

	/**
	* Get sjttarget : 수강대상
	* DB TYPE : VARCHAR2
	*/
	public String getSjttarget(){
		return this.sjttarget;
	}
	/**
	* Set sjttarget : 수강대상
	* DB TYPE : VARCHAR2
	*/
	public void setSjttarget(String sjttarget){
		this.sjttarget = sjttarget;
	}

	/**
	* Get sjtdesc : 과목소개
	* DB TYPE : CLOB
	*/
	public String getSjtdesc(){
		return this.sjtdesc;
	}
	/**
	* Set sjtdesc : 과목소개
	* DB TYPE : CLOB
	*/
	public void setSjtdesc(String sjtdesc){
		this.sjtdesc = sjtdesc;
	}

	/**
	* Get reqsjt : 선이수과목
	* DB TYPE : VARCHAR2
	*/
	public String getReqsjt(){
		return this.reqsjt;
	}
	/**
	* Set reqsjt : 선이수과목
	* DB TYPE : VARCHAR2
	*/
	public void setReqsjt(String reqsjt){
		this.reqsjt = reqsjt;
	}

}
