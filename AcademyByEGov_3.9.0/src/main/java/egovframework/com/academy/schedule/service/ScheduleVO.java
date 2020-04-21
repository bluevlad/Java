package egovframework.com.academy.schedule.service;

import java.io.Serializable;
/**
 * 일정관리 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  장동한          최초 생성
 *   2020.04.00  rainend		myProject 적용
 * </pre>
 */
public class ScheduleVO implements Serializable {

	private static final long serialVersionUID = 6643386546296100019L;

	/** 일정ID */
	private String scdId;
	/** 일정구분(회의/교육/세미나/강의 기타) */
	private String scdSe;
	/** 일정부서ID */
	private String scdDeptId;
	/** 일정종류(부서일정/개인일정) */
	private String scdKindCode;
	/** 일정시작일자 */
	private String scdBgnde;
	/** 일정종료일자 */
	private String scdEndde;
	/** 일정명 */
	private String scdNm;
	/** 일정내용 */
	private String scdDesc;
	/** 일정장소 */
	private String scdPlace;
	/** 일정중요도코드 */
	private String scdIpcrCode;
	/** 일정담담자ID */
	private String scdChargerId;
	
	/** 첨부파일ID */
	private String atchFileId;
	/** 반복구분(반복, 연속, 요일반복) */
	private String reptitSeCode;
	/** 최초등록시점 */
	private String frstRegisterPnttm = "";
	/** 최초등록자ID */
	private String frstRegisterId = "";
	/** 최종수정시점 */
	private String lastUpdusrPnttm = "";
	/** 최종수정ID */
	private String lastUpdusrId = "";
	/** 일정시작일자(시간) */
	private String scdBgndeHH = "";
	/** 일정시작일자(분) */
	private String scdBgndeMM = "";
	/** 일정종료일자(시간) */
	private String scdEnddeHH = "";
	/** 일정종료일자(분) */
	private String scdEnddeMM = "";
	/** 일정시작일자(Year/Month/Day) */
	private String scdBgndeYYYYMMDD = "";
	/** 일정종료일자(Year/Month/Day) */
	private String scdEnddeYYYYMMDD = "";
	/** 담당부서 */
	private String scdDeptName = "";
	/** 담당자명 */
	private String scdChargerName = "";

	/**
	 * schdulId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdId() {
		return scdId;
	}
	/**
	 * schdulId attribute 값을 설정한다.
	 * @return schdulId String
	 */
	public void setScdId(String scdId) {
		this.scdId = scdId;
	}

	/**
	 * schdulSe attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdSe() {
		return scdSe;
	}
	/**
	 * schdulSe attribute 값을 설정한다.
	 * @return schdulSe String
	 */
	public void setScdSe(String scdSe) {
		this.scdSe = scdSe;
	}

	/**
	 * schdulDeptId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdDeptId() {
		return scdDeptId;
	}
	/**
	 * schdulDeptId attribute 값을 설정한다.
	 * @return schdulDeptId String
	 */
	public void setScdDeptId(String scdDeptId) {
		this.scdDeptId = scdDeptId;
	}

	/**
	 * schdulKindCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdKindCode() {
		return scdKindCode;
	}
	/**
	 * schdulKindCode attribute 값을 설정한다.
	 * @return schdulKindCode String
	 */
	public void setScdKindCode(String scdKindCode) {
		this.scdKindCode = scdKindCode;
	}

	/**
	 * scdBgnde attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdBgnde() {
		return scdBgnde;
	}
	/**
	 * scdBgnde attribute 값을 설정한다.
	 * @return scdBgnde String
	 */
	public void setScdBgnde(String scdBgnde) {
		this.scdBgnde = scdBgnde;
	}

	/**
	 * schdulEndde attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdEndde() {
		return scdEndde;
	}
	/**
	 * schdulEndde attribute 값을 설정한다.
	 * @return schdulEndde String
	 */
	public void setScdEndde(String scdEndde) {
		this.scdEndde = scdEndde;
	}

	/**
	 * schdulNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdNm() {
		return scdNm;
	}
	/**
	 * schdulNm attribute 값을 설정한다.
	 * @return schdulNm String
	 */
	public void setScdNm(String scdNm) {
		this.scdNm = scdNm;
	}

	/**
	 * scdDesc attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdDesc() {
		return scdDesc;
	}
	/**
	 * schdulCn attribute 값을 설정한다.
	 * @return schdulCn String
	 */
	public void setScdDesc(String scdDesc) {
		this.scdDesc = scdDesc;
	}

	/**
	 * schdulPlace attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdPlace() {
		return scdPlace;
	}
	/**
	 * schdulPlace attribute 값을 설정한다.
	 * @return schdulPlace String
	 */
	public void setScdPlace(String scdPlace) {
		this.scdPlace = scdPlace;
	}

	/**
	 * schdulIpcrCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdIpcrCode() {
		return scdIpcrCode;
	}
	/**
	 * schdulIpcrCode attribute 값을 설정한다.
	 * @return schdulIpcrCode String
	 */
	public void setScdIpcrCode(String scdIpcrCode) {
		this.scdIpcrCode = scdIpcrCode;
	}

	/**
	 * schdulChargerId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdChargerId() {
		return scdChargerId;
	}

	/**
	 * schdulChargerId attribute 값을 설정한다.
	 * @return schdulChargerId String
	 */
	public void setScdChargerId(String scdChargerId) {
		this.scdChargerId = scdChargerId;
	}

	/**
	 * atchFileId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getAtchFileId() {
		return atchFileId;
	}

	/**
	 * atchFileId attribute 값을 설정한다.
	 * @return atchFileId String
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * reptitSeCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getReptitSeCode() {
		return reptitSeCode;
	}

	/**
	 * reptitSeCode attribute 값을 설정한다.
	 * @return reptitSeCode String
	 */
	public void setReptitSeCode(String reptitSeCode) {
		this.reptitSeCode = reptitSeCode;
	}

	/**
	 * frstRegisterPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * frstRegisterPnttm attribute 값을 설정한다.
	 * @return frstRegisterPnttm String
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @return frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * lastUpdusrPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrPnttm attribute 값을 설정한다.
	 * @return lastUpdusrPnttm String
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @return lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * scdBgndeHH attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdBgndeHH() {
		return scdBgndeHH;
	}

	/**
	 * scdBgndeHH attribute 값을 설정한다.
	 * @return scdBgndeHH String
	 */
	public void setScdBgndeHH(String scdBgndeHH) {
		this.scdBgndeHH = scdBgndeHH;
	}

	/**
	 * scdBgndeMM attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdBgndeMM() {
		return scdBgndeMM;
	}

	/**
	 * scdBgndeMM attribute 값을 설정한다.
	 * @return scdBgndeMM String
	 */
	public void setScdBgndeMM(String scdBgndeMM) {
		this.scdBgndeMM = scdBgndeMM;
	}

	/**
	 * scdEnddeHH attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdEnddeHH() {
		return scdEnddeHH;
	}

	/**
	 * scdEnddeHH attribute 값을 설정한다.
	 * @return scdEnddeHH String
	 */
	public void setScdEnddeHH(String scdEnddeHH) {
		this.scdEnddeHH = scdEnddeHH;
	}

	/**
	 * scdEnddeMM attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdEnddeMM() {
		return scdEnddeMM;
	}

	/**
	 * scdEnddeMM attribute 값을 설정한다.
	 * @return scdEnddeMM String
	 */
	public void setScdEnddeMM(String scdEnddeMM) {
		this.scdEnddeMM = scdEnddeMM;
	}

	/**
	 * scdBgndeYYYMMDD attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdBgndeYYYYMMDD() {
		return scdBgndeYYYYMMDD;
	}

	/**
	 * scdBgndeYYYYMMDD attribute 값을 설정한다.
	 * @return scdulBgndeYYYYMMDD String
	 */
	public void setScdBgndeYYYYMMDD(String scdBgndeYYYYMMDD) {
		this.scdBgndeYYYYMMDD = scdBgndeYYYYMMDD;
	}

	/**
	 * scdEnddeYYYYMMDD attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdEnddeYYYYMMDD() {
		return scdEnddeYYYYMMDD;
	}

	/**
	 * scdEnddeYYYYMMDD attribute 값을 설정한다.
	 * @return scdEnddeYYYYMMDD String
	 */
	public void setScdEnddeYYYYMMDD(String scdEnddeYYYYMMDD) {
		this.scdEnddeYYYYMMDD = scdEnddeYYYYMMDD;
	}

	/**
	 * schdulDeptName attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdDeptName() {
		return scdDeptName;
	}

	/**
	 * schdulDeptName attribute 값을 설정한다.
	 * @return schdulDeptName String
	 */
	public void setScdDeptName(String scdDeptName) {
		this.scdDeptName = scdDeptName;
	}

	/**
	 * schdulChargerName attribute 를 리턴한다.
	 * @return the String
	 */
	public String getScdChargerName() {
		return scdChargerName;
	}
	/**
	 * schdulChargerName attribute 값을 설정한다.
	 * @return schdulChargerName String
	 */
	public void setScdChargerName(String scdChargerName) {
		this.scdChargerName = scdChargerName;
	}


}
