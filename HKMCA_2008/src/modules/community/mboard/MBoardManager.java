/*
 * Created on 2004. 8. 23.
 *
 */
package modules.community.mboard;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import maf.aam.MafUserManager;
import maf.base.BaseHttpSession;
import maf.logger.Logging;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.web.http.MyHttpServletRequest;
import maf.web.multipart.UploadedFile;
import modules.community.mboard.beans.MbbsAttachBean;
import modules.community.mboard.beans.MbbsCommentBean;
import modules.community.mboard.beans.MbbsDataBean;
import modules.community.mboard.beans.MbbsDataRefListBean;
import modules.community.mboard.beans.MbbsMetaBean;
import modules.community.mboard.dao.MbbsAttachDB;
import modules.community.mboard.dao.MbbsCommentDB;
import modules.community.mboard.dao.MbbsDataDB;
import modules.community.mboard.dao.MbbsDataRefListDB;
import modules.community.mboard.dao.MbbsMetaDB;
import modules.community.mboard.exception.MBoardException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bluevlad
 * 
 */
public class MBoardManager {
    public static final String ATT_FILE_PATH = "/mbbsfile";
    public static final String THUMB_PATH = "thumb";		// 손톱이미지 저장 경로
    private Log logger = LogFactory.getLog(getClass());
    
    private String att_file_path = null;
    private String thumb_path = null;

    private MdbDriver oDB = null;
    private String bid = null;
    private boolean isOk = false;
    private MbbsMetaBean oBoard=null;
    
    private int listDataCount = 0;
    private int v_page = 0;
    private BaseHttpSession sBean = null;
    
    public MbbsMetaBean getBoard() {
        return this.oBoard;
    }
    public int getListDataCount() {
        return listDataCount;
    }
    public void setListDataCount(int listDataCount) {
        this.listDataCount = listDataCount;
    }
    public int getV_page() {
        return v_page;
    }
    public void setV_page(int v_page) {
    	if(v_page < 1) v_page = 1;
        this.v_page = v_page;
    }
    /**
     * 게시판의 상태가 오른지 
     * @return
     */
    public boolean isOk() {
        return isOk;
    }
    
    public String getBid() {
        return this.bid;
    }
    
    public void setBid(String bid) {
        this.bid = bid;
    }
    
    public String getBidPath() {
    	return MBoardManager.getBid2BidPath(this.bid);
    }

    public   MBoardManager( String bid, String file_path, BaseHttpSession pBean) 
    throws SQLException, Exception {
        this.att_file_path = file_path;
        if (bid == null) {
            throw new MBoardException("잘못된 요청입니다.");            
        } else {
            this.oDB =Mdb.getMdbDriver();
	        oBoard = GetBoardInfo(oDB, bid);
            sBean = pBean;
        }
    }
    
    /**
     * constructor
     * @param conn : Database Connection
     * @param bid   : Board ID값 
     * @throws SQLException
     * @throws Exception
     */
    public MBoardManager(  String bid, String file_path) throws SQLException, Exception {
        this( bid,  file_path, null);
    }    

    
    private MbbsMetaBean GetBoardInfo(MdbDriver oDb, String bid) throws Exception  {

        MbbsMetaBean oM = MbbsMetaDB.getInstance().view( oDb, bid);
        
        if(oM == null) {
        	oM = new MbbsMetaBean();
        }
        if ( oM.isOk()) {
            this.isOk = true;
            this.bid = oM.getBid();
        } else {
            this.isOk = false;
        }
        return oM;
    }    
    
   
    /**
     * 게시물 목록 가져 오기
     * @param v_pagedown	
     * @param v_page	: 보여줄 페이지
     * @param v_max_index	: 현 Screen내의 max_index
     * @param v_min_index	: 현 Screen내의 min_index
     * @param v_key			: 검색 Key
     * @param v_srch		: 검색할 단어
     * @return
     * @throws SQLException
     */
	public synchronized List getList(int v_page, String v_key, String v_srch) throws Exception
	{
		return getList(v_page, v_key, v_srch, "T");
	}
	
	public List getList(int v_page, String v_key, String v_srch, String v_status) throws Exception
	{
		return MbbsDataDB.get_instance().getList(this,  v_page, v_key, v_srch, v_status);
	}	
	
	/**
	 * 공지 목록 가져오기
	 * @return
	 * @throws Exception
	 */
	public List getNoticeList( ) throws Exception
	{
		return MbbsDataDB.get_instance().getNoticeList(this);
	}	
	/**
	 * 하나의 게시물을 읽어 온다. (Board는 this에서 넘겨줌)
	 * @param c_index	: 게시물 ID
	 * @return
	 */
	public MbbsDataBean getOne(String c_index) throws Exception
	{
		//Data cb = new Data(conn);
		return MbbsDataDB.get_instance().getOne(this, c_index);
	}
	/**
	 * 하나의 게시물을 내용과 제목 없이 usn, 암호, 상태만 가져 온다.(삭제 확인용)
	 * @param c_index
	 * @return
	 * @throws Exception
	 */
	public MbbsDataBean getOneSimple(String c_index) throws Exception
	{
	    return MbbsDataDB.get_instance().getOneSimple(this, c_index);
	}	
	/**
	 * 게시물에 관련된 앞뒤 게시물을 
	 * @param c_index	: 게시물 ID
	 * @param ref_cnt	: 가져올 앞뒤게시물 수 총 (ref_cnt * 2개)
	 * @return
	 */
	public MbbsDataRefListBean[] getRefList(String c_index, int ref_cnt) throws Exception{
	    return MbbsDataRefListDB.getInstance().getList(this,  c_index, ref_cnt);
	}
	
	public MbbsDataRefListBean[] getRefList(String c_index) throws Exception {
	    return getRefList(c_index, 1);
	}	
	
	/** 
	 * 첨부 파일과 함께 게시물 저장
	 * @param item
	 * @param _req
	 * @return
	 */
	public String addItem( MyHttpServletRequest _req, String c_ref) throws Exception 
	{
	    chkRight(BoardConfig.CMD_ADD, null);

	    String c_index = null;
	    MbbsAttachBean[] attFiles = null;

	    logger.debug("Max : " + oBoard.getMax_attach_size()*1024 + "\n act: " + _req.getTotalAttachSize());
        if(oBoard.getMax_attach_size()*1024 < _req.getTotalAttachSize()) {
            _req.deleteAllAttachFile();
            throw new MBoardException("첨부파일 용량이 한도(" +oBoard.getMax_attach_size()+ "MB)를 초과되었습니다.");  
        }
        
	    try {
	    	oDB.setAutoCommit(false);
	        String c_content = _req.getParameter("c_content");
	        attFiles = Attach_SaveFiles(_req);
	        if (attFiles != null ) {
	            c_content = MBoardLib.ArrangeContents(attFiles, this.getBid(), c_content, "http://" + _req.getHeader("HOST") + _req.getContextPath());
	        }
    		MbbsDataBean oB	= new MbbsDataBean();

    		oB.setBid(bid); 
    		oB.setC_subject(_req.getParameter("c_subject"));
    		oB.setC_content(c_content);
    		oB.setC_ip(_req.getRemoteAddr());
    		oB.setC_category(_req.getParameter("category"));
    		oB.setC_type(_req.getParameter("C_type","H"));
    		oB.setIs_notice(_req.getParameter("is_notice","F"));
    		
    		if(c_ref != null) {	// 답글의 경우
    		    oB.setC_ref(c_ref);
    		}
   		
//    	    if (!"".equals(this.sBean) && this.sBean != null) {
    		    oB.setUsn(this.sBean.getUsn());
    		    oB.setWname(this.sBean.getNm());
    		    oB.setC_passwd(null);
//    	    } else {
//    		    oB.setWname(_req.getP("wname"));
//    		    oB.setC_passwd(_req.getP("c_passwd"));
//    	    }
	        
            c_index = MbbsDataDB.get_instance().addItem(this, oB);
            MbbsAttachDB.AddItems( this, c_index, attFiles);
            oDB.commit();
	    } catch (Exception e) {
	       logger.error(e.getMessage());
	       Logging.trace(e);
	        try{oDB.rollback();}catch(Exception sqle){};
	        _req.deleteAllAttachFile();
	        MBoardLib.DeleteAttchFiles(this, attFiles, this.att_file_path);
	        throw new MBoardException("게시물 저장중 오류가 발생하였습니다.");  
	    }
		return c_index;
	}	
	
	public String addItem( MyHttpServletRequest _req) throws Exception 
	{
	    return addItem(_req, null);
	}
	/** 
	 * 게시물 Update
	 * @param item
	 * @return
	 */
	public void UpdateItem( MyHttpServletRequest _req, String c_index) throws Exception 
	{
	    chkRight(BoardConfig.CMD_UPDATE, c_index);
	    MbbsAttachBean[]  attFiles = null; // MbbsAttachBean[]

        if(oBoard.getMax_attach_size() * 1024 < _req.getTotalAttachSize()) {
            _req.deleteAllAttachFile();
            
            throw new MBoardException("첨부파일 용량이 한도(" +oBoard.getMax_attach_size()+ "MB)를 초과되었습니다."); 
        }
        
	    try {
	    	oDB.setAutoCommit(false);
	        String c_content = _req.getParameter("c_content");
	        // 첨부파일을 저장 한다. 
	        attFiles = Attach_SaveFiles(_req);
	        if (attFiles != null ) {
	            c_content = MBoardLib.ArrangeContents(attFiles, this.bid, c_content, "http://" + _req.getHeader("HOST") + _req.getContextPath());
	        }
    		MbbsDataBean oB	= new MbbsDataBean();
    		oB.setBid(bid); 
    		oB.setC_index(c_index );
    		oB.setC_content(c_content);
    		oB.setC_subject(_req.getParameter("c_subject"));
    		oB.setC_ip(_req.getRemoteAddr());
    		oB.setC_category(_req.getParameter("category"));
    		oB.setC_type(_req.getParameter("C_type","H"));
    		oB.setIs_notice(_req.getParameter("is_notice","F"));
    		if(this.sBean != null) {
    		    oB.setUsn(this.sBean.getUsn());
    		    oB.setWname(this.sBean.getNm());
    		    oB.setC_passwd(null);
    		} else {
        		oB.setWname(_req.getParameter("wname"));
        		oB.setC_passwd(_req.getParameter("passwd"));    		    
    		}
	        MbbsDataDB.get_instance().UpdateItem( this, oB);
	        String[] del_img = _req.getParameterValues("deleteAttachSeq");
	        if(del_img != null ) {
	            List delFiles= MbbsAttachDB.DelItems( this, c_index, del_img);
	            
	    		for(int i=0; i < delFiles.size(); i++ ) {
	    		    MBoardLib.DeleteAttchFile(this, (String) delFiles.get(i), this.att_file_path);
	    		}
	        }
	        MbbsAttachDB.AddItems(this, c_index, attFiles);
	        oDB.commit();
	    } catch (Throwable e) {
	       // logger.error(miraenet.etc.Trace.getStackTrace(e));
	        MBoardLib.DeleteAttchFiles(this, attFiles, this.att_file_path);
	        _req.deleteAllAttachFile();
	        try{oDB.rollback();}catch(Exception sqle){};
	        throw new MBoardException("게시물 수정중 오류가 발생하였습니다.", e);  
	    }
	}	
	
	/**
	 * 하나의 게시물 삭제
	 * 삭제:첨부까지 삭제, 해우소는 해우소로 이동만 
	 * @param item
	 * @param mode : 삭제  = d: 삭제, c:완전삭제, w:해우소로 이동, n:복원 
	 * @return
	 */
	public void DeleteItem(String c_index, String mode) throws Exception
	{
	    chkRight(BoardConfig.CMD_UPDATE, c_index);
	    if("d".equals(mode) || "c".equals(mode)) {

	        MBoardLib.DeleteAttchFiles(this, getAttchList( c_index), this.att_file_path);
	    }	    
	    MbbsDataDB.get_instance().DeleteItem(this, c_index, mode);
	}


	/**
	 * 게시판 삭제
	 * @return
	 */
	public void RemoveBoard() throws Exception {
	    MbbsMetaDB dao = MbbsMetaDB.getInstance();
		dao.delete(oDB, this.bid, this.att_file_path);
    }
    
    /**
     * 첨부파일을 모두 저장 하고(이미지는 Thumbnail 작성)
     * 관련 리스트를 돌려줌.
     * 오류시 관련 파일 삭제.
     * @param _req
     * @return
     */
    private MbbsAttachBean[] Attach_SaveFiles( MyHttpServletRequest _req ) throws Exception
    {
        List attFiles = 	new ArrayList(); 
        
	    try {
		    Map filesMap =  _req.getFileParameterMap();
		    Set set = filesMap.entrySet();
		    Iterator i = set.iterator();

		    while(i.hasNext()){
				Map.Entry me = (Map.Entry) i.next();
				List fileList = (List) me.getValue();
				for(int j = 0; j < fileList.size(); j++ ) {
					UploadedFile uFile = (UploadedFile) fileList.get(j);
					if(uFile.getFileSize() > 0 ) {
						MbbsAttachBean oAttBean;
						// 첨부파일 저장 및 손톱이미지 제작 
						oAttBean = MBoardLib.Attach_SaveFile(uFile, this.getBidPath(), true, 
						        				this.att_file_path,
										        this.oBoard.getThumbwidth(), this.oBoard.getThumbheight());
						// 저장이 제대로 되었으면 
						if (oAttBean != null) {
						    attFiles.add(oAttBean);
						} else {
							break;
						}
					}
				}
		    } // while
		   
	    } catch (Throwable e) {
	        MBoardLib.DeleteAttchFiles(this, (MbbsAttachBean[]) attFiles.toArray(new MbbsAttachBean[0]), this.att_file_path);
	       logger.error(maf.logger.Trace.getStackTrace(e));
	        throw new MBoardException("Attach_SaveFiles() : error in attach file save", e);
	    }
	    return (MbbsAttachBean[]) attFiles.toArray(new MbbsAttachBean[0]);
    }
    
   
    /**
     * 첨부파일 목록 가져 오기
     * @param c_index
     * @return
     * @throws Exception
     */
	public MbbsAttachBean[] getAttchList(String c_index) throws Exception {
	    return MbbsAttachDB.getList(this, c_index);
	}	    
	
	/**
	 * Comment 추가 
	 * @param conn
	 * @param item
	 * @return
	 */
	public void CmtAdd( MbbsCommentBean item) throws Exception {
	    chkRight(BoardConfig.CMD_CMT_ADD, null);
		MbbsCommentDB.addItem(this, item);
	}	
	
	/**
	 * Comment 삭제
	 * @param conn
	 * @param item
	 * @return
	 */
	public void CmtDel(String c_index, String c_id) throws Exception
	{
	    chkRight(BoardConfig.CMD_CMT_DEL, c_index);
        MbbsCommentDB.delItem(this, c_index, c_id);
	}		
	/**
	 * Comment 리스트 가져오기 
	 * @param c_index
	 * @return
	 */
	public MbbsCommentBean[] getCmtList( String c_index) throws Exception {
		return MbbsCommentDB.getList(this, c_index);
	}		
	
	/**
	 * 권한 체크후 권한이 없을 경우 exception을 발생한다.
	 */


	public void chkRight(String Mode, String c_index) throws Exception
	{
	    
	    if(sBean != null && MafUserManager.isSuperAdmin(sBean)) 
	    {
//	      administrator 는 모든 권한을 가짐
	    } else {


	    }
	}
	
    public MdbDriver getODB() {
        return oDB;
    }
    
    public void destructODB() {
        this.oDB.close();
        this.oDB = null;
        
    }
    
    /**
     * @return att_file_path을 리턴합니다.
     */
    public String getAtt_file_path() {
        return att_file_path;
    }
    /**
     * @return thumb_path을 리턴합니다.
     */
    public String getThumb_path() {
        return thumb_path;
    }
    
    // Window 계열에서 : 를 디렉토리로 못만드는 관계로.
    public static String getBid2BidPath(String bid) {
    	if (bid != null) {
    		return bid.replace(':','_');
    	} else {
    		return null;
    	}
    }
}
