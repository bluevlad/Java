package egovframework.com.academy.room.service;

import java.io.Serializable;

/**
*
* 독서실정보 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2020.05.21			rainend			독서실 정보 등록
* </pre>
*/

public class RoomVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	/** 검색조건 */
    private String searchCondition = "";
    /** 검색Keyword */
    private String searchKeyword = "";
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

	/** 독서실코드 */
	private int roomCd;
	/** 독서실이름 */
	private String roomNm;
	/** 독서실갯수 */
	private int roomCount;
    /** 독서실대여금 */
	private int roomPrice;
	/** 사용갯수 */
    private int useNum;
	/** 예약가능갯수 */
    private int reqNum;
	/** 독서실 시작번호 */
    private int startNum;
    /** 독서실 종료번호 */
    private int endNum;
    /** 등록일시 */
    private String regDt;
    /** 등록자 */
    private String regId;
    /** 수정일시 */
    private String updDt;
    /** 수정자 */
    private String updId;
    /** 사용자ID */
    private String userId;
    /** 사용자명 */
    private String userNm;
    /** 독서실상태 */
    private String roomFlag;
    /** 독서실대여번호 */
    private int rentSeq;
    /** 독서실 자리정보 메모 */
    private String roomMemo;
    /** 독서실번호 */
    private int roomNum;
    /** 독서실대여시메모 */
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
    /** 결제구분 */
    private String payType;
    /** 주문번호 */
    private String ordId;
    /** 사용여부 */
    private String isUse;
    
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
	 * searchUseYn attribute 를 리턴한다.
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
	 * roomCd attribute 를 리턴한다.
	 * @return int
	 */
	public int getRoomCd() {
		return roomCd;
	}
	/**
	 * roomCd attribute 값을 설정한다.
	 * @param roomCd String
	 */
	public void setRoomCd(int roomCd) {
		this.roomCd = roomCd;
	}
	
	/**
	 * roomNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getRoomNm() {
		return roomNm;
	}
	/**
	 * roomNm attribute 값을 설정한다.
	 * @param roomNm String
	 */
	public void setRoomNm(String roomNm) {
		this.roomNm = roomNm;
	}

	/**
	 * roomCount attribute 를 리턴한다.
	 * @return int
	 */
	public int getRoomCount() {
		return roomCount;
	}
	/**
	 * roomCount attribute 값을 설정한다.
	 * @param roomCount int
	 */
	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}
	/**
	 * roomPrice attribute 를 리턴한다.
	 * @return int
	 */
	public int getRoomPrice() {
		return roomPrice;
	}
	/**
	 * roomPrice attribute 값을 설정한다.
	 * @param roomPrice int
	 */
	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}
	
	/**
	 * useNum attribute 를 리턴한다.
	 * @return String
	 */
	public int getUseNum() {
		return useNum;
	}
	/**
	 * useNum attribute 값을 설정한다.
	 * @param useNum int
	 */
	public void setUseNum(int useNum) {
		this.useNum = useNum;
	}

	/**
	 * reqNum attribute 를 리턴한다.
	 * @return String
	 */
	public int getReqNum() {
		return reqNum;
	}
	/**
	 * reqNum attribute 값을 설정한다.
	 * @param reqNum int
	 */
	public void setReqNum(int reqNum) {
		this.reqNum = reqNum;
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
	 * @param endNum String
	 */
	public void setEndNum(int endNum) {
		this.endNum = endNum;
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
	 * updDt attribute 를 리턴한다.
	 * @return String
	 */
	public String getUpdDt() {
		return updDt;
	}
	/**
	 * updDt attribute 값을 설정한다.
	 * @param updDt String
	 */
	public void setUpdDt(String updDt) {
		this.regDt = updDt;
	}

	/**
	 * updId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUpdId() {
		return updId;
	}
	/**
	 * updId attribute 값을 설정한다.
	 * @param updId String
	 */
	public void setUpdId(String updId) {
		this.updId = updId;
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
	 * roomMemo attribute 를 리턴한다.
	 * @return String
	 */
	public String getRoomMemo() {
		return roomMemo;
	}
	/**
	 * roomMemo attribute 값을 설정한다.
	 * @param roomMemo String
	 */
	public void setRoomMemo(String roomMemo) {
		this.roomMemo = roomMemo;
	}
	
	/**
	 * roomFlag attribute 를 리턴한다.
	 * @return String
	 */
	public String getroomFlag() {
		return roomFlag;
	}
	/**
	 * roomFlag attribute 값을 설정한다.
	 * @param roomFlag String
	 */
	public void setRoomFlag(String roomFlag) {
		this.roomFlag = roomFlag;
	}
	
	/**
	 * rentSeq attribute 를 리턴한다.
	 * @return String
	 */
	public int getRentSeq() {
		return rentSeq;
	}
	/**
	 * rentSeq attribute 값을 설정한다.
	 * @param rentSeq String
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
	 * roomNum attribute 를 리턴한다.
	 * @return int
	 */
	public int getRoomNum() {
		return roomNum;
	}
	/**
	 * roomNum attribute 값을 설정한다.
	 * @param roomNum String
	 */
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
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
	 * payType attribute 를 리턴한다.
	 * @return String
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * payGubun attribute 값을 설정한다.
	 * @param payType String
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	/**
	 * ordId attribute 를 리턴한다.
	 * @return String
	 */
	public String getOrdId() {
		return ordId;
	}
	/**
	 * ordId attribute 값을 설정한다.
	 * @param ordId String
	 */
	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	
	/**
	 * isUse attribute 를 리턴한다.
	 * @return String
	 */
	public String getIsUse() {
		return isUse;
	}
	/**
	 * isUse attribute 값을 설정한다.
	 * @param isUse String
	 */
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

}
