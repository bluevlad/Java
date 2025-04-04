package com.academy.locker.service;

import java.io.Serializable;

import com.academy.common.CommonVO;

/**
*
* 주문정보 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2023.10.00			rainend			주문정보 등록
* </pre>
*/

public class OrdersVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	/** 회원 id */
	private String userId;
	/** 성명 */
	private String userNm;
	/** 이메일 */
	private String email;
    /** 주문번호 */
    private String orderNo;
    /** 주문번호 */
    private String itemNo;
    /** 주문상태 */
    private String statusCd;
    /** 취소일시 */
    private String cancelDt;
    /** 취소여부 */
    private String isCancel;
    /** 주문방법 */
    private String orderType;
    /** 주문상품구분 */
    private String prdType;
	/** 상품주문개수 */
	private int orderCnt;
	/** 상품금액 */
	private int price;
	/** 추가금액(배송료) */
	private int addPrice;
	/** 결제금액 */
	private int payTotal;
	/** 할인금액 */
	private int priceDiscount;
	/** 사용포인트 */
	private int usePoint;
	/** 카드결제금액 */
	private int priceCard;
	/** 현금결제금액 */
	private int priceCash;
    /** 할인사유 */
    private String priceDiscountReason;
    /** 결제구분 */
    private String payCd;
    /** 주문상품정보 */
    private String memo;
	/** 결제승인 id */
	private String confirmId;
	/** 환불금액 */
	private int refundPrice;
    /** 환불일시 */
    private String refundDt;
	/** 환불은행명 */
	private String accBankName;
	/** 환불은행계좌 */
	private String accBankNum;
	/** 환불카드금액 */
	private int refundCard;
	/** 환불현금금액 */
	private int refundCash;
    /** 환불내용 */
    private String refundMemo;
	
	/**
	 * userId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 *  attribute 값을 설정한다.
	 * @param userId String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * userNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * userNm attribute 값을 설정한다.
	 * @param userNm String
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * email attribute 를 리턴한다.
	 * @return String
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * email attribute 값을 설정한다.
	 * @param email String
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @param orderNo String
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
	 * itemno attribute 값을 설정한다.
	 * @param itemNo String
	 */
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	/**
	 * statusCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getStatusCd() {
		return statusCd;
	}
	/**
	 * statusCd attribute 값을 설정한다.
	 * @param statusCd String
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	/**
	 * cancelDt attribute 를 리턴한다.
	 * @return String
	 */
	public String getCancelDt() {
		return cancelDt;
	}
	/**
	 * cancelDt attribute 값을 설정한다.
	 * @param cancelDt String
	 */
	public void setCancelDt(String cancelDt) {
		this.cancelDt = cancelDt;
	}
	/**
	 * isCancel attribute 를 리턴한다.
	 * @return String
	 */
	public String getIsCancel() {
		return isCancel;
	}
	/**
	 * isCancel attribute 값을 설정한다.
	 * @param isCancel String
	 */
	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
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
	 * prdType attribute 를 리턴한다.
	 * @return String
	 */
	public String getPrdType() {
		return prdType;
	}
	/**
	 * prdType attribute 값을 설정한다.
	 * @param orderType String
	 */
	public void setPrdType(String prdType) {
		this.prdType = prdType;
	}
	/**
	 * orderCnt attribute 를 리턴한다.
	 * @return int
	 */
	public int getOrderCnt() {
		return orderCnt;
	}
	/**
	 * orderCnt attribute 값을 설정한다.
	 * @param orderCnt int
	 */
	public void setOrderCnt(int orderCnt) {
		this.orderCnt = orderCnt;
	}
	/**
	 * price attribute 를 리턴한다.
	 * @return int
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * price attribute 값을 설정한다.
	 * @param price int
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * addPrice attribute 를 리턴한다.
	 * @return int
	 */
	public int getAddPrice() {
		return addPrice;
	}
	/**
	 * addPrice attribute 값을 설정한다.
	 * @param price int
	 */
	public void setAddPrice(int addPrice) {
		this.addPrice = addPrice;
	}
	/**
	 * payTotal attribute 를 리턴한다.
	 * @return int
	 */
	public int getPayTotal() {
		return payTotal;
	}
	/**
	 * payTotal attribute 값을 설정한다.
	 * @param payTotal int
	 */
	public void setPayTotal(int payTotal) {
		this.payTotal = payTotal;
	}
	/**
	 * priceDiscount attribute 를 리턴한다.
	 * @return int
	 */
	public int getPriceDiscount() {
		return priceDiscount;
	}
	/**
	 * priceDiscount attribute 값을 설정한다.
	 * @param priceDiscount int
	 */
	public void setPriceDiscount(int priceDiscount) {
		this.priceDiscount = priceDiscount;
	}
	/**
	 * usePoint attribute 를 리턴한다.
	 * @return int
	 */
	public int getUsePoint() {
		return usePoint;
	}
	/**
	 * usePoint attribute 값을 설정한다.
	 * @param usePoint int
	 */
	public void setUsePoint(int usePoint) {
		this.usePoint = usePoint;
	}
	/**
	 * priceCard attribute 를 리턴한다.
	 * @return int
	 */
	public int getPriceCard() {
		return priceCard;
	}
	/**
	 * priceCard attribute 값을 설정한다.
	 * @param priceCard int
	 */
	public void setPriceCard(int priceCard) {
		this.priceCard = priceCard;
	}
	/**
	 * priceCash attribute 를 리턴한다.
	 * @return int
	 */
	public int getPriceCash() {
		return priceCash;
	}
	/**
	 * priceCash attribute 값을 설정한다.
	 * @param priceCash int
	 */
	public void setPriceCash(int priceCash) {
		this.priceCash = priceCash;
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
	 * payCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getPayCd() {
		return payCd;
	}
	/**
	 * payCd attribute 값을 설정한다.
	 * @param payGubun String
	 */
	public void setPayCd(String payCd) {
		this.payCd = payCd;
	}
	/**
	 * memo attribute 를 리턴한다.
	 * @return String
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * memo attribute 값을 설정한다.
	 * @param memo String
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * confirmId attribute 를 리턴한다.
	 * @return String
	 */
	public String getConfirmId() {
		return confirmId;
	}
	/**
	 * confirmId attribute 값을 설정한다.
	 * @param confirmId String
	 */
	public void setConfirmId(String confirmId) {
		this.confirmId = confirmId;
	}
	/**
	 * refundPrice attribute 를 리턴한다.
	 * @return int
	 */
	public int getRefundPrice() {
		return refundPrice;
	}
	/**
	 * refundPrice attribute 값을 설정한다.
	 * @param refundPrice int
	 */
	public void setRefundPrice(int refundPrice) {
		this.refundPrice = refundPrice;
	}
	/**
	 * refundDt attribute 를 리턴한다.
	 * @return String
	 */
	public String getRefundDt() {
		return refundDt;
	}
	/**
	 * refundDt attribute 값을 설정한다.
	 * @param refundDt String
	 */
	public void setRefundDt(String refundDt) {
		this.refundDt = refundDt;
	}
	/**
	 * accBankName attribute 를 리턴한다.
	 * @return String
	 */
	public String getAccBankName() {
		return accBankName;
	}
	/**
	 * accBankName attribute 값을 설정한다.
	 * @param accBankName String
	 */
	public void setAccBankName(String accBankName) {
		this.accBankName = accBankName;
	}
	/**
	 * accBankNum attribute 를 리턴한다.
	 * @return String
	 */
	public String getAccBankNum() {
		return accBankNum;
	}
	/**
	 * accBankNum attribute 값을 설정한다.
	 * @param accBankNum String
	 */
	public void setAccBankNum(String accBankNum) {
		this.accBankNum = accBankNum;
	}
	/**
	 * refundCard attribute 를 리턴한다.
	 * @return int
	 */
	public int getRefundCard() {
		return refundCard;
	}
	/**
	 * refundCard attribute 값을 설정한다.
	 * @param refundCard int
	 */
	public void setRefundCard(int refundCard) {
		this.refundCard = refundCard;
	}
	/**
	 * refundCash attribute 를 리턴한다.
	 * @return int
	 */
	public int getRefundCash() {
		return refundCash;
	}
	/**
	 * refundCash attribute 값을 설정한다.
	 * @param refundCash int
	 */
	public void setRefundCash(int refundCash) {
		this.refundCash = refundCash;
	}
	/**
	 * refundMemo attribute 를 리턴한다.
	 * @return String
	 */
	public String getRefundMemo() {
		return refundMemo;
	}
	/**
	 * refundMemo attribute 값을 설정한다.
	 * @param refundMemo String
	 */
	public void setRefundMemo(String refundMemo) {
		this.refundMemo = refundMemo;
	}
	
}
