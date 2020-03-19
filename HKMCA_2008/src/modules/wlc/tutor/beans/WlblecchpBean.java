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
     * �����ڵ�
     */
    private String leccode = null;

    /**
     * ITEM�ĺ���
     */
    private String itm_id = null;

    /**
     * Organization �ĺ��� manifest.xml  -
     * organization identifier
     */
    private String org_id = null;

    /**
     * Course �ĺ��� manifest.xml  -
     * manifest identifier
     */
    private String crs_id = null;

    /**
     * Resource �ĺ���
     */
    private String res_id = null;

    /**
     * SCO ���۽� ��������
     * cmi.launch_data
     */
    private String launch_data = null;

    /**
     * ���� �ִ� ����(0~100)
     * cmi.core.score.max
     */
    private String score_max = null;

    /**
     * ���� �ּ� ����(0~100)
     * cmi.core.score.min
     */
    private String score_min = null;

    /**
     * LMS �ڸ�Ʈ (�н�����, �н���ǥ,�н�����,�н�����,�н��־��� ���� LMS�� �̿��Ͽ� ����)
     * cmi.comments_from_lms
     */
    private String comments_from_lms = null;

    /**
     * Item�� ������ ���ϰ��ΰ��� ����(Y:YES, N:NO)
     */
    private String itm_isvisible = null;

    /**
     * ITEM ����
     */
    private String itm_title = null;

    /**
     * ȣ��� �Ѱ��ִ� �Ķ���� ��
     */
    private String itm_parameterstring = null;

    /**
     * ���� ITEM�� �� ITEM�� ����Ǳ� ���ؼ� ���� �����н� �Ǿ�� �� ITEM��
     */
    private String itm_prerequisites = null;

    /**
     * ITEM ����("aicc_script")
     */
    private String itm_type = null;

    /**
     * �ִ� �н� ��� �ð�(HHHH:MM:SS.SS)
     * cmi.student_data.max_time_allowed
     */
    private String itm_maxtimeallowed = null;

    /**
     * �н��ð� �ʰ���  �߻� �̺�Ʈ
     * cmi.student_data.time_limit_action
     * exit,message : SCO���� ���� �����Ű�� ������ �����ϴ� �޽����� ����
     * exit,no message : SCO���� �����Ű�� ������ �����ϴ� �޽����� �������� ����
     * continue,message : ������� ��� �н��� ����
     * continue,no message : ��� �н��� ���������� �ð��� �ʰ��Ǿ����� ���� �޽��� ��
     */
    private String itm_timelimitaction = null;

    /**
     * �հ� ���� ����
     * cmi.student_data.mastery_score
     */
    private String itm_masteryscore = null;

    /**
     * ��Ÿ����Ÿ�� ��ġ
     * manifest.xml - oraganization item adlcp:location
     */
    private String itm_location = null;

    /**
     * ���� Organization������ ITEM ����
     */
    private long itm_sequence = 0;

    /**
     * �������� ����
     */
    private long itm_lvl = 0;

    /**
     * ITEM ���� �ĺ���
     */
    private String itm_pid = null;

    /**
     * ON:�¶���,OFF:��������
     */
    private String place_type = null;

    /**
     * ��[�ܿ�����]
     */
    private long week = 0;

    /**
     * �������� ��ȣ(�������� ���� ��ȣ�ο�) 1�� 1��, 1�� 2��, 2�� 1��, 2�� 2��
     */
    private long chpnumb = 0;

    /**
     * �������ڵ�INX_LST ���̺��� HTMCODE ��
     */
    private int htmcode = 0;

    /**
     * ����������[�Ϲ��������� ��� ����������]
     */
    private long totpage = 0;

    /**
     * �˾������� â ����
     */
    private String cnt_width = null;

    /**
     * �˾������� â����
     */
    private String cnt_height = null;

    /**
     * ���������� (SCORM1.2, �Ϲ�)
     */
    private String version = null;

    /**
     * YYYYMMDD����
     */
    private String chp_sdat = null;

    /**
     * YYYYMMDD����
     */
    private String chp_edat = null;

    /**
     * �������ΰ������
     */
    private String off_place = null;

    /**
     * �������ΰ�������
     */
    private String off_date_info = null;

    /**
     * NETg�����ڽ�ID[NETg�������� �����ϴ� �������ڽ�ID]
     */
    private String netg_cid = null;

    /**
     * NETg�����ڽ�����ID[NETg�������� �����ϴ� �������ڽ�ID - ������ �ɰ������ ID]
     */
    private String netg_sub_cid = null;

    /**
     * ���� ������
     */
    private Date update_dt = null;

    /**
     * ��������ID
     */
    private String update_id = null;


    ////////////////////////////////////////////////////////////////////////////////


    /**
     * Get leccode : �����ڵ�
     * DB TYPE : VARCHAR2
     */
    public String getLeccode(){
        return this.leccode;
    }
    /**
     * Set leccode : �����ڵ�
     * DB TYPE : VARCHAR2
     */
    public void setLeccode(String leccode){
        this.leccode = leccode;
    }

    /**
     * Get itm_id : ITEM�ĺ���
     * DB TYPE : VARCHAR2
     */
    public String getItm_id(){
        return this.itm_id;
    }
    /**
     * Set itm_id : ITEM�ĺ���
     * DB TYPE : VARCHAR2
     */
    public void setItm_id(String itm_id){
        this.itm_id = itm_id;
    }

    /**
     * Get org_id : Organization �ĺ���
     * manifest.xml  - organization identifier
     * DB TYPE : VARCHAR2
     */
    public String getOrg_id(){
        return this.org_id;
    }
    /**
     * Set org_id : Organization �ĺ���
     * manifest.xml  - organization identifier
     * DB TYPE : VARCHAR2
     */
    public void setOrg_id(String org_id){
        this.org_id = org_id;
    }

    /**
     * Get crs_id : Course �ĺ���
     * manifest.xml  - manifest identifier
     * DB TYPE : VARCHAR2
     */
    public String getCrs_id(){
        return this.crs_id;
    }
    /**
     * Set crs_id : Course �ĺ���
     * manifest.xml  - manifest identifier
     * DB TYPE : VARCHAR2
     */
    public void setCrs_id(String crs_id){
        this.crs_id = crs_id;
    }

    /**
     * Get res_id : Resource �ĺ���
     * DB TYPE : VARCHAR2
     */
    public String getRes_id(){
        return this.res_id;
    }
    /**
     * Set res_id : Resource �ĺ���
     * DB TYPE : VARCHAR2
     */
    public void setRes_id(String res_id){
        this.res_id = res_id;
    }

    /**
     * Get launch_data : SCO ���۽� ��������
     * cmi.launch_data
     * DB TYPE : VARCHAR2
     */
    public String getLaunch_data(){
        return this.launch_data;
    }
    /**
     * Set launch_data : SCO ���۽� ��������
     * cmi.launch_data
     * DB TYPE : VARCHAR2
     */
    public void setLaunch_data(String launch_data){
        this.launch_data = launch_data;
    }

    /**
     * Get score_max : ���� �ִ� ����(0~100)
     * cmi.core.score.max
     * DB TYPE : VARCHAR2
     */
    public String getScore_max(){
        return this.score_max;
    }
    /**
     * Set score_max : ���� �ִ� ����(0~100)
     * cmi.core.score.max
     * DB TYPE : VARCHAR2
     */
    public void setScore_max(String score_max){
        this.score_max = score_max;
    }

    /**
     * Get score_min : ���� �ּ� ����(0~100)
     * cmi.core.score.min
     * DB TYPE : VARCHAR2
     */
    public String getScore_min(){
        return this.score_min;
    }
    /**
     * Set score_min : ���� �ּ� ����(0~100)
     * cmi.core.score.min
     * DB TYPE : VARCHAR2
     */
    public void setScore_min(String score_min){
        this.score_min = score_min;
    }

    /**
     * Get comments_from_lms : LMS �ڸ�Ʈ
     * (�н�����, �н���ǥ,�н�����,�н�����,�н��־��� ���� LMS�� �̿��Ͽ� ����)
     * cmi.comments_from_lms
     * DB TYPE : CLOB
     */
    public String getComments_from_lms(){
        return this.comments_from_lms;
    }
    /**
     * Set comments_from_lms : LMS �ڸ�Ʈ
     * (�н�����, �н���ǥ,�н�����,�н�����,�н��־��� ���� LMS�� �̿��Ͽ� ����)
     * cmi.comments_from_lms
     * DB TYPE : CLOB
     */
    public void setComments_from_lms(String comments_from_lms){
        this.comments_from_lms = comments_from_lms;
    }

    /**
     * Get itm_isvisible : Item�� ������ ���ϰ��ΰ��� ����(Y:YES, N:NO)
     * DB TYPE : VARCHAR2
     */
    public String getItm_isvisible(){
        return this.itm_isvisible;
    }
    /**
     * Set itm_isvisible : Item�� ������ ���ϰ��ΰ��� ����(Y:YES, N:NO)
     * DB TYPE : VARCHAR2
     */
    public void setItm_isvisible(String itm_isvisible){
        this.itm_isvisible = itm_isvisible;
    }

    /**
     * Get itm_title : ITEM ����
     * DB TYPE : VARCHAR2
     */
    public String getItm_title(){
        return this.itm_title;
    }
    /**
     * Set itm_title : ITEM ����
     * DB TYPE : VARCHAR2
     */
    public void setItm_title(String itm_title){
        this.itm_title = itm_title;
    }

    /**
     * Get itm_parameterstring : ȣ��� �Ѱ��ִ� �Ķ���� ��
     * DB TYPE : VARCHAR2
     */
    public String getItm_parameterstring(){
        return this.itm_parameterstring;
    }
    /**
     * Set itm_parameterstring : ȣ��� �Ѱ��ִ� �Ķ���� ��
     * DB TYPE : VARCHAR2
     */
    public void setItm_parameterstring(String itm_parameterstring){
        this.itm_parameterstring = itm_parameterstring;
    }

    /**
     * Get itm_prerequisites : ���� ITEM��
     * �� ITEM�� ����Ǳ� ���ؼ� ���� �����н� �Ǿ�� �� ITEM��
     * DB TYPE : VARCHAR2
     */
    public String getItm_prerequisites(){
        return this.itm_prerequisites;
    }
    /**
     * Set itm_prerequisites : ���� ITEM��
     * �� ITEM�� ����Ǳ� ���ؼ� ���� �����н� �Ǿ�� �� ITEM��
     * DB TYPE : VARCHAR2
     */
    public void setItm_prerequisites(String itm_prerequisites){
        this.itm_prerequisites = itm_prerequisites;
    }

    /**
     * Get itm_type : ITEM ����("aicc_script")
     * DB TYPE : VARCHAR2
     */
    public String getItm_type(){
        return this.itm_type;
    }
    /**
     * Set itm_type : ITEM ����("aicc_script")
     * DB TYPE : VARCHAR2
     */
    public void setItm_type(String itm_type){
        this.itm_type = itm_type;
    }

    /**
     * Get itm_maxtimeallowed : �ִ� �н� ��� �ð�(HHHH:MM:SS.SS)
     * cmi.student_data.max_time_allowed
     * DB TYPE : VARCHAR2
     */
    public String getItm_maxtimeallowed(){
        return this.itm_maxtimeallowed;
    }
    /**
     * Set itm_maxtimeallowed : �ִ� �н� ��� �ð�(HHHH:MM:SS.SS)
     * cmi.student_data.max_time_allowed
     * DB TYPE : VARCHAR2
     */
    public void setItm_maxtimeallowed(String itm_maxtimeallowed){
        this.itm_maxtimeallowed = itm_maxtimeallowed;
    }

    /**
     * Get itm_timelimitaction : �н��ð� �ʰ���  �߻� �̺�Ʈ
     * cmi.student_data.time_limit_action
     * exit,message : SCO���� ���� �����Ű�� ������ �����ϴ� �޽����� ����
     * exit,no message : SCO���� �����Ű�� ������ �����ϴ� �޽����� �������� ����
     * continue,message : ������� ��� �н��� ����
     * continue,no message : ��� �н��� ���������� �ð��� �ʰ��Ǿ����� ���� �޽��� ��
     * DB TYPE : VARCHAR2
     */
    public String getItm_timelimitaction(){
        return this.itm_timelimitaction;
    }
    /**
     * Set itm_timelimitaction : �н��ð� �ʰ���  �߻� �̺�Ʈ
     * cmi.student_data.time_limit_action
     * exit,message : SCO���� ���� �����Ű�� ������ �����ϴ� �޽����� ����
     * exit,no message : SCO���� �����Ű�� ������ �����ϴ� �޽����� �������� ����
     * continue,message : ������� ��� �н��� ����
     * continue,no message : ��� �н��� ���������� �ð��� �ʰ��Ǿ����� ���� �޽��� ��
     * DB TYPE : VARCHAR2
     */
    public void setItm_timelimitaction(String itm_timelimitaction){
        this.itm_timelimitaction = itm_timelimitaction;
    }

    /**
     * Get itm_masteryscore : �հ� ���� ����
     * cmi.student_data.mastery_score
     * DB TYPE : VARCHAR2
     */
    public String getItm_masteryscore(){
        return this.itm_masteryscore;
    }
    /**
     * Set itm_masteryscore : �հ� ���� ����
     * cmi.student_data.mastery_score
     * DB TYPE : VARCHAR2
     */
    public void setItm_masteryscore(String itm_masteryscore){
        this.itm_masteryscore = itm_masteryscore;
    }

    /**
     * Get itm_location : ��Ÿ����Ÿ�� ��ġ
     * manifest.xml - oraganization item adlcp:location
     * DB TYPE : VARCHAR2
     */
    public String getItm_location(){
        return this.itm_location;
    }
    /**
     * Set itm_location : ��Ÿ����Ÿ�� ��ġ
     * manifest.xml - oraganization item adlcp:location
     * DB TYPE : VARCHAR2
     */
    public void setItm_location(String itm_location){
        this.itm_location = itm_location;
    }

    /**
     * Get itm_sequence : ���� Organization������ ITEM ����
     * DB TYPE : NUMBER
     */
    public long getItm_sequence(){
        return this.itm_sequence;
    }
    /**
     * Set itm_sequence : ���� Organization������ ITEM ����
     * DB TYPE : NUMBER
     */
    public void setItm_sequence(long itm_sequence){
        this.itm_sequence = itm_sequence;
    }

    /**
     * Get itm_lvl : �������� ����
     * DB TYPE : NUMBER
     */
    public long getItm_lvl(){
        return this.itm_lvl;
    }
    /**
     * Set itm_lvl : �������� ����
     * DB TYPE : NUMBER
     */
    public void setItm_lvl(long itm_lvl){
        this.itm_lvl = itm_lvl;
    }

    /**
     * Get itm_pid : ITEM ���� �ĺ���
     * DB TYPE : VARCHAR2
     */
    public String getItm_pid(){
        return this.itm_pid;
    }
    /**
     * Set itm_pid : ITEM ���� �ĺ���
     * DB TYPE : VARCHAR2
     */
    public void setItm_pid(String itm_pid){
        this.itm_pid = itm_pid;
    }

    /**
     * Get place_type : ON:�¶���,OFF:��������
     * DB TYPE : VARCHAR2
     */
    public String getPlace_type(){
        return this.place_type;
    }
    /**
     * Set place_type : ON:�¶���,OFF:��������
     * DB TYPE : VARCHAR2
     */
    public void setPlace_type(String place_type){
        this.place_type = place_type;
    }

    /**
     * Get week : ��[�ܿ�����]
     * DB TYPE : NUMBER
     */
    public long getWeek(){
        return this.week;
    }
    /**
     * Set week : ��[�ܿ�����]
     * DB TYPE : NUMBER
     */
    public void setWeek(long week){
        this.week = week;
    }

    /**
     * Get chpnumb : �������� ��ȣ(�������� ���� ��ȣ�ο�) 1�� 1��, 1�� 2��, 2�� 1��, 2�� 2��
     * DB TYPE : NUMBER
     */
    public long getChpnumb(){
        return this.chpnumb;
    }
    /**
     * Set chpnumb : �������� ��ȣ(�������� ���� ��ȣ�ο�) 1�� 1��, 1�� 2��, 2�� 1��, 2�� 2��
     * DB TYPE : NUMBER
     */
    public void setChpnumb(long chpnumb){
        this.chpnumb = chpnumb;
    }

    /**
     * Get htmcode : �������ڵ�INX_LST ���̺��� HTMCODE ��
     * DB TYPE : NUMBER
     */
    public int getHtmcode(){
        return this.htmcode;
    }
    /**
     * Set htmcode : �������ڵ�INX_LST ���̺��� HTMCODE ��
     * DB TYPE : NUMBER
     */
    public void setHtmcode(int htmcode){
        this.htmcode = htmcode;
    }

    /**
     * Get totpage : ����������[�Ϲ��������� ��� ����������]
     * DB TYPE : NUMBER
     */
    public long getTotpage(){
        return this.totpage;
    }
    /**
     * Set totpage : ����������[�Ϲ��������� ��� ����������]
     * DB TYPE : NUMBER
     */
    public void setTotpage(long totpage){
        this.totpage = totpage;
    }

    /**
     * Get cnt_width : �˾������� â ����
     * DB TYPE : VARCHAR2
     */
    public String getCnt_width(){
        return this.cnt_width;
    }
    /**
     * Set cnt_width : �˾������� â ����
     * DB TYPE : VARCHAR2
     */
    public void setCnt_width(String cnt_width){
        this.cnt_width = cnt_width;
    }

    /**
     * Get cnt_height : �˾������� â����
     * DB TYPE : VARCHAR2
     */
    public String getCnt_height(){
        return this.cnt_height;
    }
    /**
     * Set cnt_height : �˾������� â����
     * DB TYPE : VARCHAR2
     */
    public void setCnt_height(String cnt_height){
        this.cnt_height = cnt_height;
    }

    /**
     * Get version : ���������� (SCORM1.2, �Ϲ�)
     * DB TYPE : VARCHAR2
     */
    public String getVersion(){
        return this.version;
    }
    /**
     * Set version : ���������� (SCORM1.2, �Ϲ�)
     * DB TYPE : VARCHAR2
     */
    public void setVersion(String version){
        this.version = version;
    }

    /**
     * Get chp_sdat : YYYYMMDD����
     * DB TYPE : VARCHAR2
     */
    public String getChp_sdat(){
        return this.chp_sdat;
    }
    /**
     * Set chp_sdat : YYYYMMDD����
     * DB TYPE : VARCHAR2
     */
    public void setChp_sdat(String chp_sdat){
        this.chp_sdat = chp_sdat;
    }

    /**
     * Get chp_edat : YYYYMMDD����
     * DB TYPE : VARCHAR2
     */
    public String getChp_edat(){
        return this.chp_edat;
    }
    /**
     * Set chp_edat : YYYYMMDD����
     * DB TYPE : VARCHAR2
     */
    public void setChp_edat(String chp_edat){
        this.chp_edat = chp_edat;
    }

    /**
     * Get off_place : �������ΰ������
     * DB TYPE : NUMBER
     */
    public String getOff_place(){
        return this.off_place;
    }
    /**
     * Set off_place : �������ΰ������
     * DB TYPE : NUMBER
     */
    public void setOff_place(String off_place){
        this.off_place = off_place;
    }

    /**
     * Get off_date_info : �������ΰ�������
     * DB TYPE : VARCHAR2
     */
    public String getOff_date_info(){
        return this.off_date_info;
    }
    /**
     * Set off_date_info : �������ΰ�������
     * DB TYPE : VARCHAR2
     */
    public void setOff_date_info(String off_date_info){
        this.off_date_info = off_date_info;
    }

    /**
     * Get netg_cid : NETg�����ڽ�ID[NETg�������� �����ϴ� �������ڽ�ID]
     * DB TYPE : VARCHAR2
     */
    public String getNetg_cid(){
        return this.netg_cid;
    }
    /**
     * Set netg_cid : NETg�����ڽ�ID[NETg�������� �����ϴ� �������ڽ�ID]
     * DB TYPE : VARCHAR2
     */
    public void setNetg_cid(String netg_cid){
        this.netg_cid = netg_cid;
    }

    /**
     * Get netg_sub_cid : NETg�����ڽ�����ID[NETg�������� �����ϴ� �������ڽ�ID - ������ �ɰ������ ID]
     * DB TYPE : VARCHAR2
     */
    public String getNetg_sub_cid(){
        return this.netg_sub_cid;
    }
    /**
     * Set netg_sub_cid : NETg�����ڽ�����ID[NETg�������� �����ϴ� �������ڽ�ID - ������ �ɰ������ ID]
     * DB TYPE : VARCHAR2
     */
    public void setNetg_sub_cid(String netg_sub_cid){
        this.netg_sub_cid = netg_sub_cid;
    }

    /**
     * Get update_dt : ���� ������
     * DB TYPE : DATE
     */
    public Date getUpdate_dt(){
        return this.update_dt;
    }
    /**
     * Set update_dt : ���� ������
     * DB TYPE : DATE
     */
    public void setUpdate_dt(Date update_dt){
        this.update_dt = update_dt;
    }

    /**
     * Get update_id : ��������ID
     * DB TYPE : VARCHAR2
     */
    public String getUpdate_id(){
        return this.update_id;
    }
    /**
     * Set update_id : ��������ID
     * DB TYPE : VARCHAR2
     */
    public void setUpdate_id(String update_id){
        this.update_id = update_id;
    }

}