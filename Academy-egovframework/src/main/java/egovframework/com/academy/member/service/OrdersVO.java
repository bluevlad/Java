package egovframework.com.academy.member.service;

import java.io.Serializable;

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

public class OrdersVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	/** 검색조건 */
    private String searchCondition = "";
    /** 검색Keyword */
    private String searchKeyword = "";
    /** 검색직렬 */
    private String searchCategory = "";
    /** 검색사용여부 */
    private String searchUseYn = "";
    /** 현재페이지 */
    private int pageIndex = 1;
    /** 페이지갯수 */
    private int pageUnit = 10;
    /** 페이지사이즈 */
    private int pageSize = 10;
    /** firstIndex */
    private int firstIndex = 1;
    /** lastIndex */
    private int lastIndex = 1;
    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    /** 등록일시 */
    private String regDt;
    /** 등록자ID */
    private String regId;
    /** 수정일시 */
    private String updDt;
    /** 수정자ID */
    private String updId;
    /** 사용 여부 */
    private String isUse;

	/** 회원 id */
	private String userId;
	/** 성명 */
	private String userNm;
	/** 이메일 */
	private String email;
    /** 주문번호 */
    private String orderno;
    /** 주문번호 */
    private String itemno;
    /** 주문상태 */
    private String statuscode;
    /** 취소일시 */
    private String cancelDt;
    /** 취소여부 */
    private String isCancel;
    /** 주문방법 */
    private String orderType;
	/** 상품금액 */
	private int price;
	/** 결제금액 */
	private int payTotal;
	/** 할인금액 */
	private int priceDiscount;
	/** 카드결제금액 */
	private int priceCard;
	/** 현금결제금액 */
	private int priceCash;
    /** 할인사유 */
    private String priceDiscountReason;
    /** 결제구분 */
    private String payGubun;
    /** 주문상품정보 */
    private String memo;
	/** 결제승인 id */
	private String confirmid;
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
	 * searchCondition attribute 를 리턴한다.
	 * @return String
	 */
	public String getSearchCondition() {
		return searchCondition;
	}
	/**
	 * searchCondition attribute 값을 설정한다.
	 * @param searchCondition String
	 */
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	/**
	 * searchKeyword attribute 를 리턴한다.
	 * @return String
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}
	/**
	 * searchKeyword attribute 값을 설정한다.
	 * @param searchKeyword String
	 */
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	/**
	 * searchCategory attribute 를 리턴한다.
	 * @return String
	 */
	public String getSearchCategory() {
		return searchCategory;
	}
	/**
	 * searchCategory attribute 값을 설정한다.
	 * @param searchCategory String
	 */
	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	/**
	 * searchUse attribute 를 리턴한다.
	 * @return String
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}
	/**
	 * searchUseYn attribute 값을 설정한다.
	 * @param searchUseYn String
	 */
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}
	/**
	 * pageIndex attribute 를 리턴한다.
	 * @return int
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	/**
	 * pageIndex attribute 값을 설정한다.
	 * @param pageIndex int
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	/**
	 * pageUnit attribute 를 리턴한다.
	 * @return int
	 */
	public int getPageUnit() {
		return pageUnit;
	}
	/**
	 * pageUnit attribute 값을 설정한다.
	 * @param pageUnit int
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	/**
	 * pageSize attribute 를 리턴한다.
	 * @return int
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * pageSize attribute 값을 설정한다.
	 * @param pageSize int
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * firstIndex attribute 를 리턴한다.
	 * @return int
	 */
	public int getFirstIndex() {
		return firstIndex;
	}
	/**
	 * firstIndex attribute 값을 설정한다.
	 * @param firstIndex int
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	/**
	 * lastIndex attribute 를 리턴한다.
	 * @return int
	 */
	public int getLastIndex() {
		return lastIndex;
	}
	/**
	 * lastIndex attribute 값을 설정한다.
	 * @param lastIndex int
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	/**
	 * recordCountPerPage attribute 를 리턴한다.
	 * @return int
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	/**
	 * recordCountPerPage attribute 값을 설정한다.
	 * @param recordCountPerPage int
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	/**
	 * regDt attribute 를 리턴한다.
	 * @return String
	 */
	public String getRegDt() {
		return regDt;
	}
	/**
	 * regDt attribute 값을 설정한다.
	 * @param regDt String
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	/**
	 * regId attribute 를 리턴한다.
	 * @return String
	 */
	public String getRegId() {
		return regId;
	}
	/**
	 * regId attribute 값을 설정한다.
	 * @param regId String
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}
	/**
	 * uptDt attribute 를 리턴한다.
	 * @return String
	 */
	public String getUpdDt() {
		return updDt;
	}
	/**
	 * uptDt attribute 값을 설정한다.
	 * @param uptDt String
	 */
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	/**
	 * uptId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUpdId() {
		return updId;
	}
	/**
	 * uptId attribute 값을 설정한다.
	 * @param uptId String
	 */
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	/**
	 * isUse attribute 를 리턴한다.
	 * @return String
	 */
	public String getIsUse() {
		return isUse;
	}
	/**
	 * isUSe attribute 값을 설정한다.
	 * @param isUse String
	 */
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	
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
	 * orderno attribute 를 리턴한다.
	 * @return String
	 */
	public String getOrderno() {
		return orderno;
	}
	/**
	 * orderno attribute 값을 설정한다.
	 * @param orderno String
	 */
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	/**
	 * itemno attribute 를 리턴한다.
	 * @return String
	 */
	public String getItemno() {
		return itemno;
	}
	/**
	 * itemno attribute 값을 설정한다.
	 * @param itemno String
	 */
	public void setItemno(String itemno) {
		this.itemno = itemno;
	}
	/**
	 * statuscode attribute 를 리턴한다.
	 * @return String
	 */
	public String getStatuscode() {
		return statuscode;
	}
	/**
	 * statuscode attribute 값을 설정한다.
	 * @param statuscode String
	 */
	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
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
	 * payGubun attribute 를 리턴한다.
	 * @return String
	 */
	public String getPayGubun() {
		return payGubun;
	}
	/**
	 * payGubun attribute 값을 설정한다.
	 * @param payGubun String
	 */
	public void setPayGubun(String payGubun) {
		this.payGubun = payGubun;
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
	 * confirmid attribute 를 리턴한다.
	 * @return String
	 */
	public String getConfirmid() {
		return confirmid;
	}
	/**
	 * confirmid attribute 값을 설정한다.
	 * @param confirmid String
	 */
	public void setConfirmid(String confirmid) {
		this.confirmid = confirmid;
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
