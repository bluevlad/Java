package egovframework.com.edu.video.service;

/**
*
* 시험정보 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2020.04.21			rainend			시험정보 등록
* </pre>
*/

public class VideoVO {

	private static final long serialVersionUID = 638950577710720796L;

	/** 사물함코드 */
	private String boxCd;
	/** 사물함명칭 */
	private String boxNm;
    
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
	
}
