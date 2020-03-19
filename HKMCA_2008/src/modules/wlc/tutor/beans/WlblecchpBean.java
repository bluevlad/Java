/*
 * By beans generator source file
 * source file name : LbLecChpBean.java
 *
 * ### BeanMaker 1.0 ###
 * @author  miraenet
 * @date
 *
 */

package modules.wlc.tutor.beans;

import java.util.Date;

public class WlblecchpBean{

    /**
     * 강의코드
     */
    private String leccode = null;

    /**
     * ITEM식별자
     */
    private String itm_id = null;

    /**
     * Organization 식별자 manifest.xml  -
     * organization identifier
     */
    private String org_id = null;

    /**
     * Course 식별자 manifest.xml  -
     * manifest identifier
     */
    private String crs_id = null;

    /**
     * Resource 식별자
     */
    private String res_id = null;

    /**
     * SCO 제작시 생성정보
     * cmi.launch_data
     */
    private String launch_data = null;

    /**
     * 성취 최대 점수(0~100)
     * cmi.core.score.max
     */
    private String score_max = null;

    /**
     * 성취 최소 점수(0~100)
     * cmi.core.score.min
     */
    private String score_min = null;

    /**
     * LMS 코멘트 (학습개요, 학습목표,학습공지,학습제목,학습주안점 등을 LMS를 이용하여 전달)
     * cmi.comments_from_lms
     */
    private String comments_from_lms = null;

    /**
     * Item의 제목을 보일것인가의 유무(Y:YES, N:NO)
     */
    private String itm_isvisible = null;

    /**
     * ITEM 제목
     */
    private String itm_title = null;

    /**
     * 호출시 넘겨주는 파라미터 값
     */
    private String itm_parameterstring = null;

    /**
     * 선수 ITEM들 이 ITEM이 수행되기 위해서 먼저 선행학습 되어야 할 ITEM들
     */
    private String itm_prerequisites = null;

    /**
     * ITEM 유형("aicc_script")
     */
    private String itm_type = null;

    /**
     * 최대 학습 허용 시간(HHHH:MM:SS.SS)
     * cmi.student_data.max_time_allowed
     */
    private String itm_maxtimeallowed = null;

    /**
     * 학습시간 초과후  발생 이벤트
     * cmi.student_data.time_limit_action
     * exit,message : SCO에서 강제 강퇴시키고 이유를 설명하는 메시지를 제시
     * exit,no message : SCO에서 강퇴시키고 이유를 설명하는 메시지를 제시하지 않음
     * continue,message : 강퇴없이 계속 학습을 진행
     * continue,no message : 계속 학습을 진행하지만 시간이 초과되었음에 대한 메시지 제
     */
    private String itm_timelimitaction = null;

    /**
     * 합격 기준 점수
     * cmi.student_data.mastery_score
     */
    private String itm_masteryscore = null;

    /**
     * 메타데이타의 위치
     * manifest.xml - oraganization item adlcp:location
     */
    private String itm_location = null;

    /**
     * 동일 Organization에서의 ITEM 순서
     */
    private long itm_sequence = 0;

    /**
     * 계층구조 순서
     */
    private long itm_lvl = 0;

    /**
     * ITEM 상위 식별자
     */
    private String itm_pid = null;

    /**
     * ON:온라인,OFF:오프라인
     */
    private String place_type = null;

    /**
     * 주[단원정보]
     */
    private long week = 0;

    /**
     * 각주차의 번호(주차마다 새로 번호부여) 1주 1강, 1주 2강, 2주 1강, 2주 2강
     */
    private long chpnumb = 0;

    /**
     * 컨텐츠코드INX_LST 테이블의 HTMCODE 값
     */
    private int htmcode = 0;

    /**
     * 총페이지수[일반컨텐츠인 경우 총페이지수]
     */
    private long totpage = 0;

    /**
     * 팝업윈도우 창 넓이
     */
    private String cnt_width = null;

    /**
     * 팝업윈도우 창높이
     */
    private String cnt_height = null;

    /**
     * 컨텐츠버전 (SCORM1.2, 일반)
     */
    private String version = null;

    /**
     * YYYYMMDD형태
     */
    private String chp_sdat = null;

    /**
     * YYYYMMDD형태
     */
    private String chp_edat = null;

    /**
     * 오프라인강의장소
     */
    private String off_place = null;

    /**
     * 오프라인강의일정
     */
    private String off_date_info = null;

    /**
     * NETg과정코스ID[NETg서버에서 관리하는 컨텐츠코스ID]
     */
    private String netg_cid = null;

    /**
     * NETg과정코스서브ID[NETg서버에서 관리하는 컨텐츠코스ID - 과정을 쪼갠경우의 ID]
     */
    private String netg_sub_cid = null;

    /**
     * 최종 수정일
     */
    private Date update_dt = null;

    /**
     * 최종수정ID
     */
    private String update_id = null;


    ////////////////////////////////////////////////////////////////////////////////


    /**
     * Get leccode : 강의코드
     * DB TYPE : VARCHAR2
     */
    public String getLeccode(){
        return this.leccode;
    }
    /**
     * Set leccode : 강의코드
     * DB TYPE : VARCHAR2
     */
    public void setLeccode(String leccode){
        this.leccode = leccode;
    }

    /**
     * Get itm_id : ITEM식별자
     * DB TYPE : VARCHAR2
     */
    public String getItm_id(){
        return this.itm_id;
    }
    /**
     * Set itm_id : ITEM식별자
     * DB TYPE : VARCHAR2
     */
    public void setItm_id(String itm_id){
        this.itm_id = itm_id;
    }

    /**
     * Get org_id : Organization 식별자
     * manifest.xml  - organization identifier
     * DB TYPE : VARCHAR2
     */
    public String getOrg_id(){
        return this.org_id;
    }
    /**
     * Set org_id : Organization 식별자
     * manifest.xml  - organization identifier
     * DB TYPE : VARCHAR2
     */
    public void setOrg_id(String org_id){
        this.org_id = org_id;
    }

    /**
     * Get crs_id : Course 식별자
     * manifest.xml  - manifest identifier
     * DB TYPE : VARCHAR2
     */
    public String getCrs_id(){
        return this.crs_id;
    }
    /**
     * Set crs_id : Course 식별자
     * manifest.xml  - manifest identifier
     * DB TYPE : VARCHAR2
     */
    public void setCrs_id(String crs_id){
        this.crs_id = crs_id;
    }

    /**
     * Get res_id : Resource 식별자
     * DB TYPE : VARCHAR2
     */
    public String getRes_id(){
        return this.res_id;
    }
    /**
     * Set res_id : Resource 식별자
     * DB TYPE : VARCHAR2
     */
    public void setRes_id(String res_id){
        this.res_id = res_id;
    }

    /**
     * Get launch_data : SCO 제작시 생성정보
     * cmi.launch_data
     * DB TYPE : VARCHAR2
     */
    public String getLaunch_data(){
        return this.launch_data;
    }
    /**
     * Set launch_data : SCO 제작시 생성정보
     * cmi.launch_data
     * DB TYPE : VARCHAR2
     */
    public void setLaunch_data(String launch_data){
        this.launch_data = launch_data;
    }

    /**
     * Get score_max : 성취 최대 점수(0~100)
     * cmi.core.score.max
     * DB TYPE : VARCHAR2
     */
    public String getScore_max(){
        return this.score_max;
    }
    /**
     * Set score_max : 성취 최대 점수(0~100)
     * cmi.core.score.max
     * DB TYPE : VARCHAR2
     */
    public void setScore_max(String score_max){
        this.score_max = score_max;
    }

    /**
     * Get score_min : 성취 최소 점수(0~100)
     * cmi.core.score.min
     * DB TYPE : VARCHAR2
     */
    public String getScore_min(){
        return this.score_min;
    }
    /**
     * Set score_min : 성취 최소 점수(0~100)
     * cmi.core.score.min
     * DB TYPE : VARCHAR2
     */
    public void setScore_min(String score_min){
        this.score_min = score_min;
    }

    /**
     * Get comments_from_lms : LMS 코멘트
     * (학습개요, 학습목표,학습공지,학습제목,학습주안점 등을 LMS를 이용하여 전달)
     * cmi.comments_from_lms
     * DB TYPE : CLOB
     */
    public String getComments_from_lms(){
        return this.comments_from_lms;
    }
    /**
     * Set comments_from_lms : LMS 코멘트
     * (학습개요, 학습목표,학습공지,학습제목,학습주안점 등을 LMS를 이용하여 전달)
     * cmi.comments_from_lms
     * DB TYPE : CLOB
     */
    public void setComments_from_lms(String comments_from_lms){
        this.comments_from_lms = comments_from_lms;
    }

    /**
     * Get itm_isvisible : Item의 제목을 보일것인가의 유무(Y:YES, N:NO)
     * DB TYPE : VARCHAR2
     */
    public String getItm_isvisible(){
        return this.itm_isvisible;
    }
    /**
     * Set itm_isvisible : Item의 제목을 보일것인가의 유무(Y:YES, N:NO)
     * DB TYPE : VARCHAR2
     */
    public void setItm_isvisible(String itm_isvisible){
        this.itm_isvisible = itm_isvisible;
    }

    /**
     * Get itm_title : ITEM 제목
     * DB TYPE : VARCHAR2
     */
    public String getItm_title(){
        return this.itm_title;
    }
    /**
     * Set itm_title : ITEM 제목
     * DB TYPE : VARCHAR2
     */
    public void setItm_title(String itm_title){
        this.itm_title = itm_title;
    }

    /**
     * Get itm_parameterstring : 호출시 넘겨주는 파라미터 값
     * DB TYPE : VARCHAR2
     */
    public String getItm_parameterstring(){
        return this.itm_parameterstring;
    }
    /**
     * Set itm_parameterstring : 호출시 넘겨주는 파라미터 값
     * DB TYPE : VARCHAR2
     */
    public void setItm_parameterstring(String itm_parameterstring){
        this.itm_parameterstring = itm_parameterstring;
    }

    /**
     * Get itm_prerequisites : 선수 ITEM들
     * 이 ITEM이 수행되기 위해서 먼저 선행학습 되어야 할 ITEM들
     * DB TYPE : VARCHAR2
     */
    public String getItm_prerequisites(){
        return this.itm_prerequisites;
    }
    /**
     * Set itm_prerequisites : 선수 ITEM들
     * 이 ITEM이 수행되기 위해서 먼저 선행학습 되어야 할 ITEM들
     * DB TYPE : VARCHAR2
     */
    public void setItm_prerequisites(String itm_prerequisites){
        this.itm_prerequisites = itm_prerequisites;
    }

    /**
     * Get itm_type : ITEM 유형("aicc_script")
     * DB TYPE : VARCHAR2
     */
    public String getItm_type(){
        return this.itm_type;
    }
    /**
     * Set itm_type : ITEM 유형("aicc_script")
     * DB TYPE : VARCHAR2
     */
    public void setItm_type(String itm_type){
        this.itm_type = itm_type;
    }

    /**
     * Get itm_maxtimeallowed : 최대 학습 허용 시간(HHHH:MM:SS.SS)
     * cmi.student_data.max_time_allowed
     * DB TYPE : VARCHAR2
     */
    public String getItm_maxtimeallowed(){
        return this.itm_maxtimeallowed;
    }
    /**
     * Set itm_maxtimeallowed : 최대 학습 허용 시간(HHHH:MM:SS.SS)
     * cmi.student_data.max_time_allowed
     * DB TYPE : VARCHAR2
     */
    public void setItm_maxtimeallowed(String itm_maxtimeallowed){
        this.itm_maxtimeallowed = itm_maxtimeallowed;
    }

    /**
     * Get itm_timelimitaction : 학습시간 초과후  발생 이벤트
     * cmi.student_data.time_limit_action
     * exit,message : SCO에서 강제 강퇴시키고 이유를 설명하는 메시지를 제시
     * exit,no message : SCO에서 강퇴시키고 이유를 설명하는 메시지를 제시하지 않음
     * continue,message : 강퇴없이 계속 학습을 진행
     * continue,no message : 계속 학습을 진행하지만 시간이 초과되었음에 대한 메시지 제
     * DB TYPE : VARCHAR2
     */
    public String getItm_timelimitaction(){
        return this.itm_timelimitaction;
    }
    /**
     * Set itm_timelimitaction : 학습시간 초과후  발생 이벤트
     * cmi.student_data.time_limit_action
     * exit,message : SCO에서 강제 강퇴시키고 이유를 설명하는 메시지를 제시
     * exit,no message : SCO에서 강퇴시키고 이유를 설명하는 메시지를 제시하지 않음
     * continue,message : 강퇴없이 계속 학습을 진행
     * continue,no message : 계속 학습을 진행하지만 시간이 초과되었음에 대한 메시지 제
     * DB TYPE : VARCHAR2
     */
    public void setItm_timelimitaction(String itm_timelimitaction){
        this.itm_timelimitaction = itm_timelimitaction;
    }

    /**
     * Get itm_masteryscore : 합격 기준 점수
     * cmi.student_data.mastery_score
     * DB TYPE : VARCHAR2
     */
    public String getItm_masteryscore(){
        return this.itm_masteryscore;
    }
    /**
     * Set itm_masteryscore : 합격 기준 점수
     * cmi.student_data.mastery_score
     * DB TYPE : VARCHAR2
     */
    public void setItm_masteryscore(String itm_masteryscore){
        this.itm_masteryscore = itm_masteryscore;
    }

    /**
     * Get itm_location : 메타데이타의 위치
     * manifest.xml - oraganization item adlcp:location
     * DB TYPE : VARCHAR2
     */
    public String getItm_location(){
        return this.itm_location;
    }
    /**
     * Set itm_location : 메타데이타의 위치
     * manifest.xml - oraganization item adlcp:location
     * DB TYPE : VARCHAR2
     */
    public void setItm_location(String itm_location){
        this.itm_location = itm_location;
    }

    /**
     * Get itm_sequence : 동일 Organization에서의 ITEM 순서
     * DB TYPE : NUMBER
     */
    public long getItm_sequence(){
        return this.itm_sequence;
    }
    /**
     * Set itm_sequence : 동일 Organization에서의 ITEM 순서
     * DB TYPE : NUMBER
     */
    public void setItm_sequence(long itm_sequence){
        this.itm_sequence = itm_sequence;
    }

    /**
     * Get itm_lvl : 계층구조 순서
     * DB TYPE : NUMBER
     */
    public long getItm_lvl(){
        return this.itm_lvl;
    }
    /**
     * Set itm_lvl : 계층구조 순서
     * DB TYPE : NUMBER
     */
    public void setItm_lvl(long itm_lvl){
        this.itm_lvl = itm_lvl;
    }

    /**
     * Get itm_pid : ITEM 상위 식별자
     * DB TYPE : VARCHAR2
     */
    public String getItm_pid(){
        return this.itm_pid;
    }
    /**
     * Set itm_pid : ITEM 상위 식별자
     * DB TYPE : VARCHAR2
     */
    public void setItm_pid(String itm_pid){
        this.itm_pid = itm_pid;
    }

    /**
     * Get place_type : ON:온라인,OFF:오프라인
     * DB TYPE : VARCHAR2
     */
    public String getPlace_type(){
        return this.place_type;
    }
    /**
     * Set place_type : ON:온라인,OFF:오프라인
     * DB TYPE : VARCHAR2
     */
    public void setPlace_type(String place_type){
        this.place_type = place_type;
    }

    /**
     * Get week : 주[단원정보]
     * DB TYPE : NUMBER
     */
    public long getWeek(){
        return this.week;
    }
    /**
     * Set week : 주[단원정보]
     * DB TYPE : NUMBER
     */
    public void setWeek(long week){
        this.week = week;
    }

    /**
     * Get chpnumb : 각주차의 번호(주차마다 새로 번호부여) 1주 1강, 1주 2강, 2주 1강, 2주 2강
     * DB TYPE : NUMBER
     */
    public long getChpnumb(){
        return this.chpnumb;
    }
    /**
     * Set chpnumb : 각주차의 번호(주차마다 새로 번호부여) 1주 1강, 1주 2강, 2주 1강, 2주 2강
     * DB TYPE : NUMBER
     */
    public void setChpnumb(long chpnumb){
        this.chpnumb = chpnumb;
    }

    /**
     * Get htmcode : 컨텐츠코드INX_LST 테이블의 HTMCODE 값
     * DB TYPE : NUMBER
     */
    public int getHtmcode(){
        return this.htmcode;
    }
    /**
     * Set htmcode : 컨텐츠코드INX_LST 테이블의 HTMCODE 값
     * DB TYPE : NUMBER
     */
    public void setHtmcode(int htmcode){
        this.htmcode = htmcode;
    }

    /**
     * Get totpage : 총페이지수[일반컨텐츠인 경우 총페이지수]
     * DB TYPE : NUMBER
     */
    public long getTotpage(){
        return this.totpage;
    }
    /**
     * Set totpage : 총페이지수[일반컨텐츠인 경우 총페이지수]
     * DB TYPE : NUMBER
     */
    public void setTotpage(long totpage){
        this.totpage = totpage;
    }

    /**
     * Get cnt_width : 팝업윈도우 창 넓이
     * DB TYPE : VARCHAR2
     */
    public String getCnt_width(){
        return this.cnt_width;
    }
    /**
     * Set cnt_width : 팝업윈도우 창 넓이
     * DB TYPE : VARCHAR2
     */
    public void setCnt_width(String cnt_width){
        this.cnt_width = cnt_width;
    }

    /**
     * Get cnt_height : 팝업윈도우 창높이
     * DB TYPE : VARCHAR2
     */
    public String getCnt_height(){
        return this.cnt_height;
    }
    /**
     * Set cnt_height : 팝업윈도우 창높이
     * DB TYPE : VARCHAR2
     */
    public void setCnt_height(String cnt_height){
        this.cnt_height = cnt_height;
    }

    /**
     * Get version : 컨텐츠버전 (SCORM1.2, 일반)
     * DB TYPE : VARCHAR2
     */
    public String getVersion(){
        return this.version;
    }
    /**
     * Set version : 컨텐츠버전 (SCORM1.2, 일반)
     * DB TYPE : VARCHAR2
     */
    public void setVersion(String version){
        this.version = version;
    }

    /**
     * Get chp_sdat : YYYYMMDD형태
     * DB TYPE : VARCHAR2
     */
    public String getChp_sdat(){
        return this.chp_sdat;
    }
    /**
     * Set chp_sdat : YYYYMMDD형태
     * DB TYPE : VARCHAR2
     */
    public void setChp_sdat(String chp_sdat){
        this.chp_sdat = chp_sdat;
    }

    /**
     * Get chp_edat : YYYYMMDD형태
     * DB TYPE : VARCHAR2
     */
    public String getChp_edat(){
        return this.chp_edat;
    }
    /**
     * Set chp_edat : YYYYMMDD형태
     * DB TYPE : VARCHAR2
     */
    public void setChp_edat(String chp_edat){
        this.chp_edat = chp_edat;
    }

    /**
     * Get off_place : 오프라인강의장소
     * DB TYPE : NUMBER
     */
    public String getOff_place(){
        return this.off_place;
    }
    /**
     * Set off_place : 오프라인강의장소
     * DB TYPE : NUMBER
     */
    public void setOff_place(String off_place){
        this.off_place = off_place;
    }

    /**
     * Get off_date_info : 오프라인강의일정
     * DB TYPE : VARCHAR2
     */
    public String getOff_date_info(){
        return this.off_date_info;
    }
    /**
     * Set off_date_info : 오프라인강의일정
     * DB TYPE : VARCHAR2
     */
    public void setOff_date_info(String off_date_info){
        this.off_date_info = off_date_info;
    }

    /**
     * Get netg_cid : NETg과정코스ID[NETg서버에서 관리하는 컨텐츠코스ID]
     * DB TYPE : VARCHAR2
     */
    public String getNetg_cid(){
        return this.netg_cid;
    }
    /**
     * Set netg_cid : NETg과정코스ID[NETg서버에서 관리하는 컨텐츠코스ID]
     * DB TYPE : VARCHAR2
     */
    public void setNetg_cid(String netg_cid){
        this.netg_cid = netg_cid;
    }

    /**
     * Get netg_sub_cid : NETg과정코스서브ID[NETg서버에서 관리하는 컨텐츠코스ID - 과정을 쪼갠경우의 ID]
     * DB TYPE : VARCHAR2
     */
    public String getNetg_sub_cid(){
        return this.netg_sub_cid;
    }
    /**
     * Set netg_sub_cid : NETg과정코스서브ID[NETg서버에서 관리하는 컨텐츠코스ID - 과정을 쪼갠경우의 ID]
     * DB TYPE : VARCHAR2
     */
    public void setNetg_sub_cid(String netg_sub_cid){
        this.netg_sub_cid = netg_sub_cid;
    }

    /**
     * Get update_dt : 최종 수정일
     * DB TYPE : DATE
     */
    public Date getUpdate_dt(){
        return this.update_dt;
    }
    /**
     * Set update_dt : 최종 수정일
     * DB TYPE : DATE
     */
    public void setUpdate_dt(Date update_dt){
        this.update_dt = update_dt;
    }

    /**
     * Get update_id : 최종수정ID
     * DB TYPE : VARCHAR2
     */
    public String getUpdate_id(){
        return this.update_id;
    }
    /**
     * Set update_id : 최종수정ID
     * DB TYPE : VARCHAR2
     */
    public void setUpdate_id(String update_id){
        this.update_id = update_id;
    }

}