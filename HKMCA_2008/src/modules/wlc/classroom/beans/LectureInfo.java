package modules.wlc.classroom.beans;

import maf.base.BaseObject;

/**
 * 강의실 정보 
 * @author UBQ
 *
 */
public class LectureInfo extends BaseObject{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
	private String sjt_cd = null;
	private String lec_cd = null;
	private String lec_nm = null;
	private String lec_num = null;
	private String lec_type = null;
	private String exmid = null;
	
	private String finished_score = null;
	private String lec_sdate = null;
	private String lec_edate = null;
	
	private String exm_condition = null;
	
	public LectureInfo() {
	}

//	public LectureInfo(BaseHttpSession sessionBean) {
//		this.leccode = SessionUtil.getLeccode(sessionBean);
//    	this.sjt_cd = SessionUtil.getSjt_cd(sessionBean);
//	}

	public String getLec_cd() {
		return lec_cd;
	}

	public void setLec_cd(String lec_cd) {
		this.lec_cd = lec_cd;
	}

	public String getSjt_cd() {
		return sjt_cd;
	}

	public void setSjt_cd(String sjt_cd) {
		this.sjt_cd = sjt_cd;
	}

	public String getLec_nm() {
		return lec_nm;
	}

	public void setLec_nm(String lec_nm) {
		this.lec_nm = lec_nm;
	}

	public String getLec_num() {
		return lec_num;
	}

	public void setLec_num(String lec_num) {
		this.lec_num = lec_num;
	}

	public String getLec_type() {
    	return lec_type;
    }

	public void setLec_type(String lec_type) {
    	this.lec_type = lec_type;
    }

	public String getFinished_score() {
    	return finished_score;
    }

	public void setFinished_score(String finished_score) {
    	this.finished_score = finished_score;
    }

	/**
	 * 강좌의 시험코드
	 * @return
	 */
	public String getExmid() {
    	return exmid;
    }

	public void setExmid(String exmid) {
    	this.exmid = exmid;
    }

	public String getLec_edate() {
    	return lec_edate;
    }

	public void setLec_edate(String lec_edate) {
    	this.lec_edate = lec_edate;
    }

	public String getLec_sdate() {
    	return lec_sdate;
    }

	public void setLec_sdate(String lec_sdate) {
    	this.lec_sdate = lec_sdate;
    }

	public String getExm_condition() {
    	return exm_condition;
    }

	public void setExm_condition(String exm_condition) {
    	this.exm_condition = exm_condition;
    }

		
}
