package com.academy.exam.service;

import java.io.Serializable;

import com.academy.common.CommonVO;

/**
*
* 시험요청 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2025.02.01			rainend			 시험요청 정보 등록
* </pre>
*/
public class ExamVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

    private int examId;
    private String examNm;
    private int examYear;
    private int examRound;
    private String examOpen;
    private String examEnd;
    private int examPeriod;
    private int examTime;
    private String isUse;
    private String useFlag;
    private int setId;
    private String userId;
    private String identyId;
    private String answer;
    private String correctYn;
	/** 문제번호 */
	private int queId;
    
    // Getter and Setter Methods
    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getExamNm() {
        return examNm;
    }

    public void setExamNm(String examNm) {
        this.examNm = examNm;
    }

    public int getExamYear() {
        return examYear;
    }

    public void setExamYear(int examYear) {
        this.examYear = examYear;
    }

    public int getExamRound() {
        return examRound;
    }

    public void setExamRound(int examRound) {
        this.examRound = examRound;
    }

    public String getExamOpen() {
        return examOpen;
    }

    public void setExamOpen(String examOpen) {
        this.examOpen = examOpen;
    }

    public String getExamEnd() {
        return examEnd;
    }

    public void setExamEnd(String examEnd) {
        this.examEnd = examEnd;
    }

    public int getExamPeriod() {
        return examPeriod;
    }

    public void setExamPeriod(int examPeriod) {
        this.examPeriod = examPeriod;
    }

    public int getExamTime() {
        return examTime;
    }

    public void setExamTime(int examTime) {
        this.examTime = examTime;
    }

    public String isUse() {
        return isUse;
    }

    public void setUse(String use) {
        isUse = use;
    }

    public String getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag;
    }

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdentyId() {
        return identyId;
    }

    public void setIdentyId(String identyId) {
        this.identyId = identyId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCorrectYn() {
        return correctYn;
    }

    public void setCorrectYn(String correctYn) {
        this.correctYn = correctYn;
    }
	
	/**
	 * queId attribute 를 리턴한다.
	 * @return int
	 */
	public int getQueId() {
		return queId;
	}
	/**
	 * queId attribute 값을 설정한다.
	 * @param queId int
	 */
	public void setQueId(int queId) {
		this.queId = queId;
	}
    
}
