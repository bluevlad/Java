package egovframework.com.uss.olp.qqm.service;

import java.io.Serializable;
/**
 * 설문문항 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *
 * </pre>
 */
public class SurveyBankVO implements Serializable {

	private static final long serialVersionUID = -1010670861596333788L;

	/** 설문문항 ID */
	private int queId = 0;

	/** 설문제목 */
	private String queTitle = "";

	/** 설문대상 */
	private String queOwner = "";

	/** 문항수 */
	private int queCount = 0;

	/** 질문유형코드 */
	private String queType = "";

	/** 사용여부 */
	private String isUse = "";

	/** 최종수정일 */
	private String uptDt = "";

	/** 문항1 */
	private String queViw1 = "";

	/** 문항2 */
	private String queViw2 = "";

	/** 문항3 */
	private String queViw3 = "";

	/** 문항4 */
	private String queViw4 = "";

	/** 문항5 */
	private String queViw5 = "";

	/** 문항6 */
	private String queViw6 = "";

	/** 문항7 */
	private String queViw7 = "";

	/** 문항8 */
	private String queViw8 = "";

	/** 문항9 */
	private String queViw9 = "";

	/** 문항10 */
	private String queViw10 = "";

	/** 힌트1 */
	private String hint1 = "";

	/** 힌트2 */
	private String hint2 = "";

	/** 힌트3 */
	private String hint3 = "";

	/** 힌트4 */
	private String hint4 = "";

	/** 힌트5 */
	private String hint5 = "";

	/** 힌트6 */
	private String hint6 = "";

	/** 힌트7 */
	private String hint7 = "";

	/** 힌트8 */
	private String hint8 = "";

	/** 힌트9 */
	private String hint9 = "";

	/** 힌트10 */
	private String hint10 = "";

	/** 문항설명  */
	private String queDesc = "";

	/**
	 * queId attribute 를 리턴한다.
	 * @return the int
	 */
	public int getQueId() {
		return queId;
	}

	/**
	 * queId attribute 값을 설정한다.
	 * @return queId int
	 */
	public void setQueId(int queId) {
		this.queId = queId;
	}

	/**
	 * queTitle attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueTitle() {
		return queTitle;
	}

	/**
	 * queTitle attribute 값을 설정한다.
	 * @return queTitle String
	 */
	public void setQueTitle(String queTitle) {
		this.queTitle = queTitle;
	}

	/**
	 * queOwner attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueOwner() {
		return queOwner;
	}

	/**
	 * queOwner attribute 값을 설정한다.
	 * @return queOwner String
	 */
	public void setQueOwner(String queOwner) {
		this.queOwner = queOwner;
	}

	/**
	 * queCount attribute 를 리턴한다.
	 * @return the String
	 */
	public int getQueCount() {
		return queCount;
	}

	/**
	 * queCount attribute 값을 설정한다.
	 * @return queCount String
	 */
	public void setQueCount(int queCount) {
		this.queCount = queCount;
	}

	/**
	 * queType attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueType() {
		return queType;
	}

	/**
	 * queType attribute 값을 설정한다.
	 * @return queType String
	 */
	public void setQueType(String queType) {
		this.queType = queType;
	}

	/**
	 * isUse attribute 를 리턴한다.
	 * @return the String
	 */
	public String getIsUse() {
		return isUse;
	}

	/**
	 * isUse attribute 값을 설정한다.
	 * @return isUse String
	 */
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	/**
	 * uptDt attribute 를 리턴한다.
	 * @return the String
	 */
	public String getUptDt() {
		return uptDt;
	}

	/**
	 * uptDt attribute 값을 설정한다.
	 * @return uptDt String
	 */
	public void setUptDt(String uptDt) {
		this.uptDt = uptDt;
	}

	/**
	 * queViw1 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueViw1() {
		return queViw1;
	}

	/**
	 * queViw1 attribute 값을 설정한다.
	 * @return queViw1 String
	 */
	public void setQueViw1(String queViw1) {
		this.queViw1 = queViw1;
	}


	/**
	 * queViw2 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueViw2() {
		return queViw2;
	}

	/**
	 * queViw2 attribute 값을 설정한다.
	 * @return queViw2 String
	 */
	public void setQueViw2(String queViw2) {
		this.queViw2 = queViw2;
	}

	/**
	 * queViw3 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueViw3() {
		return queViw3;
	}

	/**
	 * queViw3 attribute 값을 설정한다.
	 * @return queViw3 String
	 */
	public void setQueViw3(String queViw3) {
		this.queViw3 = queViw3;
	}

	/**
	 * queViw4 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueViw4() {
		return queViw4;
	}

	/**
	 * queViw4 attribute 값을 설정한다.
	 * @return queViw4 String
	 */
	public void setQueViw4(String queViw4) {
		this.queViw4 = queViw4;
	}

	/**
	 * queViw5 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueViw5() {
		return queViw5;
	}

	/**
	 * queViw5 attribute 값을 설정한다.
	 * @return queViw5 String
	 */
	public void setQueViw5(String queViw5) {
		this.queViw5 = queViw5;
	}

	/**
	 * queViw6 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueViw6() {
		return queViw6;
	}

	/**
	 * queViw6 attribute 값을 설정한다.
	 * @return queViw6 String
	 */
	public void setQueViw6(String queViw6) {
		this.queViw6 = queViw6;
	}

	/**
	 * queViw7 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueViw7() {
		return queViw7;
	}

	/**
	 * queViw7 attribute 값을 설정한다.
	 * @return queViw7 String
	 */
	public void setQueViw7(String queViw7) {
		this.queViw7 = queViw7;
	}

	/**
	 * queViw8 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueViw8() {
		return queViw8;
	}

	/**
	 * queViw8 attribute 값을 설정한다.
	 * @return queViw8 String
	 */
	public void setQueViw8(String queViw8) {
		this.queViw8 = queViw8;
	}

	/**
	 * queViw9 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueViw9() {
		return queViw9;
	}

	/**
	 * queViw9 attribute 값을 설정한다.
	 * @return queViw9 String
	 */
	public void setQueViw9(String queViw9) {
		this.queViw9 = queViw9;
	}

	/**
	 * queViw10 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueViw10() {
		return queViw10;
	}

	/**
	 * queViw10 attribute 값을 설정한다.
	 * @return queViw10 String
	 */
	public void setQueViw10(String queViw10) {
		this.queViw10 = queViw10;
	}

	/**
	 * hint1 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getHint1() {
		return hint1;
	}

	/**
	 * hint1 attribute 값을 설정한다.
	 * @return hint1 String
	 */
	public void setHint1(String hint1) {
		this.hint1 = hint1;
	}

	/**
	 * hint2 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getHint2() {
		return hint2;
	}

	/**
	 * hint2 attribute 값을 설정한다.
	 * @return hint2 String
	 */
	public void setHint2(String hint2) {
		this.hint2 = hint2;
	}

	/**
	 * hint3 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getHint3() {
		return hint3;
	}

	/**
	 * hint3 attribute 값을 설정한다.
	 * @return hint3 String
	 */
	public void setHint3(String hint3) {
		this.hint3 = hint3;
	}

	/**
	 * hint4 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getHint4() {
		return hint4;
	}

	/**
	 * hint4 attribute 값을 설정한다.
	 * @return hint4 String
	 */
	public void setHint4(String hint4) {
		this.hint4 = hint4;
	}

	/**
	 * hint5 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getHint5() {
		return hint5;
	}

	/**
	 * hint5 attribute 값을 설정한다.
	 * @return hint5 String
	 */
	public void setHint5(String hint5) {
		this.hint5 = hint5;
	}

	/**
	 * hint6 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getHint6() {
		return hint6;
	}

	/**
	 * hint6 attribute 값을 설정한다.
	 * @return hint6 String
	 */
	public void setHint6(String hint6) {
		this.hint6 = hint6;
	}

	/**
	 * hint7 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getHint7() {
		return hint7;
	}

	/**
	 * hint7 attribute 값을 설정한다.
	 * @return hint7 String
	 */
	public void setHint7(String hint7) {
		this.hint7 = hint7;
	}

	/**
	 * hint8 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getHint8() {
		return hint8;
	}

	/**
	 * hint8 attribute 값을 설정한다.
	 * @return hint8 String
	 */
	public void setHint8(String hint8) {
		this.hint8 = hint8;
	}

	/**
	 * hint9 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getHint9() {
		return hint9;
	}

	/**
	 * hint9 attribute 값을 설정한다.
	 * @return hint9 String
	 */
	public void setHint9(String hint9) {
		this.hint9 = hint9;
	}

	/**
	 * hint10 attribute 를 리턴한다.
	 * @return the String
	 */
	public String getHint10() {
		return hint10;
	}

	/**
	 * hint10 attribute 값을 설정한다.
	 * @return hint10 String
	 */
	public void setHint10(String hint10) {
		this.hint10 = hint10;
	}

	/**
	 * queDesc attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQueDesc() {
		return queDesc;
	}

	/**
	 * queDesc attribute 값을 설정한다.
	 * @return queDesc String
	 */
	public void setQueDesc(String queDesc) {
		this.queDesc = queDesc;
	}

}
