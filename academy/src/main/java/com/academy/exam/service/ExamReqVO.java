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
public class ExamReqVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	    private int orderNo; // 주문번호
	    private String identyId; // 응시번호
	    private int examId; // 모의고사코드
	    private String userId; // 사용자 ID
	    private String userNm; // 사용자명
	    private String reqType; // 신청접수구분 (O:온라인접수, F:방문접수, D:데스크접수)
	    private String examType; // 응시형태 (O:온라인, F:오프라인)
	    private String reqDesc; // 비고
	    private int payAmount; // 결제 대상 금액
	    private int discountRatio; // 추가 할인 비율
	    private int discountAmount; // 추가 할인 정액
	    private String discountReason; // 할인 사유
	    private String examStatus; // 응시상태(R:응시전,P:응시중,S:임시저장,F:제출완료)
	    private String examStart; // 응시 시작 일시
	    private String examEnd; // 응시 종료 일시
	    private int examRest; // 남은 응시 시간 (초)
	    private String examInfo; // 응시자 IP

	    // Getter & Setter Methods
	    public int getOrderNo() { 
	    	return orderNo; 
	    }
	    public void setOrderNo(int orderNo) { 
	    	this.orderNo = orderNo; 
	    }

	    public String getIdentyId() { 
	    	return identyId; 
	    }
	    public void setIdentyId(String identyId) { 
	    	this.identyId = identyId; 
	    }

	    public int getExamId() { 
	    	return examId; 
	    }
	    public void setExamId(int examId) { 
	    	this.examId = examId; 
	    }

	    public String getUserId() { 
	    	return userId; 
	    }
	    public void setUserId(String userId) { 
	    	this.userId = userId; 
	    }

	    public String getUserNm() { 
	    	return userNm; 
	    }
	    public void setUserNm(String userNm) { 
	    	this.userNm = userNm; 
	    }

	    public String getReqType() { 
	    	return reqType; 
	    }
	    public void setReqType(String reqType) { 
	    	this.reqType = reqType; 
	    }

	    public String getExamType() { 
	    	return examType; 
	    }
	    public void setExamType(String examType) { 
	    	this.examType = examType; 
	    }

	    public String getReqDesc() { 
	    	return reqDesc; 
	    }
	    public void setReqDesc(String reqDesc) { 
	    	this.reqDesc = reqDesc; 
	    }

	    public int getPayAmount() { 
	    	return payAmount; 
	    }
	    public void setPayAmount(int payAmount) { 
	    	this.payAmount = payAmount; 
	    }

	    public int getDiscountRatio() { 
	    	return discountRatio; 
	    }
	    public void setDiscountRatio(int discountRatio) { 
	    	this.discountRatio = discountRatio; 
	    }

	    public int getDiscountAmount() { 
	    	return discountAmount; 
	    }
	    public void setDiscountAmount(int discountAmount) { 
	    	this.discountAmount = discountAmount; 
	    }

	    public String getDiscountReason() { 
	    	return discountReason; 
	    }
	    public void setDiscountReason(String discountReason) { 
	    	this.discountReason = discountReason; 
	    }

	    public String getExamStatus() { 
	    	return examStatus; 
	    }
	    public void setExamStatus(String examStatus) { 
	    	this.examStatus = examStatus; 
	    }

	    public String getExamStart() { 
	    	return examStart; 
	    }
	    public void setExamStart(String examStart) { 
	    	this.examStart = examStart; 
	    }

	    public String getExamEnd() { 
	    	return examEnd; 
	    }
	    public void setExamEnd(String examEnd) { 
	    	this.examEnd = examEnd; 
	    }

	    public int getExamRest() { 
	    	return examRest; 
	    }
	    public void setExamRest(int examRest) { 
	    	this.examRest = examRest; 
	    }

	    public String getExamInfo() { 
	    	return examInfo; 
	    }
	    public void setExamInfo(String examInfo) { 
	    	this.examInfo = examInfo; 
	    }
    
}
