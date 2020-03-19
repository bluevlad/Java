/*
 * 작성자 : bluevlad
 * Created on 2004. 10. 4.
 *
 */
package modules.community.mboard.beans;

/**
 * 게시판의 첨부파일 
 * @author bluevlad
 * Create by 2004. 10. 4.
 * 
 */
public class MbbsAttachBean {

	/**
	* 보드 ID
	*/	
	private String bid = null;

	/**
	* 게시물 ID
	*/	
	private String c_index = null;

	/**
	* 순번
	*/	
	private int seq = 0;

	/**
	* 손톱이미지여부보유여부(T;보유)
	*/	
	private String thumbnail = null;

	/**
	* 파일크기
	*/	
	private long file_size = 0;

	/**
	* MIME type
	*/	
	private String content_type = null;

	/**
	* 서버에 저장된 파일명
	*/	
	private String real_filename = null;

	/**
	* 등록당시 파일명
	*/	
	private String ori_filename = null;

	/**
	* 대표이미지여부(c_index당 한개만 가능)
	*/	
	private String is_major = null;

	private String savepath = null;
	private String  thumbsavepath = null;
	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get bid : 보드 ID
	* DB TYPE : VARCHAR2
	*/
	public String getBid(){
		return this.bid;
	}
	/**
	* Set bid : 보드 ID
	* DB TYPE : VARCHAR2
	*/
	public void setBid(String bid){
		this.bid = bid;
	}

	/**
	* Get c_index : 게시물 ID
	* DB TYPE : VARCHAR2
	*/
	public String getC_index(){
		return this.c_index;
	}
	/**
	* Set c_index : 게시물 ID
	* DB TYPE : VARCHAR2
	*/
	public void setC_index(String c_index){
		this.c_index = c_index;
	}

	/**
	* Get seq : 순번
	* DB TYPE : NUMBER
	*/
	public int getSeq(){
		return this.seq;
	}
	/**
	* Set seq : 순번
	* DB TYPE : NUMBER
	*/
	public void setSeq(int seq){
		this.seq = seq;
	}

	/**
	* Get thumbnail : 손톱이미지여부보유여부(T;보유)
	* DB TYPE : VARCHAR2
	*/
	public String getThumbnail(){
		return this.thumbnail;
	}
	/**
	* Set thumbnail : 손톱이미지여부보유여부(T;보유)
	* DB TYPE : VARCHAR2
	*/
	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}

	/**
	* Get file_size : 파일크기
	* DB TYPE : NUMBER
	*/
	public long getFile_size(){
		return this.file_size;
	}
	/**
	* Set file_size : 파일크기
	* DB TYPE : NUMBER
	*/
	public void setFile_size(long file_size){
		this.file_size = file_size;
	}

	/**
	* Get content_type : MIME type
	* DB TYPE : VARCHAR2
	*/
	public String getContent_type(){
		return this.content_type;
	}
	/**
	* Set content_type : MIME type
	* DB TYPE : VARCHAR2
	*/
	public void setContent_type(String content_type){
		this.content_type = content_type;
	}

	/**
	* Get real_filename : 서버에 저장된 파일명
	* DB TYPE : VARCHAR2
	*/
	public String getReal_filename(){
		return this.real_filename;
	}
	/**
	* Set real_filename : 서버에 저장된 파일명
	* DB TYPE : VARCHAR2
	*/
	public void setReal_filename(String real_filename){
		this.real_filename = real_filename;
	}

	/**
	* Get ori_filename : 등록당시 파일명
	* DB TYPE : VARCHAR2
	*/
	public String getOri_filename(){
		return this.ori_filename;
	}
	/**
	* Set ori_filename : 등록당시 파일명
	* DB TYPE : VARCHAR2
	*/
	public void setOri_filename(String ori_filename){
		this.ori_filename = ori_filename;
	}

	/**
	* Get is_major : 대표이미지여부(c_index당 한개만 가능)
	* DB TYPE : VARCHAR2
	*/
	public String getIs_major(){
		return this.is_major;
	}
	/**
	* Set is_major : 대표이미지여부(c_index당 한개만 가능)
	* DB TYPE : VARCHAR2
	*/
	public void setIs_major(String is_major){
		this.is_major = is_major;
	}


    public String getSavepath() {
        return savepath;
    }
    public void setSavepath(String savepath) {
        this.savepath = savepath;
    }
    
    public String getThumbsavepath() {
        return thumbsavepath;
    }
    public void setThumbsavepath(String thumbsavepath) {
        this.thumbsavepath = thumbsavepath;
    }
}
