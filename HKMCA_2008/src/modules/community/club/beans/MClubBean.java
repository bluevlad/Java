/*
 * 작성된 날짜: 2005-03-03
 *
 */
package modules.community.club.beans;

/**
 * 
 *
 * 작성자 :신규문
 * 작성된 날짜 : 2005-03-03
 */
public class MClubBean {

	
	private String c_id = null;

		
	private String c_title = null;

		
	private String c_type = null;

		
	private String is_use = null;

	private String bid = null;

	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get c_id : 
	* DB TYPE : VARCHAR2
	*/
	public String getC_id(){
		return this.c_id;
	}
	/**
	* Set c_id : 
	* DB TYPE : VARCHAR2
	*/
	public void setC_id(String c_id){
		this.c_id = c_id;
	}

	/**
	* Get c_title : 
	* DB TYPE : VARCHAR2
	*/
	public String getC_title(){
		return this.c_title;
	}
	/**
	* Set c_title : 
	* DB TYPE : VARCHAR2
	*/
	public void setC_title(String c_title){
		this.c_title = c_title;
	}

	/**
	* Get c_type : 
	* DB TYPE : VARCHAR2
	*/
	public String getC_type(){
		return this.c_type;
	}
	/**
	* Set c_type : 
	* DB TYPE : VARCHAR2
	*/
	public void setC_type(String c_type){
		this.c_type = c_type;
	}

	/**
	* Get is_use : 
	* DB TYPE : CHAR
	*/
	public String getIs_use(){
		return this.is_use;
	}
	/**
	* Set is_use : 
	* DB TYPE : CHAR
	*/
	public void setIs_use(String is_use){
		this.is_use = is_use;
	}

    /**
     * @return bid을 리턴합니다.
     */
    public String getBid() {
        return bid;
    }
    /**
     * @param bid 설정하려는 bid.
     */
    public void setBid(String bid) {
        this.bid = bid;
    }
}
