package egovframework.com.academy.lecture.service;

import java.io.Serializable;

/**
*
* 교재 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2023.11.00			rainend			강의형태 등록
* </pre>
*/

public class BookVO implements Serializable {

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
    
	/** 교재코드(L + 현재날짜 + 시퀀스5자리, 시퀀스가 20일경우 앞의 3자리는 0으로 채운다) */
	private String bookCd;
	/** 과목(과목코드#강사아이디로 입력)(과목을 두개 이상 추가할 경우,로 구분) */
	private String subjectCd;
    /** 직종코드 */
    private String categoryCd;
    /** 학습형태코드 */
    private String formCode;
    /** 도서명 */
    private String bookNm;
    /** 도서상세설명 */
    private String bookInfo;
    /** 메모 */
    private String bookMemo;
    /** 키워드 */
    private String bookKeyword;
    /** 이벤트 기간 */
    private String issueDate;
    /** 상품상태(A 주문가능, S 품절, O 절판, N 신규) */
    private String coverType;
    /** 목차 */
    private String bookContents;
    /** 도서가격 */
    private int price;
    /** 할인율 */
    private int discount;
    /** 할인가 */
    private int discountPrice;
    /** 포인트 */
    private int point;
    /** 출판사 */
    private String bookPublishers;
    /** 저자 */
    private String bookAuthor;
    /** 보충자료 */
    private String bookSupplementdata;
    /** 프린트자료 */
    private String bookPrintingdate;
    /** 주교재 */
    private String bookMain;
    /** 부교재 */
    private String bookSub;
    /** 수강생교재 */
    private String bookStudentbook;
    /** 첨부파일 */
    private String attachFile;
    /** 도서이미지(L) */
    private String attachImgL;
    /** 도서이미지(M) */
    private String attachImgM;
    /** 도서이미지(S) */
    private String attachImgS;
    /** 도서이미지(D) */
    private String attachDetailInfo;
    /** 도서재고 */
    private int bookStock;
    /** 무료배송(Y/N)  */
    private String freePost;
    /** 도서 발행일 */
    private String bookDate;
    /** 신간반영(반영/미반영) */
    private String newBook;
    /** 메인반영(반영/미반영) */
    private String mainView;
    /** 페이지수 */
    private int bookPage;
    /** 교재판형(크기) */
    private String bookFormat;

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
	 * bookCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookCd() {
		return bookCd;
	}
	/**
	 * bookCd attribute 값을 설정한다.
	 * @param bookCd String
	 */
	public void setBookCd(String bookCd) {
		this.bookCd = bookCd;
	}
	
	/**
	 * subjectCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getSubjectCd() {
		return subjectCd;
	}
	/**
	 * subjectCd attribute 값을 설정한다.
	 * @param subjectCd String
	 */
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}
	/**
	 * categoryCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getCategoryCd() {
		return categoryCd;
	}
	/**
	 * categoryCd attribute 값을 설정한다.
	 * @param categoryCd String
	 */
	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}
	/**
	 * formCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getFormCode() {
		return formCode;
	}
	/**
	 * formCode attribute 값을 설정한다.
	 * @param formCode String
	 */
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}
	/**
	 * bookNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookNm() {
		return bookNm;
	}
	/**
	 * bookNm attribute 값을 설정한다.
	 * @param bookNm String
	 */
	public void setBookNm(String bookNm) {
		this.bookNm = bookNm;
	}
	/**
	 * bookInfo attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookInfo() {
		return bookInfo;
	}
	/**
	 * bookInfo attribute 값을 설정한다.
	 * @param bookInfo String
	 */
	public void setBookInfo(String bookInfo) {
		this.bookInfo = bookInfo;
	}
	
	/**
	 * bookMemo attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookMemo() {
		return bookMemo;
	}
	/**
	 * bookMemo attribute 값을 설정한다.
	 * @param bookMemo String
	 */
	public void setBookMemo(String bookMemo) {
		this.bookMemo = bookMemo;
	}
	/**
	 * bookKeyword attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookKeyword() {
		return bookKeyword;
	}
	/**
	 * bookKeyword attribute 값을 설정한다.
	 * @param bookKeyword String
	 */
	public void setBookKeyword(String bookKeyword) {
		this.bookKeyword = bookKeyword;
	}
	/**
	 * issueDate attribute 를 리턴한다.
	 * @return String
	 */
	public String getIssueDate() {
		return issueDate;
	}
	/**
	 * issueDate attribute 값을 설정한다.
	 * @param issueDate String
	 */
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	/**
	 * coverType attribute 를 리턴한다.
	 * @return String
	 */
	public String getCoverType() {
		return coverType;
	}
	/**
	 * coverType attribute 값을 설정한다.
	 * @param coverType String
	 */
	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}
	/**
	 * bookContents attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookContents() {
		return bookContents;
	}
	/**
	 * bookContents attribute 값을 설정한다.
	 * @param bookContents String
	 */
	public void setBookContents(String bookContents) {
		this.bookContents = bookContents;
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
	 * discount attribute 를 리턴한다.
	 * @return int
	 */
	public int getDiscount() {
		return discount;
	}
	/**
	 * discount attribute 값을 설정한다.
	 * @param discount int
	 */
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	/**
	 * discountPrice attribute 를 리턴한다.
	 * @return int
	 */
	public int getDiscountPrice() {
		return discountPrice;
	}
	/**
	 * discountPrice attribute 값을 설정한다.
	 * @param discountPrice int
	 */
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	/**
	 * point attribute 를 리턴한다.
	 * @return int
	 */
	public int getPoint() {
		return point;
	}
	/**
	 * point attribute 값을 설정한다.
	 * @param point int
	 */
	public void setPoint(int point) {
		this.point = point;
	}
	/**
	 * bookPublishers attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookPublishers() {
		return bookPublishers;
	}
	/**
	 * bookPublishers attribute 값을 설정한다.
	 * @param bookPublishers String
	 */
	public void setBookPublishers(String bookPublishers) {
		this.bookPublishers = bookPublishers;
	}
	/**
	 * bookAuthor attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookAuthor() {
		return bookAuthor;
	}
	/**
	 * bookAuthor attribute 값을 설정한다.
	 * @param bookAuthor String
	 */
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	/**
	 * bookSupplementdata attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookSupplementdata() {
		return bookSupplementdata;
	}
	/**
	 * bookSupplementdata attribute 값을 설정한다.
	 * @param bookSupplementdata String
	 */
	public void setBookSupplementdata(String bookSupplementdata) {
		this.bookSupplementdata = bookSupplementdata;
	}
	/**
	 * bookPrintingdate attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookPrintingdate() {
		return bookPrintingdate;
	}
	/**
	 * bookPrintingdate attribute 값을 설정한다.
	 * @param bookPrintingdate String
	 */
	public void setBookPrintingdate(String bookPrintingdate) {
		this.bookPrintingdate = bookPrintingdate;
	}
	/**
	 * bookMain attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookMaind() {
		return bookMain;
	}
	/**
	 * bookMain attribute 값을 설정한다.
	 * @param bookMain String
	 */
	public void setBookMain(String bookMain) {
		this.bookMain = bookMain;
	}
	/**
	 * bookSub attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookSub() {
		return bookSub;
	}
	/**
	 * bookSub attribute 값을 설정한다.
	 * @param bookSub String
	 */
	public void setBookSub(String bookSub) {
		this.bookSub = bookSub;
	}
	/**
	 * bookStudentbook attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookStudentbook() {
		return bookStudentbook;
	}
	/**
	 * bookStudentbook attribute 값을 설정한다.
	 * @param bookStudentbook String
	 */
	public void setBookStudentbook(String bookStudentbook) {
		this.bookStudentbook = bookStudentbook;
	}
	/**
	 * attachFile attribute 를 리턴한다.
	 * @return String
	 */
	public String getAttachFile() {
		return attachFile;
	}
	/**
	 * attachFile attribute 값을 설정한다.
	 * @param attachFile String
	 */
	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}
	/**
	 * attachImgL attribute 를 리턴한다.
	 * @return String
	 */
	public String getAttachImgL() {
		return attachImgL;
	}
	/**
	 * attachImgL attribute 값을 설정한다.
	 * @param attachImgL String
	 */
	public void setAttachImgL(String attachImgL) {
		this.attachImgL = attachImgL;
	}
	/**
	 * attachImgM attribute 를 리턴한다.
	 * @return String
	 */
	public String getAttachImgM() {
		return attachImgM;
	}
	/**
	 * attachImgM attribute 값을 설정한다.
	 * @param attachImgM String
	 */
	public void setAttachImgM(String attachImgM) {
		this.attachImgM = attachImgM;
	}
	/**
	 * attachImgS attribute 를 리턴한다.
	 * @return String
	 */
	public String getAttachImgS() {
		return attachImgS;
	}
	/**
	 * attachImgS attribute 값을 설정한다.
	 * @param attachImgS String
	 */
	public void setAttachImgS(String attachImgS) {
		this.attachImgS = attachImgS;
	}
	/**
	 * attachDetailInfo attribute 를 리턴한다.
	 * @return String
	 */
	public String getAttachDetailInfo() {
		return attachDetailInfo;
	}
	/**
	 * attachDetailInfo attribute 값을 설정한다.
	 * @param attachDetailInfo String
	 */
	public void setAttachDetailInfo(String attachDetailInfo) {
		this.attachDetailInfo = attachDetailInfo;
	}
	/**
	 * freePost attribute 를 리턴한다.
	 * @return String
	 */
	public String getFreePost() {
		return freePost;
	}
	/**
	 * freePost attribute 값을 설정한다.
	 * @param freePost String
	 */
	public void setFreePost(String freePost) {
		this.freePost = freePost;
	}
	/**
	 * bookStock attribute 를 리턴한다.
	 * @return int
	 */
	public int getBookStock() {
		return bookStock;
	}
	/**
	 * bookStock attribute 값을 설정한다.
	 * @param bookStock int
	 */
	public void setBookStock(int bookStock) {
		this.bookStock = bookStock;
	}
	/**
	 * bookDate attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookDate() {
		return bookDate;
	}
	/**
	 * bookDate attribute 값을 설정한다.
	 * @param bookDate String
	 */
	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}
	/**
	 * newBook attribute 를 리턴한다.
	 * @return String
	 */
	public String getNewBook() {
		return newBook;
	}
	/**
	 * newBook attribute 값을 설정한다.
	 * @param newBook String
	 */
	public void setNewBook(String newBook) {
		this.newBook = newBook;
	}
	/**
	 * mainView attribute 를 리턴한다.
	 * @return String
	 */
	public String getMainView() {
		return mainView;
	}
	/**
	 * mainView attribute 값을 설정한다.
	 * @param mainView String
	 */
	public void setMainView(String mainView) {
		this.mainView = mainView;
	}
	/**
	 * bookPage attribute 를 리턴한다.
	 * @return int
	 */
	public int getBookPage() {
		return bookPage;
	}
	/**
	 * bookPage attribute 값을 설정한다.
	 * @param bookPage int
	 */
	public void setBookPage(int bookPage) {
		this.bookPage = bookPage;
	}
	/**
	 * bookFormat attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookFormat() {
		return bookFormat;
	}
	/**
	 * bookFormat attribute 값을 설정한다.
	 * @param bookFormat String
	 */
	public void setBookFormat(String bookFormat) {
		this.bookFormat = bookFormat;
	}
	
}
