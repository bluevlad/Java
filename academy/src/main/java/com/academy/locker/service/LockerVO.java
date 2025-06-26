package com.academy.locker.service;

import java.io.Serializable;

import com.academy.common.CommonVO;

/**
*
* 사물함 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2025.02.01			rainend			 사물함 정보 등록
* </pre>
*/
public class LockerVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	/** 사물함코드 */
	private String boxCd;
	/** 사물함명칭 */
	private String boxNm;
	/** 사물함코드 */
	private String usedBoxCd;
	/** 사물함갯수 */
	private int boxCount;
	/** 사물함사용금액 */
	private int boxPrice;
	/** 예치금 */
	private int deposit;
	/** 위치층수 */
	private int rowCount;
	/** 배열갯수 */
	private int rowNum;
	/** 시작번호 */
	private int startNum;
	/** 종료번호 */
	private int endNum;
	/** 사용갯수 */
	private int useCnt;
	/** 미사용갯수 */
	private int notCnt;

	/** 사물함번호 */
	private int boxNum;
	/** 사물함번호 */
	private int usedBoxNum;
	/** 사물함상태 */
	private String boxFlag;
	/** 사물함대여번호 */
	private int rentSeq;
	/** 대여정보 */
	private String rentMemo;
    /** 대여시작일 */
    private String rentStart;
    /** 대여종료일 */
    private String rentEnd;
    /** 예치금반환여부 */
    private String depositYn;
    /** 연장여부 */
    private String extendYn;
    /** 키반납여부 */
    private String keyYn;
    /** 신청구분 */
    private String rentType;
	/** 예치금환불 */
	private int depositRefund;
	/** 대여기간 잔여 여부 */
	private int restYn;

    /** 주문코드 */
    private String orderNo;
    /** 상품코드 */
    private String itemNo;
    /** 할인사유 */
    private String priceDiscountReason;
    /** 주문구분 */
    private String orderType;
    /** 주문년도 */
    private String orderYear;
	/** 회원 id */
	private String userId;
    
	/**
	 * boxCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getBoxCd() {
		return boxCd;
	}
	/**
	 * boxCd attribute 값을 설정한다.
	 * @param boxCd String
	 */
	public void setBoxCd(String boxCd) {
		this.boxCd = boxCd;
	}
	/**
	 * usedBoxCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getUsedBoxCd() {
		return usedBoxCd;
	}
	/**
	 * usedBoxCd attribute 값을 설정한다.
	 * @param usedBoxCd String
	 */
	public void setUsedBoxCd(String usedBoxCd) {
		this.usedBoxCd = usedBoxCd;
	}
	
	/**
	 * boxNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getBoxNm() {
		return boxNm;
	}
	/**
	 * boxNm attribute 값을 설정한다.
	 * @param boxNm String
	 */
	public void setBoxNm(String boxNm) {
		this.boxNm = boxNm;
	}
	
	/**
	 * boxCount attribute 를 리턴한다.
	 * @return int
	 */
	public int getBoxCount() {
		return boxCount;
	}
	/**
	 * boxCount attribute 값을 설정한다.
	 * @param boxCount int
	 */
	public void setBoxCount(int boxCount) {
		this.boxCount = boxCount;
	}
	
	/**
	 * boxPrice attribute 를 리턴한다.
	 * @return int
	 */
	public int getBoxPrice() {
		return boxPrice;
	}
	/**
	 * boxPrice attribute 값을 설정한다.
	 * @param boxPrice int
	 */
	public void setBoxPrice(int boxPrice) {
		this.boxPrice = boxPrice;
	}
	
	/**
	 * deposit attribute 를 리턴한다.
	 * @return int
	 */
	public int getDeposit() {
		return deposit;
	}
	/**
	 * deposit attribute 값을 설정한다.
	 * @param deposit int
	 */
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	
	/**
	 * rowCount attribute 를 리턴한다.
	 * @return int
	 */
	public int getRowCount() {
		return rowCount;
	}
	/**
	 * rowCount attribute 값을 설정한다.
	 * @param rowCount String
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	/**
	 * rowNum attribute 를 리턴한다.
	 * @return int
	 */
	public int getRowNum() {
		return rowNum;
	}
	/**
	 * rowNum attribute 값을 설정한다.
	 * @param rowNum String
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	/**
	 * startNum attribute 를 리턴한다.
	 * @return int
	 */
	public int getStartNum() {
		return startNum;
	}
	/**
	 * startNum attribute 값을 설정한다.
	 * @param startNum String
	 */
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	/**
	 * endNum attribute 를 리턴한다.
	 * @return int
	 */
	public int getEndNum() {
		return endNum;
	}
	/**
	 * endNum attribute 값을 설정한다.
	 * @param endNum int
	 */
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	/**
	 * useCnt attribute 를 리턴한다.
	 * @return int
	 */
	public int getUseCnt() {
		return useCnt;
	}
	/**
	 * useCnt attribute 값을 설정한다.
	 * @param useCnt int
	 */
	public void setUseCnt(int useCnt) {
		this.useCnt = useCnt;
	}
	/**
	 * notCnt attribute 를 리턴한다.
	 * @return int
	 */
	public int getNotCnt() {
		return notCnt;
	}
	/**
	 * notCnt attribute 값을 설정한다.
	 * @param notCnt int
	 */
	public void setNotCnt(int notCnt) {
		this.notCnt = notCnt;
	}
	
	/**
	 * boxNum attribute 를 리턴한다.
	 * @return int
	 */
	public int getBoxNum() {
		return boxNum;
	}
	/**
	 * boxNum attribute 값을 설정한다.
	 * @param boxNum int
	 */
	public void setBoxNum(int boxNum) {
		this.boxNum = boxNum;
	}
	/**
	 * usedBoxNum attribute 를 리턴한다.
	 * @return int
	 */
	public int getUsedBoxNum() {
		return usedBoxNum;
	}
	/**
	 * usedBoxNum attribute 값을 설정한다.
	 * @param usedBoxNum int
	 */
	public void setUsedBoxNum(int usedBoxNum) {
		this.usedBoxNum = usedBoxNum;
	}
	/**
	 * boxFlag attribute 를 리턴한다.
	 * @return String
	 */
	public String getBoxFlag() {
		return boxFlag;
	}
	/**
	 * boxFlag attribute 값을 설정한다.
	 * @param boxFlag String
	 */
	public void setBoxFlag(String boxFlag) {
		this.boxFlag = boxFlag;
	}
	/**
	 * rentSeq attribute 를 리턴한다.
	 * @return int
	 */
	public int getRentSeq() {
		return rentSeq;
	}
	/**
	 * rentSeq attribute 값을 설정한다.
	 * @param rentSeq int
	 */
	public void setRentSeq(int rentSeq) {
		this.rentSeq = rentSeq;
	}
	/**
	 * rentMemo attribute 를 리턴한다.
	 * @return String
	 */
	public String getRentMemo() {
		return rentMemo;
	}
	/**
	 * rentMemo attribute 값을 설정한다.
	 * @param rentMemo String
	 */
	public void setRentMemo(String rentMemo) {
		this.rentMemo = rentMemo;
	}
	/**
	 * rentStart attribute 를 리턴한다.
	 * @return String
	 */
	public String getRentStart() {
		return rentStart;
	}
	/**
	 * rentStart attribute 값을 설정한다.
	 * @param rentStart String
	 */
	public void setRentStart(String rentStart) {
		this.rentStart = rentStart;
	}
	/**
	 * rentEnd attribute 를 리턴한다.
	 * @return String
	 */
	public String getRentEnd() {
		return rentEnd;
	}
	/**
	 * rentEnd attribute 값을 설정한다.
	 * @param rentEnd String
	 */
	public void setRentEnd(String rentEnd) {
		this.rentEnd = rentEnd;
	}
	/**
	 * depositYn attribute 를 리턴한다.
	 * @return String
	 */
	public String getDepositYn() {
		return depositYn;
	}
	/**
	 * depositYn attribute 값을 설정한다.
	 * @param depositYn String
	 */
	public void setDepositYn(String depositYn) {
		this.depositYn = depositYn;
	}
	/**
	 * extendYn attribute 를 리턴한다.
	 * @return String
	 */
	public String getExtendYn() {
		return extendYn;
	}
	/**
	 * extendYn attribute 값을 설정한다.
	 * @param extendYn String
	 */
	public void setExtendYn(String extendYn) {
		this.extendYn = extendYn;
	}
	/**
	 * keyYn attribute 를 리턴한다.
	 * @return String
	 */
	public String getKeyYn() {
		return keyYn;
	}
	/**
	 * keyYn attribute 값을 설정한다.
	 * @param keyYn String
	 */
	public void setKeyYn(String keyYn) {
		this.keyYn = keyYn;
	}
	/**
	 * rentType attribute 를 리턴한다.
	 * @return String
	 */
	public String getRentType() {
		return rentType;
	}
	/**
	 * rentType attribute 값을 설정한다.
	 * @param rentType String
	 */
	public void setRentType(String rentType) {
		this.rentType = rentType;
	}
	/**
	 * depositRefund attribute 를 리턴한다.
	 * @return int
	 */
	public int getDepositRefund() {
		return depositRefund;
	}
	/**
	 * depositRefund attribute 값을 설정한다.
	 * @param depositRefund int
	 */
	public void setDepositRefund(int depositRefund) {
		this.depositRefund = depositRefund;
	}
	/**
	 * restYn attribute 를 리턴한다.
	 * @return int
	 */
	public int getRestYn() {
		return restYn;
	}
	/**
	 * restYn attribute 값을 설정한다.
	 * @param restYn int
	 */
	public void setRestYn(int restYn) {
		this.restYn = restYn;
	}

	/**
	 * orderNo attribute 를 리턴한다.
	 * @return String
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * orderNo attribute 값을 설정한다.
	 * @param orderno String
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * itemNo attribute 를 리턴한다.
	 * @return String
	 */
	public String getItemNo() {
		return itemNo;
	}
	/**
	 * itemNo attribute 값을 설정한다.
	 * @param itemNo String
	 */
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	/**
	 * priceDiscountReason attribute 를 리턴한다.
	 * @return String
	 */
	public String getPriceDiscountReason() {
		return priceDiscountReason;
	}
	/**
	 * priceDiscountReason attribute 값을 설정한다.
	 * @param priceDiscountReason String
	 */
	public void setPriceDiscountReason(String priceDiscountReason) {
		this.priceDiscountReason = priceDiscountReason;
	}
	/**
	 * orderType attribute 를 리턴한다.
	 * @return String
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * orderType attribute 값을 설정한다.
	 * @param orderType String
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * orderYear attribute 를 리턴한다.
	 * @return String
	 */
	public String getOrderYear() {
		return orderYear;
	}
	/**
	 * orderYear attribute 값을 설정한다.
	 * @param orderYear String
	 */
	public void setOrderYear(String orderYear) {
		this.orderYear = orderYear;
	}
	/**
	 * userId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId attribute 값을 설정한다.
	 * @param userId String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
