package com.batch.mig.vo;

import org.apache.commons.lang.StringUtils;
import java.util.List;
import java.util.UUID;

/**
 *	MI컨텐츠 
 */
public class MIContentsVO extends CommonVO {

	private final int MAX_SUM_LENGTH = 50;	// 최대컨텐츠개요길이

	private String rObjectId;				// id[컬럼: r_object_id]
	private int number;						// No. 
	private String uProjectCd;				// (식별자) 프로젝트코드[컬럼: u_project_cd]
	private String uArchiveClass;			// [필수] Archive Class[컬럼: u_archive_class, 2자리(MI)]
	private String objectName;				// [필수] 컨텐츠명(한글)[컬럼: object_name]
	private String title;					// [필수] 컨텐츠명(영문)[컬럼: title]
	private String uSummary;				// 컨텐츠개요[컬럼: u_summary]
	private String[] uKeywordKo;			// [Repeating] 국문키워드[컬럼: u_keyword_ko]
	private String[] uInPubBland;			// [필수/Repeating] 대상회사[컬럼: u_in_pub_bland]
	private String ownerName;				// [필수] 담당자[컬럼: owner_name]
	private String uOwnerGroup;				// 담당조직[컬럼: u_owner_group]
	private String uMiSubject;				// 컨텐츠관련MI주제[컬럼: u_mi_subject]
	private String[] uArea; 				// [Repeating] 컨텐츠권역정보[컬럼: u_area]
	private String[] uCarType;				// [Repeating] 컨텐츠차종정보[컬럼: u_car_type]
	private String[] uSector;				// [Repeating] 컨텐츠섹터정보[컬럼: u_sector]
	private String[] readAuthCompany;		// [복수개선택-Repeating확인필요] 열람권한대상회사 추후 권한설정 - 확인필요
	private String[] readAuthRange;			// [복수개선택-Repeating확인필요] 열람권한설정 추후 권한설정 - 확인필요
	private String uSecurity;				// 보안등급설정[컬럼: u_security : 2자리]
	private String uImportance;				// 중요도설정[컬럼: u_importance]
	private String rCreationDate;			// 생성일시
	private String rModifyDate;				// 수정일시
	private String[] uPubSource;			// [필수/Repeating] 발행주체[컬럼: u_pub_source]
	private String projectType;				// 컨텐츠(프로젝트) 유형 - 확인필요
	private String uPoint;					// 별점평균점수[컬럼: u_point]
	private Integer uViewCnt;				// 조회수[컬럼: u_view_cnt]
	private String uImagePath;				// thumbnail이미지path
	private String strfileInfo;				// 첨부파일정보
	private String fileName;				// mig test 파일명
	private String u_link;
	private String status;					// 컨텐츠상태(내부사용,DB저장대상아님)
	
	private long contentSize = 0L;
	
	
	
	
	
	public long getContentSize() {
		return contentSize;
	}

	public void setContentSize(long contentSize) {
		this.contentSize = contentSize;
	}

	public String getU_link() {
		return u_link;
	}

	public void setU_link(String u_link) {
		this.u_link = u_link;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// 초기값 설정
	public void setDefaultValues() {
		this.uArchiveClass = "MI";

		// TODO: 임시생성코드
		this.uProjectCd = UUID.randomUUID().toString().substring(0,20).replaceAll("-", "");

		/*// TODO: 임시 - 발행주체
		String[] tmpSource = new String[1];
		tmpSource[0] = "10";
		this.uPubSource = tmpSource;*/
	}

	private boolean isStringArrayEmpty(String[] strArr) {
		return (strArr == null || strArr.length <= 0);
	}

	public String validateContentValues() {
		String message = "";
	
		// 필수여부
		if (StringUtils.isBlank(this.objectName)) return "컨텐츠명(한글)은 필수입니다.";
		if (StringUtils.isBlank(this.title)) return "컨텐츠명(영문)은 필수입니다.";
		if (this.isStringArrayEmpty(this.uInPubBland)) return "대상회사는 필수입니다.";
		if (StringUtils.isBlank(this.ownerName)) return "담당자는 필수입니다.";
		//if (this.isStringArrayEmpty(this.uPubSource)) return "발행주체는 필수입니다.";

		// 최대길이
		if (!StringUtils.isBlank(this.uSummary) && this.uSummary.length() > this.MAX_SUM_LENGTH)
			return "컨텐츠 개요의 최대길이는 " + this.MAX_SUM_LENGTH + "자 입니다.";

		return message;
	}

	public void setImageFullPath() {
		this.uImagePath = this.setThumbnailFullPath(this.uImagePath);
	}

	// set/getter 함수
	public String getuPoint() {
		return uPoint;
	}

	public void setuPoint(String uPoint) {
		this.uPoint = uPoint;
	}

	public Integer getuViewCnt() {
		return uViewCnt;
	}

	public void setuViewCnt(Integer uViewCnt) {
		this.uViewCnt = uViewCnt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getuMiSubject() {
		return uMiSubject;
	}

	public void setuMiSubject(String uMiSubject) {
		this.uMiSubject = uMiSubject;
	}

	public String getuImportance() {
		return uImportance;
	}

	public void setuImportance(String uImportance) {
		this.uImportance = uImportance;
	}

	public String getrObjectId() {
		return rObjectId;
	}

	public void setrObjectId(String rObjectId) {
		this.rObjectId = rObjectId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getuProjectCd() {
		return uProjectCd;
	}

	public void setuProjectCd(String uProjectCd) {
		this.uProjectCd = uProjectCd;
	}

	public String getuArchiveClass() {
		return uArchiveClass;
	}

	public void setuArchiveClass(String uArchiveClass) {
		this.uArchiveClass = uArchiveClass;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getuSummary() {
		return uSummary;
	}

	public void setuSummary(String uSummary) {
		this.uSummary = uSummary;
	}

	public String[] getuKeywordKo() {
		return uKeywordKo;
	}

	public void setuKeywordKo(String[] uKeywordKo) {
		this.uKeywordKo = uKeywordKo;
	}

	public String[] getuInPubBland() {
		return uInPubBland;
	}

	public void setuInPubBland(String[] uInPubBland) {
		this.uInPubBland = uInPubBland;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getuOwnerGroup() {
		return uOwnerGroup;
	}

	public void setuOwnerGroup(String uOwnerGroup) {
		this.uOwnerGroup = uOwnerGroup;
	}

	public String[] getuArea() {
		return uArea;
	}

	public void setuArea(String[] uArea) {
		this.uArea = uArea;
	}

	public String[] getuCarType() {
		return uCarType;
	}

	public void setuCarType(String[] uCarType) {
		this.uCarType = uCarType;
	}

	public String[] getuSector() {
		return uSector;
	}

	public void setuSector(String[] uSector) {
		this.uSector = uSector;
	}

	public String[] getReadAuthCompany() {
		return readAuthCompany;
	}

	public void setReadAuthCompany(String[] readAuthCompany) {
		this.readAuthCompany = readAuthCompany;
	}

	public String[] getReadAuthRange() {
		return readAuthRange;
	}

	public void setReadAuthRange(String[] readAuthRange) {
		this.readAuthRange = readAuthRange;
	}

	public String getuSecurity() {
		return uSecurity;
	}

	public void setuSecurity(String uSecurity) {
		this.uSecurity = uSecurity;
	}

	public String getrCreationDate() {
		return rCreationDate;
	}

	public void setrCreationDate(String rCreationDate) {
		this.rCreationDate = rCreationDate;
	}

	public String getrModifyDate() {
		return rModifyDate;
	}

	public void setrModifyDate(String rModifyDate) {
		this.rModifyDate = rModifyDate;
	}

	public String[] getuPubSource() {
		return uPubSource;
	}

	public void setuPubSource(String[] uPubSource) {
		this.uPubSource = uPubSource;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getStrfileInfo() {
		return strfileInfo;
	}

	public void setStrfileInfo(String strfileInfo) {
		this.strfileInfo = strfileInfo;
	}

	public String getuImagePath() {
		return uImagePath;
	}

	public void setuImagePath(String uImagePath) {
		this.uImagePath = uImagePath;
	}
}
