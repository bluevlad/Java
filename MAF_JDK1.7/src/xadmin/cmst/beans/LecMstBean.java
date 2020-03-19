/*
 * Created on 2005. 5. 17.
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.beans;

import java.util.Date;

public class LecMstBean {
	private String crsname = null;
	private String crscode = null;
	private String sjtname = null;
	private long rcnt = 0;
	
	/**
	 * 수강생 수
	 * @return Returns the rCnt.
	 */
	public long getRcnt() {
		return rcnt;
	}
	/**
	 * 수강생 수
	 * @param cnt The rCnt to set.
	 */
	public void setRcnt(long cnt) {
		rcnt = cnt;
	}
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
	
	
	/**
	 * @return Returns the crscode.
	 */
	public String getCrscode() {
		return crscode;
	}
	/**
	 * @param crscode The crscode to set.
	 */
	public void setCrscode(String crscode) {
		this.crscode = crscode;
	}
	/**
	 * @return Returns the sjtname.
	 */
	public String getSjtname() {
		return sjtname;
	}
	/**
	 * @param sjtname The sjtname to set.
	 */
	public void setSjtname(String sjtname) {
		this.sjtname = sjtname;
	}
	//==========================================
	//== 자동 생성
	//==========================================


	//==========================================
	//== 자동 생성 
	//==========================================
	

	/**
	* 과목코드-년도-학기
	*/	
	private String leccode = null;

	/**
	* 과정코드
	*/	
	private String sjtcode = null;

		
	private String lecname = null;

		
	private String lecyear = null;

		
	private String lecterm = null;

		
	private String bunban = "1";

	/**
	* 강의정원
	*/	
	private int lecquot = 0;

	/**
	* 학사담당당자
	*/	
	private String admcode = null;

	/**
	* 강의목표 및 개요
	*/	
	private String lecobjt = null;

	/**
	* 총강의컨텐츠수
	*/	
	private int lectttr = 0;

	/**
	* 목록화면 주차 구분 여부 W : 주별  T : 차별
	*/	
	private String lecwtfg = null;

	/**
	* O:개설 C:폐설 E:강의종료
	*/	
	private String lecstat = "C";

	/**
	* 기준학습시간
	*/	
	private int lectime = 0;

	/**
	* 학습기간을 일단위로 입력
	*/	
	private int lecgigan = 0;

	/**
	* 강좌 오픈일
	*/	
	private Date start_dt = null;

	/**
	* 강좌범위
	*/	
	private String lecbound = null;

	/**
	* 연계강좌
	*/	
	private String leclink = null;

	/**
	* 공지사항
	*/	
	private String lec_notice = null;

	/**
	* 샘플 컨텐츠코드정보
	*/	
	private int lecsample = 0;

	/**
	* 체험강좌여부               
Y:무료체험강좌               
N:유료강좌(기본값)
	*/	
	private String isfree = "N";

		
	private Date update_dt = null;

		
	private String update_id = null;

	/**
	* 수강신청시작일
	*/	
	private Date rstart_dt = null;

	/**
	* 수강신청종료일
	*/	
	private Date rfinish_dt = null;

	/**
	* 강좌구분: N=정규, O=상시개설
	*/	
	private String lectype = "N";

	/**
	* 강좌 종료일
	*/	
	private Date finish_dt = null;

	/**
	* 강좌명(영문)
	*/	
	private String lecenam = null;

	/**
	* 컨텐츠 ID
	*/	
	private String cnt_id = null;

	/**
	* 강의계획서파일
	*/	
	private String attach_file = null;

	/**
	* 원래 파일명
	*/	
	private String attach_filename = null;

	/**
	* 성적비율:출석율
	*/	
	private int rate_att = 10;

	/**
	* 성적비율:시험
	*/	
	private int rate_exam = 60;

	/**
	* 성적비율:과제
	*/	
	private int rate_report = 5;

	/**
	* 성적비율:토론
	*/	
	private int rate_parti = 5;

	/**
	* 성적비율:진도
	*/	
	private int rate_jindo = 20;

	/**
	* 시험성적비율:쪽지
	*/	
	private int exam_rate_j = 30;

	/**
	* 시험성적비율:정규
	*/	
	private int exam_rate_n = 70;
	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get leccode : 과목코드-년도-학기
	* DB TYPE : VARCHAR2
	*/
	public String getLeccode(){
		return this.leccode;
	}
	/**
	* Set leccode : 과목코드-년도-학기
	* DB TYPE : VARCHAR2
	*/
	public void setLeccode(String leccode){
		this.leccode = leccode;
	}

	/**
	* Get sjtcode : 과정코드
	* DB TYPE : VARCHAR2
	*/
	public String getSjtcode(){
		return this.sjtcode;
	}
	/**
	* Set sjtcode : 과정코드
	* DB TYPE : VARCHAR2
	*/
	public void setSjtcode(String sjtcode){
		this.sjtcode = sjtcode;
	}

	/**
	* Get lecname : 
	* DB TYPE : VARCHAR2
	*/
	public String getLecname(){
		return this.lecname;
	}
	/**
	* Set lecname : 
	* DB TYPE : VARCHAR2
	*/
	public void setLecname(String lecname){
		this.lecname = lecname;
	}

	/**
	* Get lecyear : 
	* DB TYPE : VARCHAR2
	*/
	public String getLecyear(){
		return this.lecyear;
	}
	/**
	* Set lecyear : 
	* DB TYPE : VARCHAR2
	*/
	public void setLecyear(String lecyear){
		this.lecyear = lecyear;
	}

	/**
	* Get lecterm : 
	* DB TYPE : VARCHAR2
	*/
	public String getLecterm(){
		return this.lecterm;
	}
	/**
	* Set lecterm : 
	* DB TYPE : VARCHAR2
	*/
	public void setLecterm(String lecterm){
		this.lecterm = lecterm;
	}

	/**
	* Get bunban : 
	* DB TYPE : VARCHAR2
	*/
	public String getBunban(){
		return this.bunban;
	}
	/**
	* Set bunban : 
	* DB TYPE : VARCHAR2
	*/
	public void setBunban(String bunban){
		this.bunban = bunban;
	}

	/**
	* Get lecquot : 강의정원
	* DB TYPE : NUMBER
	*/
	public int getLecquot(){
		return this.lecquot;
	}
	/**
	* Set lecquot : 강의정원
	* DB TYPE : NUMBER
	*/
	public void setLecquot(int lecquot){
		this.lecquot = lecquot;
	}

	/**
	* Get admcode : 학사담당당자
	* DB TYPE : VARCHAR2
	*/
	public String getAdmcode(){
		return this.admcode;
	}
	/**
	* Set admcode : 학사담당당자
	* DB TYPE : VARCHAR2
	*/
	public void setAdmcode(String admcode){
		this.admcode = admcode;
	}

	/**
	* Get lecobjt : 강의목표 및 개요
	* DB TYPE : VARCHAR2
	*/
	public String getLecobjt(){
		return this.lecobjt;
	}
	/**
	* Set lecobjt : 강의목표 및 개요
	* DB TYPE : VARCHAR2
	*/
	public void setLecobjt(String lecobjt){
		this.lecobjt = lecobjt;
	}

	/**
	* Get lectttr : 총강의컨텐츠수
	* DB TYPE : NUMBER
	*/
	public int getLectttr(){
		return this.lectttr;
	}
	/**
	* Set lectttr : 총강의컨텐츠수
	* DB TYPE : NUMBER
	*/
	public void setLectttr(int lectttr){
		this.lectttr = lectttr;
	}

	/**
	* Get lecwtfg : 목록화면 주차 구분 여부 W : 주별  T : 차별
	* DB TYPE : VARCHAR2
	*/
	public String getLecwtfg(){
		return this.lecwtfg;
	}
	/**
	* Set lecwtfg : 목록화면 주차 구분 여부 W : 주별  T : 차별
	* DB TYPE : VARCHAR2
	*/
	public void setLecwtfg(String lecwtfg){
		this.lecwtfg = lecwtfg;
	}

	/**
	* Get lecstat : O:개설 C:폐설 E:강의종료
	* DB TYPE : VARCHAR2
	*/
	public String getLecstat(){
		return this.lecstat;
	}
	/**
	* Set lecstat : O:개설 C:폐설 E:강의종료
	* DB TYPE : VARCHAR2
	*/
	public void setLecstat(String lecstat){
		this.lecstat = lecstat;
	}

	/**
	* Get lectime : 기준학습시간
	* DB TYPE : NUMBER
	*/
	public int getLectime(){
		return this.lectime;
	}
	/**
	* Set lectime : 기준학습시간
	* DB TYPE : NUMBER
	*/
	public void setLectime(int lectime){
		this.lectime = lectime;
	}

	/**
	* Get lecgigan : 학습기간을 일단위로 입력
	* DB TYPE : NUMBER
	*/
	public int getLecgigan(){
		return this.lecgigan;
	}
	/**
	* Set lecgigan : 학습기간을 일단위로 입력
	* DB TYPE : NUMBER
	*/
	public void setLecgigan(int lecgigan){
		this.lecgigan = lecgigan;
	}

	/**
	* Get start_dt : 강좌 오픈일
	* DB TYPE : DATE
	*/
	public Date getStart_dt(){
		return this.start_dt;
	}
	/**
	* Set start_dt : 강좌 오픈일
	* DB TYPE : DATE
	*/
	public void setStart_dt(Date start_dt){
		this.start_dt = start_dt;
	}

	/**
	* Get lecbound : 강좌범위
	* DB TYPE : VARCHAR2
	*/
	public String getLecbound(){
		return this.lecbound;
	}
	/**
	* Set lecbound : 강좌범위
	* DB TYPE : VARCHAR2
	*/
	public void setLecbound(String lecbound){
		this.lecbound = lecbound;
	}

	/**
	* Get leclink : 연계강좌
	* DB TYPE : VARCHAR2
	*/
	public String getLeclink(){
		return this.leclink;
	}
	/**
	* Set leclink : 연계강좌
	* DB TYPE : VARCHAR2
	*/
	public void setLeclink(String leclink){
		this.leclink = leclink;
	}

	/**
	* Get lec_notice : 공지사항
	* DB TYPE : VARCHAR2
	*/
	public String getLec_notice(){
		return this.lec_notice;
	}
	/**
	* Set lec_notice : 공지사항
	* DB TYPE : VARCHAR2
	*/
	public void setLec_notice(String lec_notice){
		this.lec_notice = lec_notice;
	}

	/**
	* Get lecsample : 샘플 컨텐츠코드정보
	* DB TYPE : NUMBER
	*/
	public int getLecsample(){
		return this.lecsample;
	}
	/**
	* Set lecsample : 샘플 컨텐츠코드정보
	* DB TYPE : NUMBER
	*/
	public void setLecsample(int lecsample){
		this.lecsample = lecsample;
	}

	/**
	* Get isfree : 체험강좌여부               
Y:무료체험강좌               
N:유료강좌(기본값)
	* DB TYPE : VARCHAR2
	*/
	public String getIsfree(){
		return this.isfree;
	}
	/**
	* Set isfree : 체험강좌여부               
Y:무료체험강좌               
N:유료강좌(기본값)
	* DB TYPE : VARCHAR2
	*/
	public void setIsfree(String isfree){
		this.isfree = isfree;
	}

	/**
	* Get update_dt : 
	* DB TYPE : DATE
	*/
	public Date getUpdate_dt(){
		return this.update_dt;
	}
	/**
	* Set update_dt : 
	* DB TYPE : DATE
	*/
	public void setUpdate_dt(Date update_dt){
		this.update_dt = update_dt;
	}

	/**
	* Get update_id : 
	* DB TYPE : VARCHAR2
	*/
	public String getUpdate_id(){
		return this.update_id;
	}
	/**
	* Set update_id : 
	* DB TYPE : VARCHAR2
	*/
	public void setUpdate_id(String update_id){
		this.update_id = update_id;
	}

	/**
	* Get rstart_dt : 수강신청시작일
	* DB TYPE : DATE
	*/
	public Date getRstart_dt(){
		return this.rstart_dt;
	}
	/**
	* Set rstart_dt : 수강신청시작일
	* DB TYPE : DATE
	*/
	public void setRstart_dt(Date rstart_dt){
		this.rstart_dt = rstart_dt;
	}

	/**
	* Get rfinish_dt : 수강신청종료일
	* DB TYPE : DATE
	*/
	public Date getRfinish_dt(){
		return this.rfinish_dt;
	}
	/**
	* Set rfinish_dt : 수강신청종료일
	* DB TYPE : DATE
	*/
	public void setRfinish_dt(Date rfinish_dt){
		this.rfinish_dt = rfinish_dt;
	}

	/**
	* Get lectype : 강좌구분: N=정규, O=상시개설
	* DB TYPE : CHAR
	*/
	public String getLectype(){
		return this.lectype;
	}
	/**
	* Set lectype : 강좌구분: N=정규, O=상시개설
	* DB TYPE : CHAR
	*/
	public void setLectype(String lectype){
		this.lectype = lectype;
	}

	/**
	* Get finish_dt : 강좌 종료일
	* DB TYPE : DATE
	*/
	public Date getFinish_dt(){
		return this.finish_dt;
	}
	/**
	* Set finish_dt : 강좌 종료일
	* DB TYPE : DATE
	*/
	public void setFinish_dt(Date finish_dt){
		this.finish_dt = finish_dt;
	}

	/**
	* Get lecenam : 강좌명(영문)
	* DB TYPE : VARCHAR2
	*/
	public String getLecenam(){
		return this.lecenam;
	}
	/**
	* Set lecenam : 강좌명(영문)
	* DB TYPE : VARCHAR2
	*/
	public void setLecenam(String lecenam){
		this.lecenam = lecenam;
	}

	/**
	* Get cnt_id : 컨텐츠 ID
	* DB TYPE : VARCHAR2
	*/
	public String getCnt_id(){
		return this.cnt_id;
	}
	/**
	* Set cnt_id : 컨텐츠 ID
	* DB TYPE : VARCHAR2
	*/
	public void setCnt_id(String cnt_id){
		this.cnt_id = cnt_id;
	}

	/**
	* Get attach_file : 강의계획서파일
	* DB TYPE : VARCHAR2
	*/
	public String getAttach_file(){
		return this.attach_file;
	}
	/**
	* Set attach_file : 강의계획서파일
	* DB TYPE : VARCHAR2
	*/
	public void setAttach_file(String attach_file){
		this.attach_file = attach_file;
	}

	/**
	* Get attach_filename : 원래 파일명
	* DB TYPE : VARCHAR2
	*/
	public String getAttach_filename(){
		return this.attach_filename;
	}
	/**
	* Set attach_filename : 원래 파일명
	* DB TYPE : VARCHAR2
	*/
	public void setAttach_filename(String attach_filename){
		this.attach_filename = attach_filename;
	}

	/**
	* Get rate_att : 성적비율:출석율
	* DB TYPE : NUMBER
	*/
	public int getRate_att(){
		return this.rate_att;
	}
	/**
	* Set rate_att : 성적비율:출석율
	* DB TYPE : NUMBER
	*/
	public void setRate_att(int rate_att){
		this.rate_att = rate_att;
	}

	/**
	* Get rate_exam : 성적비율:시험
	* DB TYPE : NUMBER
	*/
	public int getRate_exam(){
		return this.rate_exam;
	}
	/**
	* Set rate_exam : 성적비율:시험
	* DB TYPE : NUMBER
	*/
	public void setRate_exam(int rate_exam){
		this.rate_exam = rate_exam;
	}

	/**
	* Get rate_report : 성적비율:과제
	* DB TYPE : NUMBER
	*/
	public int getRate_report(){
		return this.rate_report;
	}
	/**
	* Set rate_report : 성적비율:과제
	* DB TYPE : NUMBER
	*/
	public void setRate_report(int rate_report){
		this.rate_report = rate_report;
	}

	/**
	* Get rate_parti : 성적비율:토론
	* DB TYPE : NUMBER
	*/
	public int getRate_parti(){
		return this.rate_parti;
	}
	/**
	* Set rate_parti : 성적비율:토론
	* DB TYPE : NUMBER
	*/
	public void setRate_parti(int rate_parti){
		this.rate_parti = rate_parti;
	}

	/**
	* Get rate_jindo : 성적비율:진도
	* DB TYPE : NUMBER
	*/
	public int getRate_jindo(){
		return this.rate_jindo;
	}
	/**
	* Set rate_jindo : 성적비율:진도
	* DB TYPE : NUMBER
	*/
	public void setRate_jindo(int rate_jindo){
		this.rate_jindo = rate_jindo;
	}
	
	/**
	* Get exam_rate_j : 시험성적비율:쪽지
	* DB TYPE : NUMBER
	*/
	public int getExam_rate_j(){
		return this.exam_rate_j;
	}
	/**
	* Set exam_rate_j : 시험성적비율:쪽지
	* DB TYPE : NUMBER
	*/
	public void setExam_rate_j(int exam_rate_j){
		this.exam_rate_j = exam_rate_j;
	}

	/**
	* Get exam_rate_n : 시험성적비율:정규
	* DB TYPE : NUMBER
	*/
	public int getExam_rate_n(){
		return this.exam_rate_n;
	}
	/**
	* Set exam_rate_n : 시험성적비율:정규
	* DB TYPE : NUMBER
	*/
	public void setExam_rate_n(int exam_rate_n){
		this.exam_rate_n = exam_rate_n;
	}

}
