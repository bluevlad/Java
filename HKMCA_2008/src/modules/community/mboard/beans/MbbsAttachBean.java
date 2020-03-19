/*
 * �ۼ��� : bluevlad
 * Created on 2004. 10. 4.
 *
 */
package modules.community.mboard.beans;

/**
 * �Խ����� ÷������ 
 * @author bluevlad
 * Create by 2004. 10. 4.
 * 
 */
public class MbbsAttachBean {

	/**
	* ���� ID
	*/	
	private String bid = null;

	/**
	* �Խù� ID
	*/	
	private String c_index = null;

	/**
	* ����
	*/	
	private int seq = 0;

	/**
	* �����̹������κ�������(T;����)
	*/	
	private String thumbnail = null;

	/**
	* ����ũ��
	*/	
	private long file_size = 0;

	/**
	* MIME type
	*/	
	private String content_type = null;

	/**
	* ������ ����� ���ϸ�
	*/	
	private String real_filename = null;

	/**
	* ��ϴ�� ���ϸ�
	*/	
	private String ori_filename = null;

	/**
	* ��ǥ�̹�������(c_index�� �Ѱ��� ����)
	*/	
	private String is_major = null;

	private String savepath = null;
	private String  thumbsavepath = null;
	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get bid : ���� ID
	* DB TYPE : VARCHAR2
	*/
	public String getBid(){
		return this.bid;
	}
	/**
	* Set bid : ���� ID
	* DB TYPE : VARCHAR2
	*/
	public void setBid(String bid){
		this.bid = bid;
	}

	/**
	* Get c_index : �Խù� ID
	* DB TYPE : VARCHAR2
	*/
	public String getC_index(){
		return this.c_index;
	}
	/**
	* Set c_index : �Խù� ID
	* DB TYPE : VARCHAR2
	*/
	public void setC_index(String c_index){
		this.c_index = c_index;
	}

	/**
	* Get seq : ����
	* DB TYPE : NUMBER
	*/
	public int getSeq(){
		return this.seq;
	}
	/**
	* Set seq : ����
	* DB TYPE : NUMBER
	*/
	public void setSeq(int seq){
		this.seq = seq;
	}

	/**
	* Get thumbnail : �����̹������κ�������(T;����)
	* DB TYPE : VARCHAR2
	*/
	public String getThumbnail(){
		return this.thumbnail;
	}
	/**
	* Set thumbnail : �����̹������κ�������(T;����)
	* DB TYPE : VARCHAR2
	*/
	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}

	/**
	* Get file_size : ����ũ��
	* DB TYPE : NUMBER
	*/
	public long getFile_size(){
		return this.file_size;
	}
	/**
	* Set file_size : ����ũ��
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
	* Get real_filename : ������ ����� ���ϸ�
	* DB TYPE : VARCHAR2
	*/
	public String getReal_filename(){
		return this.real_filename;
	}
	/**
	* Set real_filename : ������ ����� ���ϸ�
	* DB TYPE : VARCHAR2
	*/
	public void setReal_filename(String real_filename){
		this.real_filename = real_filename;
	}

	/**
	* Get ori_filename : ��ϴ�� ���ϸ�
	* DB TYPE : VARCHAR2
	*/
	public String getOri_filename(){
		return this.ori_filename;
	}
	/**
	* Set ori_filename : ��ϴ�� ���ϸ�
	* DB TYPE : VARCHAR2
	*/
	public void setOri_filename(String ori_filename){
		this.ori_filename = ori_filename;
	}

	/**
	* Get is_major : ��ǥ�̹�������(c_index�� �Ѱ��� ����)
	* DB TYPE : VARCHAR2
	*/
	public String getIs_major(){
		return this.is_major;
	}
	/**
	* Set is_major : ��ǥ�̹�������(c_index�� �Ѱ��� ����)
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
